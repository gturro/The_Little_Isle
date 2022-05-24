package main;

import java.awt.Graphics2D;

import menus.LoadMenu;
import menus.MainMenu;
import menus.Menu;
import menus.SelectClassMenu;

public class MenuHandler{

    GamePanel gp;
    

    Menu m;
    MainMenu mainM;
    LoadMenu loadM;
    SelectClassMenu selectM;

    public MenuHandler(GamePanel gp){
        this.gp = gp;
        
        this.m = new Menu(gp);
        this.mainM = new MainMenu(gp);
        this.loadM = new LoadMenu(gp);
        this.selectM = new SelectClassMenu(gp);
        
    }
    
    //
    public void checkKey() {
        if (gp.menuState == gp.mainMenu){
            mainM.checkKey();
        } else if (gp.menuState == gp.selectClassMenu){
            selectM.checkKey();
        } else if (gp.menuState == gp.loadMenu){
            loadM.checkKey();
        }
    }

    public void drawMenu(Graphics2D g2) {
        if (gp.menuState == gp.mainMenu) {
            mainM.draw(g2);
        } else if (gp.menuState == gp.loadMenu) {
            loadM.draw(g2);
        } else if (gp.menuState == gp.selectClassMenu) {
            selectM.draw(g2);
        }
 
    }
}