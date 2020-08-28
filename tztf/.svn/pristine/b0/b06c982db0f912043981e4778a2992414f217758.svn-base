define(function(require) {
	var Draw = require("../../draw/draw");
	/**
	 * 台风预报与实况点管理器
	 */
	return OpenLayers.Class(Draw, {
		/**
		 * 监听事件
		 * @type {Object}
		 */
		eventListeners: {
			featureclick: function(e) {
				var info = e.feature.data,
					data = info.data,
					t = info.typhoon;

				t.box.addPopup(t.data.name, t.data.type, data, info.isForecast);

				if (!info.isForecast) {
					t.changeTime(data.time);
				}
				return false;
			},
			featureover: function(e) {
				var info = e.feature.data,
					data = info.data,
					t = info.typhoon;
				t.box.addPopup(t.data.name, t.data.type, data);
				this.drawFeature(e.feature, this.getStyle(data.windPower, info.isForecast, true));
				return false;
			},
			featureout: function(e) {
				var info = e.feature.data,
					data = info.data,
					t = info.typhoon;

				t.box.hidePopup();
				this.drawFeature(e.feature, this.getStyle(data.windPower, info.isForecast));
				return false;
			}
		},
		/**
		 * 初始化
		 * @param  {object} box            台风容器
		 */
		initialize: function(box) {
			Draw.prototype.initialize.apply(this);
			this.box = box;
		},
		/**
		 * 获取样式
		 * @param  {number}  windPower  风力等级
		 * @param  {boolean} isForecast 是否是预报
		 * @param  {Boolean} isChecked  是否被选中
		 * @return {object}             样式
		 */
		getStyle: function(windPower, isForecast, isChecked) {
			windPower = parseFloat(windPower);

			var pointRadius = isChecked ? 6 : 4;
			var strokeColor = isForecast ? "#666" : "#000";
			var fillColor = "#fff";
			if (!isNaN(windPower)) {
				var arr = [6, 8, 10, 12, 14, 16]; //风力等级临界点
				for (var i = arr.length - 1; i >= 0; i--) {
					if (windPower >= arr[i]) {
						fillColor = this.box.windPowers[i].color;
						break;
					}
				}
			}

			return {
				strokeWidth: 1,
				strokeColor: strokeColor,
				pointRadius: pointRadius,
				fillColor: fillColor
			};
		},
		/**
		 * 添加点
		 * @param {object}  data       实况或预报点信息
		 * @param {object}  typhoon    台风实例
		 * @param {Boolean} isForecast 是否是预报
		 * @return {object} 生成的点
		 */
		add: function(data, typhoon, isForecast) {
			var feature = new OpenLayers.Feature.Vector(
				this.getPoint({
					lon: data._lon,
					lat: data._lat
				}), {
					typhoon: typhoon,
					data: data,
					isForecast: isForecast
				},
				this.getStyle(data.windPower, isForecast)
			);
			this.addFeatures(feature);
			return feature;
		},
		/**
		 * 批量增加点
		 * @param  {array}  data       点集合
		 * @param {object}  typhoon    台风实例
		 * @param {Boolean} isForecast 是否是预报
		 * @return {array}             生成的点的集合
		 */
		adds: function(data, typhoon, isForecast) {
			var i, ll, n, arr = [];
			for (i = 0, ll = data.length; i < ll; i++) {
				arr[i] = this.add(data[i], typhoon, isForecast);
			}
			return arr;
		},
		/**
		 * 移除点
		 * @param  {object|array} feature 点
		 */
		remove: function(feature) {
			this.removeFeatures(feature);
		},
		/**
		 * 销毁台风实况与预报点管理器
		 */
		destroy: function() {
			Draw.prototype.destroy.apply(this);
			this.box = null;
		},
		CLASS_NAME: "GI.Typhoon.Points"
	});
});