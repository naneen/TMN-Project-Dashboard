//<![CDATA[
$(function(){jQuery(document).ready(function () {
    var map;
    //var markerArr = [];

    var locationArray = [
        ['Central', 13.777699,100.476291, 4],
        ['AIA', 13.764197002592573, 100.56806197594256, 5],
        ['Lotus', 13.912447,100.496309, 3],
        ['Fashion Island',13.825877,100.678959, 2],
        ['Future Rangsit', 13.989174,100.617912, 1]
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
                               "stylers": [
                                   {
                                       "hue": "#16a085"
                                   },
                                   {
                                       "saturation": 0
                                   }
                               ]
                           },
                           {
                               "featureType": "road",
                               "elementType": "geometry",
                               "stylers": [
                                   {
                                       "lightness": 100
                                   },
                                   {
                                       "visibility": "simplified"
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
                    infoBubble.setContent(locationArray[i][0]);
                    infoBubble.open(map, marker);
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

