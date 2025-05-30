package business.i18n;

import application.i18n.ITranslationsLogic;
import data.i18n.TranslationsPropertiesDataAccess;

import java.util.List;
import java.util.Locale;
import java.util.Properties;

public class TranslationsLogic implements ITranslationsLogic {

    private static TranslationsLogic myself;

    private final ITranslationsDataAccess translationsDao;

    private final List<String> supportedLanguageTags;

    private Properties translations;

    public TranslationsLogic() {
        this.translationsDao = TranslationsPropertiesDataAccess.getInstance();
        this.supportedLanguageTags = translationsDao.getSupportedLanguageTags();
    }

    public static TranslationsLogic getInstance() {
        if (myself == null) {
            myself = new TranslationsLogic();
        }
        return myself;
    }

    @Override
    public boolean isSupportedLanguageTag(String languageTag) {
        if (!this.supportedLanguageTags.contains(languageTag)) {
            return false;
        }
        return true;
    }

    @Override
    public boolean changeLanguage(String languageTag) {
        this.translations = translationsDao.getTranslations(Locale.forLanguageTag(languageTag));
        return this.translations != null;
    }

    @Override
    public String translate(String key) {
        return this.translations.getProperty(key);
    }

    @Override
    public List<String> getSupportedLanguageTags() {
        return getSupportedLanguageTags();
    }

    @Override
    public String getLanguage() {
        return translationsDao.getLanguage();
    }

    @Override
    public void setLanguage(String languageTag) {
        translationsDao.setLanguage(languageTag);
    }


}
