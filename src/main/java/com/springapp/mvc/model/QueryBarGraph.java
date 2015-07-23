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
    private QueryCountTransaction queryCountTransaction0;
    @Autowired
    private  QueryAmount queryAmount0;

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
        queryCountTransaction0.setDay_ago(0);
        model.addAttribute("month", month_name);
        model.addAttribute("mobileAppTran", queryCountTransaction0.getCountMobileApp());
        model.addAttribute("kioskTran", queryCountTransaction0.getCountKiosk());
        model.addAttribute("tmxTran", queryCountTransaction0.getCountTmx());
        model.addAttribute("paymentGatewayTran", queryCountTransaction0.getCountPaymentGate());
        model.addAttribute("TopUpMobileTran", queryCountTransaction0.getCountTopupMobile());
        model.addAttribute("TopUpGameTran", queryCountTransaction0.getCountTopupGame());
        model.addAttribute("masterCardTran", queryCountTransaction0.getCountMasterCard());
        model.addAttribute("billPayTran", queryCountTransaction0.getCountBillPay());
    }

    public void amountBar(ModelMap model) throws SQLException {
        queryAmount0.setDay_ago(0);
        model.addAttribute("mobileAppAmount", queryAmount0.getAmountMobileApp()/1000);
        model.addAttribute("kioskAmount", queryAmount0.getAmountKiosk()/1000);
        model.addAttribute("tmxAmount", queryAmount0.getAmountTmx()/1000);
        model.addAttribute("paymentGatewayAmount", queryAmount0.getAmountPaymentGate()/1000);
        model.addAttribute("TopUpMobileAmount",  queryAmount0.getAmountTopupMobile()/1000);
        model.addAttribute("TopUpGameAmount", queryAmount0.getAmountTopupGame()/1000);
        model.addAttribute("masterCardAmount", queryAmount0.getAmountMasterCard()/1000);
        model.addAttribute("billPayAmount", queryAmount0.getAmountBillPay()/1000);
    }
}
