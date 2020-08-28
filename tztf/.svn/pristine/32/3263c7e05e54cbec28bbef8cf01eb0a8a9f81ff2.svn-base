define(function(require) {
    var moveTo = require("../control/moveto");
    var getCoordinate = function(lon, lat, map) {
        new OpenLayers.LonLat(lon, lat).transform(map.getProjection(), map.projection)
    };

    return function(id, imgUrl, zIndex) {
        var that = this;
        var markerLayer = that.addMarkers();

        var style = new OpenLayers.Style({
            fillColor: "#f20800",
            strokeColor: "#fd8044",
            strokeWidth: 3,
            fillOpacity: 0.6,
            pointRadius: 6
        });
        var layer;
	    var renderer = OpenLayers.Util.getParameters(window.location.href).renderer;
	    renderer = (renderer) ? [renderer] : OpenLayers.Layer.Vector.prototype.renderers;
	    var DrawControls;
	    var keyType;

        
	    
        $('#btnPolygen').click(function(){
        	$(".selected-area").html(" ")
        	$(".tishi").html("画完请双击鼠标左键保存");
        	$('.tishi').show();
        	for(var i=0;i<bounderList.length;++i) {
        		bounderList[i].destroy();
        	}
        	
            if (!layer) {
                layer = that.map.getLayersByName("绘图工具")[0];
                if (!layer) {
                    layer = new OpenLayers.Layer.Vector("绘图工具", {
                        styleMap: new OpenLayers.StyleMap(style),
                        zIndex: zIndex
                    });
                    that.map.addLayer(layer);
                }
                DrawControls = {
                    point: new OpenLayers.Control.DrawFeature(layer, OpenLayers.Handler.Point),
                    line: new OpenLayers.Control.DrawFeature(layer, OpenLayers.Handler.Path),
                    rectangle: new OpenLayers.Control.DrawFeature(layer, OpenLayers.Handler.RegularPolygon, {
                        handlerOptions: {
                            sides: 4,
                            irregular: true
                        }
                    }),
                    polygon: new OpenLayers.Control.DrawFeature(layer, OpenLayers.Handler.Polygon),
                    circle: new OpenLayers.Control.DrawFeature(layer, OpenLayers.Handler.RegularPolygon, {
                        handlerOptions: {
                            sides: 40
                        }
                    }),
                    distance: new OpenLayers.Control.Measure(OpenLayers.Handler.Path, {
                        persist: true,
                        immediate: false,
                        handlerOptions: {
                            layerOptions: {
                                renderers: renderer,
                                styleMap: new OpenLayers.StyleMap(style)
                            }
                        }
                    }),
                    drag: new OpenLayers.Control.DragFeature(layer)
                }
                for (var key in DrawControls) {
                    control = DrawControls[key];
                    if (key == "distance") {
                        control.events.on({
                            "measure": handleMeasurements,
                            "measurepartial": seperateMeasurement
                        });
                    }
                    that.map.addControl(control);
                }
             // 启动、关闭绘图
                for (var key in DrawControls) {
                    var control = DrawControls[key];
                    if (key == "polygon") {
                        keyType = key;
                        if (!control.events.listeners.featureadded)
                            control.events.register("featureadded", control, onMapClick);
                        control.activate();
                    } else {
                        control.deactivate();
                    }
                }
            }
            if (markerLayer) {
                markerLayer.clearMarkers();
            }
            var val = this.getAttribute("data-val");
            if (val == "cancel") {
                if (layer) {
                    layer.removeAllFeatures();
                    return;
                }
            }

            layer.removeAllFeatures();
//            DrawControlTools();
            DrawControls['polygon'].activate();
        });

        //最后总距离
        function handleMeasurements(event) {
            var geometry = event.geometry;
            var units = event.units;
            var measure = event.measure;
            var len = geometry.components.length;
            var pos = geometry.components[len - 1];
            var ll = getCoordinate(pos.x, pos.y, that.map);
            var out = "";
            out += measure.toFixed(3) + " " + units;
            that.addPointMarkers(ll.lon, ll.lat, {
                url: imgUrl,
                width: 16,
                height: 16
            }, "总长：" + out, null, null, markerLayer);
            that.calcDistance = "on";
        }
        //时时测距
        function seperateMeasurement(event) {
            if (that.calcDistance == "on") {
                if (markerLayer) {
                    markerLayer.clearMarkers();
                }
                that.calcDistance = "off";
            }
            var measure = event.measure;
            var units = event.units;
            var geometry = event.geometry;
            var len = geometry.components.length;
            var pos = geometry.components[len - 1];
            var ll = getCoordinate(pos.x, pos.y, that.map);
            var out = "";
            out += measure.toFixed(1) + " " + units;
            that.addPointMarkers(ll.lon, ll.lat, {
                url: imgUrl,
                width: 16,
                height: 16
            }, out, null, null, markerLayer);
        }
        //多边形面积,圆形面积，矩形面积
        var onMapClick = function() {
        if (layer) {
            if (layer.features.length > 0) {
                switch (keyType) {
                    case "geometryPs":
                        var geom = layer.features[layer.features.length - 1].geometry;
                        if (geom.id.indexOf('LineString') != -1) { //获取画的最后一条线
                            var components = geom.components;
                            var points = '';
                            $.each(components, function(i, item) {
                                points += item.x + ',' + item.y + ';';
                                if (i == 1) {
                                    return false; //只取前两个点
                                }
                            });
                            points = points.substr(0, points.length - 1);
                            radarTileChartData(points);
                        }
                        break;
                    case "polygon":
                    case "circle":
                    case "rectangle":
                        var geom = layer.features[layer.features.length - 1].geometry;
                        $('#btnSave').click();
                        break;
                    case "line":
                        //获取画的最后一条线
                        var geom = layer.features[layer.features.length - 1].geometry;
                        var geodesicArea = geom.getGeodesicLength("EPSG:4326");
                        var geodesicArea = geom.getLength();
                        geodesicArea = geodesicArea / 1000.0;
                        geodesicArea = geodesicArea.toFixed(2);
                        //距离约为：平方千米
                        if (callback && typeof callback == "function") {

                            callback(keyType, geodesicArea, layer);
                        }
                        break;
                    case "point":
                        //获取点的经纬度坐标
                        var geom = layer.features[layer.features.length - 1].geometry;//获取最后一个点
                        var pos=that.tanslateLonLat(geom.x,geom.y,true);
                        $(".dataTool").find("span.pointx").html(pos.lon.toFixed(2));
                        $(".dataTool").find("span.pointy").html(pos.lat.toFixed(2));
                            break;
                }

            }

        }

    }

        var boundrys = [];
        //边界线集合
        //上虞乡镇缩写
//        var areaMap = {
//            "百官街道": "bgjd",
//            "曹娥街道": "cejd",
//            "长塘镇": "ctz",
//            "陈溪乡": "cxx",
//            "道墟镇": "dxz",
//            "丁宅乡": "dzx",
//            "东关街道": "dgjd",
//            "丰惠镇": "fhz",
//            "盖北镇": "gbz",
//            "沥海镇": "lhz",
//            "梁湖镇": "lianghz",
//            "岭南乡": "lnx",
//            "上浦镇": "spz",
//            "上虞围垦区": "sywgq",
//            "汤浦镇": "tpz",
//            "下管镇": "xgz",
//            "小越镇": "xyz",
//            "谢塘镇": "xtz",
//            "永和镇": "yhz",
//            "章镇镇": "zzz",
//            "崧厦镇": "sxz",
//            "驿亭镇": "ytz"
//        };
        var areaMap={};
        switch(areaId)
        {
        case "1":
        	  areaMap = {
                "滨江区": "bjq",
                "拱墅区": "gsq",
                "江干区": "jgq",
                "上城区": "scq",
                "西湖区": "xhq",
                "下城区": "xcq",
                "大江东区": "djdq"
            };
          break;
        case "2":
        	areaMap = {
        		"新合乡":"xhx",
        		"合村乡":"hcx",
        		"百江镇":"bjz",
        		"分水镇":"fsz",
        		"钟山乡":"zsx",
        		"瑶琳镇":"ylz",
        		"横村镇":"hcz",
        		"莪山畲族乡":"esszx",
        		"旧县街道":"jxjd",
        		"富春江镇":"fcjz",
        		"桐君街道":"tjjd",
        		"城南街道":"cnjd",
        		"凤川街道":"fcjd",
        		"江南镇":"jnz",
                
        };

          break;
        case "3":
      	  areaMap = {
        		"中洲镇":"zzz",
        		"汾口镇":"fkz",
        		"浪川乡":"lcx",
        		"姜家镇":"jjz",
        		"枫树岭镇":"fslz",
        		"梓桐镇":"ztz",
        		"鸠坑乡":"jkx",
        		"大墅镇":"dsz",
        		"界首乡":"jsx",
        		"威坪镇":"wpz",
        		"安阳乡":"ayx",
        		"里商乡":"lsx",
        		"宋村乡":"scx",
        		"王阜乡":"wfx",
        		"金峰乡":"jfx",
        		"屏门乡":"pmx",
        		"千岛湖镇":"qdhz",
        		"左口乡":"zkx",
        		"瑶山乡":"ysx",
        		"石林镇":"slz",
        		"临岐镇":"lqz",
        		"文昌镇":"wcz",
        		"富文乡":"fwx"
             
          };
        break;
      case "4":
    	  areaMap = {
    		  "李家镇":"ljz",
    		  "大同镇":"dtz",
    		  "航头镇":"htz",
    		  "寿昌镇":"scz",
    		  "新安江街道":"xajjd",
    		  "更楼街道":"gljd",
    		  "大慈岩镇":"dcyz",
    		  "莲花镇":"lhz",
    		  "洋溪街道":"yxjd",
    		  "下涯镇":"xyz",
    		  "乾潭镇":"qtz",
    		  "杨村桥镇":"ycqz",
    		  "大洋镇":"dyz",
    		  "梅城镇":"mcz",
    		  "钦堂乡":"qtx",
    		  "三都镇":"sdz",
              
      };

        break;
      case "5":
    	  areaMap = {
    		  
    		  "楼塔镇":"ltz",
    		  "戴村镇":"dcz",
    		  "河上镇":"hsz",
    		  "义桥镇":"yqz2",
    		  "闻堰镇":"wyz",
    		  "蜀山街道":"ssjd",
    		  "浦阳镇":"pyz",
    		  "城厢街道":"cxjd",
    		  "临浦镇":"lpz",
    		  "宁围镇":"nwz",
    		  "北干街道":"bgjd",
    		  "所前镇":"sqz",
    		  "进化镇":"jhz",
    		  //"顺坝垦区":"sbkq",
    		  "新塘街道":"xtjd",
    		  "新街镇":"xjz",
    		  "衙前镇":"yqz",
    		  "瓜沥镇":"glz",
    		  "南阳街道":"nyjd",
    		  "靖江街道":"jjjd",
    		  "党湾镇":"dwz",
    		  "益农镇":"ynz",
    		  //"萧山区围垦区":"xsqwkq"
            
        };
      break;
    case "6":
    	areaMap = {
    		"百丈镇":"bzz",
    		"鸬鸟镇":"lnz",
    		"径山镇":"jsz",
    		"黄湖镇":"hhz",
    		"余杭街道":"yhjd",
    		"中泰街道":"ztjd",
    		"瓶窑镇":"pyz",
    		"闲林街道":"xljd",
    		"仓前街道":"cqjd",
    		"良渚街道":"lzjd",
    		"五常街道":"wcjd",
    		"仁和街道":"rhjd",
    		"崇贤街道":"cxjd",
    		"塘栖镇":"tqz",
    		"临平街道":"lpjd",
    		"星桥街道":"xqjd",
    		"运河街道":"yhjd2",
    		"乔司街道":"qsjd",
    		"南苑街道":"nyjd",
    		"东湖街道":"dhjd"
            
    };

      break;
    case "7":
  	  areaMap = {
    		"万市镇":"wsz",
    		"洞桥镇":"dqz",
    		"胥口镇":"xkz",
    		"新登镇":"xdz",
    		"永昌镇":"ycz",
    		"渌渚镇":"lzz",
    		"春建乡":"cjx",
    		"新桐乡":"xtx",
    		"富春街道":"fcjd",
    		"鹿山街道":"lsjd",
    		"场口镇":"ckz",
    		"银湖街道":"yhjd",
    		"常安镇":"caz",
    		"湖源乡":"hyx",
    		"环山乡":"hsx",
    		"龙门镇":"lmz",
    		"春江街道":"cjjd",
    		"东洲街道":"dzjd",
    		"大源镇":"dyz",
    		"上官乡":"sgx",
    		"灵桥镇":"lqz",
    		"常绿镇":"clz",
    		"里山镇":"lsz",
    		"渔山乡":"ysx"


          
      };
    break;
  case "8":
	  areaMap = {
		  "清凉峰镇":"qlfz",
		  "岛石镇":"dsz",
		  "龙岗镇":"lgz",
		  "河桥镇":"hqz",
		  "湍口镇":"tkz",
		  "昌化镇":"chz",
		  "太阳镇":"tyz",
		  "潜川镇":"qcz",
		  "於潜镇":"wqz",
		  "天目山镇":"tmsz",
		  "太湖源镇":"thyz",
		  "玲珑街道":"lljd",
		  "锦城街道":"jcjd",
		  "高虹镇":"ghz",
		  "锦北街道":"jbjd",
		  "板桥镇":"bqz",
		  "锦南街道":"jnjd",
		  "青山湖街道":"qshjd"
      };

    break;
        default:

        }
        

        
        
       
        var bounderList = [];
        var country=["","hangzhou/q/","tonglu/","chunan/","jiande/","xiaoshan/","yuhang/","fuyang/","linan/"];

        function need(list) {

        	
            for (var i = 0; i < bounderList.length; i++) {
                var item = bounderList[i];
                $('#' + item.id).html("");
            }
            bounderList = [];
            $('#map').css('display', '');
            $('#cloudImg').html('');
            var twon = areaMap[list];
            //var twon = '';
            var l = boundrys.length;
            if (l > 1 || l == 0) {
               /* for (var i = 0; i < l; i++) {
                    boundrys[i].destroy();
                }
                boundrys = [];
                for (var key in areaMap) {
                    if (key == list) {
                        twon = areaMap[key];
                    }
                }*/
                $.ajax({
                    //url: "/static/js/areaXML/hangzhou/q/" + twon + ".xml",
                    url: "/static/js/areaXML/"+country[areaId]+ twon + ".xml",
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    type: "get",
                    dataType: "xml",
                    success: function(xml) {
                        b = mapstol.drawBoundary("", xml, {
                        	fillOpacity: 0.3,
                            strokeColor: "#00009C",
                            fillColor: "#e627cf"
                        });
                        bounderList.push(b);
                    },
                    error: function() {
                        alert("网络请求失败！无法绘制地图区域！");
                    }
                });
            }
        }
        $('#btnCancle').click(function() {
            if (layer) {
                layer.removeAllFeatures();
                return;
            }
        });

        function clear() {
            if (layer) {
                layer.removeAllFeatures();
                return;
            }
        }
        //来自预警信息管理页面的点击事件
        $("li[name=abutton]").on("click",null, function() {
             //点击右菜单时地图点击选中相应的预警图标start
            var markerArr = that.map.mapMarkers;
            var warnID=$(this).find("input").attr("value");
            $.each(markerArr, function(i, item) {
                if(warnID==item.content.content.id){
                   var t = item.text; 
                   $(t).click();
                }
            });
            var alarmId = $(this).children("input[type=hidden]").val();
            for (var k = 0; k < bounderList.length; k++) {
                var item = bounderList[k];
                $('#' + item.id).html("");
            }
            bounderList = [];
            messageId = "";
            $("#signalName").empty();
            $("#publishTime").empty();
            $("#departemnt").empty();
            $("#publishWay").empty();
            $("#publishArea").empty();
            $("#forecast").empty();
            $("#needtime").empty();
            $("input[type='button']").attr("id", "");
            for (var i = 0; i < alarmNeed.length; i++) {
                var lin = alarmNeed[i];
                var idr = lin.id;
                messageId = idr;
                if (idr == alarmId) {
                    $("input[type='button']").attr("id", idr);
                    var name = lin.signalName;
                    console.log(lin.publishTime);
                    //var time = new Date(lin.publishTime).toLocaleString('zh-Cn', {hour12: false});
                    var time = lin.publishTime.replace('T', ' ');
                    var way = "";
                    for (var index = 0; index < lin.channelInstances.length; ++index) {
                        way += lin.channelInstances[index].channel.name + ";";
                    }
                    var area = lin.publishArea;
                    var cast = lin.forecast;
                    var depert = "上虞区气象局";
                    var needs = lin.duration + "小时";
                    $("#signalName").append(name);
                    $("#publishTime").append(time);
                    $("#departemnt").append(depert);
                    $("#publishWay").append(way);
                    $("#publishArea").append(area);
                    $("#forecast").append(cast);
                    $("#needtime").append(needs);
                    clear();
                    var colorStr = lin.alarmType.color;
                    var colorHex = '#ff0000';

                    if (colorStr == 'Orange') {
                        colorHex = '#f58c1f';
                    } else if (colorStr === 'Yellow') {
                        colorHex = '#ffe400'
                    } else if (colorStr == 'Blue') {
                        colorHex = '#2e62ff'
                    }
                    var twon = '';
                    var needArea = '';
                    var parea = area.split(",");
                    for (var j = 0; j < parea.length; j++) {
                        needArea = parea[j];
                        for (var key in areaMap) {
                            if (key == needArea) {
                                twon = areaMap[key];
                            }
                        }
                        $.ajax({
                            url: "/static/js/areaXML/shangyu/" + twon + ".xml",
                            contentType: "application/x-www-form-urlencoded; charset=utf-8",
                            type: "get",
                            dataType: "xml",
                            success: function(xml) {
                                b = mapstol.drawBoundary("", xml, {
                                    fillOpacity: 0.3,
                                    strokeColor: "#f00",
                                    fillColor: colorHex
                                });
                                bounderList.push(b);
                            },
                            error: function() {
                                alert("网络请求失败！无法回执地图区域！");
                            }
                        });
                    }
                }
            }
        });
        //over
        
        $('#close').click(function(){
            console.log(bounderList);
        	if(layer){
        		  //layer.removeAllFeatures();
        		  DrawControls.drag.activate();
        		  DrawControls.polygon.deactivate();
        	}
        	//暂时封掉退出时清楚地图区域选择的功能
//        	for(var i=0;i<bounderList.length;++i) {
//        		bounderList[i].destroy();
//        	}
        	
            $('.map-wrapper').hide();
            $('.tishi').hide();

        });
        
        function close(){
        	console.log(bounderList);
        	if(layer){
        		  //layer.removeAllFeatures();
        		  DrawControls.drag.activate();
        		  DrawControls.polygon.deactivate();
        	}
        	//暂时封掉退出时清楚地图区域选择的功能
//        	for(var i=0;i<bounderList.length;++i) {
//        		bounderList[i].destroy();
//        	}
        	
            $('.map-wrapper').hide();
            $('.tishi').hide();
        }
        
        //事件管理地图描边
        $('li.event-list').on("click",null, function() {
        	var colorStr = "";
            //点击右菜单时地图点击选中相应的预警图标start
            var markerArr = that.map.mapMarkers;
            var warnID=$(this).attr("data-id");
            $.each(markerArr, function(i, item) {
            	
                if(warnID==item.content.content.id){
                	colorStr = item.content.content.alarmLevel;
                   var t = item.text; 
                   $(t).click();
                }
            });
            var colorHex = '#ff0000';
            if (colorStr == 2) {
                colorHex = '#f58c1f';
            } else if (colorStr === 3) {
                colorHex = '#ffe400'
            } else if (colorStr == 4) {
                colorHex = '#2e62ff'
            }
            for (var j = 0; j < bounderList.length; j++) {
                var item = bounderList[j];
                $('#' + item.id).html("");
            }
            bounderList = [];

            var $this = $(this);
            var dataArea = $this.attr('data-area');
            if (dataArea == null || dataArea == undefined)
                return;
            var parea = dataArea.split(",");
            for (var i = 0; i < parea.length; i++) {
                needArea = parea[i];
                for (var key in areaMap) {
                    if (key == needArea) {
                        twon = areaMap[key];
                    }
                }
                $.ajax({
                    url: "/static/js/areaXML/shangyu/" + twon + ".xml",
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    type: "get",
                    dataType: "xml",
                    success: function(xml) {
                        b = mapstol.drawBoundary("", xml, {
                            fillOpacity: 0.3,
                            strokeColor: "#f00",
                            fillColor: colorHex
                        });
                        bounderList.push(b);
                    },
                    error: function() {
                        alert("网络请求失败！无法绘制地图区域！");
                    }
                });
            }

        });
        //end 事件管理地图描边

        //来自message页面的lam预警点击事件
        $("a[name=alarmButton]").on("click",null, function() {
            for (var j = 0; j < bounderList.length; j++) {
                var item = bounderList[j];
                $('#' + item.id).html("");
            }
            bounderList = [];
            var id = $(this).children("input[type=hidden]").val();
            //点击右菜单时地图点击选中相应的预警图标start
            var markerArr = that.map.mapMarkers;
            $.each(markerArr, function(i, item) {
                if(id==item.content.content.id){
                   var t = item.text; 
                   $(t).click();
                }
            })
            //点击右菜单时地图点击选中相应的预警图标end
            $.ajax({
                type: "get",
                url: "alarm!lam.action",
                data: {
                    "id": id
                },
                dataType: "json",
                success: function(json) {
                    clear();
                    var colorStr = json.alarmType.color;
                    var colorHex = '#ff0000';

                    if (colorStr == 'Orange') {
                        colorHex = '#f58c1f';
                    } else if (colorStr === 'Yellow') {
                        colorHex = '#ffe400'
                    } else if (colorStr == 'Blue') {
                        colorHex = '#2e62ff'
                    }
                    //                    $(".snow_warn").remove();
                    //                    $(".event").css('display', 'none');
                    //                    $(".notice").css('display', 'none');
                    //                    var content = "<b>预警内容:</b>" + json.signalMean;
                    //                    var defence = "<b>防御措施:</b>" + json.defence;
                    //                    var image = "<img src='${base}/images/warnsmall/" + json.image + "' class='snow_warn' style='z-index:2000;top:400px;left:400px;'>";
                    //                    var time = json.publishTime;
                    //                     var strDate = new Date(time);
                    //                     var year=strDate.getFullYear();  
                    //                     var month=strDate.getMonth()+1; 
                    //                     var day=strDate.getDate();       
                    //                     var hh=strDate.getHours();  
                    //                     var mm=strDate.getMinutes();  
                    //                     time=year+"-"+month+"-"+day+" "+hh+":"+mm+":00";
                    var time = new Date(json.publishTime).toISOString2();
                    //                    $("#alarm_time").empty();
                    //                    $("#signal_mean").empty();
                    //                    $("#defence").empty();
                    //                    $("#image_div").empty();
                    //                    $("#alarmText").empty();
                    //                    $("#alarm_time").append(time);
                    //                    $("#signal_mean").append(content);
                    //                    $("#defence").append(defence);
                    //                    $("#image_div").append(image);
                    //                    $("#alarmText").append(json.signalName+"实况预警信息:");
                    //                    $("#en_div").hide();
                    //                    $("#alarm_div").show();
                    //                    $("#image_div").show();
                    var twon = '';
                    var needArea = '';
                    var parea = json.publishArea.split(",");
                    for (var i = 0; i < parea.length; i++) {
                        needArea = parea[i];
                        for (var key in areaMap) {
                            if (key == needArea) {
                                twon = areaMap[key];
                            }
                        }
                        $.ajax({
                            url: "/static/js/areaXML/shangyu/" + twon + ".xml",
                            contentType: "application/x-www-form-urlencoded; charset=utf-8",
                            type: "get",
                            dataType: "xml",
                            success: function(xml) {
                                b = mapstol.drawBoundary("", xml, {
                                    fillOpacity: 0.3,
                                    strokeColor: "#f00",
                                    fillColor: colorHex
                                });
                                bounderList.push(b);
                            },
                            error: function() {
                                alert('填充地图操作发生错误！');
                            }
                        });
                    }
                }
            });
        });
        //over
        //来自message页面的lev事件点击事件
        $("a[name=eventButton]").on("click", function() {
            var ther = $(this).children("input[type=hidden]").val();
            for (var j = 0; j < bounderList.length; j++) {
                var item = bounderList[j];
                $('#' + item.id).html("");
            }
            bounderList = [];
            $("div[name='dele']").remove();
            //点击右菜单时地图点击选中相应的事件图标start
            var markerArr = that.map.mapMarkers;
            $.each(markerArr, function(i, item) {
                if(ther==item.content.content.id){
                   var t = item.text; 
                   $(t).click();
                }
            });
            //点击右菜单时地图点击选中相应的事件图标end
            $.ajax({
                type: "get",
                url: "event!lev.action",
                data: {
                    "id": ther
                },
                dataType: "json",
                success: function(json) {
                    clear();
                    $(".snow_warn").remove();
                    $(".event").css('display', 'block');
                    $(".alarm").css('display', 'none');
                    $(".notice").css('display', 'none');
                    var color = json.alarmLevel;
                    var name;
                    var colorHex = '#ff0000';
                    if (color == 1) {
                        name = "红色";
                        colorHex = '#ff0000';
                    } else if (color == 2) {
                        name = "橙色";
                        colorHex = '#f58c1f';
                    } else if (color == 3) {
                        name = "黄色";
                        colorHex = '#ffe400';
                    } else if (color == 4) {
                        name = "蓝色";
                        colorHex = '#2e62ff'
                    }
                    
                    //                    var title = json.type+name;
                    //                    var content = "<b>发布原因:</b>" + json.reason;
                    //                    //                     var time = json.time;
                    //                    //                     var strDate = new Date(time);
                    //                    //                     var year=strDate.getFullYear();  
                    //                    //                     var month=strDate.getMonth()+1; 
                    //                    //                     var day=strDate.getDate();       
                    //                    //                     var hh=strDate.getHours();  
                    //                    //                     var mm=strDate.getMinutes();  
                    //                    //                     time=year+"-"+month+"-"+day+" "+hh+":"+mm+":00";
                    //                    var time = new Date(json.time).toISOString2();
                    //                    $("#wb_tit_time").empty();
                    //                    $("#fbcontent").empty();
                    //                    $("#eventText").empty();
                    //                    $("#eventText").append(title);
                    //                    $("#wb_tit_time").append(time);
                    //                    $("#fbcontent").append(content);
                    //                    $("#alarm_div").hide();
                    //                    $("#image_div").hide();
                    //                    $("#en_div").show();
                    var twon = '';
                    var needArea = '';
                    var area = json.area.split(",");
                    for (var i = 0; i < area.length; i++) {
                        needArea = area[i];
                        for (var key in areaMap) {
                            if (key == needArea) {
                                twon = areaMap[key];
                            }
                        }
                        $.ajax({
                            url: "/static/js/areaXML/shangyu/" + twon + ".xml",
                            contentType: "application/x-www-form-urlencoded; charset=utf-8",
                            type: "get",
                            dataType: "xml",
                            success: function(xml) {
                                b = mapstol.drawBoundary("", xml, {
                                    fillOpacity: 0.3,
                                    strokeColor: "#f00",
                                    fillColor: colorHex
                                });
                                bounderList.push(b);
                            },
                            error: function() {
                                alert("网络请求失败！无法绘制地图区域！");
                            }
                        });
                    }
                }
            });
        });
        //end
        
        //事件审核页面展示当前选择区域
        $("#showAreas2").on("click",null, function() {
        	console.log("show areas go!");
        
            bounderList = [];
            clear();
            var color = $('select[name="model.alarmLevel"]').val();
            var name;
            var colorHex = '#ff0000';
            if (color == 1) {
                name = "红色";
                colorHex = '#ff0000';
            } else if (color == 2) {
                name = "橙色";
                colorHex = '#f58c1f';
            } else if (color == 3) {
                name = "黄色";
                colorHex = '#ffe400';
            } else if (color == 4) {
                name = "蓝色";
                colorHex = '#2e62ff'
            }
            var twon = '';
            var needArea = '';
            var area = $('input[type="checkbox"][name="areaListCheckbox"]:checked').map(function(){return this.value});
            for (var i = 0; i < area.length; i++) {
                needArea = area[i];
                for (var key in areaMap) {
                    if (key == needArea) {
                        twon = areaMap[key];
                    }
                }
                $.ajax({
                    url: "/static/js/areaXML/shangyu/" + twon + ".xml",
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    type: "get",
                    dataType: "xml",
                    success: function(xml) {
                        b = mapstol.drawBoundary("", xml, {
                            fillOpacity: 0.6,
                            strokeColor: "#f00",
                            fillColor: colorHex
                        });
                        bounderList.push(b);
                    },
                    error: function() {
                        alert("网络请求失败！无法绘制地图区域");
                    }
                });
            }
        });
        //end
        
        
        //来自message页面的notice公告通知的点击事件
        $("a[name=noticeButton]").on("click",null, function() {
            var ther = $(this).children("input[type=hidden]").val();
            //清除地图上的花边
            for (var k = 0; k < bounderList.length; k++) {
                var item = bounderList[k];
                $('#' + item.id).html("");
            }
            bounderList = [];
            //点击右菜单时地图点击选中相应的通知图标start
            var markerArr = that.map.mapMarkers;
            $.each(markerArr, function(i, item) {
                if(ther==item.content.content.id){
                   var t = item.text; 
                   $(t).click();
                }
            });
            //点击右菜单时地图点击选中相应的通知图标end
            //
            $.ajax({
                type: "get",
                url: "notice!getNotice.action",
                data: {
                    "model.id": ther
                },
                dataType: "json",
                success: function(result) {
                    if (result.code != 0) {
                        alert(result.message);
                        return;
                    }
                    $(".snow_warn").remove();
                    $(".event").css('display', 'block');
                    $(".alarm").css('display', 'none');
                    $(".event").css('display', 'none');
                    var obj = result.content;
                    if (obj === null || obj === undefined) {
                        alert("无法获取记录！");
                        return;
                    }

                    //                    var title = obj.title;
                    //                    var content = "<b>发布原因:</b>" + obj.reason;
                    //                    var time = new Date(obj.publishTime).toISOString2();
                    //                    $("#wb_tit_id").empty();
                    //                    $("#notice_time").empty();
                    //                    $("#notcontent").empty();
                    //                    $("#wb_tit_id").append(title);
                    //                    $("#notice_time").append(time);
                    //                    $("#notcontent").append(content);
                    //                    $("#alarm_div").hide();
                    //                    $("#image_div").hide();
                    //                    $("#notice_div").show();

                    var needArea = '';
                    var area = obj.area.split(",");
                    for (var i = 0; i < area.length; i++) {
                        needArea = area[i];
                        for (var key in areaMap) {
                            if (key == needArea) {
                                twon = areaMap[key];
                            }
                        }
                        $.ajax({
                            url: "/static/js/areaXML/shangyu/" + twon + ".xml",
                            contentType: "application/x-www-form-urlencoded; charset=utf-8",
                            type: "get",
                            dataType: "xml",
                            success: function(xml) {
                                b = mapstol.drawBoundary("", xml, {
                                    fillOpacity: 0.3,
                                    strokeColor: "#f00",
                                    fillColor: "#e627cf"
                                });
                                bounderList.push(b);
                            },
                            error: function() {
                                alert("网络请求失败！无法绘制地图区域！");
                            }
                        });
                    }
                }
            });
        });
        //end
        
        $("#btnEdit").click(function() {
        	layer.removeAllFeatures();
        	$(".selected-area").html(" ")
            $(".m-checkbox.checkboxArea").each(function() {
            	$(this).find(".radioclass").prop("checked", false);
            	$(this).find(".checkbox").removeClass("on_check");
            });
        });
        
        $('#btnSave').click(function(e) {
        	

        	$("#points").val("");
            if (layer) {
                var index = layer.features.length;
                var areaArray = [];
                for (var ind = 0; ind < index; ind++) {
                    var geom = layer.features[ind].geometry.components[0];
                    var points = '';
                    for (var i = 0; i < geom.components.length; i++) {
                        points += geom.components[i].x + ',' + geom.components[i].y + ';';
                    }
                    points = points.substr(0, points.length - 1);
                    
                    console.info("所有点的集合：" + points);
                    
                    
                    $.ajax({
                        url: "/sev/alarm/getAreaJsonData",
                        data: {
                            "points": points
                        },
                        async: false,
                        //dataType: "json",
                        success: function(areas) {
                        	console.log(areas);
                            //var areaAr = eval('(' + areas + ')');
                           // var areaAr = $.parseJSON(areas);
                            var areaAr = areas;
                            var lat;
                            var lot;
                            for (var key in areaAr) {
                            	console.log(key);
                                if (key == "town") {
                                    console.log(areaAr[key]);
                                    //		            					 areaArray.concat(areaAr[key]);
                                    var text = areaAr[key];
                                    for (var twonName = 0; twonName < text.length; twonName++) {
                                        areaArray.push(text[twonName]);
                                    }
                                }
                                if (key == "lat") {
                                    lat = areaAr[key];
                                }
                                if (key == "lot") {
                                    lot = areaAr[key];
                                }
                                if (key == "points") {
                                    $("#points").val(areaAr[key]);
                                }
                                
                            }
                            $("#lat").val(lat);
                            $("#lot").val(lot);
                        }
                    });
                }
                //$(".checkbox_style input[type='checkbox']").prop("checked", false);
               /* $("[name='pubRangeName1']").removeClass("on_check");
                $("[name='pubRangeName']").removeClass("on_check");
                console.log(areaArray);
                for (var i = 0; i < areaArray.length; ++i) {
                    var areaName = areaArray[i];
                    need(areaName);
                    $("[name='pubRangeName1']").each(function() {
                        var name = $(this).attr('value');
                        //alert(name);
                        if (name === areaName) {
                        	//alert(name);
                        	$(this).addClass("on_check");
                       
                        }
                    });
                    $("[name='pubRangeName']").each(function() {
                        var name = $(this).attr('value');
                        //alert(name);
                        if (name === areaName) {
                        	$(this).prop("checked", true);
                        	
                        }
                    });
                }*/
               
                //setTimeout("$('#fxy').click()",1);
                $(".m-checkbox.checkboxArea").each(function() {
                	$(this).find(".radioclass").prop("checked", false);
                	$(this).find(".checkbox").removeClass("on_check");
                });
                $("[name='AreaAllTown']").each(function() {
                	$(this).removeClass("on_check");
                });
                console.log(areaArray);
                $(".selected-area").append("你已选择的区域：")
                for (var i = 0; i < areaArray.length; ++i) {
                    var areaName = areaArray[i];
                    $(".selected-area").append("<span style='color:red'>"+areaName+"</span>")
                    $(".selected-area").show();
                    //need(areaName);
                    /*$("[name='AreaAllTown']").each(function() {
                    	var name = $(this).attr('value');
                    	if (name === areaName) {
                        	$(this).click();
                        	
                        }
                    });*/
                    $("[name='pubRangeName1']").each(function() {
                    	
                        var name = $(this).attr('value');
                        //alert(name);
                        if (name === areaName&&!($(this).is('.on_check'))) {
                        	$(this).click();
                        	
                        }
                    });
//                    $("[name='pubRangeNameTown']").each(function() {
//                    	
//                        var name = $(this).attr('value');
//                        //alert(name);
//                        if (name === areaName&&!($(this).is('.on_check'))) {
//                        	$(this).click();
//                        	
//                        }
//                    });
                }
                $('.checkbox_style input[type="checkbox"]:checked').parent('div .checkbox_style').addClass('checkbox_styles');
                $('.checkbox_style input[type="checkbox"]:not(:checked)').parent('div .checkbox_style').removeClass('checkbox_styles');
                
                //移除选择好的多边形
               //layer.removeAllFeatures();
               DrawControls.drag.activate();
               DrawControls.polygon.deactivate();
               if(areaArray.length >0) {
            	   $(".save-pop").fadeIn(200);
            	   $(".save-pop .popup_con").append("<div style='position: fixed;background: #fff;left: 50%;transform: translate(-50%,-50%);top: 50%;padding: 20px;border-radius: 10px;box-shadow: 0 0 8px 2px rgba(0,0,0,0.24);'><span>保存成功!</span></div>")
                   $(".save-pop").fadeOut(1000);
                   close();
               }else {
            	   $(".edit-pop").fadeIn(200);
            	   $(".edit-pop .popup_con").append("<div style='position: fixed;background: #fff;left: 50%;transform: translate(-50%,-50%);top: 50%;padding: 20px;border-radius: 10px;box-shadow: 0 0 8px 2px rgba(0,0,0,0.24);'><span>绘图不规范，请重新绘图！</span></div>")
                   $(".edit-pop").fadeOut(3000);
//            	   alert("绘图不规范，请重新绘图！");
            	   layer.removeAllFeatures();
               }
            }
            
        });
    };
});