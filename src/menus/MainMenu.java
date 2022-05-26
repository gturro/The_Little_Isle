package menus;

import main.GamePanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class MainMenu extends Menu{

    BufferedImage longinus;

    public MainMenu(GamePanel gp){
        super(gp);
        longinus = uT.setUp("/objects/longinus_Sword", gp.tileSize*3, gp.tileSize*2); 
    }


    private void drawTitle(){
        String title = "THE LITTLE ISLE";
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 90F));
        g2.setColor(Color.MAGENTA);
        int x = getXCentredText(title, gp.screenWidth/2);
        g2.drawString(title, x, gp.tileSize*3);
    }

    private void drawNewGame(){
        String newGame = "New Game";
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 20));
        g2.setColor(Color.WHITE);
        int x = getXCentredText(newGame, gp.screenWidth/2);
        int y = gp.tileSize*8;
        g2.drawString(newGame, x, y);
        if(getSelector() == 0){
            g2.drawImage(longinus, x-(gp.tileSize*3), y-(gp.tileSize)-gp.tileSize/6, null);
        }
    }

    private void drawLoadGame(){
        String loadGame = "Load Game";
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 20));
        g2.setColor(Color.WHITE);
        int x = getXCentredText(loadGame, gp.screenWidth/2);
        int y = gp.tileSize * 9;
        g2.drawString(loadGame, x, y);
        if(getSelector() == 1){
            g2.drawImage(longinus, x-(gp.tileSize*3), y-(gp.tileSize)-gp.tileSize/6, null);
        }
    }

    private void drawExit(){
        String exit = "Exit";
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 20));
        g2.setColor(Color.WHITE);
        int x = getXCentredText(exit, gp.screenWidth/2);
        int y = gp.tileSize * 10;
        g2.drawString(exit, x, y);
        if(getSelector() == 2){
            g2.drawImage(longinus, x-(gp.tileSize*3), y-(gp.tileSize)-gp.tileSize/6, null);
        }
    }

    @Override
    public void checkKey(){
        super.checkKey();
        if (gp.keyH.enterPressed){
            //NEWGAME
            if (getSelector() == 0) { gp.menuState = gp.selectClassMenu; }
            //LOAD GAME
            if (getSelector() == 1) { 
                gp.menuState = gp.loadMenu; 
                resetSelector();
            }
            //EXIT GAME
            if (getSelector()== 2) { System.exit(0); }
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        this.g2 = g2;
        super.draw(g2);
        drawTitle();
        drawNewGame();
        drawLoadGame();
        drawExit();
    }
}