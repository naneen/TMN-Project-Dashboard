package com.springapp.mvc.controller;

import com.springapp.mvc.model.Offset;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.sql.*;
import java.util.ArrayList;

@Controller
@RequestMapping( "/" )
public class HelloController {
    Connection connect = null;
    Statement state = null;
    String url = "jdbc:mysql://localhost:3306/test";
    String username = "";
    String pass = "";
    ResultSet resultSet = null;

    public void ConnectDB(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connect = DriverManager.getConnection(url,username,pass);
            state = connect.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/QueryTop4", method = RequestMethod.GET)
    public @ResponseBody
    String QueryTop4(){
        StringBuilder resultTop4 = new StringBuilder();
        int i = 1;
        try {
            resultSet = state.executeQuery("SELECT * FROM DashBoardTable order by percent desc limit 0,4");
            while(resultSet.next()){
                String name = resultSet.getString("name");
                int percent = resultSet.getInt("percent");
                resultTop4.append("<br><div class = \"boxs"+i+"\">" + name + ":" + percent + "<div>");
                System.out.println("<br><div class = \"boxs"+i+"\">" + name + ":" + percent + "<div>");
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultTop4.toString();
    }

    @RequestMapping(value = "/QueryPieChart", method = RequestMethod.GET)
    public @ResponseBody
    String QueryPieChart(){
        int avgOffset = 0;
        try {
            resultSet = state.executeQuery("SELECT AVG(percent) FROM  DashBoardTable");
            resultSet.next();
            avgOffset = resultSet.getInt("AVG(percent)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return avgOffset+"";
    }

    @RequestMapping(value = "/",method = RequestMethod.GET )
    public String MainCon(){
        ConnectDB();
        return "hello";
    }

}