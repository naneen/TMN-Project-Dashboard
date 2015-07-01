package com.springapp.mvc.controller;

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
    public static final String SetDay = "SYDATE - 1";


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



    public void Revenue(ModelMap model) throws SQLException {
        double actual = 450;
        double target = 666;
        double percent = (actual/target)*100;

        // initial month
        String month = "5" ;

        //bill of each week , b = bill
        double week1b  ;
        double week2b ;
        double week3b  ;
        double week4b  ;

        //topup of each week , t = topup
        double week1t  ;
        double week2t  ;
        double week3t  ;
        double week4t ;

        ResultSet  resultSet1b,resultSet2b,resultSet3b,resultSet4b,resultSet1t,resultSet2t,resultSet3t,resultSet4t;
        Statement state1b,state2b,state3b,state4b,state1t,state2t,state3t,state4t;

        state1t = connect.createStatement();
        state2t = connect.createStatement();
        state3t = connect.createStatement();
        state4t = connect.createStatement();
        state1b = connect.createStatement();
        state2b = connect.createStatement();
        state3b = connect.createStatement();
        state4b = connect.createStatement();

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
                "WHERE SVC_ID = 'PostBillConfirm' and STATE = 0 "+
                "and (created >= TO_CHAR(SYSDATE - 21, 'DD-MON-YYYY') and created <= TO_CHAR(SYSDATE-14, 'DD-MON-YYYY'))");
        resultSet4b = state4b.executeQuery("SELECT COUNT(DISTINCT TRANS_ID)  as COUNT " +
                "FROM TR_TRANS_MULTIBILL h  " +
                "WHERE h.SVC_ID = 'PostBillConfirm' and h.STATE = 0 "+
                "and (created >= TO_CHAR(SYSDATE - 28, 'DD-MON-YYYY') and created <= TO_CHAR(SYSDATE-21, 'DD-MON-YYYY'))");

        resultSet1t.next();
        resultSet2t.next();
        resultSet3t.next();
        resultSet4t.next();
        resultSet1b.next();
        resultSet2b.next();
        resultSet3b.next();
        resultSet4b.next();

        week1t = resultSet1t.getDouble("COUNT");
        week2t = resultSet2t.getDouble("COUNT");
        week3t = resultSet3t.getDouble("COUNT");
        week4t = resultSet4t.getDouble("COUNT");
        week1b = resultSet1b.getDouble("COUNT");
        week2b = resultSet2b.getDouble("COUNT");
        week3b = resultSet3b.getDouble("COUNT");
        week4b = resultSet4b.getDouble("COUNT");


        String bill = "[[\"Start\", 0],"+"[\"Week1\","+ Double.toString(week1b)+"],[\"Week2\","+Double.toString(week2b)+"],[\"Week3\","+Double.toString(week3b)+"],[\"Week4\","+Double.toString(week4b)+"]]";
        String topup = "[[\"Start\", 0],"+"[\"Week1\","+ Double.toString(week1t)+"],[\"Week2\","+Double.toString(week2t)+"],[\"Week3\","+Double.toString(week3t)+"],[\"Week4\","+Double.toString(week4t)+"]]";

        model.addAttribute("actual", Double.toString(actual));
        model.addAttribute("target", Double.toString(target));
        model.addAttribute("topup", topup);
        model.addAttribute("bill", bill);
        model.addAttribute("percent", Integer.toString((int) percent));

        resultSet1t.close();
        resultSet2t.close();
        resultSet3t.close();
        resultSet4t.close();
        resultSet1b.close();
        resultSet2b.close();
        resultSet3b.close();
        resultSet4b.close();

        state1b.close();
        state2b.close();
        state3b.close();
        state4b.close();
        state1t.close();
        state2t.close();
        state3t.close();
        state4t.close();
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
        Revenue(model);
        return "Kiosk";
    }
}