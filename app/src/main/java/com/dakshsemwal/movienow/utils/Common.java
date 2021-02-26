package com.dakshsemwal.movienow.utils;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Common {

    public static final String DATE_FORMAT_1 = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    public static final String DATE_FORMAT_2 = "dd-MM-yyyy";

    //Convert One Date Format to Another
    @NotNull
    public static String getDate(String inputFormat, String outputFormat, String selectedDate) {
        SimpleDateFormat parseDateFormat = new SimpleDateFormat(inputFormat, Locale.getDefault());
        Date date = null;
        try {
            date = parseDateFormat.parse(selectedDate);
        } catch (ParseException e) {

            e.printStackTrace();
        }
        SimpleDateFormat requiredDateFormat = new SimpleDateFormat(outputFormat, Locale.getDefault());

        return date != null ? requiredDateFormat.format(date) : "--";
    }

}
