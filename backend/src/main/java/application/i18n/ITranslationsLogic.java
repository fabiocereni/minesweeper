package application.i18n;

public interface ITranslationsLogic {

    boolean isSupportedLanguageTag(String languageTag);

    boolean changeLanguage(String languageTag);

    String translate(String key);

}
