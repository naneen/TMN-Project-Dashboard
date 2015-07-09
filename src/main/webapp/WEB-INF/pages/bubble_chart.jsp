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

  <%--&lt;%&ndash;size of  bubble graph&ndash;%&gt;--%>
  <%--<link href='${pageContext.request.contextPath}/resources/css/Bubble.css' rel="stylesheet">--%>

  <%--script in head for bubble chart--%>
  <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>

  <script type="text/javascript">
    $(function () {
      $('#container_bubble').highcharts({
        credits: {
          enabled: false
        },
        yAxis: {
          title:{
            text : 'Amount (Million Baht)'
          }
        },

        chart: {
          type: 'bubble'
        },

        title: {
          text: 'Bubble Size = Transaction Volume',

        },
        legend: {
          align: 'right',
          verticalAlign: 'top',
          layout: 'vertical',
          x: 0,
          y: 100
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
          },{
            data: [[54, 28, 69], [15, 50, 32], [20, 36, 15], [86, 55, 93], [42, 26, 51], [12, 66, 45], [2, 76, 99], [42, 37, 14], [19, 27, 5], [80, 76, 89]],
            name: '#Topup Mobile',
            color: '#86E2D5'
          },{
            data: [[33, 42, 93], [84, 70, 60], [55, 69, 47], [13, 2, 9], [39, 89, 22], [26, 29, 39], [36, 33, 30], [43, 46, 49], [41, 51, 60], [11, 51, 81]],
            name: 'Topup Game',
            color: '#2ECC71'
          },{
            data: [[84, 42, 46], [4, 62, 13], [45, 65, 78], [23, 57, 16], [38, 9, 46], [25, 97, 22], [67, 57, 35], [21, 7, 56], [27, 95, 32], [21, 6, 31]],
            name: 'Master Card',
            color: '#F89406'
          },{
            data: [[86, 75, 24], [75, 23, 9], [71, 35, 38], [64, 44, 45], [9, 23, 32], [45, 54, 65], [27, 73, 39], [62, 28, 84], [86, 41, 32], [20, 31, 33]],
            name: 'Bill pay',
            color: '#D35400'
          }]
      });
    });
  </script>
</head>
<body>
<%--<a href="/DashBoard/kiosk" id="headNav">${msg}</a>--%>


<%--script in body for bubble chart--%>
<script src="${pageContext.request.contextPath}/resources/js/highcharts.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/highcharts-more.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/modules/exporting.js"></script>

<div id="container_bubble" style="height: 45% ; width : 45% ; min-width: 310px;"></div>

</body>
</html>
