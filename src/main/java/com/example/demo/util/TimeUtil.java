package com.example.demo.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
    public static String convertDate(String longTIme) {
        try {
            Long timestamp = Long.parseLong(longTIme);
            Date date = new Date(timestamp);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");
            String time = format.format(date);
            return time;
        } catch (Exception e) {
            return "";
        }
    }
}
