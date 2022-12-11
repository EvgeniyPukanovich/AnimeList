package com.example.animelist;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    private static final SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");

    public static String getShortDate(Date date) {
        if(date != null)
            return formatter.format(date);
        return null;
    }
}
