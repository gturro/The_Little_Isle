package objects;

import entities.Entity;
import main.GamePanel;

public class Coin_obj extends Entity{

    public Coin_obj(GamePanel gp){
        super(gp);

        setObjectName("coin");
        idleLeft1 = setUp("/objects/coin");
    
    }
}
