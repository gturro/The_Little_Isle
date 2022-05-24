package menus;

import main.GamePanel;
import main.UtilityTool;

import java.awt.Color;
import java.awt.Graphics2D;

import UI.UI;

public class Menu extends UI {

    UtilityTool uT;
    

    Color blackC= new Color(0, 0, 0);

    private int selector = 0;

    private int maxSelector = 2;

    private boolean slotsLoaded = false;

    public Menu(GamePanel gp) {
        super(gp); 
        this.uT = new UtilityTool(gp);
    }
    
    protected boolean slotsLoaded() {
        return slotsLoaded;
    }

    protected void setSlotsLoaded(boolean slotsLoaded) {
        this.slotsLoaded = slotsLoaded;
    }

    //GET MAX ELEMENTS SELECTABLE
    protected int getMaxSelector() {
        return maxSelector;
    }

    //SET MAX ELEMENTS SELECTABLE
    protected void setMaxSelector(int maxSelector) {
        this.maxSelector = maxSelector;
    }

    //GET CURRENT SLECTOR INT
    protected int getSelector() {
        return selector;
    }

    //SET SELECTOR
    protected void setSelector(int selector) {
        this.selector = selector;
    }

    //SELECTOR UP
    private void selectorUp() {
        setSelector(getSelector() - 1);
        if(getSelector() < 0) {
            setSelector(getMaxSelector());
        }
    }

    //SELECTOR DOWN
    private void selectorDown() {
        setSelector(getSelector() + 1);
        if(getSelector() > getMaxSelector()) {
            setSelector(0);
        }
    }

    //RESET SELECTOR
    protected void resetSelector(){ this.selector = 0; }

    public void checkKey(){
        if(gp.keyH.upPressed || gp.keyH.leftPressed) {
            selectorUp();
        }
        if(gp.keyH.downPressed || gp.keyH.rightPressed){
            selectorDown();
        }
    }

    public void draw(Graphics2D g2){
        g2.setFont(getMaruMonica());
        g2.setColor(blackC);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

    }
    
}