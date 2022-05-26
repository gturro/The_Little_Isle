package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import entities.Player;
import java.io.IOException;

public class UtilityTool {
    GamePanel gp;

    public UtilityTool(GamePanel gp){
        this.gp = gp;
    }

    //returns a scaled BufferedImg
    public BufferedImage setUp(String imgPath, int w, int h) {
        BufferedImage img = null;

        try {
            img = ImageIO.read(getClass().getResourceAsStream(imgPath + ".png"));
            img = scaleImg(img, w, h);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }
    
    //scales Buffered img with static w, h
    public BufferedImage scaleImg (BufferedImage originalImg, int w, int h){
        BufferedImage scaledImg = new BufferedImage(w, h, originalImg.getType()); //canvas vacio
        Graphics2D g2 = scaledImg.createGraphics(); //elementos creados en g2 se almacenan en scaledImg
        g2.drawImage(originalImg, 0, 0, w, h, null);
        g2.dispose();
        return scaledImg;
    } 
    //scales Buffered img with dinamic w, h
    public BufferedImage scaleImg (BufferedImage originalImg, int x, int y, int w, int h){
        BufferedImage scaledImg = new BufferedImage(w, h, originalImg.getType()); //canvas vacio
        Graphics2D g2 = scaledImg.createGraphics(); //elementos creados en g2 se almacenan en scaledImg
        g2.drawImage(originalImg, x, y, w, h, null);
        g2.dispose();
        return scaledImg;
    } 

    //check if something with world pos is visible on screen
    public boolean isVisible(int worldX, int worldY){
        boolean isVisible = false;

        int boundaryXRight = worldX - gp.tileSize;
        int boundaryXLeft = worldX + gp.tileSize;
        int boundaryYUp = worldY + gp.tileSize;
        int boundaryYBottom = worldY - gp.tileSize;

        if (boundaryXLeft > gp.player.getWorldX() - Player.screenX &&
            boundaryXRight < gp.player.getWorldX() + Player.screenX &&
            boundaryYUp > gp.player.getWorldY() - Player.screenY &&
            boundaryYBottom < gp.player.getWorldY() + Player.screenY) 
            
            isVisible = true;

        return isVisible;
    }

    //check if deamon is visible acording with the offset position of deamon due to diferent scale format
    public boolean isDemonVisible(int worldX, int worldY){
        boolean isVisible = false;
        
        //deamon boundary diferent becasue offset resolution display
        //worldX = [x value] -(gp.tileSize*2); scale correction
        //worldY = [x value] -(gp.tileSize*2);
        int boundaryXRight = worldX - (gp.tileSize*3);
        int boundaryXLeft = worldX + (gp.tileSize*3);
        int boundaryYUp = worldY + (gp.tileSize*3);
        int boundaryYBottom = worldY - (gp.tileSize*3);

        if (boundaryXLeft > gp.player.getWorldX() - Player.screenX &&
            boundaryXRight < gp.player.getWorldX() + Player.screenX &&
            boundaryYUp > gp.player.getWorldY() - Player.screenY &&
            boundaryYBottom < gp.player.getWorldY() + Player.screenY) 
            
            isVisible = true;

        return isVisible;
    }

    //images for loadMenu
    public ArrayList<BufferedImage> getIdleImages(String entity, ArrayList <BufferedImage> classImg, int w, int h){
        BufferedImage img = null;
        img = setUp("/entities/"+entity+"/right/idleRight1", w, h); classImg.add(img);
        img = setUp("/entities/"+entity+"/right/idleRight2", w, h); classImg.add(img);
        img = setUp("/entities/"+entity+"/right/idleRight3", w, h); classImg.add(img);
        img = setUp("/entities/"+entity+"/right/idleRight4", w, h); classImg.add(img);
        img = setUp("/entities/"+entity+"/right/right1", w, h); classImg.add(img);
        img = setUp("/entities/"+entity+"/right/right2", w, h); classImg.add(img);
        img = setUp("/entities/"+entity+"/right/right3", w, h); classImg.add(img);
        img = setUp("/entities/"+entity+"/right/right4", w, h); classImg.add(img);

        return classImg;
    } 

     
}