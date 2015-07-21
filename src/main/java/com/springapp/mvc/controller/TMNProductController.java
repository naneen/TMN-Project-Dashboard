package com.springapp.mvc.controller;

import com.springapp.mvc.model.QueryAmount;
import com.springapp.mvc.model.QueryBarGraph;
import com.springapp.mvc.model.QueryCountTransaction;
import org.json.JSONException;
import org.json.JSONObject;
import com.springapp.mvc.model.ConnectDB;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;

@Controller
public class TMNProductController {
    @Autowired
    private ConnectDB connectMobileApp,connectKiosk,connectTmx,connectPaymentGate,connectTopupMobile,connectTopupGame,connectMasterCard,connectBillPay;
    @Autowired
    private QueryCountTransaction queryCountTransaction,queryCountTransaction0;
    @Autowired
    private  QueryAmount queryAmount,queryAmount0;
    @Autowired
    private QueryBarGraph queryBarGraph;

    public int getPercentInt(int count,int sum){
        return (int) Math.round(((double) count / sum) * 100);
    }

    public Double getPercent2Decimal(double count,double sum){
        double temp =  count / sum * 10000;
        temp = Math.round(temp)/100.0;
        return temp;
    }

    public String getDateAgo(int day_ago){
        String[] monthNames = {"Jan", "Feb", "Mar", "Apr", "May", "Jun","Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        Calendar calendar = Calendar.getInstance();
        Date date = new Date();
        date.setDate(date.getDate() - day_ago);
        calendar.setTime(date);
        int Day = calendar.get(Calendar.DATE);
        String Month = monthNames[calendar.get(Calendar.MONTH)];
        return Day + " " + Month;
    }

    @RequestMapping(value = "/pieTransaction", method = RequestMethod.GET)
    public @ResponseBody
    String pieTransaction() throws JSONException, SQLException {
        JSONArray userArray = new JSONArray();
        JSONObject userJSON;
        String[] trueMoneyProduct = {"Mobile Application", "Kiosk", "TMX", "Payment Gateway", "Topup Mobile","Topup Game","Master Card","Bill pay"};
        queryCountTransaction0.setDay_ago(0);
        int[] countTransaction = {
                queryCountTransaction0.getCountMobileApp(),queryCountTransaction0.getCountKiosk(),
                queryCountTransaction0.getCountTmx(),queryCountTransaction0.getCountPaymentGate(),
                queryCountTransaction0.getCountTopupMobile(),queryCountTransaction0.getCountTopupGame(),
                queryCountTransaction0.getCountMasterCard(),queryCountTransaction0.getCountBillPay()
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

    @RequestMapping(value = "/tranTMNProduct", method = RequestMethod.GET)
    public @ResponseBody
    String tranAllProduct() throws JSONException, SQLException {
        JSONArray tranJson=new JSONArray();
        JSONObject jsonObject=new JSONObject();
        JSONArray jsontranArray=new JSONArray();
        JSONArray jsonamountArray=new JSONArray();
        String[] trueMoneyProduct = {"Mobile Application", "Kiosk", "TMX", "Payment Gateway", "Topup Mobile","Topup Game","Master Card","Bill pay","Total"};
        DecimalFormat changeFormat = new DecimalFormat("#,##0.00");
        DecimalFormat changeFormatTran = new DecimalFormat("#,##0");
        queryCountTransaction0.setDay_ago(0);
        queryAmount0.setDay_ago(0);
        int[] countTran = {
                queryCountTransaction0.getCountMobileApp(),queryCountTransaction0.getCountKiosk(),
                queryCountTransaction0.getCountTmx(),queryCountTransaction0.getCountPaymentGate(),
                queryCountTransaction0.getCountTopupMobile(),queryCountTransaction0.getCountTopupGame(),
                queryCountTransaction0.getCountMasterCard(),queryCountTransaction0.getCountBillPay()
        };

        Double[] amount = {
                queryAmount0.getAmountMobileApp(),queryAmount0.getAmountKiosk(),
                queryAmount0.getAmountTmx(),queryAmount0.getAmountPaymentGate(),
                queryAmount0.getAmountTopupMobile(),queryAmount0.getAmountTopupGame(),
                queryAmount0.getAmountMasterCard(),queryAmount0.getAmountBillPay()
        };

        int sumTran = 0;
        Double sumAmount = 0.0;

        for (String aTrueMoneyProduct : trueMoneyProduct) {
            tranJson.put(aTrueMoneyProduct);
        }

        jsonObject.put("productName",tranJson);

        for(int valueComponent : countTran){
            jsontranArray.put(changeFormatTran.format(valueComponent));
            sumTran += valueComponent;
        }
        jsonObject.put("tran",jsontranArray);
        jsonObject.put("totalTran",changeFormatTran.format(sumTran));

        for(Double valueComponent : amount){
            jsonamountArray.put(changeFormat.format(valueComponent));
            sumAmount += valueComponent;
        }

        jsonObject.put("amount",jsonamountArray);
        jsonObject.put("totalAmount",changeFormat.format(sumAmount));

        return jsonObject.toString();
    }

    @RequestMapping(value = "/bubbleGraph", method = RequestMethod.GET)
    public @ResponseBody
    String bubbleGraph() throws JSONException, SQLException {
        JSONArray jsonxAXIS = new JSONArray();
        JSONObject jsonObject=new JSONObject();
        JSONArray jsonArrayMoblieApp,jsonArrayKiosk,jsonArrayTmx,jsonArrayPaymentGate,jsonArrayTopupMobile,jsonArrayTopupGame,jsonArrayMasterCard,jsonArrayBillPay;
        jsonArrayMoblieApp = new JSONArray();
        jsonArrayKiosk = new JSONArray();
        jsonArrayTmx = new JSONArray();
        jsonArrayPaymentGate = new JSONArray();
        jsonArrayTopupMobile = new JSONArray();
        jsonArrayTopupGame = new JSONArray();
        jsonArrayMasterCard = new JSONArray();
        jsonArrayBillPay = new JSONArray();

        for(int i = 4;i>=0;i--){
            String day = getDateAgo(1+7*i);
            queryCountTransaction.setDay_ago(7*i);
            queryAmount.setDay_ago(7*i);
            int[] countTran = {
                    queryCountTransaction.getCountMobileApp(),queryCountTransaction.getCountKiosk(),
                    queryCountTransaction.getCountTmx(),queryCountTransaction.getCountPaymentGate(),
                    queryCountTransaction.getCountTopupMobile(),queryCountTransaction.getCountTopupGame(),
                    queryCountTransaction.getCountMasterCard(),queryCountTransaction.getCountBillPay()
            };

            Double[] amount = {
                    queryAmount.getAmountMobileApp(),queryAmount.getAmountKiosk(),
                    queryAmount.getAmountTmx(),queryAmount.getAmountPaymentGate(),
                    queryAmount.getAmountTopupMobile(),queryAmount.getAmountTopupGame(),
                    queryAmount.getAmountMasterCard(),queryAmount.getAmountBillPay()
            };
            jsonArrayMoblieApp.put(new int[]{(int) Math.round(amount[0]),countTran[0]});
            jsonArrayKiosk.put(new int[]{(int)Math.round(amount[1]),countTran[1]});
            jsonArrayTmx.put(new int[]{(int) Math.round(amount[2]),countTran[2]});
            jsonArrayPaymentGate.put(new int[]{(int)Math.round(amount[3]),countTran[3]});
            jsonArrayTopupMobile.put(new int[]{(int) Math.round(amount[4]),countTran[4]});
            jsonArrayTopupGame.put(new int[]{(int)Math.round(amount[5]),countTran[5]});
            jsonArrayMasterCard.put(new int[]{(int)Math.round(amount[6]),countTran[6]});
            jsonArrayBillPay.put(new int[]{(int)Math.round(amount[7]),countTran[7]});
            jsonxAXIS.put(day);
        }
        jsonObject.put("MobileApp",jsonArrayMoblieApp);
        jsonObject.put("Kiosk",jsonArrayKiosk);
        jsonObject.put("Tmx",jsonArrayTmx);
        jsonObject.put("PaymentGate",jsonArrayPaymentGate);
        jsonObject.put("TopupMobile",jsonArrayTopupMobile);
        jsonObject.put("TopupGame",jsonArrayTopupGame);
        jsonObject.put("MasterCard",jsonArrayMasterCard);
        jsonObject.put("BillPay",jsonArrayBillPay);
        jsonObject.put("xAXIS",jsonxAXIS);

        return jsonObject.toString();
    }

    @RequestMapping(value = "/barGraph", method = RequestMethod.GET)
    public @ResponseBody
    String barGraph() throws SQLException, JSONException {
        JSONObject jsonObject=new JSONObject();
        JSONArray jsonArrayTran = new JSONArray();
        JSONArray jsonArrayAmount = new JSONArray();
        String[] trueMoneyProduct = {"Mobile Application", "Kiosk", "TMX", "Payment Gateway", "Topup Mobile","Topup Game","Master Card","Bill pay"};
        String month_name = null;
        switch(Calendar.getInstance().get(Calendar.MONTH)){
            case 0: month_name = "January"; break;
            case 1: month_name = "February"; break;
            case 2: month_name = "March"; break;
            case 3: month_name = "April"; break;
            case 4: month_name = "May"; break;
            case 5: month_name = "June"; break;
            case 6: month_name = "July"; break;
            case 7:
                month_name = "August"; break;
            case 8: month_name = "September"; break;
            case 9: month_name = "October"; break;
            case 10: month_name = "November"; break;
            case 11: month_name = "December"; break;
        }
        queryCountTransaction0.setDay_ago(0);
        queryAmount0.setDay_ago(0);
        int[] countTran = {
                queryCountTransaction0.getCountMobileApp(),queryCountTransaction0.getCountKiosk(),
                queryCountTransaction0.getCountTmx(),queryCountTransaction0.getCountPaymentGate(),
                queryCountTransaction0.getCountTopupMobile(),queryCountTransaction0.getCountTopupGame(),
                queryCountTransaction0.getCountMasterCard(),queryCountTransaction0.getCountBillPay()
        };

        Double[] amount = {
                queryAmount0.getAmountMobileApp(),queryAmount0.getAmountKiosk(),
                queryAmount0.getAmountTmx(),queryAmount0.getAmountPaymentGate(),
                queryAmount0.getAmountTopupMobile(),queryAmount0.getAmountTopupGame(),
                queryAmount0.getAmountMasterCard(),queryAmount0.getAmountBillPay()
        };
        for(int i = 0;i<trueMoneyProduct.length;i++){
            jsonArrayTran.put(countTran[i]);
            jsonArrayAmount.put(amount[i]);
        }
        jsonObject.put("month",month_name);
        jsonObject.put("product",trueMoneyProduct);
        jsonObject.put("countTran",jsonArrayTran);
        jsonObject.put("amount",jsonArrayAmount);
        return jsonObject.toString();
    }

    @RequestMapping(value = "/",method = RequestMethod.GET )
    public String product(ModelMap model) throws SQLException, ClassNotFoundException {
        connectMobileApp.setConnect("ewreport", "9wb77b");
        connectKiosk.setConnect("kioskpx", "kioskdev");
        connectTmx.setConnect("tmx", "tmx#4672");
        connectPaymentGate.setConnect("payment", "PAY#MENT12");
        connectTopupMobile.setConnect("cpgreport", "rpt1#cpg");
        connectTopupGame.setConnect("gamereport", "RPT#4Game");
        connectMasterCard.setConnect("prepaidcard", "PRE#PAID99");
        connectBillPay.setConnect("bpay", "bpay#123$");
        queryBarGraph.tranBar(model);
        queryBarGraph.amountBar(model);
        return "TMNProduct";
    }
}
