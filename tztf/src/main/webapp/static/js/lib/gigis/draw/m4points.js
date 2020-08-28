define(function(require) {
	var M4 = require("../draw/m4");
	/*
		四类格式数据-画点
	 */
	return OpenLayers.Class(M4, {
		moveTo: function(bounds, zoomChanged, dragging) {
			var lonlats = this.lonlats;
			if (!lonlats) return;
			zoomChanged && this.calcData();
			this.changeRange();
			OpenLayers.Layer.Vector.prototype.moveTo.apply(this, arguments);
		},
		changeRange: function(value) {
			// var date=new Date();
			// var t=date.getTime();

			this.removeAllFeatures(); //清空
			this.setRange(value);

			var features = [];
			// 范围过滤
			var bounds = this.map.calculateBounds();
			for (var i = 0, l = this.data.length; i < l; i++) {
				var d = this.data[i];
				var type = d.content;
				var lonlat = d.lonlat;
				if (bounds.contains(lonlat.lon, lonlat.lat)) {
					var feature = new OpenLayers.Feature.Vector(
						new OpenLayers.Geometry.Point(lonlat.lon, lonlat.lat),
						null,
						d.style
					);
					if (this.value != null) {
						type = this.compareRange(type) ? type : "";
					}
					feature.attributes = {
						type: type
					};
					features.push(feature);
				}
			}
			// var date=new Date();
			// console.log("changeRange1 ----"+(date.getTime()-t));
			this.addFeatures(features);

			// var date=new Date();
			// console.log("changeRange2 ----"+(date.getTime()-t));
		},
		calcData: function() {
			var lonlats = this.lonlats;
			if (!lonlats) return;
			var arr = [];
			//计算间隔数
			var group = lonlats.group;
			var tl = this.map.getPixelFromLonLat(lonlats.start);
			var br = this.map.getPixelFromLonLat(lonlats.end);
			var num = lonlats.num;
			var numLat = num.lat;
			var numLon = num.lon;
			var IntervalNumLon = Math.round(numLon * 50 / (br.x - tl.x)) || 1;
			var IntervalNumLat = Math.round(numLat * 50 / (br.y - tl.y)) || 1;

			for (var i = 0; i < numLat; i++) {
				if (i % IntervalNumLat == 0) {
					for (var j = 0; j < numLon; j++) {
						if (j % IntervalNumLon == 0) {
							var d = group[i][j];
							d && arr.push(d);
						}
					}
				}
			};
			this.data = arr;
		},
		setData:function(lonlats){
			this.lonlats=lonlats;
		},
		CLASS_NAME: "Gi.Drawing.Grid"
	});
});