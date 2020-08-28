define(function(require) {
	var Lines = require("../draw/m4collection");
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
				strokeWidth: value==588?5:1
			};

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
				content: group
			});
		}
		return lines;
	};
	return function(name, xml, opacity, value, sort) {
		var data = formatXML.call(this, xml);
		var layer = new Lines(name || "lines", {
			data: data,
			value: value,
			sort: sort,
			type:"line"
		});
		this.addLayer(layer);
		opacity != null && layer.setOpacity(opacity);
		return layer;
	}
});