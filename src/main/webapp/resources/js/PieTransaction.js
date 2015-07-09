function pieTransection() {
    $.getJSON("PieTransaction", function(json){
        var name = 'TrueMoneyProduct';
        var browserData = [];
        var colors =  ["#38BCFF","#fdfa04","#0074AA"  ,"#fd3f04"  ,"#e9fd05","#fd5004" ,"#fdb404" ,"#68f40b" ,"#e9fd05","#47e888","#4eff63","#70ff4a","#9fe843","#efff57","#e8dc43","#ffdf4a"];

        for (var i = 0; i < json.length; i++) {
            browserData.push({
                name: json[i].product,
                y: json[i].percent,
                color: colors[i]
            });
        }

        $('#chart').highcharts({
            chart: {
                type: 'pie',
               backgroundColor:false

            },
            credits: {
                enabled: false
            },
            title: {
                text: false
            },
            yAxis: {
                title: {
                    text: false
                }
            },
            plotOptions: {
                pie: {
                    shadow: true,
                    center: ['50%', '45%']
                }
            },
            tooltip: {
                valueSuffix: '%'
            },
            series: [{
                name: 'Percent',
                data: browserData,
                size: '70%',
                dataLabels: {
                    color: 'gray',
                    distance: 20,
                    //useHTML : true,
                    formatter: function() {
                        return "<p><span style=\"font-size: 100%;\">" + this.point.name + " </p></span><p><span style=\"font-size: 100%; color: orange;\">" + this.point.y + "%</span></p>";
                    }
                },
                cursor: 'pointer',
                events: {
                    click: function (event) {
                        if(event.point.name = "Kiosk")
                        document.location.href = '/DashBoard/'+(event.point.name).replace( /\s/g, "").toLowerCase();
                    }
                }
            },{
                name: 'Percent',
                data: browserData,
                size: '70%',
                dataLabels : false,
                cursor: 'pointer',
                events: {
                    click: function (event) {
                        if(event.point.name = "Kiosk")
                        document.location.href = '/DashBoard/'+(event.point.name).replace( /\s/g, "").toLowerCase();
                    }
                }
            }],
            exporting: {
                buttons: [
                    {
                        symbol: false
                    }
                ]
            }
        });
    });
}