package entities;

import main.GamePanel;

public class Human extends Player{

    private final String CLASSE = "human";

    public Human(GamePanel gp) {
        super(gp);
        setEntityName(CLASSE);
        getEntityImages(CLASSE);
        
    }
    
}
