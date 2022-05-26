package menus;

import java.awt.Graphics2D;
import java.util.ArrayList;
import entities.Mage;
import entities.Human;
import entities.Warrior;

import java.awt.Color;
import java.awt.Font;
import java.awt.BasicStroke;
import java.awt.image.BufferedImage;


import main.GamePanel;

public class SelectClassMenu extends Menu{

    private final Color titleColor  = new Color(100, 0, 160, 230);

    private int spriteCounter = 0;
    private int spriteNum = 0;
    private final int spriteSpeed = 34;



    private ArrayList<BufferedImage> mageImg; 
    private ArrayList<BufferedImage> normalImg;
    private ArrayList<BufferedImage> warriorImg;

    public SelectClassMenu(GamePanel gp) {
        super(gp);
        mageImg = new ArrayList<>(); 
        normalImg = new ArrayList<>(); 
        warriorImg = new ArrayList<>();
        setMaxSelector(2);
        getImages();
    }

 
    //UI

    private void drawTitle() {
        String loadTitle = "CHOOSE YOUR CLASS";
        g2.setColor(titleColor);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 65F));
        g2.drawString(loadTitle, getXCentredText(loadTitle, gp.screenWidth/2), gp.tileSize*2);
    }

    private void drawAllClasses () {
        int x = gp.screenWidth/2-gp.tileSize;
        int y = gp.screenHeight/2 -gp.tileSize;
        g2.setFont(g2.getFont().deriveFont(40F));
        g2.drawString("MAGE", x- gp.tileSize*4, y-gp.tileSize);
        drawSprite(mageImg, x - gp.tileSize*4, y);
        g2.setStroke(new BasicStroke(4));
        if (getSelector() == 0){
            g2.drawRoundRect(x - gp.tileSize*4, y, gp.tileSize*2, gp.tileSize*2, 20, 20);
        }
        g2.drawString("HUMAN", x, y-gp.tileSize);
        drawSprite(normalImg, x, y);
        if (getSelector() == 1){
            g2.drawRoundRect(x, y, gp.tileSize*2, gp.tileSize*2, 20, 20);
        }
        g2.drawString("WARRIOR", x+ gp.tileSize*4, y-gp.tileSize);
        drawSprite(warriorImg, x + gp.tileSize*4, y);
        if (getSelector() == 2){
            g2.drawRoundRect(x + gp.tileSize*4, y, gp.tileSize*2, gp.tileSize*2, 20, 20);
        }
    }

    private void drawSprite(ArrayList<BufferedImage> imgArray, int x , int y) {
        
        BufferedImage img = null;
        spriteCounter++;
        if (spriteCounter > spriteSpeed) {
            spriteNum++;
        
            if (spriteNum == imgArray.size()-1) { spriteNum = 0; }
            spriteCounter = 0;
        }
        img = imgArray.get(spriteNum);
        g2.drawImage(img, x, y, null);
        
    }

    private void drawInputName() {
        String text = "ENTER YOUR NAME IN THE TERMINAL";
        g2.drawString(text, getXCentredText(text, gp.screenWidth/2), gp.screenHeight-gp.tileSize*2);
        /* Scanner s = new Scanner(System.in);
        String playerName = s.nextLine();
        gp.player.setPlayerName(playerName); */
    }

    private void getImages() {
        int w = gp.tileSize*2;
        int h = gp.tileSize*2;
        mageImg = uT.getIdleImages("mage", mageImg, w, h);
        normalImg = uT.getIdleImages("human", normalImg, w, h);

        warriorImg = uT.getIdleImages("warrior", warriorImg, w, h);
    } 

    //CHECK KEY INPUT
    @Override
    public void checkKey(){
        super.checkKey();
        String selectedClass = null;
        System.out.println("Class to load: "+selectedClass);

        if (gp.keyH.enterPressed){
            //SET CLASS MAGE
            if (getSelector() == 0) { 
                gp.player = new Mage(this.gp);
                gp.player.setClasse("mage");
            }
            //SET CLASS NORMAL
            if (getSelector() == 1) { 
                gp.player = new Human(this.gp); 
                gp.player.setClasse("human");
            }
            //SET CLASS WARRIOR
            if (getSelector() == 2) { 
                gp.player = new Warrior(this.gp); 
                gp.player.setClasse("warrior");
            }
            gp.gameState = gp.playState;
            gp.setGameSaved(false);
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        this.g2 = g2;
        super.draw(g2);
        drawTitle();

        drawAllClasses();
        drawInputName();
    }
    
}