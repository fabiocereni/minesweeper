package l10n.application;

public interface ITranslationsBusiness {

    boolean isSupportedLanguage(String languageTag);
    boolean changeLanguage(String languageTag);
    String translate(String key);

}
