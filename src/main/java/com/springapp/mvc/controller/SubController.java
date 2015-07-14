package com.springapp.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Calendar;
import java.util.Date;

@Controller
public class SubController {
    @RequestMapping(value = "/getCorrectTime", method = RequestMethod.GET)
    public @ResponseBody
    String getCorrectTime(){
        String time="";
        Date date = new Date();
        int curSecs = date.getSeconds();
        int curMin = date.getMinutes();
        int curHour = date.getHours();
        if (curHour < 10) {
            time += "0" + curHour + ":";
        }
        else{
            time += curHour + ":";
        }
        if (curMin < 10) {
            time += "0" + curMin + ":";
        }
        else{
            time += curMin + ":";
        }
        if (curSecs < 10) {
            time += "0" + curSecs;
        }
        else{
            time += curSecs;
        }
        return time;
    }

    @RequestMapping(value = "/dateYesterDay", method = RequestMethod.GET)
    public @ResponseBody
    String DateYesterDay(){
        String[] monthNames = {"January", "February", "March", "April", "May", "June","July", "August", "September", "October", "November", "December"};
        Calendar calendar = Calendar.getInstance();
        Date date = new Date();
        date.setDate(date.getDate()-1);
        calendar.setTime(date);
        int Day = calendar.get(Calendar.DATE);
        String Month = monthNames[calendar.get(Calendar.MONTH)];
        int Year = calendar.get(Calendar.YEAR);
        return Day + " " + Month + " " + Year;
    }

}
