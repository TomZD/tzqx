//绘制边界
define(function(require) {
	var Draw = require("../draw/draw");
	OpenLayers.Renderer.SVG.prototype.MAX_PIXEL = 150000000; //修复svg对最大绘制的限制

	var formatXML = function(xml) {
		var Region = new Object();
		Region["lines"] = new Array();
		Region["center"] = new String();
		var lines = [];
		//获取关键字不同
		var R = xml.getElementsByTagName("Line");
		if (!R.length) {
			var element = xml.getElementsByTagName("Area");
			for (var t = 0, length = element[0].attributes.length; t < length; t++) {
				if (element[0].attributes[t].name == "Coords") {
					R = element[0].attributes[t];
				}
				if (element[0].attributes[t].name == "Center") { //为获取选中区域的center
					Region["center"] = element[0].attributes[t];
				}
			}
			var dat = R.nodeValue.split(",");
			var line = [];
			for (var j = 0, k = 0, m = dat.length; j < m; j++) {
				var lon = dat[j];
				var lat = dat[++j];
				line[k] = this.tanslateLonLat(
					lon,
					lat
				);
				k++;
			}
			lines.push(line);
		} else {
			var area = xml.getElementsByTagName("Area"); //为获取选中区域的center
			for (var t = 0, length = area[0].attributes.length; t < length; t++) {
				if (area[0].attributes[t].name == "Center") {
					Region["center"] = area[0].attributes[t];
					break;
				}
			}
			for (var i = 0, l = R.length; i < l; i++) {
				var dat = R[i].childNodes[0].nodeValue.split(",");
				var line = [];
				for (var j = 0, k = 0, m = dat.length; j < m; j++) {
					var lon = dat[j];
					var lat = dat[++j];
					line[k] = this.tanslateLonLat(
						lon,
						lat
					);
					k++;
				}
				lines.push(line);
			}
		}

/*		
		lines.push([
			this.tanslateLonLat(-180, 90),
			this.tanslateLonLat(-180, -90),
			this.tanslateLonLat(180, -90),
			this.tanslateLonLat(180, 90)
		]);
*/

		//---修改
		Region["lines"] = lines;
		return Region;
		//return lines;
	};
	var Boundary = OpenLayers.Class(Draw, {
	    isBoundary: true,
		setContours: function(lines) {
			this.removeAllFeatures();
			if (lines) {
				this.addFeatures(
					new OpenLayers.Feature.Vector(this.getPolygon(lines), null, this.style)
				);
			}
		},
		setStyle: function(style) {
			if (style && typeof style == "object" && this.features.length) {
				this.style = style = style || this.style;
				var oStyle = this.features[0].style;

				var fillColor = style.fillColor;
				if (fillColor != null) {
					oStyle.fillColor = fillColor
				}

				var fillOpacity = style.fillOpacity;
				if (fillOpacity != null) {
					oStyle.fillOpacity = fillOpacity
				}

				var strokeColor = style.strokeColor;
				if (strokeColor != null) {
					oStyle.strokeColor = strokeColor
				}

				var strokeOpacity = style.strokeOpacity;
				if (strokeOpacity != null) {
					oStyle.strokeOpacity = strokeOpacity
				}

				var strokeWidth = style.strokeWidth;
				if (strokeWidth != null) {
					oStyle.strokeWidth = strokeWidth
				}

				this.redraw();
			}
		},
		CLASS_NAME: "Gi.Drawing.Boundary"
	});

	return function(name, xml, style) {
		var layer = new Boundary(name || "Boundary", {
			style: style
		});
		layer.isBoundary = true; //设置等级
		this.addLayer(layer);

		//---修改
		var region = formatXML.call(this, xml);
		layer.setContours(region.lines);
		//layer.setContours(formatXML.call(this, xml));
		return layer;
	}
});