define('fzjz-save.js', function(require, exports, module){
	var GIS = require("lib/gigis/gi-gis");
	var Layer = require("components/map-layer");
  var timerAxis = null;
  var warningMarkerlayers;
    //初始化gis：此处可以设置默认图层：baselayer
    var gis = GIS("map", "grass", {
    	center: {
    		lon: 120.15,
    		lat: 30.0
    	},
    	zoom: 9,
    	baselayer: "traffic",
        // restrictedExtent:extent,//区域范围
        disableZoom: false, //禁用滚轮事件
        documentDrag:false
      });
    // 图层切换
    gis.addControl("baseLayerSwitcher", -20, 20);
    // 图层缩放
    gis.addControl("zoom", -70, 140);
    // gis.addControl("mousePosition", 85, 20, {
    // 	prefix: "经度：",
    // 	separator: " 纬度：",
    // 	numDigits: 3
    // });
    //地图点击事件
    gis.map.events.on({
    	"click":function(){
    		$(".map-left>li").removeClass("click");
    	}
    });
    (function(){
      $(".olMapViewport").append('<img class="map-shaw" src="./static/images/map_control.png">');
      var mapControlAdd ="<a herf='javascript:void(0)' class='olButton'>+</a>";
      mapControlAdd  += "<a herf='javascript:void(0)' class='olButton'>-</a>"
      $(".olControlZoom").append(mapControlAdd);
      //中心点横坐标
      var dotLeft = ($(".olControlZoom>a").width() / 2);
      //中心点纵坐标
      var dotTop = -70 - ($(".olControlZoom>a").height() / 2);
      //起始角度
      var stard = 0;
      //半径
      var radius = 70;
      //每一个BOX对应的角度;
      var avd = 360 / 12;
      //每一个BOX对应的弧度;
      var ahd = avd*Math.PI/180;
      $(".olControlZoom .olButton").each(function(index, element){
        $(this).css({"left": -dotLeft - Math.sin((ahd*index))*radius,"top":Math.cos((ahd*index))*radius+dotTop});
      });
    })();
    
  //点击下一个时次
  $(".m-next").click(function(e){
    if(e.which){//是手动点击
      stopPlay();
    }
    var index = $(".m-axis-list li.z-on").index();
    var num = $(".m-axis-list li").length;
    if(index == num - 1) return;
    index++;
    timeAxisControl(index);
  })
  //点击上一个时次
  $(".m-prev").click(function(){
    stopPlay();
    var index = $(".m-axis-list li.z-on").index();
    if(index == 0) return;
    index--;
    timeAxisControl(index);
  });
  //直接点击时间轴
  $("body").on("click",".m-axis-list li",function(){
    stopPlay();
    var index = $(this).index();
    timeAxisControl(index);
  })
  //点击播放
  $(".m-paly").click(function(){
    if($(this).hasClass("stop")){
      stopPlay();
    }else{
      $(this).addClass("stop")
      clearInterval(timerAxis);
      timerAxis = setInterval(function(){
        $(".m-next").click();
      },3000);
      
    }
  });
  //停止播放
  function stopPlay(){
    $(".m-play").removeClass("stop");
    if(timerAxis){
      clearInterval(timerAxis);
    }
  }
  //时间轴滚动
  function timeAxisControl(index){
    $(".m-axis-list li").eq(index).addClass("z-on").siblings().removeClass("z-on");
    var timeAxix = $(".m-time-axis-box");
    var timeW = timeAxix.width();
    var liW = $(".m-axis-list li").eq(0).width();
    var pageSize = Math.floor(timeW/liW);
    pageSize = pageSize / 2;
    var curPage = index - pageSize;
    if(curPage>0){
      timeAxix.animate({scrollLeft:liW * curPage},300);
    }else{
      timeAxix.animate({scrollLeft:0},300);
    }
  }
  var stationData = [
  {
   lon: 119.25,
   lat: 29.94,
   name:"岱山"
 },
 {
   lon: 119.4,
   lat: 29.8,
   name:"普陀"
 },
 {
   lon: 120.1,
   lat: 30.20,
   name:"衢山"
 }
 ]
    //绘制站点
    getStationData();
    function getStationData(){
    	if (!warningMarkerlayers) {
    		warningMarkerlayers = gis.addMarkers();
    	} else {
            // 清空图层
            warningMarkerlayers.clearMarkers();
          }
          for(var i=0;i<stationData.length;i++){
           addWarning(stationData[i])
         }
       }
       function addWarning(data) {
         var icon = {
          url: "/static/images/emergency-save/emergency-stuff.png",
          width: 45,
          height: 50
        };
        var avgLon = data.lon,
        avgLat = data.lat;
        var areaName = data.name;
        var marker = gis.addMarker({
          icon: icon,
          lon: avgLon,
          lat: avgLat,
          content: { 'data': data, 'lon': avgLon, 'lat': avgLat },
          text: "<div class='fzd-num-save'>应急物资</div>"
        }, warningMarkerlayers)[0];
       marker.events.register("click", marker, clickMarker); //mouseenter  mouseover
     };
     function clickMarker(){
      layer.open({
        title:["<div class='popup_hd_bk'>安置点</div><div>七都溪流域</div><div class='layer-t-right'>周边视频>></div>",'font-size:14px;padding:0;background:#1c4560;color:#fff;height:auto;border-bottom:none;'],
        type:1,
        skin:'layui-layer-rim',
        closeBtn:2,
        shade: [0],
        area:['400px','430px'],
        content:"<div class='video-con'></div><div class='layer_table'><table><tr><td>面积</td><td>123.254平方米</td></tr><tr><td>面积</td><td>123.254平方米</td></tr><tr><td>面积</td><td>123.254平方米</td></tr></table></div><button class='ala'>灾情分析</button>",
        resize:false,
      });
    }
    $("body").on("click",".m-nav>ul>li",function(){
      if($(this).find("ul").length){
        if($(this).hasClass("z-open"))
        {
          $(this).removeClass("z-open");
        }
        else{
          $(this).addClass("z-on z-open").siblings().removeClass("z-on z-open");
        }
      }
      else{
        $(this).addClass("z-on").siblings().removeClass("z-on z-open");
      }
    })
    $(window).resize(function(){
      var hei = Math.max(document.documentElement.scrollHeight, document.documentElement.clientHeight);
      $(".m-nav>ul>li>ul").height(hei);
    })
  });