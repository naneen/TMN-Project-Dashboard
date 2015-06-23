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
@RequestMapping( "/" )
public class HelloController {
    Connection connect = null;
    String url = "jdbc:oracle:thin:@//10.224.102.10:2992/pdev";
    String username = "kioskpx";
    String pass = "kioskdev";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection connect = null;
        String url = "jdbc:oracle:thin:@//10.224.102.10:2992/pdev";
        String username = "kioskpx";
        String pass = "kioskdev";
        Class.forName("oracle.jdbc.driver.OracleDriver");
        connect = DriverManager.getConnection(url,username,pass);
        ResultSet  resultSet;
        Statement state;
        state = connect.createStatement();
        String avgOffset ="AAA";
        resultSet = state.executeQuery("select * from DT_TRM");
        resultSet.next();
        avgOffset = resultSet.getString("TRM_AMOUNT");
        System.out.println(avgOffset);
    }


    public void ConnectDB() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        connect = DriverManager.getConnection(url,username,pass);
    }

    public void Revenue(ModelMap model){
        double actual = 698;
        double target = 666;
        double percent = (actual/target)*100;

        String topup = "[[\"Jan\", 67],[\"Feb\", 91],[\"Mar\", 36],[\"Apr\", 190],[\"May\", 28],[\"Jun\", 123],[\"Jul\", 38]]";
        String bill = "[[\"Jan\", 94],[\"Feb\", 49],[\"Mar\", 45],[\"Apr\", 94],[\"May\", 76],[\"Jun\", 22],[\"Jul\", 31]]";

        model.addAttribute("actual", Double.toString(actual));
        model.addAttribute("target", Double.toString(target));
        model.addAttribute("topup", topup);
        model.addAttribute("bill", bill);
        model.addAttribute("percent",Integer.toString((int)percent));
    }

    @RequestMapping(value = "/QueryTop4", method = RequestMethod.GET)
    public @ResponseBody
    String QueryTop4() throws SQLException {
        StringBuilder resultTop4 = new StringBuilder();
        HashMap<String,Integer> map = new HashMap<String,Integer>();
        ValueComparator bvc =  new ValueComparator(map);
        TreeMap<String,Integer> sorted_map = new TreeMap<String,Integer>(bvc);
        ResultSet resultSet1,resultSet2;
        Statement state1,state2;
        state1 = connect.createStatement();
        state2 = connect.createStatement();
        resultSet1 = state1.executeQuery("SELECT t4.PLACE,count(*) as COUNT " +
                "FROM TR_PAY_DETAIL_MULTIBILL t1,TR_PAY_MULTIBILL t2,RE_LOCATION_KIOSK t3,DT_LOCATION t4 " +
                "WHERE t1.trans_id IN ( " +
                "SELECT DISTINCT(TRANS_ID) " +
                "FROM TR_TRANS_MULTIBILL " +
                "WHERE SVC_ID = 'PostBillConfirm' AND state=0 and TO_CHAR (SYSDATE-45,'DD-MON-YYYY') = TO_CHAR (ENDED,'DD-MON-YYYY') ) " +
                "AND t1.TRANS_ID = t2.TRANS_ID AND t2.KIOSK_ID = t3.KIOSK_ID AND t3.LOCATION_ID = t4.LOCATION_ID " +
                "GROUP BY t4.PLACE ");

        resultSet2 = state2.executeQuery("select t2.PLACE,t1.TRM_AMOUNT as COUNT " +
                "from DT_TRM t1,DT_LOCATION t2 " +
                "where TO_CHAR (SYSDATE-45,'DD-MON-YYYY') = TO_CHAR (t1.TRM_DATE,'DD-MON-YYYY') and t1.LOCATION_ID = t2.LOCATION_ID");

        while(resultSet2.next()){
            String place = resultSet2.getString("PLACE");
            int count = resultSet2.getInt("COUNT");
            map.put(place,count);
        }
        while (resultSet1.next()){
            String place = resultSet1.getString("PLACE");
            int count = resultSet1.getInt("COUNT");
            if(map.containsKey(place)){
                int z = map.get(place);
                map.put(place, (int) Math.round((double)z/(z+count)*100));
            }
            else{
                map.put(place,0);
            }
        }
        sorted_map.putAll(map);
        Iterator<String> Vmap = sorted_map.keySet().iterator();
        for(int i = 1;i<=4;i++) {
            if(Vmap.hasNext()) {
                String key = Vmap.next();
                Integer val = map.get(key);
                resultTop4.append("<div id=\"box" + i + "\" class=\"boxall\"><div id=\"boxa"+i+"\" class=\"boxa\"><div class=\"displayed\"><center>" + key + "<br>" + val + "%</center></div></div><img src=\"resources/img/" + i + ".png\" width=70% class=\"displayed\"/></div>");
            }
            else{
                resultTop4.append("<div id=\"box" + i + "\" class=\"boxall\"><div id=\"boxa"+i+"\" class=\"boxa\"><center><br> </center></div><img src=\"resources/img/" + i + ".png\" width=70% class=\"displayed\"/></div>");
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
    String QueryPieChart() throws SQLException {
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
                "where SVC_ID = 'PostBillConfirm' and state=0 and TO_CHAR (SYSDATE-45,'DD-MON-YYYY') = TO_CHAR (ENDED,'DD-MON-YYYY')) " +
                "and t1.TRANS_ID = t2.TRANS_ID and t2.KIOSK_ID = t3.KIOSK_ID");
        resultSet2 = state2.executeQuery("select SUM(TRM_AMOUNT) as COUNT " +
                "from DT_TRM " +
                "where TO_CHAR (SYSDATE-45,'DD-MON-YYYY') = TO_CHAR (TRM_DATE,'DD-MON-YYYY')");
        resultSet1.next();
        resultSet2.next();
        int count1 = resultSet1.getInt("COUNT");
        int count2 = resultSet2.getInt("COUNT");
        avgOffset = (int) Math.round((double)count2/(count1+count2)*100);
        resultSet1.close();
        resultSet2.close();
        state1.close();
        state2.close();
        return avgOffset+"";
    }

    @RequestMapping(value = "/TimeSet", method = RequestMethod.GET)
    public @ResponseBody
    String TimeSet(){
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

    @RequestMapping(value = "/",method = RequestMethod.GET )
    public String MainCon(ModelMap model) throws SQLException, ClassNotFoundException {
        ConnectDB();
        Revenue(model);
        return "hello";
    }
}