package com.example.myapplication.ultity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Ultitys {
    private static final SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static Date convertStringToDate(String dateString) {
        try {
            return inputFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String formatDateToString(Date date) {
        return outputFormat.format(date);
    }

    public static void main(String[] args) {
        String dateString = "2023-08-14 08:00:00";

        Date date = convertStringToDate(dateString);
        System.out.println("Converted Date: " + date);

        String formattedDate = formatDateToString(date);
        System.out.println("Formatted Date: " + formattedDate);
    }
}
