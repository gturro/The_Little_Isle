package objects;

import entities.Entity;
import main.GamePanel;

public class Chest_obj extends Entity{
    
    public Chest_obj(GamePanel gp){
        super(gp);
        
        setObjectName("chest");
        idleLeft1 = setUp("/objects/chest_closed");
    
    }
}
