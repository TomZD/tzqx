define('channel_state.js', function(require, exports, module){
	var GIS = require("lib/gigis/gi-gis");
	var Layer = require("components/map-layer");
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

    //获取杭州边界
    getBoundaryData()
    function getBoundaryData(){
    	$.ajax({
    		url:"../../data/杭州市.xml",
    		dataType:"xml",
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
            }
        })
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
    		url: "/static/images/channel_type/bus-g.png",
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
					//, 
					// click: function(e) { // 同样的可以在点击事件里处理
					//     chart.setTitle({
					//         text: e.point.name+ '\t'+ e.point.y + ' %'
					//     });
					// }
				}
			},
		}
	},
	series: [{
		type: 'pie',
		innerSize: '80%',
		name: 'LED连接',
		data: [
		['异常', 26.8],
		['未连接', 8.5],
		['已连接', 6.2],
		]
	}]
}, function(c) { // 图表初始化完毕后的会掉函数
	// 环形图圆心
	var centerY = c.series[0].center[1],
	titleHeight = parseInt(c.title.styles.fontSize);
	// 动态设置标题位置
	c.setTitle({
		y:centerY + titleHeight/2
	});
});
});