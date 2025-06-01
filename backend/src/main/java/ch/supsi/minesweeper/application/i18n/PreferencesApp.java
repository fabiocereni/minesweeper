package ch.supsi.minesweeper.application.i18n;

import ch.supsi.minesweeper.business.i18n.PreferencesLogic;

public class PreferencesApp {

    private static PreferencesApp myself;
    private final IPreferencesLogic preferencesLogic;

    private PreferencesApp() {
        this.preferencesLogic = PreferencesLogic.getInstance();
    }

    public static PreferencesApp getInstance() {
        if (myself == null) {
            myself = new PreferencesApp();
            return myself;
        }
        return myself;
    }

    public Object getPreference(String key) {
        return this.preferencesLogic.getPreference(key);
    }

}
