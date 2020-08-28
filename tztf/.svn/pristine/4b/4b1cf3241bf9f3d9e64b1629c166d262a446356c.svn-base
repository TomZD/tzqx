define(function (require) {
    var gis, markerlayer, onMouseover, onClick, bFilter, list = {};

    /**
     * 加载站点
     * @param  {array}  data  标注信息
     * @param  {string} type  类型
     */
    /*监测站展示*/
    var addStation = function (data, type, popup_name, icon, isShow) {
        var arr = [];
        
        //创建标注层
        if (!markerlayer) {
            markerlayer = gis.addMarkers();
        }

       

        //加载点
        $.each(data, function (i, n) {
            n.popup_name = popup_name;
            var str;
           
            var txt = "<img src=\"../../static/images/" + n.name + ".png\" />";
            
            var marker = gis.addMarker({
                icon: icon,
                position: "br",
                lon:n.Lontitude,
                lat:n.Latitude,
                content: n,
                text: "<div class='site_txt'>" + txt + "</div>",
            }, markerlayer)[0];

            marker.events.register("mouseover", marker, onMouseover);
            marker.events.register("click", marker, onClick);
            arr.push(marker);
        });

        //是否过滤
        if (bFilter) {
            filter(bFilter);
        }
        list[type] = list[type] || [];
        for (var i = 0; i < arr.length; i++) {
            list[type].push(arr[i]);
        }

    };

    /*警报*/
    var addLine = function (data, type, addWarning) {
        var arr = [];
        $.each(data, function (i, line) {
            var lines = gis.drawLine(line, {
                strokeColor: '#63c828',
                strokeWidth: 4,
                strokeOpacity: 0.9,
                addWarning: addWarning
            }, true);
            lines.show = function () {
                $(lines.div).show();
            }
            lines.hide = function () {
                $(lines.div).hide();
            }
            arr.push(lines);
        });
        list[type] = list[type] || [];
        for (var i = 0; i < arr.length; i++) {
            list[type].push(arr[i]);
        }
    }
    var addWarning = function (data, type, popup_name, icon, isShow) {
        var arr = [];
        
        //创建标注层
        if (!markerlayer) {
            markerlayer = gis.addMarkers();
        }


        //加载点
        $.each(data, function (i, n) {
            n.popup_name = popup_name;
           
            var txt = n.name;
            if (!isShow || txt == undefined) {
                txt = "";
            }
            var popupTxt = (txt==null||txt=='')?"":"<div class='popup-txt'>" + txt + "</div>";
            var marker = gis.addMarker({
                icon: icon,
                position: "br",
                text: popupTxt,
                lon: n.lon,
                lat: n.lat,
                content: n
            }, markerlayer)[0];
            arr.push(marker);
        });

        //鼠标移开事件
        function onMouseout() {
            $(".olPopup").css("display", "none");
        }
        //是否过滤
        if (bFilter) {
            filter(bFilter);
        }

        //list[type] = arr;
        list[type] = list[type] || [];
        for (var i = 0; i < arr.length; i++) {
            list[type].push(arr[i]);
        }
    };

    /*预报点*/
    var forecastSpot = function (data, type, popup_name, icon, isShow) {
        var arr = [];
        
        //创建标注层
        if (!markerlayer) {
            markerlayer = gis.addMarkers();
        }

        //加载点
        $.each(data, function (i, n) {
            n.popup_name = popup_name;
            var str;
            
            var marker = gis.addMarker({
                icon: icon,
                position: "br",
                text: "<div class='popup-txt'>" + txt + "</div>",
                lon: n.spotLon,
                lat: n.spotLat,
                content: n
            }, markerlayer)[0];
            arr.push(marker);
        });

        //是否过滤
        if (bFilter) {
            filter(bFilter);
        }
        list[type] = list[type] || [];
        for (var i = 0; i < arr.length; i++) {
            list[type].push(arr[i]);
        }
    };
    /* 清除站点 */
    var clear = function (type) {
        if (!markerlayer) return;

        var markers;
        if (type) {
            markers = list[type] || [];
            list[type] = null;
            $.each(markers, function (i, n) {
                markerlayer.removeMarker(n);
            });
        } else {
            markers = markerlayer.markers;
            markerlayer.clearMarkers();
            list = {};
        }
    };

    var showOrHide = function (isTrue) {
        var markers = list["micaps4Station"] || [];
        if (isTrue) {
            markerlayer.display(true);
        } else {
            markerlayer.display(false);
        }
    }
    /*过滤站点*/
    var filter = function (bl) {
        bFilter = bl;

        if (!markerlayer) return;

        $.each(markerlayer.markers, function (i, n) {
            if (!n.content.Select) {
                if (bFilter) {
                    n.display(false);
                } else {
                    n.display(true);
                }
            }
        });
    };

    return function (g, ev, ev_click) {
        gis = g;
        onMouseover = ev;
        onClick = ev_click;

        return {
            addStation: addStation,
            addWarning: addWarning,
            addLine: addLine,
            forecastSpot: forecastSpot,
            clear: clear,
            filter: filter,
            showOrHide: showOrHide,
            stationList: list
        };
    };
});
