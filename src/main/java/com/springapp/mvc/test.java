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
        Connection connect = DriverManager.getConnection(url, "bpay", "bpay#123$");
        ResultSet resultSet;
        Statement state;
        state = connect.createStatement();
        String query = "select Count(payment_trans_id) as COUNT from " +
                "(select to_char(payment_date,'yyyymmdd') AS PAYDATE ,payment_trans_id,partner_id,channel,pay_amout,customer_fee,biller_fee,tmn_revernue,partner_revernue,sof,biller_code,REF1,REF2, " +
                "PARTNER_NAME,PAYMENT_TRANSACTION_STATUS AS PAYMAENT_STS " +
                "from bpay.payment_transactions " +
                "where payment_date between to_date('01/01/2015 00:00:00', 'dd/MM/yyyy hh24:mi:ss') and to_date(SYSDATE, 'dd/MM/yyyy hh24:mi:ss')) ";
        resultSet = state.executeQuery(query);
        resultSet.next();
        int ans = resultSet.getInt("COUNT");
        System.out.println(ans);
    }
}
