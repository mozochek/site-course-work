package com.mozochek.utils;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateUtil {

    private DateUtil() {

    }

    public static String printFormattedDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return simpleDateFormat.format(date).replace('-', '.');
    }

    public static Date parseDate(String date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        date = date.replace('.', '-');
        Date parsedDate = null;
        try {
            parsedDate = new Date(simpleDateFormat.parse(date).getTime());
        } catch (ParseException e) {
            System.out.println("Некорректный ввод даты");
        }
        return parsedDate;
    }
}
