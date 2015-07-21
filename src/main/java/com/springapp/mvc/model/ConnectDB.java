package com.springapp.mvc.model;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class ConnectDB {
    private Connection connect;

    public Connection getConnect() {
        return connect;
    }

    public void setConnect(String username,String pass) throws ClassNotFoundException, SQLException {
        String url = "jdbc:oracle:thin:@//10.224.102.10:2992/pdev";
        Class.forName("oracle.jdbc.driver.OracleDriver");
        this.connect = DriverManager.getConnection(url, username, pass);
        
    }
}
