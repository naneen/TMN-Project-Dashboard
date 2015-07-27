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
    <link href='${pageContext.request.contextPath}/resources/css/TempProduct.css' rel="stylesheet">

    <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.min.js"></script>
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.10.1.min.js"></script>

    <script src="${pageContext.request.contextPath}/resources/js/jquery-1.10.2.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>

    <script>
        function getYear(){
            var date = new Date();
            var year = date.getFullYear();
            $("#year").html(year);
        }
        function addCommas(nStr) {
            nStr += '';
            x = nStr.split('.');
            x1 = x[0];
            x2 = x.length > 1 ? '.' + x[1] : '';
            var rgx = /(\d+)(\d{3})/;
            while (rgx.test(x1)) {
              x1 = x1.replace(rgx, '$1' + ',' + '$2');
           }
            return x1 + x2;
        }
    </script>
    <script>
        function tranTMNProduct() {

            $.getJSON("${pageContext.request.contextPath}/tranTMNProduct", function (json) {
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
        }
    </script>


    <%--<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>--%>
    <link href='${pageContext.request.contextPath}/resources/css/PieTransaction.css' rel="stylesheet">
    <script src='${pageContext.request.contextPath}/resources/js/highcharts.js'></script>
    <script src="${pageContext.request.contextPath}/resources/js/highcharts-more.js"></script>
    <script src='${pageContext.request.contextPath}/resources/js/exporting.js'></script>
    <%--<script src="${pageContext.request.contextPath}/resources/js/PieTransaction.js"></script>--%>

    <script type="text/javascript">
        function bubbleGraph() {
            $.getJSON("${pageContext.request.contextPath}/bubbleGraph", function(json){
                $('#container_bubble').highcharts({
                    credits: {
                        enabled: false
                    },
                    yAxis: {
                        title: {
                            text: 'Amount (Million Baht)'
                        }
                    },
                    xAxis: {
                        title:{
                            text:'Day'
                        },
                        categories: json.xAXIS
                    },
                    chart: {
                        backgroundColor: {
                            linearGradient: [0, 0, 0, 500],
                            stops: [
                                [0, 'rgb(255, 255, 255)'],
                                [2, 'rgb(200, 200, 255)']
                            ]
                        },
                        style: {"height": "99.8%", "width": "100%"},
                        type: 'bubble'

                    },
                    title: {
                        text: 'Bubble Size = Transaction Volume',
                        style: {"fontSize": "110%"}

                    },

                    legend: {
                        align: 'right',
                        verticalAlign: 'top',
                        dataLabels: false,
                        layout: 'vertical',
                        x: 0,
                        y: 25
                    },
                    tooltip: {
                        formatter: function () {
                            return this.series.name  + '<br>Transaction : ' + addCommas(this.point.z) + '<br>Amount : ' + addCommas(this.y);
                        }
                    },
                    series: [{
                        data: json.MobileApp,
                        name: 'Moblie app',
                        color: '#F64747'
                    }, {
                        data: json.Kiosk,
                        name: 'Kiosk',
                        color: '#F62459'
                    }, {
                        data: json.Tmx,
                        name: 'TMX',
                        color: '#D91E18'
                    }, {
                        data: json.PaymentGate,
                        name: 'Payment Gateway',
                        color: '#00CCFF'
                    }, {
                        data: json.TopupMobile,
                        name: 'Topup Mobile',
                        color: '#86E2D5'
                    }, {
                        data: json.TopupGame,
                        name: 'Topup Game',
                        color: '#2ECC71'
                    }, {
                        data: json.MasterCard,
                        name: 'Master Card',
                        color: '#F89406'
                    }, {
                        data: json.BillPay,
                        name: 'Bill pay',
                        color: '#D35400'
                    }],
                    exporting: {
                        buttons: [
                            {
                                enabled:false,

                                symbol: false
                            }
                        ]
                    }
                });
            });
        }

    </script>


    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/js/bootstrap-3.1.1.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/barGraph.css">

    <script src="${pageContext.request.contextPath}/resources/js/run_prettify.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jchart.js"></script>

    <script>
        function pieTransaction() {
            $.getJSON("${pageContext.request.contextPath}/pieTransaction", function(json){
                var name = 'TrueMoneyProduct';
                var browserData = [];
                var colors =  ["#38BCFF","#fdfa04","#0074AA"  ,"#fd3f04"  ,"#e9fd05","#fd5004" ,"#fdb404" ,"#68f40b" ,"#e9fd05","#47e888","#4eff63","#70ff4a","#9fe843","#efff57","#e8dc43","#ffdf4a"];

                for (var i = 0; i < json.length; i++) {
                    browserData.push({
                        name: json[i].product,
                        y: json[i].percent,
                        color: colors[i]
                    });
                }

                $('#chart').highcharts({
                    chart: {
                        type: 'pie',
                        backgroundColor:false

                    },
                    credits: {
                        enabled: false
                    },
                    title: {
                        text: false
                    },
                    yAxis: {
                        title: {
                            text: false
                        }
                    },
                    plotOptions: {
                        pie: {
                            shadow: true,
                            center: ['50%', '45%']
                        }
                    },
                    tooltip: {
                        valueSuffix: '%'
                    },
                    series: [{
                        name: 'Percent',
                        data: browserData,
                        size: '70%',
                        dataLabels: {
                            color: 'gray',
                            distance: 20,
                            //useHTML : true,
                            formatter: function() {
                                return "<span style=\"font-size: 100%;\">" + this.point.name + " </span><span style=\"font-size: 100%; color: orange;\">" + this.point.y + "%</span>";
                            }
                        },
                        cursor: 'pointer',
                        events: {
                            click: function (event) {
                                if(event.point.name == "Kiosk")
                                    document.location.href = window.location + "/" + (event.point.name).replace( /\s/g, "").toLowerCase();
                            }
                        }
                    } , {

                        name: 'Percent',
                        data: browserData,
                        size: '70%',
                        dataLabels : false,
                        cursor: 'pointer',
                        events: {
                            click: function (event) {
                                if(event.point.name == "Kiosk")
                                    document.location.href = window.location + "/" + (event.point.name).replace( /\s/g, "").toLowerCase();
                            }
                        }
                    }],
                    exporting: {
                        buttons: [
                            {
                                enabled: false,
                                symbol: false
                            }
                        ]
                    }
                });
            });
        }
    </script>

    <script>
        function barGraph() {

            $.getJSON("${pageContext.request.contextPath}/barGraph", function (json) {
                $('#containerBarTran').highcharts({
                    colors: ['#1abc9c', '#16a085', '#2ecc71', '#27ae60', '#3498db', '#2980b9', '#9b59b6', '#8e44ad'],
                    chart: {
                        type: 'bar'
                    },
                    title: {
                        text: "Success Transaction made ("+json.month+")"
                    },
                    xAxis: {
                        categories: json.product
                    },
                    yAxis: {
                        min: 0,
                        title: {
                            text: 'Transaction'
                        }
                    },
                    legend: {
                        reversed: true
                    },
                    plotOptions: {
                        series: {
                            stacking: 'normal',
                            colorByPoint: true
                        }
                    },
                    series: [{
                        showInLegend: false,
                        data: json.countTran
                    }],
                    tooltip: {
                        shared : true,
                        formatter: function () {
                            return 'Product : '+ this.x + '<br>Transaction : ' + addCommas(this.y);
                        }
                    },
                    credits: {
                        enabled: false
                    },
                    exporting: {
                        buttons: [
                            {
                                enabled: false,
                                symbol: false
                            }
                        ]
                    }
                });

                $('#containerBarAmount').highcharts({
                    colors: ['#f1c40f', '#f39c12', '#e67e22', '#d35400', '#e74c3c', '#c0392b', '#F6546A', '#FF0000'],
                    chart: {
                        type: 'bar'
                    },
                    title: {
                        text: "Success Amount made ("+json.month+")"
                    },
                    xAxis: {
                        categories: json.product
                    },
                    yAxis: {
                        min: 0,
                        title: {
                            text: 'Amount'
                        }
                    },
                    legend: {
                        reversed: true
                    },
                    plotOptions: {
                        series: {
                            stacking: 'normal',
                            colorByPoint: true
                        }
                    },
                    series: [{
                        showInLegend: false,
                        data: json.amount
                    }],
                    tooltip: {
                        shared : true,
                        formatter: function () {
                            return 'Product : '+ this.x + '<br>Amount : ' + addCommas(this.y);
                        }
                    },
                    credits: {
                        enabled: false
                    },
                    exporting: {
                        buttons: [
                            {
                                enabled: false,
                                symbol: false
                            }
                        ]
                    }
                });
            });
        }
    </script>


    <script>
        function getCorrectTime() {
            $.ajax({
                url: "${pageContext.request.contextPath}/getCorrectTime", success: function (data) {
                    if (data == "00:00:00") {
                        bubbleGraph();
                        pieTransaction();
                        tranTMNProduct();
                        barGraph();
                    }
                }
            });
        }
        setInterval(getCorrectTime, 1000);

        window.onload = function () {
            bubbleGraph();
            pieTransaction();
            tranTMNProduct();
            barGraph();
            getCorrectTime();
            getYear();
        };
    </script>
</head>
<body>
<nav class="navbar navbar-default navbar-fixed-top">
    <div id="headerContainer" class="container">
        <img id="tmnLogo" src="${pageContext.request.contextPath}/resources/img/tmn_logo.png" alt="Null">
        <a id="header">TMN Product Dashboard <span id="year"></span></a>
        <%--<img id="triangleLogo-header" src="${pageContext.request.contextPath}/resources/img/triangle_up.gif" alt="Null">--%>
    </div>
</nav>

<div id="allContents">

<div id="div1Pie">


    <div id="divallPie">
        <div id="textdivPie"><b>Transaction Pie Graph</b></div>

    </div>

    <div id="chart" class="pie pie1"></div>

</div>


<div id="div1Bubble">

    <div id="divallBubble">

        <div id="textdivBubble"><b>Transaction and Amount Bubble Graph</b></div>


    </div>

    <div id="container_bubble" style="height: 90%"></div>
</div>


<div id="div4PT">


    <div id="divallPT">
        <div id="textdivPT"><b>Total Transaction and Amount Table</b></div>

    </div>

    <table class="table divtable">
        <thead bgcolor="#000000" style="color: #fff">
        <tr>
            <th>Product Name</th>
            <th width="20%" class="text-right">Transaction</th>
            <th width="40%" class="text-right">Amount(THB)</th>

        </tr>
        </thead>
        <tbody>

        <tr>
            <td>
                <div id="MobileApp"></div>
            </td>
            <td>

                <div id="tranMobileApp"class="text-right" ></div>

            </td>
            <td>
                <div id="amountMobileApp" class="text-right"></div>
            </td>

        </tr>
        <tr>
            <td>
                <div id="Kiosk"></div>
            </td>
            <td>
                <div id="tranKiosk" class="text-right"></div>
            </td>
            <td>

                <div id="amountKiosk" class="text-right"></div>

            </td>

        </tr>

        <tr>
            <td>
                <div id="TMX"></div>
            </td>
            <td>
                <div id="tranTMX" class="text-right"></div>
            </td>
            <td>
                <div id="amountTMX" class="text-right"></div>
            </td>

        </tr>

        <tr>
            <td>
                <div id="Payment"></div>
            </td>
            <td>
                <div id="tranPayment" class="text-right"></div>
            </td>
            <td>
                <div id="amountPayment" class="text-right"></div>
            </td>

        </tr>

        <tr>
            <td>
                <div id="TopupMobile"></div>
            </td>
            <td>
                <div id="tranTopupMobile" class="text-right"></div>
            </td>
            <td>
                <div id="amountTopupMobile" class="text-right"></div>
            </td>

        </tr>

        <tr>
            <td>
                <div id="TopupGame"></div>
            </td>
            <td>
                <div id="tranTopupGame" class="text-right"></div>
            </td>
            <td>
                <div id="amountTopupGame" class="text-right"></div>
            </td>

        </tr>

        <tr>
            <td>
                <div id="WeCard"></div>
            </td>
            <td>
                <div id="tranWeCard" class="text-right"></div>
            </td>
            <td>
                <div id="amountWeCard" class="text-right"></div>
            </td>

        </tr>

        <tr>
            <td>
                <div id="BillPay"></div>
            </td>
            <td>
                <div id="tranBillPay" class="text-right"></div>
            </td>
            <td>
                <div id="amountBillPay" class="text-right"></div>
            </td>

        </tr>

        </tbody>
        <thead bgcolor="#BDC3C7" style="color: #000">
        <tr>
            <th>
                <div id="Total"></div>
            </th>
            <th>
                <div id="tranTotal" class="text-right"></div>
            </th>
            <th>
                <div id="amountTotal" class="text-right"></div>
            </th>
        </tr>
        </thead>
    </table>
</div>




<div id="divBarTran">

    <div id="divallBarTran"><div id="textdivBarTran"><b>Transaction Bar Graph</b></div></div>
    <div id="containerBarTran" style="width: 100%; height: 90%; margin: 0 auto"></div>

</div>

<div id="divBarAmount">
    <div id="divallBarAmount"><div id="textdivBarAmount"><b>Amount Bar Graph</b></div></div>
    <div id="containerBarAmount" style="width: 100%; height: 90%; margin: 0 auto"></div>
</div>

</div>

</body>
</html>
