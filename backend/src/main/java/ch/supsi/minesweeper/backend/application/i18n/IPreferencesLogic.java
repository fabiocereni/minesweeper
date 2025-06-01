package ch.supsi.minesweeper.backend.application.i18n;

public interface IPreferencesLogic {

    String getCurrentLanguage();
    Object getPreference(String key);

}
