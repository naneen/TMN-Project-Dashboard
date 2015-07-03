<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
        <link href='${pageContext.request.contextPath}/resources/css/KioskStyle.css' rel="stylesheet">

        <script src='${pageContext.request.contextPath}/resources/js/Chart.js'></script>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.min.js"></script>
        <script type="text/javascript" src="http://code.jquery.com/jquery-1.10.1.min.js"></script>

        <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&signed_in=false"  type="text/javascript"></script>
        <script src='${pageContext.request.contextPath}/resources/js/infobubble.js'></script>
        <script src='${pageContext.request.contextPath}/resources/js/Map.js'></script>

        <script type="text/javascript">
            function pieCHART() {
                $.get("PieChart",function(data){
                        $("#result2").text(data+"%");
                        var freeSpace = 100-data;
                        var pieData = [
                            {
                                value: data,
                                color: "#46BFBD",
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
                $.getJSON("Top4", function(json){
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

            function revenueBar() {
                $.getJSON("revenueBar", function(json){
                    var actualBar = 0;
                    if(json.percent <= 100){
                        document.getElementById('color_revenuebar').className = "progress lessThan100 progress-sm";
                        actualBar = json.percent;
                    }
                    else{
                        document.getElementById('color_revenuebar').className = "progress greaterThan100 progress-sm";
                        actualBar = 200 - json.percent;
                    }
                    $("#actual").html(json.actual);
                    $("#target").html(json.target);
                    $("#percent").html(json.percent + " %");
                    $("#percent2").attr({"aria-valuenow":actualBar,style:"width: "+actualBar+"%;"});
                });
            }
            function dateYesterDay() {
                $.get("DateYesterDay", function(data){
                    $("#yesterday").text(data);
                });
            };

            function getCorrectTime() {
                $.ajax({
                    url : "GetCorrectTime" , success : function(data) {
                        if(data == "00:00:00"){
                            top4();
                            pieCHART();
                            revenueBar();
                            dateYesterDay();
                        }
                    }
                });
            };
            setInterval(getCorrectTime,1000);

            window.onload = function () {
                pieCHART();
                top4();
                revenueBar();
                getCorrectTime();
                dateYesterDay();
            };

            $(function () {
                //BEGIN AREA CHART SPLINE
                var d6_1 = ${topup};
                var d6_2 = ${bill};
                $.plot("#area-chart-spline", [{
                    data: d6_1,
                    label: "Top-up",
                    color: "#F7C445"
                },{
                    data: d6_2,
                    label: "Bill payment",
                    color: "#7E98F7"
                }], {
                    series: {
                        lines: {
                            show: !1
                        },
                        splines: {
                            show: !0,
                            tension: .4,
                            lineWidth: 2,
                            fill: .8
                        },
                        points: {
                            show: !0,
                            radius: 4
                        }
                    },
                    grid: {
                        borderColor: "#fafafa",
                        borderWidth: 1,
                        hoverable: !0
                    },
                    tooltip: !0,
                    tooltipOpts: {
                        content: "%x : %y",
                        defaultTheme: true
                    },
                    xaxis: {
                        tickColor: "#ABB7B7",
                        mode: "categories"
                    },
                    yaxis: {
                        tickColor: "#ABB7B7"
                    },
                    shadowSize: 0
                });
                //END AREA CHART SPLINE
            });
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
        <div id="divBG"></div>
                                                        <%--TOP4 & PIE--%>

        <div id="div1">
            <a id="head" href="/DashBoard">TMN Product Dashboard</a>

            <div id="boxoffload">
                <div id="diva">
                    <div id="canvas-holder"><canvas id="chart-area"></canvas></div>
                </div>
                <center><div class="displayoffload"><font color="#green" id = "result2"></font> OffLoad <br>As of <font id="yesterday"></font></div></center>
                <%--<center><div id="result2" class="displayoffload"></div></center>--%>
            </div>
            <div id="box1" class="boxall"><img src="${pageContext.request.contextPath}/resources/img/1.png" width=20% class="pic"/><div id="text"><center><div id="PercentageSize"><div id="resultTop4-1-percent"></div></div><div id="textSize"><div id="resultTop4-1-place"></div></div></center></div></div>
            <div id="box2" class="boxall"><img src="${pageContext.request.contextPath}/resources/img/2.png" width=20% class="pic"/><div id="text"><center><div id="PercentageSize"><div id="resultTop4-2-percent"></div></div><div id="textSize"><div id="resultTop4-2-place"></div></div></center></div></div>
            <div id="box3" class="boxall"><img src="${pageContext.request.contextPath}/resources/img/3.png" width=20% class="pic"/><div id="text"><center><div id="PercentageSize"><div id="resultTop4-3-percent"></div></div><div id="textSize"><div id="resultTop4-3-place"></div></div></center></div></div>
            <div id="box4" class="boxall"><img src="${pageContext.request.contextPath}/resources/img/4.png" width=20% class="pic"/><div id="text"><center><div id="PercentageSize"><div id="resultTop4-4-percent"></div></div><div id="textSize"><div id="resultTop4-4-place"></div></div></center></div></div>




        <div id="resultTop4"></div>
        </div>

                                                            <%--REVENUE--%>

        <div id="div2" class="col-lg-6">
            <div class="panel">
                <div class="panel-body">

                    <div id="revenue" class="row">
                        <div class="col-md-12" id="target-bar">
                            <h4 class="mbm">Revenue</h4>

                            <span class="task-item">
                                <span style="color: red">Actual:</span>
                                <span style="color: #737373" id="actual"></span>
                                <span style="color: red">Target: </span>
                                <span style="color: #737373" id="target"></span>
                                <small class="pull-right text-muted" id="percent"></small>
                                <div id="color_revenuebar">
                                    <div id="percent2" role="progressbar" aria-valuenow="50"
                                        aria-valuemin="0" aria-valuemax="100"
                                        style="width: 50%;" class="progress-bar" >
                                    </div>
                                </div>
                            </span>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-12">
                            <div id="area-chart-spline" style="width: 100%; height: 75%">
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>

                                                            <%--Map--%>
        <div id="div3">
            <div id="headMap">
                <h3>Complaint Bangkok + Suburb</h3>
            </div>
            <div id="map"></div>
            <%-- Js will call Google API (line18). --%>
            <%-- Set theme in Map.js --%>
        </div>


                                                            <%--Verson--%>
        <div id="chart4" class="board">
            <div id="deployTitle">Deployment Success by versions</div>
            <div id="deployVersion">4.9.10</div>
            <div id="deployChart">
                <div id="donutChart" data-percent="69">
                    <span id="donutPerc">69%</span>
                </div>
            </div>
        </div>

        <script src="resources/js/deployChartDecor.js"></script>
        <script src="resources/js/deployChartJs.js"></script>
    </body>
</html>


