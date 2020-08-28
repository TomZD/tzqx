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

	return function(json) {
	    var i, info, m,
			forecastHours, lon, lat, time, publisher,
			windPower, windVelocity, pressure,
			lv7, lv10, lv12, moveVelocity, direction,
			name = json.nameCn, type,
			forecasts = {}, //预报集合
			actual = [], //实况
            forecastInfo = json.forecastPaths,//预报路径
			fleng = forecastInfo.length,
            actualInfo = json.livePaths,//实况路径
	        aleng = actualInfo.length,
			id = json.no;
	    for (var i = 0; i < aleng; i++) {
	        var aitem = actualInfo[i];
	        actual.push({
	            direction: null,
	            forecasts: {},//{"babj":[],"rjtd":[]}
	            lat: aitem.latitude,
	            lon: aitem.longitude,
	            lv7: aitem.fenglifor7,
	            lv10: aitem.fenglifor10,
	            //lv12: aitem.longitude,
	            //moveVelocity: aitem.longitude,
	            pressure: aitem.press,
	            time: aitem.nowString,
	            windPower: aitem.windLevel,
	            windVelocity: aitem.wind
	        });

	        var forObj = actual[i]["forecasts"];
	        for (var j = 0; j < fleng; j++) {

	            var fitem = forecastInfo[j];
	            //获取某个时间点的所有类型的预报路径｛BABJ，RJTD，PGTW，VHHH，RKSL，BEHZ｝
	            if (fitem.nowString == aitem.nowString) { //"NowString": "08100200",
	                var fObj = {
	                    direction: null,
	                    lat: fitem.latitude,
	                    lon: fitem.longitude,
	                    lv7: fitem.fenglifor7,
	                    lv10: fitem.fenglifor10,
	                    //lv12: fitem.longitude,
	                    //moveVelocity: fitem.longitude,
	                    pressure: fitem.press,
	                    time: formatTime(fitem.nowString, id, fitem.interval),
	                    windPower: fitem.windLevel,
	                    windVelocity: fitem.wind
	                };
	                if (!forObj) {
	                    forObj = {};
	                } if (!forObj[fitem.publisher]) {
	                    forObj[fitem.publisher] = [];
	                }
	                forObj[fitem.publisher].push(fObj);
	            }
	        }
		}

		for (i = actual.length - 1; i >= 0; i--) {
			m = actual[i];
			//m.forecasts = forecasts[m.time];
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