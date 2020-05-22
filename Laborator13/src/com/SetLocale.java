package com;

import app.LocaleExplore;

import java.util.Locale;
import java.util.ResourceBundle;

public class SetLocale {
    public static void set(String language) {
        LocaleExplore.locale = Locale.forLanguageTag(language);
        Locale loc = LocaleExplore.locale;
        LocaleExplore.resourceBundle = ResourceBundle.getBundle(LocaleExplore.baseName, loc);
        String text = LocaleExplore.resourceBundle.getString("locale.set");
        String message = java.text.MessageFormat.format(text, loc.getDisplayName());
        System.out.println(message);
    }
}
