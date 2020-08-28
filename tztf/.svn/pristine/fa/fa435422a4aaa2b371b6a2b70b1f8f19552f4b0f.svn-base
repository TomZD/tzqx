//绘制边界
define(function(require) {
	var Draw = require("../draw/draw");
	var Rail = OpenLayers.Class(Draw, {
		linesFormat: function(lines) {
			var source = new OpenLayers.Projection(this.map.displayProjection),
				dest = new OpenLayers.Projection(this.map.getProjection());
			for (var i = 0, l = lines.length; i < l; i++) {
				var line = lines[i].geometry;
				for (var j = 0, len = line.components.length; j < len; j++) {
					var component = line.components[j];
					component.transform(source, dest);
				}
				this.bounds = null;
			}
		},
		redraw: function() {
			Draw.prototype.redraw.apply(this);

			if (this.json && this.json.length) {
				var that = this;
				var overline = [], underline = [];
				for (var i = 0, ilen = this.json.length; i < ilen; i++) {
					var lines = this.json[i].lines;

					for(var j = 0, jlen = lines.length; j < jlen; j++){
						line = lines[j].line;
						var arr = [];

						for(var k = 0, klen = line.length; k < klen; k++){
							arr.push(line[k]);
						}
						if(lines[j].position == "overground"){
							overline.push(
								new OpenLayers.Feature.Vector(that.getLine(arr), null, {
									strokeWidth: 5,
									strokeColor: "#008000"
								})
							);
						}
						else if(lines[j].position == "underground"){
							underline.push(
								new OpenLayers.Feature.Vector(that.getLine(arr), null, {
									strokeWidth: 5,
									strokeColor: "#00588b"
								})
							);
						}
					}
				}
				this.linesFormat(overline);
				this.addFeatures(overline);
				this.linesFormat(underline);
				this.addFeatures(underline);
			}
		},
		destroy: function() {
			Draw.prototype.destroy.apply(this);
			this.markers && this.markers.destroy();
			this.markers = null;
			this.ps = null;
		},
		CLASS_NAME: "Gi.Drawing.Rail"
	});

	//站点集合
	var stations = new Array();

	return function(name, json, style) {

		var layer = new Rail(name || "轨道线", {
			json: json.lines,
			style: {
				strokeWidth: 5,
				strokeColor: "#008000"
			}
		});
		this.addLayer(layer);

		layer.markers = this.addMarkers();
		for(var i = 0, len1 = json.stations.length; i < len1; i++)
		{
			//将所有站点存入stations
			stations.push(json.stations[i]);
		}

		var arr = [];
		//lon, lat, icon, text, content, ev
		var s,imgUrl;
		for (var i = 0, l = stations.length; i < l; i++) {
			s = stations[i];
			if(s.position == "underground")
				imgUrl = "images/marker-b.png";
			else if(s.position == "overground")
				imgUrl = "images/marker-g.png";
			arr.push({
				lon: s.lonlat[1],
				lat: s.lonlat[0],
				url: imgUrl,
				width: 13,
				height: 13,
				position: "mc",
				offsetX: -5,
				offsetY: -5,
				text: "<div class='rail-sname'>" + s.name + "</div>",
				content: {
					name: s.name
				},
				ev: null
			});
		}
		var ps = this.addMarker(arr, layer.markers);
		layer.ps = ps;
		return layer;
	}
});