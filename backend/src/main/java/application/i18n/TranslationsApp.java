package application.i18n;

import business.i18n.PreferencesLogic;
import business.i18n.TranslationsLogic;

public class TranslationsApp {

    private static TranslationsApp myself;
    private final ITranslationsLogic translationsLogic;
    private final IPreferencesLogic preferencesLogic;

    private TranslationsApp() {
        this.translationsLogic = TranslationsLogic.getInstance();
        this.preferencesLogic = PreferencesLogic.getInstance();

        String currentLanguage = this.preferencesLogic.getCurrentLanguage();
        this.translationsLogic.changeLanguage("it-CH");
    }

    public static TranslationsApp getInstance() {
        if (myself == null) {
            myself = new TranslationsApp();
        }
        return myself;
    }

    public String translate(String key) {
        return this.translationsLogic.translate(key);
    }

}
