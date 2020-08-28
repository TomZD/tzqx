define(function(require) {
	var Animation = require("../../animation/animation");

	return OpenLayers.Class({
		/**
		 * 初始化
		 * @param  {string} id   台风编号
		 * @param  {string} name 台风名称
		 * @param  {object} data 台风数据
		 * @param  {object} box  台风容器
		 */
		initialize: function(data, box) {
			if (!data || !box) return;

			this.box = box;
			this.forecasts = {}; //预报路径集合
			this.actual = {}; //实况集合
			this.windring = null; //风圈

			this.data = this.format(data);
			this.id = data.id;
			this.list = data.list;
			this.index = null; //当前选中的条目

			this.loadActual();
		},
		/**
		 * 数据格式化
		 * @param  {object} data   台风数据
		 * @return {object} 格式化后的数据
		 */
		format: function(data) {
			var a, i, l, ll, n, m, f, lonlat, list, map = this.box.map;
			list = data.list;
			for (i = 0, l = list.length; i < l; i++) {
				n = list[i];
				lonlat = map.tanslateLonLat(new OpenLayers.LonLat(n.lon, n.lat));
				n._lon = lonlat.lon;
				n._lat = lonlat.lat;

				for (a in n.forecasts) {
					f = n.forecasts[a];

					for (j = 0, ll = f.length; j < ll; j++) {
						m = f[j];
						lonlat = map.tanslateLonLat(new OpenLayers.LonLat(m.lon, m.lat));
						m._lon = lonlat.lon;
						m._lat = lonlat.lat;
					}
				}
			}
			return data;
		},
		/**
		 * 获取经纬度数据
		 * @param {array} data 数据
		 * @return {array} 实况数据
		 */
		getLonlats: function(data) {
			var j, l, n, arr = [];
			if (data) {
				for (j = 0, l = data.length; j < l; j++) {
					n = data[j];
					arr.push({
						lon: n._lon,
						lat: n._lat
					});
				}
			}
			return arr;
		},
		/**
		 * 加载实况数据
		 */
		loadActual: function() {
			var box = this.box,
				pathsLayer = box.pathsLayer,
				pointLayer = box.pointLayer,
				that = this,
				id = this.id,
				name = this.data.name,
				list = this.list;


			var lonlats = this.getLonlats(list); //实况经纬度数据
			var start = lonlats[0]; //起始点
			var points = this.actual.points = [];
			var path = this.actual.path = pathsLayer.add([start]); //创建实况路径

			this.nameLabel=box.namesLayer.add(name, start.lon, start.lat); //台风名称

			if(lonlats && lonlats.length>1){
				this.animation = new Animation.linear(
					lonlats, 2, 0.02,
					function(lon, lat) {
						pathsLayer.redrawPath(path, lon, lat);
						that.moveWindring(lon, lat);
					},
					function(lon, lat, index) {
						var p = list[index];
						pathsLayer.redrawPath(path, lon, lat); //绘制实况线
						points.push(
							pointLayer.add(p, that)
						);
						that.addWindring(p); //转折点，使用新的风圈
					},
					function(lon, lat) {
						points.push(
							pointLayer.add(list[list.length - 1], that)
						);
						that.animation.destroy();
						that.animation = null;

						that.changeTime(); //加载预报
					}
				);
			}else{
				points.push(
					pointLayer.add(list[0], this)
				);
				this.addWindring(start); //风圈
				this.changeTime(); //加载预报
			}
		},
		/**
		 * 获取数据索引
		 * @param  {string} time 实况点时间
		 */
		getIndex: function(time) {
			var i, l = this.list.length;
			if (time) {
				for (i = 0; i < l; i++) {
					if (this.list[i].time == time) {
						return i;
					}
				}
			} else {
				return l - 1;
			}
		},
		/**
		 * 改变起报时间并加载预报数据
		 * @param {string} time 起报时间
		 */
		changeTime: function(time) {
			var a, arr, i, str = this.box.publishersFilter;

			//删除已有预报线路
			for (a in this.forecasts) {
				this.removeForecast(a);
			}

			this.index = this.getIndex(time);

			//加载新的预报线路
			if (str) {
				arr = str.split(",");
				for (i = arr.length - 1; i >= 0; i--) {
					this.addForecast(arr[i]);
				}
			}

			//修改风圈
			this.addWindring(this.list[this.index]);

			//显示弹出框
		},
		/**
		 * 加载预报数据
		 * @param  {string} publisher 发布者
		 */
		addForecast: function(publisher) {
			var i, l, ll, n, m, data,
				points = [],
				box = this.box,
				pointLayer = box.pointLayer,
				pathsLayer = box.pathsLayer,
				index = this.index;

			for (i = index; i >= 0; i--) {
				m = this.list[i];
				data = m && m.forecasts && m.forecasts[publisher];
				if (data) {
					this.forecasts[publisher] = {
						points: pointLayer.adds(data, this, true,publisher),
						path: pathsLayer.add(
							this.getLonlats(data).concat([{
								lon: m._lon,
								lat: m._lat
							}]),
							publisher
						)
					};
					break;
				}
			}
		},
		/**
		 * 删除预报数据
		 * @param  {string} publisher 发布者
		 */
		removeForecast: function(publisher) {
			var i, l, box = this.box;

			var d = this.forecasts[publisher];
			if (d) {
				box.pathsLayer.remove(d.path);
				box.pointLayer.remove(d.points);

				delete this.forecasts[publisher];
			}
		},
		/**
		 * 添加风圈
		 * @param {object} data 实况或预报点数据
		 */
		addWindring: function(data) {
			this.removeWindring();
			this.windring = this.box.windringLayer.add(data._lon, data._lat, data.lv7, data.lv10, data.lv12);
		},
		/**
		 * 删除风圈
		 */
		removeWindring: function() {
			if (this.windring) {
				this.box.windringLayer.remove(this.windring);
				this.windring = null;
			}
		},
		/**
		 * 移动风圈
		 * @param  {number} lon 经度
		 * @param  {number} lat 纬度
		 */
		moveWindring: function(lon, lat) {
			if (this.windring)
				this.box.windringLayer.move(lon, lat, this.windring);
		},
		/**
		 * 销毁台风
		 */
		destroy: function() {
			//结束未结束的动画
			if (this.animation) {
				this.animation.stop();
				this.animation = null;
			}

			this.removeWindring();

			//删除已有预报线路
			for (var a in this.forecasts) {
				this.removeForecast(a);
			}
			this.forecasts = null;

			//实况
			this.box.pathsLayer.remove(this.actual.path);
			this.box.pointLayer.remove(this.actual.points);
			this.actual = null;

			//删除名字
			this.box.namesLayer.remove(this.nameLabel);

			//其他引用
			this.box = null;
			this.data = null;
		}
	});
});