define('tqsk.js', function(require, exports, module){
	var GIS = require("lib/gigis/gi-gis");
	var Layer = require("components/map-hzqxsk");
	var windFieldImg = require("components/windFieldImg"); //风场
  var timerAxis = null;
  var Ismapfirstload = true;
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
      $(".olMapViewport").append('<img class="map-shaw" src="./static2/images/map_control.png">');
//      var mapControlAdd ="<a herf='javascript:void(0)' class='olButton'>+</a>";
//      mapControlAdd  += "<a herf='javascript:void(0)' class='olButton'>-</a>"
//      $(".olControlZoom").append(mapControlAdd);
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
      //获取杭州边界
    getBoundaryData()
    function getBoundaryData(){
      var indexLayer;
      $.ajax({
        url:"/static2/data/hzs.xml",
        dataType:"xml",
        beforeSend: function() {
          indexLayer=layer.load(2);               
      },
        success:function(data){
          //绘制边界
          var boundary = gis.drawBoundary("杭州市", data, {
                            fillOpacity: 1,//透明度
                            fillColor: "transparent",//填充色
                            strokeColor: "red",//边界线条颜色灰色
                            strokeWidth: "2"
                        });
                //蒙层
                var boundary = gis.drawBoundary("杭州市", data, {
                            fillOpacity: 0.68,//透明度
                            fillColor: "#2077be",//填充色
                            strokeColor: "red",//边界线条颜色灰色
                            strokeWidth: "2",
                            isCut:true,
                        });
            },
        complete:function(){
              layer.close(indexLayer);      
          },
        });
      marks = gis.addMarkers();
    }
    $("body").on("click",".m-nav>ul>li",function(){
      if($(this).find("ul").length){
        if($(this).hasClass("z-open"))
        {
          $(this).removeClass("z-open");
        }
        else{
          $(this).addClass("z-on z-open").siblings().removeClass("z-on z-open");
		        var value = $(this).val();
		      	if(value == 1){
		      		
		      	}else if(value == 2){
		      	}else if(value == 3){
		      	}
        }
      }
      else{
        $(this).addClass("z-on").siblings().removeClass("z-on z-open");
        var value = $(this).val();
      	if(value == 1){
      	}else if(value == 2){
      	}else if(value == 3){
      	}
      }
    });
     $("body").on("click",".m-nav ul ul>li",function(){
        $(".m-nav ul ul>li").removeClass("z-on");
        $(this).addClass("z-on");
        return false;
     })
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

  
  
  var requstWeatherData;
  var curType;
  var map1 = {"WPOINT_TEMP":"5min_T","DAY_MAX_TEMP":"d2n_TMax","DAY_MIN_TEMP":"d2n_TMin","24HOUR_MAX_TEMP":"24hour_TMax","24HOUR_MIN_TEMP":"24hour_TMin","1HOUR_RAIN":"1hour_R","3HOUR_RAIN":"3hour_R",
          "6HOUR_RAIN":"6hour_R","12HOUR_RAIN":"12hour_R","24HOUR_RAIN":"24hour_R","WPOINT_VISIBILITY":"d2n_Vis","1HOUR_MAX_WIND":"1hour_MaxWindV","1HOUR_EXTRE_WIND":"1hour_ExMaxWind",
          "DAY_MAX_WIND":"d2n_MaxWindV","DAY_EXTRE_WIND":"d2n_ExMaxWind"};
  var mapType ="WPOINT_TEMP";
  var gis, layer;
  var windFieldImgLayer;
  var stationMarkerlayers;
  init();
  function init() {
      if (Ismapfirstload) { //还未加载过地图的情况
          Ismapfirstload = false;
          requstWeatherData=function(type) {
          	curType=type;
  	        //天气等值线
          	showLayer("./static2/data/d2n_ExMaxWind.xml");
          	
  	        //加载站点
  	        loadStations(type);
  	        // 不是风，不加载风场数据
  	        if(type == '1hour_MaxWind' || type == '1hour_ExMaxWind' || type == 'd2n_MaxWind' || type == 'd2n_ExMaxWind'){
  	        	getWindFieldImgData(type);
  	        }
  	        else{
  	        	if(windFieldImgLayer){
  	        		windFieldImgLayer.destroy();
  	        		windFieldImgLayer=undefined;
  	        	}
  	        }
  	        gisType =type;
  	   }
         requstWeatherData(map1[mapType]);
      } else {
      	requstWeatherData(map1[mapType]);	
      };
  }
//请求站点数据 
  function loadStations(type) {
    var indexLayer;
      $.ajax({
          url: "/dd/weather/zd?type="+type,
          type: "get",
          dataType: "json",
          beforeSend: function() {
            indexLayer=layer.load(2);               
          },
          success: function(stations) {
              loadMarker(stations);
          },
          complete:function(){
            layer.close(indexLayer);      
        },
      });
  }
//等值线加载
  function showLayer(url) {
      if (!layer) {
          layer = Layer(gis);
      } else {
          layer.grid.destroy();
      }
      layer.add("grid", "ceshi", url, controllegend);
  }
  
  /*
  风场数据
  */
  function getWindFieldImgData(type) {
      $.ajax({
              url: "/dd/weather/zd?type="+type,
              type: 'get',
              dataType: 'json',
              // data: {param1: 'value1'},
          })
          .done(function(data) {
              drawWindFieldImg(data);
          })
          .fail(function() {
              console.log("error");
          })
          .always(function() {
              console.log("complete");
          });
  }
  
//绘制站点
  function loadMarker(stationData) {
  	var titlestr="杭州市当前实况气温分布图";
      if (!stationMarkerlayers) {
          stationMarkerlayers = gis.addMarkers();
      } else {
          // 清空图层
          stationMarkerlayers.clearMarkers();
      }
      var icon = {
          url: "./static2/images/point.png",
          width: 23,
          height: 29
      };
      $.each(stationData, function(i, item) {
      	var itemValue=item.Value;
      	var textstr;
      	if (titlestr.indexOf("风速")>-1) {
      		var windV=windS2WindV(itemValue);
  			textstr=item.Name+windV; //风速转风力
      	}
      	else if(titlestr.indexOf("风场")>-1){
      		var windV=windS2WindV(itemValue);
      		textstr=item.Name+windV; //风速转风力
      	}
      	else {
      		textstr=item.Name+itemValue; //摄氏度或其他单位，可通过参数判断
      	}
//      	var textstr=item.Name+item.Value; //摄氏度或其他单位，可通过参数判断
          var marker = gis.addMarker({
              icon: icon,
              lon: item.Longitude,
              lat: item.Latitude,
              offsetX: -icon.width / 2,
              offsetY: -icon.height / 2,
              content: item,
              text: "<div class='maptext'>" + textstr + "</div>"
          }, stationMarkerlayers)[0];
          marker.CLASS_NAME = "hangzhou" + item.Name;
      });

  }
  
  //等值线回调函数
  function controllegend() {
      layer.grid.setOpacity(0.8);
      var titlestr="杭州市当前实况气温分布图";
      var typetag="单位：";
      if(titlestr.indexOf("温")>-1){
          typetag+=" ℃";
      }else if(titlestr.indexOf("降水")>-1){
          typetag+="mm";
      }else if(titlestr.indexOf("能见度")>-1){
          typetag+="m";
      }else if(titlestr.indexOf("风速")>-1){
          typetag+="级（米/秒）";
          windSpeed2WindV();
      }
      else if(titlestr.indexOf("风场")>-1){
//	    	typetag+="级（米/秒）";
	    	typetag+="（米/秒）";
	    	windSpeed2WindV();
	    }
      $(".olGiLegend .legend").find("span b").html(typetag);
  }
  
//色标风力转风速
  function windSpeed2WindV() {
  	var lis=$(".olGiLegend .legend").find("ul li");
  	$.each(lis, function(i, li){
  		var thisHtml=$(this).html();
//  		console.log(thisHtml);
  		var thisText=$(this).text();
//  		console.log(thisText);
  		thisHtml=thisHtml.substring(0,thisHtml.indexOf(thisText));
  		switch(thisText){
	    		case "0.1":
	    			thisText="<label>0级</label>（0.1）";
	    			break;
	    		case "0.3":
	    			thisText="<label>1级</label>（0.3）";
	    			break;
	    		case "1.6":
	    			thisText="<label>2级</label>（1.6）";
	    			break;
	    		case "3.4":
	    			thisText="<label>3级</label>（3.4）";
	    			break;
	    		case "5.5":
	    			thisText="<label>4级</label>（5.5）";
	    			break;
	    		case "8.0":
	    			thisText="<label>5级</label>（8.0）";
	    			break;
	    		case "10.8":
	    			thisText="<label>6级</label>（10.8）";
	    			break;
	    		case "13.9":
	    			thisText="<label>7级</label>（13.9）";
	    			break;
	    		case "17.2":
	    			thisText="<label>8级</label>（17.2）";
	    			break;
	    		case "20.8":
	    			thisText="<label>9级</label>（20.8）";
	    			break;
	    		case "24.5":
	    			thisText="<label>10级</label>（24.5）";
	    			break;
	    		case "28.5":
	    			thisText="<label>11级</label>（28.5）";
	    			break;
	    		case "32.7":
	    			thisText="<label>12级</label>（32.7）";
	    			break;
	    		case "37.0":
	    			thisText="<label>13级</label>（37.0）";
	    			break;
	    		case "41.5":
	    			thisText="<label>14级</label>（41.5）";
	    			break;
	    		case "46.2":
	    			thisText="<label>15级</label>（46.2）";
	    			break;
	    		case "51.0":
	    			thisText="<label>16级</label>（51.0）";
	    			break;
	    		case "56.1":
	    			thisText="<label>17级</label>（56.1）";
	    			break;
	    		case "61.3":
	    			thisText="<label>>17级</label>（≥61.3）";
	    			break;
  		}
  		$(this).html(thisHtml+thisText);
  	});
  }
  
//风速转风力
  function windS2WindV(windS) {
  	var windV;
  	if(parseFloat(windS)<=0.2){
  		windV='0级';
  	}
  	else if (parseFloat(windS)<=1.5){
  		windV='1级';
  	}
  	else if (parseFloat(windS)<=3.3){
  		windV='2级';
  	}
  	else if (parseFloat(windS)<=5.4){
  		windV='3级';
  	}
  	else if (parseFloat(windS)<=7.9){
  		windV='4级';
  	}
  	else if (parseFloat(windS)<=10.7){
  		windV='5级';
  	}
  	else if (parseFloat(windS)<=13.8){
  		windV='6级';
  	}
  	else if (parseFloat(windS)<=17.1){
  		windV='7级';
  	}
  	else if (parseFloat(windS)<=20.7){
  		windV='8级';
  	}
  	else if (parseFloat(windS)<=24.4){
  		windV='9级';
  	}
  	else if (parseFloat(windS)<=28.4){
  		windV='10级';
  	}
  	else if (parseFloat(windS)<=32.5){
  		windV='11级';
  	}
  	else if (parseFloat(windS)<=36.9){
  		windV='12级';
  	}
  	else if (parseFloat(windS)<=41.4){
  		windV='13级';
  	}
  	else if (parseFloat(windS)<=46.1){
  		windV='14级';
  	}
  	else if (parseFloat(windS)<=50.9){
  		windV='15级';
  	}
  	else if (parseFloat(windS)<=56.0){
  		windV='16级';
  	}
  	else if (parseFloat(windS)<=61.2){
  		windV='17级';
  	}
  	else if (parseFloat(windS)>=61.3){
  		windV='>17级';
  	}
  	return windV;
  }

      
  });