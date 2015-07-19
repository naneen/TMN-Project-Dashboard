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
                "                and t1.PAYMENT_DATETIME between to_date('01/01/2015 00:00:00', 'dd/MM/yyyy hh24:mi:ss') and SYSDATE");
        resultSet.next();
        int ans = resultSet.getInt("ACTUAL");
        resultSet.close();
        state.close();
        return ans;
    }

    public int getTarget() throws SQLException {
        ResultSet resultSet;
        Statement state;
        state = connectKiosk.getConnect().createStatement();
        resultSet = state.executeQuery("SELECT TARGET_AMOUNT as TARGET " +
                "                FROM ( " +
                "                  SELECT *  " +
                "                  FROM TEST_DT_TARGET a " +
                "                  WHERE SYSDATE between a.TARGET_DATE_START and a.TARGET_DATE_END " +
                "                  ORDER BY a.TARGET_DATE_START desc) " +
                "                 WHERE rownum <= 1 ");
        resultSet.next();
        int ans = resultSet.getInt("TARGET");
        resultSet.close();
        state.close();
        return ans;
    }
}
