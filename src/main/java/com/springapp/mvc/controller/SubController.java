package com.springapp.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Calendar;
import java.util.Date;

@Controller
public class SubController {
    @RequestMapping(value = "/GetCorrectTime", method = RequestMethod.GET)
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

    @RequestMapping(value = "/DateYesterDay", method = RequestMethod.GET)
    public @ResponseBody
    String DateYesterDay(){
        String[] monthNames = {"January", "February", "March", "April", "May", "June","July", "August", "September", "October", "November", "December"};
        Calendar date = Calendar.getInstance();
        Date x = new Date();
        x.setDate(x.getDate()-1);
        date.setTime(x);
        int Day = date.get(Calendar.DATE);
        String Month = monthNames[date.get(Calendar.MONTH)];
        int Year = date.get(Calendar.YEAR);
        return Day + " " + Month + " " + Year;
    }

}
