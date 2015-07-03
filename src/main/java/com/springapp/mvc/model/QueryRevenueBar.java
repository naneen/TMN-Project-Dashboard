package com.springapp.mvc.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


@Component
public class QueryRevenueBar {

    @Autowired
    private ConnectDB connectKiosk;


    public int getActual() throws SQLException {
        ResultSet resultSet;
        Statement state;
        state = connectKiosk.getConnect().createStatement();
        resultSet = state.executeQuery("select sum(AMOUNT) as ACTUAL " +
                "                from TR_PAY_MULTIBILL t1,TR_PAY_DETAIL_MULTIBILL t2 " +
                "                where t1.TRANS_ID = t2.TRANS_ID " +
                "                and t1.PAYMENT_DATETIME between to_date('01/01/2015 00:00:00', 'dd/MM/yyyy hh24:mi:ss') and to_date(SYSDATE, 'dd/MM/yyyy hh24:mi:ss')");
        resultSet.next();
        String ans2 = resultSet.getString("ACTUAL");
        System.out.println(ans2);
        int ans = resultSet.getInt("ACTUAL");
        System.out.println(ans);
        resultSet.close();
        state.close();
        return ans;
    }

    public int getTarget() throws SQLException {
        ResultSet resultSet;
        Statement state;
        state = connectKiosk.getConnect().createStatement();
        resultSet = state.executeQuery("SELECT TARGET_AMOUNT as TARGET " +
                "                       FROM ( " +
                "                       SELECT a.TARGET_DATE,a.TARGET_AMOUNT  " +
                "                       FROM DT_TARGET a " +
                "                       ORDER BY TARGET_DATE desc) " +
                "                       WHERE rownum <= 1 and to_date(TARGET_DATE, 'dd/MM/yyyy hh24:mi:ss') <= to_date(SYSDATE, 'dd/MM/yyyy hh24:mi:ss')");
        resultSet.next();
        int ans = resultSet.getInt("TARGET");
        resultSet.close();
        state.close();
        return ans;
    }
}
