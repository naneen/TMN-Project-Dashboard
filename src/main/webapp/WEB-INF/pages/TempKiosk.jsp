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
        <%--<script src="${pageContext.request.contextPath}/resources/js/jquery-1.10.2.min.js"></script>--%>
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
        <%-- pie --%>
        <script src='${pageContext.request.contextPath}/resources/js/Chart.js'></script>
        <%-- map --%>
        <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&signed_in=false"  type="text/javascript"></script>
        <script src='${pageContext.request.contextPath}/resources/js/infobubble.js'></script>
        <script src='${pageContext.request.contextPath}/resources/js/Map.js'></script>

        <script type="text/javascript">
            function PIECHART() {
                $.get("QueryPieChart",function(data){
                            var myDate = new Date();
                            var monthNames = ["January", "February", "March", "April", "May", "June",
                                "July", "August", "September", "October", "November", "December"
                            ];
                            myDate.setDate(myDate.getDate()-1);
                            var Day = myDate.getDate();
                            var Month = monthNames[myDate.getMonth()];
                            var Year = myDate.getFullYear();
                            $("#offloadResult").html("<font color=\"#38b44a\" size=\"10px\">"+data+"%</font>"+" OffLoad <br>"+ "As of " + Day + " " + Month + " " + Year);
                            var freeSpace = 100-data;
                            var pieData = [
                                {
                                    value: data,
                                    color: "#ff4220",
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

            function TOP4() {
                $.get("QueryTop4", function(data){
                    $("#resultTop4").html(data);
                });
            }

            function TimeRE() {
                $.ajax({
                    url : "TimeSet" , success : function(data) {
                        if(data == "00:00:00"){
                            TOP4();
                            PIECHART();
                        }
                    }
                });
            }
            setInterval(TimeRE,1000);

            $(function () {
                //BEGIN AREA CHART SPLINE
                var d6_1 = ${topup};
                var d6_2 = ${bill};
                $.plot("#area-chart-spline", [{
                    data: d6_1,
                    label: "Top-up",
                    color: "#E5412D"
                },{
                    data: d6_2,
                    label: "Bill payment",
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
//                        borderColor: "#fafafa",
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
        </script>

        <%--  --%>
        <script src="${pageContext.request.contextPath}/resources/js/jquery-1.10.2.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/jquery-ui.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/jquery.flot.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/jquery.flot.categories.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/jquery.flot.tooltip.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/jquery.flot.spline.js"></script>
    </head>
    <body>
        <script type="text/javascript">
            PIECHART();
            TOP4();
            TimeRE();
        </script>

        <%-- navigation bar --%>
        <nav class="navbar navbar-default navbar-fixed-top">
            <div id="headerContainer" class="container">
                <img id="tmnLogo" src="${pageContext.request.contextPath}/resources/img/tmn_logo.png" alt="Null">
                <a id="header" href="/Dashboard/kiosk">TMN Product Dashboard</a>
            </div>
        </nav>

        <div id="allContents">

            <%-- offset + top4 --%>
            <div id="Q2">
                <div id="pieDiv" class="well well-lg">
                    <canvas id="chart-area"></canvas>
                    <div id="offloadResult" class="displayoffload"></div>
                </div>

                <div id="top4div">
                    <div id="top1" class="well top4boxes">
                        ONE
                    </div>
                    <div id="top2" class="well top4boxes">
                        TWO
                    </div>
                    <div id="top3" class="well top4boxes">
                        THREE
                    </div>
                    <div id="top4" class="well top4boxes">
                        FOUR
                    </div>
                </div>
            </div>

            <%-- deployment --%>
            <div id="Q1">
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
                <div id="revenue">
                    <div class="col-md-12" id="target-bar">
                        <h4 class="mbm">Revenue</h4>
                        <span class="task-item">
                            <span style="color: red">Actual:</span>
                            <span style="color: #313131">${actual} </span>
                            <span style="color: red">Target: </span>
                            <span style="color: #000000">${target}</span>
                            <small class="pull-right text-muted">${percent}%</small>
                            <div id="progressbar" class="progress progress-sm">
                                <div role="progressbar" aria-valuenow="${percent}"
                                     aria-valuemin="0" aria-valuemax="100"
                                     style="width: ${percent}%;" class="progress-bar progress-bar">
                                </div>
                                <div role="progressbar" aria-valuenow="${lessPercent}"
                                     aria-valuemin="0" aria-valuemax="100"
                                     style="width: ${lessPercent}%;" class="progress-bar progress-bar-red">
                                </div>
                            </div>
                        </span>
                    </div>
                </div>
                <div id="billTopup">
                    <div id="area-chart-spline"></div>
                </div>
            </div>

        </div>
    </body>
</html>