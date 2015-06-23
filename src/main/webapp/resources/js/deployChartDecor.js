//deploy chart--------------------------------------------------------------------------------
//var heightOfWindow = window.innerHeight;
//document.getElementById('chart4').style.height = (heightOfWindow/2)+"px"; 


document.getElementById('chart4').style.height = 46+"%";

var chart4Height = document.getElementById('chart4').offsetHeight;
var donutHeight =  (chart4Height - document.getElementById('deployTitle').offsetHeight - document.getElementById('deployVersion').offsetHeight)*80/100;
document.getElementById('deployChart').style.width = donutHeight+"px";
document.getElementById('donutChart').style.height = donutHeight+"px";
document.getElementById('donutChart').style.width = donutHeight+"px";

var percPadding = (donutHeight/2 - document.getElementById('donutPerc').offsetHeight)/2;
document.getElementById('donutPerc').style.padding = percPadding+"px 0 0 0";


////revenue chart------------------------------------------------------------------------------------
//
//var heightOfRevenue = document.getElementById('revenue').offsetHeight;
//var heightOfLine = (heightOfDiv - heightOfRevenue)*0.8;
//document.getElementById('area-chart-spline').style.height = heightOfLine+"px";