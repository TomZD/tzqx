define('components/map-markersManage.js', function(require, exports, module){ var util = require("components/util");
var template=require("lib/template");
//获取任一点天气
var tplMax = "<div class=\"m-map-maxpop\">\r\n\t<div class=\"site\">\r\n        当前位置：<%=area%> <span class=\"map_time\">更新时间：<%=actual.observTime%></span>\r\n    </div>\r\n   \t<div class=\"m-map-maxpop-left\">\r\n\t\t<img src=\"/static/images/ico-weather/d<%=actual.weatherCode%>.png\" \r\n\t\ttitle=\"<%=actual.weather || actual.weatherName%>\">\r\n\t\t<div class=\"temp\">\r\n\t        <%=actual.stationTemp || actual.temp%>\r\n\t        <sup>℃</sup>\r\n\t    </div>\r\n   \t</div>\r\n   \t<div class=\"m-map-maxpop-right\">\r\n\t\t<div class=\"un\">\r\n\t\t\t<%=actual.windDirection%>\r\n\t\t\t<%=actual.windLevel%>级\r\n\t\t</div>\r\n\t\t<div class=\"un\">能见度：<%=actual.visi%>米</div>\r\n\t\t<div class=\"un\">相对湿度：<%=actual.rh%>%</div>\r\n\t\t<%if(actual.rain&&actual.rain!=\"0\"){%>\r\n\t\t\t<div class=\"un\">近1小时降水：<%=actual.rain%>mm</div>\r\n\t\t<%}%>\r\n\t\t<%if(actual['24HourRain']&&actual['24HourRain']!=\"0\"){%>\r\n\t\t\t<div class=\"un\">近24小时降水：<%=actual['24HourRain']%>mm</div>\r\n\t\t<%}%>\r\n\t\t<div class=\"aqi-pm\">\r\n\t\t\t<a class=\"m-aqi m-aqi-lv<%=actual.aqiLevelVal%>\">\r\n\t\t        <b>AQI</b><span><%=actual.aqi%> <%=actual.aqiLevel%></span>\r\n\t\t    </a>\r\n\t\t    <%\r\n\t\t        var pm=actual.pm25;\r\n\t\t        if(pm){\r\n\t\t    %>\r\n\t\t        <a class=\"m-aqi m-aqi-lv3\">\r\n\t\t            <b>PM2.5</b><span><%=pm%>μg/m³</span>\r\n\t\t        </a>\r\n\t\t    <%}%>\r\n\t    </div>\r\n   \t</div>\r\n\r\n</div>";
var tplMin = "<div class='m-map-minpop'>\r\n\t<%=area%>\r\n\t<img \r\n\t\tsrc=\"/static/images/ico-weather/d<%=actual.weatherCode%>.png\" \r\n\t\ttitle=\"<%=actual.weatherName%>\" \r\n\t\tsrc=\"<%=actual.weatherName%>\"\r\n\t\tclass=\"img-weather\"\r\n\t>\r\n\t<%=actual.temp%>℃\r\n\t<%\r\n\t\tif(warns && warns.length){\r\n\t\t\tfor(var i=0,l=warns.length;i<l;i++){\r\n\t\t\t\tvar n=warns[i];\r\n\t%>\r\n\t\t<img src=\"<%=n.url%>\" class=\"img-warn\" title=\"<%=n.type%><%=n.level%>\"/>\r\n\t<%\r\n\t\t\t}\r\n\t\t}\r\n\t%>\r\n</div>";
var tplUser = "<div class='m-map-minpop'>我的位置</div>";
var tplLoad = "<div class=\"m-map-loadpop\">\r\n\tloading...\r\n</div>";

// var testData = {
//     "Status": 1,
//     "Result": {
//         "area": "浙江省宁波市江东区中山东路589号",
//         "actual": {
//             "time":"23:45",
//             "weatherCode": "00",
//             "weatherName": "晴",
//             "temp": 22.7,
//             "windExDirection": "东风",
//             "windDirection": "北风",
//             "windExVelocity": 3.5,
//             "windExLevel": 3,
//             "windVelocity": 0.7,
//             "windLevel": 1,
//             "visi": 14857,
//             "rain": 0.0,
//             "rh": 81.0,
//             "slp": 1014.01,

//             aqi: 64,
//             aqiLevel: "良",
//             pm25: 40,
//             "stationId": "58562",
//             "stationTemp": 22.5
//         }
//     }
// };

/**
 * 获取经纬度
 * @param  {object} marker 标注
 * @return {object}        经纬度
 */
var getLonlat = function(marker) {
    var ll = marker.lonlat;
    return marker.map.tanslateLonLat(new OpenLayers.LonLat(ll.lon, ll.lat), true);
};

/**
 * 标注管理器
 * @param {object} gis 地图实例
 */
var MarkersManage = function(gis) {
    this.gis = gis;
    this.markers = gis.addMarkers(); //创建标注层

    this.disable = 0; //0 区县标注和任意标注互斥（只显示其一），1 全部隐藏
    this.markerCountryStatus = "show"; //??待优化
};

/**
 * 显示弹框详情
 */
MarkersManage.prototype.showPopup = function(lon, lat, offsetX, offsetY, data) {
    var that = this;
    //创建弹框
    if (!this.popup) {
        this.popup = this.gis.addPopup(lon, lat, "loading", offsetX, offsetY, true, function() {
            this.hide();

            var lonlat = getLonlat(this.marker);
            if (this.marker && this.marker.popup) {
                var html, offsetX, offsetY;
                if (this.marker == that.markerDrag) {
                    html = tplUser;
                    offsetX = 0;
                    offsetY = -26;
                } else {
                    html = template.compile(tplMin)(this.marker.json);
                    offsetX = 0;
                    offsetY = -8;
                }
                var popup = this.marker.popup;
                popup.setContentHTML(html);
                popup.show();
                popup.setLonlat(lonlat.lon, lonlat.lat, offsetX, offsetY);
            }
        });
        this.popup.div.style.zIndex = 900;
        this.popup.closeOnMove = true;
    }

    //待优化
    if (this.popup.marker != that.markerDrag && this.popup.marker) {
        var lonlat = getLonlat(this.popup.marker);
        var html, offsetX, offsetY;
        html = template.compile(tplMin)(this.popup.marker.json);
        offsetX = 0;
        offsetY = -8;
        var popup = this.popup.marker.popup;
        popup.setContentHTML(html);
        popup.show();
        popup.setLonlat(lonlat.lon, lonlat.lat, offsetX, offsetY);
    }

    var html = data ? template.compile(tplMax)(data) : tplLoad;
    this.popup.setContentHTML(html);
    this.popup.show();
    this.popup.setLonlat(lon, lat, offsetX, offsetY);
};

/**
 * 隐藏弹框详情
 */
MarkersManage.prototype.hidePopup = function() {
    if (this.popup) {
        this.popup.hide();
    }
};

/**
 * 创建可拖动的任意点标注
 * @param  {number}   lon      经度
 * @param  {number}   lat      维度
 */
MarkersManage.prototype.creatMarkerDrag = function(lon, lat) {
    var that = this;
    lon = lon || 0;
    lat = lat || 0;

    //可拖动的点
    this.markerDrag = this.gis.addMarker({
        url: "/static/images/marker.png",
        width: 20,
        height: 26,
        lon: lon,
        lat: lat,
        offsetX: -10,
        offsetY: -26
    }, this.markers)[0];

    var popup = that.gis.addPopup(lon, lat, tplUser, 0, -26, false);

    this.markerDrag.popup = popup;

    this.gis.markerDrag(this.markerDrag, {
        onClick: function() {
            var lonlat = getLonlat(this.marker);
            that.showMarkerDrag(lonlat.lon, lonlat.lat);
        },
        onStart: function() {
            this.marker.popup.hide();
            that.hidePopup();
        },
        onStop: function() {
            var lonlat = getLonlat(this.marker);
            that.showMarkerDrag(lonlat.lon, lonlat.lat);
        }
    });
};

/**
 * 加载任意点天气数据
 * @param  {number}   lon      经度
 * @param  {number}   lat      维度
 * @param  {string}   name     地区名
 */
MarkersManage.prototype.showMarkerDrag = function(lon, lat, name) {
    if (!this.markerDrag) {
        this.creatMarkerDrag(lon, lat);
    } else {
        this.markerDrag.moveTo(
            this.gis.map.getLayerPxFromLonLat(
                this.gis.map.tanslateLonLat(new OpenLayers.LonLat(lon, lat))
            )
        );
        this.markerDrag.display(1);
    }

    this.markerDrag.popup.hide();
    this.showPopup(lon, lat, 0, -26);
    this.popup.marker = this.markerDrag;

    //test
    // var data = testData.Result;

    // if (name !== null) {
    //     data.area = name;
    // }
    // data.actual.aqiLevelVal = util.aqiLevelVal(data.actual.aqiLevel);
    // this.showPopup(lon, lat, 0, -26, data);

    // return;

    var that = this;
    if (this.ajax) {
        this.ajax.abort();
        this.ajax = null;
    }
    this.ajax = $.ajax({
        url: serverUrl + "/data.ashx?method=getWeatherByLonlat&lonlat=" + lon + "," + lat,
        dataType: "jsonp",
        success: function(json) {
            if (json && json.Status) {
                var data = json.Result;

                if (name !== null) {
                    data.area = name;
                }
                that.showPopup(lon, lat, 0, -26, data);
            } else {
                // alert("获取天气失败");
            } 
            data.actual.aqiLevelVal = util.aqiLevelVal(data.actual.aqiLevel);
        },
        error: function(message) {
            // alert("获取天气失败");
        }
    });
};

/**
 * 隐藏任意点标注
 */
MarkersManage.prototype.hideMarkerDrag = function() {
    if (this.markerDrag) {
        this.markerDrag.display();
        this.markerDrag.popup.hide();

        if (this.popup.marker == this.markerDrag) {
            this.popup.hide();
            this.popup.marker = null;
        }
    }
};

/**
 * 创建区县天气标注
 * @param  {array}    actualData 区县数据
 * @param  {array}    warnsData  预警信号
 * @param  {Function} callback   回调函数
 */
MarkersManage.prototype.creatMarkerCountry = function(dataActual, dataWarns, callback) {
    if (!dataActual) return;

    var that = this;

    this.markerCountry = [];

    $.each(dataActual, function(i, n) {
        var arr = [];
        var area = n.area;

        //合并预警数据
        if (dataWarns) {
            $.each(dataWarns, function(i, n) {
                if (n.publishstation == area) {
                    arr.push(n);
                }
            });

            n.warns = arr; 
        }
        n.actual.aqiLevelVal = util.aqiLevelVal(n.actual.aqiLevel);
        //创建标注
        var content = template.compile(tplMin)(n);
        var lonlat = n.lonlat.split(",");
        var lon = lonlat[0];
        var lat = lonlat[1];
        var popup = that.gis.addPopup(lon, lat, content, 0, -8, false, null, true);

        var marker = that.gis.addMarker({
            url: "/static/images/dot.png",
            width: 16,
            height: 16,
            lon: lon,
            lat: lat,
            offsetX: -8,
            offsetY: -8,
            content: "cm",
            ev: callback || function() {
                popup.hide();
                that.showPopup(lon, lat, 0, -8, n);
                that.popup.marker = this;
            }
        }, that.markers)[0];

        $(popup.div).bind("click", function() {
            popup.hide();
            that.showPopup(lon, lat, 0, -8, n);
            that.popup.marker = marker;
        });

        marker.popup = popup;
        marker.json = n;

        that.markerCountry.push(marker);
    });

    //注册地图事件
    this.gis.map.events.on({
        "zoomend": function() {
            if (that.markerCountryStatus == "hide") return;

            var zoom = this.getZoom();
            if (zoom < 8) {
                // if (zoom < 5) {
                //     $.each(that.markerCountry, function(i, n) {
                //         n.display();
                //         n.popup.hide();
                //         that.popup && that.popup.hide();
                //     });
                // } else {
                    $.each(that.markerCountry, function(i, n) {
                        if (n.json.area != "鄞州") {
                            n.display();
                            n.popup.hide();
                            that.popup && that.popup.hide();
                        } else {
                            n.display(1);
                            n.popup.show();
                        }
                    });
                // }
            } else {
                if (zoom > 10) {
                    $.each(that.markerCountry, function(i, n) {
                        n.display();
                        n.popup.hide();
                        that.popup && that.popup.hide();
                    });
                } else {
                    $.each(that.markerCountry, function(i, n) {
                        n.display(1);
                        n.popup.show();
                    });
                }
            }
        }
    });
};

/**
 * 隐藏区县标注
 */
MarkersManage.prototype.hideMarkerCountry = function() {
    this.markerCountryStatus = "hide";

    if (this.markerCountry && this.markerCountry.length) {
        $.each(this.markerCountry, function(i, n) {
            n.display();
            n.popup.hide();
        });
    }
};

/**
 * 显示区县标注
 */
MarkersManage.prototype.showMarkerCountry = function() {
    this.markerCountryStatus = "show";

    if (this.markerCountry && this.markerCountry.length) {
        $.each(this.markerCountry, function(i, n) {
            n.display(1);
            n.popup.show();
        });
    }
};

return function(gis) {
    return new MarkersManage(gis);
};
 
});