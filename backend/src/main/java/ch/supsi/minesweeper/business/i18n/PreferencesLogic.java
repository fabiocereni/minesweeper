package ch.supsi.minesweeper.business.i18n;

import ch.supsi.minesweeper.application.i18n.IPreferencesLogic;
import ch.supsi.minesweeper.data.i18n.PreferencesPropertiesDataAccess;

import java.util.Properties;

public class PreferencesLogic implements IPreferencesLogic {

    private static PreferencesLogic myself;
    private final IPreferencesDataAccess preferencesDataAccess;
    private final Properties userPreferences;

    private PreferencesLogic() {
        this.preferencesDataAccess = PreferencesPropertiesDataAccess.getInstance();
        this.userPreferences = preferencesDataAccess.getPreferences();
    }

    public static PreferencesLogic getInstance() {
        if (myself == null) {
            myself = new PreferencesLogic();
        }
        return myself;
    }

    @Override
    public String getCurrentLanguage() {
        return userPreferences.getProperty("language-tag");
    }

    @Override
    public Object getPreference(String key) {
        if (key == null || key.isEmpty()) {
            return null;
        }
        if (userPreferences == null) {
            return null;
        }
        return userPreferences.get(key);
    }

}
