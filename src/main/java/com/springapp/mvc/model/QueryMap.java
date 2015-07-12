package com.springapp.mvc.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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

        ResultSet  resultSet;
        Statement state;
        state = connectKiosk.getConnect().createStatement();

        resultSet = state.executeQuery("select DT_LOCATION.PLACE,DT_LOCATION.LON,DT_LOCATION.LAT,DT_LOCATION.LOCATION_ID,COUNT(*) as COUNT"+
                " FROM (DT_LOCATION INNER JOIN RE_LOCATION_KIOSK ON DT_LOCATION.LOCATION_ID = RE_LOCATION_KIOSK.LOCATION_ID)" +
                "INNER JOIN DT_TICKETS"+
                " ON RE_LOCATION_KIOSK.KIOSK_ID = DT_TICKETS.KIOSK_ID"+
                " WHERE DT_LOCATION.AREA_BANGKOK='yes' AND DT_TICKETS.DATE_OF_ARRIVAL >= to_date('01/01/2015','DD-MM-YYYY')"+
                " GROUP BY DT_LOCATION.PLACE,DT_LOCATION.LON,DT_LOCATION.LAT,DT_LOCATION.LOCATION_ID ORDER BY COUNT(*) DESC");

        while(resultSet.next()) {
            arrayJSON.put(resultSet.getString("PLACE"));
            arrayJSON.put(resultSet.getDouble("LON"));
            arrayJSON.put(resultSet.getDouble("LAT"));
            arrayJSON.put(resultSet.getInt("LOCATION_ID"));
            arrayJSON.put(resultSet.getInt("COUNT"));
        }
        resultSet.close();
        state.close();
        return arrayJSON.toString();
    }
}




