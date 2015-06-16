<html>
<link>
<script src='${pageContext.request.contextPath}/resources/js/Chart.js'></script>
<link href='${pageContext.request.contextPath}/resources/css/style.css' rel="stylesheet" />
</head>
<body>
<h1>${message}</h1>
<div id="canvas-holder">
    <canvas id="chart-area" width="80" height="80" style="margin-left: 5%;"/>
</div>
<script>
    var pieData = [
//            {
//                value: 300,
//                color:"#F7464A",
//                highlight: "#FF5A5E",
//                label: "Red"
//            },
        {
            value: 75,
            color: "#46BFBD",
            highlight: "#5AD3D1",
            label: "Green"
        },
        {
            value: 25,
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