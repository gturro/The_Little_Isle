package objects;

import entities.Entity;
import main.GamePanel;

public class Heart_obj extends Entity{

    public Heart_obj(GamePanel gp) {
        super(gp);

        setObjectName("heart");

        image = setUp("/objects/heart_full");
        image2 = setUp("/objects/heart_med");
        image3 = setUp("/objects/heart_empty");
    }
    

    

}
