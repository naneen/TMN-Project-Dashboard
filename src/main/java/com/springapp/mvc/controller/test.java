package com.springapp.mvc.controller;

import org.json.JSONArray;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by James on 7/20/15 AD.
 */
public class test {

    public static String getDateAgo(int day_ago){
        String[] monthNames = {"Jan", "Feb", "Mar", "Apr", "May", "Jun","Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        Calendar calendar = Calendar.getInstance();
        Date date = new Date();
        date.setDate(date.getDate()-day_ago);
        calendar.setTime(date);
        int Day = calendar.get(Calendar.DATE);
        String Month = monthNames[calendar.get(Calendar.MONTH)];
        return Day + " " + Month;
    }

    public static void main(String[] args){
        JSONArray jsonxAXIS = new JSONArray();
        for(int i = 4;i>=0;i--){
           String day = getDateAgo(1+7*i);
            jsonxAXIS.put(day);
       }
//        System.out.println(jsonArray.toString());
    }
}
