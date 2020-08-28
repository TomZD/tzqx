define('zhxx.js', function (require, exports, module) {
    var GIS = require("lib/gigis/gi-gis");
    var Layer = require("components/map-layer");
    var timerAxis = null;
    var Ismapfirstload = true;
    var warningMarkerlayers;
    var zooms = 11;
    var parmTemp;
    var markerlayer;
    var contourlayer;
    //等值线图层
    var contourLayer;
    var oneValue = 1,//最外层li判断 取 value
        outType = "",//二级li判断 取 type
        lowType = "";//三级li判断 取 type
	var boundaryData = [];
	var boundary1;
	var radarLayer;
    //初始化gis：此处可以设置默认图层：baselayer
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
        wrapDateLine : true
    });
    // 图层上显示经纬度
//    gis.addControl("mousePosition", 24, -1, {
//        prefix: "经度：",
//        separator: " 纬度：",
//    });
    var mapLayer = Layer(gis);
    if (!contourlayer) {
        contourlayer = Layer(gis);
    }
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
        "click": function () {
            $(".map-left>li").removeClass("click");
        },
        "moveend": function (e) {
            var zoom = gis.getZoom();
            var center = gis.getCenter();
            localStorage.setItem("zoom", zoom);
            localStorage.setItem("center", JSON.stringify(center));
        }
    });
    (function () {
        $(".olMapViewport").append('<img class="map-shaw" src="./static2/images/map_control.png">');
        var mapControlAdd = "<a herf='javascript:void(0)' class='olButton' id='leadingOut' style='cursor: pointer'><img src='./static2/images/map_tool/export.png' title='导出'></a>";
//      var mapControlAdd ="<a herf='javascript:void(0)' class='olButton'>+</a>";
//      mapControlAdd  += "<a herf='javascript:void(0)' class='olButton'>-</a>"
//      $(".olControlZoom").append(mapControlAdd);
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
        var ahd = avd * Math.PI / 180;
        $(".olControlZoom .olButton").each(function (index, element) {
            $(this).css({
                "left": -dotLeft - Math.sin((ahd * index)) * radius,
                "top": Math.cos((ahd * index)) * radius + dotTop
            });
        });
    })();

    //获取菜单
    getitem();

    function getitem() {
        var data = {
            id: 3
        };
        $(".fzjz-save").empty();
        var indexLayer = layer.load(2);
        getData('/sen/station/getOneMenu', "get", data, "json", function (data) {
            console.log(data);
            layer.close(indexLayer);
            if (data) {
                var sum = '';
                for (var i = 0; i < data.length; i++) {					//type="'+data[i].type
                    if (data[i].dataType) {
                        var str = '<li class="' + data[i].icon + '" value="' + data[i].value + '" icon-type="' + data[i].type + '" data-name="' + data[i].name + '"><i></i><span>' + data[i].name + '</span></li>';
                        if (data[i].liMenu != null) {
                            var limune = '<ul class="meau_erji">';
                            for (var j = 0; j < data[i].liMenu.length; j++) {

                                if (data[i].liMenu[j].threeMenu && data[i].liMenu[j].threeMenu != null) {
                                    var threemenu = "";
                                    for (var k = 0; k < data[i].liMenu[j].threeMenu.length; k++) {
                                        threemenu += '<li class="threeV" fileurl="' + data[i].liMenu[j].threeMenu[k].fileURL + '" data-type="' + data[i].type + '" data-id="' + data[i].liMenu[j].threeMenu[k].typeId + '"  type="' + data[i].liMenu[j].threeMenu[k].type + '"><i></i>' + data[i].liMenu[j].threeMenu[k].name + '</li>';
                                    }

                                    limune += '<li class="emV" data-type="' + data[i].type + '" lower-type="' + data[i].liMenu[j].type + '" type="' + data[i].liMenu[j].type + '"><div class="dropDown"><span>' + data[i].liMenu[j].name + '</span><i class="afterI"></i></div><ul class="menu_sanji">' + threemenu + '</ul></li>';

                                } else {

                                    limune += '<li class="emV" data-type="' + data[i].type + '" icon-type="' + data[i].liMenu[j].icon + '" lower-type="' + data[i].liMenu[j].type + '"><div class="noDropDown erjiChecked"><i class="prevI"></i><span>' + data[i].liMenu[j].name + '</span></div></li>';
                                }

                            }
                            limune += "</ul>";
                            str = '<li class="' + data[i].icon + '" value="' + data[i].value + '" data-type="' + data[i].type + '"><i></i><span>' + data[i].name + '</span>' + limune + '</li>';
                        }
                    } else {
                        var str = '<li class="' + data[i].icon + '" value="' + data[i].value + '"><i></i><span>' + data[i].name + '</span></li>';
                    }
                    sum += str;
                }
                $(".fzjz-save").append(sum);
                $(".fzjz-save").find("ul").eq(0).show();
                $(".fzjz-save").find("ul").eq(0).find("li").eq(0).click();
            } else {
                layer.close(indexLayer);
            }

        });
    }

    //获取边界数据
    getBoundaryData()
    function getBoundaryData() {
        var indexLayer = layer.load(2);
        getData('/static2/data/hyq.xml', "get", '', "xml", function (data) {
            //蒙层
            var boundary = gis.drawBoundary("黄岩区", data, {
                fillOpacity: 0.68,//透明度
                fillColor: "#2077be",//填充色
                strokeColor: "red",//边界线条颜色灰色
                strokeWidth: "2",
                isCut: true,
            });
            boundary.setZIndex(100);
            layer.close(indexLayer);
        })
    }
    //一级菜单点击事件
    $("body").on("click", ".fzjz-save>li", function (e) {
        oneValue = parseInt($(this).attr("value"));
        e.stopPropagation();
        var value = parseInt($(this).attr("value"));
        if (value == 6) {
//    		window.location.href = "/typhoon";
            window.open("/typhoon");
        }
        var url = "";
        switch (value) {
            case 7: {
                url = "/sen/disasterInformation/getAll";
                $(".maptitle").hide();
                getStations(url);
                break;
            }
        }
    })
    //菜单栏二级菜单点击事件
    $("body").on("click", ".meau_erji>li", function (e) {
//        $(".maptitle").text("");
        if ($(this).children("div").hasClass("noDropDown")) {
            $(".threeV").removeClass("on");
            $(".menu_sanji").hide();
            $(".emV").removeClass("on");
            $(this).addClass("on");
            $("#radarColorList").hide();
            outType = $(this).attr("lower-type");
            lowType = "";
            if (contourLayer) {
                contourLayer.destroy();
            }
            if(radarLayer){
                radarLayer.destroy();
            }
            if (markerlayer) {
                markerlayer.clearMarkers();
            }
            twoIcon = $(this).attr("icon-type");
            if (twoIcon == "Weather") {//自动站一小时降水
                lowType = $(this).attr("lower-type");
                outType = lowType;
                getTimeAxisData(lowType);
            }

            if (oneValue == 4 ||oneValue == 5) {
                $(".map").show();
                $(".m-tool-box").show();
                $(".maptitle").show();
                $(".m-decision").hide();
                if(outType =="high_rain" || outType == "high_rain_forecast" || outType == "strongwind_area" || outType == "strongwind_area_forecast") {
                	$(".export").hide();
                }else {
                	$(".export").hide();
                }
            }
            if(oneValue == 3){
                $(".map").show();

//                $(".maptitle").show();
                $(".m-decision").hide();
                $(".export").hide();
//                if(outType=="current_risk" || outType == "12hour_risk" || outType == "24hour_risk" || outType == "recently_risk"){
//                    $(".m-tool-box").show();
//                }else{
//                    $(".m-tool-box").hide();
//                }
            }
            //灾害区域划分
            if (oneValue == 1) {
                $(".code").hide();
                $(".map").hide();
                $(".m-tool-box").hide();
                $(".maptitle").hide();
                $(".export").hide();
                var data = {
                    low_type: outType
                };
                getData('/sen/floodRisk/getDisasterImage', "get", data, "json", function (data) {
                    if (data) {
                        var img = '<img class="disaster" src="' + data + '">';
                        var decision = $(".m-decision").html(img);
                        decision.show();
                    }
                });
            }
            //气温大雾专题 二级菜单点击事件进入
            if (oneValue == 8) {
                $(".map").show();
                $(".m-tool-box").show();
                $(".maptitle").show();
                $(".export").hide();
                getMaxTem(outType);
            }

            //地质灾害模块 二级菜单点击事件进入
            if (oneValue == 3) {
                geologicalSite(outType);
            }
            //雷雨大风模块进入
            if (oneValue == 5) {

                getTimeAxisData(outType);
            }
            if(oneValue!=2){
            	$(".no_risk").hide();
                $(".code").hide();
                if(boundary1){
                    clearBoundary();
                }
            }
            if(outType=="rautomatic_station"||outType=="automatic_station"){
                $(".legend").hide();
            }
            //高温
         if(outType=="TMax24" || outType == "TMin24" || outType == "Vis"){
             getMaxTem();
             $(".maptitle").show();
         }

            if(outType=="strongwind_area" || outType == "strongwind_area_forecast"){
                $(".legend").show();
            }else{
                $(".legend").hide();
            }
        }else{
            $(this).addClass("on").siblings().removeClass("on");
            $(this).siblings().find("ul.menu_sanji").hide();
        }
        e.stopPropagation();
    })
    $("body").on("click", ".meau_erji", function (e) {
        e.stopPropagation();
    })
    $("body").on("click", "div.dropDown", function () {
        $(this).next("ul").fadeToggle();
        $(this).parent().siblings().find("ul.menu_sanji").hide();
    })
    //菜单栏三级菜单点击事件
    $("body").on("click",".menu_sanji>li",function(e){
        if (contourLayer) {
            contourLayer.destroy();
        }
        if(radarLayer){
            radarLayer.destroy();
        }
        if (markerlayer) {
            markerlayer.clearMarkers();
        }
    	$(".menu_sanji>li").removeClass("on");
        $(".emV").removeClass("on");
        $(this).parents(".meau_erji").parent().siblings().find("ul.menu_sanji").hide();
    	outType = $(this).parents("li.emV").attr("type");
		lowType = $(this).attr("type");
    	$(this).addClass("on");
    	if(oneValue==4||oneValue==5){	
    	//if(oneValue==5){
            var text = $(this).text();
            if(text.indexOf("级") != -1){
                if(text=="7级-8级"){
                    parmTemp = 1;
                }else if(text=="8级-9级"){
                    parmTemp = 2;
                }else if(text=="9级-10级"){
                    parmTemp = 3;
                } else if(text=="10级以上"){
                    parmTemp = 4;
                }
                getTimeAxisData(outType);
                $(".m-select-time ul li").eq($(".m-select-time ul li").length - 1).click();
            }

    		twoIcon = $(this).attr("icon-type");
    		var id =$(this).attr("data-id");
    		var url = "/sen/station/getKeyPlaces?typeId="+id;
    		getStations(url);
    	}
    	e.stopPropagation();
    	if(oneValue==2){
    		getSmallWatershedData();
            $(".m-tool-box").hide();
            $(".maptitle").hide();
            $(".export").hide();
    	}else {
    		$(".no_risk").hide();
    	}
    	if(outType=="high_rain" ||outType=="high_rain_forecast"){
    		getTimeAxisData();
    		$(".export").hide();
    	}else {
    		$(".export").hide();
    	}
    	if(outType=="key_place"){
    		getTadarTimeAxisControl();
    	}
        if ( oneValue != 1||oneValue !=6) {
            $(".m-decision").hide();
            $(".map").show();
        }
        if(oneValue!=2){
            $(".code").hide();
            if(boundary1){
                clearBoundary();
            }
        }
        if (oneValue == 4 ||oneValue == 5) {
            $(".m-tool-box").show();
            $(".maptitle").show();
        }
        if(outType=="important_place"||outType=="key_place"){
            $("#radarColorList").show();
        }else{
            $("#radarColorList").hide();
        }
        if(outType!="strongwind_area" || outType == "strongwind_area_forecast"){
            $(".legend").hide();
        }
    })
    
    //导出图片
//      $(".export").click(function() {
//     	 var path = $(".m-axis-list li.z-on").attr("path").replace(/\\/g,"/");
// //     	 var path ="E:/hydata/Micaps4/Interval5Min/1hour_R/201908141050.000";
// //          var path ="E:/hydata/Micaps4/Interval5Min/1hour_R/201910270110.000";
//
//     	 window.location.href="http://"+window.location.host+"/sen/rainstormFlood/getImage?path="+path;
//      })

    $(".olMapViewport").on("click", "#leadingOut", function() {
        var path = $(".m-axis-list li.z-on").attr("path").replace(/\\/g,"/");

        window.location.href="http://"+window.location.host+"/sen/rainstormFlood/getImage?path="+path;
    });


   //下拉框
	 
	  $("body").on("click",".m-select-time>ul>li",function(){ 
	    var index = $(this).index();	   
	    timeAxisControl(index);
	    
	  });

    //获取站点数据
    function getStations(url) {
        getData(url, "get", "", "json", function (data) {
            if (data && data.length > 0) {
                showStations(data);
            }
        })
    }

    /**
     * 获取地质灾害站点信息
     * @param data
     */
    function geologicalSite(lowType) {
        var data = {
            lowType: lowType
        }
        getData('/sen/dangerAlarm/getCurrentRisk', "get", data, "json", function (d) {

            // $('.maptitle').html("123");
            if (d && d.length > 0) {
                if (lowType == 'current_risk' ) {
                    $('.maptitle').html(d[0].title);
                    //绑定时间轴 当前风险
                    getTimeAxisDataByRisk(d);
                } else {
                    showGeologicalSite(d);
                }
                $(".maptitle").show();
                $(".m-tool-box").show();
            }else {
            	$(".no_risk").show();
            	$(".m-tool-box").hide();
            	$(".maptitle").hide();
            }
        })
    }

    /**
     * 获取自动站风力6级以上的站点数据
     */
    function getWindRank(data, url) {
        getData(url, "get", data, "json", function (d) {
            if (d && d.length > 0) {
                showWindRank(d);
            }
        })
    }

    /**
     * 获取最高温度（绑定时间轴）
     */
    function getMaxTem(datalist) {
        $(".m-select-time ul").html("");
        $(".m-axis-list").empty();
        var data;
        var url;
        //先通用 data
        data = {
            type: outType,
            lowType: lowType
        }
        //气温专题
        if (outType == "TMax24" || outType == "TMin24" || outType == "Vis") {
            data = {
                lowType: outType
            };
            url = '/sen/station/getMaxTemp';
        }

        getData(url, "get", data, "json", function (datalist) {
            var beginTimeValue;
            var selectStr = "";
            if (datalist) {

                //时间轴数据
                var data = datalist;
                var str = '';
                var str1 = '';
                for (var i = 0; i < data.length; i++) {

                    var time = data[i].time.substr(8, 10);
                    time = time.replace(" ", "");
                    var radartime = data[i].time.replace(/[^0-9]/ig, "");
                    if (i == 0)
                        str += '<li class="z-on" time=' + radartime + ' fileName=' + data[i].fileName + ' path=' + data[i].path + ' title=' + data[i].title.replace(/\s*/g, "") + "情况" + '><span>' + time + '</span></li>';
                    else {
                        str += '<li time=' + radartime + ' fileName=' + data[i].fileName + ' path=' + data[i].path + ' title=' + data[i].title.replace(/\s*/g, "") + "情况" + '><span>' + time + '</span></li>';
                    }
                    str1 += '<li>' + time + '</li>';
                }
                $("#selectDiv").show();
                $(".m-axis-list").html(str);
                $(".m-select-time ul").html(str1);
                //$(".m-axis-list").width(data.length * 100);
                var len = data.length * 100;
                if (len > 960) {
                    $(".m-axis-list").width(data.length * 100);
                } else {
                    $(".m-axis-list").width(960);
                }
                //timeAxisControl(data.length - 1);
                $(".m-select-time ul li").eq($(".m-select-time ul li").length - 1).click();
            }
        });
    }

    /**
     * 展示自动站风力6级以上数据
     */
    function showWindRank(data) {
        //加载点
        if (markerlayer) {
            markerlayer.clearMarkers();
        } else {
            markerlayer = gis.addMarkers();
        }
        $.each(data, function (i, n) {
            var name = n.name;
            var isred = n.isRed;
            var url;
            if (isred == '1') {
                url = './static2/emergencyImages/waterlogging-1.png';
            } else if (isred == '0') {
                url = './static2/emergencyImages/waterlogging.png';
            }
            var icon = {
                url: url,
                width: 25,
                height: 25
            };
            var text = '';
            text += "<div class='site_content' style='width: 50px;'>" + name + "</div>";
            var marker = gis.addMarker({
                icon: icon,
                position: "br",
                lon: parseFloat(n.lon),
                lat: parseFloat(n.lat),
                content: n,
                text: text
            }, markerlayer)[0];
            marker.events.register("click", marker, clickShowWindRank);
        });
    }


    /**
     * 显示地质灾害站点
     * @param data
     */
    function showGeologicalSite(data) {
        //加载点
        if (markerlayer) {
            markerlayer.clearMarkers();
        } else {
            markerlayer = gis.addMarkers();
        }
        $.each(data, function (i, n) {
            var name = n.name;
            var url = './static2/emergencyImages/scenicspot.png';
            var icon = {
                url: url,
                width: 25,
                height: 25
            };
            //气泡详细信息
            //var text = "<div class='site_txt on_site'>" + stationNo + "</div>";
            var text = '';
            text += "<div class='site_content' style='width: 50px;'>" + name + "</div>";
            var marker = gis.addMarker({
                id: n.pointId,
                icon: icon,
                position: "br",
                lon: parseFloat(n.longitude),
                lat: parseFloat(n.latitude),
                content: n,
                text: text
            }, markerlayer)[0];
            marker.events.register("click", marker, clickGeologicalSite);
        });
    }

    /**
     * 点击地质灾害站点 显示详情
     * @param data
     */
    function clickGeologicalSite(e) {
        var data = e.object.content;
        //e代表该站点数据
        var typeName = $(".meau_erji .on span").text();
        var html = "";
        html = "<div class='layer_table'><table><tr><td>站名</td><td>" + data.name + "</td></tr><tr><td>阈值</td><td>" + data.value + "</td></tr>" +
            "<tr><td>经度</td><td>" + data.longitude + "</td></tr><tr><td>纬度</td><td>" + data.latitude + "</td></tr>" +
            "<tr><td>等级</td><td>" + data.level + "</td></tr><tr><td>报警时间</td><td>" + data.time + "</td></tr></table></div>";
        layer.open({
            title: ["<div class='popup_hd_bk'>" + typeName + "</div><div></div>", 'font-size:14px;padding:0;background:#1c4560;color:#fff;height:auto;border-bottom: 1px solid rgba(255,255,255,0.28);'],
            type: 1,
            skin: 'layui-layer-rim',
            closeBtn: 2,
            shade: [0],
            area: ['400px', '330px'],
            content: html,
            resize: false,
        });

    }


    /**
     * 显示自动站风力6级以上的站点信息详情
     * @param data
     */
    function clickShowWindRank(e) {
        var data = e.object.content;
        //e代表该站点数据
        var typeName = $(".meau_erji .on span").text();
        var html = "";
        var isRed = data.isRed == '1' ? "是" : "否";
        html = "<div class='layer_table'><table><tr><td>站名</td><td>" + data.name + "</td></tr><tr><td>风速</td><td>" + data.value + "</td></tr>" +
            "<tr><td>经度</td><td>" + data.lon + "</td></tr><tr><td>纬度</td><td>" + data.lat + "</td></tr>" +
            "<tr><td>风力等级</td><td>" + data.rank + "</td></tr><tr><td>是否高亮</td><td>" + isRed + "</td></tr></table></div>";
        layer.open({
            title: ["<div class='popup_hd_bk'>" + typeName + "</div><div></div>", 'font-size:14px;padding:0;background:#1c4560;color:#fff;height:auto;border-bottom: 1px solid rgba(255,255,255,0.28);'],
            type: 1,
            skin: 'layui-layer-rim',
            closeBtn: 2,
            shade: [0],
            area: ['400px', '330px'],
            content: html,
            resize: false,
        });

    }


    //展示站点数据
    function showStations(data) {
    	console.log(data);
        //加载点
        if (markerlayer) {
            markerlayer.clearMarkers();
        } else {
            markerlayer = gis.addMarkers();
        }
   
        $.each(data, function (i, n) {
            var name = n.name;
            var url;
            var alarm = n.alarm;
            var text = '';
     if(n.icon){
    	 url = "./static2/emergencyImages/"+n.icon+".png";
     }else{
    	 text += "<div class='site_content'>" + name + "</div>";
    	 if(alarm==true){
         	url = "./static2/images/marker/Weather1.png";
         }else{
         	url = "./static2/images/marker/Weather.png";
         }
     }
                
            var icon = {
                url: url,
                width: 25,
                height: 25
            };
            //气泡详细信息
            //var text = "<div class='site_txt on_site'>" + stationNo + "</div>";
  
           

            var marker = gis.addMarker({
                id: n.id,
                icon: icon,
                position: "br",
                lon: parseFloat(n.lon),
                lat: parseFloat(n.lat),
                content: n,
                text: text
            }, markerlayer)[0];
            if(n.icon){
            	marker.events.register("click", marker, clickStation);
            }else{
            	marker.events.register("click", marker, clickRainStation);
            }

            //markerArr.push(marker);
        });
        // markerlayer.setZIndex("9999");
    }
    
    
    /**
     * 显示自动站风力6级以上的站点信息详情
     * @param data
     */
    function clickRainStation(e) {
        var data = e.object.content;
        //e代表该站点数据
        var typeName = $(".meau_erji .on span").text();
        var html = "";
        var isRed = data.alarm == '1' ? "是" : "否";
        html = "<div class='layer_table'><table><tr><td>站名</td><td>" + data.name + "</td></tr><tr><td>降水</td><td>" + data.value + "</td></tr>" +
            "<tr><td>经度</td><td>" + data.lon + "</td></tr><tr><td>纬度</td><td>" + data.lat + "</td></tr>" +
            "</tr><tr><td>是否报警</td><td>" + isRed + "</td></tr></table></div>";
        layer.open({
            title: ["<div class='popup_hd_bk'>" + typeName + "</div><div></div>", 'font-size:14px;padding:0;background:#1c4560;color:#fff;height:auto;border-bottom: 1px solid rgba(255,255,255,0.28);'],
            type: 1,
            skin: 'layui-layer-rim',
            closeBtn: 2,
            shade: [0],
            area: ['400px', '330px'],
            content: html,
            resize: false,
        });

    }
    
    
    


    //点击站点
    function clickStation(e) {
        var value = $(".fzjz-save li.z-on").attr("value");
        var id = e.object.content.id;
        var url = ""
        var data;
        if (value == 7) {
            url = "/sen/disasterInformation/getDisaster";
            data = {
                id: id
            }
        } else if (value == 4 || value == 5) {
            if (twoIcon == "Weather") {
                var path = e.object.content.path;
                var code = e.object.content.name;
                url = "/sen/disasterInformation/getAutomatic";
                data = {
                    path: path,
                    code: code
                }
            } else {
                url = "/sen/station/getOneKeyPlace";
                data = {
                    id: id
                }

            }
        }
        getData(url, "get", data, "json", function (data) {
            if (data) {
                var typeName;
                //layer.close(indexLayer);
                if (value == 7) {//灾情上报
                    typeName = $(".fzjz-save li.z-on").attr("data-name");
                    var imgHtml = "";
                    if (data.imageList && data.imageList.length > 0) {
                        imgHtml += "<ul id='img_popup'>";
                        for (var i = 0; i < data.imageList.length; i++) {
                            if (i == 0)
                                imgHtml += "<li><img src='" + data.imageList[i] + "' data-src='" + data.imageList[i] + "' style='cursor: pointer'></li>";
                            else {
                                imgHtml += "<li style='display:none'><img src='" + data.imageList[i] + "' data-src='" + data.imageList[i] + "'></li>";
                            }
                        }
                        imgHtml += "</ul>";
                    }
                    var html = imgHtml + "<div class='layer_table'><ul></ul><table><tr><td>灾情描述</td><td>" + data.disasterDescription + "</td></tr><tr><td>灾情类型</td><td>" + data.disasterType + "</td></tr><tr><td>灾情地点</td><td>" + data.disasterLocation + "</td></tr></table></div>";
                } else if (value == 4 || value == 5) {
                    if (twoIcon == "Weather") {
                        typeName = "自动站";
                        var html = "<div class='layer_table'><table><tr><td>站名</td><td>" + data.name + "</td></tr><tr><td>数值</td><td>" + data.value + "</td></tr>" +
                            "<tr><td>经度</td><td>" + e.object.content.lon + "</td></tr><tr><td>纬度</td><td>" + e.object.content.lat + "</td></tr></table></div>"
                    } else {
                        typeName = data.type;
                        var html = "<div class='layer_table'><table><tr><td>名称</td><td>" + data.name + "</td></tr><tr><td>位置</td><td>" + data.location + "</td></tr>" +
                            "<tr><td>经度</td><td>" + data.longitude + "</td></tr><tr><td>纬度</td><td>" + data.latitude + "</td></tr><tr><td>单位</td><td>" + data.depart + "</td></tr><tr><td>联系方式</td><td>" + data.phone + "</td></tr></table></div>";
                    }
                }
                layer.open({
                    title: ["<div class='popup_hd_bk'>" + typeName + "</div><div></div>", 'font-size:14px;padding:0;background:#1c4560;color:#fff;height:auto;border-bottom: 1px solid rgba(255,255,255,0.28);'],
                    type: 1,
                    skin: 'layui-layer-rim',
                    closeBtn: 2,
                    shade: [0],
                    area: ['400px', '330px'],
                    content: html,
                    resize: false,
                });
                if (value == 7) {
                    var viewer = new Viewer(document.getElementById('img_popup'), {
                        url: 'data-src',
                        show: function () {
                            viewer.update();
                        }
                    });
                }
            }
        });
    };

    //点击下一个时次
    $(".m-next").click(function (e) {
        if (e.which) {//是手动点击
            stopPlay();
        }
        var index = $(".m-axis-list li.z-on").index();
        var num = $(".m-axis-list li").length;
        if (index == num - 1) return;
        index++;
        timeAxisControl(index);
        var txt = $(".m-select-time ul li").eq(index).text();
        $(".m-select-time>span").text(txt);
    })
    //点击上一个时次
    $(".m-prev").click(function () {
        stopPlay();
        var index = $(".m-axis-list li.z-on").index();
        if (index == 0) return;
        index--;
        timeAxisControl(index);
        var txt = $(".m-select-time ul li").eq(index).text();
        $(".m-select-time>span").text(txt);
    });
    //直接点击时间轴
    $("body").on("click", ".m-axis-list li", function () {
        stopPlay();
        var index = $(this).index();
        timeAxisControl(index);
        var txt = $(".m-select-time ul li").eq(index).text();
        $(".m-select-time>span").text(txt);
    })
    //点击播放
    $(".m-paly").click(function () {
        if ($(this).hasClass("stop")) {
            stopPlay();
        } else {
            $(this).addClass("stop")
            clearInterval(timerAxis);
            timerAxis = setInterval(function () {
                $(".m-next").click();
            }, 3000);

        }
    });

    //停止播放
    function stopPlay() {
        $(".m-play").removeClass("stop");
        if (timerAxis) {
            clearInterval(timerAxis);
        }
    }


    /**
     * 时间轴滚动
     */
    function timeAxisControl(index) {
        //alert(parmTemp);
        var path = $(".m-axis-list li").eq(index).attr("path");
        var title = $(".m-axis-list li").eq(index).attr("title");
        $(".maptitle").text(title);
        $(".maptitle").show();
        var data;
        var url;
        if (outType == "strongwind_area" || outType == "strongwind_area_forecast") {
            data = {
                xmlPath: path
            }
            url = '/sen/refinement/getStrongwindAreaData';
        } else if (outType == "high_rain" || outType == "high_rain_forecast") {
            data = {
                path: path
            }
            url = '/sen/rainstormFlood/getRainXml';
        } else if (outType == "rautomatic_station") {
            data = {
                path: path
            }
            url = '/sen/rainstormFlood/getStationData';
        }
        //雷雨大风 大风落区模块
        else if (outType == "automatic_station") {
            data = {
                xmlPath: path,
                parmTemp:parmTemp
            }
            url = '/sen/refinement/getWindRank';
            //开始显示站点数据
            //getWindRank(data,url);
        }
        //地质灾害 当前风险模块
        else if (outType == "current_risk") {
            data = {
                time: path
            }
            url = '/sen/dangerAlarm/getCurrentRiskData';
            //开始显示站点数据
            //getCurrentRisk(data,url);
        }
        else if(outType=="key_place"){
			var fileName = $(".m-axis-list li").eq(index).attr("fileName");
			var urlTime = $(".m-axis-list li").eq(index).attr("time");
			var data = {
				icon:"XCSRadarCR_Z9576",
				fileName:fileName,
				time:urlTime,
				type:"CR"
			}	 
			url='/sen/radarAndCloud/getData';
        }
        //气温大雾模块
        else if(outType == "TMax24" || outType == "TMin24" || outType == "Vis"){
            data = {
                xmlPath: path
            }
            url = '/sen/station/getStationList';
        }

      getData(url, "get", data, "json", function (d) {
          if (d) {
              if(outType=="current_risk"){
                  showGeologicalSite(d);
              }
              else if(outType=="automatic_station"){
                  showWindRank(d);
              }
        	  else if(outType=="rautomatic_station"){
        		  showStations(d);
        	  }else if(outType=="key_place"){
        		  var newData = JSON.parse(d);
				  var radartime = $(".m-axis-list li").eq(index).attr("time");
				  if(radartime){
					  bindCloudData(newData.path);
					  $(".maptitle").text(newData.title);
			      }
        	  }else if(outType == "TMax24" || outType == "TMin24" || outType == "Vis") {
                  if (JSON.parse(d).xml) {
                      contourData = JSON.parse(d).xml;
                  }
                  drawContour(contourData);
              }else{
        		 drawContour(d);
        	  } 
          }
        })
        $(".m-axis-list li").eq(index).addClass("z-on").siblings().removeClass("z-on");
        var timeAxix = $(".m-time-axis-box");
        var timeW = timeAxix.width();
        var liW = $(".m-axis-list li").eq(0).width();
        var pageSize = Math.floor(timeW / liW);
        pageSize = pageSize / 2;
        var curPage = index - pageSize;
        if (curPage > 0) {
            timeAxix.animate({scrollLeft: liW * curPage}, 300);
        } else {
            timeAxix.animate({scrollLeft: 0}, 300);
        }
    }


    function getSign(level, time) {
        var sign = "";
        if (parseInt(level) == 1) {
            sign = "隐患点";
        } else if (parseInt(level) == 2) {
            sign = "蓝色预警(2级," + time + ")";
        } else if (parseInt(level) == 3) {
            sign = "黄色预警(3级," + time + ")";
        } else if (parseInt(level) == 4) {
            sign = "橙色预警(4级," + time + ")";
        } else if (parseInt(level) == 5) {
            sign = "红色预警(5级," + time + ")";
        }
        return sign;
    }

    function addMarker(level, lon, lat, title) {
        var icon = {url: "/static2/images/disaster/" + parseInt(level) + ".png", width: 14, height: 14};
        var _marker = gis.addMarker({
            icon: icon,
            lon: lon,
            lat: lat,
            offsetX: -10,
            offsetY: -26,
            content: title
        }, marks)[0];
        _marker.events.register("click", _marker, addPop);
        //获取经纬度
        //	gis.addPopup(lon, lat, title, 0, -26, true)});
    }

    var pop;

    function addPop(e) {
        if (pop && pop.arrow) {
            pop.destroy();
        }
        var typeObject = e.object;
        pop = gis.addPopup(typeObject._lon, typeObject._lat, typeObject.content, 0, -26, true);

    }

    /**
     * 绑定时间轴
     */
    function getTimeAxisData() {
        $(".m-select-time ul").html("");
        $(".m-axis-list").empty();
        var data;
        var url;
        //先通用 data
        data = {
            type: outType,
            lowType: lowType
        }
        //雷雨大风落区 风力六级以上
        if (outType == "strongwind_area" || outType == "strongwind_area_forecast") {
            data = {
                lowType: outType
            };
            url = '/sen/refinement/getStrongwindArea';
        } else if (outType == "high_rain" || outType == "high_rain_forecast") {
            url = '/sen/rainstormFlood/getTimeList';
        } else if (outType == "rautomatic_station") {
            url = '/sen/rainstormFlood/getSTimeList';
        } else if (outType == "automatic_station") {
            data = {
                lowType: outType
            };
            url = '/sen/refinement/getWindTimeList';
        }

        getData(url, "get", data, "json", function (datalist) {
            var beginTimeValue;
            var selectStr = "";
            if (datalist) {
                //时间轴数据
                var data = datalist;
                if (outType == "strongwind_area" || outType == "strongwind_area_forecast") {
                    data = JSON.parse(datalist).fileList;
                }
                if (outType == "automatic_station") {
                    data = JSON.parse(datalist).fileList;
                }
                var str = '';
                var str1 = '';
                for (var i = 0; i < data.length; i++) {
                    var time = data[i].time.substr(8, 10);
                    time = time.replace(" ", "");
                    var radartime = data[i].time.replace(/[^0-9]/ig, "");
                    if (i == 0)
                        str += '<li class="z-on" time=' + radartime + ' fileName=' + data[i].fileName + ' path=' + data[i].path + ' title=' + data[i].title.replace(/\s*/g, "") + "情况" + '><span>' + time + '</span></li>';
                    else {
                        str += '<li time=' + radartime + ' fileName=' + data[i].fileName + ' path=' + data[i].path + ' title=' + data[i].title.replace(/\s*/g, "") + "情况" + '><span>' + time + '</span></li>';
                    }
                    str1 += '<li>' + time + '</li>';
                }
                $("#selectDiv").show();
                $(".m-axis-list").html(str);
                $(".m-select-time ul").html(str1);
                //$(".m-axis-list").width(data.length * 100);
                var len = data.length * 100;
                if (len > 960) {
                    $(".m-axis-list").width(data.length * 100);
                } else {
                    $(".m-axis-list").width(960);
                }
                //timeAxisControl(data.length - 1);
                $(".m-select-time ul li").eq($(".m-select-time ul li").length - 1).click();
            }
        });
    }

    //站点实况详细信息1
    //获取等值线数据
    //加载等值线图
    function drawContour(data, num) {
        if (data) {
            var xmlData = createXml(data);
            //清空对象
            if (contourLayer) {
                contourLayer.destroy();
            }
            if (xmlData) {
                if (num == undefined) {
                    num = 0.5;
                }
                contourLayer = contourlayer.add("grid", "", xmlData, true, num);
            }
        }

    }


    /**
     *地质灾害模块 当前风险 绑定时间轴
     */
    function getTimeAxisDataByRisk(data) {
        $(".m-select-time ul").html("");
        $(".m-axis-list").empty();
        if (data) {
            //时间轴数据
            var str = '';
            var str1 = '';
            for (var i = 0; i < data.length; i++) {
                var timeToBase = data[i].time;
                var title = data[i].title;
                var time = data[i].primaryTime.substr(8, 10);
                time = time.replace(" ", "");
                var radartime = data[i].primaryTime.replace(/[^0-9]/ig, "");
                if (i == 0)
                    str += '<li class="z-on" time=' + radartime + ' path="' + timeToBase + '" title="' + title + '"><span>' + time + '</span></li>';
                else {
                    str += '<li time=' + radartime + ' path="' + timeToBase + '" title="' + title + '"><span>' + time + '</span></li>';
                }
                str1 += '<li>' + time + '</li>';
            }
            $("#selectDiv").show();
            $(".m-axis-list").html(str);
            $(".m-select-time ul").html(str1);
            //$(".m-axis-list").width(data.length * 100);
            var len = data.length * 100;
            if (len > 960) {
                $(".m-axis-list").width(data.length * 100);
            } else {
                $(".m-axis-list").width(960);
            }
            //timeAxisControl(data.length - 1);
            $(".m-select-time ul li").eq($(".m-select-time ul li").length - 1).click();
        }
    }

    /**
     * 地质灾害模块 当前风险 展示站点数据
     */
    function getCurrentRisk(data, url) {
        getData(url, "get", data, "json", function (d) {
            if (d) {
                showGeologicalSite(d);
            }
        })
    }
    //小流域山洪 模块
    function getSmallWatershedData(){
        if(boundary1){
            clearBoundary();
        }
    	var data = {
    		outType:outType,
    		lowType:lowType
    	}
    	url='/sen/floodRisk/getFloodRisk';
    	 getData(url, "get", data, "json", function (d) {
             if (d&&d.length>0) {
            	 var str='';
            	 $(".code").show();
            	 $(".code ul").empty();
            	 var colorCode = d[0].color_code.split("\r\n");
 	    		 for(var j=0;j<colorCode.length;j++){
     				var codeValue = colorCode[j].split(" ")[0];
     				var codeColor = colorCode[j].split(" ")[1];
     				str+='<li><span>'+codeValue+'</span><i style="background:rgb('+codeColor+')"></i></li>';
     			 }
                 for(var i=0;i<d.length;i++){
                	 var areaName = d[i].areaName;
                	 var level  = d[i].level ;
                	 var value   = d[i].value  ;
                	 var color  = "rgb("+d[i].color+")" ;
                	 showSmallWatershedColor(areaName,color);
                 }
                 $(".code ul").html(str);
             }else {
            	 $(".no_risk").show();
             }
         })
    }
    //画小流域色块图
    function showSmallWatershedColor(name,color){
    	url = '/static2/data/'+name+'.xml';
		//var indexLayer=layer.load(2);
        getData(url,"get",'',"xml",function (data){
            //蒙层
            boundary1 = gis.drawBoundary(name, data, {
                fillOpacity: 0.68,//透明度
                fillColor: color,//填充色
                strokeColor: "tansparent",//边界线条颜色灰色
                strokeWidth: "1",
              
            });
            //layer.close(indexLayer);
            boundaryData.push(boundary1);
        })
    }
    function clearBoundary() {
        for (var i = 0; i < boundaryData.length; i++) {
            var boundary = boundaryData[i];
            if (boundary) {
                boundary.destroy();
            }
        }
        boundaryData = [];
    }
    //获取雷达、卫星时间轴
	 function getTadarTimeAxisControl(){
		 $(".m-select-time ul").html("");
		 var data = {
		      type:"CR",
		 };
		 getData('/sen/radarAndCloud/getFileList',"get",data,"json",function (data) {
			 if(data){
				 var data = JSON.parse(data);
				 $(".m-axis-list").empty();
   			  var str = '';
   			  var str1 = '';
   			  radarFile = data.fileList;
   			  for(var i=0;i<data.fileList.length;i++){
   				  var time = data.fileList[i].time.substr(8,10);
   				  time = time.replace(" ","");
   				  var radartime = data.fileList[i].time.replace(/[^0-9]/ig,"");
   				  if(i==0)
   					  str +='<li class="z-on" time='+radartime+' fileName='+data.fileList[i].fileName+'><span>'+time+'</span></li>';
   				  else{
   					  str +='<li time='+radartime+' fileName='+data.fileList[i].fileName+'><span>'+time+'</span></li>';
   				  }
   				  str1+='<li>'+time+'</li>';
   			  }
	   			if (data.colorList&& data.colorList.length > 0) {
	                radarColorLegend(data.colorList); //绘制雷达色标
	            } else {
	                if ($(".radar").hasClass("current")) {
	                    alert("无雷达色标数据！");
	                }
	            }
   				  
   			  
   			  $(".m-axis-list").html(str);
   			  $(".m-select-time ul").html(str1);
   			 // $(".m-axis-list").width(data.fileList.length*100);
   			  var len;
   			  if(data.fileList&&data.fileList.length>0){
   				len = data.fileList.length*100;
   			  }
			  if(len>960){
				  $(".m-axis-list").width(data.fileList.length*100);
			  }else{
				  $(".m-axis-list").width(960);
			  }

   			  $(".m-select-time ul li").eq($(".m-select-time ul li").length-1).click();
			 }
		  });
	 }
	 //绘制雷达
	 function bindCloudData(url) {	
		 	
		    urlPath=url;
		    var opcity = 0.75;
		    if(radarLayer){
		    	radarLayer.destroy();
		    }
		    radarLayer = gis.addTileLayer(urlPath);
		    radarLayer.setOpacity(opcity);
	  }
	 //绘制雷达色标
	    function radarColorLegend(data) {
	        var rColorHtml = "";
	        var mapWidth = $("#map").width();
	        var num = data.length;
	        $.each(data, function (i, object) {
	            rColorHtml += "<li data-id='" + object.item + "'><i style='background:rgb(" + object.color + ")'></i><span>" + object.item + "</span></li>";
	        });
	        $("#radarColorList").html(rColorHtml);
	       // $("#radarColorList").find("li").width(mapWidth / num + 'px');//色标布满屏幕
	        //clickRadarLegend();//默认点击最后一个
	    }
	    //点击雷达色标
	    function clickRadarLegend(index) {
	        var $li = $("#radarColorList").find("li");
	        var timeFile;//时间轴时间
	        var fileName='';//对应的时间轴文件
	        var filterValue;//色标筛选值
	        timeFile = $(".m-axis-list").find("li.z-on").attr("time");
	        timeFile = timeFile.substring(0, 4) + '-' + timeFile.substring(4, 6) + '-' + timeFile.substring(6, 8) + ' ' + timeFile.substring(8, 10) + ':' + timeFile.substring(10, 12);
	        //timeFile = '2017-10-26 13:50';
	        for (var i = 0, l = radarFile.length; i < l; i++) {
	        	radarFile[i].Time = radarFile[i].time.substring(0, 4) + '-' + radarFile[i].time.substring(5, 7) + '-' + radarFile[i].time.substring(8, 10) + ' ' + radarFile[i].time.substring(12, 14) + ':' + radarFile[i].time.substring(15, 17);
	            if (radarFile[i].Time == timeFile) {
	                fileName = radarFile[i].fileName;
	                break;
	            }
	        }
	        if (fileName=='' || fileName == 'undifend') {
	            warnPopup("无该时刻雷达数据！");
	            clearLayers();
	            //if (radarlayer && radarlayer == '') {
	            //    radarlayer.setVisibility(false);//雷达图层控制显隐
	            //}
	            $("#radarColorList").hide();//雷达图例控制显隐
	            $(".olGiLegend-title").hide();//雷达标题文字显隐
	            return;
	        }
	        $("#radarColorList").show();//雷达图例控制显隐
	        $(".olGiLegend-title").show();//雷达标题文字显隐
	        filterValue = $li.eq(index).find("span").text();
	        var url = urlPath.split("FilterValue")[0]+"FilterValue="+filterValue+"&Level=0";
	        bindCloudData(url);
	        //formRadarLayer(fileName, filterValue);
	    }

    /**
     * 风级下拉专用
     */
    // function timeAxisControlTemp(parm) {
    //     var path = $(".m-axis-list li").eq(index).attr("path");
    //     var title = $(".m-axis-list li").eq(index).attr("title");
    //     $(".maptitle").text(title);
    //     $(".maptitle").show();
    //     var data;
    //     var url;
    //
    //     //雷雨大风 大风落区模块
    //
    //     data = {
    //         xmlPath: path
    //     }
    //     url = '/sen/refinement/getWindRank';
    //
    //     getData(url, "get", data, "json", function (d) {
    //         if (d) {
    //             showWindRank(d);
    //         }
    //     })
    //     $(".m-axis-list li").eq(index).addClass("z-on").siblings().removeClass("z-on");
    //     var timeAxix = $(".m-time-axis-box");
    //     var timeW = timeAxix.width();
    //     var liW = $(".m-axis-list li").eq(0).width();
    //     var pageSize = Math.floor(timeW / liW);
    //     pageSize = pageSize / 2;
    //     var curPage = index - pageSize;
    //     if (curPage > 0) {
    //         timeAxix.animate({scrollLeft: liW * curPage}, 300);
    //     } else {
    //         timeAxix.animate({scrollLeft: 0}, 300);
    //     }
    // }
});