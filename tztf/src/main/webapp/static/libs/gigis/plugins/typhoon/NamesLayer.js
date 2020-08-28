define(function(require) {
	/**
	 * 台风名称管理器
	 */
	return OpenLayers.Class({
		/**
		 * 初始化
		 * @param  {object} box   台风容器
		 * @param  {string} klass 台风名称class
		 */
		initialize: function(box, klass) {
			this.box = box;
			this.namesLayerClass = klass || "tptxt";
		},
		/**
		 * 添加台风名
		 * @param {string} name 台风名称
		 * @param {number} lon  经度
		 * @param {number} lat  纬度
		 */
		add: function(name, lon, lat) {
			return this.box.gis.addLabelBox(
				new OpenLayers.LonLat(lon, lat),
				"<div class='" + this.namesLayerClass + "'>" + name + "</div>", {
					w: 20,
					h: 50
				}, {
					x: 20,
					y: 10
				}
			);
		},
		/**
		 * 删除台风名
		 * @param  {object} label 控件对象
		 */
		remove: function(label) {
			this.box.gis.removeLabelBox(label);
		},
		/**
		 * 销毁台风名称管理器
		 */
		distroy: function() {
			this.box = null;
		}
	});
});