package entities;

import main.GamePanel;

public class Warrior extends Player{

    private final String CLASSE = "warrior";

    public Warrior(GamePanel gp) {
        super(gp);
        setEntityName(CLASSE);
        getEntityImages(CLASSE);
    }
    
}
