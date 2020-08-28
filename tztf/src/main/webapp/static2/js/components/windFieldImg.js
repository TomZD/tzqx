//风场数据-to-java
define('components/windFieldImg.js', function(require, exports, module) {
    var M4 = require("../lib/gigis/draw/m4");
    var thatG;
    var getWindFieldImg = OpenLayers.Class(M4, {
        //解析四类格式
        formart: function(data) {
        
            return {
                group: data.Data,
                num: {
                    lon: data.LonNum,
                    lat: data.LatNum
                },
                start: this.map.tanslateLonLat(new OpenLayers.LonLat(data.StartLon, data.StartLat)),
                end: this.map.tanslateLonLat(new OpenLayers.LonLat(data.EndLon, data.EndLat))
            };
        },
        moveTo: function(bounds, zoomChanged, dragging) {
            this.lonlats = this.lonlats || (this.data && this.formart(this.data));
            thatG = this;
            zoomChanged && this.calcData();
            this.changeRange();
            OpenLayers.Layer.Vector.prototype.moveTo.apply(this, arguments);
        },
        changeRange: function(value) {
            changeRangeT();
        },
        setStyle: function(newValue, isSetColor) {
            var zoom = this.map.zoom;
            var pointRadius = 10;
            
            var layer_style = $.extend(true, {}, this.style);
            layer_style.pointRadius = pointRadius;
            if (isSetColor) {
                var color = "rgba(0, 0, 0, 0)";
                var colorValue;
                var isolineColors = this.map.isolineColors;
                for (var k = 0; k <= isolineColors.length - 1; k++) {
                    var colorV = isolineColors[k];
                    if (parseFloat(newValue) >= parseFloat(colorV.value)) {
                        colorValue = colorV.value;
                        color = "rgb(" + colorV.color + ")";
                        break;
                    }
                }
                // 取最大值
                if (color == 'rgba(0, 0, 0, 0)') {
                    colorValue = isolineColors[k - 1].value;
                    color = "rgb(" + isolineColors[k - 1].color + ")";
                }
                if (color == "rgb(255,255,255)") {
                    color = "rgba(255,255,255,0)";
                }
                layer_style.fillColor = color;
                layer_style.strokeColor = color;
                // layer_style.strokeColor = "#fff";
                if (this.map.fillGrid) {
                    layer_style.fillOpacity = 0.5;
                    // layer_style.strokeOpacity=0.6;
                }
            }
            return layer_style;
        },
        calcData: function() {
            calcDataT();
        },
        CLASS_NAME: "Gi.DrawWindFiledImg"
    });

    function calcDataT() {
        var lonlats = thatG.lonlats;
        if (!lonlats) return;
        var arr = [];
        //计算间隔数
        var group = lonlats.group;
        var tl = thatG.map.getPixelFromLonLat(lonlats.start);
        var br = thatG.map.getPixelFromLonLat(lonlats.end);
        var num = lonlats.num;
        var numLat = num.lat;
        var numLon = num.lon;
        var IntervalNumLon = Math.round(numLon * 50 / (br.x - tl.x)) || 1;
        var IntervalNumLat = Math.round(numLat * 50 / (br.y - tl.y)) || 1;

        if (thatG.map.specGrid) {
            for (var i = 0; i < numLat; i++) {

                if (i % IntervalNumLat == 0) {
                    for (var j = 0; j < numLon; j++) {
                        var d = group[i][j];
                        if (j % IntervalNumLon == 0) {
                            d.showFlag = true;
                        } else {
                            // 不显示文本
                            d.showFlag = false;
                        }
                        d && arr.push(d);
                    }
                } else {
                    //过滤纬度值：不显示当前value值
                    for (var j = 0; j < numLon; j++) {
                        var d = group[i][j];
                        //过滤经度值：不显示当前value值
                        d.showFlag = false; //不显示文本
                        d && arr.push(d);
                    }
                }
            };
        } else {
            for (var i = 0; i < numLat; i++) {
                if (i % IntervalNumLat == 0) {
                    for (var j = 0; j < numLon; j++) {
                        if (j % IntervalNumLon == 0) {
                            var d = group[i][j];
//                            d.showFlag = true;
                            d && arr.push(d);
                        }
                    }
                }
            }
        }
        thatG.data = arr;
    }

    function changeRangeT() {
        // 地图change时调用
        thatG.removeAllFeatures(); //清空
        thatG.setRange();
        var drawArrayCorrect = thatG.map.drawArrayCorrect;
        var features = [];
        var count = 0;
        // 范围过滤
        var bounds = thatG.map.calculateBounds();
        for (var i = 0, l = thatG.data.length; i < l; i++) {
            var d = thatG.data[i];
            var type = d.Img;
            var lonlat = thatG.map.tanslateLonLat(new OpenLayers.LonLat(d.Lon,d.Lat));
            var isSetStyle = false;

            var layer_style = thatG.setStyle(type, thatG.map.fillFeature);
            if (layer_style.label && d.showFlag) {
                layer_style.label = type + "";
                count++;
            }
            if (layer_style.backgroundGraphic) {
                // var img = parseInt(type);
                // layer_style.backgroundGraphic = layer_style.backgroundGraphic.replace("bgimg", img);
                layer_style.backgroundGraphic+=type;

            }
            // if (type != null && bounds.contains(lonlat.lon, lonlat.lat)) 
            {
                var feature = new OpenLayers.Feature.Vector(
                    new OpenLayers.Geometry.Point(lonlat.lon, lonlat.lat), //Polygon Point
                    null, layer_style
                );
                if (thatG.value != null) {
                    type = thatG.compareRange(type) ? type : "";
                }
                feature.attributes = {
                    type: type,
                    lonlatM: d.lonlatM,
                    style: layer_style
                };
                features.push(feature);
            }
        }

        thatG.addFeatures(features);
        thatG.allFeatures = features;

    }
    return function(name, data, styles, invalidValue) {
      
        var layer = new getWindFieldImg(name || "风场", {
            data: data,
            invalidValue: invalidValue,
            style: styles
        });

        // this.addLayer(layer);
        // layer.setZIndex(100);
        layer.calcDataT = calcDataT;
        layer.changeRangeT = changeRangeT;
        return layer;
    }
});
