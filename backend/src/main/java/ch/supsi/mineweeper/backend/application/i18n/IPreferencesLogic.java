package ch.supsi.mineweeper.backend.application.i18n;

public interface IPreferencesLogic {

    String getCurrentLanguage();
    Object getPreference(String key);

}
