// 路径层
define(function(require) {
	var Draw = require("../../draw/draw");

	return OpenLayers.Class(Draw, {
		/**
		 * 初始化
		 * @param  {object} box  台风容器
		 */
		initialize: function(box) {
			Draw.prototype.initialize.apply(this);
			this.box = box;
		},
		/**
		 * 根据发布者过滤预报路径并获取样式
		 * @param  {string} publisher 发布者。如果publisher不存在，表示实况
		 * @return {object} 	相关样式
		 */
		getStyle: function(publisher) {
			if (publisher) {
				var m = this.box.publishers[publisher];
				//是否存在配置中
				if (m) {
					return {
						strokeColor: m.color,
						strokeWidth: 1,
						strokeDashstyle: "dash"
					};
				} else {
					return;
				}
			} else {
				return {
					strokeColor: "#000",
					strokeWidth: 3
				};
			}
		},
		/**
		 * 添加台风路径
		 * @param {array} points    经纬度集合
		 * @param {string} publisher 发布者
		 */
		add: function(points, publisher) {
			var style = this.getStyle(publisher);
			if (!style) return; //过滤无返回的路径

			var feature = new OpenLayers.Feature.Vector(this.getLine(points), null, style);
			this.addFeatures(feature);
			return feature;
		},
		/**
		 * 移除台风路径
		 * @param  {object} feature 路径
		 */
		remove: function(feature) {
			this.removeFeatures(feature);
		},
		/**
		 * 重绘路径
		 * @param  {object}  feature 路径
		 * @param  {number}  lon     经度
		 * @param  {number}  lat     纬度
		 */
		redrawPath: function(feature, lon, lat) {
			var path = feature.geometry;
			path.addPoint(this.getPoint({
				lon: lon,
				lat: lat
			}));
			this.renderer.redrawNode(path.id, path, feature.style || {}, this.id);

			for(var i=0,l=feature.geometry.components.length;i<l;i++){
				if(feature.geometry.components[i].lon==0){
					alert(1)
				}
			}
		},
		/**
		 * 销毁路径层
		 */
		destroy: function() {
			Draw.prototype.destroy.apply(this);
			this.box = null;
		},
		CLASS_NAME: "GI.Typhoon.Paths"
	});
});