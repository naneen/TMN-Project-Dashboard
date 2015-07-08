package com.springapp.mvc.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import java.sql.SQLException;
import java.text.AttributedString;
import java.util.Calendar;

/**
 * Created by admin on 8/7/2558.
 */
@Component
public class QueryBarGraph {
    @Autowired
    private QueryCountTransaction queryCountTransaction;
    @Autowired
    private  QueryAmount queryAmount;

    public void tranBar(ModelMap model) throws SQLException {
        String month_name = null;
        switch(Calendar.getInstance().get(Calendar.MONTH)){
            case 0: month_name = "January"; break;
            case 1: month_name = "February"; break;
            case 2: month_name = "March"; break;
            case 3: month_name = "April"; break;
            case 4: month_name = "May"; break;
            case 5: month_name = "June"; break;
            case 6: month_name = "July"; break;
            case 7: month_name = "August"; break;
            case 8: month_name = "September"; break;
            case 9: month_name = "October"; break;
            case 10: month_name = "November"; break;
            case 11: month_name = "December"; break;
        }


        model.addAttribute("month", month_name);
        model.addAttribute("mobileAppTran", queryCountTransaction.getCountMobileApp());
        model.addAttribute("kioskTran", queryCountTransaction.getCountKiosk());
        model.addAttribute("tmxTran", queryCountTransaction.getCountTmx());
        model.addAttribute("paymentGatewayTran", queryCountTransaction.getCountPaymentGate());
        model.addAttribute("TopUpMobileTran", queryCountTransaction.getCountTopupMobile());
        model.addAttribute("TopUpGameTran", queryCountTransaction.getCountTopupGame());
        model.addAttribute("masterCardTran", queryCountTransaction.getCountMasterCard());
        model.addAttribute("billPayTran", queryCountTransaction.getCountBillPay());


    }
    public void amountBar(ModelMap model) throws SQLException {
        model.addAttribute("mobileAppAmount", queryAmount.getAmountMobileApp()/1000);
        model.addAttribute("kioskAmount", queryAmount.getAmountKiosk()/1000);
        model.addAttribute("tmxAmount", queryAmount.getAmountTmx()/1000);
        model.addAttribute("paymentGatewayAmount", queryAmount.getAmountPaymentGate()/1000);
        model.addAttribute("TopUpMobileAmount",  queryAmount.getAmountTopupMobile()/1000);
        model.addAttribute("TopUpGameAmount", queryAmount.getAmountTopupGame()/1000);
        model.addAttribute("masterCardAmount", queryAmount.getAmountMasterCard()/1000);
        model.addAttribute("billPayAmount", queryAmount.getAmountBillPay()/1000);


    }



}
