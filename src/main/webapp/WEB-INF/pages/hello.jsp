<html>
<head>
    <script src='${pageContext.request.contextPath}/resources/js/Chart.js'></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/pie.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.css">

</head>
<body>
	<h1>${message}</h1>
    <div id="canvas-holder">
        <canvas id="chart-area"/>
    </div>
    <script>
        var avg = 75;
        var freeSpace = 100-avg;
        var pieData = [
//            {
//                value: 300,
//                color:"#F7464A",
//                highlight: "#FF5A5E",
//                label: "Red"
//            },
            {
                value: avg,
                color: "#46BFBD",
                highlight: "#5AD3D1",
                label: "Green"
            },
            {
                value: freeSpace,
                color: "#FDB45C",
                highlight: "#FFC870",
                label: "Yellow"
            }
        ]

        window.onload = function(){
            var ctx = document.getElementById("chart-area").getContext("2d");
            window.myPie = new Chart(ctx).Pie(pieData);
        };
    </script>


</body>
</html>