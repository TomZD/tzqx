// 台风警戒线
define(function(require) {
	var Draw = require("../../draw/draw");

	//警戒线样式
	var lineStyles = [{
		strokeColor: "#ff0000",
		strokeWidth: 1,
		strokeDashstyle: "solid"
	}, {
		strokeColor: "blue",
		strokeWidth: 1,
		strokeDashstyle: "longdash"
	}];
	//名称样式
	var nameStyles = [{
		label: "24\n小\n时\n警\n戒\n线",
		fontColor: "#ff0000",
		fontSize: 13,
		labelXOffset: 12
	}, {
		label: "48\n小\n时\n警\n戒\n线",
		fontColor: "blue",
		fontSize: 13,
		labelXOffset: 12
	}];
	//经纬度信息
	var data = [
		[
			"127,34",
			"127,22",
			"119,18",
			"119,11",
			"113,4.5",
			"105,0"
		],
		[
			"132,34",
			"132,15",
			"120,0",
			"105,0"
		]
	];

	return OpenLayers.Class(Draw, {
		initialize: function() {
			Draw.prototype.initialize.apply(this, ["warningline"]);
		},
		//根据等级显示或隐藏警戒线名称
		showNameByLevel: function(args) {
			if (args && args.zoomChanged) {
				var zoom = this.map.getZoom();
				var i, l, n;
				for (i = 0, l = this.names.length; i < l; i++) {
					n = this.names[i];
					n.style = this.getNameStyle(zoom, nameStyles[i]);
					this.drawFeature(n);
				}
			}
		},
		//如果地图等级2级以上才显示名称
		getNameStyle: function(zoom, style) {
			return zoom > 2 ? style : {
				"display": "none"
			};
		},
		redraw: function() {
			Draw.prototype.redraw.apply(this);

			var lines = [];
			var names = [];
			var zoom = this.map.getZoom();
			var i, j, l, ll, n, m, lonlats;

			for (i = 0, l = data.length; i < l; i++) {
				n = data[i];
				lonlats = []; //转换经纬度
				for (j = 0, ll = n.length; j < ll; j++) {
					m = n[j].split(",");
					lonlats.push(
						this.map.tanslateLonLat(
							new OpenLayers.LonLat(m[0], m[1])
						)
					);
				}

				lines.push(
					new OpenLayers.Feature.Vector(
						this.getLine(lonlats),
						null,
						lineStyles[i]
					)
				);
				names.push(
					new OpenLayers.Feature.Vector(
						this.getPoint({
							lon: (lonlats[0].lon + lonlats[1].lon) / 2,
							lat: (lonlats[0].lat + lonlats[1].lat) / 2
						}),
						null,
						this.getNameStyle(zoom, nameStyles[i])
					)
				);
			}

			this.names = names;
			this.addFeatures(lines.concat(names));
			this.events.register("moveend", this, this.showNameByLevel); //根据地图等级判断是否显示名称
		},
		destroy: function() {
			Draw.prototype.destroy.apply(this);
			this.names = null;
		}
	});
});