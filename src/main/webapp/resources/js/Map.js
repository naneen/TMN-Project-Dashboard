//<![CDATA[





$(function(){jQuery(document).ready(function () {
    var map;
    //var markerArr = [];

    var count=0;
    $.getJSON( "complaintTicket", function(json) {
        initialize();
        var lon,lat,ticket;
        for(i=0;i<json.length;i+=5){
             lon=parseFloat(json[i+1]);
             lat=parseFloat(json[i+2]);
            ticket=parseInt(json[i+4]);



            setMarkers(map,json[i], lon,lat,ticket);



        }
    });



    function initialize() {
        var mapOptions = {
            zoom: 13,
            center: new google.maps.LatLng(13.7557261,100.4988848),
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




    }

    function setMarkers(map,location, lon,lat,ticket) {

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
            if( count>3 ) {
                marker.setIcon('resources/img/pin_orange_default.png');
            }
            else{
                marker.setIcon('resources/img/pin_orange_alert.png');
            }
            google.maps.event.addListener(marker, 'mouseover', (function(marker, i) {
                return function() {
                    infoBubble.setContent(location + "<br />Ticket : "+ticket );
                    infoBubble.open(map, marker);
                }
            })(marker, i));

            google.maps.event.addListener(marker, 'mouseout', (function(marker, i) {
                return function() {
                    infoBubble.close();
                }
            })(marker, i));
        }


    //google.maps.event.addDomListener(window, 'load', initialize);
    });
});//]]>

