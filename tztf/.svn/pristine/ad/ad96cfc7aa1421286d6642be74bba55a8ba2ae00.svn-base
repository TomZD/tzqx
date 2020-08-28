define(function(require) {
    var moveTo = require("../control/moveto");
    var getCoordinate = function(lon, lat, map) {
        new OpenLayers.LonLat(lon, lat).transform(map.getProjection(), map.projection)
    };
    return function(id, imgUrl, zIndex) {
        var that = this;
        var container = document.getElementById(id);
        var containerClass = container.className;
        var markerLayer = that.addMarkersLayer();
        var str = "<span class='ico-1'>工具</span><ul><li data-val='point'><span class='ico-2' >点</span></li>";
        str += "<li data-val='line'><span class='ico-3' >线</span></li><li data-val='rectangle'><span class='ico-4' >矩形</span></li>";
        str += "<li data-val='polygon'><span class='ico-5' >多边形</span></li><li data-val='circle'><span class='ico-6' >圆</span></li>";
        str += "<li data-val='distance'><span class='ico-9' >测距</span></li><li data-val='drag'><span class='ico-7' >拖曳</span></li>";
        str += "<li data-val='cancel'><span class='ico-8' >清除</span></li></ul>";
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
        var layer

        var renderer = OpenLayers.Util.getParameters(window.location.href).renderer;
        renderer = (renderer) ? [renderer] : OpenLayers.Layer.Vector.prototype.renderers;

        var DrawControls;
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

        for (var i = 0, len = els.length; i < len; i++) {
            var el = els[i];
            el.onclick = function() {
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
            that.addPointMarkers(ll.lon, ll.lat, {
                    url: imgUrl,
                    width: 16,
                    height: 16
                }, "总长：" + out,
                null,
                null,
                markerLayer);
            that.calcDistance = "on";
        }

        //时时测距

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
            that.addPointMarkers(ll.lon, ll.lat, {
                    url: imgUrl,
                    width: 16,
                    height: 16
                }, out,
                null,
                null,
                markerLayer);
        }

    };
});