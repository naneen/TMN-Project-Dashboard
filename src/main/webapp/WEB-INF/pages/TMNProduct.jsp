<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TMN Product</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
    <link href='${pageContext.request.contextPath}/resources/css/Bubble.css' rel="stylesheet">

    <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.min.js"></script>
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.10.1.min.js"></script>

    <script src="${pageContext.request.contextPath}/resources/js/jquery-1.10.2.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>


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
    <div id="chart" class="pie pie1"></div>
</body>
</html>
