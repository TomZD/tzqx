define('typhoon2.js', function(require, exports, module) {
    var drawLayer;//绘图后产生的图形层
    var GIS = require("lib/gigis/gi-gis");
    var Typhoons = require("components/map-typhoon");
    var Layer = require("components/map-layer");
    var maplayer;
    var box = $(".m-map2");
    var typhoonsLayers=[];//台风图层数组
    var playTimer;//循环播放
    var overMapLayer={"gird":"","contour":"","img":""};//产品叠加图层
    //初始化gis
    var gis = GIS("map", "amap", {
        center : {
            lon : 121.57,
            lat : 29.87
        },
        zoom : 4,
        type : "traffic",
        wrapDateLine : true
    });
    exports.gisobj = gis;
    gis.addControl("zoom", 10, -10);
    gis.addControl("mousePosition", 75, -17, {
        prefix: "经度：",
        separator: " 纬度：",
        numDigits: 3});
    if(!maplayer){
        maplayer = Layer(gis);
    }
    //台风信息切换
    $(".m-history-typhoon>ul>li").click(function(){
        var liIndex = $(this).index();
        if(liIndex==0){
            $(".m-select-year").show();
            $(".typhoon_list table").hide().eq(0).show();
        }else{
            $(".m-select-year").hide();
            $(".typhoon_list table").hide().eq(1).show();
        }
        $(this).addClass("checked").siblings().removeClass("checked");
    })
    //点击确定查询历史台风
    $("#query").click(function(){
        getTyphoonData();
    })
    //绘制相似台风路径点击事件
    $("#geometryPs").click(function(){
        $(this).toggleClass("click");
        clearBoundary();
    })

    //预报模式显隐控制
    $(".m-forecasting-model>div").click(function(){
        $(this).parent().find("ul").toggle();
        $(this).find("i").toggle();
    });
    $(".next_play").click(function(){
        stopPlay();
        changeTime("next");
    });
    $(".prev_play").click(function(){
        stopPlay();
        changeTime("last");
    });
    $(".m-time-axis").on("click","li",function(){
        var time = $(this).text();
        $(".m-time-axis").find("span").text(time);
        $(".m-time-axis div").removeClass("click");
        $(this).addClass("checked").siblings().removeClass("checked");
    });

    $(".m-time-axis div span").click(function(){
        stopPlay();
        $(this).parent().toggleClass("click");
    });

    $(".auto_play").click(function(){
        if($(this).hasClass("pause")){
            stopPlay();
        }else{
            playTimer = setInterval(function(){
                changeTime("next");
            },5000);
            $(this).addClass("pause");
            $(".auto_play").attr("src","../static/images/suspend_icon.png")
        }
    });

    $("#export_typhoon_gif").click(function () {
        var bianhao=$(".menu-title span").attr("data-id");
        window.location.href="/hnqx/typhoon/exportTyphoonGif?bianhao="+bianhao;
    });
    //点击叠加图层菜单
    $(".m-maptools-layers .dropdown-menu").on("click","label",function(){
        var that = this;
        clearOverMapLayer();
        if($(this).attr("data-id")){
            $(".m-time-axis").show();
            getMapOverlyTime(that);
            var value = $(this).text();
            $(".m-maptools-layers>a").text(value).css("color","blue");
        }else{
            clearOverMapLayer();
            $(".m-time-axis").hide();
            $(".m-maptools-layers>a").text('天气图').css("color","#333");
        }
    });
    //点击时间添加叠加产品图层
    $(".m-time-axis").on("click","li",function(){
        var $this = $(this);
        var url = $this.parent().attr("data-url");
        var menuId = $this.parent().attr("menuId");
        var name = $this.parent().attr("name")
        var time = $(this).text();
        var index = $(this).index();
        url += "?callback=?&menuId=" + menuId +"&dateTime="+time+"&area=嘉兴市";
        if(index<2){
            url +="&area=嘉兴市";
        }
        // getMapOverlyData(url,name,callback)
        getMapOverlyData(url,name,overMapData);
    });
    //叠加图层
    function overMapData(name,data){
        clearOverMapLayer();
        if(name.indexOf("雷达")>-1||name.indexOf("云图")>-1){//雷达产品雷达
            overMapLayer.gird = gis.addTileLayer(data.Url,"Radar");
            overMapLayer.gird.setOpacity(0.8);
        }
        if(name.indexOf("实况")>-1){
            var xmlData = createXml(data.Xml)
            overMapLayer.contour = maplayer.add("grid","",xmlData,true,0.5);
        }
    }
    //清空叠加图层
    function clearOverMapLayer() {
        if(overMapLayer){
            if(overMapLayer.gird){
                overMapLayer.gird.clearGrid();
                overMapLayer.gird.destroy();
                overMapLayer.gird="";
            }
            if(overMapLayer.contour){
                overMapLayer.contour .destroy();
                overMapLayer.gird="";
            }
        }
    }
    //停止播放
    function stopPlay(){
        clearInterval(playTimer);
        $(".auto_play").removeClass("pause");
        $(".auto_play").attr("src","../static/images/play_icon.png")
    }
  //初始化经纬网格
    var graticule = new OpenLayers.Control.Graticule({
        numPoints: 1,
        labelled: true,
        lineSymbolizer: {
            strokeColor: "#333",
            strokeWidth: 1,
            strokeOpacity: 0.5
        },
        labelFormat: "d", //dms
    });
    gis.map.addControl(graticule);
    $("#jwwg").click(function(){
    	var str =  $("#jwwg").html();
    	if(str.indexOf('隐藏')!=-1){
    		str = str.replace('隐藏','显示');
    		graticule.deactivate();
    	}else{
    		str = str.replace('显示','隐藏');
    		graticule.activate();
    	}
    	$("#jwwg").html(str);
    });
    //加载最新台风
    Typhoons(gis, box, null, true);
    $('#map').append('<div class=mapdBZ><img src=/hnqx/static/images/dBZ.png /></div>');
    $('#map').append('<div class=mapdBZt></div>');
    $('.mapdBZ').css({
        position : 'absolute',
        zIndex : '2',
        right : '2%',
        bottom : '10%'
    });
    $('.mapdBZt').css({
        position : 'absolute',
        zIndex : '2',
        right : '8%',
        backgroundColor : '#ffffff',
        bottom : '2%',
        border : '4px solid #ffffff'
    });
    $('.mapdBZ').hide();
    $('.mapdBZt').hide();
    //天气图
//     var maplayer;
//     box.find(".m-maptools-layers .dropdown-menu").delegate('label', 'click', function() {
//         if (!$(this).hasClass('on')) {
//             $(this).addClass('on').siblings().removeClass('on');
//             var id = $(this).attr("data-id");
//             if (id == 'lonlat/href') {
//                 $('.mapdBZ').show();
//                 $(".m-time-axis").show();
//             } else {
//                 $('.mapdBZ').hide();
//                 $(".m-time-axis").hide();
//             }
//             if (id == ''){
//                 $('.mapdBZt').hide();
//                 $(".m-time-axis").hide();
//             }
//             else{
//                 $('.mapdBZt').show();
//                 $(".m-time-axis").show();
//             }
//             if (!maplayer) {
//                 maplayer = Layer(gis);
//             }
//             if (id) {
// //                markersManage.hideMarkerDrag();
// //                markersManage.hideMarkerCountry();
// //                markersManage.hidePopup();
// //                markersManage.hideImg();
//             }
//             maplayer.add(id);
//         }
//     });
    var startYear=1949;
    bindYear();
    function bindYear() {
        var html='';
        var myDate = new Date;
        var year = myDate.getFullYear();//获取当前年
        while (year>=startYear)
        {
            html+=' <option>'+year+'</option>';
            year--;
        }
        $("#selectYear").html(html);
    }
    var tools = $("#tools"); //绘图工具
    if (tools && gis) {
        gis.drawTools("tools", "./images/addr.png", 10000, "Radar",function (type, floatResult,pathPoint, maplayer) {
            drawLayer = maplayer;
            var l = parseFloat(floatResult);//绘制第一段线的长度
            var a1 = pathPoint[0];
            var a2 = pathPoint[1];
            var areaPoints=[];
            var p1={};
            p1.x=a1.lon;p1.y=a1.lat;
            var p2={};
            p2.x=a2.lon;p2.y=a2.lat;
            areaPoints.push(p1);
            areaPoints.push(p2);
            //检索穿过该区域的台风信息
            getSimilarData(areaPoints);
            $(".m-history-typhoon>ul>li").eq(1).click();
        })
    }
    var boundaryAreaData=[];
    function drawRectangle(data){
        clearBoundary();
        var xmldata ='<?xml version="1.0" encoding="utf-8"?><Area Id="象山县" area="0.117227353242924" GovCenter="0,0" Center="121.842,29.363" Coords="';
        for(var i=0;i<4;i++){
            xmldata +=data[i].x+","+data[i].y+",";
            if(i==3){
              xmldata +=data[0].x+","+data[0].y;
            }
        }
        xmldata +='" />';
        var xmlObj = createXml(xmldata);
        //加载边界以及区域
        var boundary = gis.drawBoundary("余", xmlObj, {
            fillOpacity: 0.6,//透明度
            fillColor: '#fff',//填充色
            strokeColor: "red",//边界线条颜色灰色
            strokeWidth: "3"
        });
        boundaryAreaData.push(boundary);
    }
    //检索穿过该区域的点
    function getSimilarData(data) {
         var areaPoint = JSON.stringify( data );
        $.ajax({
            url:'/hnqx/typhoon/getSelectArea',
            type:"post",
            data:{"pointstring":areaPoint},
            traditional :true,  //注意这个参数是必须的
            cache:false,
            success:function(rs){
                drawRectangle(rs)
                getGoAreaTyphoon(rs);
            },
            error:function(e){
                alert("获取区域顶点接口：getSelectArea 错误");
            }
        })
    }
    //转化xml文件  字符串转xml
    function createXml(str) {
        if (document.all) {
            var xmlDom = new ActiveXObject("Microsoft.XMLDOM");
            xmlDom.loadXML(str);
            return xmlDom;
        }
        else
            return new DOMParser().parseFromString(str, "text/xml");
    }
    //清空图层信息
    function clearBoundary() {
        for (var i = 0; i < boundaryAreaData.length; i++) {
            var boundary = boundaryAreaData[i];
            if (boundary) {
                boundary.destroy();
            }
        }
        boundaryAreaData = [];
    }
    //获取穿过该区域的台风
    function getGoAreaTyphoon(data){
        var areaPoint = JSON.stringify( data );
        $(".typhoon_list table#typhoon_similar_route tbody").empty();
        $.ajax({
            url:'/hnqx/typhoon/getTyphoonCrossArea',
            type:"post",
            data:{"pointstring":areaPoint},
            traditional :true,  //注意这个参数是必须的
            cache:false,
            success:function(data){
                if(data){
                    var tr = "";
                    for(var i=0,l=data.length;i<l;i++){
                        var nameCn = data[i].nameCn?data[i].nameCn:"";
                        var nameEn = data[i].nameEn?data[i].nameEn:"";
                        tr += '<tr><td><input type="checkbox"></td><td>'+data[i].no+'</td><td>'+nameCn+'</td><td>'+nameEn+'</td></tr>'
                    }
                    $(".typhoon_list table#typhoon_similar_route tbody").html(tr);
                }
            },
            error:function(e){
                alert("获取穿过该区域台风信息接口：getTyphoonCrossArea 错误");
            }
        })
    }
    getTyphoonData();
    //获取历史台风列表
    function getTyphoonData(){
        var querryTime = $(".m-select-year").find("select").val();
        var querryKeyWord = $(".m-select-year").find("input").val();
        $(".typhoon_list table#typhoon_history tbody").empty();
        $.ajax({
            url:'/hnqx/typhoon/getSearchTyphoons',
            type:"post",
            data:{"year":querryTime,"keyWords":querryKeyWord},
            cache:false,
            success:function (data) {
               if(data){
                   var tr = "";
                   for(var i=0,l=data.length;i<l;i++){
                       var nameCn = data[i].nameCn?data[i].nameCn:"";
                       var nameEn = data[i].nameEn?data[i].nameEn:"";
                       tr += '<tr><td><input type="checkbox"></td><td>'+data[i].no+'</td><td>'+nameCn+'</td><td>'+nameEn+'</td></tr>'
                   }
                   $(".typhoon_list table#typhoon_history tbody").html(tr);
               }
            },
            error:function (e) {
                alert("获取台风历史记录接口 getSearchTyphoons 报错！")
            }
        })
    }
    //时间轴时间修改
    function changeTime(obj){
        var index = $(".m-time-axis li.checked").index();
        var num = $(".m-time-axis li").length;
        switch (obj) {
            case "last":{//上一个
                index = index -1;
                if(index<num-1){
                    index = num-1;
                }
            }
            break;
            case "next":{//下一个
                index = index +1;
                if(index>num-1){
                    index = 0;
                }
            }
            break;
            default://直接点击时间
        }
        $(".m-time-axis li").eq(index).click()
    }
    //获取地图上叠加的菜单
    getMapOverlyingData();
    function getMapOverlyingData(){
        $(".m-maptools-layers .dropdown-menu").empty();
        $.ajax({
            url:'/hnqx/typhoon/getTyphoonMenu',
            data:{},
            type:"post",
            cache:false,
            success:function(data){
                if(data){
                    var div = "<i></i>";
                    for(var i=0,l=data.length;i<l;i++){
                        div +='<label data-id="lonlat/href" data-num="'+data[i].id+'" data-url="'+data[i].url+'" data-urlList="'+data[i].urlList+'" >'+data[i].name+'</label>'
                    }
                    div +='<label data-id="">清空</label>';
                    $(".m-maptools-layers .dropdown-menu").html(div);
                }
            },
            error:function(e){
                alert("获取地图叠加菜单接口：getTyphoonMenu 错误")
            }
        })
    }
    //根据叠加地图样式获取对应的时间
    function getMapOverlyTime(obj){
        var name = $(obj).text();
        var dataUrl = $(obj).attr("data-url");
        var timeUrl = $(obj).attr("data-urllist");
        // timeUrl = "http://192.168.2.238:8031" + timeUrl.split(":8088")[1];
        var id = $(obj).attr("data-num");
        $(".m-time-axis ul").empty();
        $.ajax({
            url:timeUrl+"?callback=?&menuId="+id,
            type:"get",
            cache:false,
            dataType:"jsonp",
            success:function(data){
               if(data&&data.length >0){
                   var li = "";
                   if(name.indexOf("实况")>-1){
                       for(var i=0,l=data.length;i<l&&i<20;i++){
                           li +="<li>"+data[i].Item2+"</li>";
                       }
                   }else{
                       for(var i=0,l=data.length;i<l&&i<20;i++){
                           li +="<li>"+data[i]+"</li>";
                       }
                   }
                   $(".m-time-axis ul").attr({"data-url":dataUrl,"name":name,"menuId":id}).html(li);
                   $(".m-time-axis ul li").eq(0).click();
               }else{
                   layer.alert(name +"数据为空!");
                   $(".m-time-axis span").html("");
               }
            },
            error:function(data){
                alert("获取叠加菜单对应的时间接口错误");
            }
        })

    }
    // 获取地图叠加层
    function getMapOverlyData(url,name,callback){
        var dataUrl = url;
        // dataUrl = "http://192.168.2.238:8031" + url.split(":8088")[1];
        $.ajax({
            url:dataUrl,
            type:"get",
            cache:false,
            dataType:"jsonp",
            success:function(data){
                 if(data&&data.Url||data.Xml){
                     if(typeof callback === "function"){
                         callback(name,data);
                     }
                 }else{
                     var content = url.split("dateTime=")[1];
                     content = name + content+"时刻暂无数据！"
                     layer.alert(content);
                 }
            },
            error:function(e){
                alert("获取"+ name +"数据接口失败！");
            }
        })
    }
});
