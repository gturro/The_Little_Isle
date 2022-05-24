package events;

import entities.Demon;
import main.GamePanel;


// --------------------------------  ON PROGRESS ---------------------------------

public class DemonTriggerZone extends Event{

    GamePanel gp;
    Demon dm;


    public DemonTriggerZone(GamePanel gp, Demon dm, int x, int y, int w, int h) {
        super(gp, x, y, w, h);
        this.dm = dm;
        setWorldX(35 * gp.tileSize);
        setWorldY(7 * gp.tileSize);

    }

    public void trigger() {
        int MoveToX = gp.player.getWorldX();
        int MoveToY = gp.player.getWorldY();

        int diffX = MoveToX - dm.getWorldX();
        int diffY = MoveToY - dm.getWorldY();

        float angle = (float)Math.atan2(diffY, diffX);
        int dmWorldX = dm.getWorldX();
        int dmWorldY = dm.getWorldY();
        
        dm.setWorldX(dmWorldX += (dm.getSpeed() * Math.cos(angle)));
        dm.setWorldY(dmWorldY += (dm.getSpeed() * Math.sin(angle)));
    }
    
}