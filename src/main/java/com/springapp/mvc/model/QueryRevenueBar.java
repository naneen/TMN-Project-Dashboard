package com.springapp.mvc.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class QueryRevenueBar {

    @Autowired
    private ConnectDB connectKiosk;


    public Double getActual() throws SQLException {
        ResultSet resultSet;
        Statement state;
        state = connectKiosk.getConnect().createStatement();
        resultSet = state.executeQuery("select sum(AMOUNT) as ACTUAL " +
                "from TR_PAY_MULTIBILL t1,TR_PAY_DETAIL_MULTIBILL t2 " +
                "where t1.TRANS_ID = t2.TRANS_ID and TO_CHAR (SYSDATE-1,'DD-MON-YYYY') = TO_CHAR (t1.PAYMENT_DATETIME,'DD-MON-YYYY') ");
        resultSet.next();
//        String ans2 = resultSet.getString("ACTUAL");
//        System.out.println(ans2);
        Double ans = resultSet.getDouble("ACTUAL");
        System.out.println(ans);
        resultSet.close();
        state.close();
        return ans;
    }

    public Double getTarget() throws SQLException {
        ResultSet resultSet;
        Statement state;
        state = connectKiosk.getConnect().createStatement();
        resultSet = state.executeQuery("SELECT TARGET_AMOUNT as TARGET  " +
                                    "FROM ( " +
                                    "  SELECT a.TARGET_DATE,a.TARGET_AMOUNT  " +
                                    "  FROM DT_TARGET a\n" +
                                    "  ORDER BY TARGET_DATE desc " +
                                    ") " +
                                    "WHERE rownum <= 1 ");
        resultSet.next();
        Double ans = resultSet.getDouble("TARGET");
        resultSet.close();
        state.close();
        return ans;
    }
}
