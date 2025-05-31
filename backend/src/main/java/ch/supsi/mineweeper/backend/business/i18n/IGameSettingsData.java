package ch.supsi.mineweeper.backend.business.i18n;

public interface IGameSettingsData {

    int getNumBombs();
    void setNumBombs(int numBombs);
    String getLanguageTag();
    void setLanguageTag(String languageTag);

}
