//<![CDATA[
$(function(){jQuery(document).ready(function () {
    var map;
    //var markerArr = [];

    var locationArray = [
        ['Central', 13.777699,100.476291, 4, 34],
        ['AIA', 13.764197002592573, 100.56806197594256, 5, 2],
        ['Lotus', 13.912447,100.496309, 3],
        ['Fashion Island',13.825877,100.678959, 2, 27],
        ['Future Rangsit', 13.989174,100.617912, 1, 23]
    ];

    function initialize() {
        var mapOptions = {
            zoom: 10,
            center: new google.maps.LatLng(13.76717, 100.523186),
            disableDefaultUI: true,
            boxClass: "info-windows"
        }
        map = new google.maps.Map(document.getElementById('map'),
            mapOptions);

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

        setMarkers(map, locationArray);
    }

    function setMarkers(map) {
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
        for (i = 0; i < locationArray.length; i++) {
            marker = new google.maps.Marker({
                position: new google.maps.LatLng(locationArray[i][1], locationArray[i][2]),
                map: map
            });
            if( i%2==0 ) {
                marker.setIcon('resources/img/pin_orange.png');
            }
            else{
                marker.setIcon('resources/img/pin_caution1.png');
            }
            google.maps.event.addListener(marker, 'mouseover', (function(marker, i) {
                return function() {
                    infoBubble.setContent(locationArray[i][0] + "<br />Ticket : " + locationArray[i][4]);
                    infoBubble.open(map, marker);4
                }
            })(marker, i));

            google.maps.event.addListener(marker, 'mouseout', (function(marker, i) {
                return function() {
                    infoBubble.close();
                }
            })(marker, i));
        }
    }

    google.maps.event.addDomListener(window, 'load', initialize);
    });
});//]]>

