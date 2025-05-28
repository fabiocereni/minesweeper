package data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.beans.Transient;
import java.io.File;
import java.io.IOException;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GameSettings {

    private static final String SETTINGS_FILE = "minesweeper_settings.json";

    private static final int DEFAULT_BOMBS = 10;
    private static final int MIN_BOMBS = 1;
    private static final int MAX_BOMBS = 80;
    private int numBombs;

    private static GameSettings instance;

    // Costruttore richiesto da Jackson e fallback
    public GameSettings() {
        this.numBombs = DEFAULT_BOMBS;
    }

    public static GameSettings getInstance() {
        if (instance == null) {
            instance = load();
        }
        return instance;
    }

    public static void deleteSettingsFile() {
        File file = new File(SETTINGS_FILE);
        if (file.exists()) {
            file.delete();
            System.out.println("File impostazioni eliminato.");
        }
    }


    public int getNumBombs() {
        return numBombs;
    }

    public void setNumBombs(int numBombs) {
        if (numBombs < MIN_BOMBS) {
            this.numBombs = DEFAULT_BOMBS;
        } else if (numBombs > MAX_BOMBS) {
            this.numBombs = DEFAULT_BOMBS;
        } else {
            this.numBombs = numBombs;
        }
    }

    public boolean save() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(SETTINGS_FILE), this);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static GameSettings load() {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(SETTINGS_FILE);

        if (file.exists()) {
            try {
                return mapper.readValue(file, GameSettings.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return new GameSettings();
    }
}
