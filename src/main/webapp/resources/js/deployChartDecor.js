var chart4Height = document.getElementById('Q1').offsetHeight;
var donutHeight =  (chart4Height - document.getElementById('deployTitle').offsetHeight - document.getElementById('deployVersion').offsetHeight)*80/100;
document.getElementById('deployChart').style.width = donutHeight+"px";
document.getElementById('donutChart').style.height = donutHeight+"px";
document.getElementById('donutChart').style.width = donutHeight+"px";

var percPadding = (donutHeight/2 - document.getElementById('donutPerc').offsetHeight)/2;