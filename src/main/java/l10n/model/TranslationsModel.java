package l10n.model;

import l10n.application.ITranslationsBusiness;
import l10n.dataaccess.TranslationsPropertiesDataAccess;

import java.util.List;
import java.util.Locale;
import java.util.Properties;

public class TranslationsModel implements ITranslationsBusiness {

    private static TranslationsModel myself;

    private final ITranslationsDataAccess translationsDao;

    private final List<String> supportedLanguageTags;

    private Properties translations;

    protected TranslationsModel() {
        this.translationsDao = TranslationsPropertiesDataAccess.getInstance();
        this.supportedLanguageTags = translationsDao.getSupportedLanguageTags();
    }

    public static TranslationsModel getInstance() {
        if (myself == null) {
            myself = new TranslationsModel();
        }

        return myself;
    }

    @Override
    public boolean isSupportedLanguage(String languageTag) {
        if (!this.supportedLanguageTags.contains(languageTag)) {
            return false;
        }
        return true;
    }

    @Override
    public boolean changeLanguage(String languageTag) {
        if (languageTag == null || languageTag.isBlank()) {
            System.err.println("Errore: languageTag è nullo o vuoto");
            return false;
        }

        this.translations = translationsDao.getTranslations(Locale.forLanguageTag(languageTag));
        return this.translations != null;
    }

    @Override
    public String translate(String key) {
        return this.translations.getProperty(key);
    }

}
