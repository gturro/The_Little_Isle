package main;

import entities.Demon;
import events.Event;
import objects.Bonfire_obj;
import objects.Boots_obj;
import objects.Chest_obj;
import objects.Coin_obj;
import objects.Door1_obj;
import objects.Door2_obj;
import objects.Key_obj;

public class AssetsSetter {
    GamePanel gp;

    public AssetsSetter(GamePanel gp) {
        this.gp = gp;
    }
    
    public void setObject (){
        gp.objects[0] = new Key_obj(gp);
        gp.objects[0].setWorldX(23*gp.tileSize);
        gp.objects[0].setWorldY(7*gp.tileSize);

        gp.objects[1] = new Key_obj(gp);
        gp.objects[1].setWorldX(40*gp.tileSize);
        gp.objects[1].setWorldY(9*gp.tileSize);

        gp.objects[2] = new Bonfire_obj(gp);
        gp.objects[2].setWorldX(35*gp.tileSize);
        gp.objects[2].setWorldY(7*gp.tileSize);

        gp.objects[3] = new Door1_obj(gp);
        gp.objects[3].setWorldX(8* gp.tileSize);
        gp.objects[3].setWorldY(28*gp.tileSize);

        gp.objects[4] = new Door2_obj(gp);
        gp.objects[4].setWorldX(10*gp.tileSize);
        gp.objects[4].setWorldY(12*gp.tileSize);

        gp.objects[5] = new Boots_obj(gp);
        gp.objects[5].setWorldX(37*gp.tileSize);
        gp.objects[5].setWorldY(42*gp.tileSize);

        gp.objects[6] = new Chest_obj(gp);
        gp.objects[6].setWorldX(10*gp.tileSize);
        gp.objects[6].setWorldY(8*gp.tileSize);

        gp.objects[8] = new Coin_obj(gp);
        gp.objects[8].setWorldX(8*gp.tileSize);      
        gp.objects[8].setWorldY(38*gp.tileSize);
        
        gp.objects[9] = new Door2_obj(gp);
        gp.objects[9].setWorldX(26*gp.tileSize);
        gp.objects[9].setWorldY(37*gp.tileSize);
        
    }

    public void setEnemies(){
        gp.enemies[0] = new Demon(gp);
        gp.enemies[0].setWorldX(26*gp.tileSize);
        gp.enemies[0].setWorldY(32*gp.tileSize);
    }

    public void setEvents(){
        gp.events[0] = new Event(gp, 0, 0, 7*gp.tileSize, 11*gp.tileSize);
        gp.events[0].worldX = 27*gp.tileSize;
        gp.events[0].worldY = 27*gp.tileSize;


    }
}