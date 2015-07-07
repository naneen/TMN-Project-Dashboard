function pieTransection() {
    $.getJSON("PieTransaction", function(json){
        var name = 'TrueMoneyProduct';
        var browserData = [];
        var colors =  ["#dc49ee","#b64dee","#7946e8","#615aff","#466ae8","#4da3ff","#4ed6ff","#47e8e3","#5bffcb","#47e888","#4eff63","#70ff4a","#9fe843","#efff57","#e8dc43","#ffdf4a"];

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