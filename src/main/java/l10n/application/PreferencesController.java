package l10n.application;

import l10n.model.PreferencesModel;

public class PreferencesController {

    private static PreferencesController myself;
    private final IPreferencesBusiness preferencesModel;

    protected PreferencesController() {
        this.preferencesModel = PreferencesModel.getInstance();
    }

    public static PreferencesController getInstance() {
        if (myself == null) {
            myself = new PreferencesController();
        }
        return myself;
    }

    public Object getPreference(String key) {
        return this.preferencesModel.getPreference(key);
    }
}
