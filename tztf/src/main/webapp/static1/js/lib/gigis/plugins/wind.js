//海上大风
define(function(require) {
	var Points = require("../draw/m4points");
	var formart = function(json, invalidValue) {
		var numLon = json.num.lon,
			numLat = json.num.lat,
			arrNew = json.group;
		var slat = json.start.lat;
		var slon = json.start.lon;
		var elat = json.end.lat;
		var elon = json.end.lon;
		var group = [];

		for (var i = 0; i < numLat; i++) {
			group[i] = [];

			for (var j = 0; j < numLon; j++) {
				var bl = true;
				var c = arrNew[i * numLon + j];
				group[i][j] = bl && {
					style: {
						externalGraphic: "http://10.135.30.12/GetWindVanePic.ashx?A=" + c.A + "&V="+c.V,
						
			graphicWidth: 55,
			graphicHeight: 55
					},
					lonlat: this.map.tanslateLonLat(
						new OpenLayers.LonLat(
							c.X, //当前经度
							c.Y
						)
					)
				};
			}
		}
		var re={
			group: group,
			num: {
				lon: numLon,
				lat: numLat
			},
			start: this.map.tanslateLonLat(new OpenLayers.LonLat(slon, slat)),
			end: this.map.tanslateLonLat(new OpenLayers.LonLat(elon, elat))
		};
		return re;
	};
	return function(name, text, styles, invalidValue) {
		var layer = new Points(name || "海上大风");
		layer.setData(formart.call(this, text, invalidValue));
		this.addLayer(layer);
		return layer;
	}
});