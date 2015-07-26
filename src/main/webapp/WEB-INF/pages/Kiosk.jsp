<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Temp Kiosk</title>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/RedBootstrap.css">
        <link href='${pageContext.request.contextPath}/resources/css/TempKiosk.css' rel="stylesheet">

        <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.min.js"></script>
        <script type="text/javascript" src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>

        <%-- pie --%>
        <script src='${pageContext.request.contextPath}/resources/js/Chart.js'></script>

        <%-- map --%>
        <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&signed_in=false"  type="text/javascript"></script>
        <script src='${pageContext.request.contextPath}/resources/js/infobubble.js'></script>
        <script src='${pageContext.request.contextPath}/resources/js/Map.js'></script>

        <script>
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
        <%-- deploychart --%>
        <script type="text/javascript">
            var changeDeployPerc = true;
            var changeDeployDonut = true;
            function pieCHART() {
                $.get("${pageContext.request.contextPath}/pieChart",function(data){
                        $("#result2").text(data+"%");
                        var freeSpace = 100-data;
                        var pieData = [
                            {
                                value: data,
                                color: "rgb(229, 65, 45)",
                                highlight: "#5AD3D1",
                                label: "Green"
                            },
                            {
                                value: freeSpace,
                                color: "rgba(0,0,0,0)",
                                highlight: "rgba(255,255,255,0.3)",
                                label: "Yellow"
                            }
                        ];
                        var ctx = document.getElementById("chart-area").getContext("2d");
                        myPie = new Chart(ctx).Pie(pieData);
                    }
                );
            }

            function top4() {
                $.getJSON("${pageContext.request.contextPath}/top4", function(json){
                    for(var i = 1;i<=4;i++){
                        $("#resultTop4-"+i+"-place").text(json[i-1].place);
                        if(json[i-1].place != ""){
                            $("#resultTop4-"+i+"-percent").text(json[i-1].percent + "%");
                        }
                        else{
                            $("#resultTop4-"+i+"-percent").text(json[i-1].percent);
                        }
                    }
                });
            }
            var json_revenueBar_old = 0;
            function revenueBar() {
                $.getJSON("${pageContext.request.contextPath}/revenueBar", function(json){
                    if(json_revenueBar_old != json){
                        json_revenueBar_old = json;
                    var actualPercent = 0;
                    var lessPercent = 0;
                    var bonusPercent = 0;
                    if(json.actual < json.target){
                        actualPercent = json.percent;
                        lessPercent = 100 - actualPercent;
                        $("#actualP_inbar").html(actualPercent+"%");
                        $("#triangleLogo-header").attr("src","${pageContext.request.contextPath}/resources/img/down.gif");
                        $("#triangleLogo-revenue").attr("src","${pageContext.request.contextPath}/resources/img/down.gif");
                        $("#actual").attr("style","color: #FF0000;");
                        $("#actualHeader").attr("style","color: #ff2020;");

                    }
                    else if(json.actual == json.target){
                        actualPercent = json.percent;
                        lessPercent = 100 - actualPercent;
                        $("#actualP_inbar").html(actualPercent+"%");
                        $("#triangleLogo-header").attr("src","${pageContext.request.contextPath}/resources/img/equal.gif");
                        $("#triangleLogo-revenue").attr("src","${pageContext.request.contextPath}/resources/img/equal.gif");
                        $("#actual").attr("style","color: #4edf41;");
                        $("#actualHeader").attr("style","color: #00FA9A;");

                    }
                    else if(json.actual > json.target){
                        if(json.percent <= 200){
                            actualPercent = 200 - json.percent;
                            bonusPercent = 100 - actualPercent;
                            $("#actualP_inbar").html("100%");
                            $("#bonusP_inbar").html(bonusPercent+"%");
                        }
                        else{
                            bonusPercent = json.percent - 100;
                            $("#bonusP_inbar").html(addCommas(bonusPercent)+"%");
                            bonusPercent = 100;
                        }
                        $("#triangleLogo-header").attr("src","${pageContext.request.contextPath}/resources/img/up.gif");
                        $("#triangleLogo-revenue").attr("src","${pageContext.request.contextPath}/resources/img/up.gif");
                        $("#actual").attr("style","color: #25acf4;");
                        $("#actualHeader").attr("style","color: #00ffff;");
                    }

                    $("#actualHeader").html(addCommas(json.actual));
                    $("#actual").html(addCommas(json.actual));
                    $("#target").html(addCommas(json.target));
                    $("#percent").html(json.percent + " %");
                    $("#actualP").attr({"aria-valuenow":actualPercent,style:"width: "+actualPercent+"%;"});
                    $("#lessP").attr({"aria-valuenow":lessPercent,style:"width: "+lessPercent+"%;"});
                    $("#bonusP").attr({"aria-valuenow":bonusPercent,style:"width: "+bonusPercent+"%;"});
                    $("#lessP_inbar").html(lessPercent+"%");
                }
                });
            }


            function deployChartGetValue() {
                $.getJSON("${pageContext.request.contextPath}/deployChart", function(json) {
                    var version = json.version;
                    var deployPercent = json.deployPercent;
                    var tmpDeployPerc = deployPercent + "%";

                    var oldVersion = $("#deployVersion").html();
                    var oldPerc = $("#donutPerc").html();

                    if(oldVersion!=version || oldPerc!=tmpDeployPerc) {
                        changeDeployPerc = true;
                        changeDeployDonut = false;
                        $("#deployVersion").html(version);
                        $("#donutPerc").html(deployPercent+"%");
                        $("#donutChart").attr("data-percent",deployPercent);
                    }
                    else {
                        changeDeployPerc = false;
                    }
                })
            }

            function dateYesterDay() {
                $.get("${pageContext.request.contextPath}/dateYesterDay", function(data){
                    $("#yesterday").text(data);
                });
            }

            function getBillTopup() {
                $.getJSON("${pageContext.request.contextPath}/bill_topup_chart", function (rootJSON){
                    //BEGIN AREA CHART SPLINE

                    var d6_1 = rootJSON.bill;
                    var d6_2 = rootJSON.topup;


                    $.plot("#area-chart-spline", [{
                        data: d6_1,
                        label: "Bill payment",
                        color: "#E5412D"
                    },{
                        data: d6_2,
                        label: "Top-up",
                        color: "rgb(124,124,124)"
                    }], {
                        series: {
                            lines: {
                                show: !1

                            },
                            splines: {
                                show: !0,
                                tension: .4,
                                lineWidth: 3,
                                fill: 0.35
                            },
                            points: {
                                show: !0,
                                radius: 4
                            }
                        },
                        grid: {
                            borderColor: "#ABB7B7",
                            borderWidth: 1,
                            hoverable: !0
                        },
                        tooltip: !0,
                        tooltipOpts: {
                            content: "%x : %y",
                            defaultTheme: true
                        },
                        xaxis: {
                            tickColor: "#DDDDDD",
                            mode: "categories"
                        },
                        yaxis: {
                            tickColor: "#DDDDDD"
                        },
                        shadowSize: 0
                    });
                    //END AREA CHART SPLINE
                });
            }

            function getCorrectTime() {
                $.ajax({
                    url : "${pageContext.request.contextPath}/getCorrectTime" , success : function(data) {
                        if(data == "00:00:00"){
                            top4();
                            pieCHART();
                            getBillTopup();
                            dateYesterDay();
                        }
                        deployChartGetValue();
                        if(changeDeployPerc) {
                            drawDonutChart();
                        }
                        else if(!changeDeployDonut){
                            drawDonutChart();
                            changeDeployDonut = true;
                        }
                        revenueBar();
                    }
                });
            }
            setInterval(getCorrectTime,1000);

            window.onload = function () {
                getBillTopup();
                pieCHART();
                top4();
                revenueBar();
                deployChartGetValue();
                getCorrectTime();
                dateYesterDay();
            };

        </script>

        <script src="${pageContext.request.contextPath}/resources/js/jquery-1.10.2.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/jquery-ui.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/jquery.flot.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/jquery.flot.categories.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/jquery.flot.tooltip.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/jquery.flot.spline.js"></script>

    </head>

    <body>
        <%-- navigation bar --%>
        <nav class="navbar navbar-default navbar-fixed-top">
            <div id="headerContainer" class="container">
                <img id="tmnLogo" src="${pageContext.request.contextPath}/resources/img/tmn_logo.png" alt="Null">
                <a id="header" href="javascript:history.back()">TMN Kiosk Dashboard</a>
                <img id="triangleLogo-header" src="${pageContext.request.contextPath}/resources/img/up.gif" alt="Null">
                <span id="headerActual">Actual : <span id="actualHeader" style="color: mediumspringgreen"></span></span>
            </div>
        </nav>

        <div id="allContents">

            <%-- top4 --%>
            <div id="Q2-revenue">
                <span class="task-item">
                    <div id="actualTx">Actual </div>
                    <span id="actual" class="actualAmount"></span>
                     <img id="triangleLogo-revenue" src="${pageContext.request.contextPath}/resources/img/up.gif" alt="Null" class="up-downArrow">
                    <div id="targetTx">Target </div>
                    <span id="target" class="targetAmount"></span>




                    <%--<small class="pull-right text-muted" id="percent"></small>--%>
                    <div id="revenueBar"><br>
                    <div id="color_revenuebar" class="progress progress-sm">
                        <div id="actualP" role="progressbar" aria-valuenow="0"
                             aria-valuemin="0" aria-valuemax="100"
                             style="width: 0%;" class="progress-bar progress-bar">
                            <div id="actualP_inbar"></div>
                        </div>
                        <div id="lessP" role="progressbar" aria-valuenow="0"
                             aria-valuemin="0" aria-valuemax="100"
                             style="width: 0%;" class="progress-bar progress-bar-red">
                            <div id="lessP_inbar"></div>
                        </div>
                        <div id="bonusP" role="progressbar" aria-valuenow="0"
                             aria-valuemin="0" aria-valuemax="100"
                             style="width: 0%;" class="progress-bar progress-bar-blue">
                            <div id="bonusP_inbar"></div>
                        </div>
                    </div>
                    </div>
                </span>
            </div>


            <%-- top4 --%>
            <div id="Q2-top4">
                <div id="top4div">
                    <div id="top1" class="well top4boxes">
                        <div id="first" class="order">
                            <p>1<sup>st</sup></p>
                        </div>
                        <div class="percentOffset">
                            <div id="resultTop4-1-percent" class="percent"></div>
                            <div id="resultTop4-1-place" class="location"></div>
                        </div>
                    </div>
                    <div id="top2" class="well top4boxes">
                        <div id="second" class="order">
                            <p>2<sup>nd</sup></p>
                        </div>
                        <div class="percentOffset">
                            <div id="resultTop4-2-percent" class="percent"></div>
                            <div id="resultTop4-2-place" class="location"></div>
                        </div>
                    </div>
                    <div id="top3" class="well top4boxes">
                        <div id="third" class="order">
                            <p>3<sup>rd</sup></p>
                        </div>
                        <div class="percentOffset">
                            <div id="resultTop4-3-percent" class="percent"></div>
                            <div id="resultTop4-3-place" class="location"></div>
                        </div>
                    </div>
                    <div id="top4" class="well top4boxes">
                        <div id="fourth" class="order">
                            <p>4<sup>th</sup></p>
                        </div>
                        <div class="percentOffset">
                            <div id="resultTop4-4-percent" class="percent"></div>
                            <div id="resultTop4-4-place" class="location"></div>
                        </div>
                    </div>
                </div>
            </div>

            <%-- OFFLOAD --%>
            <div id="Q1-left">
                <div class="displayoffload">
                    <p><text id = "result2" style="color: #00FA9A; font-size: 30px;"></text> OffLoad <br>As of <text id="yesterday"></text></p>
                </div>
                <canvas id="chart-area"></canvas>
            </div>

            <%-- deployment --%>
            <div id="Q1-right">
                <div id="deployTitle">Deployment Success by versions</div>
                <div id="deployVersion"></div>
                <div id="deployChart">
                    <div id="donutChart" data-percent="0">
                        <span id="donutPerc">0%</span>
                    </div>
                </div>
            </div>

            <script src="resources/js/deployChartDecor.js"></script>
            <script src="resources/js/deployChartJs.js"></script>

            <%-- map --%>
            <div id="Q3">
                <div id="headMap">
                    <h4>Complaint Bangkok + Suburb</h4>
                </div>
                <div id="map"></div>
                <%-- Js will call Google API (line18). --%>
                <%-- Set theme in Map.js --%>
            </div>

            <%-- revenue --%>
            <div id="Q4">
                <h4 class="mbm">Revenue</h4>
                <div id="billTopup">
                    <p>Amount(million baht)</p>
                    <div id="area-chart-spline"></div>
                </div>
            </div>
        </div>
    </body>
</html>

