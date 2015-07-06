function pieCHART() {
    $.get("PieChart",function(data){
            $("#result2").text(data+"%");
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

function top4() {
    $.getJSON("Top4", function(json){
        for(var i = 1;i<=4;i++){
            $("#resultTop4-"+i+"-place").text(json[i-1].place);
            if(json[i-1].place != ""){
                $("#resultTop4-"+i+"-percent").text(json[i-1].percent + "%");
            }
            else{
                $("#resultTop4-"+i+"-percent").text(json[i-1].percent);
            }
        }
    });
}

function revenueBar() {
    $.getJSON("revenueBar", function(json){
        var actualBar = 0;
        if(json.percent <= 100){
            document.getElementById('color_revenuebar').className = "progress lessThan100 progress-sm";
            actualBar = json.percent;
        }
        else{
            document.getElementById('color_revenuebar').className = "progress greaterThan100 progress-sm";
            actualBar = 200 - json.percent;
        }
        $("#actual").html(json.actual);
        $("#target").html(json.target);
        $("#percent").html(json.percent + " %");
        $("#percent2").attr({"aria-valuenow":actualBar,style:"width: "+actualBar+"%;"});
    });
}
function dateYesterDay() {
    $.get("DateYesterDay", function(data){
        $("#yesterday").text(data);
    });
};

function getCorrectTime() {
    $.ajax({
        url : "GetCorrectTime" , success : function(data) {
            if(data == "00:00:00"){
                top4();
                pieCHART();
                revenueBar();
                dateYesterDay();
            }
        }
    });
};
setInterval(getCorrectTime,1000);

window.onload = function () {
    pieCHART();
    top4();
    revenueBar();
    getCorrectTime();
    dateYesterDay();
};

$(function () {
    //BEGIN AREA CHART SPLINE
    var d6_1 = ${topup};
    var d6_2 = ${bill};
    $.plot("#area-chart-spline", [{
        data: d6_1,
        label: "Top-up",
        color: "#E5412D"
    },{
        data: d6_2,
        label: "Bill payment",
        color: "rgb(124,124,124)"
    }], {
        series: {
            lines: {
                show: !1

            },
            splines: {
                show: !0,
                tension: .4,
                lineWidth: 3,
                fill: 0.35
            },
            points: {
                show: !0,
                radius: 4
            }
        },
        grid: {
//                        borderColor: "#fafafa",
            borderColor: "#ABB7B7",
            borderWidth: 1,
            hoverable: !0
        },
        tooltip: !0,
        tooltipOpts: {
            content: "%x : %y",
            defaultTheme: true
        },
        xaxis: {
            tickColor: "#DDDDDD",
            mode: "categories"
        },
        yaxis: {
            tickColor: "#DDDDDD"
        },
        shadowSize: 0
    });
    //END AREA CHART SPLINE
});