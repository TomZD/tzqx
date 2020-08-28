define('components/map.js', function(require, exports, module){ var template = require("lib/template");
var util = require("lib/util");
var gis;
var markers;
var root = "http://10.138.163.218";

//返回宁波
//定位
//拖动点

//图例
//缩放
//台风路径，台风信息
//实况

//加载图层
var addLayer = (function() {
	var tile;
	var grid;
	var legend;
	var ajax;

	//加载瓦片
	var loadTile = function(name, type, file) {
		if (type == "fy2") {
			gis.setZoom(5);
		}

		if (tile) {
			gis.removeLayer(tile);
			tile = null;
		}
		tile = gis.addTileLayer(
			root + "/GetTiledImage/GetTiledImage.ashx?opacity=1.0&type=" + type + "&file=" + file + "&x=${x}&y=${y}&z=${z}",
			name
		);
		tile.setOpacity(0.5);
	};
	//加载格点
	var loadGrid = function(name, type, file) {
		ajax = $.ajax({
			dataType: "jsonp",
			url: root + "/data.ashx?method=getMapDrawNewestData&type=" + type + "/xml&fileName=" + file,
			success: function(json) {
			    var str = json.Result;
			    var xml = util.string2XML(str);
			    grid = gis.drawContours(
					name,
					$(xml).find("RS")[0],
					0.5
				);

			    legend = gis.addControl("legend", -10, -50, {
			        content: $(xml).find("Rainbow Content").text(),
			        layers: grid,
			        extendClass: "legend" //外部传入的自定义样式
			    });
			}
		});
	};
	//加载文件
	var loadFile = (function() {
		var ajax;
		return function(type, callback) {
			if (ajax) {
				ajax.abort();
				ajax = null;
			}
			ajax = $.ajax({
				url: root + "/data.ashx?method=getMapDrawList&topn=1&type=" + type,
				dataType: "jsonp",
				success: function(json) {
					var data = json.Result[0];
					if (data && typeof callback == "function") {
						callback(data.FileName, data.Title);
					}
				}
			});
		};
	}());

	return function(id) {
		//清除内容
		if (grid) {
			gis.removeLayer(grid);
		}
		if (legend) {
			gis.removeControl(legend);
		}
		if (ajax) {
			ajax.abort();
			ajax = null;
		}
		if (tile) {
			gis.removeLayer(tile);
			tile = null;
		}

		//加载图层
		if (!id) return;

		loadFile(id, function(file, title) {
			var type = id.split("/")[0];
			if (type == "grid") {
				loadGrid(title, id, file);
			} else {
				loadTile(title, type, file);
			}
		});
	};
}());

//高德搜索
var search = (function() {
	var ajax;
	var url = "http://restapi.amap.com/v3/place/text?s=rsv3&offset=10&page=1&language=zh_cn";
	var region = "0574"; //辅助城市宁波区号
	var key = "e391324389dc6b9749f7f5a61c1943df"; //应用key
	var tpl = "<%\r\n\tfor(var i=0,l=$data.length;i<l;i++){\r\n\t\tvar n=$data[i];\r\n%>\r\n\t<li data-lonlat=\"<%=n.location%>\">\r\n\t\t<b><%=n.name%></b>\r\n\t\t<%if(n.address){%>\r\n\t\t\t<span>\r\n\t\t\t\t<%=n.address%>\r\n\t\t\t</span>\r\n\t\t<%}%>\r\n\t</li>\r\n<%}%>";

	return function(query, el) {
		if (ajax) {
			ajax.abort();
			ajax = null;
		}
		ajax = $.ajax({
			dataType: "jsonp",
			url: url,
			data: {
				output: "json",
				keywords: query,
				city: region,
				key: key
			},
			success: function(json) {
				if (json && json.status) {
					el.html(template.compile(tpl)(json.pois));
				} else {
					el.html("");
				}
			}
		});
	};
}());

//获取任一点天气
var getWeatherBylonlat = function(lon, lat, callback) {
	$.ajax({
		url: root + "/data.ashx?method=getWeatherByLonlat&lonlat=" + lon + "," + lat,
		dataType: "jsonp",
		success: function(json) {
			if (json && json.Status) {
				callback(json.Result);
			}
		},
		error: function(message) {
			// alert("获取天气失败");
		}

	});
};
//任一点天气
var anywhereWeather = (function() {
	var popup;
	var marker;
	var tpl = "<div class=\"m-map-maxpop m-weatherbox\">\r\n\t<div class=\"m-weatherbox-hd\">\r\n        当前位置：<%=area%>\r\n    </div>\r\n    <div class=\"m-weatherbox-bd\">\r\n    \t<div class=\"actual\">\r\n    \t\t<div class=\"img\">\r\n    \t\t\t<img src=\"/static/images/weather/d<%=actual.weatherCode%>.png\">\r\n    \t\t</div>\r\n\t\t\t<div class=\"temp\">\r\n\t\t        <%=actual.temp%><sup>℃</sup>\r\n\t\t    </div>\r\n\t\t\t<div class=\"wind\"><%=actual.windDirection%><%=actual.windLevel%>级</div>\r\n\t\t\t<div class=\"visi\">能见度：<%=actual.visi%>米</div>\r\n\t\t\t<%if(actual.rain){%>\r\n\t\t\t\t<div class=\"rain\">降水量：<%=actual.rain%>mm</div>\r\n\t\t\t<%}%>\r\n    \t</div>\r\n\t    <div class=\"m-weahterlist\">\r\n\t        <ul><%\r\n\t                for(var j=0,l=hour24s.length;j<l;j++){\r\n\t                    var n=hour24s[j];\r\n\t                    var date=n.dateTime.substr(3,2)+\"时\";\r\n\t            %><li data-role=\"<%=n.weatherCode%>\">\r\n\t                <span><%=date%></span>\r\n\t                <img src=\"/static/images/weather/d<%=n.weatherCode%>.png\">\r\n\t                <span><%=n.weather%></span>\r\n\t                <span><%=n.temp%>℃</span>\r\n\t                <span><%=n.WindDirect%>风<%=n.WindVelocity%>级</span>\r\n\t            </li><%}%></ul>\r\n\t        <div class=\"btns\">\r\n\t            <i class=\"prev-btn\">&lt;</i>\r\n\t            <i class=\"next-btn\">&gt;</i>\r\n\t        </div>\r\n\t    </div>\r\n    </div>\r\n</div>";
	return function(name, lon, lat) {
		marker && markers.removeMarker(marker);
		marker = gis.addMarker({
			url: "/static/images/marker.png",
			width: 20,
			height: 26,
			lon: lon,
			lat: lat,
			offsetX: -10,
			offsetY: -26
		}, markers)[0];

		//可拖动的点
		gis.markerDrag(marker, {
			onClick: function() {
				// alert("click")
			}
		});



		// marker.json = json;
		gis.setCenter(lon, lat, 12);

		getWeatherBylonlat(lon, lat, function(json) {
			json.area = name;
			var content = template.compile(tpl)(json);
			if (popup) {
				popup.setContentHTML(content);
				popup.show();
				popup.setLonlat(lon, lat);
			} else {
				popup = gis.addPopup(lon, lat, content, 0, -26, true);

				$(popup.div).find(".m-weahterlist").eq(0).horizontalScroll({
					sizeAuto: true,
					elRight: $(popup.div).find(".m-weahterlist").eq(0).find(".next-btn"), //上一页
					elLeft: $(popup.div).find(".m-weahterlist").eq(0).find(".prev-btn"), //下一页
					isLoop: true //是否循环播放
				});
			}
		});
	};
}());

//地区搜索
var searchByInput = (function() {
	var el = $(".m-map-search");
	var label = el.find("label");
	var timer;
	var span = label.find('span');
	var input = label.find('input');
	var tipsbox = label.find('ul');
	//实时查询
	input.bind("input propertychange", function(event) {
		var txt = $(this).val();
		if (txt) {
			span.hide();
			tipsbox.html("");
			tipsbox.show();

			if (timer) {
				window.clearTimeout(timer);
			}
			timer = setTimeout(function() {
				search(txt, tipsbox);
			}, 500);
		} else {
			span.show();
			tipsbox.hide();
		}
	});

	//查询按钮
	el.find("button").bind("click", function(event) {
		input.trigger("input");
	});

	//选择
	tipsbox.delegate('li', 'click', function(event) {
		//输入框的值
		var v = $(this).find("b").text();
		input.val(v);
		//定位
		var ll = $(this).attr("data-lonlat");
		if (ll) {
			ll = ll.split(",");
			anywhereWeather(v, ll[0], ll[1]);
		}
		//
		tipsbox.hide();
	});
}());

//自动定位
var autoLocation = function() {
	//HTML5获取经纬度
	if (navigator.geolocation) {
		var locBtn = $(".btn-location");
		locBtn.show().click(function() {
			$(this).html("定位中..");

			navigator.geolocation.getCurrentPosition(
				function(position) {
					var lon = position.coords.longitude;
					var lat = position.coords.latitude;
					anywhereWeather("您的位置", lon, lat);
					locBtn.html("<i class='ico-20 ico-20-location'></i>定位");
				},
				function(error) {
					locBtn.html("<i class='ico-20 ico-20-location'></i>定位");
					alert("定位失败");
				}, {
					enableHighAccuracy: true,
					timeout: 2000
				}
			);
		});
	} else {
		// alert("浏览器不支持html5来获取地理位置信息");
		// 默认定位到宁波中心
	}
};

//台风
var typhoon = function() {
	//如果有台风，加载台风
	var modeTyphoon = require("mode/typhoonList");
	var loadTyphoon = require("components/typhoon")(gis);
	modeTyphoon(function(json) {
		$.each(json, function(i, n) {
			if (!n.isHistory) {
				loadTyphoon(n.no);
			}
		});
	});

	//按钮
	var btnShow = {};
	$.each(json, function(i, n) {
		var type = n.type;
		var level = n.level;
		if (type == "台风") {
			if (level == "红色" && level == "橙色") {
				btnShow.showWind = true;
				btnShow.showRadar = true;
			} else {
				btnShow.showCloud = true;
			}
		}
		if (type == "大风") {
			btnShow.showWind = true;
		}
		if (type == "寒潮" || type == "道路结冰" || type == "高温" || type == "干旱") {
			btnShow.showTemp = true;
		}
		if (type == "空气污染" || type == "雾霾") {
			btnShow.showVis = true;
		}
		if (type == "暴雨" || type == "雷电" || type == "冰雹") {
			btnShow.showWind = true;
			btnShow.showRain = true;
		}
		if (type == "霜冻" || type == "沙尘暴") {
			btnShow.showCloud = true;
		}
		if (type == "暴雪") {
			btnShow.showRadar = true;
		}
	});
	// var tpl3 = __inline('btns.tpl');
	// $(".m-map-btns").html(template.compile(tpl3)(btnShow));
};

//区县实况
var loadCountryActual = (function() {
	var tpl = "<div class='m-map-minpop'>\r\n\t<%=area%>\r\n\t<img \r\n\t\tsrc=\"/static/images/weather/d<%=actual.weatherCode%>.png\" \r\n\t\ttitle=\"<%=actual.weatherName%>\" \r\n\t\tsrc=\"<%=actual.weatherName%>\"\r\n\t\tclass=\"img-weather\"\r\n\t>\r\n\t<%=actual.temp%>℃\r\n\t<%\r\n\t\tif(warns && warns.length){\r\n\t\t\tfor(var i=0,l=warns.length;i<l;i++){\r\n\t\t\t\tvar n=warns[i];\r\n\t%>\r\n\t\t<img src=\"<%=n.url%>\" class=\"img-warn\" title=\"<%=n.type%><%=n.level%>\"/>\r\n\t<%\r\n\t\t\t}\r\n\t\t}\r\n\t%>\r\n</div>"; //简单模板
	var tpl2 = "<div class=\"m-map-maxpop m-weatherbox\">\r\n\t<div class=\"m-weatherbox-hd\">\r\n        当前位置：<%=area%>\r\n    </div>\r\n    <div class=\"m-weatherbox-bd\">\r\n    \t<div class=\"actual\">\r\n    \t\t<div class=\"img\">\r\n    \t\t\t<img src=\"/static/images/weather/d<%=actual.weatherCode%>.png\">\r\n    \t\t</div>\r\n\t\t\t<div class=\"temp\">\r\n\t\t        <%=actual.temp%><sup>℃</sup>\r\n\t\t    </div>\r\n\t\t\t<div class=\"wind\"><%=actual.windDirection%><%=actual.windLevel%>级</div>\r\n\t\t\t<div class=\"visi\">能见度：<%=actual.visi%>米</div>\r\n\t\t\t<%if(actual.rain){%>\r\n\t\t\t\t<div class=\"rain\">降水量：<%=actual.rain%>mm</div>\r\n\t\t\t<%}%>\r\n    \t</div>\r\n\t    <div class=\"m-weahterlist\">\r\n\t        <ul><%\r\n\t                for(var j=0,l=hour24s.length;j<l;j++){\r\n\t                    var n=hour24s[j];\r\n\t                    var date=n.dateTime.substr(3,2)+\"时\";\r\n\t            %><li data-role=\"<%=n.weatherCode%>\">\r\n\t                <span><%=date%></span>\r\n\t                <img src=\"/static/images/weather/d<%=n.weatherCode%>.png\">\r\n\t                <span><%=n.weather%></span>\r\n\t                <span><%=n.temp%>℃</span>\r\n\t                <span><%=n.WindDirect%>风<%=n.WindVelocity%>级</span>\r\n\t            </li><%}%></ul>\r\n\t        <div class=\"btns\">\r\n\t            <i class=\"prev-btn\">&lt;</i>\r\n\t            <i class=\"next-btn\">&gt;</i>\r\n\t        </div>\r\n\t    </div>\r\n    </div>\r\n</div>"; //详情模板
	return function(dataActual, dataWarns, clickEv) {
		if (!dataActual) return;
		//合并预警数据
		if (dataWarns) {
			$.each(dataActual, function(i, n) {
				var arr = [];
				var area = n.area;
				$.each(dataWarns, function(i, n) {
					if (n.publishstation == area) {
						arr.push(n);
					}
				});
				n.warns = arr;
			});
		}
		//加载数据
		$.each(dataActual, function(i, n) {
			var content = template.compile(tpl)(n);
			var lonlat = n.lonlat.split(",");
			var lon = lonlat[0];
			var lat = lonlat[1];
			var popup = gis.addPopup(lon, lat, content, 0, -4, false);

			var marker = gis.addMarker({
				url: "./static/images/dot.png",
				width: 16,
				height: 16,
				lon: lon,
				lat: lat,
				offsetX: -8,
				offsetY: -8,
				content: "cm",
				ev: clickEv
			}, markers)[0];

			// marker.popup2 = popup2;
			marker.popup = popup;
			marker.json = n;

		});
	};
}());
return function(g) {
	gis = g;
	markers = gis.addMarkers();

	return {
		loadCountryActual: loadCountryActual, //区县实况
		addLayer: addLayer
	};
}; 
});