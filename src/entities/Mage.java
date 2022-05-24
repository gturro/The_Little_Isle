package entities;

import main.GamePanel;

public class Mage extends Player{

    private final String CLASSE = "mage";

    public Mage(GamePanel gp) {
        super(gp);
        setEntityName(CLASSE);
        getEntityImages(CLASSE);
    }

    
}
