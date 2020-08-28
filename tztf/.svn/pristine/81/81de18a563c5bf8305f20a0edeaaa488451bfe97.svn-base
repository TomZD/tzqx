define(function(require) {
    var moveTo = require("../control/moveto");
    var getCoordinate = function(lon, lat, map) {
        new OpenLayers.LonLat(lon, lat).transform(map.getProjection(), map.projection)
    };
    return function(id, imgUrl, zIndex, callback) {
        var that = this;
        var container = document.getElementById(id);
        var containerClass = container.className;
        var markerLayer = that.addMarkers();
        var str = "<i class='toolbar_btn06'></i><span>工具</span>";
        str += "<ul><li data-val='point' class='Gitool_li'><em class='gl_btn01'></em><p class='gl_sp'>点</p></li>";
        str += "<li data-val='line' class='Gitool_li'><em class='gl_btn02'></em><p class='gl_sp'>线</p></li>";
        str += "<li data-val='distance' class='Gitool_li'><em class='gl_btn02'></em><p class='gl_sp'>测距</p></li>";
        str += "<li data-val='rectangle' class='Gitool_li'><em class='gl_btn03'></em><p class='gl_sp'>矩形/面积</p></li>";
        str += "<li data-val='polygon' class='Gitool_li'><em class='gl_btn04'></em><p class='gl_sp'>多边形/面积</p></li>";
        str += "<li data-val='circle' class='Gitool_li'><em class='gl_btn05'></em><p class='gl_sp'>圆形/面积</p></li>";
        str += "<li data-val='drag' class='Gitool_li'><em class='gl_btn06'></em><p class='gl_sp'>拖曳</p></li>";
        str += "<li data-val='cancel' class='Gitool_li'><em class='gl_btn07'></em><p class='gl_sp'>清除</p></li></ul>";
        container.innerHTML = str;


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
        //工具条显隐
        container.onmousemove = function() {
            var cla = toolList.className;
            if (cla.indexOf("show") < 0) {
                toolList.className = cla + "show";
            }
        };
        container.onmouseout = function() {
            var cla = toolList.className;
            toolList.className = cla.replace("show", "");
        }

        toolList.onmouseout = function() {
            var cla = toolList.className;
            toolList.className = cla.replace("show", "");
        };
        // 图层
        var layer;
        // 绘图对象
        var DrawControls;
        var renderer = OpenLayers.Util.getParameters(window.location.href).renderer;
        renderer = (renderer) ? [renderer] : OpenLayers.Layer.Vector.prototype.renderers;

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
                        immediate: true,
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
                // if(key!="drag"){
                    // 在标绘的时候如果不希望地图拖动可以调用
                //     //绘图时禁止拖拽
                //     control.handler.stopDown=true;
                // controls.handler.stopUp = true
                // }
                if (key == "distance") {
                    control.events.on({
                        "measure": handleMeasurements,
                        "measurepartial": seperateMeasurement
                    });
                }
                that.map.addControl(control);
            }
        }

        var keyType;
        // 绑定工具条事件
        for (var i = 0, len = els.length; i < len; i++) {
            var el = els[i];
            el.onclick = function() {
                // if (markerLayer) {
                //     markerLayer.clearMarkers();
                // }
                var val = this.getAttribute("data-val");
                if (val == "cancel") {
                    if (layer) {
                        layer.removeAllFeatures();
                        return;
                    }
                }
                // 启动、关闭绘图
                for (var key in DrawControls) {
                    var control = DrawControls[key];
                    if (key == val) {
                        keyType = key;
                        if (!control.events.listeners.featureadded)
                             control.events.register("featureadded", control, onMapClick);
                        control.activate();
                    } else {
                        control.deactivate();
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
            // that.addPointMarkers(ll.lon, ll.lat, {
            //         url: imgUrl,
            //         width: 16,
            //         height: 16
            //     }, "总长：" + out,
            //     null,
            //     null,
            //     markerLayer);
            // that.calcDistance = "on";
            if (callback && typeof callback == "function") {
                callback(keyType, measure.toFixed(2), layer);
            }
        }

        //时时测距
        function seperateMeasurement(event) {
            if (that.calcDistance == "on") {
                // if (markerLayer) {
                //     markerLayer.clearMarkers();
                // }
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
            console.log(out);
            // that.addPointMarkers(ll.lon, ll.lat, {
            //         url: imgUrl,
            //         width: 16,
            //         height: 16
            //     }, out,
            //     null,
            //     null,
            //     markerLayer);
        }

        //多边形面积,圆形面积，矩形面积
        var onMapClick = function() {
            if (layer) {
                if (layer.features.length > 0) {
                    switch (keyType) {
                        case "geometryPs":
                            var geom = layer.features[layer.features.length - 1].geometry;
                            if (geom.id.indexOf('LineString') != -1) { //获取画的最后一条线
                                var components = geom.components;
                                var points = '';
                                $.each(components, function(i, item) {
                                    points += item.x + ',' + item.y + ';';
                                    if (i == 1) {
                                        return false; //只取前两个点
                                    }
                                });
                                points = points.substr(0, points.length - 1);
                                radarTileChartData(points);
                            }
                            break;
                        case "polygon":
                        case "circle":
                        case "rectangle":
                            var geom = layer.features[layer.features.length - 1].geometry;
                            if (geom.id.indexOf('Polygon') != -1) { //获取画的最后一条线
                                var geodesicArea = geom.getGeodesicArea("EPSG:4326");
                                var geodesicArea = geom.getArea();
                                geodesicArea = geodesicArea / 1000000.0;
                                geodesicArea = geodesicArea.toFixed(2);
                                //面积约为：平方千米
                                if (callback && typeof callback == "function") {
                                    callback(keyType, geodesicArea, layer);
                                }
                            }
                            break;
                        case "line":
                            //获取画的最后一条线
                            var geom = layer.features[layer.features.length - 1].geometry;
                            var geodesicArea = geom.getGeodesicLength("EPSG:4326");
                            var geodesicArea = geom.getLength();
                            geodesicArea = geodesicArea / 1000.0;
                            geodesicArea = geodesicArea.toFixed(2);
                            //距离约为：平方千米
                            if (callback && typeof callback == "function") {

                                callback(keyType, geodesicArea, layer);
                            }
                            break;
                        case "point":
                            //获取点的经纬度坐标
                            var geom = layer.features[layer.features.length - 1].geometry;//获取最后一个点
                            debugger
                            var pos=that.tanslateLonLat(geom.x,geom.y,true);
                            $(".dataTool").find("span.pointx").html(pos.lon.toFixed(2));
                            $(".dataTool").find("span.pointy").html(pos.lat.toFixed(2));
                             break;
                    }

                }

            }

        }
    };
});
