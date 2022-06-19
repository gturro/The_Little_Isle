package dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {
    private final Connection conn;

    public DataBase() throws SQLException{
        String url = "jdbc:mysql://localhosttleIslandData";
        //String url = "jdbc:mysql://localhost/LittleIslandData";
        String user = "littleIslandAdminData";
        String password = "";
        conn = DriverManager.getConnection(url, user, password);
    }

    //Inserts new game data to sql dataBase
    public void saveNewGame (String playerName, String classP, int coinsCollected, int keysCollected, double gameTimer) throws SQLException{
        String query = "INSERT INTO userGameData (userName, class, coinsCollected, keysCollected, timeGame) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement st = conn.prepareStatement(query);
            
        st.setString(1, playerName);
        st.setString(2, classP);
        st.setInt(3, coinsCollected);
        st.setInt(4, keysCollected);
        st.setDouble(5, gameTimer);
        st.executeUpdate();
        
    }

    public void saveLoadedGame (int coinsCollected, int keysCollected, double gameTimer, int gameID) throws SQLException{
        String query = "UPDATE userGameData SET coinsCollected = ?, keysCollected = ?, timeGame = ? WHERE id = ?";
        PreparedStatement st = conn.prepareStatement(query);
        st.setInt(1, coinsCollected);
        st.setInt(2, keysCollected);
        st.setDouble(3, gameTimer);
        st.setInt(4, gameID);
        st.executeUpdate();
        
    }


    /** Load Slot array with game data saved
     ** if empty return array = null
    **/
    public Slot[] getGameData() throws SQLException{
        Slot [] savedGames = new Slot[5];
        if(countSlots() != 0){
            for (int i = 0; i < savedGames.length; i++) {
                Slot s = new Slot();
                savedGames[i] = s;
            }
            if (savedGames != null){
                savedGames = getGameID(savedGames);
                savedGames = getPlayerNames(savedGames);
                savedGames = getPlayerClass(savedGames);
                savedGames = getPlayerCoins(savedGames);
                savedGames = getPlayerKeys(savedGames);
                savedGames = getPlayerTime(savedGames);
            }
        }
        
        return savedGames;
    }


    //Getters gamedata from sql dataBase
    private Slot[] getGameID(Slot[] savedGames) throws SQLException {
        Statement st = conn.createStatement();
        String getGameId = "SELECT id FROM UserGameData ORDER BY gameDate DESC";
        ResultSet rs =  st.executeQuery(getGameId);
        for (Slot savedGame : savedGames) {
            if (rs.next()) {
                int gameID = rs.getInt(1);
                savedGame.setGameID(gameID);
            }
        }
        return savedGames;
    }

    private Slot[] getPlayerNames(Slot[] savedGames) throws SQLException {
        Statement st = conn.createStatement();
        String getNames = "SELECT userName FROM UserGameData ORDER BY gameDate DESC";
        ResultSet rs =  st.executeQuery(getNames);
        for (Slot savedGame : savedGames) {
            if (rs.next()) {
                String name = rs.getString(1);
                savedGame.setUserName(name);
            }
        }
        return savedGames;
    }

    private Slot[] getPlayerClass(Slot[] savedGames) throws SQLException {
        Statement st = conn.createStatement();
        String getClass = "SELECT class FROM UserGameData ORDER BY gameDate DESC";
        ResultSet rs =  st.executeQuery(getClass);
        for (Slot savedGame : savedGames) {
            if (rs.next()) {
                String classPlayer = rs.getString(1);
                savedGame.setClassPlayer(classPlayer);
            }
        }
        return savedGames;
    }

    private Slot[] getPlayerCoins (Slot[] savedGames) throws SQLException {
        Statement st = conn.createStatement();
        String getCoins = "SELECT coinsCollected FROM UserGameData ORDER BY gameDate DESC";
        ResultSet rs =  st.executeQuery(getCoins);
        for (Slot savedGame : savedGames) {
            if (rs.next()) {
                int coins = rs.getInt(1);
                savedGame.setCoins(coins);
            }
        }
        return savedGames;
    }

    private Slot[] getPlayerKeys (Slot[] savedGames) throws SQLException {
        Statement st = conn.createStatement();
        String getKeys = "SELECT keysCollected FROM UserGameData ORDER BY gameDate DESC";
        ResultSet rs =  st.executeQuery(getKeys);
        for (Slot savedGame : savedGames) {
            if (rs.next()) {
                int keys = rs.getInt(1);
                savedGame.setKeys(keys);
            }
        }
        return savedGames;
    }

    private Slot[] getPlayerTime (Slot[] savedGames) throws SQLException {
        Statement st = conn.createStatement();
        String getTime = "SELECT timeGame FROM UserGameData ORDER BY gameDate DESC";
        ResultSet rs =  st.executeQuery(getTime);
        for (Slot savedGame : savedGames) {
            if (rs.next()) {
                float time = rs.getFloat(1);
                savedGame.setGameTime(time);
            }
        }
        return savedGames;

    }


    //Count gamesSaved
    private int countSlots() throws SQLException{
        Statement st = conn.createStatement();
        String query = "SELECT COUNT(*) FROM UserGameData ORDER BY gameDate DESC";
        int count = 0;
        ResultSet rs = st.executeQuery(query);
        if (rs.next()){
            count = rs.getInt(1);
        }
        return count;
    }
}