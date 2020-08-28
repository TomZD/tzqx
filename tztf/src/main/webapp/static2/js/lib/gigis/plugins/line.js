//绘制边界
define(function(require) {
	var Draw = require("../draw/draw");
	//经纬度转换
	function drawLine(arr) {
		var pointList = [];
		for (var i = 0, l = arr.length; i < l; i++) {
			var lon = arr[i].lon;
			var lat = arr[i].lat;
			var point = this.tanslateLonLat(lon, lat);
			pointList.push(point);
		}
		return pointList;
	}
	var DrawLine = OpenLayers.Class(Draw, {
		setLine: function(points) {
			this.removeAllFeatures();
			this.addFeatures(
				new OpenLayers.Feature.Vector(this.getLine(points), null, this.style)
			);
		},
		setStyle: function(style) {
			if (style && typeof style == "object" && this.features.length) {
				this.style = style = style || this.style;
				var oStyle = this.features[0].style;
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
		}
	});
	return function(arr, style, name) {
		var layer = new DrawLine(name || "线", {
			style: style
		});
		this.addLayer(layer);

		//---修改
		var points = drawLine.call(this, arr);
		layer.setLine(points);
		return layer;
	}
});