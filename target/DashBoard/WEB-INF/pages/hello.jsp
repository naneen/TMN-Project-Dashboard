<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <script src='${pageContext.request.contextPath}/resources/js/Chart.js'></script>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.min.js"></script>
        <%--<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/pie.css">--%>
        <%--<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.css">--%>
        <link href='${pageContext.request.contextPath}/resources/css/style.css' rel="stylesheet" />
        <script type="text/javascript" src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
        <script type="text/javascript">
            function x() {
                $.ajax({
                    url : "QueryPieChart", success : function(y){
                        $("#result2").html(y);
                        var freeSpace = 100-y;
                        var pieData = [
                            {
                                value: y,
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
                        ]
                        var ctx = document.getElementById("chart-area").getContext("2d");
                        myPie = new Chart(ctx).Pie(pieData);
                }
                });
            }
            setInterval(x, 5000);
        </script>
        <script type="text/javascript">
            function crunchifyAjax() {
                $.ajax({
                    url : "QueryTop4", success : function(data) {
                        $("#resultTop4").html(data);
                    }
                });
            }
            setInterval(crunchifyAjax, 1000);
        </script>
    </head>

    <body>
        <script type="text/javascript">
            x();
        </script>


        <div class="head"><img src="resources/img/TMN.png" width=80%/></div>

        <div class="boxoffload" ><div id="result2"></div></div>

        <div id="canvas-holder"><canvas id="chart-area"></canvas></div>

        <%--<div class="box1st" ><img src="resources/img/1.png" width=15%/></div>--%>

        <%--<div class="box2nd" ><img src="resources/img/2.png" width=15%/>2222</div>--%>

        <%--<div class="box3rd" ><img src="resources/img/3.png" width=15%/>3333</div>--%>

        <%--<div class="box4th" ><img src="resources/img/4.png" width=15%/>4444</div>--%>
        <c:forEach var="i" begin="1" end="4">
            <div class="box${i}" ><img src="resources/img/${i}.png" width=15%/></div>
        </c:forEach>
        <div id="resultTop4"></div>

    </body>
</html>