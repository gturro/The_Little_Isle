package objects;

import entities.Entity;
import main.GamePanel;

public class Door1_obj extends Entity{
    
    public Door1_obj(GamePanel gp){
        super(gp);
        
        setObjectName("door");
        idleLeft1 = setUp("/objects/door1");

        setHasCollision(true);
    
    }
}
