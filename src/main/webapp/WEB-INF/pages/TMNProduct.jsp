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
    

</head>
<body>
    <a href="/DashBoard/kiosk" id="headNav">${msg}</a>
    
   
   
       <div id="div4">
     <div id="divall"><div id="textdiv"><b>Total by TMN Product</b></div></div>

  <table class="table divtable">
    <thead bgcolor="#000000" style="color: #fff">
      <tr>
        <th>Product Name</th>
        <th>Transaction</th>
        <th>Amount</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td>Moblie app</td>
        <td>100</td>
         <td>100</td>
        
      </tr>

      <tr>
       <td>Kiosk</td>
        <td>100</td>
         <td>100</td>

      </tr>

      <tr>
        <td>TMX</td>
       <td>100</td>
        <td>100</td>

      </tr>

       <tr>
        <td>Payment Gateway</td>
        <td>100</td>
         <td>100</td>

      </tr>

      <tr>
        <td>Topup Mobile</td>
        <td>100</td>
         <td>100</td>

       </tr>

     <tr>  
         <td>Topup Game</td>
         <td>100</td>
         <td>100</td>

     </tr>

     <tr>  
        <td>Master Card</td>
        <td>100</td>
         <td>100</td>

      </tr>

     <tr>   
         <td>Bil pay</td>
         <td>100</td>
         <td>100</td>

      </tr>

    </tbody>
 <thead bgcolor="#BDC3C7" style="color: #000">
      <tr>
        <th>Total</th>
        <th>800</th>
        <th>800</th>
      </tr>
    </thead>
  </table>
</div>

</body>
</html>
