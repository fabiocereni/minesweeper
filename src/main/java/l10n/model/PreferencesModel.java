package l10n.model;

import l10n.application.IPreferencesBusiness;
import l10n.dataaccess.PreferencesPropertiesDataAccess;

import java.util.Properties;

public class PreferencesModel implements IPreferencesBusiness {

    private static PreferencesModel myself;
    private final IPreferencesDataAccess preferencesDao;
    private final Properties userPreferences;

    protected PreferencesModel() {
        this.preferencesDao = PreferencesPropertiesDataAccess.getInstance();
        this.userPreferences = preferencesDao.getProperties();
    }

    public static PreferencesModel getInstance() {
        if (myself == null) {
            myself = new PreferencesModel();
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
