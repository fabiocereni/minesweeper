package l10n.dataaccess;

import l10n.model.IPreferencesDataAccess;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;
import java.util.Properties;

public class PreferencesPropertiesDataAccess implements IPreferencesDataAccess {

    private static final String defaultPreferencesPath = "/default-user-preferences.properties";
    private static final String userHomeDirectory = System.getProperty("user.home");
    private static final String preferencesDirectory = ".l10n";
    private static final String preferencesFile = "preferences.properties";

    public static PreferencesPropertiesDataAccess dao;
    private static Properties userPreferences;

    protected PreferencesPropertiesDataAccess() {}

    public static PreferencesPropertiesDataAccess getInstance() {
        if (dao == null) {
            dao = new PreferencesPropertiesDataAccess();
        }
        return dao;
    }

    private Path getUserPreferencesDirectoryPath() {
        return Path.of(userHomeDirectory, preferencesDirectory);
    }

    private boolean userPreferencesDirectoryExists() {
        return Files.exists(this.getUserPreferencesDirectoryPath());
    }

    private Path createUserPreferencesDirectory() {
        try {
            return Files.createDirectories(this.getUserPreferencesDirectoryPath());

        } catch (IOException ignoredForDemoPurposes) {
            ;
        }
        return null;
    }

    private Path getUserPreferencesFilePath() {
        return Path.of(userHomeDirectory, preferencesDirectory, preferencesFile);
    }

    private boolean userPreferencesFileExists() {
        return Files.exists(this.getUserPreferencesFilePath());
    }

    private boolean createUserPreferencesFile(Properties defaultPreferences) {
        if (defaultPreferences == null) {
            return false;
        }

        if (!userPreferencesDirectoryExists()) {
            // user preferences directory does not exist
            // create it
            this.createUserPreferencesDirectory();
        }

        if (!userPreferencesFileExists()) {
            // user preferences file does not exist
            // create it
            try {
                // create user preferences file (with default preferences)
                FileOutputStream outputStream = new FileOutputStream(String.valueOf(this.getUserPreferencesFilePath()));
                defaultPreferences.store(outputStream, null);
                return true;

            } catch (IOException ignoredForDemoPurposes) {
                return false;
            }
        }

        return true;
    }

    private Properties loadDefaultPreferences() {
        Properties defaultPreferences = new Properties();
        try {
            InputStream defaultPreferencesStream = this.getClass().getResourceAsStream(defaultPreferencesPath);
            defaultPreferences.load(defaultPreferencesStream);

        } catch (IOException ignored) {
            ;
        }

        // return the properties object with the loaded preferences
        return defaultPreferences;
    }

    private Properties loadPreferences(Path path) {
        Properties preferences = new Properties();
        try {
            preferences.load(new FileInputStream(String.valueOf(path)));

        } catch (IOException ignoredForDemoPurposes) {
            return null;
        }

        return preferences;
    }

    @Override
    public Properties getProperties() {
        Properties props = new Properties();
        try (InputStream in = getClass().getResourceAsStream("/default-user-preferences.properties")) {
            if (in != null) {
                props.load(in);
            } else {
                System.err.println("File default-user-preferences.properties non trovato nel classpath!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }

    public Locale getLocaleFromPreferences() {
        Properties props = getProperties();

        String language = props.getProperty("language");
        String country = props.getProperty("country");

        if (language != null && country != null) {
            return new Locale(language, country);
        }

        String tag = props.getProperty("locale");
        if (tag != null && !tag.isBlank()) {
            return Locale.forLanguageTag(tag);
        }

        return Locale.getDefault(); // fallback
    }
}
