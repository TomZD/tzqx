/**
 * @require "components/tabs/tabs.js"
 * @require "libs/gigis.js"
 */
//计算高度

(function() {
    var elMapBox = $("#mapBox"),
        elMapBoxBd = elMapBox.find(".m-pan-bd"),
        hMapBoxHd = elMapBox.find(".m-pan-hd").innerHeight(),
        bMapBox = parseInt(elMapBox.css("border-top-width")) - parseInt(elMapBox.css("border-bottom-width"));

    resizeLayout(function(hMain) {
        elMapBoxBd.height(hMain - bMapBox - hMapBoxHd);
    });
}());

//
$("#share").tabs({
    tabBtns: ".types span",
    tabUns: ".un",
    event: "click"
});

var gis = GIS("map", {
    centerLon: 120.868835,
    centerLat: 30.014409,
    zoom: 10
});
debugger;
var markers = [];
var arr = [];

$.each(arr, function(i, n) {
    markers.push(
        gis.addMarker(gis.getLonLat(n.lon, n.lat), n.options)
    );
});


//改变点状态
setTimeout(function() {
    gis.changeMarkerStatus(markers[1], 1);
}, 2000);

//改变点位置
setTimeout(function() {
    gis.changeMarkerLonLat(markers[1], gis.getLonLat(120.404,29.916));
}, 2000);

//清空点
setTimeout(function() {
    gis.clearMarkers();
}, 8000);

//添加工具栏
var toolbar = gis.createToolbar();

//工具
$("#btnRect").click(function(event) {
    toolbar.controls[0].activate();
    toolbar.controls[1].deactivate();
	toolbar.controls[2].deactivate();
});
$("#btnCicle").click(function(event) {
    toolbar.controls[1].activate();
    toolbar.controls[0].deactivate();
	toolbar.controls[2].deactivate();
});
$("#btnPolygen").click(function(event) {
    toolbar.controls[2].activate();
    toolbar.controls[0].deactivate();
	toolbar.controls[1].deactivate();
});
$("#btnMove").click(function(event) {
    toolbar.controls[1].deactivate();
    toolbar.controls[0].deactivate();
});
$("#btnHideTools").click(function(event) {
    toolbar.controls[1].deactivate();
    toolbar.controls[0].deactivate();
    toolbar.controls[0].layer.removeAllFeatures();
});

$("#btnSaveArea").click(function() {
    toolbar.setStyle(0);
});

$("#btnDangerArea").click(function() {
    toolbar.setStyle(1);
});
$("#btnSave").click(function() {
	
	
    var geom = toolbar.controls[0].layer.features[0].geometry.components[0];
	var points = '';
	for(var i=0;i<geom.components.length;i++){
		points += geom.components[i].x + ',' + geom.components[i].y + ';' ;
	}
	points = points.substr(0,points.length-1);
	console.info(points);
	$.ajax({
		url:"/sev/alarm/getAreaJsonData",
		data:{"points":points},
		success:function(json){
			var obj = jQuery.parseJSON(json);
			var ae = obj.key1.split(",");
			var bian = obj.key2;
			console.log(bian);
			var lin="";
		
			for(var i in ae){
				var list = ae[i];
				lin+="<div class='checkbox_style'><input type='checkbox' id='town' name='town' class='filter0' value='"+list+"'></div><span name='del'>"+list+"</span>";
			}
			$("#area div[class='checkbox_style']").remove();
			$("span[name='del']").remove();
			$("#area").append(lin);
			
		}
	});
	var bounds = geom.bounds.bottom + ',' + geom.bounds.left + ',' + geom.bounds.right + ',' + geom.bounds.top;
	console.info(bounds);
});





//边界线
var drawBoundary = (function() {
	var boundary;

	return function(xmlstr) {
		boundary && boundary.destroy();

		var xml;
		if (document.all) {
			xml = new ActiveXObject("Microsoft.XMLDOM")
			xml.loadXML(xmlstr);
		} else {
			xml = new DOMParser().parseFromString(xmlstr, "text/xml")
		}
		
		boundary = gis.drawBoundary("行政边界", xml, {
			fillColor: se,
			fillOpacity: 0.81,
			strokeWidth: 2,
			strokeColor: "#d90f0f",
			strokeOpacity: 0.9
		});
	}
}());
//画点
var drawPoints =window.drawPoints=(function() {
	//var points;
	var layer = gis.addMarkers();

	return function(data,fn) {
		layer.clearMarkers();
		points = [];
		//格式化数据
		var sites = [];
		$.each(data, function(i, n) {
			sites.push({
				icon: {
					url: "../images/bg.png",
					width: 24,
					height: 24
				},
				lon: n.lngX,
				lat: n.latY,
				content: n,
				position: "tc",
				ev:fn
				
			});
		});
		//加载数据
		points = gis.addMarker(sites, layer);
		//设置提示文字
		$.each(points, function(i, n) {
			n.setText('<div class="poptext" data-val="' + n.content.status + '">'+(n.content.siteName || "提示文字")+"</div>");
		});
	}
}());
