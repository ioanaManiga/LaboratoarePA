package com;

import app.LocaleExplore;

import java.sql.SQLOutput;
import java.text.DateFormatSymbols;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Currency;
import java.util.Locale;

public class Info {
    public static void info() {
            Locale loc = LocaleExplore.locale;
            String text = LocaleExplore.resourceBundle.getString("info");
            String message = java.text.MessageFormat.format(text, loc.getDisplayName());
            Currency currency = Currency.getInstance(loc);
            DateFormatSymbols dateFormatSymbols = DateFormatSymbols.getInstance(loc);
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy", loc);
            System.out.println(message);
            System.out.println("Country: " + loc.getCountry());
            System.out.println("Language: " +loc.getDisplayLanguage());
            System.out.println("Currency: " + currency.getCurrencyCode());
            System.out.print("Week days:");
            for (String weekDay : dateFormatSymbols.getWeekdays()) {
                System.out.print(weekDay + " ");
            }
            System.out.println();
            System.out.print("Months:");
            for (String month : dateFormatSymbols.getMonths()) {
                System.out.print(month + " ");
            }
            System.out.println();
            LocalDateTime now = LocalDateTime.now();
            System.out.println("Today: " + dateTimeFormatter.format(now));
        }
    }

