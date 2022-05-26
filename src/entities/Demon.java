package entities;

import javax.imageio.ImageIO;

import events.Event;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.Graphics2D;
import java.awt.Color;

import java.util.Random;

import main.GamePanel;

public class Demon extends Entity {

    private int spriteSpeed;

    private int movingPeriod = 0;

    private int idleTimer = 0;

    private BufferedImage left5, left6, right5, right6, idleRight5, idleRight6, idleLeft5, idleLeft6;

    public Event demonEvent;

    private final String ENTITY_NAME = "demon";

    public Demon(GamePanel gp) {
        super(gp);
        this.setSpeed(1);
        setEntityName(ENTITY_NAME);
        getEntityImages(ENTITY_NAME);

        //DEMON TRIIGER ZONE
        /* int x = 0;
        int y = 0;
        int w = gp.tileSize * 5;
        int h = gp.tileSize * 5;
        DemonTriggerZone triggerZone = new DemonTriggerZone(gp, this, x, y, w, h); */

        // x, y, w, h
        setHitBoxArea((gp.tileSize * 2) + (gp.originalTileSize / 3), 
                      (gp.tileSize * 2), 
                       gp.tileSize - (gp.tileSize / 3),
                       gp.tileSize + (gp.tileSize / 2));
    }

    @Override
    protected void getWalkImages(String entity) {
        super.getWalkImages(entity);
        left5 = setUp("/entities/" + entity + "/left/left5");
        left6 = setUp("/entities/" + entity + "/left/left6");
        right5 = setUp("/entities/" + entity + "/right/right5");
        right6 = setUp("/entities/" + entity + "/right/right6");
    }

    @Override
    protected void getIdleImages(String entity) {
        super.getIdleImages(entity);
        idleRight5 = setUp("/entities/" + entity + "/right/idleRight5");
        idleRight6 = setUp("/entities/" + entity + "/right/idleRight6");
        idleLeft5 = setUp("/entities/" + entity + "/left/idleLeft5");
        idleLeft6 = setUp("/entities/" + entity + "/left/idleLeft6");

    }

    @Override
    protected BufferedImage setUp(String imgPath) {

        BufferedImage img = null;
        try {
            img = ImageIO.read(getClass().getResourceAsStream(imgPath + ".png"));
            img = uT.scaleImg(img, gp.tileSize * 5, gp.tileSize * 4);
        } catch (IllegalArgumentException | IOException e) {
            System.out.println("\n      Error getting img: " + imgPath + "\n" + e.toString());
        }
        return img;
    }

    //logic demon walking
    private void demonGuard() {
        movingPeriod++;
        if (movingPeriod == 150) {
            Random r = new Random();
            int i = r.nextInt(200) + 1;

            if (i <= 25) {
                setDirection("up");
            }
            if (i > 25 && i <= 50) {
                setDirection("down");
            }
            if (i > 50 && i <= 75) {
                setDirection("left");
            }
            if (i > 75 && i <= 100) {
                setDirection("right");
            }
            if (i > 100 && i <= 125) {
                setDirection("idleRight");
            }
            if (i > 125 && i <= 150) {
                setDirection("idleLeft");
            }
            movingPeriod = 0;
        }
    }

    @Override
    public void update() {
        if ("idleLeft".equals(getDirection())) {
        } else {
            demonGuard();
        }
        collision = false;
        gp.collisionDetection.tileCheck(this);
        gp.collisionDetection.checkColisionWithPlayer(this);

        if (collision == false) {
            switch (getDirection()) {
                case "up" -> worldY -= getSpeed();
                case "down" -> worldY += getSpeed();
                case "left" -> worldX -= getSpeed();
                case "right" -> worldX += getSpeed();
            }
        } else {
            switch (getDirection()) {
                case "up" -> setDirection("down");
                case "down" -> setDirection("up");
                case "left" -> setDirection("right");
                case "right" -> setDirection("left");
            }
        }

        // IDLE POSITION COUNTER
        idleTimer++;
        if (idleTimer > 150 && "idleLeft".equals(getDirection())) {
            setDirection("idleRight");
            idleTimer = 0;
        } else if (idleTimer > 150 && "idleRight".equals(getDirection())) {
            setDirection("idleLeft");
            idleTimer = 0;
        }

        // SPRITE SPEED
        if ("idleLeft".equals(getDirection()) || "idleRight".equals(getDirection())) {
            spriteSpeed = 10;
        }
        if ("up".equals(getDirection()) || "down".equals(getDirection()) || "left".equals(getDirection())
                || "right".equals(getDirection())) {
            spriteSpeed = 20;
        }

        // SPRITE COUNTER
        spriteCounter++;
        if (spriteCounter > spriteSpeed) {
            switch (spriteNum) {
                case 1 -> spriteNum = 2;
                case 2 -> spriteNum = 3;
                case 3 -> spriteNum = 4;
                case 4 -> spriteNum = 5;
                case 5 -> spriteNum = 6;
                case 6 -> spriteNum = 1;
                default -> {
                }
            }
            spriteCounter = 0;
        }
    }

    /*
     * //up same as left
     * 
     * @Override
     * protected BufferedImage getUpSprites(BufferedImage image){
     * super.getUpSprites(image);
     * if (spriteNum == 5){
     * image = left5;
     * }
     * if (spriteNum == 6){
     * image = left6;
     * }
     * return image;
     * }
     * 
     * //down is the same as right. but maybe in a future will change..
     * 
     * @Override
     * protected BufferedImage getDownSprites(BufferedImage image){
     * super.getDownSprites(image);
     * if (spriteNum == 5){
     * image = right5;
     * }
     * if (spriteNum == 6){
     * image = right6;
     * }
     * return image;
     * }
     */

    @Override
    protected BufferedImage getLeftSprites(BufferedImage image) {
        image = super.getLeftSprites(image);
        if (spriteNum == 5) {
            image = left5;
        }
        if (spriteNum == 6) {
            image = left6;
        }
        return image;
    }

    @Override
    protected BufferedImage getRightSprites(BufferedImage image) {
        image = super.getRightSprites(image);

        if (spriteNum == 5) {
            image = right5;
        }
        if (spriteNum == 6) {
            image = right6;
        }
        return image;
    }

    @Override
    protected BufferedImage getIdleLeftSprites(BufferedImage image) {
        image = super.getIdleLeftSprites(image);
        if (spriteNum == 5) {
            image = idleLeft5;
        }
        if (spriteNum == 6) {
            image = idleLeft6;
        }
        return image;
    }

    @Override
    protected BufferedImage getIdleRightSprites(BufferedImage image) {
        image = super.getIdleRightSprites(image);
        if (spriteNum == 5) {
            image = idleRight5;
        }
        if (spriteNum == 6) {
            image = idleRight6;
        }
        return image;
    }

    @Override
    protected BufferedImage getDirectionImage(BufferedImage image) {
        switch (getDirection()) {
            case "up":
                image = getLeftSprites(image);
                break;
            case "down":
                image = getRightSprites(image);
                break;
            case "left":
                image = getLeftSprites(image);
                break;
            case "right":
                image = getRightSprites(image);
                break;
            case "idleRight":
                image = getIdleRightSprites(image);
                break;
            case "idleLeft":
                image = getIdleLeftSprites(image);
                break;
        }
        return image;
    }

    @Override
    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        int screenX = worldX - gp.player.worldX + Player.screenX;
        int screenY = worldY - gp.player.worldY + Player.screenY;

        if (uT.isDemonVisible(worldX, worldY)) {

            image = getDirectionImage(image);
            g2.drawImage(image, screenX, screenY, null);

            // DRAW COLISION AREA
            g2.setColor(Color.red);
            g2.drawRect(screenX + getHitBoxArea().x, screenY + getHitBoxArea().y, getHitBoxArea().width,
                    getHitBoxArea().height);

            // DRAW WORLD POS
            g2.setColor(Color.blue);
            g2.drawRect(screenX, screenY, gp.tileSize, gp.tileSize);
        }
    }
}