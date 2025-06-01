package ch.supsi.minesweeper.controller.l10n;

import ch.supsi.minesweeper.application.i18n.TranslationsApp;

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
        List<String> tags = this.translationsApp.getSupportedLanguageTags();
        System.out.println(tags == null ? "null" : tags.toString());
        return this.translationsApp.getSupportedLanguageTags();
    }

    public void setLanguage(String languageTag) {
        this.translationsApp.setLanguage(languageTag);
    }

}
