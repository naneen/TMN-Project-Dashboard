package com.springapp.mvc.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.*;

@Controller
public class TMNProductController {

    @RequestMapping(value = "/",method = RequestMethod.GET )
    public String product(ModelMap model) {
        model.addAttribute("msg", "TMN Product Dashboard");
        return "TMNProduct";
    }


    @RequestMapping(value = "/transaction", method = RequestMethod.GET)
    public @ResponseBody
    String trans() throws SQLException, JSONException, ClassNotFoundException {
        Connection connect = null;
    String url = "jdbc:oracle:thin:@//10.224.102.10:2992/pdev";
    String username = "kioskpx";
    String pass = "kioskdev";

    Class.forName("oracle.jdbc.driver.OracleDriver");
    connect = DriverManager.getConnection(url, username, pass);

        ResultSet resultSet1;
        Statement state1;
        state1 = connect.createStatement();

        resultSet1 = state1.executeQuery("select COUNT(*) as COUNT from TR_PAY_MULTIBILL " +
                "where CREATED BETWEEN to_date('01/01/2015','dd/MM/yyyy') and to_date(sysdate,'dd/MM/yyyy')");

        resultSet1.next();


  String tr=resultSet1.getString("COUNT");

        resultSet1.close();
        state1.close();
        return tr;
    }

}
