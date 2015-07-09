function deployChartGetValue() {
    $.getJSON("deployChart", function(json) {
        var version = json.version;
        var deployPercent = json.deployPercent;
        document.getElementById("deployVersion").innerHTML = version;
        document.getElementById("donutPerc").innerHTML = deployPercent+"%";
        document.getElementById("donutChart").setAttribute("data-percent", deployPercent);
    });
}