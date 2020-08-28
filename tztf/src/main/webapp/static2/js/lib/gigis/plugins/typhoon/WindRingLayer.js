define(function(require) {
	var Draw = require("../../draw/draw");

	// 风圈样式
	var windRingStyle = [{
		fillColor: "#2ab746",
		fillOpacity: 0.5,
		strokeWidth: 1,
		strokeColor: "#2ab746"
	}, {
		fillColor: "#d7d232",
		fillOpacity: 0.5,
		strokeWidth: 1,
		strokeColor: "#d7d232"
	}, {
		fillColor: "red",
		fillOpacity: 0.5,
		strokeWidth: 1,
		strokeColor: "red"
	}];

	/**
	 * 风圈管理器
	 */
	return OpenLayers.Class(Draw, {
		/**
		 * 初始化
		 * @param  {object} box 台风容器
		 */
		initialize: function(box, style) {
			Draw.prototype.initialize.apply(this);
			this.box = box;
			this.style = style || windRingStyle;
		},
		/**
		 * 添加风圈
		 * @param {number} lon      经度
		 * @param {number} lat      纬度
		 * @param {number} radius7  7级风圈半径
		 * @param {number} radius10 10级风圈半径
		 * @param {number} radius12 12级风圈半径
		 */
		add: function(lon, lat, radius7, radius10, radius12) {
			var p = this.getPoint({
					lon: lon,
					lat: lat
				}),
				features = [];

			//风圈
			radius7 && features.push(this._add(p, radius7, 0));
			radius10 && features.push(this._add(p, radius10, 1));
			radius12 && features.push(this._add(p, radius12, 2));

			this.addFeatures(features);

			//台风动画图片
			var gif = this.box.gis.addLabelBox(
				new OpenLayers.LonLat(lon, lat),
				"<div class='wind-ring animation'></div>", {
					w: 50,
					h: 50
				}
			);

			return {
				gif: gif,
				features: features
			};
		},
		_add: function(point, radius, index) {
			var feature;

			//计算倍率
			var p1 = new OpenLayers.LonLat(120, 30);
			p1 = this.map.tanslateLonLat(p1);
			p1 = this.getPoint(p1);

			var p2 = new OpenLayers.LonLat(120 - 1, 30 - 1);
			p2 = this.map.tanslateLonLat(p2);
			p2 = this.getPoint(p2);

			var radiusFactor = (p1.x - p2.x) / 111.11;

			radius = radius * radiusFactor;
			
			return new OpenLayers.Feature.Vector(
				new OpenLayers.Geometry.Polygon.createRegularPolygon(point, radius, 100, 0), {
					radius: radius
				},
				this.style[index]
			);
		},
		/**
		 * 删除风圈
		 * @param  {object} f 风圈对象
		 */
		remove: function(f) {
			if (!f) return;
			this.removeFeatures(f.features);
			this.box.gis.removeLabelBox(f.gif);
		},
		/**
		 * 移动风圈
		 * @param  {object} lon 经度
		 * @param  {object} lat 纬度
		 * @param  {object} f  风圈对象
		 */
		move: function(lon, lat, f) {
			if (!f) return;
			var features = f.features;
			for (var i = 0, l = features.length; i < l; i++) {
				var feature = features[i];
				var geometry = new OpenLayers.Geometry.Polygon.createRegularPolygon(
					this.getPoint({
						lon: lon,
						lat: lat
					}),
					feature.data.radius,
					100,
					0
				);
				feature.geometry.components = geometry.components;
				this.drawFeature(feature);
			}
			f.gif.setLonlat(lon, lat);
		},
		/**
		 * 销毁风圈管理器
		 */
		destroy: function() {
			Draw.prototype.destroy.apply(this);
			this.box = null;
		},
		CLASS_NAME: "GI.Typhoon.WindRing"
	});
});