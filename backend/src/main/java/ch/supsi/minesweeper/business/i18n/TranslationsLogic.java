package ch.supsi.minesweeper.business.i18n;

import ch.supsi.minesweeper.application.i18n.ITranslationsLogic;
import ch.supsi.minesweeper.data.GameSettingsData;
import ch.supsi.minesweeper.data.i18n.TranslationsPropertiesDataAccess;

import java.util.List;
import java.util.Locale;
import java.util.Properties;

public class TranslationsLogic implements ITranslationsLogic {

    private static TranslationsLogic myself;

    private final ITranslationsDataAccess translationsDao;

    private Properties translations;


    private final IGameSettingsData settings;

    public TranslationsLogic() {
        this.translationsDao = TranslationsPropertiesDataAccess.getInstance();

        this.settings = GameSettingsData.getInstance();
    }

    public static TranslationsLogic getInstance() {
        if (myself == null) {
            myself = new TranslationsLogic();
        }
        return myself;
    }

    @Override
    public void changeLanguage(String languageTag) {
        this.translations = translationsDao.getTranslations(Locale.forLanguageTag(languageTag));
    }

    @Override
    public String translate(String key) {
        return this.translations.getProperty(key);
    }

    @Override
    public List<String> getSupportedLanguageTags() {
        return translationsDao.getSupportedLanguageTags();
    }

    @Override
    public String getLanguage() {
        return settings.getLanguageTag();
    }

    @Override
    public void setLanguage(String languageTag) {
        settings.setLanguageTag(languageTag);
    }


}
