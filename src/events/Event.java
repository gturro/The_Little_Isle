package events;

import main.EventManager;
import main.GamePanel;
import java.awt.Rectangle;


// --------------------------------  ON PROGRESS ---------------------------------

public class Event extends EventManager{
    
    GamePanel gp;

    private Rectangle getArea = new Rectangle (0, 0 ,0, 0);
    private int areaDefX, areaDefY;

    public Event(GamePanel gp, int x, int y, int w, int h) {
        super(gp);
        this.getArea.x = x;
        this.getArea.y = y;
        this.areaDefX = x;
        this.areaDefY = x;
        this.getArea.width = w;
        this.getArea.height = h;
        
    }



    public Rectangle getArea() {
        return getArea;
    }

    public void setArea(Rectangle area) {
        this.getArea = area;
    }

    public int getAreaDefX() {
        return areaDefX;
    }

    public void setAreaDefX(int areaDefX) {
        this.areaDefX = areaDefX;
    }

    public int getAreaDefY() {
        return areaDefY;
    }

    public void setAreaDefY(int areaDefY) {
        this.areaDefY = areaDefY;
    }


    
}
