var localData=[];
define(function(require) {
    var distanceMapClickNum = 0;//相似路径时点击地图的次数
    var moveTo = require("../control/moveto");
    var getCoordinate = function(lon, lat, map) {
        return new OpenLayers.LonLat(lon, lat).transform(map.getProjection(), map.projection)
    };
    return function (id, imgUrl, zIndex, type,callback) {
        if (type == "Radar") {
            var that = this;
            var markerLayer = that.addMarkers();
            var els = [];
            els.push(document.getElementById("geometryPs"));
            // els.push(document.getElementById("distanceCalc"));
            // els.push(document.getElementById("polygonArea"));
            // els.push(document.getElementById("mapMove"));
            // els.push(document.getElementById("clearFeature"));

            var style = new OpenLayers.Style({
                fillColor: "#fff",
                strokeColor: "#fd8044",
                strokeWidth: 3,
                fillOpacity: 0.6,
                pointRadius: 6
            });
            var layer;
            var onMapClick = function () {
                var menuVal = document.getElementById("btnVal").value;
                if (!layer)
                    layer = that.map.getLayersByName("绘图工具")[0];
                if (layer) {
                    if (layer.features.length > 0) {
                        switch (menuVal) {
                            case "distance":
                                var geom = layer.features[layer.features.length - 1].geometry;
                                if (geom.id.indexOf('LineString') != -1) {//获取画的最后一条线
                                    var components = geom.components;
                                    var points = '';
                                    $.each(components, function (i, item) {
                                        points += item.x + ',' + item.y + ';';
                                        if (i == 1) {
                                            return false;//只取前两个点
                                        }
                                    });
                                    points = points.substr(0, points.length - 1);
                                    if (callback && typeof callback == "function") {
                                        callback(menuVal, points, layer);
                                    }
                                }
                                break;
                            case "polygon":
                                var geom = layer.features[layer.features.length - 1].geometry;
                                if (geom.id.indexOf('Polygon') != -1) {//获取画的最后一条线
                                    //var geodesicArea = geom.getGeodesicArea("EPSG:4326");
                                    var geodesicArea = geom.getArea();
                                    geodesicArea = geodesicArea / 1000000.0;
                                    geodesicArea = geodesicArea.toFixed(2);
                                    measureResultShow("面积约为：" + geodesicArea + " 平方千米");
                                }
                                break;
                            default:
                                break;
                        }

                    }
                }
            }

            var renderer = OpenLayers.Util.getParameters(window.location.href).renderer;
            renderer = (renderer) ? [renderer] : OpenLayers.Layer.Vector.prototype.renderers;

            var DrawControls;

            for (var i = 0, len = els.length; i < len; i++) {
                var el = els[i];
                el.onclick = function () {
                    if (!layer) {
                        layer = that.map.getLayersByName("绘图工具")[0];
                        if (!layer) {
                            layer = new OpenLayers.Layer.Vector("绘图工具", {
                                styleMap: new OpenLayers.StyleMap(style),
                                zIndex: zIndex
                            });
                            //layer.events.register("click", layer, onMapClick);
                            that.map.addLayer(layer);
                        }

                        DrawControls = {
                            geometryPs: new OpenLayers.Control.DrawFeature(layer,
                                OpenLayers.Handler.Path),
                            line: new OpenLayers.Control.DrawFeature(layer,
                                OpenLayers.Handler.Path),

                            polygon: new OpenLayers.Control.DrawFeature(layer,
                                OpenLayers.Handler.Polygon),

                            distance: new OpenLayers.Control.Measure(
                                OpenLayers.Handler.Path, {
                                    persist: true,
                                    immediate: false,
                                    handlerOptions: {
                                        layerOptions: {
                                            renderers: renderer,
                                            styleMap: new OpenLayers.StyleMap(style)
                                        }
                                    }
                                }
                            ),
                            drag: new OpenLayers.Control.DragFeature(layer)
                        }

                        for (var key in DrawControls) {
                            control = DrawControls[key];
                            if (key == "distance") {
                                control.events.on({
                                    "measure": handleMeasurements,//双击
                                    "measurepartial": seperateMeasurement//单击
                                });
                            }
                            that.map.addControl(control);
                        }
                    }
                    //else
                    //{
                    //    that.map.layers.pop();
                    //}
                    if (markerLayer) {
                        markerLayer.clearMarkers();
                    }
                    var val = this.getAttribute("data-val");
                    if(val ==='drag'){
                        this.setAttribute("data-val","distance");
                        pathPoint=[];
                        distanceMapClickNum=0;
                    }else{
                        this.setAttribute("data-val","drag");
                    }
                    if (val == "cancel") {
                        if (layer) {
                            layer.removeAllFeatures();
                            if (DrawControls['line'].handler.layer)
                                DrawControls['line'].handler.layer.removeAllFeatures();
                            return;
                        }
                    }
                    if (layer) {
                        layer.removeAllFeatures();
                    }
                    for (var key in DrawControls) {
                        layer.removeAllFeatures();
                        var control = DrawControls[key];
                        if (key == val) {
                            if (!control.events.listeners.featureadded)
                                control.events.register("featureadded", control, onMapClick);
                            document.getElementById("btnVal").value = val;
                            control.activate();
                            if (control.handler)
                                control.handler.stopDown = true;

                        } else {
                            control.deactivate();
                        }
                    }
                }
            }
        } else {
            var that = this;
            var container = document.getElementById(id);
            var containerClass = container.className;
            var markerLayer = that.addMarkers();
            var str = "<ul><li data-val='point' class='Gitool_li'><em class='gl_btn01'></em><p class='gl_sp'>点</p></li>";
            str += "<li data-val='line' class='Gitool_li'><em class='gl_btn02'></em><p class='gl_sp'>线</p></li><li data-val='rectangle' class='Gitool_li'><em class='gl_btn03'></em><p class='gl_sp'>矩形</p></li>";
            str += "<li data-val='polygon' class='Gitool_li'><em class='gl_btn04'></em><p class='gl_sp'>多边形</p></li><li data-val='circle' class='Gitool_li'><em class='gl_btn05'></em><p class='gl_sp'>圆</p></li>";
            str += "<li data-val='drag' class='Gitool_li'><em class='gl_btn06'></em><p class='gl_sp'>拖曳</p></li>";//<li data-val='distance'><span class='ico-9' >测距</span></li>
            str += "<li data-val='cancel' class='Gitool_li'><em class='gl_btn07'></em><p class='gl_sp'>清除</p></li></ul>";
            container.innerHTML = str;
            //不需要线条和点功能时加载
            // hidePointAndLine();
            function hidePointAndLine() {
                $("#" + id).find("li").eq(0).hide();
                $("#" + id).find("li").eq(1).hide();
            }
            if (containerClass.indexOf("Gitool") < 0) {
                container.className = containerClass + "Gitool";
            }

            var toolList = container.getElementsByTagName("ul")[0];
            var els = toolList.getElementsByTagName("li");


            var style = new OpenLayers.Style({
                fillColor: "#fff",
                strokeColor: "#fd8044",
                strokeWidth: 3,
                fillOpacity: 0.6,
                pointRadius: 6
            });
            var layer

            var renderer = OpenLayers.Util.getParameters(window.location.href).renderer;
            renderer = (renderer) ? [renderer] : OpenLayers.Layer.Vector.prototype.renderers;

            var DrawControls;

            for (var i = 0, len = els.length; i < len; i++) {
                var el = els[i];
                el.onclick = function () {
                    if (!layer) {
                        layer = that.map.getLayersByName("绘图工具")[0];
                        if (!layer) {
                            layer = new OpenLayers.Layer.Vector("绘图工具", {
                                styleMap: new OpenLayers.StyleMap(style),
                                zIndex: zIndex
                            });
                            that.map.addLayer(layer);
                        }

                        DrawControls = {
                            point: new OpenLayers.Control.DrawFeature(layer,
                                OpenLayers.Handler.Point),
                            line: new OpenLayers.Control.DrawFeature(layer,
                                OpenLayers.Handler.Path),
                            rectangle: new OpenLayers.Control.DrawFeature(layer,
                                OpenLayers.Handler.RegularPolygon, {
                                    handlerOptions: {
                                        sides: 4,
                                        irregular: true
                                    }
                                }
                            ),
                            polygon: new OpenLayers.Control.DrawFeature(layer,
                                OpenLayers.Handler.Polygon),
                            circle: new OpenLayers.Control.DrawFeature(layer,
                                OpenLayers.Handler.RegularPolygon, {
                                    handlerOptions: {
                                        sides: 40
                                    }
                                }),
                            distance: new OpenLayers.Control.Measure(
                                OpenLayers.Handler.Path, {
                                    persist: true,
                                    immediate: false,
                                    handlerOptions: {
                                        layerOptions: {
                                            renderers: renderer,
                                            styleMap: new OpenLayers.StyleMap(style)
                                        }
                                    }
                                }
                            ),
                            drag: new OpenLayers.Control.DragFeature(layer)
                        }

                        for (var key in DrawControls) {
                            control = DrawControls[key];
                            if (key == "distance") {
                                control.events.on({
                                    "measure": handleMeasurements,
                                    "measurepartial": seperateMeasurement
                                });
                            }
                            that.map.addControl(control);
                        }
                    }
                    if (markerLayer) {
                        markerLayer.clearMarkers();
                    }
                    var val = this.getAttribute("data-val");
                    if (val == "cancel") {
                        if (layer) {
                            layer.removeAllFeatures();
                            return;
                        }
                    }
                    for (var key in DrawControls) {
                        var control = DrawControls[key];
                        if (key == val) {
                            control.activate();
                        } else {
                            control.deactivate();
                        }
                    }
                }
            }
        }
        
        //最后总距离

        function handleMeasurements(event) {
            var geometry = event.geometry;
            var units = event.units;
            var measure = event.measure;
            var len = geometry.components.length;
            var pos = geometry.components[len - 1];
            var ll = getCoordinate(pos.x, pos.y, that.map);
            var out = "";
            out += measure.toFixed(3) + " " + units;
            that.calcDistance = "on";
            if(distanceMapClickNum==1){
                pathLength = out;
                pathPoint.push(ll);
            }
            if (callback && typeof callback == "function") {
                callback("",pathLength,pathPoint, layer);
            }
            pathPoint=[];
            distanceMapClickNum=0;
            pathLength="";
        }

        //时时测距
        var pathPoint=[];
        var pathLength="";
        function seperateMeasurement(event) {
            if (that.calcDistance == "on") {
                if (markerLayer) {
                    markerLayer.clearMarkers();
                }
                that.calcDistance = "off";
            }
            var measure = event.measure;
            var units = event.units;
            var geometry = event.geometry;
            var len = geometry.components.length;
            var pos = geometry.components[len - 1];
            var ll = getCoordinate(pos.x, pos.y, that.map);
            var out = "";
            out += measure.toFixed(1) + " " + units;
            distanceMapClickNum++;
            if(distanceMapClickNum <= 2){//单击次数少于2次时
                pathPoint.push(ll);
            }
            if(distanceMapClickNum == 2){
                pathLength = out;
            }
        }
    };
});

