package com.springapp.mvc;

import java.sql.*;
import java.util.Date;

/**
 * Created by James on 7/3/15 AD.
 */
public class test {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String url = "jdbc:oracle:thin:@//10.224.102.10:2992/pdev";
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection connect = DriverManager.getConnection(url, "kioskpx", "kioskdev");
        ResultSet resultSet;
        Statement state = connect.createStatement();
        resultSet = state.executeQuery("select sum(AMOUNT) as ACTUAL " +
                "                from TR_PAY_MULTIBILL t1,TR_PAY_DETAIL_MULTIBILL t2 " +
                "                where t1.TRANS_ID = t2.TRANS_ID " +
                "                and t1.PAYMENT_DATETIME between to_date('01/01/2015 00:00:00', 'dd/MM/yyyy hh24:mi:ss') and to_date(SYSDATE, 'dd/MM/yyyy hh24:mi:ss')");
        resultSet.next();
        Double ans = resultSet.getDouble("ACTUAL");
        System.out.println(ans);

    }
}
