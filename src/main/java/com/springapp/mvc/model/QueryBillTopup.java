package com.springapp.mvc.model;

import org.springframework.beans.factory.annotation.Autowired;

import java.sql.ResultSet;
import java.sql.Statement;

import java.sql.SQLException;


/**
 * Created by Bas on 6/7/2558.
 */
public class QueryBillTopup {
    @Autowired
    private ConnectDB connectKiosk;

    //bill of each week , b = bill
    public double week1b;
    public double week2b;
    public double week3b;
    public double week4b;

    //topup of each week , t = topup
    public double week1t;
    public double week2t;
    public double week3t;
    public double week4t;

    //date of each week
    public String week1d;
    public String week2d;
    public String week3d;
    public String week4d;

    public void getBillAmount() throws SQLException  {

        ResultSet resultSet1b, resultSet2b, resultSet3b, resultSet4b;
        Statement state1b, state2b, state3b, state4b;

        state1b = connectKiosk.getConnect().createStatement();
        state2b = connectKiosk.getConnect().createStatement();
        state3b = connectKiosk.getConnect().createStatement();
        state4b = connectKiosk.getConnect().createStatement();

        // Query Database of bill transaction
        resultSet1b = state1b.executeQuery("SELECT COUNT(DISTINCT TRANS_ID)  as COUNT " +
                "FROM TR_TRANS_MULTIBILL h " +
                "WHERE SVC_ID = 'PostBillConfirm' and STATE = 0 " +
                "and (created >= TO_CHAR(SYSDATE - 7, 'DD-MON-YYYY') and created <= TO_CHAR(SYSDATE, 'DD-MON-YYYY'))");
        resultSet2b = state2b.executeQuery("SELECT COUNT(DISTINCT TRANS_ID)  as COUNT " +
                "FROM TR_TRANS_MULTIBILL h " +
                "WHERE SVC_ID = 'PostBillConfirm' and STATE = 0 " +
                "and (created >= TO_CHAR(SYSDATE - 14, 'DD-MON-YYYY') and created <= TO_CHAR(SYSDATE-7, 'DD-MON-YYYY'))");
        resultSet3b = state3b.executeQuery("SELECT COUNT(DISTINCT TRANS_ID)  as COUNT " +
                "FROM TR_TRANS_MULTIBILL h " +
                "WHERE SVC_ID = 'PostBillConfirm' and STATE = 0 " +
                "and (created >= TO_CHAR(SYSDATE - 21, 'DD-MON-YYYY') and created <= TO_CHAR(SYSDATE-14, 'DD-MON-YYYY'))");
        resultSet4b = state4b.executeQuery("SELECT COUNT(DISTINCT TRANS_ID)  as COUNT " +
                "FROM TR_TRANS_MULTIBILL h  " +
                "WHERE h.SVC_ID = 'PostBillConfirm' and h.STATE = 0 " +
                "and (created >= TO_CHAR(SYSDATE - 28, 'DD-MON-YYYY') and created <= TO_CHAR(SYSDATE-21, 'DD-MON-YYYY'))");

        resultSet1b.next();
        resultSet2b.next();
        resultSet3b.next();
        resultSet4b.next();

        week1b = resultSet1b.getDouble("COUNT");
        week2b = resultSet2b.getDouble("COUNT");
        week3b = resultSet3b.getDouble("COUNT");
        week4b = resultSet4b.getDouble("COUNT");


        resultSet1b.close();
        resultSet2b.close();
        resultSet3b.close();
        resultSet4b.close();

        state1b.close();
        state2b.close();
        state3b.close();
        state4b.close();

    }

    public void getTopupAmount() throws SQLException  {

        ResultSet resultSet1t, resultSet2t, resultSet3t, resultSet4t;
        Statement state1t, state2t, state3t, state4t;
        state1t = connectKiosk.getConnect().createStatement();
        state2t = connectKiosk.getConnect().createStatement();
        state3t = connectKiosk.getConnect().createStatement();
        state4t = connectKiosk.getConnect().createStatement();


        // Query Database of topup transaction
        resultSet1t = state1t.executeQuery("SELECT COUNT(DISTINCT TRANS_ID) as COUNT " +
                "FROM TR_TRANS_MULTIBILL " +
                "WHERE SVC_ID = 'ConfirmMobileTopupAndDebit' and STATE = 0 " +
                "and (created >= TO_CHAR(SYSDATE - 7, 'DD-MON-YYYY') and created <= TO_CHAR(SYSDATE, 'DD-MON-YYYY'))");
        resultSet2t = state2t.executeQuery("SELECT COUNT(DISTINCT TRANS_ID)  as COUNT " +
                "FROM TR_TRANS_MULTIBILL " +
                "WHERE SVC_ID = 'ConfirmMobileTopupAndDebit' and STATE = 0 " +
                "and (created >= TO_CHAR(SYSDATE - 14, 'DD-MON-YYYY') and created <= TO_CHAR(SYSDATE-7, 'DD-MON-YYYY'))");
        resultSet3t = state3t.executeQuery("SELECT COUNT(DISTINCT TRANS_ID)  as COUNT " +
                "FROM TR_TRANS_MULTIBILL " +
                "WHERE SVC_ID = 'ConfirmMobileTopupAndDebit' and STATE = 0 " +
                "and (created >= TO_CHAR(SYSDATE - 21, 'DD-MON-YYYY') and created <= TO_CHAR(SYSDATE-14, 'DD-MON-YYYY'))");
        resultSet4t = state4t.executeQuery("SELECT COUNT(DISTINCT TRANS_ID)  as COUNT " +
                "FROM TR_TRANS_MULTIBILL " +
                "WHERE SVC_ID = 'ConfirmMobileTopupAndDebit' and STATE = 0 " +
                "and (created >= TO_CHAR(SYSDATE - 28, 'DD-MON-YYYY') and created <= TO_CHAR(SYSDATE-21, 'DD-MON-YYYY'))");


        resultSet1t.next();
        resultSet2t.next();
        resultSet3t.next();
        resultSet4t.next();


        week1t = resultSet1t.getDouble("COUNT");
        week2t = resultSet2t.getDouble("COUNT");
        week3t = resultSet3t.getDouble("COUNT");
        week4t = resultSet4t.getDouble("COUNT");

        resultSet1t.close();
        resultSet2t.close();
        resultSet3t.close();
        resultSet4t.close();

        state1t.close();
        state2t.close();
        state3t.close();
        state4t.close();

    }

    public void getDate() throws SQLException  {


        ResultSet resultSet1d, resultSet2d, resultSet3d, resultSet4d;
        Statement state1d, state2d, state3d, state4d;

        state1d = connectKiosk.getConnect().createStatement();
        state2d = connectKiosk.getConnect().createStatement();
        state3d = connectKiosk.getConnect().createStatement();
        state4d = connectKiosk.getConnect().createStatement();

        //query date
        resultSet1d = state1d.executeQuery("SELECT TO_CHAR(SYSDATE - 28, 'DD-MON-YYYY') as TEXT FROM dual");
        resultSet2d = state2d.executeQuery("SELECT TO_CHAR(SYSDATE - 21, 'DD-MON-YYYY') as TEXT FROM dual");
        resultSet3d = state3d.executeQuery("SELECT TO_CHAR(SYSDATE - 14, 'DD-MON-YYYY') as TEXT FROM dual");
        resultSet4d = state4d.executeQuery("SELECT TO_CHAR(SYSDATE - 7, 'DD-MON-YYYY') as TEXT FROM dual");

        resultSet1d.next();
        resultSet2d.next();
        resultSet3d.next();
        resultSet4d.next();

        week1d = resultSet1d.getString("TEXT");
        week2d = resultSet1d.getString("TEXT");
        week3d = resultSet1d.getString("TEXT");
        week4d = resultSet1d.getString("TEXT");

        resultSet1d.close();
        resultSet2d.close();
        resultSet3d.close();
        resultSet4d.close();

        state1d.close();
        state2d.close();
        state3d.close();
        state4d.close();



    }
}
