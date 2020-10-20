package com.abcrental.shared;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

    public static long convertStringDateToMillis(String date){
        Date currentDate= null;
        try {
            currentDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(date);
        } catch (ParseException e) {
            System.out.println("Cannot Parse Date please reenter in the format yyyy/MM/dd HH:mm:ss ");
        }
        return currentDate.getTime();
    }
}
