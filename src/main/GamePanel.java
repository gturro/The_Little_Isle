package main;

import java.awt.Color;
import java.awt.Dimension;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;

import UI.*;
import dataBase.DataBase;
import entities.*;
import events.Event;
import tile.TileManeger;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    public final int originalTileSize = 24;
    final int scale = 2;
    //tile size
    public final int tileSize = originalTileSize * scale; // 48x48
    public final int maxScreenCol = 18;
    public final int maxScreenRow = 12;
    //W and H
    public final int screenWidth = tileSize * maxScreenCol; //
    public final int screenHeight = tileSize * maxScreenRow; //

    // MAP SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    // GAME STATE
    public final int titleState = 0; // TITLE SCREEN
    public final int startState = 1; // ?? maybe fade in to game
    public final int playState = 2; // PLAY STATE
    public final int pauseState = 3; // PAUSE STATE
    public final int endGameState = 4; // ENDGAME STATE
    public int gameState;

    // SUBMENU STATE
    public final int mainMenu = 5; // MAIN MENU
    public final int selectClassMenu = 6; // SELECT CLASS MENU
    public final int loadMenu = 7; // LOAD MENU
    public int menuState = mainMenu;

    //TIMER
    private float gameTime;
    private boolean gameSaved = false;

    // FPS
    final double FPS = 60;

    //GAME THREAD
    Thread gameThread;

    //DATABASE
    public DataBase dB;

    //---------------- UTILITIES ----------------
    //events
    public EventManager eM = new EventManager(this);
    //menus
    public MenuHandler mH = new MenuHandler(this);
    //assets
    public AssetsSetter aSetter = new AssetsSetter(this);
    //colision
    public CollisionDetection collisionDetection = new CollisionDetection(this);
    //ui
    public UI ui = new UI(this);
    //tiles
    public TileManeger tileM = new TileManeger(this);
    //keys Input
    public KeyHandler keyH = new KeyHandler(this);

    //---------------- LISTS ----------------
    //events
    public Event events[] = new Event[10];
    //entites
    ArrayList<Entity> entityList = new ArrayList<>();
    //enemies
    public Entity enemies[] = new Entity[4];
    //objects
    public Entity objects[] = new Entity[11];



    //---------------- PLAYER ----------------
    public Player player;


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // Enable bufferedImage capability
        this.addKeyListener(keyH); // gamepanel keys input activated
        this.setFocusable(true);

    }

    //SET ASSETS
    public void setUpGame() {
        aSetter.setEnemies();
        aSetter.setObject();
        aSetter.setEvents();
        gameState = titleState;
        try {
            setDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void setDatabase() throws SQLException {
        dB = new DataBase();
    }

    // GAME LOOP (delta)
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;

            timer += (currentTime - lastTime);

            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }

        }
    }

    // GAME LOOP (sleep)
    /*
     * public void run() {
     * 
     * double drawInterval = 1000000000 / FPS;
     * double nextDrawInterval = System.nanoTime() + drawInterval;
     * 
     * while(gameThread != null){
     * 
     * update();
     * repaint();
     * 
     * try {
     * double remainingTime = nextDrawInterval - System.nanoTime();
     * remainingTime /= 1000000;
     * 
     * if (remainingTime < 0) {
     * remainingTime = 0;
     * }
     * 
     * nextDrawInterval += drawInterval;
     * 
     * Thread.sleep((long) remainingTime);
     * } catch (InterruptedException e) {
     * e.printStackTrace();
     * }
     * }
     * }
     */
    //save game to dataBase
     private void saveGame() throws SQLException {
        dB.saveGameData(player.getPlayerName(), player.getClasse() ,player.getCoins(), player.getKeys(), (double) (Math.round(gameTime*100.0)/100.0));
        System.out.println("Time saved:"+ (Math.round(gameTime*100.0)/100.0));
        System.out.println("Game Saved");
        
    }

    //update assets
    public void update() {
        
        if (gameState == titleState) {}

        if (gameState == playState) {
            gameTime += 1/FPS;
            System.out.println("GAME TIME: "+ gameTime);
            player.update();
            for (Entity e : enemies) { if (e != null) {e.update();} }
        }

        if (gameState == pauseState) {}

        if (gameState == endGameState) {
            if (!gameSaved){
                try {
                    saveGame();
                    gameSaved = true;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                gameTime = 0;
            } 

            
        }
    }

    //draw
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        
        if (gameState == titleState) {
            mH.drawMenu(g2);
        } else {
            
            // DRAW TILES 1st
            tileM.draw(g2);
            // ENTITY LIST
            entityList.add(player);
            // add enemies
            for (int i = 0; i < enemies.length; i++) {
                if (enemies[i] != null) {
                    entityList.add(enemies[i]);
                }
            }
            // add objects
            for (int i = 0; i < objects.length; i++) {
                if (objects[i] != null) {
                    entityList.add(objects[i]);
                }
            }
            // sort list by worldY
            Collections.sort(entityList, new Comparator<Entity>() {

                @Override
                public int compare(Entity e1, Entity e2) {
                    int result = Integer.compare(e1.getWorldY(), e2.getWorldY());
                    return result;
                }
            });

            // DRAW ENTITIES
            for (int i = 0; i < entityList.size(); i++) {
                entityList.get(i).draw(g2);
            }
            entityList.clear();

            // DRAW EVENTS
            for (int i = 0; i < events.length; i++) {
                if (events[i] != null) {
                    events[i].drawEvents(g2, events[i].getArea());
                }
            }

            // DRAW UI
            ui.draw(g2);
        }

        // after ALL draws release resources
        g2.dispose();
    }

    //SETTERS & GETTERS
    public boolean isGameSaved() {
        return gameSaved;
    }

    public void setGameSaved(boolean gameSaved) {
        this.gameSaved = gameSaved;
    }

    public float getGameTime() {
        return gameTime;
    }

    public void setGameTime(float gameTime) {
        this.gameTime = gameTime;
    }

}