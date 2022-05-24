package objects;

import entities.Entity;
import main.GamePanel;

public class Boots_obj extends Entity{

    public Boots_obj(GamePanel gp){
        super(gp);
        
        setObjectName("boots");
        idleLeft1 = setUp("/objects/boots");
    
    }
}
