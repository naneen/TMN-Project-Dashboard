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
    </script>


    <%--<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>--%>
    <link href='${pageContext.request.contextPath}/resources/css/PieTransaction.css' rel="stylesheet">
    <script src='${pageContext.request.contextPath}/resources/js/highcharts.js'></script>
    <script src="${pageContext.request.contextPath}/resources/js/highcharts-more.js"></script>
    <script src='${pageContext.request.contextPath}/resources/js/exporting.js'></script>
    <%--<script src="${pageContext.request.contextPath}/resources/js/PieTransaction.js"></script>--%>

    <script type="text/javascript">
        $(function () {
            $('#container_bubble').highcharts({
                credits: {
                    enabled: false
                },
                yAxis: {
                    title: {
                        text: 'Amount (Million Baht)'
                    }
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

                series: [{
                    data: [[97, 36, 79], [94, 74, 60], [68, 76, 58], [64, 87, 56], [68, 27, 73], [74, 99, 42], [7, 93, 87], [51, 69, 40], [38, 23, 33], [57, 86, 31]],
                    name: 'Moblie app',
                    color: '#F64747'
                }, {
                    data: [[25, 10, 87], [2, 75, 59], [11, 54, 8], [86, 55, 93], [5, 3, 58], [90, 63, 44], [91, 33, 17], [97, 3, 56], [15, 67, 48], [54, 25, 81]],
                    name: 'Kiosk',
                    color: '#F62459'
                }, {
                    data: [[47, 47, 21], [20, 12, 4], [6, 76, 91], [38, 30, 60], [57, 98, 64], [61, 17, 80], [83, 60, 13], [67, 78, 75], [64, 12, 10], [30, 77, 82]],
                    name: 'TMX',
                    color: '#D91E18'
                },
                    {
                        data: [[25, 10, 87], [2, 75, 59], [11, 54, 8], [86, 55, 93], [5, 3, 58], [90, 63, 44], [91, 33, 17], [97, 3, 56], [15, 67, 48], [54, 25, 81]],
                        name: 'Payment Gateway',
                        color: '#C8F7C5'
                    }, {
                        data: [[54, 28, 69], [15, 50, 32], [20, 36, 15], [86, 55, 93], [42, 26, 51], [12, 66, 45], [2, 76, 99], [42, 37, 14], [19, 27, 5], [80, 76, 89]],
                        name: 'Topup Mobile',
                        color: '#86E2D5'
                    }, {
                        data: [[33, 42, 93], [84, 70, 60], [55, 69, 47], [13, 2, 9], [39, 89, 22], [26, 29, 39], [36, 33, 30], [43, 46, 49], [41, 51, 60], [11, 51, 81]],
                        name: 'Topup Game',
                        color: '#2ECC71'
                    }, {
                        data: [[84, 42, 46], [4, 62, 13], [45, 65, 78], [23, 57, 16], [38, 9, 46], [25, 97, 22], [67, 57, 35], [21, 7, 56], [27, 95, 32], [21, 6, 31]],
                        name: 'Master Card',
                        color: '#F89406'
                    }, {
                        data: [[86, 75, 24], [75, 23, 9], [71, 35, 38], [64, 44, 45], [9, 23, 32], [45, 54, 65], [27, 73, 39], [62, 28, 84], [86, 41, 32], [20, 31, 33]],
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
    </script>


    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/js/bootstrap-3.1.1.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/barGraph.css">

    <script src="${pageContext.request.contextPath}/resources/js/run_prettify.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jchart.js"></script>


    <script>
        $(document).ready(function () {
            $("#transaction_chart").jChart({x_label: "Transaction"});
            $("#transaction_chart2").jChart({x_label: "Amount"});
            $("#colors_chart").jChart();

        });
    </script>

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
                                return "<p><span style=\"font-size: 100%;\">" + this.point.name + " </p></span><p><span style=\"font-size: 100%; color: orange;\">" + this.point.y + "%</span></p>";
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
        function getCorrectTime() {
            $.ajax({
                url: "${pageContext.request.contextPath}/getCorrectTime", success: function (data) {
                    if (data == "23:00:00") {
                        pieTransaction();
                    }
                }
            });
        }
        setInterval(getCorrectTime, 3000);

        window.onload = function () {
            pieTransaction();
            getCorrectTime();
        };
    </script>
</head>
<body>

    <div id="div1Pie">

        <div id="divallPie">
            <div id="textdivPie"><b>Pie Graph by TMN Product</b></div>

        </div>

        <div id="chart" class="pie pie1"></div>

    </div>


    <div id="div1Bubble">

        <div id="divallBubble">
            <div id="textdivBubble"><b>Bubble Graph by TMN Product</b></div>

        </div>

        <div id="container_bubble" style="height: 90%"></div>
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
                <th>Amount(THB)</th>
            </tr>
            </thead>
            <tbody>

            <tr>
                <td>
                    <div id="MobileApp"></div>
                </td>
                <td>
                    <div id="tranMobileApp"></div>
                </td>
                <td>
                    <div id="amountMobileApp"></div>
                </td>

            </tr>
            <tr>
                <td>
                    <div id="Kiosk"></div>
                </td>
                <td>
                    <div id="tranKiosk"></div>
                </td>
                <td>
                    <div id="amountKiosk"></div>
                </td>

            </tr>

            <tr>
                <td>
                    <div id="TMX"></div>
                </td>
                <td>
                    <div id="tranTMX"></div>
                </td>
                <td>
                    <div id="amountTMX"></div>
                </td>

            </tr>

            <tr>
                <td>
                    <div id="Payment"></div>
                </td>
                <td>
                    <div id="tranPayment"></div>
                </td>
                <td>
                    <div id="amountPayment"></div>
                </td>

            </tr>

            <tr>
                <td>
                    <div id="TopupMobile"></div>
                </td>
                <td>
                    <div id="tranTopupMobile"></div>
                </td>
                <td>
                    <div id="amountTopupMobile"></div>
                </td>

            </tr>

            <tr>
                <td>
                    <div id="TopupGame"></div>
                </td>
                <td>
                    <div id="tranTopupGame"></div>
                </td>
                <td>
                    <div id="amountTopupGame"></div>
                </td>

            </tr>

            <tr>
                <td>
                    <div id="WeCard"></div>
                </td>
                <td>
                    <div id="tranWeCard"></div>
                </td>
                <td>
                    <div id="amountWeCard"></div>
                </td>

            </tr>

            <tr>
                <td>
                    <div id="BillPay"></div>
                </td>
                <td>
                    <div id="tranBillPay"></div>
                </td>
                <td>
                    <div id="amountBillPay"></div>
                </td>

            </tr>

            </tbody>
            <thead bgcolor="#BDC3C7" style="color: #000">
            <tr>
                <th>
                    <div id="Total"></div>
                </th>
                <th>
                    <div id="tranTotal"></div>
                </th>
                <th>
                    <div id="amountTotal"></div>
                </th>
            </tr>
            </thead>
        </table>
    </div>


    <div id="divBarTran">

        <div id="divallBarTran">
            <div id="textdivBarTran"><b>Transaction Bar Graph by TMN Product</b></div>

        </div>
        <div id="transaction_chart" data-sort="false" data-width="200%" class="jChart chart-lg"
             name="Success Transaction made (${month})">
            <div class="define-chart-row" data-color="#84d6ff" title="Mobile App.">${mobileAppTran}</div>
            <div class="define-chart-row" data-color="#38BCFF" title="Kiosk">${kioskTran}</div>
            <div class="define-chart-row" data-color="#00A9FF" title="TMX">${tmxTran}</div>
            <div class="define-chart-row" data-color="#008DD3" title="Payment Gateway">${paymentGatewayTran}</div>
            <div class="define-chart-row" data-color="#0074AA" title="Top-up Mobile">${TopUpMobileTran}</div>
            <div class="define-chart-row" data-color="#005882" title="Top-up Game">${TopUpGameTran}</div>
            <div class="define-chart-row" data-color="#00496B" title="Master Card">${masterCardTran}</div>
            <div class="define-chart-row" data-color="#013750" title="Bill Payment">${billPayTran}</div>

            <div class="define-chart-footer">10000</div>
            <div class="define-chart-footer">20000</div>
            <div class="define-chart-footer">30000</div>
            <div class="define-chart-footer">40000</div>
            <div class="define-chart-footer">50000</div>
        </div>


    </div>

    <div id="divBarAmount">

        <div id="divallBarAmount">
            <div id="textdivBarAmount"><b>Transaction Bar Graph by TMN Product</b></div>

        </div>
        <div id="transaction_chart2" data-sort="false" data-width="200%" class="jChart chart-lg"
             name="Success Amount made (${month})">
            <div class="define-chart-row" data-color="#e9fd05" title="Mobile App.">${mobileAppAmount}</div>
            <div class="define-chart-row" data-color="#fdee04" title="Kiosk">${kioskAmount}</div>
            <div class="define-chart-row" data-color="#fddd04" title="TMX">${tmxAmount}</div>
            <div class="define-chart-row" data-color="#fdbf04" title="Payment Gateway">${paymentGatewayAmount}</div>
            <div class="define-chart-row" data-color="#fdb404" title="Top-up Mobile">${TopUpMobileAmount}</div>
            <div class="define-chart-row" data-color="#fda204" title="Top-up Game">${TopUpGameAmount}</div>
            <div class="define-chart-row" data-color="#fd7904" title="Master Card">${masterCardAmount}</div>
            <div class="define-chart-row" data-color="#fd3f04" title="Bill Payment">${billPayAmount}</div>

            <div class="define-chart-footer">40000</div>
            <div class="define-chart-footer">80000</div>
            <div class="define-chart-footer">120000</div>
            <div class="define-chart-footer">160000</div>
            <div class="define-chart-footer">200000</div>
        </div>


    </div>

</body>
</html>
