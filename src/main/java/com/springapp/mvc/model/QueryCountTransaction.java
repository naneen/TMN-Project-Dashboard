package com.springapp.mvc.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class QueryCountTransaction {

    @Autowired
    private ConnectDB connectMobileApp,connectKiosk,connectTmx,connectPaymentGate,connectTopupMobile,connectTopupGame,connectMasterCard,connectBillPay;

    public int getCountMobileApp() throws SQLException {
        ResultSet resultSet;
        Statement state;
        state = connectMobileApp.getConnect().createStatement();
        String query = "select sum(COUNT) as COUNT from " +
                "((select to_char(create_ts,'yyyymmdd') trans_date ,channel ,type_desc ,debtor_ref,debtor_name,sourceoffund,count(*) as COUNT,sum(amount),count(distinct(initiator_ref)) from SUMMARY_TRANS " +
                "where trans_state = '2'and type_desc in ('banksource','bankcashin') " +
                "and channel = 'IOS_APP' " +
                "and amount > 0 " +
                "and create_ts between TO_DATE('20150101','yyyymmdd') and TO_DATE(sysdate) " +
                "group by to_char(create_ts,'yyyymmdd'),channel,type_desc,debtor_ref,debtor_name,sourceoffund ) " +
                "union " +
                "(select to_char(create_ts,'yyyymmdd') trans_date,channel , type_desc ,'debtor_ref','debtor_name',sourceoffund,count(*)  as COUNT,sum(amount),count(distinct(initiator_ref)) " +
                "from SUMMARY_TRANS " +
                "where trans_state = '2' " +
                "and type_desc in ('transfer') " +
                "and channel = 'IOS_APP' " +
                "and amount > 0 " +
                "and create_ts between TO_DATE('20150101','yyyymmdd') and TO_DATE(sysdate) " +
                "group by to_char(create_ts,'yyyymmdd'),channel,type_desc,sourceoffund) " +
                "union " +
                "(select to_char(create_ts,'yyyymmdd') trans_date ,channel, type_desc , creditor_ref,creditor_name,sourceoffund,count(*)  as COUNT,sum(amount),count(distinct(initiator_ref)) " +
                "from SUMMARY_TRANS " +
                "where trans_state = '2' " +
                "and type_desc in ('bankcashout','billcollect','billpay','buy','sell') " +
                "and channel = 'IOS_APP' " +
                "and amount > 0 " +
                "and create_ts between TO_DATE('20150101','yyyymmdd') and TO_DATE(sysdate) " +
                "group by to_char(create_ts,'yyyymmdd'),channel,type_desc,creditor_ref,creditor_name,channel,sourceoffund ) " +
                "union " +
                "(select to_char(create_ts,'yyyymmdd') trans_date ,channel ,type_desc ,debtor_ref,debtor_name,sourceoffund,count(*) as COUNT,sum(amount),count(distinct(initiator_ref)) from SUMMARY_TRANS " +
                "where trans_state = '2' " +
                "and type_desc in ('banksource','bankcashin') " +
                "and channel = 'ANDROID' " +
                "and amount > 0 " +
                "and create_ts between TO_DATE('20150101','yyyymmdd') and TO_DATE(sysdate) " +
                "group by to_char(create_ts,'yyyymmdd'),channel,type_desc,debtor_ref,debtor_name,sourceoffund ) " +
                "union " +
                "(select to_char(create_ts,'yyyymmdd') trans_date,channel , type_desc ,'debtor_ref','debtor_name',sourceoffund,count(*) as COUNT,sum(amount),count(distinct(initiator_ref)) " +
                "from SUMMARY_TRANS " +
                "where trans_state = '2' " +
                "and type_desc in ('transfer') " +
                "and channel = 'ANDROID' " +
                "and amount > 0 " +
                "and create_ts between TO_DATE('20150101','yyyymmdd') and TO_DATE(sysdate) " +
                "group by to_char(create_ts,'yyyymmdd'),channel,type_desc,sourceoffund) " +
                "union " +
                "(select to_char(create_ts,'yyyymmdd') trans_date ,channel, type_desc , creditor_ref,creditor_name,sourceoffund,count(*) as COUNT,sum(amount),count(distinct(initiator_ref)) " +
                "from SUMMARY_TRANS " +
                "where trans_state = '2' " +
                "and type_desc in ('bankcashout','billcollect','billpay','buy','sell') " +
                "and channel = 'ANDROID' " +
                "and amount > 0 " +
                "and create_ts between TO_DATE('20150101','yyyymmdd') and TO_DATE(sysdate) " +
                "group by to_char(create_ts,'yyyymmdd'),channel,type_desc,creditor_ref,creditor_name,channel,sourceoffund )) ";
        resultSet = state.executeQuery(query);
        resultSet.next();
        int ans = resultSet.getInt("COUNT");
        resultSet.close();
        state.close();
        return ans;
    }

    public int getCountKiosk() throws SQLException {
        ResultSet resultSet;
        Statement state;
        state = connectKiosk.getConnect().createStatement();
        String query = "select COUNT(TRANS_ID) as COUNT from " +
                "(select DISTINCT(P.trans_id) from kioskpx.TR_PAY_MULTIBILL P " +
                "where P.CREATED BETWEEN to_date('01/01/2015 00:00:00', 'dd/MM/yyyy hh24:mi:ss') and to_date(SYSDATE, 'dd/MM/yyyy hh24:mi:ss')) ";
        resultSet = state.executeQuery(query);
        resultSet.next();
        int ans = resultSet.getInt("COUNT");
        resultSet.close();
        state.close();
        return ans;
    }

    public int getCountTmx() throws SQLException {
        ResultSet resultSet;
        Statement state;
        state = connectTmx.getConnect().createStatement();
        String query = "select COUNT(*) as COUNT from " +
                "(select to_char(a.trans_date,'yyyymmdd') transac_date ,to_char(a.trans_date,'hh24:mi:ss') transac_time, a.trans_command service, a.service_code service_name,a.trans_tmxcode2 tmxcode,a.trans_tmxname2 tmx_name,b.addr_district,b.addr_province, a.trans_amount,a.trans_commission_amount trans_com_amt " +
                "from tmx_transaction a,tmx.tmx_agent b " +
                "where a.agent_id=b.agent_id(+) " +
                "and trans_date BETWEEN to_date('01/01/2015 00:00:00', 'dd/MM/yyyy hh24:mi:ss') and to_date(SYSDATE, 'dd/MM/yyyy hh24:mi:ss') " +
                "and response_code='0' " +
                "and service_code not in ('BANKA/C','TMN_TRANSFER') " +
                "and trans_cancel_status is null " +
                "order by trans_date) ";
        resultSet = state.executeQuery(query);
        resultSet.next();
        int ans = resultSet.getInt("COUNT");
        resultSet.close();
        state.close();
        return ans;
    }

    public int getCountPaymentGate() throws SQLException {
        ResultSet resultSet;
        Statement state;
        state = connectPaymentGate.getConnect().createStatement();
        String query = "select sum(COUNT) as COUNT from " +
                "(select to_char(created_date,'yyyymmdd'),app_name,app_id ,trans_state ,count(*) as COUNT,SUM(AMOUNT) " +
                "from payment.trans " +
                "where created_date between to_date('01/01/2015 00:00:00', 'dd/MM/yyyy hh24:mi:ss') and to_date(SYSDATE, 'dd/MM/yyyy hh24:mi:ss') " +
                "group by to_char(created_date,'yyyymmdd'),app_name,app_id,trans_state order by to_char(created_date,'yyyymmdd'),app_name,app_id,trans_state)";
        resultSet = state.executeQuery(query);
        resultSet.next();
        int ans = resultSet.getInt("COUNT");
        resultSet.close();
        state.close();
        return ans;
    }

    public int getCountTopupMobile() throws SQLException {
        ResultSet resultSet;
        Statement state;
        state = connectTopupMobile.getConnect().createStatement();
        String query = "select SUM(cnt) as COUNT " +
                "from(SELECT to_char(a.paydate,'yyyymmdd')pay_date, a.merchant_id,a.merchant_name,a.channel, a.sof ,a.ref2,(a.totalamtvat/100) as price, count(*) cnt , sum(a.totalamtvat/100) as sum_amt " +
                "FROM cpgreport.cpg711_trans a " +
                "where a.resp_code = '0' and a.ref2 like '%topup%' and a.ref2 not like '%cc' " +
                "and a.merchant_id = '800000641' " +
                "and a.paydate between to_date('01/01/2015 00:00:00', 'dd/MM/yyyy hh24:mi:ss') and to_date(SYSDATE,'dd/MM/yyyy hh24:mi:ss') " +
                "group by to_char(a.paydate,'yyyymmdd'), a.merchant_id,a.merchant_name,a.channel, a.sof,a.ref2,(a.totalamtvat/100) " +
                "order by to_char(a.paydate,'yyyymmdd'), a.merchant_id,a.merchant_name,a.channel, a.sof,a.ref2,(a.totalamtvat/100)) ";
        resultSet = state.executeQuery(query);
        resultSet.next();
        int ans = resultSet.getInt("COUNT");
        resultSet.close();
        state.close();
        return ans;
    }

    public int getCountTopupGame() throws SQLException {
        ResultSet resultSet;
        Statement state;
        state = connectTopupGame.getConnect().createStatement();
        String query = "select sum(COUNT) as COUNT from " +
                "(SELECT to_char(a.paydate,'yyyymm') pay_date,a.merchant_id, a.merchant_name,sof ,(a.totalamtvat)/100 price,count(9) as COUNT,sum(a.totalamtvat)/100 sumamt_baht " +
                "FROM gamereport.cpggame_trans a " +
                "where a.transstatus like '%Success%' " +
                "and a.paydate between to_date('01/01/2015 00:00:00', 'dd/MM/yyyy hh24:mi:ss') and to_date(SYSDATE, 'dd/MM/yyyy hh24:mi:ss') " +
                "group by to_char(a.paydate,'yyyymm'),a.merchant_id, a.merchant_name ,sof,(a.totalamtvat)/100 order by to_char(a.paydate,'yyyymm'),a.merchant_id, a.merchant_name ,sof,(a.totalamtvat)/100)";
        resultSet = state.executeQuery(query);
        resultSet.next();
        int ans = resultSet.getInt("COUNT");
        resultSet.close();
        state.close();
        return ans;
    }

    public int getCountMasterCard() throws SQLException {
        ResultSet resultSet;
        Statement state;
        state = connectMasterCard.getConnect().createStatement();
        String query = "select COUNT(*) as COUNT from " +
                "(select * " +
                "from PAYMENT_TRANSACTION P " +
                "where P.CREATED_DATETIME BETWEEN to_date('01/01/2015 00:00:00', 'dd/MM/yyyy hh24:mi:ss') and to_date(SYSDATE, 'dd/MM/yyyy hh24:mi:ss') " +
                "order by CREATED_DATETIME desc) ";
        resultSet = state.executeQuery(query);
        resultSet.next();
        int ans = resultSet.getInt("COUNT");
        resultSet.close();
        state.close();
        return ans;
    }

    public int getCountBillPay() throws SQLException {
        ResultSet resultSet;
        Statement state;
        state = connectBillPay.getConnect().createStatement();
        String query = "select Count(payment_trans_id) as COUNT from " +
                "(select to_char(payment_date,'yyyymmdd') AS PAYDATE ,payment_trans_id,partner_id,channel,pay_amout,customer_fee,biller_fee,tmn_revernue,partner_revernue,sof,biller_code,REF1,REF2, " +
                "PARTNER_NAME,PAYMENT_TRANSACTION_STATUS AS PAYMAENT_STS " +
                "from bpay.payment_transactions " +
                "where payment_date between to_date('01/01/2015 00:00:00', 'dd/MM/yyyy hh24:mi:ss') and to_date(SYSDATE, 'dd/MM/yyyy hh24:mi:ss')) ";
        resultSet = state.executeQuery(query);
        resultSet.next();
        int ans = resultSet.getInt("COUNT");
        resultSet.close();
        state.close();
        return ans;
    }
}
