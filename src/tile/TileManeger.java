package tile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import entities.Player;

import java.awt.Graphics2D;

import main.GamePanel;
import main.UtilityTool;

public class TileManeger {
    GamePanel gp;
    UtilityTool uT;
    public Tile[] tile;
    public int baseLayer [][]; //layer 1
    public int overLayer [][]; //layer 2

    public TileManeger (GamePanel gp){
        this.gp = gp;
        uT = new UtilityTool(gp);
        tile = new Tile[50];
        baseLayer = new int [gp.maxWorldCol][gp.maxWorldRow];
        overLayer = new int [gp.maxWorldCol][gp.maxWorldRow];
        getTileTimage();
        loadLayer("/maps/myWorldLayer1.txt", baseLayer);
        loadLayer("/maps/myWorldLayer2.txt", overLayer);
    }

    //BUFFER IMAGE LOADER
    private void getTileTimage(){
            setUp(10, "grass00", false);
            setUp(11, "grass01", false);
            setUp(12, "water00", true);
            setUp(13, "water01", true);
            setUp(14, "water02", true);
            setUp(15, "water03", true);
            setUp(16, "water04", true);
            setUp(17, "water05", true);
            setUp(18, "water06", true);
            setUp(19, "water07", true);
            setUp(20, "water08", true);
            setUp(21, "water09", true);
            setUp(22, "water10", true);
            setUp(23, "water11", false);
            setUp(24, "water12", true);
            setUp(25, "water13", true);
            setUp(26, "water14", false);
            setUp(27, "water15", true);
            setUp(28, "water16", true);
            setUp(29, "water17", true);
            setUp(30, "boss01", false);
            setUp(31, "boss02", false);
            setUp(32, "boss03", false);
            setUp(33, "boss04", false);
            setUp(34, "boss05", false);
            setUp(35, "boss06", false);
            setUp(36, "boss07", false);
            setUp(37, "boss08", false);
            setUp(38, "boss09", false);
            setUp(39, "bush", false);
            setUp(40, "wall", true);
            setUp(41, "tree", true);
            setUp(42, "tree2", true);
            setUp(43, "grass03", false);
            setUp(44, "cartel", true);
            setUp(45, "parquet", false); 
            setUp(46, "rock", true);
            //setUp(index, imgPath, collision);

    }

    //IMG SCALER
    public void setUp(int index, String imgPath, boolean collision) {

        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/"+imgPath+".png"));
            tile[index].image = uT.scaleImg(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].colision = collision;

        } catch (IOException e) {
            System.out.println("Error input on tile "+index);        
        }
    }

    //LOAD MAP ARRAY
    private void loadLayer(String path, int [][] layer) {
        try {
            InputStream iS = getClass().getResourceAsStream(path);
            try (BufferedReader bR = new BufferedReader(new InputStreamReader(iS))) {
                int col = 0;
                int row = 0;
                while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                    String linia = bR.readLine();
                    
                    while(col < gp.maxWorldCol){ // bucle para dibujar cada fila
                        
                        // eliminamos los espacios entre los numeros de cada linia del mapa.txt
                        String numeros [] = linia.split(" ");
                        
                        //guardamos cada numero en la variable para almacenarla en tileMapNum
                        int num = Integer.parseInt(numeros[col]);
                        
                        layer [col][row] = num;
                        col++; //incrementamos para procesar un nuevo numero
                    }
                    
                    if(col == gp.maxWorldCol){ //reset columna, pasamos a la siguiente fila
                        col = 0;
                        row++;
                    }
                }
                //mapa cargadp --> cerramos buffer (libermaos recursos)
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    //DRAW TILES
    public void draw(Graphics2D g2){
        int worldRow = 0;
        int worldCol = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){

            int layer1Tile = baseLayer[worldCol][worldRow];
            int layer2Tile = overLayer[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.getWorldX() + Player.screenX; //posicion en pantalla teniendo en cuenta que player siempre esta en medio
            int screenY = worldY - gp.player.getWorldY() + Player.screenY; //cuando worldX=0 player.worldX=0 tile se dibujara en medio= player.screenX

            if(uT.isVisible(worldX, worldY)){
                try {
                    if(layer1Tile!=00 ){
                        //LAYER 1
                         g2.drawImage(tile[layer1Tile].image, screenX, screenY, null);  
                    }
                    //LAYER2
                    if (layer2Tile != 00){
                        g2.drawImage(tile[layer2Tile].image, screenX, screenY, null);
                    }
                } catch (NullPointerException e) {
                   System.out.println(e);
                }
            }
            worldCol++;
 

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}