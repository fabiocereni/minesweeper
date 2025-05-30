package controller.l10n;

import application.i18n.TranslationsApp;

import java.util.List;

public class TranslationsController {

    private static TranslationsController myself;
    private final TranslationsApp translationsApp;

    public TranslationsController() {
        this.translationsApp = TranslationsApp.getInstance();
    }

    public static TranslationsController getInstance() {
        if (myself == null) {
            myself = new TranslationsController();
        }
        return myself;
    }

    public String translate(String key) {
        return this.translationsApp.translate(key);
    }

    public List<String> getSupportedLanguageTags() {
        return this.translationsApp.getSupportedLanguageTags(); // DICE CHE C'È UN STACKOVERFLOW
    }

    public void setLanguage(String languageTag) {
        this.translationsApp.setLanguage(languageTag);
    }

}
