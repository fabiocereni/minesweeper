package l10n.application;


public interface IPreferencesBusiness {
    String getCurrentLanguage();
    Object getPreference(String key);
}
