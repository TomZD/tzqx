var warningMarkerlayers;
define('channel_state.js', function(require, exports, module){
	var GIS = require("lib/gigis/gi-gis");
	var Layer = require("components/map-layer");
	var LED;//定义计时器
	var time//定义计时器
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
    warningMarkerlayers = gis.addMarkers();
    // 图层切换
    gis.addControl("baseLayerSwitcher", 20, -60);
    // 图层缩放
    gis.addControl("zoom", 22, -20);
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
    })
    
     $(".channel_box").on("click","li",function(){
        $(this).addClass("z-on").siblings().removeClass("z-on");
        var value = $(this).val();
        var url="";
        switch (value) {
	        case 0:{
	            url = "/sev/led/leds";
	            break;
	        }
            case 1:{
                url = "/sev/led/trafficLeds";
                break;
            }
            case 2:{
                url = "/sev/led/busLeds";
                break;
            }
            case 3:{
                url = "/sev/led/metroLeds";
                break;
            }
            case 4:{
                url = "/sev/led/schoolLeds";
                break;
            }
            case 5:{
                url = "/sev/led/cinemaLeds";
                break;
            }
            case 6:{
                url = "/sev/led/weatherLeds";
                break;
            }
        }
        	clearTimeout(LED);
        	getMarkerData(url,value);
     })
  
   //弹窗
    popupShow();
	function popupShow(){
		var html="";
		html = "<table class='warn_msg'>";
        html +="<tr><td rowspan='3' colspan='2'><img src='../static2/images/warning_icon/暴雪红色.jpg' alt='暴雪红色'></td><td colspan='2'><span class='warn_title'>杭州市气象局发布的暴雨蓝色预警(IV级/一般)</span></td></tr>";
        html +="<tr><td><span>发布时间</span><input type='text' value='2017-07-27 13：36' disabled></td><td><span>发布时效</span><input type='text' value='6小时' disabled></td></tr>";
        html +="<tr><td><span>链接终端</span><input type='text' disabled value='邮政报亭led'></td><td><span>链接状态</span><input type='text' disabled value='正常'></td></tr>";
        html+="<tr><td colspan='4'><span>终端地址</span><input type='text' value='某某区某某街道' disabled></td></tr>"
        html+="<tr><td colspan='4'><span>发布内容</span><textarea disabled>北方强冷空气将于今日下午起影响我区，日平均气温过程降温幅度可达8-10℃，10日、11日最低气温平原0℃左右或以下，有薄冰，山区零下2℃到零下4℃，有冰冻，夜里到明天最大偏北风5-7级，请注意做好防风和保暖等工作。</textarea></td></tr>"
        html += "</table>";
		layer.open({
            title:["<div class='popup_hd_bk'>预警详情</div>",'font-size:14px;padding:0;background:none;color:#fff;height:auto;border-bottom: 1px solid rgba(23, 255, 254,0.2);'],
            type:1,
            skin:'m-popup',
            closeBtn:2,
            shade: [0],
            area:['910px','500px'],
            content:html,
            resize:true,
        });
	}

    
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
        })
    }
    
  //获取站点数据
    function getMarkerData(url,value){
		var indexLayer;
        $.ajax({
            url:url,
            type:"get",
			dataType:"JSON",
			beforeSend: function() {
                indexLayer=layer.load(2);               
            },
            success:function(data){
            	if (!warningMarkerlayers) {
            		warningMarkerlayers = gis.addMarkers();
            	} else {
                    // 清空图层
                    warningMarkerlayers.clearMarkers();
                }
            	$.each(data, function(i,item){
            		var a = {
            			 lon: item.lon,
            			 lat: item.lat,	
            			 name: item.name,
            			 status: item.connectStatus,
            			 type: item.ledType
            			}
            		darwMarker(a);
            	});
            },           
            error:function(){
                alert("接口"+ url + "错误！")
            },
            complete:function(){
				layer.close(indexLayer);    
            	autoplayLED(value+1);
            }
        })
    }

   
   function darwMarker(data) {
	   var pic = '';
	   var path = '';
	   switch(data.status){
	   		case 1:
	   			pic = 'b';
	   			break;
	   		case 2:
	   			pic = 'g';
	   			break;
	   		case 3:
	   			pic = 'r';
	   			break;
	   };
	   switch(data.type){
	   		case '1':
	   			path = '/static2/images/channel_type/tsp-'+pic+'.png';
	   			break;
	   		case '2':
	   			path = '/static2/images/channel_type/bus-'+pic+'.png';
	   			break;
	   		case '3':
	   			path = '/static2/images/channel_type/subway-'+pic+'.png';
	   			break;
	   		case '4':
	   			path = '/static2/images/channel_type/sc-'+pic+'.png';
	   			break;
	   		case '5':
	   			path = '/static2/images/channel_type/movie-'+pic+'.png';
	   			break;
	   		case '6':
	   			path = '/static2/images/channel_type/weather-'+pic+'.png';
	   			break;
	   }
	   var icon = {
			url: path,
			width: 32,
			height: 39
		};
	var avgLon = data.lon,
	avgLat = data.lat;
	var areaName = data.name;
	var marker = gis.addMarker({
		icon: icon,
		lon: avgLon,
		lat: avgLat,
		offsetX:-24,
		offsetY:-24,
		content: { 'data': data, 'lon': avgLon, 'lat': avgLat },
		text: ""
	}, warningMarkerlayers)[0];
  //marker.events.register("click", marker, clickShowLineDetail); //mouseenter  mouseover
};


function getHotelData(value){
	var indexLayer;
    $.ajax({
        url:"/ser/hotel/getHotel",
        type:"get",
		dataType:"JSON",
		beforeSend: function() {
			indexLayer=layer.load(2);               
		},
        success:function(data){
        	if (!warningMarkerlayers) {
        		warningMarkerlayers = gis.addMarkers();
        	} else {
                // 清空图层
                warningMarkerlayers.clearMarkers();
            }
        	$.each(data, function(i,item){
        		var a = {
        			 lon: item.lon,
        			 lat: item.lat,	
        			 name: item.name,
        			}
        		addHotel(a);
        	});
        },
        error:function(){
            alert("接口错误！")
        },
        complete:function(){
			layer.close(indexLayer);    
        	autoplay(value);
        }
    })
}

function addHotel(data) {
	    	var icon = {
	    		url: "/static2/images/channel_type/tv-g.png",
	    		width: 32,
	    		height: 39
	    		};
	var avgLon = data.lon,
	avgLat = data.lat;
	var areaName = data.name;
	var marker = gis.addMarker({
		icon: icon,
		lon: avgLon,
		lat: avgLat,
		offsetX:-24,
		offsetY:-24,
		content: { 'data': data, 'lon': avgLon, 'lat': avgLat },
		text: ""
	}, warningMarkerlayers)[0];
  //marker.events.register("click", marker, clickShowLineDetail); //mouseenter  mouseover
};
    
 
    //LED连接状态
    getLedStatus();
    function getLedStatus(){
		var indexLayer;
    	$.ajax({
            type:"GET",
            url:'/sev/led/getLeds',
			dataType:"JSON",
			beforeSend: function() {
                indexLayer=layer.load(2);               
            },
            success:function(data){
            	var type1 = data.type1;
            	var type2 = data.type2;
            	var type3 = data.type3;
            	var chart = Highcharts.chart('container', {
           			
            	   	chart: {
            	   		spacing : [40, 0 , 40, 0],
            	   		backgroundColor:"transparent",
            	   	},
            	   	title: {
            	   		floating:true,
            	   		text: 'LED'
            	   	},
            	   	tooltip: {
            	   		pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
            	   	},
            	   	plotOptions: {
            	   		pie: {
            	   			allowPointSelect: true,
            	   			cursor: 'pointer',
            	   			center: [120, 100],
            	   			size:150,
            	   			dataLabels: {
            	   				enabled: true,
            	   				format: '<b>{point.name}</b>: {point.percentage:.1f} %',
            	   				style: {
            	   					color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
            	   				}
            	   			},
            	   			point: {
            	   				events: {
            						mouseOver: function(e) {  // 鼠标滑过时动态更新标题
            							// 标题更新函数，API 地址：https://api.hcharts.cn/highcharts#Chart.setTitle
            							chart.setTitle({
            								text: e.target.name+ '\t'+ e.target.y + ' %'
            							});
            						}
            					}
            				},
            			}
            		},
            		series: [{
            			type: 'pie',
            			innerSize: '80%',
            			name: 'LED连接',
            			data: [
            				['异常', type3],
            				['未连接', type2],
            				['已连接', type1],
            				]
            			
            			
            		}]
            	}, function(c) { // 图表初始化完毕后的会掉函数
            		// 环形图圆心
            		var centerY = c.series[0].center[1],
            		titleHeight = parseInt(c.title.styles.fontSize);
            		// 动态设置标题位置
            		c.setTitle({
            			y:centerY + titleHeight/2-120
            		});
            	});
			},
			complete:function(){
                layer.close(indexLayer);      
            },
            error:function(e){
            	alert("接口报错");
            }
            });
    }
    //右侧列表点击事件
    $(".channel_list tr").click(function(){
    	var value = $(this).index();
    	$(this).addClass("ontime").siblings().removeClass("ontime");
    	//清楚计时器
    	clearTimeout(LED);
		clearTimeout(time);
    	if(value!=$(".channel_list tbody").children().length-1)
		{
    		$(".channel_box").hide();
			$(".show_mess").hide();
			//只切换部分
			/*
			if(value == 3)
				autoplay(9)
			else{
				autoplay(3)
			}*/
    		autoplay(value);
		}else{
			$(".channel_box").show();
			$(".show_mess").show();
			//设置一开始显示就是第一个
			$(".channel_box ul li").eq(0).addClass("z-on").siblings().removeClass("z-on");
			autoplayLED(0);
		}
    })
    //右侧随时间变化轮播
    autoplay(1);
    function autoplay(value){
    	time = setTimeout(function(){
    		if(value!=$(".channel_list tbody").children().length-1){
    			if(value == 2||value==9){
    				//if函数在之后要去掉
    				$(".channel_box").hide();
        			$(".show_mess").hide();
        			$(".channel_list table tr").eq(value).addClass("ontime").siblings().removeClass("ontime");
    			}
    			if(value==2){
    				getHotelData(3);
    			}
    			//选择切换
    			else{
    				autoplay(9)
    			}/*
    			if(value!=2&&value!=9){
    				warningMarkerlayers.clearMarkers();
    				clearTimeout(time);
    				autoplay(value+1);
    			}*/
    		}else{
    			$(".channel_list table tr").eq(value).addClass("ontime").siblings().removeClass("ontime");
    			$(".channel_box").show();
    			$(".show_mess").show();
    			clearTimeout(time);
    			autoplayLED(0);
    		}
    	},5000)
    }
    function autoplayLED(value){
    	LED = setTimeout(function(){
    	var url;
		if(value!=7){
			 switch (value) {
		        case 0:{
		            url = "/sev/led/leds";
		            break;
		        }
	            case 1:{
	                url = "/sev/led/trafficLeds";
	                break;
	            }
	            case 2:{
	                url = "/sev/led/busLeds";
	                break;
	            }
	            case 3:{
	                url = "/sev/led/metroLeds";
	                break;
	            }
	            case 4:{
	                url = "/sev/led/schoolLeds";
	                break;
	            }
	            case 5:{
	                url = "/sev/led/cinemaLeds";
	                break;
	            }
	            case 6:{
	                url = "/sev/led/weatherLeds";
	                break;
	            }
	        }
			 	$(".channel_box ul li").eq(value).addClass("z-on").siblings().removeClass("z-on");
			 	getMarkerData(url,value);
    		}else{
    			clearInterval(LED);
    			//修改回来autoplay(1)
    			autoplay(2);
    		}
    	},5000)
    }
});