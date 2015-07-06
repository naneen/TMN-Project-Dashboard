package com.springapp.mvc.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

@Component
public class QueryTop4 {

    @Autowired
    private ConnectDB connectKiosk;

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
        return (int) Math.round((double) count / sum * 100);
    }

    public HashMap<String, Integer> getCountByPlace(int sum) throws SQLException {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        ResultSet resultSet;
        Statement state;
        state = connectKiosk.getConnect().createStatement();
        resultSet = state.executeQuery("select t4.PLACE,count(*) as COUNT " +
                "from TR_PAY_DETAIL_MULTIBILL t1,TR_PAY_MULTIBILL t2,RE_LOCATION_KIOSK t3,DT_LOCATION t4 " +
                "where t1.TRANS_ID = t2.TRANS_ID and  (t1.trans_id,t2.KIOSK_ID) in ( " +
                "Select distinct(TRANS_ID),KIOSK_ID from TR_TRANS_MULTIBILL where SVC_ID = 'PostBillConfirm' and state=0 and TO_CHAR (SYSDATE-1,'DD-MON-YYYY') = TO_CHAR (ENDED,'DD-MON-YYYY')) " +
                "and t2.KIOSK_ID = t3.KIOSK_ID and t3.LOCATION_ID = t4.LOCATION_ID " +
                "group by t4.PLACE ");
        while(resultSet.next()){
            String place = resultSet.getString("PLACE");
            int count = resultSet.getInt("COUNT");
            place = fucPlaceCut(place);
            count = getPercent(count,sum);
            map.put(place,count);
        }
        resultSet.close();
        state.close();
        return map;
    }

    public int getSum() throws SQLException {
        ResultSet resultSet;
        Statement state;
        state = connectKiosk.getConnect().createStatement();
        resultSet = state.executeQuery("select count(*) as SUM " +
                "from TR_PAY_DETAIL_MULTIBILL t1,TR_PAY_MULTIBILL t2 " +
                "where t1.TRANS_ID = t2.TRANS_ID and  (t1.trans_id,t2.KIOSK_ID) in ( " +
                "Select distinct(TRANS_ID),KIOSK_ID from TR_TRANS_MULTIBILL where SVC_ID = 'PostBillConfirm' and state=0 and TO_CHAR (SYSDATE-1,'DD-MON-YYYY') = TO_CHAR (ENDED,'DD-MON-YYYY')) ");
        resultSet.next();
        int ans = resultSet.getInt("SUM");
        resultSet.close();
        state.close();
        return ans;
    }
}
