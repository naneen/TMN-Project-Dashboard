package com.springapp.mvc.controller;

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
        int percent = getPercent(actual,target);
        userJSON.put("actual", actual);
        userJSON.put("target", target);
        userJSON.put("percent", percent);
        userJSON.put("percent", 100-percent);
        return userJSON.toString();
    }

    public void revenueGraph(ModelMap model){
        String topup = "[[\"Start\", 0],[\"Week1\", 91],[\"Week2\", 36],[\"Week3\", 100],[\"Week4\", 64]]";
        String bill = "[[\"Start\", 0],[\"Week1\", 29],[\"Week2\", 15],[\"Week3\", 67],[\"Week4\", 40]]";
        model.addAttribute("topup", topup);
        model.addAttribute("bill", bill);
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

    @RequestMapping(value = "/kiosk",method = RequestMethod.GET )
    public String mainCon(ModelMap model) throws SQLException, ClassNotFoundException {
        connectKiosk.setConnect("kioskpx", "kioskdev");
        revenueGraph(model);
        return "Kiosk";
    }

    @RequestMapping(value = "/",method = RequestMethod.GET )
    public String product(ModelMap model) throws SQLException, ClassNotFoundException {
        connectKiosk.setConnect("kioskpx", "kioskdev");
        revenueGraph(model);
//        return "TMNProduct";
        return "TempKiosk";
    }
}