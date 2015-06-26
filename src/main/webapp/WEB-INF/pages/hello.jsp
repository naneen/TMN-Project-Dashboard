<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <%--<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css">--%>
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
        <link href='${pageContext.request.contextPath}/resources/css/style.css' rel="stylesheet">

        <script src='${pageContext.request.contextPath}/resources/js/Chart.js'></script>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.min.js"></script>
        <script type="text/javascript" src="http://code.jquery.com/jquery-1.10.1.min.js"></script>

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
                        $("#result2").html(data+"% OffLoad <br>"+ "As of " + Day + " " + Month + " " + Year);
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
        <script type="text/javascript">
            PIECHART();
            TOP4();
            TimeRE();
        </script>

                                                        <%--TOP4 & PIE--%>
        <div id="div1">
            <div id="head">TMN Product Dashboard</div>
        </div>

        <div id="boxoffload">
            <div id="diva">
                <div id="canvas-holder"><canvas id="chart-area"></canvas></div>
            </div>
            <center><div id="result2" class="displayoffload"></div></center>
        </div>

        <div id="resultTop4"></div>

                                                            <%--REVENUE--%>

        <div id="div2" class="col-lg-6">
            <div class="panel">
                <div class="panel-body">

                    <div id="revenue" class="row">
                        <div class="col-md-12" id="target-bar">
                            <h4 class="mbm">Revenue</h4>

                            <span class="task-item">
                                <span style="color: red">Actual:</span>
                                <span style="color: #737373">${actual} </span>
                                <span style="color: red">Target: </span>
                                <span style="color: #737373">${target}</span>
                                <small class="pull-right text-muted">${percent}%</small>
                                <div class="progress progress-sm">
                                    <div role="progressbar" aria-valuenow="${percent}"
                                        aria-valuemin="0" aria-valuemax="100"
                                        style="width: ${percent}%;" class="progress-bar progress-bar">
                                        <span class="sr-only">${percent}% Complete (success)</span>
                                    </div>
                                </div>
                            </span>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-12">
                            <div id="area-chart-spline" style="width: 100%; height: 210px">
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>

                                                            <%--Map--%>
        <div id="div3">
            <div id="map"></div>
            <%-- js will call Google API (line18) --%>
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


