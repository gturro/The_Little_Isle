package entities;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import events.Event;
import main.GamePanel;
import main.UtilityTool;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Rectangle;
import java.io.IOException;


public class Entity {

    GamePanel gp;


// ---------------------------------------NEED TO REARRENGE PROPERTIES ---------------------------------

            // WORLD POSITION
                protected int worldX, worldY;
                private float speed;
                
                private String entityName;

            // BUFFERED IMAGES
                // UP DOWN LEFT RIGHT
                protected BufferedImage left1, left2, left3, left4, right1, right2, right3, right4, idleRight1, idleRight2, idleRight3,
                        idleRight4, idleLeft1, idleLeft2, idleLeft3, idleLeft4;
                // ATACK
                protected BufferedImage attackLeft1, attackLeft2, attackLeft3, attackLeft4, attackLeft5, attackLeft6, attackRight1,
                        attackRight2, attackRight3, attackRight4, attackRight5, attackRight6;
                // DYING
                protected BufferedImage deadLeft1, deadLeft2, deadLeft3, deadLeft4, deadRight1, deadRight2, deadRight3, deadRight4;
            //EVENTS
                protected Event event;

            // DIRECTION
                private String direction = "idleLeft"; // objects direction is always idleRight

            // OBJECTS
                public BufferedImage image, image2, image3;
                private String objectName;
                private boolean hasCollision = false;

            // SPRITE IMAGE SETTINGS
                protected int spriteCounter = 0;
                protected int spriteNum = 1;
                private int spriteSpeed;

            // HITBOX
                private Rectangle hitBoxArea = new Rectangle(0, 0, 48, 48);
                private int hitBoxAreaDefX, hitBoxAreaDefY;

            //UTILITY TOOL
                UtilityTool uT;
                //ENTITY PROPERTIES
                private int maxHP;
                private int currentHP;
                
                public boolean collision = false;

// -----------------------------------------------------------------------------------------------------

    // CONSTRUCTOR
    public Entity(GamePanel gp) {
        this.gp = gp;
        this.uT = new UtilityTool(gp);
        
    }


    // UPDATE
    public void update() {
        
        if(uT.isVisible(worldX, worldY)) {
        //gp.eM.checkEvents();
        }
        spriteCounter++;
        if (spriteCounter > spriteSpeed) {
            switch (spriteNum) {
                case 1 -> spriteNum = 2;
                case 2 -> spriteNum = 3;
                case 3 -> spriteNum = 4;
                case 4 -> spriteNum = 1;
                default -> {
                }
            }
            spriteCounter = 0;
        }
    }
    
    // LOAD SCALED IMAGE
    protected BufferedImage setUp(String imgPath) {
        BufferedImage img = null;

        try {
            img = ImageIO.read(getClass().getResourceAsStream(imgPath + ".png"));
            img = uT.scaleImg(img, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }
   
    //up same as left
    protected BufferedImage getUpSprites(BufferedImage image){
            if (spriteNum == 1) {
                image = left1;
            }
            if (spriteNum == 2) {
                image = left2;
            }
            if (spriteNum == 3) {
                image = left3;
            }
            if (spriteNum == 4) {
                image = left4;
            }
    return image;
    }
    
 //GETS IMG OF CURRENT DIRECTION
    protected BufferedImage getDirectionImage(BufferedImage image) {
        try {
            switch (direction) {
                case "up" -> { image = getUpSprites(image); setDirection("idleLeft"); }
                case "down" -> { image = getDownSprites(image); setDirection("idleRight"); }
                case "left" -> { image = getLeftSprites(image); setDirection("idleLeft"); }
                case "right" -> {  image = getRightSprites(image); setDirection("idleRight"); }
                case "idleLeft" -> image = getIdleLeftSprites(image) ;
                case "idleRight" -> image = getIdleRightSprites(image);
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } 
        
        return image;
    }

    //down is the same as right. but maybe in a future will change..
    protected BufferedImage getDownSprites(BufferedImage image){
            if (spriteNum == 1) {
                image = right1;
            }
            if (spriteNum == 2) {
                image = right2;
            }
            if (spriteNum == 3) {
                image = right3;
            }
            if (spriteNum == 4) {
                image = right4;
            }
        return image;
    }

    protected BufferedImage getLeftSprites(BufferedImage image){
            if (spriteNum == 1) {
                image = left1;
            }
            if (spriteNum == 2) {
                image = left2;
            }
            if (spriteNum == 3) {
                image = left3;
            }
            if (spriteNum == 4) {
                image = left4;
            }
        return image;
    }

    protected BufferedImage getRightSprites(BufferedImage image){
            if (spriteNum == 1) {
                image = right1;
            }
            if (spriteNum == 2) {
                image = right2;
            }
            if (spriteNum == 3) {
                image = right3;
            }
            if (spriteNum == 4) {
                image = right4;
            }
        return image;
    }

    protected BufferedImage getIdleLeftSprites(BufferedImage image){
        resetSpriteSpeed();
            if (spriteNum == 1) {
                image = idleLeft1;
            }
            if (spriteNum == 2) {
                image = idleLeft2;
            }
            if (spriteNum == 3) {
                image = idleLeft3;
            }
            if (spriteNum == 4) {
                image = idleLeft4;
            }
        return image;
    }
    
    protected BufferedImage getIdleRightSprites(BufferedImage image){
        resetSpriteSpeed();
                if (spriteNum == 1) {
                    image = idleRight1;
                }
                if (spriteNum == 2) {
                    image = idleRight2;
                }
                if (spriteNum == 3) {
                    image = idleRight3;
                }
                if (spriteNum == 4) {
                    image = idleRight4;
                }
        return image;
    }
   
   
   
    // DRAW
    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        int screenX = worldX - gp.player.worldX + Player.screenX;
        int screenY = worldY - gp.player.worldY + Player.screenY;

        if(uT.isVisible(worldX, worldY)) {

            image = getDirectionImage(image);
            g2.drawImage(image, screenX, screenY, null);

            // DRAW COLISION AREA
            g2.setColor(Color.red);
            g2.drawRect(screenX + hitBoxArea.x, screenY + hitBoxArea.y, hitBoxArea.width, hitBoxArea.height);

            //DRAW WORLD POS
            g2.setColor(Color.blue);
            g2.drawRect(screenX, screenY, gp.tileSize, gp.tileSize);

            //DRAW EVENT AREA
            if (event!=null){
                g2.setColor(new Color(138, 43, 226, 127));
                g2.fillRect(screenX + event.getArea().x, screenY + event.getArea().y, event.getArea().width, event.getArea().height);
            }
        }
    }

    
//---------------------------------
// GET BUFFERED IMAGES OF PLAYER
    //GET ALL IMG
    public void getEntityImages(String entity){
        getIdleImages(entity);
        getWalkImages(entity);
    }

    //GET WALK IMG
    protected void getWalkImages(String entity){
        left1 = setUp("/entities/"+entity+"/left/left1");
        left2 = setUp("/entities/"+entity+"/left/left2");
        left3 = setUp("/entities/"+entity+"/left/left3");
        left4 = setUp("/entities/"+entity+"/left/left4");
        right1 = setUp("/entities/"+entity+"/right/right1");
        right2 = setUp("/entities/"+entity+"/right/right2");
        right3 = setUp("/entities/"+entity+"/right/right3");
        right4 = setUp("/entities/"+entity+"/right/right4");
    }

    //GET IDLE IMG
    protected void getIdleImages(String entity){
        idleRight1 = setUp("/entities/"+entity+"/right/idleRight1");
        idleRight2 = setUp("/entities/"+entity+"/right/idleRight2");
        idleRight3 = setUp("/entities/"+entity+"/right/idleRight3");
        idleRight4 = setUp("/entities/"+entity+"/right/idleRight4");
        idleLeft1 = setUp("/entities/"+entity+"/left/idleLeft1");
        idleLeft2 = setUp("/entities/"+entity+"/left/idleLeft2");
        idleLeft3 = setUp("/entities/"+entity+"/left/idleLeft3");
        idleLeft4 = setUp("/entities/"+entity+"/left/idleLeft4");
    }
//---------------------------------




// --------------  GETTERS & SETTERS  ---------------------------------

    //GET EVENT
    public Event getEvent() {
        return event;
    }
   
    //SET EVENT
    public void setEvent(Event event, int x, int y, int w, int h) {

    }

    //GET ENTITY NAME
    public String getEntityName() {
        return entityName;
    }

    //SET ENTITY NAME
    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    //GET SPEED
    public float getSpeed() {
        return speed;
    }

    //SET SPEED
    public void setSpeed(float speed) {
        this.speed = speed;
    }
    
    //GET OBJECT NAME
    public String getObjectName() {
        return objectName;
    }

    //SET OBJECT NAME
    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    //GET MAX HP
    public int getMaxHP() {
        return maxHP;
    }

    //SET MAX HP
    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    //GET CURRENT HP
    public int getCurrentHP() {
        return currentHP;
    }

    //SET CURRENT HP
    public void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
    }

    //GET WORLD X
    public int getWorldX() {
        return worldX;
    }

    //SET WORLD X
    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }

    //GET WORLD Y
    public int getWorldY() {
        return worldY;
    }

    //SET WORLD Y
    public void setWorldY(int worldY) {
        this.worldY = worldY;
    }


    public int getSpriteSpeed() {
        return spriteSpeed;
    }


    public void setSpriteSpeed(float f) {
        this.spriteSpeed = (int) f;
    }


    public void resetSpriteSpeed(){
        setSpriteSpeed(getSpeed()*6);
    }


    public String getDirection() {
        return direction;
    }


    public void setDirection(String direction) {
        this.direction = direction;
    }


    public Rectangle getHitBoxArea() {
        return hitBoxArea;
    }


    public void setHitBoxArea( int x, int y , int w, int h) {
        this.hitBoxArea.x = x;
        this.hitBoxArea.y = y;
        this.hitBoxAreaDefX = x;
        this.hitBoxAreaDefY = y;
        this.hitBoxArea.width = w;
        this.hitBoxArea.height = h;
    }


    public int getHitBoxAreaDefX() {
        return hitBoxAreaDefX;
    }


    public int getHitBoxAreaDefY() {
        return hitBoxAreaDefY;
    }


    public void setHitBoxAreaDefY(int hitBoxAreaDefY) {
        this.hitBoxAreaDefY = hitBoxAreaDefY;
    }


    public void setHitBoxAreaDefX(int hitBoxAreaDefX) {
        this.hitBoxAreaDefX = hitBoxAreaDefX;
    }


    public boolean isHasCollision() {
        return hasCollision;
    }


    public void setHasCollision(boolean hasCollision) {
        this.hasCollision = hasCollision;
    }


}