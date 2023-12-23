package com.ecostore.utils;

import com.ecostore.model.UserModel;

import java.sql.Timestamp;
import java.util.Calendar;

public class Check24Hours {
    public static boolean check(UserModel user) {
        boolean result = false;
        Timestamp keytime = user.getKeytime();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(keytime);
        calendar.add(Calendar.HOUR, 24);
        Timestamp keytimeAfter24h = new Timestamp(calendar.getTime().getTime());
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        if (currentTime.after(keytime) && currentTime.before(keytimeAfter24h)){
            result = true;
        }
        return result;
    }
}
