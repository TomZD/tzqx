define('components/map2.js', function(require, exports, module){ var GIS = require("lib/gigis/gi-gis");
var template = require("lib/template");
var util = require("lib/util");
var root = "http://10.138.163.218";

//地图函数 封装了基本的地图图层调用
var Map = function(id) {
	this.gis = GIS(id, "bing", {
		center: {
			lon: 134.57,
			lat: 17.87
		},
		zoom: 4,
		type: "traffic"
	});

	this.gis.addControl("zoom", -10, -10);
	this.gis.addControl("mousePosition", 0, -1, {
		prefix: "经度：",
		separator: " 纬度：",
		numDigits: 4
	});
};
//加载台风路劲
Map.prototype.addTyphoon = (function() {
	//发布者色标
	var publisers = [{
		name: "BABJ",
		alias: '中央台预报'
	}, {
		name: "HZHZ",
		alias: '省台预报'
	}, {
		name: "RJTD",
		alias: '东京预报'
	}, {
		name: "RKSL",
		alias: '韩国预报'
	}, {
		name: "RCTP",
		alias: '台北预报'
	}, {
		name: "VHHH",
		alias: '香港预报'
	}, {
		name: "ZJRS",
		alias: "省台预报"
	}];
	return function(xml, no) {
		if (!xml) return;
		if (!this.typhoonBox) {
			this.typhoonBox = this.gis.drawTyphoon({
				publisers: publisers
			});
		}
		var json = this.typhoonBox.xml2json(xml);
		//以最新台风实况位置为地址中心点
		var lastPoint = json[json.length - 1];
		// this.gis.setCenter(lastPoint._lon, lastPoint._lat, 5);
		//添加一条台风
		this.typhoonBox.addTyphoon(json);

		//加载图例
		this.typhoonBox.showPubliserLengend(true, 10, -10);
		this.typhoonBox.showLevelLengend(true, 10, 10);
	};
}());
//加载台风路劲
Map.prototype.clearTyphoon = function() {
	this.typhoonBox && this.typhoonBox.clearTyphoon();
};
//加载任一点天气
Map.prototype.getWeatherBylonlat = (function() {
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
			offsetY: -26,
			ev: function() {
				var lonlat = this.getLonlat();
				// popup.setContentHTML(template.compile(tpl)(this.json));
				popup.show();
				popup.setLonlat(lonlat.lon, lonlat.lat);
			}
		}, markers)[0];
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
//加载图层
Map.prototype.addLayer = (function() {
	var tile;
	var grid;
	var legend;
	var ajax;

	//加载瓦片
	var loadTile = function(name, type, file) {
		if (type == "fy2") {
			this.gis.setZoom(5);
		}

		if (tile) {
			this.gis.removeLayer(tile);
			tile = null;
		}
		tile = this.gis.addTileLayer(
			root + "/GetTiledImage/GetTiledImage.ashx?opacity=1.0&type=" + type + "&file=" + file + "&x=${x}&y=${y}&z=${z}",
			name
		);
		tile.setOpacity(0.5);
	};
	//加载格点
	var loadGrid = function(name, type, file) {
		var that = this;
		ajax = $.ajax({
			dataType: "jsonp",
			url: root + "./data.ashx?method=getMapDrawNewestData&type=" + type + "/xml&fileName=" + file,
			success: function(json) {
				var str = json.Result;
				var xml = util.string2XML(str);
				grid = that.gis.drawContours(
					name,
					$(xml).find("RS")[0],
					0.5
				);

				legend = that.gis.addControl("legend", -10, -50, {
					content: $(xml).find("Rainbow Content").text(),
					layers: grid,
					extendClass: "legend" //外部传入的自定义样式
				});
			}
		});
	};

	return function(id) {
		var that = this;
		//清除内容
		if (grid) {
			this.gis.removeLayer(grid);
		}
		if (legend) {
			this.gis.removeControl(legend);
		}
		if (ajax) {
			ajax.abort();
			ajax = null;
		}
		if (tile) {
			this.gis.removeLayer(tile);
			tile = null;
		}

		//加载图层
		if (!id) return;

		loadFile(id, function(file, title) {
			var type = id.split("/")[0];
			if (type == "grid") {
				loadGrid.call(that, title, id, file);
			} else {
				loadTile.call(that, title, type, file);
			}
		});
	};
}());

//加载预警信号
return function(id) {
	return new Map(id);
}; 
});