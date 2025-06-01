package ch.supsi.minesweeper.application.i18n;

import java.util.List;

public interface ITranslationsLogic {

    void changeLanguage(String languageTag);

    String translate(String key);

    List<String> getSupportedLanguageTags();


    String getLanguage();

    void setLanguage(String languageTag);

}
