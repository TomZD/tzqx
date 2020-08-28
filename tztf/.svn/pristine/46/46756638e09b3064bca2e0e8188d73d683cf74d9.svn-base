/**
 * @require "components/tabs/tabs.js"
 * @require "libs/gigis.js"
 */
define(function (require) {
   
    var template = require("lib/template");
    var GIS = require("lib/gigis/gi-gis");
    var markers = [];
    var arr = [];
    //gis实例
    
    var gis = GIS("map", "grass", {
        center: {
            lon: 120.907,
            lat: 29.991
        },
        zoom: 10,
        type: "traffic",
        wrapDateLine: true,
        documentDrag: true
    });
    
    mapstol=gis;
    var boundrys = [];//边界线集合
//加载边界
var drawFYBoundary = function () {

    $('#map').css('display', '');
    $('#cloudImg').html('');

    var l = boundrys.length;
    if (l > 1 || l == 0) {
        for (var i = 0 ; i < l; i++) {
            boundrys[i].destroy();
        }
        boundrys = [];

        $.ajax({
            url: "/static/js/areaXML/shangyu/area.xml",
            type: "get",
            dataType: "xml",
            success: function (xml) {
                b = gis.drawBoundary("上虞", xml, {
                    fillOpacity: 0,
                    strokeColor: "#f00"
                });
                boundrys.push(b);
            }
        });
    }
}
$('#btnPolygen').click(function (e){
	clearAll();
});
drawFYBoundary();
gis.drawTools("btnPolygen", "./images/addr.png", 10000, this);
//
$("#share").tabs({
    tabBtns: ".types span",
    tabUns: ".un",
    event: "click"
});
//定位坐标

var indexI=0;
var pointValues=new Array();

/*
if(indexI>0){
	console.log(indexI);
	   var markers= gis.addMarker(pointValues, gis.addMarkers());
	   for(var i in markers){
		   markers[i].addListener("click",showdiv);
	   }
}*/

//    gis.addControl("baseLayerSwitcher", -10, 10);
//    gis.addControl("zoom", -12, 70);
//    gis.addControl("mousePosition", 0, -1, {
//        prefix: "经度：",
//        separator: " 纬度：",
//        numDigits: 3
//    });
//    //加载作图工具
//    var tools = $("#tool");
//    if (tools && gis) {
//        gis.drawTools("tool", "./images/addr.png", 10000);
//    }
    //-------------排名点击事件-------
    var countObj = null;
    //气象站点 定位解析
    var bingLocationPoint = function () {
        var remberLast = {
            Lon: null,
            Lat: null
        };
        $('#rankInfo table tbody ').delegate('tr', 'click', function () {
            var lonlat = $(this).attr('data-lonlat').split(',');
            if (remberLast.Lon == lonlat[0] && remberLast.Lat == lonlat[1]) {
                setPoints.clear("标记点");
                remberLast = {
                    Lon: null,
                    Lat: null
                };
                return;
            } else {
                remberLast = {
                    Lon: lonlat[0],
                    Lat: lonlat[1]
                };
            }
            var other = $(this).attr('data-other').split(',');
            gis.setCenter(lonlat[0], lonlat[1], 12);
            var showMarker = [{
                Lon: lonlat[0],
                Lat: lonlat[1],
                Value: other[0].replace(/ /g, ""),
                StationNum: other[1].replace(/ /g, ""),
                Name: other[2].replace(/ /g, "")
            }];
            setPoints.clear("标记点");
            setPoints.add(showMarker, "标记点", "标记", {
                url: "/Plugins/ComprehensiveDisplay/static/images/icon/test_red.png",
                width: 12,
                height: 14
            }, false);
        });
        PageingSelf_control();
    }
    //分页设计
    var PageingSelf_control = function () {
        countObj = null;//清空上次排名信息
        var rankinfoObj = $('#rankInfo');
        countObj = rankinfoObj.find('.table table tbody tr');//重新获取排名信息
        if (countObj.length <= 10) {
            $('#lastPage').css('color', "#ccc");
            $('#nextPage').css('color', '#ccc');
            return;//排名总条数不超过10则不需要做分页
        }
        $('#nextPage').click(function () {
            nextPage();
        });
        $('#lastPage').click(function () {
            lastPage();
        });
        lastPage(-1);
    }
    //==============翻页方法
    //索引集合
    var pageIndex = {
        rankinfo: 0//当前页索引
    };
    var lastPage = function (a) {
        if (a == -1) {//初始第一页
            for (var i = 0, l = countObj.length; i < l; i++) {
                $($(countObj)[i]).attr('style', 'display:none')
            }
            if (countObj.length >= 10) {
                for (var j = 0; j < 10; j++) {
                    $($(countObj)[j]).attr('style', 'display:')
                }
            } else {
                for (var j = 0; j < countObj.length; j++) {
                    $($(countObj)[j]).attr('style', 'display:')
                }
            }
            $('#lastPage').css('color', "#ccc");
            return;
        }
        //判断当前是否可以翻页 debugger;
        if (countObj == null || countObj.length == 0) {
            alert("没有可查看数据");
        }
        var dataNum = countObj.length; //所有数据条数
        if (pageIndex.rankinfo == 0) {
            return;
        }
        var startIndex = pageIndex.rankinfo - 1; //当前索引默认0，翻页-1，翻页后页面索引
        if (startIndex * 10 < dataNum) {//翻页后存在数据条数 比较 实际数据条数 //默认每页10条数据
            pageIndex.rankinfo--;//翻页后索引修改为当前索引
            var firstIndex = startIndex * 10;
            //确认可以翻页后，进行数据修改
            for (var i = 0, l = countObj.length; i < l; i++) {
                $($(countObj)[i]).attr('style', 'display:none')
            }
            for (var j = 0; j < 10; j++) {
                $($(countObj)[firstIndex + j]).attr('style', 'display:')
            }
            $('#lastPage').css('color', "");
            $('#nextPage').css('color', '');
            if (startIndex == 0) {
                $('#lastPage').css('color', "#ccc");
            }
        }
    }
    var nextPage = function (b) {
        //判断当前是否可以翻页  debugger;
        if (countObj == null || countObj.length == 0) {
            alert("没有可查看数据");
        }
        var dataNum = countObj.length; //所有数据条数
        var startIndex = pageIndex.rankinfo + 1; //当前索引默认0，翻页+1，翻页后页面索引
        if (startIndex * 10 < dataNum) {//翻页后存在数据条数 比较 实际数据条数 //默认每页10条数据
            pageIndex.rankinfo++;//翻页后索引修改为当前索引
            var firstIndex = startIndex * 10;
            //确认可以翻页后，进行数据修改
            for (var i = 0, l = countObj.length; i < l; i++) {
                $($(countObj)[i]).attr('style', 'display:none')
            }
            for (var j = 0; j < 10; j++) {
                $($(countObj)[firstIndex + j]).attr('style', 'display:')
            }
            $('#nextPage').css('color', '');
            $('#lastPage').css('color', "");
            if ((startIndex + 1) * 10 > dataNum) {
                $('#nextPage').css('color', '#ccc');
            }
        }

    }
    //===翻页方法以上

    var isShow = true;//判断是否显示站点值
    var setLayers = require("Layers")(gis); //设置图层
    //var setPopup = require("Popup")(gis); //设置弹层
    var setPoints = require("Points")(gis, function () {//onMouseover 事件
        //setPopup.show(this._lon, this._lat, template("popupTpl_" + this.content.popup_name, this.content));
    }, function () { // click事件
    	console.log(this.content);
    	var c = this.content;
    	var obj = c.content;
    	
    	if(c.type==="alarm"){
    		console.log("show alarm");
    		$('.sqsj_main').css('display','block');
    		 $("#area").empty();
             $("#describe").empty();
             $("#pname").empty();
            $("#area").append(obj.address);
            $("#describe").append(obj.description);
            $("#pname").append(obj.title);
            var pic = obj.pics;
            for(var i=0;i<pic.length;i++){
            	var te = pic[i];
            	var src="/"+te.fileName;
            	$("#images").attr("src",src);
            }
    	} else if(c.type==="event"){
    		
    		$('.sqsj_main').css('display','block');
    		 $("#area").empty();
             $("#describe").empty();
             $("#pname").empty();
            $("#area").append(obj.address);
            $("#describe").append(obj.description);
            $("#pname").append(obj.title);
            var pic = obj.pics;
            for(var i=0;i<pic.length;i++){
            	var te = pic[i];
            	var src="/"+te.fileName;
            	$("#images").attr("src",src);
            }
    	} 
        
    }); //设置标注 参数：经度，纬度 模板信息 click事件
    //获取chart数据并处理绑定
    var setChart = function (id, stationNum, datetime) {
        $.ajax({
            type: 'get',
            url: '/WeatherLive/GetHighChartData',
            data: { 'menuId': id, 'stationNum': stationNum, 'dateTime': datetime },
            //async: false,
            success: function (json) {
                if (json && json.Data) {
                    var title = json.Title;
                    var data = json.Data;
                    dealChart(title, data);
                }
            },
            error: function (e) {
                $('#charts').html('<p style="height:30px;color:#f00">该要素无时序图</p>');
                console.log("没有获取到chart数据");
            }
        });
    }
    var dealChart = function (title, data) {
        //时间格式转换 Date.parse(time.replace(/-/g, "/"));
        var series = new Array();//处理series数据
        var serie = {};
        if (title.indexOf("降水") != -1) {
            serie = { name: "", type: "column", data: null };
        } else {
            serie = { name: "", type: "spline", data: null };
        }
        var _data = new Array();
        for (var i = 0, l = data.length; i < l; i++) {
            var item = data[i];
            var subdata = {};
            subdata.x = Date.parse(item.X.replace(/-/g, "/"));
            subdata.y = item.Y - 0;
            _data.push(subdata);
        }
        serie.data = _data;
        serie.name = title;
        series[0] = serie;
        var unit = "";
        var xchart = new Highcharts.Chart({
            chart: {
                renderTo: 'charts',//chart的id
                width: 480,
                heigh: 220,
                zoomType: 'x'
            },
            credits: {
                enabled: false
            },
            title: {
                style: {
                    "fontSize": "15px",
                    color: '#000000',
                    fontFamily: "微软雅黑"
                },
                text: title,
            },
            xAxis: {
                type: 'datetime',
                labels: {
                    //align: 'right',
                    style: {
                        //fontSize: '12px',
                        //fontFamily: 'Verdana, sans-serif'
                    },
                    formatter: function () {
                        var x = this.value;
                        var date = new Date(x);
                        var d = date.getDate(),
                            h = date.getHours();
                        d = d > 9 ? d : 0 + "" + d;
                        h = h > 9 ? h : 0 + "" + h;
                        if (h == 0) {
                            return d + "日" + h + "时";

                        } else {
                            return h + "时";
                        }
                    }
                },
            },
            yAxis: {
                title: {
                    text: ''//单位(' + unit + ')
                }
            },
            plotOptions: {
                series: {
                    //lineWidth: 2,//设置曲线粗细
                    marker: {
                        radius: 3,//设置曲线点半径，默认是4
                        symbol: 'triangle',//曲线点类型："circle", "square", "diamond", "triangle","triangle-down"
                        states: {
                            hover: {
                                // lineWidth: 2//改变曲线点内部填色多少
                            }
                        }
                    }
                },
                column: {
                    pointWidth: 10,
                    enableMouseTracking: true,//是否显示浮动标签,默认true
                    allowPointSelect: true//显示允许提示选中点 默认false

                }//,
                //line: {
                //    dataLabels: {//数据标签
                //        enabled: false,
                //        color: '#ff0000'
                //    },
                //    color: '#ff0000',
                //    enableMouseTracking: true,//是否显示浮动标签,默认true
                //    allowPointSelect: true//显示允许提示选中点 默认false
                //}
            },
            tooltip: {
                crosshairs: true,
                shared: true,
                //hideDelay: 300000,// 鼠标离开后，持续显示时间
                formatter: function () {
                    var x = parseInt(this.x);
                    var date = new Date(x);
                    var M = date.getMonth() + 1,
                        d = date.getDate(),
                        h = date.getHours();

                    M = M > 9 ? M : 0 + "" + M;
                    d = d > 9 ? d : 0 + "" + d;
                    h = h > 9 ? h : 0 + "" + h;

                    var html = "";
                    html += d + "日" + h + "时：";
                    //html += "<br/>";
                    html += "<b>" + this.y + "</b>";

                    return html;
                }
            },
            legend: {
                enabled: false,
                floating: false,
                borderWidth: 1,
                borderRadius: 5.0,
                layout: 'horizontal',//vertical
                varticalAlign: 'bottom',
                align: 'center'
            },
            series: series
        });
    }
    var makeTable = function (content) {
        $('#sp_title').html(content.popup_name);
        var titlepart = content.Name || content.Element;
        $('#sp_add').html(titlepart);
        $('#tabDiv').html("");//表格置空
        var html;
        if (content) {
            html = '<div class="br5"><table>';
            if (content.Name) {
                html += '<tr><th class="th01">名称：</th>';
                html += '<td>' + content.Name + '</td></tr>';
            }
            if (content.Adress) {
                html += '<tr><th class="th01">地址：</th>';
                html += '<td class="td01">' + content.Adress + '</td></tr>';
            }

            if (content.Type) {
                html += '<tr><th class="th01">类型：</th>';
                html += '<td>' + content.Type + '</td></tr>';
            }
            if (content.Capacity) {
                html += '<tr><th class="th02">联系电话：</th>';
                html += '<td class="td05">' + content.Phone + '</td>';
                html += '<th class="th01">容纳人数：</th>';
                html += '<td class="td04">' + content.Capacity + '</td></tr>';
            } else if (content.Phone) {
                html += '<tr><th class="th02">联系人：</th>';
                html += '<td class="td03">' + content.ConnectPerson + '</td>';
                html += '<th class="th02">联系电话：</th>';
                html += '<td class="td03">' + content.Phone + '</td></tr>';
            }
            if (content.Unit) {
                html += '<tr><th class="th01">单位：</th>';
                html += '<td>' + content.Unit + '</td></tr>';
            }

            if (content.Address) {
                html += '<tr><th class="th01">地址：</th>';
                html += '<td>' + content.Address + '</td></tr>';
            }
            if (content.Area) {
                html += '<tr><th class="th01">面积：</th>';
                html += '<td>' + content.Area + '</td></tr>';
            }
            if (content.TimeLast) {
                html += '<tr><th class="th02">时效：</th>';
                html += '<td class="td03">' + content.TimeLast + '</td>';
                html += '<th class="th02">责任单位：</th>';
                html += '<td class="td03">' + content.ResponsibleOrganization + '</td></tr>';
            }
            if (content.Value) {
                html += '<tr><th class="th01">数值：</th>';
                html += '<td class="td03">' + content.Value + '</td>';
            }
            if (content.ImpoundingLevel) {
                html += '<th class="th01">蓄水位：</th>';
                html += '<td class="td03">' + content.ImpoundingLevel + '</td></tr>';
            }
            if (content.StationNum) {
                html += '<tr><th class="th01">站号：</th>';
                html += '<td class="td03">' + content.StationNum + '</td>';
            }
            if (content.DeadWaterLevel) {
                html += '<th class="th01">死水位：</th>';
                html += '<td class="td03">' + content.DeadWaterLevel + '</td></tr>';
            }
            if (content.WarningValue) {
                html += '<tr><th class="th02">警戒水位：</th>';
                html += '<td class="td03">' + content.WarningValue + '</td>';
                html += '<th class="th02">排水面积：</th>';
                html += '<td class="td03">' + content.DraingeArea + '</td></tr>';
            }

            if (content.BuilddingTime) {
                html += '<tr><th class="th02">建造时间（年）：</th>';
                html += '<td class="td03">' + content.BuilddingTime + '</td>';
                html += '<th class="th02">最大泄洪量：</th>';
                html += '<td class="td03">' + content.MaxFloodFlow + '</td></tr>';
            }
            if (content.Town) {
                html += '<tr><th class="th01">所属地区：</th>';
                html += '<td>' + content.Town + '</td>';
            } else if (content.Town) {
                html += '<tr><th class="th01">所属地区：</th>';
                html += '<td class="td02">' + content.Town + '</td>';
                html += '<th class="th01">水域面积：</th>';
                html += '<td class="td04">' + content.NormalSurfaceArea + '</td></tr>';
            }

            if (content.DamDem) {
                html += '<tr><th class="th02">坝顶高程：</th>';
                html += '<td class="td03">' + content.DamDem + '</td>';
                html += '<th class="th02">水库类型：</th>';
                html += '<td class="td03">' + content.ReseroirType + '</td></tr>';
            }
            if (content.DamHeight) {
                html += '<tr><th class="th02">坝高：</th>';
                html += '<td class="td03">' + content.DamHeight + '</td>';
                html += '<th class="th02">总蓄水量：</th>';
                html += '<td class="td03">' + content.TotalCapacity + '</td></tr>';
            }


            //if (content.MonitorPerson) {
            //    html += '<tr><th>监测责任人：</th>';
            //    html += '<td>' + content.MonitorPerson + '</td></tr>';
            //}
            if (content.GeneralPerson) {
                html += '<tr><th class="th02">总责任人：</th>';
                html += '<td class="td03">' + content.GeneralPerson + '</td>';
            }
            if (content.GeneralPerson) {
                html += '<th class="th02">分管责任人：</th>';
                html += '<td class="td03">' + content.ChargePerson + '</td></tr>';
            }
            //if (content.PhoneNum) {
            //    html += '<tr><th>联系电话：</th>';
            //    html += '<td>' + content.PhoneNum + '</td></tr>';
            //}
            html += "</table></div>";
            if (content.Supplies) {
                html += "<div class='ma-st'><img src='./plugins/ComprehensiveDisplay/static/images/ma-st_img.png' />物资统计</div>";
                //html += '<tr><img /><th style="height:48px">物资统计</th></tr>';
                html += "<div class='br5'><table>";
                for (var i = 0, l = content.Supplies.length; i < l; i += 2) {
                    var item = content.Supplies[i];
                    if (html.indexOf(item.Name) == -1) {
                        html += '<tr><th class="th02">' + item.Name + '：</th>';
                        html += '<td class="td03">' + item.Number + item.Unit + '</td>';

                        item = content.Supplies[i + 1];
                        if (item == undefined) {
                            html += '<th class="th02">&nbsp;</th>';
                            html += '<td class="td03"></td></tr>';
                        } else {
                            html += '<th class="th02">' + item.Name + '：</th>';
                            html += '<td class="td03">' + item.Number + item.Unit + '</td></tr>';
                        }
                    }
                }
                html += "</table></div>";
            }

            if (content.Level) {
                html += '<tr><th>灾害等级：</th>';
                html += '<td>' + content.Level + '</td></tr>';
            }
            if (content.Magnitude) {
                html += '<tr><th>量级：</th>';
                html += '<td>' + content.Magnitude + '</td></tr>';
            }

            html += '</table>';
        }
        $('#tabDiv').html(html);


    }

    var legend; //色标实例
    var title; //标题实例
    var tileCollection = [];//瓦片图集合
    var grid = [];//格点数据
 
    //标题
    var mapTitle = $(template("mapTitleTpl")).appendTo($("#map").children());
    var elSlidebar = $(".slidebar");

    //清除图层数据
    var clearAll = function () {
        drawFYBoundary();//重绘上虞边界线
        setPoints.clear(); //站点集合
        setLayers.clear();//等值线图层
        mapTitle.hide();//地图中标题
        if (grid.length != 0) {
            grid.destroy();
        }
        $("#rankInfo").html("");//清除排名
        if (tileCollection && tileCollection.length > 0) {
            tileCollection[0].destroy();
            tileCollection = [];//瓦片图集合
        }

        if ($('.g-right').hasClass('right-300')) {
            $('.button-r').click();//显示右侧统计信息菜单
        }

    }
    //瓦片图 雷达
    elSlidebar.delegate('.checklabel[data-name="tile"]', 'click', function () {
        $('.top').css('display', 'none');//隐藏右侧任意时段查询模块
        clearAll();
        if (!$('.g-right').hasClass('right-300')) {
            $('.button-r').click();//隐藏右侧统计信息菜单
        }
        var value = $(this).attr("data-value").replace(/ /g, "");
        var menuID = $(this).attr("data-id");
        var datetime = $('#dateControl').val();
        $.ajax({
            url: '/WeatherLive/GetTileFileName',
            data: { 'menuID': menuID, 'dateTime': datetime },
            type: 'post',
            success: function (data) {
                if (data.Data != null) {
                    var urlPath = "";
                    switch (value) {
                        case "雷达拼图":
                            urlPath = "http://218.108.139.125:9090/GetTiledImage.ashx?Type=Radar&File=" + data.Data.Item1 + "&x=${x}&y=${y}&z=${z}&HaveStromTrack=true";
                            break;
                        case "红外云图":
                            urlPath = "http://218.108.139.125:9090/GetTiledImage.ashx?Type=FY2_IR1&File=" + data.Data.Item1 + "&x=${x}&y=${y}&z=${z}";
                            break;
                        case "水汽":
                            urlPath = "http://218.108.139.125:9090/GetTiledImage.ashx?Type=FY2_IR3&File=" + data.Data.Item1 + "&x=${x}&y=${y}&z=${z}";
                            break;
                        case "可见光":
                            urlPath = "http://218.108.139.125:9090/GetTiledImage.ashx?Type=FY2_VIS&File=" + data.Data.Item1 + "&x=${x}&y=${y}&z=${z}";
                            break;
                    }
                    mapTitle.show().html(data.Data.Item2);
                    var t = gis.addTileLayer(urlPath, value);
                    var opcity = 0.75;
                    t.setOpacity(opcity);
                    tileCollection.push(t);
                }

            },
            error: function (e) {
                console.log(e);
            }
        });


    });
    //格点显示与隐藏
    var gSymbol = 0;
    $("#aGrid").click(function () {
        if (grid.length != 0) {
            if (gSymbol) {
                grid.setVisibility(true);
                gSymbol = 0;
            } else {
                grid.setVisibility(false);
                gSymbol = 1;
            }
        } else {
            console.log("没有格点数据");
        }
    });
    //气象站点显示隐藏 showOrHide
    var sSymbol = 0;
    $("#aStation").click(function () {
        if (sSymbol) {
            setPoints.showOrHide(true);
            sSymbol = 0;
        } else {
            setPoints.showOrHide(false);
            sSymbol = 1;
        }
    });
    //等值线显示与隐藏showOrHide
    var lSymbol = 0;
    $("#aLayer").click(function () {
        if (lSymbol) {
            setLayers.showOrHide(true);
            lSymbol = 0;
        } else {
            setLayers.showOrHide(false);
            lSymbol = 1;
        }
    });
    //加载第三级菜单数据
    elSlidebar.delegate('.checklabels[data-type="radio"]', 'click', function () {
        $('.top').css('display', 'none');//隐藏右侧任意时段查询模块
        var value = $(this).attr("data-value");
        var ck = elSlidebar.find('.checklabels[data-name="micaps4"].checked');
        clearAll();
        var isChecked = $(this).hasClass('checked');
        if (isChecked) {
            return;
        }
        gSymbol = 1;//格点默认显示
        sSymbol = 0;//气象站点显示
        lSymbol = 0;//气象等值线显示
        var menuID = $(this).attr("data-id");
        var datetime = $('#dateControl').val();
        $.ajax({
            url: '/WeatherLive/GetMenuData',
            data: { 'menuID': menuID, 'dateTime': datetime },
            type: 'post',
            //async: false,
            success: function (data) {
                //debugger
                if (data.Data && data.Data != null && data.Data.Json && data.Data.Json != null) {
                    var condition = data.Data.Condition;
                    var meteThresholds = data.Data.MeteThresholds;

                    var jsonData = strToJson(data.Data.Json).Data;
                    var validData = [];//过滤掉Value为0的数据
                    for (var i = 0, l = jsonData.length; i < l; i++) {

                        if (value.indexOf("温") != -1) {
                            validData.push(jsonData[i]);
                        } else if (value.indexOf("温") == -1 && jsonData[i].Value > 0) {
                            validData.push(jsonData[i]);//过滤降水值为0的站点
                        }
                    }
                    jsonData = [];
                    jsonData = validData;
                    setPoints.add(jsonData, "micaps4Station", value, {
                        url: "/Plugins/RealMonitoring/static/images/icon/qxzd/{qxzd}",
                        condition: condition,
                        meteThresholds: meteThresholds,
                        width: 22,
                        height: 24
                    }, isShow);

                    jsonData = jsonData.sort(function (a, b) {
                        return b.Value - a.Value;
                    });
                    $("#rankInfo").html(template("table1", {
                        data: jsonData,
                        title: value
                    }));
                    bingLocationPoint();
                }
                if (data.Data && data.Data != null && data.Data.Micaps4 && data.Data.Micaps4 != null) {
                    grid = gis.drawGrid("grid_A", data.Data.Micaps4, null, [-9999]);
                    grid.setVisibility(false);
                }
                //------------------Micaps4Contour-----
                if (data.Data != null && data.Data.Xml && data.Data.Xml != null) {
                    var xmlData = createXml(data.Data.Xml);
                    setLayers.add(xmlData);

                    mapTitle.show().html(xmlData.getElementsByTagName("Micaps4Contour")[0].getAttribute("Title"));
                }
            },
            error: function (ex) {
                console.log("没有获取到数据");
            }
        });
    });

    //字符串转json
    function strToJson(str) {
        var json = (new Function("return " + str))();
        return json;
    }
    //字符串转xml
    function createXml(str) {
        if (document.all) {
            var xmlDom = new ActiveXObject("Microsoft.XMLDOM");
            xmlDom.loadXML(str);
            return xmlDom;
        }
        else
            return new DOMParser().parseFromString(str, "text/xml");
    }
    //三维云图 img
    elSlidebar.delegate('.checklabel[data-name="image"]', 'click', function () {
        $('.top').css('display', 'none');//隐藏右侧任意时段查询模块
        var menuID = $(this).attr("data-id");
        var datetime = $('#dateControl').val();
        $.ajax({
            url: '/WeatherLive/GetCloudImage',
            data: { 'menuId': menuID, "dateTime": datetime },
            type: 'post',
            success: function (data) {
                var width = $('#map').css("width"),
                    height = $('#map').css("height");
                $('#map').css('display', 'none');
                $('#cloudImg').html('<img src="data:image/jpg;base64,' + data.Data + '" style="width:' + width + ';height:' + height + '" />');
            },
            error: function (e) {
                console.log(e);
            }
        })
    });
    //自动站 任意时段查询
    elSlidebar.delegate('.checklabel[data-name="queryAutoStation"]', 'click', function () {
        clearAll();
        showQueryDiv('queryAutoStation');
    });
    //自动站 面雨量时段查询
    elSlidebar.delegate('.checklabel[data-name="arealRainfallQuery"]', 'click', function () {
        clearAll();
        showQueryDiv('arealRainfallQuery');
    });
    //闪电定位 任意时段查询
    elSlidebar.delegate('.checklabel[data-name="queryLightningLocation"]', 'click', function () {
        clearAll();
        showQueryDiv('queryLightningLocation');
    });
    //显示查询菜单
    var showQueryDiv = function (name) {
        switch (name) {
            case "queryAutoStation":
                $('#div_queryAutoStation').css('display', 'block').siblings().css('display', 'none');
                break;
            case "arealRainfallQuery":
                $('#div_arealRainfallQuery').css('display', 'block').siblings().css('display', 'none');
                break;
            case "queryLightningLocation":
                //获取并绑定sel_area数据 
                setSelect();
                $('#div_queryLightningLocation').css('display', 'block').siblings().css('display', 'none');
                break;
        }
        $('.top').css('display', '');
    }
    //闪电定位 任意时段查询 行政区划数据获取
    var setSelect = function () {
        $.ajax({
            url: '/WeatherLive/GetAllTowns',
            data: {},
            type: 'get',
            async: false,
            success: function (data) {
                $('#sel_area').html("");
                if (data.Data != null) {
                    var html = "";
                    for (var i = 0, l = data.Data.length; i < l; i++) {
                        var item = data.Data[i];
                        html += '<option value="' + item.Fname + '">' + item.Fname + '</option>';
                    }
                    $('#sel_area').html(html);
                }
            },
            error: function (e) {
                console.log("行政区划数据获取失败!");
            }
        });
    }

    //注册 时段查询按钮事件
    $(function () {

        $('#queryAutoStation').click(function () {
            var sel_element_val = $('#sel_element').val();
            var startDate = $('#startDate1').val();
            var endDate = $('#endDate1').val();
            if (startDate && endDate) {
                console.log('--------自动站 任意时段查询中------');
                $.ajax({
                    url: '/WeatherLive/GetQueryValues',//
                    data: { 'startDate': startDate, 'endDate': endDate, 'elementType': sel_element_val },
                    type: 'get',
                    success: function (data) {
                        console.log("---请求成功--自动站 任意时段查询");
                        clearAll();
                        setLayersByData(data, "任意时段查询");
                    },
                    error: function (e) {
                        console.log(e);
                    }
                });
            } else {
                alert('请选择查询时间!');
            }

        });
        $('#arealRainfallQuery').click(function () {
            var startDate = $('#startDate2').val();
            var endDate = $('#endDate2').val();
            if (startDate && endDate) {
                console.log('--------自动站 面雨量任意时段查询中------');
                $.ajax({
                    url: '/WeatherLive/GetCalcAreaRain',
                    data: { 'startDate': startDate, 'endDate': endDate },
                    type: 'get',
                    success: function (data) {
                        console.log("---请求成功--自动站 面雨量任意时段查询");
                        clearAll();
                        setBoundaryByData(data, "面雨量任意时段查询");
                    },
                    error: function (e) {
                        console.log(e);
                    }
                });
            } else {
                alert('请选择查询时间!');
            }

        });
        $('#queryLightningLocation').click(function () {
            var sel_area_val = $('#sel_area').val();
            var startDate = $('#startDate3').val();
            var endDate = $('#endDate3').val();
            if (startDate1 && endDate1) {
                console.log('-------- 闪电定位 任意时段查询中------');
                $.ajax({
                    url: '/WeatherLive/GetTimeRangeModernLighting',
                    data: { 'startDate': startDate, 'endDate': endDate, 'street': sel_area_val },
                    type: 'get',
                    success: function (data) {
                        console.log("---请求成功--闪电定位 任意时段查询");
                        clearAll();
                        setLightningLocationByData(data, "任意时段查询");
                    },
                    error: function (e) {
                        console.log(e);
                    }
                });
            } else {
                alert('请选择查询时间!');
            }

        });

    }, {});
    
    
    
    
    //任意时段查询 添加图层
    function setLayersByData(data, value) {

        if (data.Data && data.Data != null && data.Data.Json && data.Data.Json != null) {
            var jsonData = strToJson(data.Data.Json).Data;
            var validData = [];

            var elementName = $('#sel_element option:selected').text();
            for (var i = 0, l = jsonData.length; i < l; i++) {
                jsonData[i].Element = elementName;
                if (elementName.indexOf("温") != -1) {
                    validData.push(jsonData[i]);
                } else if (elementName.indexOf("温") == -1 && jsonData[i].Value > 0) {
                    validData.push(jsonData[i]);//过滤降水值为0的站点
                }
            }
            jsonData = [];
            jsonData = validData;
            setPoints.add(jsonData, value, value, {
                url: "/Plugins/RealMonitoring/static/images/icon/qxzd/气象站点.png",
                width: 22,
                height: 24
            }, isShow);

            jsonData = jsonData.sort(function (a, b) {
                return b.Value - a.Value;
            });
            $("#rankInfo").html(template("table1", {
                data: jsonData,
                title: value
            }));
            bingLocationPoint();
        }
        //------------------Micaps4Contour-----
        if (data.Data != null && data.Data.Xml && data.Data.Xml != null) {
            var xmlData = createXml(data.Data.Xml);
            setLayers.add(xmlData);

            mapTitle.show().html(xmlData.getElementsByTagName("Micaps4Contour")[0].getAttribute("Title"));
        }
    }
    //面雨量时段查询 添加边界线图层
    var setBoundaryByData = function (data, value) {

        var jsonData = strToJson(data.Data);

        var rainbowContent = jsonData.RainbowContent;
        var _xml = '<?xml version="1.0" encoding="utf-8"?>'
                    + '<Micaps4Contour Title="'
                    + jsonData.Title
                    + '" SubTitle="">'
                    + '<Rainbow>'
                    + '<Content>'
                    + rainbowContent
                    + '</Content></Rainbow><RS></RS>'
                    + '</Micaps4Contour>';
        var xmlData = createXml(_xml);
        setLayers.add(xmlData);
        mapTitle.show().html(xmlData.getElementsByTagName("Micaps4Contour")[0].getAttribute("Title"));

        var datas = jsonData.AreaRainDatas;
        for (var i = 0, l = datas.length; i < l; i++) {
            var item = datas[i];
            if (item.MapId.indexOf("杭州市") != -1 || item.MapId.indexOf("高桥") != -1 || item.MapId.indexOf("受降") != -1) {
                continue;//绍兴市/上虞区覆盖整个上虞区，去掉 高桥镇和受降合并成了银湖街道 去掉（注：数据库没有银湖街道）
            }
            var theUrl = "/Config/fyCountry/" + item.MapId.replace('/', '_') + ".xml";
            var theColor = item.Color;
            var theVal = item.Value < 0 ? 0 : item.Value;//面雨量值小于0 重置为0
            $.ajax({
                url: theUrl,
                type: "get",
                dataType: "xml",
                async: false,
                success: function (xml) {
                    var b = gis.drawBoundary("上虞", xml, {
                        fillOpacity: 0.7,
                        fillColor: theColor,
                        strokeColor: '#ADACAC'
                    });
                    boundrys.push(b);

                    var lonlat = $(xml).find("Area").attr("GovCenter").split(',');
                    var name = $(xml).find("Area").attr("Id");
                    var val = [{
                        Latitude: lonlat[1],
                        Longitude: lonlat[0],
                        Name: name,
                        Value: theVal
                    }];
                    setPoints.add(val, value, value, {
                        url: "/Plugins/RealMonitoring/static/images/icon/qxzd/气象站点.png",
                        width: 22,
                        height: 24
                    }, isShow);

                }
            });
        }
    }
    //闪电定位任意时段查询
    var setLightningLocationByData = function (data, value) {
        clearAll();
        if (data.Data && data.Data != null) {
            var jsonData = strToJson(data.Data).Data;

            setPoints.addLightningPoints(jsonData, value, value, {
                url: "/Plugins/RealMonitoring/static/images/icon/{value}.png",
                width: 12,
                height: 12
            });
        }
    }
    //闪电定位
    elSlidebar.delegate('.checklabel[data-name="lightningLocation"]', 'click', function () {
        $('.top').css('display', 'none');//隐藏右侧任意时段查询模块
        var value = $(this).attr("data-value");
        clearAll();
        var menuID = $(this).attr("data-id");
        var datetime = $('#dateControl').val();
        $.ajax({
            url: '/WeatherLive/GetLightningLocation',
            data: { 'menuID': menuID, 'dateTime': datetime },
            type: 'post',
            success: function (data) {
                if (data.Data && data.Data != null) {
                    var jsonData = strToJson(data.Data).Data;

                    setPoints.addLightningPoints(jsonData, value, value, {
                        url: "/Plugins/RealMonitoring/static/images/icon/{value}.png",
                        width: 12,
                        height: 12
                    }, isShow);
                    $("#rankInfo").html(template("table_闪电定位", {
                        data: statistical(jsonData),
                        title: value
                    }));
                }
            },
            error: function (ex) {
                console.log("没有获取到数据");
            }
        });
    });
    //闪电定位 乡镇街道闪电次数统计数据排序
    function statistical(data) {
        var sortList = [];
        var sortItem = {
            Name: null,
            Count: 0
        };
        for (var i = 0, l = data.length; i < l; i++) {
            var item = data[i];
            if (sortList.length > 0) {
                var symbol = 1;
                for (var j = 0, len = sortList.length; j < len; j++) {
                    var item2 = sortList[j];
                    if (item2.Name == item.Name) {
                        item2.Count++;
                        symbol = 0;
                        break;
                    }
                }
                if (symbol) {
                    sortItem = {
                        Name: item.Name,
                        Count: 1
                    }
                    sortList.push(sortItem);
                }
            } else {
                sortItem = {
                    Name: item.Name,
                    Count: 1
                }
                sortList.push(sortItem);
            }
        }
        sortList = sortList.sort(function (a, b) {
            return b.Count - a.Count;
        });
        return sortList;
    }

    //是否显示导出按钮
    elSlidebar.delegate('.checklabel[data-type="radio"]', 'click', function () {
        var ck = $(this);
        var value = $(this).attr("data-value");
        if (value != undefined) {
            if (value.indexOf("查询") != -1 || value.indexOf("分钟") != -1) {
                $('#export').css("display", "");
            } else {
                $('#export').css("display", "none");
            }
        } else {
            ck = $(ck.find('ul li.checklabels.checked'));//获取第三级菜单
            if (ck.attr("data-value")) {
                $('#export').css("display", "");
            } else {
                $('#export').css("display", "none");
            }
        }
    });
    //注册导出按钮 下拉框
    function exportEvent(data) {
        if (data.Type && data.Type.Type) {
            $('#sp_export').attr("data-type", data.Type.Type);
        }
        if (data.Menus) {
            var html = "";
            for (var i = 0, l = data.Menus.length ; i < l; i++) {
                html += '<li><label><input type="checkbox"  checked="checked" id="' + data.Menus[i].ID + '" />' + data.Menus[i].Name + '</label></li>';
            }
            $('.export ul').html(html);
        }
        //----导出样式事件-------
        $('#export').css("display", "");//显示控件
        $('#export span').mouseover(function () {
            $('.export ul').addClass('show');
        });
        $('.export ul').mouseover(function () {
            $(this).addClass('show');
        });
        $('.export ul').mouseout(function () {
            $('.export ul').removeClass('show');
        });
    }
    //显示站点
    elSlidebar.delegate('.checklabel[data-name="station"]', 'click', function () {
        //if ($(this).find('ul li').length > 0) {
        //    return;//判断是否是子节点
        //}
        //var isChecked = $(this).hasClass("checked");
        //var value = $(this).attr("data-value");
        //if (value == "地质灾害点") {
        //    zhLegend.show();
        //} else {
        //    zhLegend.hide();
        //}

        //if (isChecked) {
        //    setPoints.clear(value);
        //    setPopup.hide();
        //} else {
        //    var menuID = $(this).attr("data-id");
        //    var datetime = $('#dateControl').val();
        //    $.ajax({
        //        url: '/WeatherLive/GetMenuData',
        //        data: { 'menuID': menuID, 'dateTime': datetime },
        //        type: 'post', //async:false, //dataType: "json",
        //        success: function (data) {
        //            if (data.Data != null) {
        //                var jsonData = strToJson(data.Data.Json);
        //                var icon;

        //                if (value == "地质灾害点") {
        //                    icon = {
        //                        url: '/Plugins/RealMonitoring/static/images/icon/{value}.png',
        //                        width: 16,
        //                        height: 16
        //                    };
        //                    // gis.addControl("");
        //                } else {
        //                    icon = {
        //                        url: '/Plugins/RealMonitoring/static/images/icon/' + value + ".png",
        //                        width: 16,
        //                        height: 16
        //                    };
        //                }
        //                setPoints.add(jsonData, value, value, icon);
        //                //----排名统计-------------
        //                if (value.indexOf("林业") != -1 || value.indexOf("农业") != -1 || value.indexOf("渔业") != -1 || value.indexOf("牧业") != -1) {
        //                    jsonData = jsonData.sort(function (a, b) {
        //                        return b.Value - a.Value;
        //                    });

        //                    $(".tablebox").html(template("table1_nlmy", {
        //                        data: jsonData,//农林牧渔模板
        //                        title: value
        //                    }));
        //                } else if (value.indexOf("救灾") != -1 || value.indexOf("安置") != -1 || value.indexOf("监控点") != -1) {
        //                    ;//不做排名
        //                } else if (value.indexOf("地质灾害点") != -1) {

        //                    jsonData = jsonData.sort(function (a, b) {
        //                        return b.Level - a.Level;
        //                    });

        //                    $(".tablebox").html(template("table1_dzzhd", {
        //                        data: jsonData,//地质灾害模板
        //                        title: value
        //                    }));
        //                } else {
        //                    jsonData = jsonData.sort(function (a, b) {
        //                        return b.Value - a.Value;
        //                    });

        //                    $(".tablebox").html(template("table1", {
        //                        data: jsonData,//站点模板
        //                        title: value
        //                    }));
        //                }
        //            }
        //        }
        //    });
        //}
    });
    //显示等值线
    elSlidebar.delegate('.checklabel[data-name="micaps4"]', 'click', function () {
        //if ($(this).find('ul li').length > 0) {
        //    return;
        //}
        //var isChecked = $(this).hasClass("checked");
        //var value = $(this).attr("data-value");
        //var ck = elSlidebar.find('.checklabel[data-name="micaps4"].checked');
        ////清除被选中的
        //if (ck && ck.attr("data-value") != value) {
        //    setPoints.clear(); //不管什么，都全部清除
        //}

        //setLayers.clear();
        //mapTitle.hide();
        //if (isChecked) {
        //    setPopup.hide();
        //} else {
        //    var menuID = $(this).attr("data-id");
        //    var datetime = $('#dateControl').val();
        //    $.ajax({
        //        url: '/WeatherLive/GetMenuData',
        //        data:{'menuID':menuID,'dateTime':datetime},
        //        type: 'post',
        //        success: function (data) {
        //            //debugger;
        //            if (data.Data && data.Data != null && data.Data.Json && data.Data.Json != null) {
        //                var jsonData = strToJson(data.Data.Json).Data;

        //                setPoints.add(jsonData, value, value, {
        //                    url: "/Plugins/RealMonitoring/static/images/icon/test.png",
        //                    width: 12,
        //                    height: 24
        //                });

        //                jsonData = jsonData.sort(function (a, b) {
        //                    return b.Value - a.Value;
        //                });
        //                $(".tablebox").html(template("table1", {
        //                    data: jsonData,
        //                    title: value
        //                }));
        //            }
        //            //------------------Micaps4Contour-----
        //            if (data.Data != null && data.Data.Xml && data.Data.Xml != null) {
        //                var xmlData = createXml(data.Data.Xml);
        //                setLayers.add(xmlData);

        //                mapTitle.show().html(xmlData.getElementsByTagName("Micaps4Contour")[0].getAttribute("Title"));
        //            }
        //        },
        //        error: function (ex) {
        //            console.log("没有获取到数据");
        //        }

        //    });
        //}
    });
    
    //上虞预警展示
    /*
    var syEwpShowList = $('.syewp-map-show-icon');
    syEwpShowList.live("click",function(){
    	alert('fuck!');
        var jsonData = [];
        var val = [{
            Latitude: 29.801,
            Longitude: 120.701,
            Name: '1234',
            Value: 'theVal'
        }];
        jsonData = val;
        setPoints.add(jsonData, "ffff", "ffff", {
            url: "/movinginfo-web/images/warnsmall/03.jpg",
            width: 40,
            height: 40
        }, true);
    	
    });*/
    
    $.ajax({
        url:'scene!setPointsList.action',
        success: function (list) {
            var length = list.length;
            for(var i=0;i<length;++i){
            	var data = list[i];
            	setPoints.add(data.point, data.type, data.popup_name,data.icon, true);
            }
        },
        error: function (ex) {
            console.log("获取数据失败，无法获取需要显示的icon数据！");
        }
    });
    
    
});