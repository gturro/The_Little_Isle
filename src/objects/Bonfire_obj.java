package objects;


import entities.Entity;
import events.Event;
import main.GamePanel;

public class Bonfire_obj extends Entity{

    public Bonfire_obj(GamePanel gp) {
        super(gp);
        
        setObjectName("bonfire");

        idleLeft1 = setUp("/objects/bonfire");

        event = new Event(gp, -gp.tileSize, 0, gp.tileSize*3, gp.tileSize*2);

        setHasCollision(true);
    }   
}