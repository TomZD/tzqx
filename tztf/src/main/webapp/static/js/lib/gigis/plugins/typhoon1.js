/*
	台风路径
 */

//1.色标文件
//2.警戒线
//3.风圈 圆
//4.风圈的效果
//5.例子 提示框，事件配置
//6.台风名字
//7.预报线
define(function(require) {
	var Draw = require("../draw/draw");
	var Animation = require("../animation/animation");

	//实况样式
	var aPointStyle = {
		base: [{
			strokeWidth: 1,
			strokeColor: "#000",
			pointRadius: 4,
			fillColor: "#0ba"
		}, {
			strokeWidth: 1,
			strokeColor: "#000",
			pointRadius: 4,
			fillColor: "#ad0",
		}, {
			strokeWidth: 1,
			strokeColor: "#000",
			pointRadius: 4,
			fillColor: "#aa0"
		}, {
			strokeWidth: 1,
			strokeColor: "#000",
			pointRadius: 4,
			fillColor: "#cc0"
		}, {
			strokeWidth: 1,
			strokeColor: "#000",
			pointRadius: 4,
			fillColor: "#0cc"
		}, {
			strokeWidth: 1,
			strokeColor: "#000",
			pointRadius: 4,
			fillColor: "#c0c"
		}],
		hover: [{
			strokeWidth: 1,
			strokeColor: "#000",
			pointRadius: 8,
			fillColor: "#0ba"
		}, {
			strokeWidth: 1,
			strokeColor: "#000",
			pointRadius: 8,
			fillColor: "#ad0",
		}, {
			strokeWidth: 1,
			strokeColor: "#000",
			pointRadius: 8,
			fillColor: "#aa0"
		}, {
			strokeWidth: 1,
			strokeColor: "#000",
			pointRadius: 8,
			fillColor: "#cc0"
		}, {
			strokeWidth: 1,
			strokeColor: "#000",
			pointRadius: 8,
			fillColor: "#0cc"
		}, {
			strokeWidth: 1,
			strokeColor: "#000",
			pointRadius: 8,
			fillColor: "#c0c"
		}]
	};
	var aLineStyle = {
		strokeWidth: 3,
		strokeColor: "#000"
	};
	//预报样式
	var fPointStyle = {
		base: [{
			strokeWidth: 1,
			strokeColor: "#666",
			pointRadius: 4,
			fillColor: "#0ba"
		}, {
			strokeWidth: 1,
			strokeColor: "#666",
			pointRadius: 4,
			fillColor: "#ad0",
		}, {
			strokeWidth: 1,
			strokeColor: "#666",
			pointRadius: 4,
			fillColor: "#aa0"
		}, {
			strokeWidth: 1,
			strokeColor: "#666",
			pointRadius: 4,
			fillColor: "#cc0"
		}, {
			strokeWidth: 1,
			strokeColor: "#666",
			pointRadius: 4,
			fillColor: "#0cc"
		}, {
			strokeWidth: 1,
			strokeColor: "#666",
			pointRadius: 4,
			fillColor: "#c0c"
		}],
		hover: [{
			strokeWidth: 1,
			strokeColor: "#666",
			pointRadius: 8,
			fillColor: "#0ba"
		}, {
			strokeWidth: 1,
			strokeColor: "#666",
			pointRadius: 8,
			fillColor: "#ad0",
		}, {
			strokeWidth: 1,
			strokeColor: "#666",
			pointRadius: 8,
			fillColor: "#aa0"
		}, {
			strokeWidth: 1,
			strokeColor: "#666",
			pointRadius: 8,
			fillColor: "#cc0"
		}, {
			strokeWidth: 1,
			strokeColor: "#666",
			pointRadius: 8,
			fillColor: "#0cc"
		}, {
			strokeWidth: 1,
			strokeColor: "#666",
			pointRadius: 8,
			fillColor: "#c0c"
		}]
	};
	var fLineStyle = {
		strokeWidth: 1,
		strokeColor: "#000",
		strokeDashstyle: "dash"
	};

	// 台风风圈层
	var WindRingLayer = OpenLayers.Class(Draw, {
		initialize: function(box, style) {
			Draw.prototype.initialize.apply(this);
			this.style = {};
			this.setStyle(style);
			this.box = box;
			this.windrings = []; //
		},
		setStyle: function(style) {

		},
		add: function(point, style) {
			var p = this.getPoint(point);
			var features = [];
			features.push(new OpenLayers.Feature.Vector(this.getSector(p, 100000, 100, 0, 91), null, style));
			features.push(new OpenLayers.Feature.Vector(this.getSector(p, 400000, 100, 90, 91), null, style));
			features.push(new OpenLayers.Feature.Vector(this.getSector(p, 300000, 100, 180, 91), null, style));
			features.push(new OpenLayers.Feature.Vector(this.getSector(p, 200000, 100, 270, 91), null, style));
			this.windrings.push(features);

			this.addFeatures(features);
			return features;
		},
		remove: function() {
			var rings = this.windrings;
			for (var i = 0; i < rings.length; i++) {
				this.removeFeatures(rings[i]);
			}
			this.windrings = [];
		},
		moveWindRing: function(point) {
			var p = this.getPoint(point);
			var x = p.x;
			var y = p.y;
			var rings = this.windrings;
			for (var i = 0; i < rings.length; i++) {
				// rings[i]
				// this.removeFeatures(rings[i]);
			}
		},
		CLASS_NAME: "GI.Typhoon.WindRing"
	});
	// 台风路线层
	var PathsLayer = OpenLayers.Class(Draw, {
		actualStyle: aLineStyle,
		forecastStyle: [fLineStyle],
		initialize: function(box, actualStyle, forecastStyle) {
			Draw.prototype.initialize.apply(this);
			this.box = box;

			this.actualStyle = actualStyle || this.actualStyle;
			this.forecastStyleList = forecastStyle || this.forecastStyle;
			this.setStyle(actualStyle, forecastStyle);

			this.list = {}; //数据集合
		},
		setStyle: function(style) {

		},
		remove: function(no, publisher) {
			var features = this.list[no];
			if (features) {
				//删除制定台风
				if (publisher == null) {
					this.removeFeatures(features.fList);
					this.removeFeatures(features.aList);
				} else {
					if (publisher == "forecast") { //预报
						this.removeFeatures(features.forecast);
					} else if (publisher == "actual") { //实况
						this.removeFeatures(features.actual);
					} else { //单条
						if (!features.forecast) return;

						for (var i = 0, l = features.forecast.length; i < l; i++) {
							var f = features.forecast[i];
							if (f.publisher == publisher) {
								this.removeFeatures(f);
								features.forecast.splice(i, 1);
								break;
							}
						}
					}
				}
			}
		},
		add: function(no, publisher, points) {
			var path = this.getLine(points);
			var style;

			var list = this.list[no] = this.list[no] || {};

			if (publisher == "actual") {
				style = this.actualStyle;
			} else {
				style = this.forecastStyleList[0];
			}
			var feature = new OpenLayers.Feature.Vector(path, null, style);
			feature.publisher = publisher;
			this.addFeatures(feature);


			//if (this.list[no][publisher]) return;
			if (publisher == "actual") {
				list.actual = feature;
			} else {
				list.forecast = list.forecast || [];

				list.forecast.push(feature)
			}

			return feature;
		},
		_redrawPath: function(feature, lon, lat, isNew) {
			//新否是新追加的点
			var path = feature.geometry;
			if (isNew) {
				path.addPoint(this.getPoint({
					lon: lon,
					lat: lat
				}));
			} else {
				var last = path.components[path.components.length - 1]; //最后一个点

				last.x = lon;
				last.y = lat;
			}

			this.renderer.redrawNode(path.id, path, feature.style || {}, this.id);
		},
		CLASS_NAME: "GI.Typhoon.Paths"
	});
	// 台风预报点层
	var PointsLayer = OpenLayers.Class(Draw, {
		initialize: function(box, style, callbacks) {
			Draw.prototype.initialize.apply(this);
			this.box = box;
			this.style = style || {
				actual: aPointStyle,
				forecast: fPointStyle
			};
			this.list = {}; //集合

			// 配置事件
			callbacks = callbacks || {};
			this.evtClick = callbacks.click || function() {};
			this.evtMouseover = callbacks.mouseover || function() {};
			this.evtMouseout = callbacks.mouseout || function() {};
		},
		add: function(points) {
			if (!OpenLayers.Util.isArray(points)) {
				points = [points];
			}

			var features = [];
			for (var i = 0, l = points.length; i < l; i++) {
				features.push(this._addPoint(points[i]));
			}
			this.addFeatures(features);
			return features;
		},
		_addPoint: function(point) {
			//如果存在相同的点，将不会被覆盖，小心使用
			var no = point.no;
			var publisher;
			var style;
			if (point.forecastHours == "0") {
				publisher = "actual";
				style = this.style.actual;
			} else {
				publisher = point.publisher;
				style = this.style.forecast;
			};

			var t = this.list[no];
			if (!t) {
				t = this.list[no] = {};
			}

			var r = t[publisher];
			if (!r) {
				r = t[publisher] = [];
			}

			var feature = new OpenLayers.Feature.Vector(
				this.getPoint(point),
				null,
				style.base[point.windPower - 8] || style.base[0]
			);
			feature.info = point;
			r.push(feature);

			return feature;
		},
		remove: function(no, publisher) {
			var features = this.list[no];
			if (features) {
				//删除制定台风
				if (publisher == null) {
					for (var a in features) {
						this.removeFeatures(features[a]);
					}
				} else {
					if (publisher == "actual") { //实况
						this.removeFeatures(features.actual);
					} else if (publisher == "forecast") { //预报
						for (var a in features) {
							if (a != "actual") {
								var f = features[a];
								this.removeFeatures(f);
								delete features[a];
							}
						}
					} else { //单条
						for (var a in features) {
							if (a == publisher) {
								var f = features[a];
								this.removeFeatures(f);
								delete features[a];
								break;
							}
						}
					}
				}
			}
		},
		//事件
		eventListeners: {
			featureclick: function(e) {
				var info = e.feature.info;
				if (info.forecastHours == "0") {
					//加载风圈
					//加载预测路径
					this.box.showForcast(info.no, info.time);
				}



			},
			featureover: function(e) {
				var info = e.feature.info;
				var style;
				if (info.forecastHours == "0") {
					style = this.style.actual.hover;
				} else {
					style = this.style.forecast.hover;
				};
				this.drawFeature(e.feature, style[info.windPower - 8] || style[0]);
				//展示预测点信息

				
			},
			featureout: function(e) {
				var info = e.feature.info;
				var style;
				if (info.forecastHours == "0") {
					style = this.style.actual.base;
				} else {
					style = this.style.forecast.base;
				};
				this.drawFeature(e.feature, style[info.windPower - 8] || style[0]);
				//关闭预测点信息
			}
		},
		CLASS_NAME: "GI.Typhoon.Points"
	});
	// 台风容器
	var TyphoonBox = OpenLayers.Class({
		actualPublisher: "BABJ", //默认选择的实况发布者
		initialize: function(options, map) {
			this.map = map;
			options = options || {};

			//创建图层
			this.windringLayer = new WindRingLayer(this);
			this.lineLayer = new PathsLayer(this);
			this.pointLayer = new PointsLayer(this, options.style, options.ev);

			this.map.addLayers([
				this.windringLayer,
				this.lineLayer,
				this.pointLayer
			]);

			this.typhoons = []; //台风列表

			//配置
			this.speed = options.speed || 0.02;
			this.step = options.step || 2;

			//图例
			if (options.legend) {

			}

			//风圈
			if (options.warningLine) {
				this.loadWarningLine(options.loadWarningLine)
			}


		},
		//xml格式数据转换成json
		formatXml: function(xml) {
			var forecasts = {}, //预报集合
				actual = []; //实况

			var infos = xml.getElementsByTagName("TyphoonInfo");
			//倒序
			for (var i = infos.length - 1; i > 0; i--) {
				var info = infos[i],
					forecastHours = info.getElementsByTagName("ForecastHours")[0].textContent,
					publisher = info.getElementsByTagName("Publisher")[0].textContent,
					obj = {
						forecastHours: forecastHours,
						publisher: publisher,
						time: info.getElementsByTagName("Time")[0].textContent,
						no: info.getElementsByTagName("NO")[0].textContent,
						cName: info.getElementsByTagName("NameCn")[0].textContent,
						type: info.getElementsByTagName("Type")[0].textContent,
						timeString: info.getElementsByTagName("TimeString")[0].textContent,
						windPower: info.getElementsByTagName("WindPower")[0].textContent,
						windVelocity: info.getElementsByTagName("WindVelocity")[0].textContent,
						pressure: info.getElementsByTagName("AirPressure")[0].textContent
					},
					lonlat;

				if (forecastHours == "0") {
					if (publisher == this.actualPublisher) { //预报小时数等于0且来源于北京，表示是实况数据
						lonlat = this.map.tanslateLonLat(new OpenLayers.LonLat(
							info.getElementsByTagName("Longitude")[0].textContent,
							info.getElementsByTagName("Latitude")[0].textContent
						));
						obj.lon = lonlat.lon;
						obj.lat = lonlat.lat;
						actual.push(obj);
					}
				} else {
					if (!forecasts[publisher]) {
						forecasts[publisher] = [];
					}
					lonlat = this.map.tanslateLonLat(new OpenLayers.LonLat(
						info.getElementsByTagName("ForecastLongitude")[0].textContent,
						info.getElementsByTagName("ForecastLatitude")[0].textContent
					));
					obj.lon = lonlat.lon;
					obj.lat = lonlat.lat;
					forecasts[publisher].push(obj);
				}
			}
			return {
				no: actual[0] && actual[0].no,
				actual: actual,
				forecasts: forecasts
			};
		},
		//设置样式
		setStyle: function(style) {},
		//查找台风路径
		getTyphoon: function(no) {
			var typhoon;
			for (var i = 0, l = this.typhoons.length; i < l; i++) {
				var d = this.typhoons[i]
				if (d.no == no) {
					typhoon = d;
					break;
				}
			}
			return typhoon;
		},
		//移除台风路径
		removeTyphoon: function(no) {
			var typhoon = this.getTyphoon(no);
			if (typhoon) {

			}
		},
		//添加台风路径
		addTyphoon: function(json) {
			if (!json) return;

			var no = json.no;
			var points = json.actual;


			//如果编号相同不执行
			if (this.getTyphoon(no)) return;
			this.typhoons.push(json);

			var lineLayer = this.lineLayer;
			var pointLayer = this.pointLayer;
			var start = points[0];
			var feature = lineLayer.add(no, "actual", [start, start]); //创建实况路径
			var i = 0;
			var wFeature = this.showWindRing(start, {
				fillColor: "#ff0",
				fillOpacity: 0.6,
				strokeWidth: 0
			});

			var animation = new Animation.linear(points, this.step, this.speed, function(lon, lat) {
					lineLayer._redrawPath(feature, lon, lat); //绘制实况线
					//移动风圈
				},
				function(lon, lat) {
					lineLayer._redrawPath(feature, lon, lat, true); //绘制实况线
					pointLayer.add(points[i++]); //创建实况点
					//重绘风圈
				},
				function(lon, lat) {
					pointLayer.add(points[i++]); //创建实况点

					animation.destroy();
					animation = null;
				}
			);
		},
		//绘制预报路径
		showForcast: function(no, time, start) {
			var typhoon = this.getTyphoon(no);
			if (!typhoon) return;

			this.hideForcast(no);

			//实况数据
			if (!start) {
				var actual = typhoon.actual;
				var start;
				for (var i = 0, l = actual.length; i < l; i++) {
					var p = actual[i];
					if (p.time == time) {
						start = {
							lon: p.lon,
							lat: p.lat
						};
						break;
					}
				}
			}

			if (!start) return; //依然没有，返回

			//预报数据
			var f = typhoon.forecasts;
			for (var a in f) {
				var arr = f[a];
				var points = [];
				for (var i = 0, l = arr.length; i < l; i++) {
					var p = arr[i];
					if (p.time == time) {
						points.push(p);
					}
				}
				//加载先后顺序不能变
				this.pointLayer.add(points);

				points.push(start);
				this.lineLayer.add(no, a, points);
			}
		},
		//隐藏预报路径
		hideForcast: function(no) {
			var typhoon = this.getTyphoon(no);
			if (!typhoon) return;
			this.pointLayer.remove(no, "forecast");
			this.lineLayer.remove(no, "forecast");
		},
		//添加风圈
		showWindRing: function(point, style) {
			if (this.windringLayer) {
				return this.windringLayer.add(point, style);
			}
		},
		moveWindRing: function(point) {
			this.windringLayer && this.windringLayer.moveWindRing(point);
		},
		//移除风圈
		removeWindRing: function() {
			this.windringLayer && this.windringLayer.remove();
		},
		//加载警戒线
		loadWarningLine: function(json) {
			if (json) {


				this.warningLine = {};
			}
		},
		//加载色标
		loadLegend: function() {},
		destroy: function() {
			this.typhoones = null;
			this.warningLine && this.warningLine.destroy();
			this.legend && this.legend.destroy();
		},
		CLASS_NAME: "GI.Typhoon"
	});

	return function(options) {
		return new TyphoonBox(options, this.map);
	}
});