package entities;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {
    KeyHandler keyH;

    public static int screenY;
    public static int screenX;

    private String playerName = "< on work >";

    //KEYS COLLECTED
    private int keys = 0;
    //COINS COLLECTED
    private int coins = 0;

    //CLASSE
    private String classe;

    public Player(GamePanel gp) {
        super(gp);
        this.keyH = gp.keyH; //keyHandler

        //Event enemyDetection = new Event(gp, -gp.tileSize*2, -gp.tileSize*2, gp.tileSize*5, gp.tileSize*5);
        
        //Player always in center of screen
        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2); 

        setHitBoxArea( gp.tileSize/3, gp.tileSize/3, gp.tileSize/3, gp.tileSize/2);
        setDefaultValues();
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    // STARTING POINT 4 player
    private void setDefaultValues() {
        worldX = 23*gp.tileSize; // posicion inicial player
        worldY = 21*gp.tileSize;

        // ---------------- SPEED = 12 FOR TESTING ---------------
            setSpeed(4); //SPEED
        // ---------------- --------------------------------------

        setSpriteSpeed(getSpeed()*6);
        setDirection("up"); // direccion inicial sprite

        setMaxHP(6);
        setCurrentHP(getMaxHP());
    }
 


    public int getKeys(){
        return this.keys;
    }

    private void pickUpObject(int objbIndex) {
        if(objbIndex != 99){
            
            String objectName = gp.objects[objbIndex].getObjectName();

            switch (objectName) {
                case "key":
                    keys++;
                    gp.objects[objbIndex] = null;
                    break;
                case "coin":
                    coins += 100;
                    gp.objects[objbIndex] = null;
                    break;
                case "door":
                    if (keys > 0){
                        keys--;
                        gp.objects[objbIndex] = null;
                    }
                    break;
                case "boots":
                    setSpeed(getSpeed() + 3);
                    gp.objects[objbIndex] = null;
                    break;
                case "bonfire":
                    gp.ui.showMessage("Magic bonfire...", Player.screenX/3, Player.screenY/2, 100);
                    
                    break;
                case "chest":
                    System.out.println("Touched a CHEST");
                    gp.gameState = gp.endGameState;
                    break;
                
            }
        }
    }

    @Override
    public void update() {
        super.update();
                 
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            
            if (keyH.upPressed) {setDirection("up");}
            if (keyH.downPressed) {setDirection("down");}
            if (keyH.leftPressed) {setDirection("left");}
            if (keyH.rightPressed) {setDirection("right");}

            collision = false;

            // ---------------- DISABLE FOR TESTING ------------------
            //COLLISIONS CHECKERS
            //TILE COLISION
            //gp.collisionDetection.tileCheck(this);
            // -------------------------------------------------------

            //OBJECT COLISION
            int objbIndex = gp.collisionDetection.objectCheck(this, true);
            pickUpObject(objbIndex);

            //ENEMIES COLISION
            gp.collisionDetection.entityColision(this, gp.enemies);;


            // if collision false player move(speed)
            if (collision == false) {
                setSpriteSpeed((int) getSpeed()*4);
                switch (getDirection()) {
                    case "up": worldY -= getSpeed(); break;
                    case "down": worldY += getSpeed(); break;
                    case "left": worldX -= getSpeed(); break;
                    case "right": worldX += getSpeed(); break;
                }
                //PRINT UBICACION PLAYER EN WORLD
                //System.out.println("World COL = "+(worldX+getHitBoxArea().x)/gp.tileSize+"\nWorld ROW = "+(worldY+getHitBoxArea().y)/gp.tileSize);
            }
        }
        
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public void setKeys(int keys) {
        this.keys = keys;
    }

}