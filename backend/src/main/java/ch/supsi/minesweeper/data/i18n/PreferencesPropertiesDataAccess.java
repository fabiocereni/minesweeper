package ch.supsi.minesweeper.data.i18n;

import ch.supsi.minesweeper.business.i18n.IPreferencesDataAccess;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PreferencesPropertiesDataAccess implements IPreferencesDataAccess {

    private static final String defaultPreferencesPath = "/default-user-preferences.properties";
    private static final String userHomeDirectory = System.getProperty("user.home");
    public static PreferencesPropertiesDataAccess dao;
    private static Properties userPreferences;

    private PreferencesPropertiesDataAccess() { }

    public static PreferencesPropertiesDataAccess getInstance() {
        if (dao == null) {
            dao = new PreferencesPropertiesDataAccess();
        }
        return dao;
    }

    @Override
    public Properties getPreferences() {
        userPreferences = loadDefaultPreferences();
        return userPreferences;
    }

    private Properties loadDefaultPreferences() {
        Properties defaultPreferences = new Properties();
        try {
            InputStream defaultPreferencesStream = this.getClass().getResourceAsStream(defaultPreferencesPath);
            defaultPreferences.load(defaultPreferencesStream);

        } catch (IOException ignored) {
            ;
        }
        return defaultPreferences;
    }

}
