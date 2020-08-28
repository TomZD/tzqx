var formpopdiv;//生成弹窗函数声明为公共方法
var gisobject;//将地图对象声明为公共变量
define('YJtyphoonMap.js', function (require, exports, module) {
    //地图部分
    var GIS = require("lib/gigis/gi-gis");
    var Typhoons = require("components/map-typhoon");
    var Layer = require("components/map-layer");
    
    var box = $(".typhoonlive");
    var gis;
    
    //初始化gis
    var gis = GIS("map", "amap", {
        center: {
            lon: 121.57,
            lat: 29.87
        },
        zoom: 4,
        baselayer: "traffic",
        wrapDateLine: true,
        documentDrag: true
    });
    gis.addControl("mousePosition", -250, 680, {
        prefix: "经度：",
        separator: " 纬度：",
        numDigits: 3
    });
    
    Typhoons(gis, box, null, true);
    
    gisobject = gis;
    gismap = gis.map;
    gismap.events.register("click", gismap, onMapClick);
    function onMapClick() {
        $("#TYname").blur();//地图上注册点击事件，使搜索框失去焦点
    }
var pop;
//生成弹窗
 formpopdiv=function(point) {
            if (pop && pop.arrow) {
                pop.destroy();
            }
            var name = $("#BindTyname").html().replace(/\d+/g, '');//取 洛坦201626中的中文
            var popid = $("#BindTyname").html().replace(/[^0-9]+/g, '');//取 洛坦201626中的数字
            var lon = point.lon,
				lat = point.lat,
				time = point.time,
				distances7 = point.lv7,
				distances10 = point.lv10,
				distances12 = point.lv12;
            //模板
            var html = "<div class='typopup'>" +
                "<a class='popdivclose' id='popdivclose' data-val='" + popid + "'></a>" +
				"<span style='background:#ec6a86'>" + name + "</span>" +
				"<ul>" +
				"<li>过去时间：" + time.substr(0, 2) + "月" + time.substr(2, 2) + "日" + time.substr(4, 2) + "时</li>" +
				"<li>中心位置：" + lon + "°E," + lat + "°N</li>" +
				"<li>最大风力：" + point.windPower + "级</li>" +
				(point.windVelocity ? "<li>最大风速：" + point.windVelocity + "m/s</li>" : "") +
				"<li>中心气压：" + point.pressure + "hPa</li>" +
				//"<li>移动速度：</li>" +//暂无该字段，此条数据隐藏
				(point.direction ? +"<li>移动方向：</li>" : "") +
				(distances7 ? "<li>7级风圈半径：" + distances7 + "km</li>" : "") +
				(distances10 ? "<li>10级风圈半径：" + distances10 + "km</li>" : "") +
				(distances12 ? "<li>12级风圈半径：" + distances12 + "km</li>" : "") +
				"</ul>" +
				"</div>";
            pop = gis.addPopup(point.lon, point.lat, html, 0, 0, false);
            pop.id = "clickpop";
            //绑定关闭事件
            $("#popdivclose").on("click", function () {
                if (pop && pop.arrow) {
                    pop.destroy();
                    $("#TydetailTab tr").removeClass("current");
                }
            });
 }
});
//定义地图上的台风点实现与右侧台风类表关联
function linktoRightlist(id, point) {
    var choseBox = $("#selectTyObj").find(".windBox");
    $.each(choseBox, function (i, item) {
        var itemId = $(item).attr("data-id");
        if (itemId == id) {
            $(item).click();
        }
    });
    var timetag = point.time;
    $("#TydetailTab").find("tr").removeClass("current");
    $("#TydetailTab").find("tr[data-id='" + id + "&" + timetag + "']").addClass("current");
    var troffsettop = $("#TydetailTab").find("tr[data-id='" + id + "&" + timetag + "']").position().top;//取该行相对与父元素的偏移量
    var Isscroll = $("table.windTab .scroller_block .scroll_absolute");
    if (Isscroll && Isscroll != "") {
        var contentH = $(".detailInner").height();
        if (troffsettop <= contentH) {
            Isscroll.animate({ "top": "0px" });
            $(".scroll_drag").animate({ "top": "0px" });
            return;
        }
        var indextab = parseInt(troffsettop / contentH);
        Isscroll.animate({ top: -contentH * indextab + "px" });
       // 滚轴定位
        var scrollH = $(".scroll_drag").height();
        var tbbodyH = $("#TydetailTab").height();
        var scrolltop = scrollH * troffsettop / tbbodyH;//偏移量
        var maxscrolltop = contentH - scrollH;//最大偏移量
        if (scrolltop > maxscrolltop) {
            $(".scroll_drag").animate({ "bottom": "0px" });
        } else {
            $(".scroll_drag").animate({ "top": scrolltop + "px" });
        }
    }
}
