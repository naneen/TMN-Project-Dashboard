package com.springapp.mvc.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class QueryPieChart {

    @Autowired
    private ConnectDB connectKiosk;


    public Double getCountBillKiosk() throws SQLException {
        ResultSet resultSet;
        Statement state;
        state = connectKiosk.getConnect().createStatement();
        resultSet = state.executeQuery("select count(*) as COUNT " +
                "from TR_PAY_DETAIL_MULTIBILL t1,TR_PAY_MULTIBILL t2,RE_LOCATION_KIOSK t3 " +
                "where t1.trans_id in ( " +
                "Select distinct(TRANS_ID) " +
                "from TR_TRANS_MULTIBILL " +
                "where SVC_ID = 'PostBillConfirm' and state=0 and TO_CHAR (SYSDATE-1,'DD-MON-YYYY') = TO_CHAR (ENDED,'DD-MON-YYYY')) " +
                "and t1.TRANS_ID = t2.TRANS_ID and t2.KIOSK_ID = t3.KIOSK_ID");
        resultSet.next();
        Double ans = resultSet.getDouble("COUNT");
        resultSet.close();
        state.close();
        return ans;
    }

    public Double getCountBillTRM() throws SQLException {
        ResultSet resultSet;
        Statement state;
        state = connectKiosk.getConnect().createStatement();
        resultSet = state.executeQuery("select SUM(TRM_AMOUNT) as COUNT " +
                "from DT_TRM " +
                "where TO_CHAR (SYSDATE-1,'DD-MON-YYYY') = TO_CHAR (TRM_DATE,'DD-MON-YYYY')");
        resultSet.next();
        Double ans = resultSet.getDouble("COUNT");
        resultSet.close();
        state.close();
        return ans;
    }
}
