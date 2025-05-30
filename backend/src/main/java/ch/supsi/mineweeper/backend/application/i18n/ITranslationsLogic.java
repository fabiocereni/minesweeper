package ch.supsi.mineweeper.backend.application.i18n;

import java.util.List;

public interface ITranslationsLogic {

    boolean isSupportedLanguageTag(String languageTag);

    boolean changeLanguage(String languageTag);

    String translate(String key);

    List<String> getSupportedLanguageTags();

    String getLanguage();

    void setLanguage(String languageTag);

}
