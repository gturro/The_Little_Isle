package objects;

import entities.Entity;
import main.GamePanel;

public class Key_obj extends Entity {

    public Key_obj(GamePanel gp){
        super(gp);
    
        setObjectName("key");
        idleLeft1 = setUp("/objects/key");
    
    }    
}