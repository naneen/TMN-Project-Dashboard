package com.springapp.mvc.controller;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.springapp.mvc.model.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.*;
import java.text.DecimalFormat;
import java.util.*;

@Controller
public class KioskController {
    @Autowired
    private ConnectDB connectKiosk;
    @Autowired
    private QueryRevenueBar queryRevenueBar;
    @Autowired
    private QueryTop4 queryTop4;
    @Autowired
    private QueryPieChart queryPieChart;
    @Autowired
    QueryBillTopup query;


    public int getPercent(int count,int sum){
        return (int) Math.round((double) count / sum * 100);
    }

    public TreeMap<String, Integer> getSort(HashMap<String, Integer> map){
        ValueComparator bvc =  new ValueComparator(map);
        TreeMap<String, Integer> sorted_map = new TreeMap<String, Integer>(bvc);
        sorted_map.putAll(map);
        return sorted_map;
    }

    @RequestMapping(value = "/revenueBar", method = RequestMethod.GET)
    public @ResponseBody
    String revenueBar() throws JSONException, SQLException {
        JSONObject userJSON = new JSONObject();
        int actual = queryRevenueBar.getActual();
        int target = queryRevenueBar.getTarget();
        int percent = getPercent(actual, target);
        userJSON.put("actual", actual);
        userJSON.put("target", target);
        userJSON.put("percent", percent);

        return userJSON.toString();
    }

    @RequestMapping(value = "/bill_topup_chart",method = RequestMethod.GET,produces = "text/plain;charset=UTF-8")
    public @ResponseBody
    String Revenue() throws SQLException, JSONException {
        System.out.println("1");
        Object[][] bill = new Object[5][2];
        Object[][] topup = new Object[5][2];


        System.out.println("2");
        query.getBillAmount();
        query.getDate();
        query.getTopupAmount();
        System.out.println("3");
        //date of each week
        topup[0][0] = query.weekd;
        topup[1][0] = query.week1d;
        topup[2][0] = query.week2d;
        topup[3][0] = query.week3d;
        topup[4][0] = query.week4d;


        bill[0][0] = query.weekd;
        bill[1][0] = query.week1d;
        bill[2][0] = query.week2d;
        bill[3][0] = query.week3d;
        bill[4][0] = query.week4d;

        //bill of each week , b = bill
        bill[0][1] = query.weekb;
        bill[1][1] = query.week1b;
        bill[2][1] = query.week2b;
        bill[3][1] = query.week3b;
        bill[4][1] = query.week4b;

        //topup of each week , t = topup
        topup[0][1] = query.weekt ;
        topup[1][1] = query.week1t;
        topup[2][1] = query.week2t;
        topup[3][1] = query.week3t;
        topup[4][1] = query.week4t;



        JSONObject rootJSON = new JSONObject();

        rootJSON.put("bill", bill);
        rootJSON.put("topup", topup);

        System.out.println("Root: " + rootJSON.toString());

        return rootJSON.toString() ;
    }



    @RequestMapping(value = "/Top4", method = RequestMethod.GET,produces = "text/plain;charset=UTF-8")
    public @ResponseBody
    String Top4() throws SQLException, JSONException {
        JSONObject userJSON;
        JSONArray userArray = new JSONArray();
        int sum = queryTop4.getSum();
        HashMap<String, Integer> map = queryTop4.getCountByPlace(sum);
        Iterator<String> Vmap = getSort(map).keySet().iterator();
        for(int i = 1;i<=4;i++) {
            userJSON = new JSONObject();
            if(Vmap.hasNext()){
                String key = Vmap.next();
                int val = map.get(key);
                userJSON.put("place",key);
                userJSON.put("percent",val);
            }
            else{
                userJSON.put("place","");
                userJSON.put("percent","");
            }
            userArray.put(userJSON);
        }
        map.clear();
        return userArray.toString();
    }

    @RequestMapping(value = "/PieChart", method = RequestMethod.GET)
    public @ResponseBody
    String queryPieChart() throws SQLException {
        int countBillKisok = queryPieChart.getCountBillKiosk();
        int countBillTRM = queryPieChart.getCountBillTRM();
        int avgOffset = getPercent(countBillKisok, countBillKisok + countBillTRM);
        return Integer.toString(avgOffset);
    }


    @RequestMapping(value = "/",method = RequestMethod.GET )
    public String product(ModelMap model) throws SQLException, ClassNotFoundException {
        connectKiosk.setConnect("kioskpx", "kioskdev");
//        revenueGraph(model);
//        return "TMNProduct";
        return "TempKiosk";
    }
}