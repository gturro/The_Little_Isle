package menus;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.sql.SQLException;
import java.util.ArrayList;

import LocalSavedGames.BinFile;
import dataBase.DataBase;
import dataBase.Slot;
import entities.Mage;
import entities.Human;
import entities.Warrior;

import java.awt.BasicStroke;

import main.GamePanel;

public class LoadMenu extends Menu{

    private Slot [] savedGames;
    
    private boolean menuDrawed = false;

    private boolean savedGamesEmpty = true;

    private boolean noConnection = false;

    //COMUN
    private int x, y, w, h;

    //BORDER
    private int borderX, borderY;
    private final int boardStroke = 7;

    //
    private int newGameSelector = 0;
    private int menuSelector = 1;

    //BOARD
    private int boardX, boardY, boardW, boardH;

    private int counter = 0;
    
    //COLORS
    private final Color white = new Color(255, 255, 255);
    private final Color boardBorder = new Color(255, 255, 255, 30);
    private final Color gridC = new Color(255, 255, 255, 150);
    private final Color slotsText = new Color(255, 255, 255 , 100);
    private final Color titleColor  = new Color(100, 0, 160, 230);
    private Color c;

    //FONTS DERIVATIVES
    private final Font optionsFont = getMaruMonica().deriveFont(30F);

    public LoadMenu(GamePanel gp) {
        super(gp);
        setMaxSelector(6); // amount of possible selections
    }

 
    //DATABASE
    //get saved games
    public void getSlots() {
        DataBase db;
        if(!slotsLoaded()){
            try {
                db = new DataBase();
                savedGames = db.getGameData();
                logSlots(savedGames);  
            } catch (SQLException e) {
                e.printStackTrace();
                noConnection = true;
                savedGames = BinFile.loadGame();
            } finally {
                setSlotsLoaded(true);
                checkIfSlotsAreEmpty();
            }
        }   
    }

    private void drawNoConnec() {
        String text="No connection";
        g2.setColor(new Color(255,0,0,180));
        x = gp.tileSize * 11;
        y = gp.tileSize * 2;
        g2.drawString(text, x, y);
    }

    
    private void logSlots(Slot[] savedGames) {
            for (int i = 0; i < savedGames.length; i++) {
                System.out.println("Slot "+(i+1)+" -->"+savedGames[i].toString());
            }
    }

    public void resetLoadMenu() {
        setSlotsLoaded(false);
        menuDrawed = false;
        newGameSelector = 0;
        menuSelector = 1;
    }
    
    //TITLE
    private void drawTitle() {
        String loadTitle = "Saved Games";
        x = gp.tileSize*2;
        y = gp.tileSize*2;
        g2.setColor(titleColor);
        g2.setFont(g2.getFont().deriveFont(Font.ITALIC, 65F));
        g2.drawString(loadTitle, x, y);
    }
    
  

    //DRAW BOARD
    int colWidth = gp.tileSize*3;
    int rowHeight = gp.tileSize;
    private void drawBoard() {
        c = new Color(100,100,100, 45);
        g2.setColor(c);
        boardX = gp.tileSize*2;
        boardY = gp.tileSize*3;
        boardW = gp.tileSize*14;
        boardH = gp.tileSize*6;
        g2.fillRoundRect(boardX, boardY, boardW, boardH, 40, 40);
        drawBoardBorder(boardX, boardY, boardW, boardH);
        g2.setStroke(new BasicStroke(1));
        drawSlots();

        if(!savedGamesEmpty) {
        //ROWS
        int rows = savedGames.length;
        x = boardX+boardStroke;
        y = gp.screenHeight - boardH -boardY + rowHeight;
            for (int i = 0; i < rows; i++){
                if (getSelector() == i) {
                    drawBorder(titleColor, x, y + i*rowHeight, boardW-boardStroke*2, rowHeight, 8, 8, 2);
                    g2.setColor(gridC);
                }
            }     
        }
        
    }

    private void drawSlots() {
        int col = 4;
        x = gp.screenWidth - boardW - boardX+colWidth+(colWidth/2);
        y = boardY + gp.tileSize - boardStroke;
        //COLS
        for (int i = 0; i < col ; i++) {
            int tempI = i;

            int nextCol = x + i*colWidth -colWidth/2;
            g2.setColor(new Color(255,0,0,180));
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));
            if (i == 0) { drawInCenterCell(i, x  - x/3 , y,  "Name"); }
            if (i == 1) { drawInCenterCell(i, nextCol , y,  "Class"); }
            if (i == 2) { drawInCenterCell(i, nextCol, y,  "Coins"); } 
            if (i == 3) { drawInCenterCell(i, nextCol, y,  "Time"); }
            if (!savedGamesEmpty){
                for (i = 0; i < savedGames.length; i++) {
                        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 35F));
                        g2.setColor(slotsText);
                        int nextRow = (i * rowHeight) + rowHeight;
                        drawInCenterCell(i, x - x/3, y + nextRow, savedGames[i].getUserName());
                        drawInCenterCell(i, x + colWidth/2, y +nextRow,  savedGames[i].getClassPlayer());
                        drawInCenterCell(i, x + colWidth + colWidth/2, y +nextRow,  String.valueOf(savedGames[i].getCoins()));
                        drawInCenterCell(i, x + colWidth*2 + colWidth/2, y + nextRow, savedGames[i].getGameTime()+" s");
                }
                i=tempI;
            }
        }
        if (savedGamesEmpty) { 
            drawEmptyText(); 
        }
        if (noConnection) {
            drawNoConnec();
        }
    }

    private void checkIfSlotsAreEmpty () {
        resetSelector();
        if(savedGames == null || savedGames.length == 0){
            setMaxSelector(1);

        } else{
            savedGamesEmpty = false;
            newGameSelector+=savedGames.length;
            menuSelector+=savedGames.length;
            setMaxSelector(menuSelector);
        }
    }

    //NEW GAME && MENU
    private void drawOptions(){
        drawNewGame();
        drawMainMenu();
    }
    //NEW GAME BUTTON
    private void drawNewGame(){
        String newGame = "NEW GAME";
        x= gp.tileSize*2;
        y= gp.tileSize*11;
        g2.setColor(white);
        g2.setFont(optionsFont);
        g2.drawString(newGame, x, y);
        drawTextBorder(x, y, newGame);
        if (getSelector() == newGameSelector) {
            drawSelectionBorder(borderX, borderY, w, h);
        }

    }
    //MENU BUTTON
    private void drawMainMenu(){
        String mainMenu = "MENU";
        x= gp.tileSize*14;
        y= gp.tileSize*11;
        g2.setColor(white);
        g2.setFont(optionsFont);
        g2.drawString(mainMenu, x, y);
        drawTextBorder(x, y, mainMenu);
        if (getSelector() == menuSelector) {
            drawSelectionBorder(borderX, borderY, w, h);
        }
    }
    

    //UTILS-----
    //board border
    private void drawBoardBorder(int x, int y, int w, int h) {
        drawBorder(boardBorder, x, y, w, h, 30, 30, boardStroke);
    }
    //text border
    private void drawTextBorder(int x, int y, String text) {
        borderX = x + (int)g2.getFontMetrics().getStringBounds(text, g2).getX()-5;
        borderY = y + (int)g2.getFontMetrics().getStringBounds(text, g2).getY()-2;
        w = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth()+11;
        h = (int)g2.getFontMetrics().getStringBounds(text, g2).getHeight()+5;
        drawBorder(white, borderX, borderY, w, h, 5, 5, 2);
    }
    //selection border
    private void drawSelectionBorder(int x, int y, int w, int h){
        drawBorder(titleColor, x, y, w, h, 5, 5, 4);
    }
    //border
    private void drawBorder(Color c, int x, int y, int w, int h, int arcW, int arcH, int stroke) {
        g2.setColor(c);
        g2.setStroke(new BasicStroke(stroke));
        g2.drawRoundRect(x, y, w, h, arcW, arcH);
    }
    //DRAW IN CENTER CELL
    private void drawInCenterCell(int i, int x, int y, String cellText){
            if(i == 0){
                g2.drawString(cellText, getXCentredText(cellText, x), y);
            }else{
                g2.drawString(cellText, getXCentredText(cellText, x), y);
            }
        
    }
    //LOADING 
    private void drawLoading(){
        String loading = "Loading";
        x = gp.tileSize * 11;
        y = gp.tileSize * 2;
        g2.setColor(Color.yellow);
        g2.setFont(optionsFont);

        g2.drawString(loading, x, y);
        drawLoadingPoints();
    }
    //loading points
    private void drawLoadingPoints(){
        x = gp.tileSize*13;
        
        g2.setColor(Color.yellow);
            if(counter > 15){
                g2.drawLine(x, y, x, y);
                x += gp.tileSize/2;  
            }
            if (counter >= 30){
                g2.drawLine(x, y, x, y);
                x += gp.tileSize/2;
            } 
            if (counter >= 45){
                g2.drawLine(x, y, x, y);
                x += gp.tileSize/2;
            }
            if (counter >= 80){
                counter = 0;
            }
        counter++;
    }
    //slots Empty text
    private void drawEmptyText() {
        String text="NO SAVED GAMES";
        g2.setColor(new Color(255,0,0,180));
        g2.drawString(text, getXCentredText(text, gp.screenWidth/2), gp.screenHeight/2);
    }
    
    private void loadClass(int slot) {
        switch (savedGames[slot].getClassPlayer()) {
            case "mage":
                gp.player = new Mage(gp);
                break;
            case "warrior":
                gp.player = new Warrior(gp);
                break;
            default:
                gp.player = new Human(gp);
                break;
        }
    }
    private void loadGame(int slot) {
        loadClass(slot);
        gp.player.setGameID(savedGames[slot].getGameID());
        gp.player.setPlayerName(savedGames[slot].getUserName());
        gp.player.setClasse(savedGames[slot].getClassPlayer());
        gp.player.setCoins(savedGames[slot].getCoins());
        gp.player.setKeys(savedGames[slot].getKeys());
        gp.setGameTime(savedGames[slot].getGameTime());
    }

    //CHECK KEY INPUT
    @Override
    public void checkKey(){
        super.checkKey();
        System.out.println("Selector: "+getSelector());
        if (gp.keyH.enterPressed){
            //LOAD GAME SLOT
            if (getSelector() < newGameSelector && !savedGamesEmpty) { loadGame(getSelector()); gp.gameState = gp.playState; }
            //NEWGAME
            if (getSelector() == newGameSelector) { gp.menuState = gp.selectClassMenu; resetSelector(); }
            //MENU
            if (getSelector() == menuSelector) { gp.menuState = gp.mainMenu; resetSelector();}
            resetLoadMenu();
        }
    }

    //DRAW LOAD PAGE
    public void draw(Graphics2D g2) {
        this.g2 = g2;
        super.draw(g2);
        
        drawTitle();
        drawBoard();
        drawOptions();

        if (!menuDrawed){
            drawLoading();
            menuDrawed=true;
        } else if (!slotsLoaded()) { 
            getSlots();
        } 
    }
}