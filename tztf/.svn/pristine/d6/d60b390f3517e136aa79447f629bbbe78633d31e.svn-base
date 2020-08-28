define(function(require) {
	var M4 = require("../draw/m4");
	var rgb2hex = require("../base/rgb2hex");
	var formatXML = function(xml) {
		var lines = [];
		var R = xml.getElementsByTagName("R");
		for (var i = 0, l = R.length; i < l; i++) {
			var el = R[i];
			var rgb = el.getAttribute("Color");
			var value = el.getAttribute("Value");
			var arr = rgb.split(",");
			var fillOpacity = 1;
			if (arr.length == 4) {
				rgb = arr[1] + "," + arr[2] + "," + arr[3];
				fillOpacity = arr[0] / 255;
			}

			var style = {
				strokeWidth: 0
			};
			style.fillColor = "rgb(" + rgb + ")";
			style.fillOpacity = fillOpacity;

			var group = [];
			var L = R[i].getElementsByTagName("L");
			for (var j = 0, m = L.length; j < m; j++) {
				var points = [];
				var arr = L[j].childNodes[0].nodeValue.split(" ");

				//console.log("节点内容长度：" + arr.length);
				//var d = new Date();
				//var s = d.getTime();

				for (var jj = 0, mm = arr.length; jj < mm; jj++) {
					var lonlat = arr[jj].split(",");
					points.push({
						lon: lonlat[0],
						lat: lonlat[1]
					});
				}

				//var d = new Date();
				//var e = d.getTime();

				//console.log("耗时：" + (e - s));

				group.push(points);
			}

			lines.push({
				value: value,
				style: style,
				group: group
			});
		}
		return lines;
	};
	var Contours = OpenLayers.Class(M4, {
		polygons: null, //多边形集合
		//获取多边形集合
		getPolygons: function(rings) {
			if (rings && rings.length) {
				var polygons = [];
				for (var i = 0, l = rings.length; i < l; i++) {
					var ring = rings[i];
					var p=this.getPolygon(ring.group);
					p.transform(
						new OpenLayers.Projection(this.map.displayProjection),
						new OpenLayers.Projection(this.map.getProjection())
					);
					polygons[i] = {
						value: ring.value,
						polygon: new OpenLayers.Feature.Vector(p, null, ring.style)
					};
				}
				return polygons;
			}
		},
		changeRange: function(value, sort) {
			this.setRange(value, sort); //更新范围

			if (this.polygons && this.polygons.length) {
				for (var i = 0, l = this.polygons.length; i < l; i++) {
					var p = this.polygons[i];
					var el = OpenLayers.Util.getElement(p.polygon.geometry.id);
					el && (el.style.display = this.compareRange(p.value) ? "" : "none");
				}
			}
		},
		redraw: function() {
			M4.prototype.redraw.apply(this);

			if (this.polygons) return; //如果存在了，不执行以下内容

			this.polygons = this.getPolygons(this.lines);
			if (!this.polygons) return; //如果转化后依然不存在，也不执行以下内容

			var arr = [];
			for (var i = 0, l = this.polygons.length; i < l; i++) {
				arr.push(this.polygons[i].polygon)
			}
			this.addFeatures(arr);
			this.changeRange();
		},
		destroy: function() {
			OpenLayers.Layer.Vector.prototype.destroy.apply(this, arguments);
			this.polygons = null;
		},
		CLASS_NAME: "Gi.Drawing.Contours"
	});
	return function(name, xml, opacity, value, sort) {
		//var d = new Date();
		//var s = d.getTime();

		var lines = formatXML.call(this, xml);

		//var d = new Date();
		//var e = d.getTime();

		//console.log("格式化时间:" + (e - s));

		//var d = new Date();
		//var s = d.getTime();

		var layer = new Contours(name || "Contours", {
			lines: lines,
			value: value,
			sort: sort
		});
		this.addLayer(layer);

		//var d = new Date();
		//var e = d.getTime();

		//console.log("绘制时间:" + (e - s));

		opacity != null && layer.setOpacity(opacity);
		return layer;
	}
});