function pieTransaction() {
    $.getJSON("pieTransaction", function(json){
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

        // Create the chart
        $('#chart').highcharts({
            chart: {
                type: 'pie'
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
                    center: ['50%', '50%']
                }
            },
            tooltip: {
              valueSuffix: '%'
            },
            series: [{
                name: 'Percent',
                data: browserData,
                size: '40%',
                dataLabels: {
                    color: 'white',
                    distance: 40,
                    //useHTML : true,
                    formatter: function() {
                        return "<p><span style=\"font-size: large;\">" + this.point.name + " </p></span><p><span style=\"font-size: large; color: orange;\">" + this.point.y + "%</span></p>";
                    }
                },
                cursor: 'pointer',
                events: {
                    click: function (event) {
                        document.location.href = '/DashBoard/'+(event.point.name).replace( /\s/g, "").toLowerCase();
                    }
                }
            }
                //,{
            //    name: 'Percent',
            //    data: browserData,
            //    size: '40%',
            //    dataLabels: {
            //        formatter: function() {
            //            return this.point.y + "%";
            //        },
            //        color: 'white',
            //        distance: -30
            //    }
            //}
                , {
                name: 'Percent',
                data: browserData,
                size: '40%',
                dataLabels : false,
                cursor: 'pointer',
                events: {
                    click: function (event) {
                        document.location.href = '/DashBoard/'+(event.point.name).replace( /\s/g, "").toLowerCase();
                    }
                }
            },{
                exporting: {
                    buttons: {
                        contextButtons: {
                            enabled: false,
                            menuItems: null
                        }
                    },
                    enabled: true
                }
            }]
        });
    });
}