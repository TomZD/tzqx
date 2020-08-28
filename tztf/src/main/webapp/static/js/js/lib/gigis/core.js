/**
	基础函数
 */
define(function(require) {
	var verify = require("./base/verify");
	var Popup = require("./core/Popup");
	var Map = require("./core/Map");
	var Markers = require("./core/Markers");
	var Marker = require("./core/Marker");
	var BoxMarker = require("./core/BoxMarker");
	var rLayers = require("./layer/resources");
	var rControls = require("./control/resources");

	return OpenLayers.Class({
		initialize: function(id, from, options) {
			var map = this.map = new Map(id, {
				displayProjection: "EPSG:4326", //输出地理坐标系
				disableZoom: options.disableZoom,
				documentDrag: options.documentDrag
			});
			var Layers = rLayers[from];
			if (Layers) {
				this.map.addLayers(Layers(options.wrapDateLine));
				if (typeof options != "object") {
					options = {};
				}
				var center = options.center || {};
				this.setBaseLayer(options.baselayer); //必须执行
				this.setCenter(center.lon, center.lat, options.zoom);
			}
		},
		tanslateLonLat: function(lon, lat, isDisplay) {
			var lonlat;
			if (typeof lon == "object") {
				lonlat = lon;
				isDisplay = lat;
			} else {
				lonlat = new OpenLayers.LonLat(lon, lat);
			}
			return this.map.tanslateLonLat(lonlat, isDisplay);
		},
		getLonLatByPosition: function(x, y) {
			return this.tanslateLonLat(this.map.getLonLatFromPixel({
				x: x,
				y: y
			}), true);
		},
		//------------位置与缩放等级
		getZoom: function(opa) {
			if (verify(opa, "number")) return opa;
			if (opa == null || typeof opa != "object") return this.map.getZoom();

			var a = this.tanslateLonLat(opa.left, opa.bottom),
				b = this.tanslateLonLat(opa.right, opa.top);

			return this.map.getZoomForExtent(
				new OpenLayers.Bounds(a.lon, a.lat, b.lon, b.lat),
				opa.closest
			);
		},
		getCenter: function() {
			return this.tanslateLonLat(this.map.getCenter(), true);
		},
		setZoom: function(zoom) {
			zoom = this.getZoom(zoom);
			this.map.zoomTo(zoom);
			return zoom;
		},
		setCenter: function(lon, lat, zoom) {
			this.map.setCenter(
				this.tanslateLonLat(lon, lat),
				this.getZoom(zoom)
			);
		},
		//------------事件
		removeEvent: function(type, listener) {
			this.map.events.unregister(type, this, listener);
		},
		click: function(listener) {
			if (!this._clickListeners) {
				this._clickListeners = [];
				this.map.events.register("click", this, this._click);
			}
			this._clickListeners.push(listener);
		},
		_click: function(evt) {
			evt.lonlat = this.tanslateLonLat(this.map.getLonLatFromPixel(evt.xy), true);;
			for (var i = 0, l = this._clickListeners.length; i < l; i++) {
				this._clickListeners[i].call(this.map, evt);
			}
		},
		//------------图层
		addMarkers: function(showType, options) {
			var layer = new Markers("标注层", showType, options);
			this.addLayer(layer);
			return layer;
		},
		addLayer: function(layer) {
			layer && this.map.addLayer(layer);
			return layer;
		},
		removeLayer: function(layer) {
			layer && layer.destroy();
		},
		/**
		 * @description 设置底图
		 * @param {Object|String} opa 图层对象或图层别名或者图层名
		 */
		setBaseLayer: function(opa) {
			var map = this.map;
			var baselayer;
			if (verify(opa, "string")) {
				baselayer = map.getLayersBy("alias", opa)[0] || map.getLayersByName(opa)[0];
			} else if (typeof opa == "object") {
				baselayer = opa;
			}
			if (!baselayer) {
				baselayer = map.getLayersBy("isBaseLayer", true)[0];
			}
			baselayer && map.setBaseLayer(baselayer);
		},
		//------------标注
		addMarker: function(infos, layer) {
			if (layer) {
				//lon, lat, icon, text, content, ev
				if (!(OpenLayers.Util.isArray(infos))) {
					infos = [infos];
				}
				var markers = [];
				for (var i = 0, l = infos.length; i < l; i++) {
				    var n = infos[i];
				    var lon = n.lon;
				    var lat = n.lat;
				    var icon = n.icon || {};
				    var width = icon.width || 24,
						height = icon.height || 24;
				    var offset = layer.getOffset(n.position, width, height);
				    var marker = new Marker(
						this.tanslateLonLat(new OpenLayers.LonLat(lon, lat)),
						new OpenLayers.Icon(
							icon.url || "images/marker.png",
							new OpenLayers.Size(width, height),
							new OpenLayers.Pixel(offset.x, offset.y)
						)
					);
				    n.text != null && marker.setText(n.text);
				    marker.setContent(n.content);
				    marker._lon = lon;
				    marker._lat = lat;
				    //设置事件
				    var ev = n.ev;
				    typeof ev == "function" && marker.events.register("click", marker, function () {
				        ev.call(this)
				    });
				    markers.push(marker);
				}
				//for (var i = 0, l = infos.length; i < l; i++) {
				//	var n = infos[i],
				//		lon = n.lon || 0,
				//		lat = n.lat || 0;
				//	var marker = new OpenLayers.Marker(
				//		this.tanslateLonLat(new OpenLayers.LonLat(lon, lat)),
				//		new OpenLayers.Icon(
				//			n.url || "",
				//			new OpenLayers.Size(
				//				n.width || 24,
				//				n.height || 24
				//			),
				//			new OpenLayers.Pixel(
				//				n.offsetX || 0,
				//				n.offsetY || 0
				//			)
				//		)
				//	);
				//	marker.content = n.content;
				//	marker._lon = lon;
				//	marker._lat = lat;
				//	//点击事件
				//	var ev = n.ev;
				//	if (typeof ev == "function") {
				//		marker.events.register("mousedown", marker, function(evt) {
				//			ev.call(this);
				//			OpenLayers.Event.stop(evt);
				//		});
				//	}
				//	markers.push(marker);
				//}

				for (var i = 0, l = markers.length; i < l; i++) {
					layer.addMarker(markers[i]);
				}
				if (layer.clusterLayer) {
					layer.clusterLayer.addPoints(markers);
				}

				return markers;
			}
		},
		//------------弹层
		addPopup: function(lon, lat, contentHtml, offsetX, offsetY, close, closeBoxCallback) {
			var map = this.map;
			var popup = new Popup(
				"",
				new OpenLayers.LonLat(0, 0),
				new OpenLayers.Size(20, 20),
				null,
				contentHtml,
				close,
				closeBoxCallback
			);
			map.addPopup(popup);
			popup.setLonlat(lon, lat, offsetX, offsetY);
			return popup;
		},
		removePopup: function(popup) {
			popup && popup.destory();
		},
		//------------控件
		addControl: function(ctrlName, x, y, options) {
			var Ctrl;
			if (typeof ctrlName == "string") {
				Ctrl = rControls[ctrlName];
			}else{
				Ctrl=ctrlName;
			}
			if (Ctrl) {
				options = options || {};
				options.moveTo = function(px) {
					if ((px != null) && (this.div != null)) {
						var style = this.div.style;
						var x = px.x;
						var y = px.y;
						if (x >= 0) {
							style.left = x + "px";
							style.right = "auto"
						} else {
							style.right = -x + "px";
							style.left = "auto"
						}
						if (y >= 0) {
							style.top = y + "px";
							style.bottom = "auto"
						} else {
							style.bottom = -y + "px";
							style.top = "auto"
						}
					}
				};
				var ctrl = new Ctrl(options);
				x = verify(x, "number") ? x : 0;
				y = verify(y, "number") ? y : 0;
				this.map.addControl(ctrl, new OpenLayers.Pixel(x, y));
				return ctrl;
			}
		},
		removeControl: function(ctrl) {
			ctrl && ctrl.destroy();
		},
		//------------获取鼠标点击的经纬度
		getMouseLonLat: function(func) {
			var that = this;
			that.addEvent("click", function(e) {
				var pixel = new OpenLayers.Pixel(e.xy.x, e.xy.y);
				var lonlat = that.map.getLonLatFromPixel(pixel);
				var ll = that.map.tanslateLonLat(lonlat, true);
				if (typeof func == "function") {
					func(ll.lon, ll.lat);
				}
			});
		},

		//---------------建议废弃的方法
		addLabelBox: function(lonlat, content, size, offset) {
			var map = this.map;
			var w, h;
			if (typeof size != null) {
				w = size.w;
				h = size.h;
			}
			var popup = new BoxMarker("labelBox",
				lonlat,
				content,
				new OpenLayers.Size((w || 50), (h || 50)), {
					x: offset && offset.x,
					y: offset && offset.y
				}
			);
			map.addPopup(popup);
			return popup;
		},
		removeLabelBox: function(popup) {
			this.map.removePopup(popup);
		},
	});
});