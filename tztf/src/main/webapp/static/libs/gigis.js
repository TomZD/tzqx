/*
    @require libs/openLayers.js
    @require libs/template.js
    @require libs/gigis.less
 */
(function() {
    var MyHandler = OpenLayers.Class(OpenLayers.Handler.RegularPolygon, {
        irregular: true,
        down: function(evt) {
            this.control.layer.removeAllFeatures();
            OpenLayers.Handler.RegularPolygon.prototype.down.call(this, evt);
        }
    });
	
	var MyHandler2 = OpenLayers.Class(OpenLayers.Handler.Polygon, {
        irregular: true,
        down: function(evt) {
            this.control.layer.removeAllFeatures();
            OpenLayers.Handler.Polygon.prototype.down.call(this, evt);
        }
    });
    /* 工具栏 */
    var MyToolbar = OpenLayers.Class(OpenLayers.Control, {
        initialize: function(layer, options) {
            OpenLayers.Control.prototype.initialize.apply(this, [options]);

            this.controls = [
                new OpenLayers.Control.DrawFeature(layer, MyHandler),
                new OpenLayers.Control.DrawFeature(layer, MyHandler, {
                    handlerOptions: {
                        sides: 100
                    }
                }),
				new OpenLayers.Control.DrawFeature(layer, MyHandler2)
            ];
        },
        draw: function() {
            OpenLayers.Control.prototype.draw.apply(this, arguments);
            var control;
            for (var i = 0, len = this.controls.length; i < len; i++) {
                control = this.controls[i];
                if (control.autoActivate === true) {
                    control.autoActivate = false;
                    this.map.addControl(control);
                    control.autoActivate = true;
                } else {
                    this.map.addControl(control);
                    control.deactivate();
                }
            }
        },
        setStyle: function(status) {
            this.controls[0].layer.styleMap.styles.default.defaultStyle.fillColor = status ? "#df2d3a" : "#3d9cd4";
            this.controls[0].layer.styleMap.styles.default.defaultStyle.strokeColor = status ? "#df2d3a" : "#3d9cd4";
        },
        CLASS_NAME: "OpenLayers.Control.MyToolbar"
    });

    //弹层
    var Popup = OpenLayers.Class(OpenLayers.Popup, {
        initialize: function(id, lonlat, contentSize, contentHTML, closeBox, closeBoxCallback, options) {
            OpenLayers.Util.extend(this, options);
            if (id == null) {
                id = OpenLayers.Util.createUniqueID(this.CLASS_NAME + "_");
            }

            this.id = id;
            this.lonlat = lonlat;

            this.contentSize = (contentSize != null) ? contentSize : new OpenLayers.Size(
                OpenLayers.Popup.WIDTH,
                OpenLayers.Popup.HEIGHT);
            if (contentHTML != null) {
                this.contentHTML = contentHTML;
            }
            this.backgroundColor = OpenLayers.Popup.COLOR;
            this.opacity = OpenLayers.Popup.OPACITY;
            this.border = OpenLayers.Popup.BORDER;

            this.div = OpenLayers.Util.createDiv(this.id);
            this.div.className = this.displayClass;

            var groupDivId = this.id + "_GroupDiv";
            this.groupDiv = OpenLayers.Util.createDiv(groupDivId, null, null,
                null, "relative");

            var id = this.div.id + "_contentDiv";
            this.contentDiv = OpenLayers.Util.createDiv(id, null, this.contentSize.clone(),
                null, "relative");
            this.contentDiv.className = this.contentDisplayClass;
            this.groupDiv.appendChild(this.contentDiv);
            this.div.appendChild(this.groupDiv);

            if (closeBox) {
                this.addCloseBox(closeBoxCallback);
            }

            this.registerEvents();
        },
        moveTo: function(px) {
            px.x += this.offsetX || 0;
            px.y += this.offsetY || 0;
            OpenLayers.Popup.prototype.moveTo.apply(this, [px]);
        }
    });
    //核心
    var Core = OpenLayers.Class({
        initialize: function(id, options) {
            // 创建地图
            var map = this.map = new OpenLayers.Map(id, {
                displayProjection: "EPSG:4326"
            });

            //高德地图
            var layer = new OpenLayers.Layer.XYZ(
                "traffic",
                "http://webrd04.is.autonavi.com/appmaptile?lang=zh_cn&size=1&scale=1&style=7&x=${x}&y=${y}&z=${z}", {
                    wrapDateLine: true,
                    sphericalMercator: true,
                    isBaseLayer: true
                });
            map.addLayer(layer);
            map.setBaseLayer(layer);

            //地图中心
            options = options || {};

            this.map.setCenter(
                this.getLonLat(options.centerLon, options.centerLat),
                options.zoom
            );
        },
        //-----------经纬度
        getLonLat: function(lon, lat, isDisplay) {
            var lonlat;
            if (typeof lon == "object") {
                lonlat = lon;
                isDisplay = lat;
            } else {
                lonlat = new OpenLayers.LonLat(lon, lat);
            }

            var dPro = this.map.displayProjection, //输出投影
                bPro = this.map.getProjection(); //底图投影
            if (dPro != bPro) {
                if (isDisplay) {
                    return lonlat.transform(bPro, dPro);
                } else {
                    return lonlat.transform(dPro, bPro);
                }
            } else {
                return lonlat;
            }
        },
        //------------标注
        addMarker: function(lonlat, info) {
            var that = this;
            //标准层
            if (!this.markersLayer) {
                this.markersLayer = new OpenLayers.Layer.Markers();
                this.map.addLayer(this.markersLayer);
            }

            //图标
            var icon = new OpenLayers.Icon(
                "static/components/ui/icons/images/marker" + info.type + "-" + info.status + ".png",
                new OpenLayers.Size(40, 54),
                new OpenLayers.Pixel(-20, -54)
            );
            //创建
            var marker = new OpenLayers.Marker(lonlat, icon);
            marker.info = info;
            this.markersLayer.addMarker(marker);

            //点击事件
            marker.events.register("mousedown", marker, function(evt) {
                that.showInfoPopup(this.lonlat, this.info);
                OpenLayers.Event.stop(evt);
            });

            //影响范围
            marker.feature = this.drawRing(lonlat, info.radius, info.status);

            return marker;
        },
        //------------改变标注状态
        changeMarkerStatus: function(marker, status) {
            this._redrawRing(marker.feature, status);
            marker.info.status = status;
            marker.icon.setUrl(marker.icon.url.replace(/\d\.png$/, status + ".png"));
        },
        //------------改变标注位置
        changeMarkerLonLat: function(marker, lonlat) {
            marker.moveTo(this.map.getPixelFromLonLat(lonlat));
            marker.feature.move(lonlat);
        },
        //清空
        clearMarkers: function() {
            if (this.markersLayer) {
                this.markersLayer.clearMarkers();
            }
            if (this.ringsLayer) {
                this.ringsLayer.removeAllFeatures();
            }
        },
        //------------文本提示框
        addTextPopup: function(text) {

        },
        //------------详情提示框
        showInfoPopup: function(lonlat, info) {
            var html = template.compile("<!-- @require components/popup/popup.less -->\r\n<div class=\"m-popup{{if $data.status}} m-popup-danger{{/if}}\">\r\n    <!-- 最大 -->\r\n    <div class=\"m-popup-max\">\r\n        <div class=\"m-popup-hd\">\r\n            <div class=\"title\">{{$data.user}}</div>\r\n            <i class=\"ico-20\"></i>\r\n            <div class=\"btn-select fr\">\r\n                <div class=\"btn btn-select-hd\">\r\n                    <div class=\"btn-select-hd-title\">操作</div>\r\n                    <div class=\"btn-select-hd-tri\">\r\n                        <i class=\"b\"></i>\r\n                        <i class=\"t\"></i>\r\n                    </div>\r\n                </div>\r\n                <div class=\"btn-select-options\">\r\n                    <div class=\"btn-select-option\">\r\n                        <i class=\"ico-16 ico-16-3\"></i> 查看位置\r\n                    </div>\r\n                    <div class=\"btn-select-option\">\r\n                        <i class=\"ico-16 ico-16-4\"></i> 发送信息\r\n                    </div>\r\n                    <div class=\"btn-select-option\">\r\n                        <i class=\"ico-16 ico-16-5\"></i> 撤回\r\n                    </div>\r\n                </div>\r\n            </div>\r\n        </div>\r\n        <div class=\"m-popup-bd\">\r\n            <div class=\"status\">\r\n                {{if $data.statusType==3}}\r\n                    <i class=\"ico ico-z{{$data.statusAngle-$data.statusAngle%15}}\"></i>\r\n                {{else}}\r\n                    <i class=\"ico ico-r{{$data.statusAngle-$data.statusAngle%15}}\"></i>\r\n                {{/if}}\r\n\r\n                {{if $data.status}}\r\n                    <span class=\"sos\">SOS</span>\r\n                {{/if}}\r\n            </div>\r\n            \r\n            <ul class=\"list1\">\r\n                <li class=\"lv1\"> \r\n                    <div class=\"name\">\r\n                        呼吸\r\n                        <i class=\"ico-16 ico-16-6\"></i> \r\n                    </div>\r\n                    <div class=\"num\">\r\n                        {{$data.infos[1]}}\r\n                        <span>次/分</span>\r\n                    </div>\r\n                </li>\r\n                <li class=\"lv2\"> \r\n                    <div class=\"name\">\r\n                        心率\r\n                        <i class=\"ico-16 ico-16-8\"></i> \r\n                    </div>\r\n                    <div class=\"num\">\r\n                        {{$data.infos[2]}}\r\n                        <span>次/分</span>\r\n                    </div>\r\n                </li>\r\n                <li> \r\n                    <div class=\"name\">\r\n                        体温\r\n                        <i class=\"ico-16 ico-16-7\"></i> \r\n                    </div>\r\n                    <div class=\"num\">\r\n                        {{$data.infos[3]}}\r\n                        <span>℃</span>\r\n                    </div>\r\n                </li>\r\n                <li> \r\n                    <div class=\"name\">\r\n                        空呼\r\n                        <i class=\"ico-16 ico-16-9\"></i> \r\n                    </div>\r\n                    <div class=\"num\">\r\n                        {{$data.infos[4]}}\r\n                        <span>%</span>\r\n                    </div>\r\n                </li>\r\n            </ul>\r\n            <ul class=\"list2\">\r\n                    {{if $data.infos[8]}}\r\n                <li>\r\n                    <i class=\"ico-16 ico-16-10\"></i>已连接 \r\n                </li>\r\n                    {{else}}\r\n                <li class=\"lv1\">\r\n                    <i class=\"ico-16 ico-16-16\"></i>失联\r\n                </li>\r\n                    {{/if}}\r\n                <li class=\"lv1\">\r\n                    <i class=\"ico-16 ico-16-11\"></i> {{$data.infos[6]}}%\r\n                </li>\r\n                <li>\r\n                    <i class=\"ico-16 ico-16-12\"></i> {{$data.infos[7]}}%\r\n                </li>\r\n            </ul>\r\n        </div>\r\n        <!-- 三角 -->\r\n        <div class=\"m-popup-tri\" style=\"display: block;\">\r\n            <b></b>\r\n            <i></i>\r\n        </div>\r\n    </div>\r\n</div>")(info);
            if (!this.infoPopup) {
                this.infoPopup = new Popup(
                    "infoPopup",
                    lonlat,
                    new OpenLayers.Size(297, 156),
                    html,
                    null,
                    null, {
                        panMapIfOutOfView: 1,
                        keepInMap: 1,
                        offsetX: -40,
                        offsetY: -10
                    }
                );
                this.map.addPopup(this.infoPopup);
                this.infoPopup.updatePosition();
                this.infoPopup.show();
                this.infoPopup.info = info;
            } else {
                if (this.infoPopup.info == info) {
                    this.infoPopup.toggle();
                } else {
                    this.infoPopup.lonlat = lonlat;
                    this.infoPopup.info = info;
                    this.infoPopup.setContentHTML(html);
                    this.infoPopup.updatePosition();
                    this.infoPopup.show();
                }
            }
        },
        //------------绘图层
        drawRing: function(lonlat, radius, status) {
            if (!this.ringsLayer) {
                this.ringsLayer = new OpenLayers.Layer.Vector();
                this.map.addLayer(this.ringsLayer);
                //修改层级
                this.ringsLayer.div.style.zIndex = this.map.Z_INDEX_BASE.Overlay - 1;
                //注册事件，防止等级被重置
                this.map.events.register("addlayer", this, function() {
                    this.ringsLayer.div.style.zIndex = this.map.Z_INDEX_BASE.Overlay - 1;
                });
                this.map.events.register("removelayer", this, function() {
                    this.ringsLayer.div.style.zIndex = this.map.Z_INDEX_BASE.Overlay - 1;
                });
            }
            var color = status ? "#df2d3a" : "#3d9cd4";
            var feature = new OpenLayers.Feature.Vector(
                new OpenLayers.Geometry.Polygon.createRegularPolygon(
                    new OpenLayers.Geometry.Point(lonlat.lon, lonlat.lat),
                    radius,
                    100,
                    0
                ), {
                    radius: radius
                }, {
                    fillColor: color,
                    fillOpacity: 0.2,
                    strokeWidth: 1,
                    strokeOpacity: 0.5,
                    strokeColor: color,
                }
            );
            this.ringsLayer.addFeatures(feature);
            return feature;
        },
        _redrawRing: function(feature, status) {
            var color = status ? "#df2d3a" : "#3d9cd4";
            feature.style.fillColor = color;
            feature.style.strokeColor = color;
            this.ringsLayer.drawFeature(feature);
        },
        //------------工具栏
        createToolbar: function() {
            if (!this.editingToolbar) {
                var drawLayer = new OpenLayers.Layer.Vector();
                this.map.addLayer(drawLayer);
                //修改层级
                drawLayer.div.style.zIndex = this.map.Z_INDEX_BASE.Overlay - 2;

                //注册事件，防止等级被重置
                this.map.events.register("addlayer", this, function() {
                    drawLayer.div.style.zIndex = this.map.Z_INDEX_BASE.Overlay - 2;
                });
                this.map.events.register("removelayer", this, function() {
                    drawLayer.div.style.zIndex = this.map.Z_INDEX_BASE.Overlay - 2;
                });
                //控件
                this.editingToolbar = new MyToolbar(drawLayer);
                this.map.addControl(this.editingToolbar);
            }
            return this.editingToolbar;
        }
    });

    this.GIS = function(id, options) {
        return new Core(id, options);
    };
}(this));
