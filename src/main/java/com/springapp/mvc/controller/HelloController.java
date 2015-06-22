package com.springapp.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.*;
import java.util.*;
import java.util.Date;

@Controller
@RequestMapping( "/" )
public class HelloController {
    Connection connect = null;
    String url = "jdbc:mysql://localhost:3306/test";
    String username = "";
    String pass = "";

    public void ConnectDB() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        connect = DriverManager.getConnection(url,username,pass);
    }

    public void Revenue(ModelMap model){
        double actual = 698;
        double target = 666;
        double percent = (actual/target)*100;

        String topup = "[[\"Jan\", 67],[\"Feb\", 91],[\"Mar\", 36],[\"Apr\", 190],[\"May\", 28],[\"Jun\", 123],[\"Jul\", 38]]";
        String bill = "[[\"Jan\", 94],[\"Feb\", 49],[\"Mar\", 45],[\"Apr\", 94],[\"May\", 76],[\"Jun\", 22],[\"Jul\", 31]]";

        model.addAttribute("actual", Double.toString(actual));
        model.addAttribute("target", Double.toString(target));
        model.addAttribute("topup", topup);
        model.addAttribute("bill", bill);
        model.addAttribute("percent",Integer.toString((int)percent));
    }


    @RequestMapping(value = "/QueryTop4", method = RequestMethod.GET)
    public @ResponseBody
    String QueryTop4() throws SQLException {
        StringBuilder resultTop4 = new StringBuilder();
        HashMap<String,Integer> map = new HashMap<String,Integer>();
        ValueComparator bvc =  new ValueComparator(map);
        TreeMap<String,Integer> sorted_map = new TreeMap<String,Integer>(bvc);
        ResultSet resultSet;
        Statement state;

        state = connect.createStatement();
        resultSet = state.executeQuery("SELECT * FROM DashBoardTable");
        while(resultSet.next()){
            String name = resultSet.getString("name");
            int percent = resultSet.getInt("percent");
            map.put(name,percent);
        }
        sorted_map.putAll(map);
        Iterator<String> Vmap = sorted_map.keySet().iterator();
        for(int i = 1;i<=4;i++) {
            if(Vmap.hasNext()) {
                String key = Vmap.next();
                Integer val = map.get(key);
                resultTop4.append("<div id=\"box" + i + "\" class=\"boxall\"><img src=\"resources/img/" + i + ".png\" width=20%/><center>" + key + "<br>" + val + "%</center></div>");
            }
            else{
                resultTop4.append("<div id=\"box" + i + "\" class=\"boxall\"><img src=\"resources/img/" + i + ".png\" width=20%/></div>");
            }
        }
        map.clear();
        resultSet.close();
        state.close();
        return resultTop4.toString();
    }


    @RequestMapping(value = "/QueryPieChart", method = RequestMethod.GET)
    public @ResponseBody
    String QueryPieChart() throws SQLException {
        ResultSet  resultSet;
        Statement state;
        state = connect.createStatement();
        int avgOffset = 0;
        resultSet = state.executeQuery("SELECT AVG(percent) FROM  DashBoardTable");
        resultSet.next();
        avgOffset = resultSet.getInt("AVG(percent)");
        resultSet.close();
        state.close();
        return avgOffset+"";
    }

    @RequestMapping(value = "/TimeSet", method = RequestMethod.GET)
    public @ResponseBody
    String TimeSet(){
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

    @RequestMapping(value = "/",method = RequestMethod.GET )
    public String MainCon(ModelMap model) throws SQLException, ClassNotFoundException {
        ConnectDB();
        Revenue(model);
        return "hello";
    }
}