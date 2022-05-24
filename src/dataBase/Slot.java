package dataBase;

public class Slot {

    private int slotID;

    private String userName;
    private String classPlayer;
    private int coins;
    private int keys;
    private float gameTime;

    public Slot(){
    }

    public int getSlotID() {
        return slotID;
    }

    public void setSlotID(int slotID) {
        this.slotID = slotID;
    }

    //GETTERS
    public String getUserName() {
        return userName;
    }
    public String getClassPlayer() {
        return classPlayer;
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
        this.userName = userName;
    }
    //class
    public void setClassPlayer(String classPlayer) {
        this.classPlayer = classPlayer;
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

    @Override
    public String toString() {
        return "[ UserName=" + userName + "Class=" + classPlayer +", coins=" + coins + ", keys=" + keys + ", timeGame=" + gameTime + " ]";
    }



}
