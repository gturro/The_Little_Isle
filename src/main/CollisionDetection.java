package main;

import entities.Entity;

public class CollisionDetection {

    GamePanel gp;

    public CollisionDetection(GamePanel gp) {
        this.gp = gp;
    }

//CHECKERS
    //ENTITY TILE CHECK
    public void tileCheck(Entity entity) {

        int entityLeftWorldX = entity.getWorldX() + entity.getHitBoxArea().x;
        int entityRightWorldX = entity.getWorldX() + (entity.getHitBoxArea().x + entity.getHitBoxArea().width);
        int entityTopWorldY = entity.getWorldY() + entity.getHitBoxArea().y;
        int entityBottomWorldY = entity.getWorldY() + (entity.getHitBoxArea().y + entity.getHitBoxArea().height);

        //col & row a comprovar de la entity segun su hitbox area
        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        int tileCheck1, tileCheck2;

        switch (entity.getDirection()) {
            case "up":
                entityTopRow = (int) ((entityTopWorldY - entity.getSpeed()) / gp.tileSize); // prediccion de tile cuando player se mueva(speed)
                tileCheck1 = gp.tileM.overLayer[entityLeftCol][entityTopRow]; //
                tileCheck2 = gp.tileM.overLayer[entityRightCol][entityTopRow];
                if (gp.tileM.tile[tileCheck1].colision == true || gp.tileM.tile[tileCheck2].colision == true) {
                    entity.collision = true;
                }
                break;

            case "down":
                entityBottomRow = (int) ((entityBottomWorldY + entity.getSpeed()) / gp.tileSize);
                tileCheck1 = gp.tileM.overLayer[entityLeftCol][entityBottomRow];
                tileCheck2 = gp.tileM.overLayer[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileCheck1].colision == true || gp.tileM.tile[tileCheck2].colision == true) {
                    entity.collision = true;
                }
                break;

            case "left":
                entityLeftCol = (int) ((entityLeftWorldX - entity.getSpeed()) / gp.tileSize);
                tileCheck1 = gp.tileM.overLayer[entityLeftCol][entityTopRow];
                tileCheck2 = gp.tileM.overLayer[entityLeftCol][entityBottomRow];
                if(gp.tileM.tile[tileCheck1].colision == true || gp.tileM.tile[tileCheck2].colision == true){
                    entity.collision = true;
                }
                break;

            case "right":
                entityRightCol = (int) ((entityRightWorldX + entity.getSpeed()) / gp.tileSize);
                tileCheck1 = gp.tileM.overLayer[entityRightCol][entityTopRow];
                tileCheck2 = gp.tileM.overLayer[entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tileCheck1].colision == true || gp.tileM.tile[tileCheck2].colision == true){
                    entity.collision = true;
                }
                break;

        }

    }

    //ENTITY OBJECT CHECK GET index OF OBJECT
    public int objectCheck(Entity entity, boolean isPlayer) {
        int objectIndex = 99;

            for (int i = 0; i < gp.objects.length - 1; i++) {

                if (gp.objects[i] != null) {
                //entity hitbox world pos
                entity.getHitBoxArea().x = entity.getWorldX() + entity.getHitBoxArea().x;
                entity.getHitBoxArea().y = entity.getWorldY() + entity.getHitBoxArea().y;

                //obj colisionArea world pos

                gp.objects[i].getHitBoxArea().x = gp.objects[i].getWorldX() + gp.objects[i].getHitBoxArea().x; //objetos tienen hitbox tileSize
                gp.objects[i].getHitBoxArea().y = gp.objects[i].getWorldY() + gp.objects[i].getHitBoxArea().y;

                switch (entity.getDirection()) {
                    case "up": entity.getHitBoxArea().y -= entity.getSpeed(); break;
                    case "down": entity.getHitBoxArea().y += entity.getSpeed(); break;
                    case "left": entity.getHitBoxArea().x -= entity.getSpeed(); break;
                    case "right": entity.getHitBoxArea().x += entity.getSpeed(); break;
                }
                if(entity.getHitBoxArea().intersects(gp.objects[i].getHitBoxArea())){
                    //System.out.println("COLISSION!");
                    if(gp.objects[i].isHasCollision()){
                        entity.collision = true;
                    }
                    if(isPlayer){
                        objectIndex = i;
                    }
                }

                //reset world solidArea world pos
                entity.getHitBoxArea().x = entity.getHitBoxAreaDefX();
                entity.getHitBoxArea().y = entity.getHitBoxAreaDefY();
                gp.objects[i].getHitBoxArea().x = gp.objects[i].getHitBoxAreaDefX();
                gp.objects[i].getHitBoxArea().y = gp.objects[i].getHitBoxAreaDefY();
                }
            }
        return objectIndex;
    }

    //ENTITY CHECK WITH OTHER ENTITIES
    public void entityColision(Entity entity, Entity target[]) {

        for(Entity targetEntity : target){
            if (targetEntity != null) {
                //entity hitbox world pos
                entity.getHitBoxArea().x = entity.getWorldX() + entity.getHitBoxArea().x;
                entity.getHitBoxArea().y = entity.getWorldY() + entity.getHitBoxArea().y;
    
                //obj colisionArea world pos
                targetEntity.getHitBoxArea().x = targetEntity.getWorldX() + targetEntity.getHitBoxArea().x;
                targetEntity.getHitBoxArea().y = targetEntity.getWorldY() + targetEntity.getHitBoxArea().y;
    
                switch (entity.getDirection()) {
                    case "up": entity.getHitBoxArea().y -= entity.getSpeed(); break;
                    case "down": entity.getHitBoxArea().y += entity.getSpeed(); break;
                    case "left": entity.getHitBoxArea().x -= entity.getSpeed(); break;
                    case "right": entity.getHitBoxArea().x += entity.getSpeed(); break;
                }
                if(entity.getHitBoxArea().intersects(targetEntity.getHitBoxArea())){
                    //System.out.println("COLISSION!");
                    entity.collision=true;}
    
                //reset world solidArea world pos
                entity.getHitBoxArea().x = entity.getHitBoxAreaDefX();
                entity.getHitBoxArea().y = entity.getHitBoxAreaDefY();
                targetEntity.getHitBoxArea().x = targetEntity.getHitBoxAreaDefX();
                targetEntity.getHitBoxArea().y = targetEntity.getHitBoxAreaDefY();
                }
        }
    }

    //CHECK COLISION WITH PLAYER
    public void checkColisionWithPlayer(Entity entity) {

                //entity hitbox world pos
                entity.getHitBoxArea().x = entity.getWorldX() + entity.getHitBoxArea().x;
                entity.getHitBoxArea().y = entity.getWorldY() + entity.getHitBoxArea().y;
    
                //player hitbox world pos
                gp.player.getHitBoxArea().x = gp.player.getWorldX() + gp.player.getHitBoxArea().x;
                gp.player.getHitBoxArea().y = gp.player.getWorldY() + gp.player.getHitBoxArea().y;
    
                switch (entity.getDirection()) { //prediction
                    case "up": entity.getHitBoxArea().y -= entity.getSpeed(); break;
                    case "down": entity.getHitBoxArea().y += entity.getSpeed(); break;
                    case "left": entity.getHitBoxArea().x -= entity.getSpeed(); break;
                    case "right": entity.getHitBoxArea().x += entity.getSpeed(); break;
                }
                if(entity.getHitBoxArea().intersects(gp.player.getHitBoxArea())) {entity.collision=true;}
    
                //reset world hitboxArea values
                entity.getHitBoxArea().x = entity.getHitBoxAreaDefX();
                entity.getHitBoxArea().y = entity.getHitBoxAreaDefY();
                gp.player.getHitBoxArea().x = gp.player.getHitBoxAreaDefX();
                gp.player.getHitBoxArea().y = gp.player.getHitBoxAreaDefY();
    }

}