package app;

import com.*;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class LocaleExplore {
    public static Locale locale;
    public static ResourceBundle resourceBundle;
    public static String baseName = "res.Messages";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        SetLocale.set("en-US");
        while(loop){
            LocaleExplore.resourceBundle = ResourceBundle.getBundle(LocaleExplore.baseName, LocaleExplore.locale);
            System.out.println(LocaleExplore.resourceBundle.getString("prompt"));
            String command = scanner.nextLine();
            switch (command) {
                case "set locale ro":
                    SetLocale.set("ro-Ro");
                    break;
                case "set locale en":
                    SetLocale.set("en-US");
                    break;
                case "display locales":
                    DisplayLocales.displayLocales();
                    break;
                case "info":
                    Info.info();
                    break;
                case "quit":
                    loop = false;
                    break;
                default:
                    LocaleExplore.resourceBundle = ResourceBundle.getBundle(LocaleExplore.baseName, LocaleExplore.locale);
                    System.out.println(LocaleExplore.resourceBundle.getString("invalid"));
                    break;
            }
        }
    }
}
