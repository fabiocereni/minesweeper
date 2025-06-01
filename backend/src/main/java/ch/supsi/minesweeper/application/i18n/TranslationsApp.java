package ch.supsi.minesweeper.application.i18n;

import ch.supsi.minesweeper.business.i18n.PreferencesLogic;
import ch.supsi.minesweeper.business.i18n.TranslationsLogic;

import java.util.List;

public class TranslationsApp {

    private static TranslationsApp myself;
    private final ITranslationsLogic translationsLogic;
    private final IPreferencesLogic preferencesLogic;

    private TranslationsApp() {
        this.translationsLogic = TranslationsLogic.getInstance();
        this.preferencesLogic = PreferencesLogic.getInstance();

        this.translationsLogic.changeLanguage(translationsLogic.getLanguage());
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

    public List<String> getSupportedLanguageTags() {
        return this.translationsLogic.getSupportedLanguageTags();
    }

    public void setLanguage(String languageTag) {
        translationsLogic.setLanguage(languageTag);
    }

    public String getCurrentLanguage() {
        return translationsLogic.getLanguage();
    }

}
