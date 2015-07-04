package com.springapp.mvc.controller;

import com.springapp.mvc.model.ConnectDB;
import com.springapp.mvc.model.QueryCountTransaction;
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
import java.util.HashMap;

@Controller
public class TMNProductController {
    @Autowired
    private ConnectDB connectMobileApp,connectKiosk,connectTmx,connectPaymentGate,connectTopupMobile,connectTopupGame,connectMasterCard,connectBillPay;
    @Autowired
    private QueryCountTransaction queryCountTransaction;

    public int getPercentInt(int count,int sum){
        return (int) Math.round(((double) count / sum) * 100);
    }

    public Double getPercent2Decimal(double count,double sum){
        double temp =  count / sum * 10000;
        temp = Math.round(temp)/100.0;
        return temp;
    }

    @RequestMapping(value = "/PieTransaction", method = RequestMethod.GET)
    public @ResponseBody
    String pieTransaction() throws JSONException, SQLException {
        JSONArray userArray = new JSONArray();
        JSONObject userJSON;
        String[] trueMoneyProduct = {"Moblie Application", "Kiosk", "TMX", "Payment Gateway", "Topup Mobile","Topup Game","Master Card","Bill pay"};
        int[] countTransaction = {
                queryCountTransaction.getCountMobileApp(),queryCountTransaction.getCountKiosk(),
                queryCountTransaction.getCountTmx(),queryCountTransaction.getCountPaymentGate(),
                100,queryCountTransaction.getCountTopupGame(),
                queryCountTransaction.getCountMasterCard(),queryCountTransaction.getCountBillPay()
        };
        int sum = 0;
        for(int valueComponent : countTransaction){
            sum += valueComponent;
        }
        for(int i = 0;i<trueMoneyProduct.length;i++){
            userJSON = new JSONObject();
            userJSON.put("product",trueMoneyProduct[i]);
            userJSON.put("percent",getPercent2Decimal(countTransaction[i], sum));
            userArray.put(userJSON);
        }
        return userArray.toString();
    }

    @RequestMapping(value = "/",method = RequestMethod.GET )
    public String product(ModelMap model) throws SQLException, ClassNotFoundException {
        connectMobileApp.setConnect("ewreport", "9wb77b");
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
