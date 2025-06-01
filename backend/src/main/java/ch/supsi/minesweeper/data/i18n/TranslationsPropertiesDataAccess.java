package ch.supsi.minesweeper.data.i18n;

import ch.supsi.minesweeper.business.i18n.ITranslationsDataAccess;

import java.util.*;

import static java.util.ResourceBundle.Control.FORMAT_DEFAULT;

public class TranslationsPropertiesDataAccess implements ITranslationsDataAccess {

    private static final String translationsResourceBundlePath = "i18n.labels";
    private static final String supportedLanguagesPath = "/supported-languages.properties";
    private static TranslationsPropertiesDataAccess myself;

    private TranslationsPropertiesDataAccess() { }

    public static TranslationsPropertiesDataAccess getInstance() {
        if (myself == null) {
            myself = new TranslationsPropertiesDataAccess();
        }
        return myself;
    }

    private Properties loadSupportedLanguageTags() {
        Properties supportedLanguages = new Properties();
        try {
            supportedLanguages.load(getClass().getResourceAsStream(supportedLanguagesPath));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return supportedLanguages;
    }

    @Override
    public List<String> getSupportedLanguageTags() {
        ArrayList<String> supportedLanguageTags = new ArrayList<>();

        Properties props = this.loadSupportedLanguageTags();
        for (Object key: props.keySet()) {
            supportedLanguageTags.add(props.getProperty((String)key));
        }

        // return
        return supportedLanguageTags;
    }

    @Override
    public Properties getTranslations(Locale locale) {
        final Properties translations = new Properties();
        ResourceBundle bundle;
        try {
            bundle = ResourceBundle.getBundle(
                    translationsResourceBundlePath,
                    locale,
                    ResourceBundle.Control.getNoFallbackControl(FORMAT_DEFAULT)
            );

        } catch (MissingResourceException mrex) {
            System.err.println("unsupported language tag..." + locale.toLanguageTag());

            List<String> supportedLanguageTags = this.getSupportedLanguageTags();
            String fallbackLanguageTag = supportedLanguageTags.get(0);
            System.err.println("falling back to..." + fallbackLanguageTag);

            bundle = ResourceBundle.getBundle(
                    translationsResourceBundlePath,
                    Locale.forLanguageTag(fallbackLanguageTag),
                    ResourceBundle.Control.getNoFallbackControl(FORMAT_DEFAULT)
            );
        }

        for (String key : bundle.keySet()) {
            translations.put(key, bundle.getString(key));
        }

        return translations;
    }

}
