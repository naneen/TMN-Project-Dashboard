package com.springapp.mvc.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by admin on 6/7/2558.
 */
@Component
public class QueryMap {

    @Autowired
    private ConnectDB connectKiosk;

    public String queryLocation() throws SQLException,JSONException {
        JSONArray arrayJSON=new JSONArray();

        ResultSet  resultSet1;
        Statement state1;
        state1 = connectKiosk.getConnect().createStatement();

        resultSet1 = state1.executeQuery("select DT_LOCATION.PLACE,DT_LOCATION.LON,DT_LOCATION.LAT,DT_LOCATION.LOCATION_ID,COUNT(*) as COUNT"+
                " FROM (DT_LOCATION INNER JOIN RE_LOCATION_KIOSK ON DT_LOCATION.LOCATION_ID = RE_LOCATION_KIOSK.LOCATION_ID)" +
                "INNER JOIN DT_TICKETS"+
                " ON RE_LOCATION_KIOSK.KIOSK_ID = DT_TICKETS.KIOSK_ID"+
                " WHERE DT_LOCATION.AREA_BANGKOK='yes' AND DT_TICKETS.DATE_OF_ARRIVAL >= to_date('01/01/2015','DD-MM-YYYY')"+
                " GROUP BY DT_LOCATION.PLACE,DT_LOCATION.LON,DT_LOCATION.LAT,DT_LOCATION.LOCATION_ID ORDER BY COUNT(*) DESC");



        while(resultSet1.next()) {
            arrayJSON.put(resultSet1.getString("PLACE"));
            arrayJSON.put(resultSet1.getDouble("LON"));
            arrayJSON.put(resultSet1.getDouble("LAT"));
            arrayJSON.put(resultSet1.getInt("LOCATION_ID"));
            arrayJSON.put(resultSet1.getInt("COUNT")
            );
        }
        resultSet1.close();
        state1.close();
        return arrayJSON.toString();
    }









}




