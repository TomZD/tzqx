// xml转json
define(function(require) {
	//格式化时间
	var formatTime = function(time, no, add) {
		var m = time.substr(0, 2),
			d = time.substr(2, 2),
			h = time.substr(4, 2);

		if (add && no) {
			var y = no.substr(0, 4);
			var date = new Date(y + "/" + m + "/" + d + " " + h + ":00");
			var mt = date.getTime();
			var newDate = new Date(mt + add * 60 * 60 * 1000);

			m = newDate.getMonth() + 1;
			m = m > 9 ? m : "0" + m;
			d = newDate.getDate();
			d = d > 9 ? d : "0" + d;
			h = newDate.getHours();
			h = h > 9 ? h : "0" + h;

			time = "" + m + d + h;
		}

		return time;
	};

	//获取节点文本内容
	var getText = function(parent, name) {
		var d = parent.getElementsByTagName(name);
		d = d && d[0];
		d = d && d.childNodes;
		d = d && d[0];
		return d && d.nodeValue;
	};

	return function(xml) {
		var i, info, m,
			forecastHours, lon, lat, time, publisher,
			windPower, windVelocity, pressure,
			lv7, lv10, lv12, moveVelocity, direction,
			name, type,
			forecasts = {}, //预报集合
			actual = [], //实况
			infos = xml.getElementsByTagName("TyphoonInfo"),
			l = infos.length - 1,
			id = getText(infos[0], "NO");

		for (i = l; i >= 0; i--) {
			info = infos[i];
			forecastHours = getText(info, "ForecastHours");
			publisher = getText(info, "Publisher");
			time = getText(info, "Time");

			if (forecastHours && time && publisher) {
				forecastHours = +forecastHours; //转为数字，方便比较

				//预报小时数等于0且来源于北京，表示是实况数据
				if (!forecastHours) {
					if (publisher == "BABJ") {
						//其他发布机构，可能中文名和类型为空
						name = getText(info, "NameCn");
						type = getText(info, "Type");



						lon = getText(info, "Longitude");
						lat = getText(info, "Latitude");

						if (lon && lon != 9999 && lat && lat != 9999) {
							windPower = getText(info, "WindPower");
							windPower = windPower && windPower != 9999 ? windPower : 0;

							pressure = getText(info, "AirPressure");
							windVelocity = getText(info, "WindVelocity");
							lv7 = getText(info, "Level7Distance");
							lv10 = getText(info, "Level10Distance");
							lv12 = getText(info, "Level12Distance");
							moveVelocity = getText(info, "MoveVelocity");
							direction = getText(info, "Direction");

							actual.push({
								time: time,
								lon: lon,
								lat: lat,
								windPower: windPower,
								windVelocity: windVelocity,
								pressure: pressure,

								direction: direction && direction != 9999 ? direction : null,
								moveVelocity: moveVelocity && moveVelocity != 9999 ? moveVelocity : null,
								lv7: lv7 && lv7 != 9999 ? lv7 : null,
								lv10: lv10 && lv10 != 9999 ? lv10 : null,
								lv12: lv12 && lv12 != 9999 ? lv12 : null
							});
						}
					}
				} else {
					lon = getText(info, "ForecastLongitude");
					lat = getText(info, "ForecastLatitude");

					if (lon && lon != 9999 && lat && lat != 9999) {
						pressure = getText(info, "ForecastAirPressure");

						windPower = getText(info, "ForecastWindPower");
						windPower = windPower && windPower != 9999 ? windPower : null;

						m = forecasts[time];
						if (!m) {
							m = forecasts[time] = {};
						}
						if (!m[publisher]) {
							m[publisher] = [];
						}
						m[publisher].push({
							time: formatTime(time, id, forecastHours),
							lon: lon,
							lat: lat,
							pressure: pressure && pressure != 9999 ? pressure : null,
							windPower: windPower,
							windVelocity: windVelocity
						});
					}
				}
			}
		}

		for (i = actual.length - 1; i >= 0; i--) {
			m = actual[i];
			m.forecasts = forecasts[m.time];
			//数据源不一定排序过，必须按时间排序一次
			for (var aa in m.forecasts) {
				var nn = m.forecasts[aa];
				nn.sort(function(a, b) {
					return a.time < b.time ? 1 : -1;
				});
			}
		}
		return {
			id: id,
			name: name,
			type: type,
			list: actual
		};
		// {
		// 	id: 1,
		// 	name: "灿鸿",
		// 	type: "超强台风",
		// 	actual: {
		// 		time: 2015101010,
		// 		lon: "",
		// 		lat: "",
		// 		AirPressure: 11,
		// 		WindVelocity: 11,
		// 		WindPower: 1,
		// 		lv7,
		// 		lv10,
		// 		lv12,
		// 		MoveVelocity,
		// 		Direction,
		// 		forecasts: {
		// 			"BABJ": [{
		// 				lon: "",
		// 				lat: "",
		// 				AirPressure: 11,
		// 				WindVelocity: 11,
		// 				WindPower: 1,
		// 				time: ""
		// 			}]
		// 		}
		// 	}
		// }
	};
});