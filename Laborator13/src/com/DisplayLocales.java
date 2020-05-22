package com;

        import app.LocaleExplore;
        import java.util.Locale;

public class DisplayLocales {
    public static void displayLocales() {

        System.out.println(LocaleExplore.resourceBundle.getString("locales"));
        Locale available[] = LocaleExplore.locale.getAvailableLocales();
        for(Locale locale : available) {
            System.out.println( locale.getDisplayCountry() + "\t" + locale.getDisplayLanguage(locale));
        }
    }
}
