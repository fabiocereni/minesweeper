package ch.supsi.minesweeper.business.i18n;

import java.util.List;
import java.util.Locale;
import java.util.Properties;

public interface ITranslationsDataAccess {

    List<String> getSupportedLanguageTags();

    Properties getTranslations(Locale locale);

}
