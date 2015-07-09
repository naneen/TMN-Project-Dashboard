var chart4Height = document.getElementById('Q1').offsetHeight;
var donutHeight =  (chart4Height - document.getElementById('deployTitle').offsetHeight - document.getElementById('deployVersion').offsetHeight)*80/100;
document.getElementById('deployChart').style.width = donutHeight+"px";
document.getElementById('donutChart').style.height = donutHeight+"px";
document.getElementById('donutChart').style.width = donutHeight+"px";


var tmpCount = 1;
var changeDeployPerc = true;


function drawDonutChart() {
    var options = {
        scaleColor: false,
        trackColor: 'rgb(200,200,200)',
        barColor: '#E5412D',
        lineWidth: 15,
        lineCap: 'butt',
        size: donutHeight
    };

    var charts = [];
    [].forEach.call(document.querySelectorAll('#donutChart'), function(el) {
        if(tmpCount!=1) {
            var last;
            last = $('#donutChart').children(':last-child');
            $(last).remove();
        }
        tmpCount++;
        charts.push(new EasyPieChart(el, options));
    });
}