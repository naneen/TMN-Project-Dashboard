package com.springapp.mvc.controller;

import com.springapp.mvc.model.Offset;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

@Controller
@RequestMapping( "/" )
public class HelloController {

    @RequestMapping( method = RequestMethod.GET )
	public String printWelcome( ModelMap model ) {
		model.addAttribute( "message", "Hello world!" );
		model.addAttribute( "avgOffset", getAvgOffset() );
		return "hello";
	}

    private double getAvgOffset() {
        String url = "jdbc:mysql://localhost:3306/test";
        String username = "";
        String password = "";
        Connection connection;
        double avgOffset = 0;

        System.out.println("Connecting database...");

        try {
            connection = DriverManager.getConnection( url, username, password );
            try {
                System.out.println( "Database connected!" );
                Statement stmt = connection.createStatement();

                ResultSet avg = stmt.executeQuery( "SELECT AVG( offset ) FROM  dashboard" );
                avg.next();
                avgOffset = avg.getDouble("AVG( offset )");

            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException("Cannot connect the database!", e);
        }

        return avgOffset;
    }
}