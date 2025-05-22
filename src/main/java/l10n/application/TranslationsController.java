package l10n.application;

import l10n.model.PreferencesModel;
import l10n.model.TranslationsModel;

public class TranslationsController {

    private static TranslationsController myself;
    private final ITranslationsBusiness translationsModel;
    private final IPreferencesBusiness preferencesModel;

    protected TranslationsController() {
        this.translationsModel = TranslationsModel.getInstance();
        this.preferencesModel = PreferencesModel.getInstance();

        String currentLanguage = preferencesModel.getCurrentLanguage();
        this.translationsModel.changeLanguage(currentLanguage);
    }

    public static TranslationsController getInstance() {
        if (myself == null) {
            myself = new TranslationsController();
        }
        return myself;
    }

    public String translate(String key) {
        return this.translationsModel.translate(key);
    }

}
