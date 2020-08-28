//台风容器
define(function(require) {
	var WarningLineLayer = require("./typhoon/warningLineLayer"); //警戒线
	var PathsLayer = require("./typhoon/PathsLayer"); // 路线层
	var NamesLayer = require("./typhoon/NamesLayer"); // 名称层
	var PointsLayer = require("./typhoon/PointsLayer"); // 点层
	var WindRingLayer = require("./typhoon/WindRingLayer"); // 风圈层
	var xml2json = require("./typhoon/xml2json"); //临时使用，之后的版本将不支持xml格式，统一json
	var Typhoon = require("./typhoon/Typhoon");
	var LegendLevel = require("./typhoon/legendLevel");
	var LegendPublisher = require("./typhoon/LegendPublisher");

	//发布者配置
	var publishers = {
		"BABJ": {
			alias: '中央台预报',
			color: "#dc6672"
		},
		"BEHZ": {
			alias: '省台预报',
			color: "#5fd2cf"
		},
		"RJTD": {
			alias: '东京预报',
			color: "#5c68f2"
		},
		"RKSL": {
			alias: '韩国预报',
			color: "#759ab5"
		},
		"RCTP": {
			alias: '台北预报',
			color: "#5d6771"
		},
		"VHHH": {
			alias: '香港预报',
			color: "#76b473"
		},
		"PGTW": {
			alias: "关岛预报",
			color: "#dccd70"
		}
	};
	//热带气旋等级配置
	var windPowers = [{
		alias: "热带低压",
		color: "#00fee2"
	}, {
		alias: "热带风暴",
		color: "#fef301"
	}, {
		alias: "强热带风暴",
		color: "#fe8e2c"
	}, {
		alias: "台风",
		color: "#ff0204"
	}, {
		alias: "强台风",
		color: "#fe38a5"
	}, {
		alias: "超强台风",
		color: "#ae00d8"
	}];

	var TyphoonBox = OpenLayers.Class({
		/**
		 * 临时使用，之后的版本将不支持xml格式，统一json
		 * @type {function}
		 */
		xml2json: xml2json,
		/**
		 * 初始化
		 * @param  {object} options 配置
		 * @param  {object} gis     GIS实例
		 */
		initialize: function(options, gis) {
			if (!gis) return;

			this.gis = gis;
			this.map = gis.map;

			//配置
			options = options || {};
			this.windPowers = options.windPowers || windPowers; //热带气旋等级配置
			this.publishers = options.publishers || publishers; //发布者配置
			this.publishersFilter = this.setPublishers(options.publishersFilter); //发布者过滤器

			//图层
			this.warningLineLayer = new WarningLineLayer(); //预警线
			this.namesLayer = new NamesLayer(this, options.namesLayerClas); //名字层
			this.windringLayer = new WindRingLayer(this, options.windRingStyle); //风圈层
			this.pathsLayer = new PathsLayer(this); //路径层
			this.pointLayer = new PointsLayer(this); //点集合

			//添加图层 顺序不能修改
			this.map.addLayers([
				this.warningLineLayer,
				this.windringLayer,
				this.pathsLayer,
				this.pointLayer
			]);

			this.typhoons = []; //台风列表
		},

		/**
		 * 查找台风路径
		 * @param {string} id 台风编号
		 * @return {object} 台风对象或者null
		 */
		getTyphoon: function(id) {
			var i, l, n;
			for (i = 0, l = this.typhoons.length; i < l; i++) {
				n = this.typhoons[i];
				if (n.id === id) {
					return n;
				}
			}
		},
		/**
		 * 移除台风路径
		 * @param  {string} id 台风编号
		 * @return {array}  剩下的台风集合
		 */
		removeTyphoon: function(id) {
			for (i = 0, l = this.typhoons.length; i < l; i++) {
				n = this.typhoons[i];
				if (n.id === id) {
					n.destroy();
					this.typhoons.splice(i, 1);
				}
			}
			return this.typhoons;
		},
		/**
		 * 清空台风路径
		 */
		clearTyphoon: function() {
			for (i = 0, l = this.typhoons.length; i < l; i++) {
				this.typhoons[i].destroy();
			}
			this.typhoons.length = 0;
		},
		/**
		 * 添加台风路径
		 * @param {string} id   台风编号
		 * @param {string} name 台风名称
		 * @param {type}   data 台风数据
		 * @return {obj} 新台风
		 */
		addTyphoon: function(data) {
			if (!data || this.getTyphoon(data.id)) return; //过滤空数据和编号相同的台风
			this.typhoons.push(new Typhoon(data, this));
		},
		/**
		 * 获取有效的发布者名单
		 * @param  {string} str 以否号分割的名单
		 * @return {array} 		有效的名单
		 */
		setPublishers: function(str) {
			var arr = str && str.split(","),
				arr2 = [],
				i, l, n, a;
			if (arr && arr.length) {
				for (i = 0, l = arr.length; i < l; i++) {
					n = arr[i];
					if (this.publishers[n]) {
						arr2.push(n);
					}
				}
			} else {
				for (a in this.publishers) {
					arr2.push(a);
				}
			}
			return arr2.join(",");
		},
		/**
		 * 添加弹框信息
		 * @param {string}  name       台风名称
		 * @param {string}  type       台风类型
		 * @param {object}  point      预报或者实况点信息
		 * @param {Boolean} isForecast 是否是预报点
		 */
		addPopup: function(name, type, point, isForecast,publisher) {
			var lon = point.lon,
				lat = point.lat,
				time = point.time,
				distances7 = point.lv7,
				distances10 = point.lv10,
				distances12 = point.lv12;

			var publishName = "";
			if (isForecast) {
			    var name = publishers[publisher]["alias"]||"";

			    publishName = "<li>预报：" + name + "</li>";
			}
			 
			//模板
			var html = "<div class='typopup'>" +
				"<span>" + name + "</span>" +
				"<ul>" +
              publishName+
				"<li>过去时间：" + time.substr(0, 2) + "月" + time.substr(2, 2) + "日" + time.substr(4, 2) + "时</li>" +
				"<li>中心位置：" + lon + "°E," + lat + "°N</li>" +
				"<li>最大风力：" + point.windPower + "级</li>" +
				(point.windVelocity ? "<li>最大风速：" + point.windVelocity + "m/s</li>" : "") +
				"<li>中心气压：" + point.pressure + "hPa</li>" +
				"<li>移动速度：</li>" +
				"<li>移动方向：</li>" +
				(distances7 ? "<li>7级风圈半径：" + distances7 + "km</li>" : "") +
				(distances10 ? "<li>10级风圈半径：" + distances10 + "km</li>" : "") +
				(distances12 ? "<li>12级风圈半径：" + distances12 + "km</li>" : "") +
				"</ul>" +
				"</div>";

			if (this.popup) {
				this.popup.setContentHTML(html);
				this.popup.setLonlat(lon, lat, 0, -15);
				this.popup.show();
			} else {
				this.popup = this.gis.addPopup(lon, lat, html, 0, -15, true);
			}
		},
		/**
		 * 隐藏信息框
		 */
		hidePopup: function() {
			this.popup && this.popup.hide();
		},
		/**
		 * 选中点
		 * @param  {string} id   台风编号
		 * @param  {string} time 实况时间
		 */
		changeTime: function(id, time) {
			var typhoon = this.getTyphoon(id);
			if (typhoon) {
				typhoon.changeTime(time);
				this.addPopup(
					typhoon.data.name,
					typhoon.data.type,
					typhoon.list[typhoon.getIndex(time)],
					false);
			}
		},

		/**
		 * 显示发布者图例隐藏
		 * @param  {number} x 0和正数表示从left算起，负数表示从right算起
		 * @param  {munber} y 0和正数表示从top算起，负数表示从bottom算起
		 */
		showPubliserLengend: function(x, y) {
			if (!this.publiserLegend) {
				var a, arr = [];
				for (a in this.publishers) {
					arr.push(this.publishers[a]);
				}
				this.publiserLegend = this.gis.addControl(LegendLevel, x, y, {
					content: arr,
					title: "预报机构"
				});
			}
		},
		/**
		 * 显示热带气旋等级图例隐藏
		 * @param  {number} x 0和正数表示从left算起，负数表示从right算起
		 * @param  {munber} y 0和正数表示从top算起，负数表示从bottom算起
		 */
		showLevelLengend: function(x, y) {
			//if (!this.levelLegend) {
			//	var arr = [];
			//	for (var i = 0, l = this.windPowers.length; i < l; i++) {
			//		arr.push(this.windPowers[i]);
			//	}
			//	this.levelLegend = this.gis.addControl(LegendLevel, x, y, {
			//		content: arr,
			//		title: "台风图例"
			//	});
			//}
		},
		/**
		 * 销毁
		 */
		destroy: function() {
			this.clearTyphoon();
			this.typhoons = null;

			if (this.popup) {
				this.gis.removePopup(this.popup);
				this.popup = null;
			}
		},
		CLASS_NAME: "GI.Typhoon"
	});

	return function(options) {
		return new TyphoonBox(options, this);
	};
});