package com.springapp.mvc.controller;

import com.springapp.mvc.model.ConnectDB;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;

@Controller
public class TMNProductController {
    @Autowired
    ConnectDB connectMoblieApp,connectKiosk,connectTmx,connectPaymentGate,connectTopupMobile,connectTopupGame,connectMasterCard,connectBillPay;


    public int getPercent(int count,int sum){
        return (int) Math.round(((double) count / sum) * 100);
    }

    @RequestMapping(value = "/PieTransection", method = RequestMethod.GET)
    public @ResponseBody
    String pieTransection() throws JSONException {
        JSONArray userArray = new JSONArray();
        JSONObject userJSON;
        String[] trueMoneyProduct = {"Moblie Application", "Kiosk", "TMX", "Payment Gateway", "Topup Mobile","Topup Game","Master Card","Bill pay"};
        for(int i = 0;i<trueMoneyProduct.length;i++){
            userJSON = new JSONObject();
            userJSON.put("product",trueMoneyProduct[i]);
            userJSON.put("percent",i*5 + 10);
            userArray.put(userJSON);
        }
        return userArray.toString();
    }

    @RequestMapping(value = "/",method = RequestMethod.GET )
    public String product(ModelMap model) throws SQLException, ClassNotFoundException {
        connectMoblieApp.setConnect("ewreport", "9wb77b");
        connectKiosk.setConnect("kioskpx", "kioskdev");
        connectTmx.setConnect("tmx", "tmx#4672");
        connectPaymentGate.setConnect("payment", "PAY#MENT12");
//        connectTopupMobile.setConnect("cpgreport", "cpg#777$");
        connectTopupGame.setConnect("gamereport", "RPT#4Game");
        connectMasterCard.setConnect("prepaidcard", "PRE#PAID99");
        connectBillPay.setConnect("bpay", "bpay#123$");

        model.addAttribute("msg", "TMN Product Dashboard");
        return "TMNProduct";
    }
}
