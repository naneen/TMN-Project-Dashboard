//<![CDATA[

$(function () {
    jQuery(document).ready(function () {
        var map;
        var count = 0;
        $.getJSON("complaintTicket", function (json) {
            initialize();
            var lon, lat, ticket;
            for (i = 0; i < json.length; i += 5) {
                lon = parseFloat(json[i + 1]);
                lat = parseFloat(json[i + 2]);
                ticket = parseInt(json[i + 4]);

                setMarkers(map, json[i], lon, lat, ticket);

            }
        });

        function initialize() {
            var mapOptions = {
                zoom: 13,
                center: new google.maps.LatLng(13.7557261, 100.4988848),
                disableDefaultUI: true,
                boxClass: "info-windows"
            }
            map = new google.maps.Map(document.getElementById('map'), mapOptions);

            map.set('styles', [
                {
                    "featureType": "all",
                    "elementType": "labels",
                    "stylers": [
                        {
                            "visibility": "off"
                        }
                    ]
                },
                {
                    "featureType": "administrative",
                    "elementType": "labels",
                    "stylers": [
                        {
                            "visibility": "off"
                        }
                    ]
                },
                {
                    "featureType": "administrative",
                    "elementType": "labels.text",
                    "stylers": [
                        {
                            "visibility": "on"
                        }
                    ]
                },
                {
                    "featureType": "administrative",
                    "elementType": "labels.text.fill",
                    "stylers": [
                        {
                            "color": "#444444"
                        }
                    ]
                },
                {
                    "featureType": "administrative.country",
                    "elementType": "labels",
                    "stylers": [
                        {
                            "visibility": "off"
                        }
                    ]
                },
                {
                    "featureType": "landscape",
                    "elementType": "all",
                    "stylers": [
                        {
                            "color": "#f2f2f2"
                        }
                    ]
                },
                {
                    "featureType": "landscape",
                    "elementType": "labels",
                    "stylers": [
                        {
                            "visibility": "off"
                        }
                    ]
                },
                {
                    "featureType": "landscape.man_made",
                    "elementType": "labels",
                    "stylers": [
                        {
                            "visibility": "off"
                        }
                    ]
                },
                {
                    "featureType": "poi",
                    "elementType": "all",
                    "stylers": [
                        {
                            "visibility": "off"
                        }
                    ]
                },
                {
                    "featureType": "poi",
                    "elementType": "labels",
                    "stylers": [
                        {
                            "visibility": "off"
                        }
                    ]
                },
                {
                    "featureType": "road",
                    "elementType": "all",
                    "stylers": [
                        {
                            "saturation": -100
                        },
                        {
                            "lightness": 45
                        }
                    ]
                },
                {
                    "featureType": "road",
                    "elementType": "labels",
                    "stylers": [
                        {
                            "visibility": "off"
                        }
                    ]
                },
                {
                    "featureType": "road.highway",
                    "elementType": "all",
                    "stylers": [
                        {
                            "visibility": "simplified"
                        }
                    ]
                },
                {
                    "featureType": "road.arterial",
                    "elementType": "labels.icon",
                    "stylers": [
                        {
                            "visibility": "off"
                        }
                    ]
                },
                {
                    "featureType": "transit",
                    "elementType": "all",
                    "stylers": [
                        {
                            "visibility": "off"
                        }
                    ]
                },
                {
                    "featureType": "transit",
                    "elementType": "labels",
                    "stylers": [
                        {
                            "visibility": "off"
                        }
                    ]
                },
                {
                    "featureType": "transit.line",
                    "elementType": "all",
                    "stylers": [
                        {
                            "visibility": "off"
                        }
                    ]
                },
                {
                    "featureType": "transit.station",
                    "elementType": "all",
                    "stylers": [
                        {
                            "visibility": "off"
                        }
                    ]
                },
                {
                    "featureType": "water",
                    "elementType": "all",
                    "stylers": [
                        {
                            "color": "#e74c3c"
                        },
                        {
                            "visibility": "on"
                        }
                    ]
                }
            ]);

        }

        function setMarkers(map, location, lon, lat, ticket) {

            var infoBubble = new InfoBubble({
                Padding: 10,
                borderRadius: 10,
                borderWidth: 0,
                borderColor: "#fff",
                backgroundColor: "#fff",
                minWidth: 200,
                minHeight: 50,
                arrowSize: 20,
                disableAutoPan: false,
                hideCloseButton: true
            }), marker, i;
            count++;
            marker = new google.maps.Marker({
                position: new google.maps.LatLng(lon, lat),
                map: map
            });
            if (count > 3) {
                marker.setIcon('resources/img/pin_orange_default.png');
            }
            else {
                marker.setIcon('resources/img/pin_orange_alert.png');
            }
            google.maps.event.addListener(marker, 'mouseover', (function (marker, i) {
                return function () {
                    infoBubble.setContent(location + "<br />Ticket : " + ticket);
                    infoBubble.open(map, marker);
                }
            })(marker, i));

            google.maps.event.addListener(marker, 'mouseout', (function (marker, i) {
                return function () {
                    infoBubble.close();
                }
            })(marker, i));
        }

    });
});
