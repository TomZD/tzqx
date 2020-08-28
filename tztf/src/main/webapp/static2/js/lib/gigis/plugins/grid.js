//格点数据
define(function(require) {
	var M4 = require("../draw/m4");
	var getGrid = OpenLayers.Class(M4, {
		//解析四类格式
		formart: function(text) {
			// var date=new Date();
			// var t=date.getTime();

			var arr = text.split(/\s+/g);
			var numLon = arr[15] - 0,
				numLat = arr[16] - 0,
				arrNew = arr.slice(22);
			var slat = arr[13] - 0;
			var slon = arr[11] - 0;
			var elat = arr[14] - 0;
			var elon = arr[12] - 0;
			var intervalLon = arr[9] - 0;
			var intervalLat = arr[10] - 0;

			var group = [];
			for (var i = 0; i < numLat; i++) {
				group[i] = [];
				var lat = slat + intervalLat * i; //当前纬度

				for (var j = 0; j < numLon; j++) {
					var bl = true;
					var v = arrNew[i * numLon + j];
					//过滤无效值
					if (this.invalidValue && this.invalidValue.length) {
						for (var k = 0, l = this.invalidValue.length; k < l; k++) {
							if (v == this.invalidValue[k]) {
								bl = null;
								break;
							}
						}
					}
					group[i][j] = bl && {
						content: v?+(parseFloat(v).toFixed(0)):v,
						lonlat: this.map.tanslateLonLat(
							new OpenLayers.LonLat(
								slon + intervalLon * j, //当前经度
								lat
							)
						)
					};
				}
			}

			//var ss; //过度变量
			//高纬度开始
			// if (slat < elat) {
			// 	ss = slat;
			// 	slat = elat;
			// 	elat = ss;
			// 	group.reverse();
			// }
			// //低经度开始
			// if (slon > elon) {
			// 	ss = slon;
			// 	slon = elon;
			// 	elon = ss;
			// 	for (var i = 0, l = group.length; i < l; i++) {
			// 		group[i].reverse();
			// 	}
			// }


			// var date=new Date();
			// console.log("fomart ----"+(date.getTime()-t));



			return {
				group: group,
				num: {
					lon: numLon,
					lat: numLat
				},
				start: this.map.tanslateLonLat(new OpenLayers.LonLat(slon, slat)),
				end: this.map.tanslateLonLat(new OpenLayers.LonLat(elon, elat))
			};
		},
		moveTo: function(bounds, zoomChanged, dragging) {
			this.lonlats = this.lonlats || (this.text && this.formart(this.text));
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
				if (type != null && bounds.contains(lonlat.lon, lonlat.lat)) {
					var feature = new OpenLayers.Feature.Vector(
						new OpenLayers.Geometry.Point(lonlat.lon, lonlat.lat)
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
			// var date=new Date();
			// var t=date.getTime();



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

			// var date=new Date();
			// console.log("calcData ----"+(date.getTime()-t));
		},
		CLASS_NAME: "Gi.Drawing.Grid"
	});
	return function(name, text, styles, invalidValue) {
		//获取图层
		var newStyles = {
			fontColor: "#000",
			fontFamily: "sans-serif",
			fontSize: 12
		};
		if (typeof styles == "object") {
			for (var a in styles) {
				newStyles[a] = styles[a];
			}
		}
		newStyles.label = "${type}";

		var layer = new getGrid(name || "格点", {
			text: text,
			styleMap: new OpenLayers.StyleMap(newStyles),
			invalidValue: invalidValue
		});
		this.addLayer(layer);
		return layer;
	}
});