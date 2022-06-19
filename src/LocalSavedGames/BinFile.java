package LocalSavedGames;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;

import dataBase.Slot;

public class BinFile {

    private static int slotsSaved = 0;
    
    public static final String FILE_PATH = "src/LocalSavedGames/savedGames.bin";
    
    public static void saveGames(Slot slot) {
        RandomAccessFile randomAccess;
        int pos;
        String slotString;

        try {
            randomAccess = new RandomAccessFile(FILE_PATH, "rw");

            pos = slotsSaved*Slot.size();
            randomAccess.seek(pos);
            slotString = slot.getFormattedString(slot.getUserName(), slot.NAME_SIZE); //user Name
            randomAccess.write(slotString.getBytes(Charset.defaultCharset()));
            slotString = slot.getFormattedString(slot.getClassPlayer(), slot.CLASS_SIZE); //user Class
            randomAccess.write(slotString.getBytes(Charset.defaultCharset())); 
            randomAccess.writeInt(slot.getCoins()); //user coins
            randomAccess.writeInt(slot.getKeys()); //user keys
            randomAccess.writeFloat(slot.getGameTime()); //user time played

            randomAccess.close();
            slotsSaved++;
        } catch (IOException e) {
            System.out.println("Couldn't acces to filepath "+FILE_PATH+"\n"+e.toString());
        }  

    }

    public static Slot[] loadGame() {
        Slot slots [] = null;
        File file;
        RandomAccessFile randomAcces;
        Slot slot;
        long fileSize;
        int i, pos, slotSize, numSlots, coins, keys;
        float gameTime;
        byte [] bName, bClass;
        String playerName, playerClass;
        
        try {
            file = new File(FILE_PATH);
            if (file.exists()){
                fileSize = file.length();
                slotSize = Slot.size();
                numSlots = (int) (fileSize / slotSize);
                slots = new Slot [numSlots];

                randomAcces = new RandomAccessFile(file, "r");
                for (i = 0; i < numSlots; i++) {
                    pos = i * slotSize; 
                    randomAcces.seek(pos);

                    bName = new byte [Slot.NAME_SIZE];
                    randomAcces.read(bName);
                    playerName = new String(bName).trim();

                    bClass = new byte [Slot.CLASS_SIZE];
                    randomAcces.read(bClass);
                    playerClass = new String(bClass).trim();

                    coins = randomAcces.readInt();

                    keys = randomAcces.readInt();

                    gameTime = randomAcces.readFloat();

                    slot = new Slot(playerName, playerClass, coins, keys, gameTime);

                    slots[i] = slot;
                }

            } else {
                System.out.println("File savedGames.bin doesn't exist in LocalSavedGames directory.");
            }
        } catch (IOException e) {
            //TODO: handle exception
        }
        return slots;
    }



}
