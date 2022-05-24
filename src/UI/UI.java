package UI;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import entities.Entity;
import main.GamePanel;
import objects.Heart_obj;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;

public class UI {

    protected GamePanel gp;

    public Graphics2D g2;

    private Font maruMonica;

    Color whiteColor = Color.white;

    BufferedImage heart_full, heart_half, heart_empty;

    public boolean messageDisplay = false;
    public boolean isBonfire = false;

    public String message = "";
    int messageTimer = 0;

    public UI(GamePanel gp) {
        this.gp = gp;
        
        InputStream is = getClass().getResourceAsStream("/fonts/MaruMonica.ttf") ;
        try {
            maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        

        Entity heart = new Heart_obj(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_empty = heart.image3;
    }

    public void showMessage (String text, int x, int y, int timer){
        message = text;
        messageDisplay = true;
        messageTimer = timer;
        drawMessage(timer, x , y);
    }

    private void drawMessage (int timer, int x, int y){
        if (messageTimer < 200){
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40));
            g2.drawString(message, x , y);
            messageTimer++;
        } else{
            messageTimer = 0;
            messageDisplay = false;
        }
        
    }

    //WELCOME TEXT
    public void drawWelcome(){
        messageDisplay = true;
        if (messageTimer < 150) {
            message = "WELCOME TO MY GAME!";
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40));
            g2.drawString(message, getXCentredText(message, gp.screenWidth/2), gp.screenHeight/2);
            messageTimer++;
        } else {
            messageTimer = 0;
            messageDisplay = false;
        }

    }

    //PAUSE TEXT
    private void displayPauseScreen() {
            message = "GAME PAUSED";
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 60));
            g2.drawString(message, getXCentredText(message, gp.screenWidth/2), gp.screenHeight/2);
    }

    //KEYS COUNT
    private void displayKeys(){
        if (gp.player.getKeys() > 0){
            message= "Keys = "+gp.player.getKeys();
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 25));
            g2.drawString(message, gp.tileSize, gp.tileSize*4);
        }

    }

    //COINS COUNT
    private void displayCoins(){
        if(gp.player.getCoins() > 0){
            message= "Coins = "+gp.player.getCoins();
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 25));
            g2.drawString(message, gp.tileSize, gp.tileSize*5);
        }
    }

    //END GAME TEXT
    public void endGameText(){
        g2.setColor(Color.YELLOW);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 50));
        message = "CONGRATULATIONS";
        g2.drawString(message, getXCentredText(message, gp.screenWidth/2), (gp.screenHeight/2) - (gp.tileSize*3));
        message = "YOU ENDED THE GAME!";
        g2.drawString(message, getXCentredText(message, gp.screenWidth/2), (gp.screenHeight/2) - (gp.tileSize));


    }

    //GET PERFECT CENTER SCREEN X FOR TEXT DISPLAY
    protected int getXCentredText(String displayText, int x) {
        int length = (int)g2.getFontMetrics().getStringBounds(displayText, g2).getWidth();
        int xCenter = x - length/2;
        return xCenter;
    }

    //DRAW HEARTS
    public void drawPlayerLife() {
        int x = gp.tileSize;
        int y = gp.tileSize - (gp.tileSize/3);
        
        int i = 0;
            while(i < gp.player.getCurrentHP()/2){
                g2.drawImage(heart_empty, x, y, null);
                i++;
                x+= gp.tileSize;
            }
        x = gp.tileSize;
        y = gp.tileSize - (gp.tileSize/3);
        i=0;

        
        while (i < gp.player.getCurrentHP()){
            g2.drawImage(heart_half, x, y, null);
            i++;
            if(i < gp.player.getCurrentHP()){
                g2.drawImage(heart_full, x, y, null);
            }
            i++;
            x+=gp.tileSize;
        }
    }


    public void draw (Graphics2D g2) {
        this.g2 = g2;

        g2.setFont(maruMonica);
        
        g2.setColor(whiteColor);


        //if (gp.gameState == gp.startState){drawWelcome();}
        
        //PLAY STATE
        if (gp.gameState == gp.playState) {
            drawPlayerLife();
            displayKeys();
            displayCoins();
        //PAUSE STATE
        }else if (gp.gameState == gp.pauseState) {
            displayPauseScreen();
            drawPlayerLife();
        //END GAME STATE
        } else if (gp.gameState == gp.endGameState) {
            endGameText();
            //end game stuffffffff
        }
        //MESSAGE DISPLAY

    }

    public Font getMaruMonica() {
        return maruMonica;
    }

    public void setMaruMonica(Font maruMonica) {
        this.maruMonica = maruMonica;
    }
}