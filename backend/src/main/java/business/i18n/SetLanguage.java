package business.i18n;

public class SetLanguage implements ISetLanguage {

    private static SetLanguage myself;
    private String language = "it-CH";

    private SetLanguage() { }

    public static SetLanguage getInstance() {
        if (myself == null) {
            myself = new SetLanguage();
        }
        return myself;
    }

    @Override
    public void setLanguage(String languageTag) {
        this.language = languageTag;
    }

    @Override
    public String getLanguage() {
        return this.language;
    }

}
