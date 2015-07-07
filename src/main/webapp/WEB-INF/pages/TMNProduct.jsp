<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TMN Product</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
    <link href='${pageContext.request.contextPath}/resources/css/Bubble.css' rel="stylesheet">
    <link href='${pageContext.request.contextPath}/resources/css/ProductTable.css' rel="stylesheet">

    <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.min.js"></script>
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.10.1.min.js"></script>

    <script src="${pageContext.request.contextPath}/resources/js/jquery-1.10.2.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>



    <script>
        $.getJSON("tranTMNProduct", function(json) {

            $("#MobileApp").html(json.productName[0]);
            $("#Kiosk").html(json.productName[1]);
            $("#TMX").html(json.productName[2]);
            $("#Payment").html(json.productName[3]);
            $("#TopupMobile").html(json.productName[4]);
            $("#TopupGame").html(json.productName[5]);
            $("#WeCard").html(json.productName[6]);
            $("#BillPay").html(json.productName[7]);
            $("#Total").html(json.productName[8]);

            $("#tranMobileApp").html(json.tran[0]);
            $("#tranKiosk").html(json.tran[1]);
            $("#tranTMX").html(json.tran[2]);
            $("#tranPayment").html(json.tran[3]);
            $("#tranTopupMobile").html(json.tran[4]);
            $("#tranTopupGame").html(json.tran[5]);
            $("#tranWeCard").html(json.tran[6]);
            $("#tranBillPay").html(json.tran[7]);
            $("#tranTotal").html(json.totalTran);

        $("#amountMobileApp").html(json.amount[0]);
        $("#amountKiosk").html(json.amount[1]);
        $("#amountTMX").html(json.amount[2]);
        $("#amountPayment").html(json.amount[3]);
        $("#amountTopupMobile").html(json.amount[4]);
        $("#amountTopupGame").html(json.amount[5]);
        $("#amountWeCard").html(json.amount[6]);
        $("#amountBillPay").html(json.amount[7]);
        $("#amountTotal").html(json.totalAmount);




        });
    </script>

    <%--<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>--%>
    <link href='${pageContext.request.contextPath}/resources/css/PieTransaction.css' rel="stylesheet">
    <script src='${pageContext.request.contextPath}/resources/js/highcharts.js'></script>
    <script src='${pageContext.request.contextPath}/resources/js/exporting.js'></script>
    <script src="${pageContext.request.contextPath}/resources/js/PieTransaction.js"></script>


    <script>
        function TimeReal() {
            $.ajax({
                url : "TimeReal" , success : function(data) {
                    if(data == "00:00:00"){
                        pieTransection();
                    }
                }
            });
        }
        setInterval(TimeReal,1000);

        window.onload = function () {
            pieTransection();
            TimeReal();
        };
    </script>


</head>
<body>

<a href="/DashBoard/kiosk" id="headNav">${msg}</a>
<div id="div1Pie">

    <div id="divallPie">
        <div id="textdivPie"><b>Pie Graph by TMN Product</b></div>

    </div>

<div id="chart" class="pie pie1"></div>

</div>





<div id="div4PT">

    <div id="divallPT">
        <div id="textdivPT"><b>Total by TMN Product</b></div>

    </div>

    <table class="table divtable">
        <thead bgcolor="#000000" style="color: #fff">
        <tr>
            <th>Product Name</th>
            <th>Transaction</th>
            <th>Amount</th>
        </tr>
        </thead>
        <tbody>

        <tr>
            <td><div id="MobileApp"></div></td>
            <td><div id="tranMobileApp"></div></td>
            <td><div id="amountMobileApp"></div></td>

        </tr>
        <tr>
            <td><div id="Kiosk"></div></td>
            <td><div id="tranKiosk"></div></td>
            <td><div id="amountKiosk"></div></td>

        </tr>

        <tr>
            <td><div id="TMX"></div></td>
            <td><div id="tranTMX"></div></td>
            <td><div id="amountTMX"></div></td>

        </tr>

        <tr>
            <td><div id="Payment"></div></td>
            <td><div id="tranPayment"></div></td>
            <td><div id="amountPayment"></div></td>

        </tr>

        <tr>
            <td><div id="TopupMobile"></div></td>
            <td><div id="tranTopupMobile"></div></td>
            <td><div id="amountTopupMobile"></div></td>

        </tr>

        <tr>
            <td><div id="TopupGame"></div></td>
            <td><div id="tranTopupGame"></div></td>
            <td><div id="amountTopupGame"></div></td>

        </tr>

        <tr>
            <td><div id="WeCard"></div></td>
            <td><div id="tranWeCard"></div></td>
            <td><div id="amountWeCard"></div></td>

        </tr>

        <tr>
            <td><div id="BillPay"></div></td>
            <td><div id="tranBillPay"></div></td>
            <td><div id="amountBillPay"></div></td>

        </tr>

        </tbody>
        <thead bgcolor="#BDC3C7" style="color: #000">
        <tr>
            <th><div id="Total"></div></th>
            <th><div id="tranTotal"></div></th>
            <th><div id="amountTotal"></div></th>
        </tr>
        </thead>
    </table>
</div>


</body>
</html>
