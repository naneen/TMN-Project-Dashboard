package com.springapp.mvc.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.*;
import java.util.*;
import java.util.Date;

@Controller
public class KioskController {

    public static Connection connect = null;
    public static String url = "jdbc:oracle:thin:@//10.224.102.10:2992/pdev";
    public static String username = "kioskpx";
    public static String pass = "kioskdev";


    public void fucConnectDB() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        connect = DriverManager.getConnection(url,username,pass);
    }

    public String fucPlaceCut(String place){
        ArrayList<String> CutPlaceSave = new ArrayList<String>();
        CutPlaceSave.add("True Shop Station in True Coffee ");
        CutPlaceSave.add("True Shop Mini ");
        CutPlaceSave.add("True Shop ");
        CutPlaceSave.add("TrueCoffee @ ");
        CutPlaceSave.add("TrueCoffee ");
        CutPlaceSave.add("True Coffee ");
        CutPlaceSave.add("True Move Shop ");
        CutPlaceSave.add("True Life ");

        for(String str : CutPlaceSave) {
            if(place.contains(str)) {
                place = place.substring(str.length());
                return place;
            }
        }
        return place;
    }

    public int getPercent(int count,int sum){
        int percent = (int) Math.round((double)count/sum*100);
        return percent;
    }

    @RequestMapping(value = "/revenueBar", method = RequestMethod.GET)
    public @ResponseBody
    String revenueBar() throws JSONException, SQLException {
        JSONObject userJSON = new JSONObject();
        ResultSet resultSet1,resultSet2;
        Statement state1,state2;
        state1 = connect.createStatement();
        state2 = connect.createStatement();
        resultSet1 = state1.executeQuery("select sum(AMOUNT) as ACTUAL " +
                "from TR_PAY_MULTIBILL t1,TR_PAY_DETAIL_MULTIBILL t2 " +
                "where t1.TRANS_ID = t2.TRANS_ID and TO_CHAR (SYSDATE-1,'DD-MON-YYYY') = TO_CHAR (t1.PAYMENT_DATETIME,'DD-MON-YYYY') ");
        resultSet2 = state2.executeQuery("select TARGET_AMOUNT as TARGET " +
                "from DT_TARGET " +
                "where TO_CHAR (SYSDATE-1,'DD-MON-YYYY') = TO_CHAR (TARGET_DATE,'DD-MON-YYYY') ");
        resultSet1.next();
        resultSet2.next();
        int actual = resultSet1.getInt("ACTUAL");
        int target = resultSet2.getInt("TARGET");
        int percent = getPercent(actual,target);
        userJSON.put("actual", actual);
        userJSON.put("target", target);
        userJSON.put("percent", percent);
        return userJSON.toString();
    }

    public void revenueGraph(ModelMap model){
        String topup = "[[\"Start\", 0],[\"Week1\", 91],[\"Week2\", 36],[\"Week3\", 100],[\"Week4\", 64]]";
        String bill = "[[\"Start\", 0],[\"Week1\", 29],[\"Week2\", 15],[\"Week3\", 67],[\"Week4\", 40]]";
        model.addAttribute("topup", topup);
        model.addAttribute("bill", bill);
    }

    @RequestMapping(value = "/QueryTop4", method = RequestMethod.GET,produces = "text/plain;charset=UTF-8")
    public @ResponseBody
    String queryTop4() throws SQLException {
        StringBuilder resultTop4 = new StringBuilder();
        HashMap<String,Integer> map = new HashMap<String,Integer>();
        ValueComparator bvc =  new ValueComparator(map);
        TreeMap<String,Integer> sorted_map = new TreeMap<String,Integer>(bvc);
        ResultSet resultSet1,resultSet2;
        Statement state1,state2;
        state1 = connect.createStatement();
        state2 = connect.createStatement();
        resultSet1 = state1.executeQuery("select t4.PLACE,count(*) as COUNT " +
                "from TR_PAY_DETAIL_MULTIBILL t1,TR_PAY_MULTIBILL t2,RE_LOCATION_KIOSK t3,DT_LOCATION t4 " +
                "where t1.TRANS_ID = t2.TRANS_ID and  (t1.trans_id,t2.KIOSK_ID) in ( " +
                "Select distinct(TRANS_ID),KIOSK_ID from TR_TRANS_MULTIBILL where SVC_ID = 'PostBillConfirm' and state=0 and TO_CHAR (SYSDATE-1,'DD-MON-YYYY') = TO_CHAR (ENDED,'DD-MON-YYYY')) " +
                "and t2.KIOSK_ID = t3.KIOSK_ID and t3.LOCATION_ID = t4.LOCATION_ID " +
                "group by t4.PLACE ");

        resultSet2 = state2.executeQuery("select count(*) as COUNT " +
                "from TR_PAY_DETAIL_MULTIBILL t1,TR_PAY_MULTIBILL t2 " +
                "where t1.TRANS_ID = t2.TRANS_ID and  (t1.trans_id,t2.KIOSK_ID) in ( " +
                "Select distinct(TRANS_ID),KIOSK_ID from TR_TRANS_MULTIBILL where SVC_ID = 'PostBillConfirm' and state=0 and TO_CHAR (SYSDATE-1,'DD-MON-YYYY') = TO_CHAR (ENDED,'DD-MON-YYYY')) ");
        resultSet2.next();
        int sum = resultSet2.getInt("COUNT");
        while(resultSet1.next()){
            String place = resultSet1.getString("PLACE");
            int count = resultSet1.getInt("COUNT");
            place = fucPlaceCut(place);
            count = getPercent(count,sum);
            map.put(place,count);
        }
        sorted_map.putAll(map);
        Iterator<String> Vmap = sorted_map.keySet().iterator();
        for(int i = 1;i<=4;i++) {
            if(Vmap.hasNext()) {
                String key = Vmap.next();
                Integer val = map.get(key);
                resultTop4.append("<div id=\"box" + i + "\" class=\"boxall\"><img src=\"resources/img/" + i + ".png\" width=20% class=\"pic\"/><div id=\"text\"><center><div id=\"PercentageSize\">" + val + "%</div><div id=\"textSize\">" + key + "</div></center></div></div>");
            }
            else{
                resultTop4.append("<div id=\"box" + i + "\" class=\"boxall\"><img src=\"resources/img/" + i + ".png\" width=20%/></div>");
            }
        }
        map.clear();
        resultSet1.close();
        resultSet2.close();
        state1.close();
        state2.close();
        return resultTop4.toString();
    }

    @RequestMapping(value = "/QueryPieChart", method = RequestMethod.GET)
    public @ResponseBody
    String queryPieChart() throws SQLException {
        ResultSet  resultSet1,resultSet2;
        Statement state1,state2;
        state1 = connect.createStatement();
        state2 = connect.createStatement();
        int avgOffset = 0;

        resultSet1 = state1.executeQuery("select count(*) as COUNT " +
                "from TR_PAY_DETAIL_MULTIBILL t1,TR_PAY_MULTIBILL t2,RE_LOCATION_KIOSK t3 " +
                "where t1.trans_id in ( " +
                "Select distinct(TRANS_ID) " +
                "from TR_TRANS_MULTIBILL " +
                "where SVC_ID = 'PostBillConfirm' and state=0 and TO_CHAR (SYSDATE-1,'DD-MON-YYYY') = TO_CHAR (ENDED,'DD-MON-YYYY')) " +
                "and t1.TRANS_ID = t2.TRANS_ID and t2.KIOSK_ID = t3.KIOSK_ID");
        resultSet2 = state2.executeQuery("select SUM(TRM_AMOUNT) as COUNT " +
                "from DT_TRM " +
                "where TO_CHAR (SYSDATE-1,'DD-MON-YYYY') = TO_CHAR (TRM_DATE,'DD-MON-YYYY')");
        resultSet1.next();
        resultSet2.next();
        int count1 = resultSet1.getInt("COUNT");
        int count2 = resultSet2.getInt("COUNT");
        avgOffset = getPercent(count1,count1+count2);
        resultSet1.close();
        resultSet2.close();
        state1.close();
        state2.close();
        return Integer.toString(avgOffset);
    }

    @RequestMapping(value = "/TimeSet", method = RequestMethod.GET)
    public @ResponseBody
    String timeSet(){
        String time="";
        Date date = new Date();
        int curSecs = date.getSeconds();
        int curMin = date.getMinutes();
        int curHour = date.getHours();
        if (curHour < 10) {
            time += "0" + curHour + ":";
        }
        else{
            time += curHour + ":";
        }
        if (curMin < 10) {
            time += "0" + curMin + ":";
        }
        else{
            time += curMin + ":";
        }
        if (curSecs < 10) {
            time += "0" + curSecs;
        }
        else{
            time += curSecs;
        }
        return time;
    }

    @RequestMapping(value = "/kiosk",method = RequestMethod.GET )
    public String MainCon(ModelMap model) throws SQLException, ClassNotFoundException {
        fucConnectDB();
        revenueGraph(model);
        return "Kiosk";
    }
}