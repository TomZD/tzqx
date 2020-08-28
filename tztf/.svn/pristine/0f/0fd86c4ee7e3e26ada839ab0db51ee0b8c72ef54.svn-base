define('components/typhoon.js', function(require, exports, module){ //加载台风路劲
var loadTyphoon = (function() {
    var ajaxs = [];
    var distance = 0;
    //距离富阳中心距离
    var getDistance = function(lon, lat) {
        var dx2 = Math.pow(121.54 - lon, 2);
        var dy2 = Math.pow(29.87 - lat, 2);
        return Math.sqrt(dx2 + dy2);
    };
    return function(no, clearCache,byDistance) {
        //清除未执行完的异步
        if (clearCache) {
            $.each(ajaxs, function(i, n) {
                n && n.abort();
            });
            ajaxs.length = 0;
        }
        ajaxs.push(
            $.ajax({
                url: baseUrl+"./data/typhoon/typhoonInfo_" + no + ".xml",
                cache: false,
                success: function(xml) {
                    if (xml) {
                        var json = typhoonBox.xml2json(xml);
                        var last = json.list[json.list.length - 1];
                        var time = last.time;
                        var d = getDistance(last.lon, last.lat);

                        typhoonBox.addTyphoon(json); //添加一条台风

                        //加载台风信息
                        if (byDistance && distance && d >= distance)return;

                        distance = d;
                        $("#info").html(template("infoTpl", {
                            name: json.name,
                            type: json.type,
                            no: json.id,
                            lon: last.lon,
                            lat: last.lat,
                            date: time.substr(0, 2) + "月" + time.substr(2, 2) + "日" + time.substr(4, 2) + "时",
                            AirPressure: last.pressure,
                            WindVelocity: last.windVelocity,
                            WindPower: last.windPower,
                            Level7Distance: last.lv7,
                            Level10Distance: last.lv10,
                            Level12Distance: last.lv12,
                            moveVelocity: last.moveVelocity,
                            direction: last.direction
                        }));
                    }
                }
            })
        );
    };
}());
var typhoonBox;
return function(gis) {
	typhoonBox = gis.drawTyphoon({
		publishersFilter: "BABJ"
	});
	typhoonBox.showLevelLengend(10, -10);
	gis.setCenter(121.5, 29, 4);

	return loadTyphoon;
}; 
});