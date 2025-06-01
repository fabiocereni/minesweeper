package ch.supsi.minesweeper.data;

import ch.supsi.minesweeper.business.i18n.IGameSettingsData;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GameSettingsData implements IGameSettingsData {

    private static final String SETTINGS_FILE = "minesweeper_settings.json";
    private static final int DEFAULT_BOMBS = 10;
    private static final String DEFAULT_LANGUAGE = "it-CH";
    private static final int MIN_BOMBS = 1;
    private static final int MAX_BOMBS = 80;
    private int numBombs;
    private String language;
    private static GameSettingsData instance;

    public GameSettingsData() {
        this.numBombs = DEFAULT_BOMBS;
        this.language = DEFAULT_LANGUAGE;
    }

    public static GameSettingsData getInstance() {
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

    @Override
    public int getNumBombs() {
        return numBombs;
    }

    @Override
    public void setNumBombs(int numBombs) {
        if (numBombs < MIN_BOMBS) {
            this.numBombs = DEFAULT_BOMBS;
        } else if (numBombs > MAX_BOMBS) {
            this.numBombs = DEFAULT_BOMBS;
        } else {
            this.numBombs = numBombs;
        }
    }

    @Override
    public String getLanguageTag() {
        return language;
    }

    @Override
    public void setLanguageTag(String languageTag) {
        this.language = languageTag;
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

    private static GameSettingsData load() {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(SETTINGS_FILE);
        if (file.exists()) {
            try {
                return mapper.readValue(file, GameSettingsData.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new GameSettingsData();
    }
}
