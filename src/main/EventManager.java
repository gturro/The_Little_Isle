package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import entities.Player;


// --------------------------------  ON PROGRESS ---------------------------------

public class EventManager {
    

    public int worldX;
    public int worldY;

    GamePanel gp;

    UtilityTool uT;
    
    //private boolean trigger = false;

    public EventManager(GamePanel gp) {
        this.gp = gp;
        this.uT = new UtilityTool(gp);
    }


    public void checkEvents() {
    }

    /* private void hit (Entity entity, Event event, int x, int y, String directionRequired){
        //event hitbox world pos
        event.getArea().x = x + event.getArea().x;
        event.getArea().y = y + event.getArea().y;

        //entity hitbox world pos
        entity.getHitBoxArea().x = entity.getWorldX() + entity.getHitBoxArea().x;
        entity.getHitBoxArea().y = entity.getWorldY() + entity.getHitBoxArea().y;

        switch (entity.getDirection()) { //prediction
            case "up": event.getArea().y -= entity.getSpeed(); break;
            case "down": event.getArea().y += entity.getSpeed(); break;
            case "left": event.getArea().x -= entity.getSpeed(); break;
            case "right": event.getArea().x += entity.getSpeed(); break;
        }

        if(event.getArea().intersects(entity.getHitBoxArea())){trigger=true;} else{trigger=false;}

        //reset world hitboxArea values
        event.getArea().x = event.getAreaDefX();
        event.getArea().y = event.getAreaDefY();
        entity.getHitBoxArea().x = entity.getHitBoxAreaDefX();
        entity.getHitBoxArea().y = entity.getHitBoxAreaDefY();
    } */

    public void drawEvents(Graphics2D g2, Rectangle area) {
                
                int screenX = worldX - gp.player.getWorldX() + Player.screenX;
                int screenY = worldY - gp.player.getWorldY() + Player.screenY;
                if(uT.isVisible(worldX+(area.width/2), worldY+(area.height/2))) {

                //DRAW EVENT AREA
                    g2.setColor(new Color(138, 43, 226, 127));
                    g2.fillRect(screenX + area.x, screenY + area.y, area.width, area.height);
            }
            
    }

    public int getWorldY() {
        return this.worldY;
    }

    public void setWorldY(int worldY) {
        this.worldY = worldY;
    }

    public int getWorldX() {
        return worldX;
    }

    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }

}