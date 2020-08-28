define('ldxx.js', function (require, exports, module) {
    var GIS = require("lib/gigis/gi-gis");
    var Layer = require("components/map-hzqxsk");
    var windFieldImg = require("components/windFieldImg");
    var jtzs = require("jtzs");
    var timerAxis = null;
    var radarLayer;
    var lons;
    var lats;
    var zooms = 11;
    var boundaryCut;
    var requstWeatherData;
    var dataType;
    var iconType;
    var radarFile;
    var urlPath;
    var lowType;
    var outType;
    var oneValue = 1;
    var markerArr = [];
    var markerlayer;
    var chartTime;
    var zoom = 11;
    var gridData;
    var contourData;
    var swType;
    var boundaryData = [];
    //雷达图层
    //格点图层
    var gridLayer;
    var boundary1;
    var boundary2;
    var contourlayer;
    //等值线图层
    var contourLayer;
    //定义点击要素类型
    var type;
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
        wrapDateLine: true
    });
    var mapLayer = Layer(gis);
    if (!contourlayer) {
        contourlayer = Layer(gis);
    }
    // 图层切换
    gis.addControl("baseLayerSwitcher", -20, 20);
    // 图层缩放
    gis.addControl("zoom", -70, 140);
    /*    gis.addControl("mousePosition", 181, 5, {
            prefix: "经度：",
            separator: " 纬度：",
            numDigits: 3
        });*/


    //地图点击事件
    gis.map.events.on({
        "click": function () {
            $(".map-left>li").removeClass("click");
        },
        "moveend": function (e) {
        }
    });

    (function () {
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
        var ahd = avd * Math.PI / 180;
        $(".olControlZoom .olButton").each(function (index, element) {
            $(this).css({
                "left": -dotLeft - Math.sin((ahd * index)) * radius,
                "top": Math.cos((ahd * index)) * radius + dotTop
            });
        });
    })();
    $(".popup-h").click(function (e) {
        $(".m-popup").addClass("hide");
        window.event ? window.event.cancelBubble = true : e.stopPropagation();
    });
    $("body").on("click", ".m-popup.hide", function () {
        $(".m-popup").removeClass("hide");
    })
    $("body").on("click", ".fzjz-save>li", function (e) {

        e.stopPropagation();
        oneValue = parseInt($(this).attr("value"));
        if (oneValue == 4) {
            if (boundary2) {
                boundary2.setVisibility(false);
            }
            $(".popup-lightning").show();
            $(".maptitle").show();
            $(".m-tool-box").show();
            $("#radarColorList").hide();
            $("ul.meau_erji").hide();
            $(".m-popup").hide();
            $(".menu_sanji").hide();
            $(".threeV").removeClass("on");
            if (markerlayer) {
                markerlayer.clearMarkers();
            }
            if (radarLayer) {
                radarLayer.destroy();
            }
            if (contourLayer) {
                contourLayer.destroy();
            }
            if (gridLayer) {
                gridLayer.destroy();
                gridLayer = null;
            }
            var type = $(this).attr("type");
            getStationTimeAxis(type);
        }


    })
    //菜单栏二级菜单点击事件
    $("body").on("click", ".meau_erji>li", function (e) {
        $(this).siblings().find("ul.menu_sanji").hide();
        if ($(this).children("div").hasClass("noDropDown")) {
            if (boundary2) {
                boundary2.setVisibility(true);
            }
            $(".code").hide();
            $(".popup-lightning").hide();
            $(".m-tool-box").show();
            $(".maptitle").show();
            $(".threeV").removeClass("on");
            $(this).addClass("on").siblings().removeClass("on");
            $(this).parent().parent().siblings().find("li.emV").removeClass("on");
            $(".m-chart").hide();
            if (oneValue == 3 || oneValue == 5) {
                $(".m-tool-box li.m-time-axis").addClass("on");
                $(".m-legend").hide();
                $(".no_data").hide();
                $(".legend").hide();
                $("#radarColorList").show();
                if (contourLayer) {
                    contourLayer.destroy();
                }
                if (markerlayer) {
                    markerlayer.clearMarkers();
                }
                if (gridLayer) {
                    gridLayer.destroy();
                    gridLayer = null;
                }
                if (boundary2) {
                    boundary2.setVisibility(false);
                }
                $(".m-popup").hide();
                dataType = $(this).attr("data-type");
                iconType = $(this).attr("icon-type");
                getTimeAxisData();
            } else if (oneValue == 1) {
                $(".m-legend").show();
                $(".legend").show();
                $("#radarColorList").hide();
                if (radarLayer) {
                    radarLayer.destroy();
                }
                outType = $(this).attr("type");
                lowType = "";
                getStationTimeAxis();
            }
            if (oneValue != 2) {
                if (boundary1) {
                    clearBoundary();
                }
            }
            if (oneValue != 4) {
                $(".no_data").hide();
            }
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
    $("body").on("click", ".menu_sanji>li", function (e) {
        $(".m-chart").hide();
        $(".code").hide();
        $(".popup-lightning").hide();
        $(".m-tool-box").show();
        $(".maptitle").show();
        $(this).parent().parent().siblings().removeClass("on");
        $(".menu_sanji>li").removeClass("on");
        $(this).addClass("on");
        if (boundary2) {
            boundary2.setVisibility(true);
        }
        if (oneValue == 3 || oneValue == 5) {
            $(".m-tool-box li.m-time-axis").addClass("on");
            $(".m-legend").hide();
            $(".no_data").hide();
            $(".legend").hide();
            $("#radarColorList").show();
            if (markerlayer) {
                markerlayer.clearMarkers();
            }
            if (contourLayer) {
                contourLayer.destroy();
            }
            if (gridLayer) {
                gridLayer.destroy();
                gridLayer = null;
            }
            if (boundary2) {
                boundary2.setVisibility(false);
            }
            $(".m-popup").hide();
            dataType = $(this).attr("data-type");
            iconType = $(this).attr("icon-type");
            getTimeAxisData();
        } else if (oneValue == 1) {
            $(".m-legend").show();
            $(".legend").show();
            $("#radarColorList").hide();
            if (radarLayer) {
                radarLayer.destroy();
            }
            if (markerlayer) {
                markerlayer.clearMarkers();
            }
            $(".m-tool-box li.m-time-axis").removeClass("on");
            if ($(this).parent().parent("li").attr("type") == "Hydrologic") {
                if (contourLayer) {
                    contourLayer.destroy();
                }
                if (gridLayer) {
                    gridLayer.destroy();
                    gridLayer = null;
                }
                $(".m-tool-box li.m-time-axis").addClass("on");
                $(".m-legend").hide();
                $(".legend").hide();
                outType = "Hydrologic";
                swType = $(this).attr("data-type");
                getStationTimeAxis(outType);
            } else {
                outType = $(this).parents(".emV").attr("type");
                lowType = $(this).attr("data-type");
                getStationTimeAxis();
            }
            $(".m-popup").show();
        } else {
            $("#radarColorList").hide();
            if (radarLayer) {
                radarLayer.destroy();
            }
            if (markerlayer) {
                markerlayer.clearMarkers();
            }
            if (contourLayer) {
                contourLayer.destroy();
            }
            if (gridLayer) {
                gridLayer.destroy();
                gridLayer = null;
            }
            outType = $(this).parents(".emV").attr("type");
            lowType = $(this).attr("data-type");
            getStationTimeAxis();
        }
        if (oneValue != 2) {
            if (boundary1) {
                clearBoundary();
            }
        }
        if (oneValue != 4) {
            $(".no_data").hide();
        }
        e.stopPropagation();
    })
    //点击雷达色标
    $("#radarColorList").on("click", "li i", function () {
        var index = $(this).parent().index();
        var $li = $("#radarColorList").find("li i");
        for (var i = 0, l = $li.length; i < l; i++) {
            if (i < index) {
                $($li[i]).css({'opacity': '0.2'});
            } else {
                $($li[i]).css({'opacity': '1'});
            }
        }
        clickRadarLegend(index);
    });
    //格点 点击事件
    $(".m-legend ul li").click(function () {
        if ($(this).hasClass("on")) {
            $(this).removeClass("on");
            if ($(this).find("span").text() == "格点" && gridLayer) {
                gridLayer.setVisibility(false);
            } else if ($(this).find("span").text() == "等值线" && contourLayer) {
                contourLayer.setVisibility(false);
                $(".legend").hide();
            } else {
                $(".site_content").hide();
            }
        } else {
            $(this).addClass("on");
            if ($(this).find("span").text() == "格点") {
                gridLayer ? gridLayer.setVisibility(true) : getGridData(gridData);
            } else if ($(this).find("span").text() == "等值线") {
                $(".legend").show();
                contourLayer ? contourLayer.setVisibility(true) : drawContour(contourData);
            } else {
                $(".site_content").show();
            }
        }
    })
    $(".m-chart>i").click(function () {
        $(".m-chart").hide();
    })
    //下拉框

    $("body").on("click", ".m-select-time>ul>li", function () {
        var index = $(this).index();
        if (oneValue == 1 && outType == "Hydrologic") {
            timeAxisControl(index, outType);
        } else {
            timeAxisControl(index);
        }
    });

    //获取边界数据
    getBoundaryData();

    function getBoundaryData() {
        var indexLayer = layer.load(2);
        getData('/static2/data/hyq.xml', "get", '', "xml", function (data) {
            var boundary = gis.drawBoundary("通州区", data, {
                fillOpacity: 0.68,//透明度
                fillColor: "transparent",//填充色
                strokeColor: "red",//边界线条颜色灰色
                strokeWidth: "2",
            });
            layer.close(indexLayer);
        })
    }

    getBoundaryData1();

    function getBoundaryData1() {
        var indexLayer = layer.load(2);
        getData('/static2/data/hyq.xml', "get", '', "xml", function (data) {
            boundary2 = gis.drawBoundary("通州区", data, {
                fillOpacity: 0.68,//透明度
                fillColor: "#2077be",//填充色
                strokeColor: "transparent",//边界线条颜色灰色
                strokeWidth: "2",
                isCut: true,
            });
            layer.close(indexLayer);
        })
    }


    function showStations(stationData) {
        //加载站点
        if (markerlayer) {
            markerlayer.clearMarkers();
        } else {
            markerlayer = gis.addMarkers();
        }

        $.each(stationData, function (i, n) {
            var value = $(".fzjz-save li.z-on").attr("value");
            var name = "";
            var url = "";
            var icon;
            if (value == 4) {
                if (n.type == "1") {
                    url = "./static2/images/marker/plus_icon.png";
                } else if (n.type == "-1") {
                    url = "./static2/images/marker/minus_icon.png";
                }
                icon = {
                    url: url,
                    width: 25,
                    height: 25
                };
            } else {
            	if(typeof(n.name) == "undefined") {
					name = n.code;
				}else {
					name = n.name;
				}
//                name = n.name;
                url = "./static2/images/marker/" + outType + ".png";
                icon = {
                    url: url,
                    width: 25,
                    height: 25
                };
            }

            var text = '';

            text += "<div class='site_txt'>" + n.value + "</div>";

            text += "<div class='site_content'>" + name + "</div>";

            var marker = gis.addMarker({
                icon: icon,
                position: "br",
                lon: parseFloat(n.lon),
                lat: parseFloat(n.lat),
                content: n,
                text: text
            }, markerlayer)[0];
            // marker.filter = {stationName: name};
            marker.events.register("click", marker, clickStation);
            markerArr.push(marker);
        });
        // markerlayer.setZIndex("9999");
    }

    function showRainStations(stationData) {
        //加载站点
        if (markerlayer) {
            markerlayer.clearMarkers();
        } else {
            markerlayer = gis.addMarkers();
        }
        $.each(stationData, function (i, n) {
            var name = "";
            var url = "";
            var icon;
            name = n.name;
            var text = '';
            if (n.type == "1") {
                url = "./static2/images/marker/Weather.png";
                text += "<div class='site_txt'>" + n.stationValue + "mm</div><div class='site_content'>" + name + "</div>";
            } else {
                url = "./static2/images/marker/otherWeather.png";
                text += "<div class='site_txt' style='color: #999999'>" + n.stationValue + "mm</div><div class='site_content' style='color: #999999'>" + name + "</div>";
            }
            icon = {
                url: url,
                width: 25,
                height: 25
            };
            var marker = gis.addMarker({
                icon: icon,
                position: "br",
                lon: parseFloat(n.lon),
                lat: parseFloat(n.lat),
                content: n,
                text: text
            }, markerlayer)[0];
            markerArr.push(marker);
        });
        var lon = "";
        var lat = "";
        if (outType == "reservoir_area") {
            lon = "120.97";
            lat = "28.61";
        } else {
            lon = "121.20";
            lat = "28.63";
        }
        icon1 = {
            url: "./static2/images/marker/1.png",
            width: 25,
            height: 25
        };
        var marker = gis.addMarker({
            icon: icon1,
            position: "br",
            lon: lon,
            lat: lat,
            text: '<span style="font-size: 20px">' + stationData[0].value + 'mm</span>'
        }, markerlayer)[0];
        markerArr.push(marker);
    }

    //清空站点
    function clearMarkers(obj) {
        if (!obj)
            return;
        obj.clearMarkers();
    }


    //获取格点数据
    //  getGridData();
    function getGridData(data) {
        //清空对象
        if (gridLayer) {
            gridLayer.destroy();
            gridLayer = null;
        }
        var invalidValue = [-9999];//过滤无效值
        if (data) {
            gridLayer = gis.drawGrid("GridPointLayer", data, null, invalidValue);
        } else {
            if ($(".grid").hasClass("markercurrent")) {
                alert("格点数据为空！");
            }
        }
        if (gridLayer) {
            if (!$(".m-legend li.grid").hasClass("on")) {
                gridLayer.setVisibility(false);
            }
        }

    }

    //站点排名
    function markerRank(data) {
        $(".popup-detail ul").empty()
        if (data && data.length > 3) {
            $(".m-popup").show();
            var str = "";
            for (var i = 0; i < 10 && i < data.length; i++) {
//                str += '<li><span>' + (i + 1) + '</span><span>' + data[i].name + '</span><span>' + data[i].value + '</span></li>';
            	if(typeof(data[i].name) == "undefined"){
                    //data[i].name== document.getElementById("station").innerText
 				   str += '<li><span>' + (i + 1) + '</span><span>' + data[i].code + '</span><span>' + data[i].value + '</span></li>';
                }else {
 				   str += '<li><span>' + (i + 1) + '</span><span>' + data[i].name + '</span><span>' + data[i].value + '</span></li>';
 			   }
            }
            $(".popup-detail ul").append(str);
        } else {
            $(".m-popup").hide();
        }
    }

    //点击站点
    function clickStation(e) {
        var value = $(".fzjz-save li.z-on").attr("value");
        var id = e.object.content.id;
        var d;
        var url;
        if (value == 4) {
            d = {
                id: id
            }
            url = "/sen/thunder/getThunder";
        } else if (outType == "Hydrologic") {
            $(".m-chart").show();
            var stcd = $(this)[0].content.name;
            var swValue = $(this)[0].content.value;
            document.getElementById("station").innerText = stcd;
            document.getElementById("value").innerText = swValue;
            var time = $(".maptitle").text().split("水")[1];
            var d = {
                stcd: $(this)[0].content.stcd,
                time: time
            }
            url = "/sen/stinf/getStcdData"
        } else {
            var code = $(this)[0].content.code;
            if (code) {
                $(".m-chart").show();
                d = {
                    type: outType,
                    lowType: lowType,
                    time: chartTime,
                    code: code
                }
                url = "/sen/station/getThisData";
            }
        }

        getData(url, "get", d, "json", function (data) {
            if (data) {
                if (value == 4) {
                    var html = "<div class='layer_table'><table><tr><td>时间</td><td>" + data.date + "</td></tr><tr><td>经度</td><td>" + data.lon + "</td></tr>" +
                        "<tr><td>纬度</td><td>" + data.lat + "</td></tr><tr><td>编号</td><td>" + data.num + "</td></tr><tr><td>强度</td><td>" + data.power + "</td></tr></table></div>";
                    layer.open({
                        title: ["<div class='popup_hd_bk'>闪电信息</div><div></div>", 'font-size:14px;padding:0;background:#1c4560;color:#fff;height:auto;border-bottom: 1px solid rgba(255,255,255,0.28);'],
                        type: 1,
                        skin: 'layui-layer-rim',
                        closeBtn: 2,
                        shade: [0],
                        area: ['400px', '330px'],
                        content: html,
                        resize: false,
                    });
                } else {
                    if (outType == "Hydrologic") {
                        var xAxis = [];
                        var seriesData = [];
                        for (var i = 0; i < data.length; i++) {
                            xAxis.push(data[i].time);
                            seriesData.push(data[i].value);
                        }
                        var chart = Highcharts.chart('stationChart', {
                            chart: {
                                type: 'area',
                                backgroundColor: 'transparent'
                            },
                            title: {
                                text: '',
                                style: {
                                    color: '#FFF',      //字体颜色

                                }
                            },
                            legend: {
                                layout: 'vertical',
                                align: 'center',
                                verticalAlign: 'bottom',
                                x: 0,
                                y: 0,
                                backgroundColor: 'transparent',

                            },
                            xAxis: {
                                labels: {
                                    style: {
                                        font: 'normal 10px 宋体',
                                        color: '#FFFFFF'
                                    },
                                },
                                categories: xAxis
                            },
                            yAxis: {
                                lineWidth: 1,
                                tickWidth: 1,//去掉刻度
                                gridLineWidth: 0,
                                title: {
                                    text: ''
                                },
                                labels: {
                                    style: {
                                        font: 'normal 10px 宋体',
                                        color: '#FFFFFF'
                                    },
                                    formatter: function () {
                                        return this.value;
                                    }
                                }
                            },
                            tooltip: {
                                formatter: function () {
                                    return '<b>' + this.y + '</b><br/>'
                                }
                            },
                            plotOptions: {
                                area: {
                                    fillOpacity: 0.5
                                }
                            },
                            credits: {
                                enabled: false
                            },
                            series: [{

                                name: '<span style="color:#fff">数值</span>',
                                data: seriesData
                            }]
                        });
                    } else {
                        var data = JSON.parse(data);
                        document.getElementById("station").innerText = data[0].code;
                        document.getElementById("value").innerText = data[0].value;
                        var xAxis = [];
                        var seriesData = [];
                        for (var i = data.length - 1; i >= 0; i--) {
                            xAxis.push(data[i].time);
                            seriesData.push(Number(data[i].value));
                        }
                        var chart = Highcharts.chart('stationChart', {
                            chart: {
                                type: 'area',
                                backgroundColor: 'transparent'
                            },
                            title: {
                                text: '',
                                style: {
                                    color: '#FFF',      //字体颜色

                                }
                            },
                            legend: {
                                layout: 'vertical',
                                align: 'center',
                                verticalAlign: 'bottom',
                                x: 0,
                                y: 0,
                                backgroundColor: 'transparent',

                            },
                            xAxis: {
                                labels: {
                                    style: {
                                        font: 'normal 10px 宋体',
                                        color: '#FFFFFF'
                                    },
                                },
                                categories: xAxis
                            },
                            yAxis: {
                                lineWidth: 1,
                                tickWidth: 1,//去掉刻度
                                gridLineWidth: 0,
                                title: {
                                    text: ''
                                },
                                labels: {
                                    style: {
                                        font: 'normal 10px 宋体',
                                        color: '#FFFFFF'
                                    },
                                    formatter: function () {
                                        return this.value;
                                    }
                                }
                            },
                            tooltip: {
                                formatter: function () {
                                    return '<b>' + this.y + '</b><br/>'
                                }
                            },
                            plotOptions: {
                                area: {
                                    fillOpacity: 0.5
                                }
                            },
                            credits: {
                                enabled: false
                            },
                            series: [{

                                name: '<span style="color:#fff">数值</span>',
                                data: seriesData
                            }]
                        });
                    }
                }
            }
        });
    };


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
            $("#map .legend").parent(".olGiLegend").css({"position": "absolute", "left": "190px", "right": "auto"});
            // if (!$(".m-legend li.contour").hasClass("on")) {
            //    contourLayer.setVisibility(false);
            //    $(".legend").hide();
            // }
        }

    }

    //获取一级，二级菜单
    getitem();

    function getitem() {
        var data = {
            id: 1
        };
        $(".fzjz-save").empty();
        var indexLayer = layer.load(2);
        getData('/sen/station/getOneMenu', "get", data, "json", function (data) {
            layer.close(indexLayer);
            if (data) {
                var sum = '';
                for (var i = 0; i < data.length; i++) {
                    if (data[i].dataType) {
                        var str = '<li class="' + data[i].icon + '" value="' + data[i].value + '" type="' + data[i].type + '"><i></i><span>' + data[i].name + '</span></li>';
                        if (data[i].liMenu != null) {
                            var limune = '<ul class="meau_erji">';
                            for (var j = 0; j < data[i].liMenu.length; j++) {
                                if (data[i].liMenu[j].threeMenu && data[i].liMenu[j].threeMenu != null) {
                                    var threemenu = "";
                                    for (var k = 0; k < data[i].liMenu[j].threeMenu.length; k++) {
                                        threemenu += '<li class="threeV" icon-type="' + data[i].liMenu[j].threeMenu[k].icon + '" data-type="' + data[i].liMenu[j].threeMenu[k].type + '" ><i></i>' + data[i].liMenu[j].threeMenu[k].name + '</li>';
                                    }
                                    limune += '<li class="emV" type="' + data[i].liMenu[j].type + '"  data-type="' + data[i].dataType + '"><div class="dropDown"><span>' + data[i].liMenu[j].name + '</span><i class="afterI"></i></div><ul class="menu_sanji">' + threemenu + '</ul></li>';
                                } else {
                                    limune += '<li class="emV" type="' + data[i].liMenu[j].type + '" icon-type="' + data[i].liMenu[j].icon + '" data-type="' + data[i].liMenu[j].type + '"><div class="noDropDown erjiChecked"><i class="prevI"></i><span>' + data[i].liMenu[j].name + '</span></div></li>';
                                }
                            }
                            limune += "</ul>";
                            str = '<li class="' + data[i].icon + '" value="' + data[i].value + '" data-type="' + data[i].dataType + '"><i></i><span>' + data[i].name + '</span>' + limune + '</li>';
                        }
                    } else {
                        var str = '<li class="' + data[i].icon + '" value="' + data[i].value + '"><i></i><span>' + data[i].name + '</span></li>';
                    }
                    sum += str;
                }
                $(".fzjz-save").append(sum);
                getStationTimeAxis(1);
            } else {
                layer.close(indexLayer);
            }

        });
    }

    //获取站点时间
    function getStationTimeAxis(d) {
        $(".m-select-time ul").html("");
        var listUrl;
        if (d && d == "Hydrologic") {
            var data = {
                type: swType
            }
            listUrl = "/sen/stinf/getTimeAxis";
        } else if (d && d == "thunder") {
            var data = {
                type: d
            }
            listUrl = "/sen/thunder/getTimeList";
        } else {
            if (!outType) {
                $(".fzjz-save li").eq(0).find("ul").show();
                $(".fzjz-save li").eq(0).find("ul.meau_erji>li").eq(0).find("ul").show();
                $(".fzjz-save li").eq(0).find("ul.meau_erji>li").eq(0).siblings().find("ul").hide();
                if ($(".emV").find("ul").length > 0) {
                    outType = $(".fzjz-save").find(".emV").eq(0).attr("type");
                    lowType = $(".emV").find("ul").eq(0).find("li").eq(0).attr("data-type");
                    $(".emV").find("ul").eq(0).find("li").eq(0).addClass("on");
                } else {
                    outType = $(".fzjz-save").find(".emV").eq(0).attr("type");
                    lowType = "";
                }
            }
            var data = {
                type: outType,
                lowType: lowType
            }
            if (oneValue == 2) {
                listUrl = "/sen/rainfall/getTimeList";
            } else {
                listUrl = "/sen/station/getTimeList";
            }

        }

        getData(listUrl, "get", data, "json", function (data) {
            if (data) {
                $(".m-axis-list").empty();
                var str = '';
                var str1 = '';
                for (var i = 0; i < data.length; i++) {
                    var time = data[i].time.substr(8, 10);
                    time = time.replace(" ", "");
                    if (i == 0)
                        str += '<li class="z-on" pointPath="' + data[i].pointPath + '" xmlPath="' + data[i].xmlPath + '" title="' + data[i].title + '" time="' + data[i].fileName + '" dataPath="' + data[i].path + '"><span>' + time + '</span></li>';
                    else {
                        str += '<li title="' + data[i].title + '" pointPath="' + data[i].pointPath + '" xmlPath="' + data[i].xmlPath + '" time="' + data[i].fileName + '" dataPath="' + data[i].path + '"><span>' + time + '</span></li>';
                    }
                    str1 += '<li>' + time + '</li>';
                }
                $(".m-axis-list").html(str);
                $(".m-select-time ul").html(str1);
                //$(".m-axis-list").width(data.length*100);
                var len = data.length * 100;
                if (len > 720) {
                    $(".m-axis-list").width(data.length * 100);
                } else {
                    $(".m-axis-list").width(720);
                }
                //timeAxisControl(data.length-1,d);
                $(".m-select-time ul li").eq($(".m-select-time ul li").length - 1).click();
            }
        });
    }

    //获取雷达、卫星时间轴
    function getTimeAxisData(value) {
        $(".m-select-time ul").html("");
        if (!dataType) {
            var erji = $(".fzjz-save>li").eq(value - 1).find(".meau_erji li").eq(0);
            if (erji.find("ul") && erji.find("ul").length > 0) {
                dataType = erji.find("ul li").eq(0).attr("data-type");
                iconType = erji.find("ul li").eq(0).attr("icon-type");
                erji.find("ul li").eq(0).addClass("on").siblings().removeClass("on");
            } else {
                dataType = erji.eq(0).attr("data-type");
                iconType = erji.eq(0).attr("icon-type");
                erji.eq(0).addClass("on").siblings().removeClass("on");
            }
        }
        var data = {
            type: dataType
        };
        getData('/sen/radarAndCloud/getFileList', "get", data, "json", function (data) {
            if (data) {
                var data = JSON.parse(data);
                $(".m-axis-list").empty();
                var str = '';
                var str1 = '';
                radarFile = data.fileList;
                for (var i = 0; i < data.fileList.length; i++) {
                    var time = data.fileList[i].time.substr(8, 10);
                    time = time.replace(" ", "");
                    var radartime = data.fileList[i].time.replace(/[^0-9]/ig, "");
                    if (i == 0)
                        str += '<li class="z-on" time=' + radartime + ' fileName=' + data.fileList[i].fileName + '><span>' + time + '</span></li>';
                    else {
                        str += '<li time=' + radartime + ' fileName=' + data.fileList[i].fileName + '><span>' + time + '</span></li>';
                    }
                    str1 += '<li>' + time + '</li>';
                }
                if (oneValue == 3) {
                    if (data.colorList && data.colorList.length > 0) {
                        radarColorLegend(data.colorList); //绘制雷达色标
                    } else {
                        if ($(".radar").hasClass("current")) {
                            alert("无雷达色标数据！");
                        }
                    }
                }
                $(".m-axis-list").html(str);
                $(".m-select-time ul").html(str1);
                $(".m-axis-list").width(data.fileList.length * 100);
                $(".m-select-time ul li").eq($(".m-select-time ul li").length - 1).click();
                //timeAxisControl(data.fileList.length-1);

            }
        });
    }

    //点击下一个时次
    $(".m-next").click(function (e) {
        if (e.which) {//是手动点击
            stopPlay();
        }
        var index = $(".m-axis-list li.z-on").index();
        var num = $(".m-axis-list li").length;
        if (index == num - 1) return;
        index++;
        if (oneValue == 1 && outType == "Hydrologic") {
            timeAxisControl(index, outType);
        } else {
            timeAxisControl(index);
        }
        var txt = $(".m-select-time ul li").eq(index).text();
        $(".m-select-time>span").text(txt);

    })
    //点击上一个时次
    $(".m-prev").click(function () {
        stopPlay();
        var index = $(".m-axis-list li.z-on").index();
        if (index == 0) return;
        index--;
        if (oneValue == 1 && outType == "Hydrologic") {
            timeAxisControl(index, outType);
        } else {
            timeAxisControl(index);
        }
        var txt = $(".m-select-time ul li").eq(index).text();
        $(".m-select-time>span").text(txt);
    });
    //直接点击时间轴
    $("body").on("click", ".m-axis-list li", function () {
        stopPlay();
        var index = $(this).index();
        if (oneValue == 1 && outType == "Hydrologic") {
            timeAxisControl(index, outType);
        } else {
            timeAxisControl(index);
        }
        var txt = $(".m-select-time ul li").eq(index).text();
        $(".m-select-time>span").text(txt);
    })
    //点击播放
    $(".m-paly").click(function () {
        if ($(this).hasClass("stop")) {
            stopPlay();
            $(this).removeClass("stop");
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
        if (timerAxis) {
            clearInterval(timerAxis);
        }
    }

    //时间轴滚动
    function timeAxisControl(index, swd) {
        if (oneValue == 3 || oneValue == 5) {
            var fileName = $(".m-axis-list li").eq(index).attr("fileName");
            var urlTime = $(".m-axis-list li").eq(index).attr("time");
            var data = {
                icon: iconType,
                fileName: fileName,
                time: urlTime,
                type: dataType
            }
            getData('/sen/radarAndCloud/getData', "get", data, "json", function (data) {
                if (data) {
                    var newData = JSON.parse(data);
                    var radartime = $(".m-axis-list li").eq(index).attr("time");
                    if (radartime) {
                        bindCloudData(newData.path);
                        $(".maptitle").text(newData.title);
                    }
                }
            })
        } else if (oneValue == 1) {
            $(".no_data").hide();
            if (swd && swd == "Hydrologic") {

                var time = $(".m-axis-list li").eq(index).attr("time");
                var title = $(".m-axis-list li").eq(index).attr("title");
                var data = {
                    type: swType,
                    time: time
                }
                getData('/sen/stinf/getRnflData', "get", data, "json", function (d) {
                    if (d) {
                        $(".maptitle").text(title);
                        showStations(d);
                        markerRank(d);
                    } else {
                        $(".maptitle").hide();
                    }
                })
            } else {
                var datapath = $(".m-axis-list li").eq(index).attr("datapath");
                var xmlPath = $(".m-axis-list li").eq(index).attr("xmlPath");
                var pointPath = $(".m-axis-list li").eq(index).attr("pointPath");
                chartTime = $(".m-axis-list li").eq(index).attr("time").split(".")[0];
                var title = $(".m-axis-list li").eq(index).attr("title");

                var data = {
                    path: datapath,
                    xmlPath: xmlPath,
                    pointPath: pointPath
                }
                getData('/sen/station/getStationData', "get", data, "json", function (d) {
                    if (d && d.length > 0) {
                        $(".maptitle").text(title);
                        if (JSON.parse(d).station) {
                            var data = JSON.parse(d).station;
                        }
                        if (JSON.parse(d).xml) {
                            contourData = JSON.parse(d).xml;
                        }
                        if (JSON.parse(d).point) {
                            gridData = JSON.parse(d).point;
                        }
                        showStations(data);
                        markerRank(data);
                        drawContour(contourData);
                        getGridData(gridData);

                    } else {
                        $(".maptitle").hide();
                    }
                })
            }
        } else if (oneValue == 4) {
            if (markerlayer) {
                markerlayer.clearMarkers();
            }
            $(".m-tool-box li.m-time-axis").addClass("on");
            $(".m-legend").hide();
            $(".legend").hide();

            var time = $(".m-axis-list li").eq(index).attr("time");
            var title = $(".m-axis-list li").eq(index).attr("title");
            $(".maptitle").text(title);
            var data = {
                time: time
            }
            getData('/sen/thunder/getThunderList', "get", data, "json", function (data) {
                if (data && data.length > 0) {
                    showStations(data);
                    $(".no_data").hide();
                } else {
                    $(".no_data").show();
                }
            })

        } else {
            $(".m-tool-box li.m-time-axis").addClass("on");
            $(".no_data").hide();
            $(".m-legend").hide();
            $(".legend").hide();
            var time = $(".m-axis-list li").eq(index).attr("time");
            var title = $(".m-axis-list li").eq(index).attr("title");
            var data = {
                type: outType,
                lowType: lowType,
                time: time
            }
            getData('/sen/rainfall/getRainfall', "get", data, "json", function (d) {
                if (d) {
                    $(".maptitle").text(title);
                    if (outType == "small_watershed" || outType == "reservoir_area") {
                        var titleValue = $(".maptitle").text();
                        $(".maptitle").text(titleValue + d[0].value + "mm");
                        $(".m-popup").hide();
                        showRainStations(d);
                        clearBoundary();
                    } else {
                        markerRank(d);
                        getBoundaryColor(d);
                    }
                } else {
                    $(".maptitle").hide();
                }
            })
        }
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

    function bindCloudData(url) {
        urlPath = url;
        var opcity = 0.75;
        if (radarLayer) {
            radarLayer.destroy();
        }
        radarLayer = gis.addTileLayer(urlPath);
        radarLayer.setOpacity(opcity);
    }

    //获取雷电数据
    function getThunderData() {
        getData('/sen/thunder/getThunderList', "get", "", "json", function (data) {
            if (data && data.length > 0) {
                showStations(data);
            }
        })
    }

    //绘制雷达色标
    function radarColorLegend(data) {
        var rColorHtml = "<li>雷达色标</li>";
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
        var fileName = '';//对应的时间轴文件
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
        if (fileName == '' || fileName == 'undifend') {
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
        var url = urlPath.split("FilterValue")[0] + "FilterValue=" + filterValue + "&Level=0";
        bindCloudData(url);
        //formRadarLayer(fileName, filterValue);
    }

    //面雨量    画区域色块
    function getBoundaryColor(d) {
        var bColor = {};
        if (boundary1) {
            clearBoundary();
        }
        if (d && d.length > 0) {
            var url;
            var str = '';
            $(".code").show();
            $(".code ul").empty();
            var colorCode = d[0].color_code.split("\r\n");
            for (var j = 0; j < colorCode.length; j++) {
                var codeValue = colorCode[j].split(" ")[0];
                var codeColor = colorCode[j].split(" ")[1];
                str += '<li><span>' + codeValue + '</span><i style="background:rgb(' + codeColor + ')"></i></li>';
            }
            for (var i = 0; i < d.length; i++) {
                var name = d[i].name;
                var color = "rgb(" + d[i].color + ")";
                bColor[name] = color;
                url = '/static2/data/' + name + '.xml';
                //var indexLayer=layer.load(2);
                getData(url, "get", '', "xml", function (data) {
                    //蒙层
                    var lonlat = data.documentElement.attributes.Center.value;
                    if (lonlat) {
                        var lon = lonlat.split(",")[0];
                        var lat = lonlat.split(",")[1];
                    }
                    var nName = data.documentElement.attributes.Id.value;
                    showAreaStations(nName, lon, lat);
                    var nColor = bColor[nName];
                    boundary1 = gis.drawBoundary(nName, data, {
                        fillOpacity: 0.68,//透明度
                        fillColor: nColor,//填充色
                        strokeColor: "blue",//边界线条颜色灰色
                        strokeWidth: "1",

                    });
                    //layer.close(indexLayer);
                    boundaryData.push(boundary1);
                })
            }
            $(".code ul").html(str);
        }
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

    function showAreaStations(name, lon, lat) {
        var icon;
        var url = './static2/images/marker1.png';
        icon = {
            url: url,
            width: 0,
            height: 0
        };
        var text = '';
        text += "<div class='site_area_content' style='width:50px'>" + name + "</div>";
        var marker = gis.addMarker({
            icon: icon,
            lon: parseFloat(lon),
            lat: parseFloat(lat),
            text: text
        }, markerlayer)[0];
    };
});





