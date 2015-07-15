package com.springapp.mvc.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class QueryDeployChart {

    @Autowired
    private ConnectDB connectKiosk;

    public int getCountBillKiosk() throws SQLException {
        ResultSet kioskUpdatedSet, kioskAllSet;
        Statement state1,state2;
        state1 = connectKiosk.getConnect().createStatement();
        state2 = connectKiosk.getConnect().createStatement();

        kioskUpdatedSet = state1.executeQuery("select count(KIOSK_ID) as UPDATED " +
                "from TEST_DT_KIOSK_DEPLOYMENT deploy " +
                "where deploy.KIOSKVER_ID = ( " +
                "select MAX(KIOSKVER_ID) " +
                "from TEST_DT_KIOSK_DEPLOY_VERSIONS)");
        kioskUpdatedSet.next();
        int number_updated = kioskUpdatedSet.getInt("UPDATED");
        kioskUpdatedSet.close();

        kioskAllSet = state2.executeQuery("select count(*) as TOTAL " +
                "from TEST_DT_KIOSK_DEPLOYMENT deploy " );
        kioskAllSet.next();
        int number_all = kioskAllSet.getInt("TOTAL");
        kioskUpdatedSet.close();

        double percentUpdated = 100.0 * number_updated / number_all;

        state1.close();
        state2.close();
        return (int)percentUpdated;
    }

    public String getLastestVersion() throws SQLException{

        ResultSet versionSet;
        Statement state;
        state = connectKiosk.getConnect().createStatement();

        versionSet = state.executeQuery("select KIOSK_VERSIONS as LASTEST " +
                "from TEST_DT_KIOSK_DEPLOY_VERSIONS " +
                "where KIOSKVER_ID = ( " +
                "select MAX(KIOSKVER_ID) " +
                "from TEST_DT_KIOSK_DEPLOY_VERSIONS)" );
        versionSet.next();
        String ver = versionSet.getString("LASTEST");
        versionSet.close();

        state.close();
        return ver;
    }
}
