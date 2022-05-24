package objects;

import entities.Entity;
import main.GamePanel;

public class Door2_obj extends Entity{
    
    public Door2_obj(GamePanel gp){
        super(gp);
    
        setObjectName("door");

        idleLeft1 = setUp("/objects/door2");

        setHasCollision(true);
        
    }
}