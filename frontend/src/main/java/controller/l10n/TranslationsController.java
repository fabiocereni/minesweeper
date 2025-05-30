package controller.l10n;

import application.i18n.TranslationsApp;

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

}
