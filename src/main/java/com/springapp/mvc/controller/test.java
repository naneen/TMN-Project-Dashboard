package com.springapp.mvc.controller;

import com.springapp.mvc.model.ConnectDB;
import com.springapp.mvc.model.QueryBillTopup;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.beans.Statement;
import java.sql.SQLException;

/**
 * Created by Bas on 6/7/2558.
 */
public class test {


    @Test
    public void dbdd() throws SQLException, JSONException, ClassNotFoundException{


        QueryBillTopup query = new QueryBillTopup();




        query.getBillAmount();
        query.getDate();
        query.getTopupAmount();

        double week1b = query.week1b;
        double week2b = query.week2b;
        double week3b = query.week3b;
        double week4b = query.week4b;

        //topup of each week , t = topup
        double week1t = query.week1t;
        double week2t = query.week2t;
        double week3t = query.week3t;
        double week4t = query.week4t;

        //date of each week
        String week1d = query.week1d ;
        String week2d = query.week2d ;
        String week3d = query.week3d ;
        String week4d = query.week4d ;

        JSONObject rootJSON = new JSONObject();
        JSONObject billJSON = new JSONObject();
        JSONObject topupJSON = new JSONObject();

        billJSON.put("Start", 0);
        billJSON.put(week1d, Double.toString(week1b));
        billJSON.put(week2d, Double.toString(week2b));
        billJSON.put(week3d, Double.toString(week3b));
        billJSON.put(week4d, Double.toString(week4b));
        rootJSON.put("bill", billJSON);

        topupJSON.put("Start", 0);
        topupJSON.put(week1d, Double.toString(week1t));
        topupJSON.put(week2d, Double.toString(week2t));
        topupJSON.put(week3d, Double.toString(week3t));
        topupJSON.put(week4d, Double.toString(week4t));
        rootJSON.put("topup", topupJSON);

        System.out.println(week1d + week1b);

   }
}
