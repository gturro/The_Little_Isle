package dataBase;

public class Slot {

    public static final int NAME_SIZE = 20;
    public static final int CLASS_SIZE = 10;

    private int gameID;
    
    private String playerName;
    private String playerClass;
    private int coins;
    private int keys;
    private float gameTime;

    public Slot(String playerName, String playerClass, int coins, int keys, float gameTime) {
        this.playerName = playerName;
        this.playerClass = playerClass;
        this.coins = coins;
        this.keys = keys;
        this.gameTime = gameTime;
    }


    public Slot(){
    }



    public int getGameID() {
        return gameID;
    }


    public void setGameID(int gameID) {
        this.gameID = gameID;
    }


    //GETTERS
    public String getUserName() {
        return playerName;
    }
    public String getClassPlayer() {
        return playerClass;
    }
    public int getCoins() {
        return coins;
    }
    public int getKeys() {
        return keys;
    }
    public float getGameTime() {
        return gameTime;
    }
    
    //SETTERS
    //player name
    public void setUserName(String userName) {
        this.playerName = userName;
    }
    //class
    public void setClassPlayer(String classPlayer) {
        this.playerClass = classPlayer;
    }
    //coins collected
    public void setCoins(int coins) {
        this.coins = coins;
    }
    //keys collected
    public void setKeys(int keys) {
        this.keys = keys;
    }
    //game time
    public void setGameTime(float gameTime) {
        this.gameTime = gameTime;
    }

    public static int size() {
        return NAME_SIZE+ CLASS_SIZE + Integer.BYTES*2 + Float.BYTES; 
    }

    public String getFormattedString(String string, int string_size) {
        String format = getStringFromat(string_size);
        String n = String.format(format, string);
        return n;
    }

    private String getStringFromat(int string_size) {
        return "%-" + string_size + "." + string_size + "s";
    }

    @Override
    public String toString() {
        return "[ UserName=" + playerName + "Class=" + playerClass +", coins=" + coins + ", keys=" + keys + ", timeGame=" + gameTime + " ]";
    }



}
