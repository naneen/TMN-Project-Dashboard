package com.springapp.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.*;

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

    @RequestMapping(value = "/QueryTop4", method = RequestMethod.GET)
    public @ResponseBody
    String QueryTop4() throws SQLException {
        StringBuilder resultTop4 = new StringBuilder();
        ResultSet resultSet = null;
        Statement state = null;
        state = connect.createStatement();
        int i = 1;
        resultSet = state.executeQuery("SELECT * FROM DashBoardTable order by percent desc limit 0,4");
        while(resultSet.next()){
            String name = resultSet.getString("name");
            int percent = resultSet.getInt("percent");
            resultTop4.append("<br><div class = \"boxs"+i+"\">" + name + ":" + percent + "<div>");
            i++;
        }
        return resultTop4.toString();
    }

    @RequestMapping(value = "/QueryPieChart", method = RequestMethod.GET)
    public @ResponseBody
    String QueryPieChart() throws SQLException {
        ResultSet  resultSet = null;
        Statement state = null;
        state = connect.createStatement();
        int avgOffset = 0;
            resultSet = state.executeQuery("SELECT AVG(percent) FROM  DashBoardTable");
            resultSet.next();
            avgOffset = resultSet.getInt("AVG(percent)");
        return avgOffset+"";
    }

    @RequestMapping(value = "/",method = RequestMethod.GET )
    public String MainCon() throws SQLException, ClassNotFoundException {
        ConnectDB();
        return "hello";
    }
}