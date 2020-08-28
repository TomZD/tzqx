define('publicMapFile.js', function (require, exports, module) {
	var GIS = require("lib/gigis/gi-gis");
    var Layer = require("components/map-hzqxsk");
    var windFieldImg = require("components/windFieldImg");
    var gis = GIS("map", "amap", {
        center: {
            lon: 121.095,
            lat: 28.610
        },
        zoom: 11,
        baselayer: "traffic",
        // restrictedExtent:extent,//区域范围
        disableZoom: false, //禁用滚轮事件
        documentDrag: false,
        wrapDateLine: true
    });
    gis.map.events.on({
        "click": function (e) {
        	  // 获取当前坐标
            var pixel = new OpenLayers.Pixel(e.xy.x, e.xy.y);
            var lonlat = gis.map.getLonLatFromPixel(pixel);
            var mapPoint = gis.map.tanslateLonLat(lonlat, true);
            $(".mapLon").val(mapPoint.lon.toFixed(4));
            $(".mapLat").val(mapPoint.lat.toFixed(4));
            console.log(lonlat);
        },
        "moveend": function (e) {
        }
    });
    $(".mapBtn button").click(function(){
    	$(".gisMap").css("visibility","visible");
    	$(".gisMap").css("zIndex","10000")
    })
    $(".umapBtn button").click(function(){
    	$(".mapLon").val("");
        $(".mapLat").val("");
    	$(".gisMap").css("visibility","visible");
    	$(".gisMap").css("zIndex","10000")
    })
    $(".mapClose").click(function(){
    	$(".gisMap").css("visibility","hidden");
    	$(".gisMap").css("zIndex","-10")
    })
    
    $(".confirm").click(function() {
    	$("#nlongitude").val($(".mapLon").val());
    	$("#nlatitude").val($(".mapLat").val());
    	$("#ulongitude").val($(".mapLon").val());
    	$("#ulatitude").val($(".mapLat").val());
    	//$(".gisMap").hide();
    	$(".gisMap").css("visibility","hidden");
    	$(".gisMap").css("zIndex","-10")
    })
})