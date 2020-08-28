var requstWeatherData;
var TYPE;
//格点图层
var gridLayer;
//var contourlayer;
//等值线图层 
//var contourLayer;
var markerlayer;
var myLayer;
var exportDiv;

var markerArr = [];
var boundaryA = [];

var mainCityArea;
define('forecastInfo.js', function (require, exports, module) {
    var GIS = require("lib/gigis/gi-gis");
    var Layer = require("components/map-layer");
    var stationMarkerlayers; //站点层
    var contour;//等值线层
    var gis, maplayer;
    var timerAxis = null;
    var contourlayer;
//等值线图层
    var contourLayer;
    var Ismapfirstload = true;
    var warningMarkerlayers;
    var lons;
    var lats;
    var zooms = 11;
    var lines = [];
    var streetArr = [];
    var boundaryArray = [];
    var oneValue = 1;
    // 适配不同分辨率
    //adaptZoom();
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
        documentDrag: false
    });
    var mapLayer = Layer(gis);
    if (!contourlayer) {
        contourlayer = Layer(gis);
    }
    // 图层切换
    gis.addControl("baseLayerSwitcher", -20, 20);
    // 图层缩放
    gis.addControl("zoom", -70, 140);
    //地图点击事件
    gis.map.events.on({
        "click": function () {
            $(".map-left>li").removeClass("click");
        },
    });

    //获取边界数据
    getBoundaryData();

    function getBoundaryData() {
        var indexLayer = layer.load(2);
        getData('/static2/data/hyq.xml', "get", '', "xml", function (data) {
            //蒙层
            var boundary = gis.drawBoundary("通州区", data, {
                fillOpacity: 0.68,//透明度
                fillColor: "#2077be",//填充色
                strokeColor: "red",//边界线条颜色灰色
                strokeWidth: "2",
                isCut: true,
            });
            boundary.setZIndex(100);
            layer.close(indexLayer);
            getArea();
        })
    }

    gis.addControl("mousePosition", 24, -1, {
        prefix: "经度：",
        separator: " 纬度：",
    });
    // 地图相关事件：拖拽/缩放、点击、鼠标移动
    gis.map.events.on({
        "mousemove": function (e) {
            if (oneValue == 1) {
                moveShow();
            }
        }
    });

    (function () {
        $(".olMapViewport").append('<img class="map-shaw" src="./static2/images/map_control.png">');
//	      var mapControlAdd ="<a herf='javascript:void(0)' class='olButton'>+</a>";
//	      mapControlAdd  += "<a herf='javascript:void(0)' class='olButton'>-</a>"
//	      $(".olControlZoom").append(mapControlAdd);
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

    //获取每个区域的xml
    function getArea() {
        var arrY = ["dongchen", "beichen", "beiyang", "chengjiang", "fushanxiang", "gaoqiao", "jiangkou", "maoshexiang", "nanchen", "ningxi", "pingtian", "shabu", "shangzhen", "shangyang", "toutuo", "xichen", "xinqian", "yutou", "yuanqiao"]
        var arr = ["东城街道", "北城街道", "北洋镇", "澄江街道", "富山乡", "高桥街道", "江口街道", "茅畲乡", "南城街道", "宁溪镇", "平田乡", "沙埠镇", "上郑乡", "上垟乡", "头陀镇", "西城街道", "新前街道", "屿头乡", "院桥镇"];
        var url = top.window.location.href.split(/\//g)[3]
        var index = arrY.indexOf(url);
        var name = arr[index];
        for (var i = 0; i < arr.length; i++) {
            streetArr.push(arr[i]);
            $.ajax({
                url: "../static2/data/" + arr[i] + ".xml",
                dataType: "xml",
                type: 'get',
                success: function (data) {
                    var R = data.getElementsByTagName("Area");

                    // 构造边界数据，用来判断点是否在某个区域内：此处借用安吉的边界数据，如果实际开发中，请自定义其他数据
                    //begin
                    for (var i = 0, l = R.length; i < l; i++) {
                        var dat = R[0].attributes[4].value.split(",");
                        var line = [];
                        for (var j = 0, k = 0, m = dat.length; j < m; j++) {
                            var lon = dat[j];
                            var lat = dat[++j];
                            line[k] = {X: parseFloat(lon), Y: parseFloat(lat)};
                            k++;
                        }
                        lines.push(line);
                    }
                    var nameN = $(data).find("Area").attr("Id");
                    var borderColor = "#2077be"
                    var borderW = 1.5;
                    var fillColor = "#2077be";//填充色
                    if (nameN == name) {
                        borderColor = "red"
                        borderW = 2.5;
                        fillColor = "transparent";
                    }
                    if (!name) {
                        borderColor = "#2077be"
                        borderW = 1.5;
                        fillColor = "transparent";
                    }
                    mainCityArea = gis.drawBoundary(arr[i], data, {
                        fillOpacity: 0.5, //透明度
                        fillColor: fillColor, //填充色
                        strokeColor: borderColor, //边界线条颜色灰色
                        strokeWidth: borderW,
                        name: arr[i],
                    });
                    if (nameN == name) {
                        mainCityArea.setZIndex(999)
                    }
                    boundaryArray.push(mainCityArea);
                    if (oneValue == 1) {
                        mainCityArea.events.register("click", mainCityArea, clickShow);
                    }

                }
            })
        }
    }

    //边界区域选中
    function moveShow() {
        var mapPoint = $(".olControlMousePosition").text();
        var lon = mapPoint.split(" ")[0].split("：")[1];
        var lat = mapPoint.split(" ")[1].split("：")[1];
        var point = {X: lon, Y: lat};
        for (var i = 0; i < lines.length; i++) {
            var includeFlag = isInclude(point, lines[i]);
            if (includeFlag) {
                drawBoundaryColor("yellow", i);
                break;
            }
        }
    }

    function clickShow(e) {
        if (oneValue == 1) {//精细化
            if (e) {
                var name;
                var pixel = new OpenLayers.Pixel(e.offsetX, e.offsetY);
                var lonlat = gis.map.getLonLatFromPixel(pixel);
                var mapPoint = gis.map.tanslateLonLat(lonlat, true);
                var point = {X: mapPoint.lon, Y: mapPoint.lat};
                for (var i = 0; i < lines.length; i++) {
                    // var includeFlag = isInclude(point, lines[i]);
                    if (isInclude(point, lines[i])) {
                        drawBoundaryColor("red", i);
                        name = streetArr[i];
                        break;
                    }
                }
                getJxhData(name, "/sen/townJxh/getJxhData?name=");
            }
        } else {
            for (var j = 0; j < boundaryArray.length; j++) {
                var itemBoundary = boundaryArray[j];
                var feature = itemBoundary.features[0];
                feature.style.fillColor = "transparent";
                itemBoundary.drawFeature(feature);
            }
        }


        //$($("#" + mainCityArea.id).find('path')[0]).css('fill', "yellow");
    }

    //改变当前选中面的高亮效果：
    function drawBoundaryColor(color, index) {
        for (var j = 0; j < boundaryArray.length; j++) {
            var itemBoundary = boundaryArray[j];
            var feature = itemBoundary.features[0];
            feature.style.fillColor = "transparent";
            itemBoundary.drawFeature(feature);
        }
        var itemBoundary1 = boundaryArray[index];
        var feature1 = itemBoundary1.features[0];
        feature1.style.fillColor = color;
        itemBoundary1.drawFeature(feature1);

    }

    //点在面内判断
    function isInclude(point, points) {
        var nCross = 0;
        for (var i = 0; i < points.length; i++) {
            var p1 = points[i];
            var p2 = points[(i + 1) % points.length];

            if (Math.abs(p1.Y - p2.Y) < 1e-6) // p1 与 p2 平行
            {
                continue;
            }
            if (point.Y < Math.min(p1.Y, p2.Y)) // 交点在p1 p2延长线上
            {
                continue;
            }
            if (point.Y >= Math.max(p1.Y, p2.Y)) // 交点在p1p2延长线上 
            {
                continue;
            }
            var doublex = (point.Y - p1.Y) * (p2.X - p1.X) / (p2.Y - p1.Y) + p1.X;

            if (doublex > point.X) {
                nCross++;
            }
        }
        return nCross % 2 == 1;
    }

    //获取一级，二级菜单
    getitem();

    function getitem() {
        var data = {
            id: 2
        };
        $(".fzjz-save").empty();
        var indexLayer = layer.load(2);
        getData('/sen/station/getOneMenu', "get", data, "json", function (data) {
            layer.close(indexLayer);
            if (data) {
                var sum = '';
                for (var i = 0; i < data.length; i++) {
                    if (data[i].dataType) {
                        var str = '<li class="' + data[i].icon + '" value="' + data[i].value + '" data-icon="' + data[i].icon + '" data-type="' + data[i].type + '"><i></i><span>' + data[i].name + '</span></li>';
                        if (data[i].liMenu != null) {
                            var limune = '<ul class="meau_erji">';
                            for (var j = 0; j < data[i].liMenu.length; j++) {
                                if (data[i].liMenu[j].threeMenu && data[i].liMenu[j].threeMenu != null) {
                                    var threemenu = "";
                                    for (var k = 0; k < data[i].liMenu[j].threeMenu.length; k++) {
                                        threemenu += '<li class="threeV" data-type="' + data[i].dataType + '" type="' + data[i].liMenu[j].threeMenu[k].type + '"><i></i>' + data[i].liMenu[j].threeMenu[k].name + '</li>';
                                    }
                                    limune += '<li class="emV" data-type="' + data[i].dataType + '"><div class="dropDown"><span>' + data[i].liMenu[j].name + '</span><i class="afterI"></i></div><ul class="menu_sanji">' + threemenu + '</ul></li>';
                                } else {
                                    limune += '<li class="emV" data-type="' + data[i].dataType + '"><div class="noDropDown erjiChecked"><i class="prevI"></i><span>' + data[i].liMenu[j].name + '</span></div></li>';
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
            } else {
                layer.close(indexLayer);
            }

        });
    }

    //街镇精细化一级菜单点击事件 获取乡镇信息以及天气
    function getWeather() {
        getData('/sen/townJxh/getWeather', "get", "", "json", function (data) {
            if (data && data.length > 0) {
                // setStations(data);
            }
        })
    }

    //一级菜单点击事件
    $("body").on("click", ".fzjz-save>li", function () {
        $(".m-pdf").hide();
        $(".decision-detail>i").hide();
        oneValue = parseInt($(this).attr("value"));
        $(".legend").hide();
        $(".no_data").hide();
        if ($(this).hasClass("weekly_weather") || $(this).hasClass("weather_climate") || $(this).hasClass("significant_weather")) {
            document.getElementById("beginTime").innerText = "";
            document.getElementById("endTime").innerText = "";
            var beginTime = document.getElementById("beginTime").innerText;
            var endTime = document.getElementById("endTime").innerText;
            var name = $("#fileName").val();
            var type = $(this).attr("data-type");
            getReport(1, beginTime, endTime, name, type);
            $(".m-decision").show();
            $(".m-map").hide();
            $(".m-tool-box").hide();
            $(".maptitle").hide();
            clickShow();
        } else if ($(this).hasClass("warning_signal")) {
            //var url = "/sev/alarm/getPublishAlarm";
            var url = "../hydata/warming/gtyj.json"
            getStations(url);
            $(".m-decision").hide();
            $(".m-map").show();
            $(".m-tool-box").hide();
            clickShow();
            $(".maptitle").hide();
        } else if ($(this).hasClass("street_town")) {  //街镇精细化预报点击事件
            // getWeather();
            $('#export-last').attr("tempValue",1);
            $(".m-decision").hide();
            $(".m-map").show();
            $(".m-tool-box").hide();
            clickShow();
            $(".maptitle").hide();
        } else if ($(this).hasClass("travel_scenic")) { //旅游景点
            $('#export-last').attr("tempValue",2);
            $(".m-decision").hide();
            $(".m-map").show();
            $(".m-tool-box").hide();
            showScenic();
            $(".maptitle").hide();
            clickShow();
        }
        if (oneValue != 3) {
            if (contourLayer) {
                contourLayer.destroy();
            }
            $(".legend").hide();
            $(".threeV").removeClass("on");
            $(".menu_sanji").hide();

        }
        if (oneValue != 6) {
            if (markerlayer) {
                markerlayer.clearMarkers();
            }
            clearBoundary();
        }
        if (oneValue!=1 && oneValue!=2) {
            $("#export-last").hide();
        }
    });

    //菜单栏二级菜单点击事件
    $("body").on("click", ".meau_erji>li", function (e) {
        $(this).addClass("on").siblings().removeClass("on");
        $(this).siblings().find("ul.menu_sanji").hide();
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
        $(".m-decision").hide();
        if (markerlayer) {
            markerlayer.clearMarkers();
        }
        $(".menu_sanji>li").removeClass("on");
        $(this).addClass("on");
        var selected = $(this).parent().parent().parent().parent().attr("value");
        if (selected == 3) {
            $(".legend").show();
            $(".m-map").show();
            $(".m-tool-box").show();
            var lowType = $(this).attr("type");
            $("#beginTimeSelect").attr("datatype", lowType);
            getTimeAxisData(lowType);
            clickShow();
            e.stopPropagation();
        }
        e.stopPropagation();
    });

    //获取站点数据
    function getStations(url) {
        getData(url, "get", "", "json", function (data) {
        	var value = $(".fzjz-save li.z-on").attr("value");
            if (data && data.length > 0) {
                showStations(data);
//                var ob = {};
//                var newObjArr =  dataArr.reduce((prev, curr)=>{
//                	ob[curr.town] ? true : ob[curr.town] = true && prev.push(curr);
//                    return prev
//                  }, [])//去重
//                  console.log(newObjArr)
//                for(var n =0;n<newObjArr.length;n++) {
                	fillColor(a.color);//填充颜色
//                }
            } else {
                // if (value == 7) {
                    $(".no_data").show();
                // }

            }

        })
    }

    var a = {};
    //展示站点数据
    function showStations(data) {
        //加载点
        if (markerlayer) {
            markerlayer.clearMarkers();
        } else {
            markerlayer = gis.addMarkers();
        }
        $.each(data, function (i, n) {
                if(i==0) {
                	a.color = level2color(n.image);
                	a.image = n.image;
                }else {
                	if (level2Num(a.image) < level2Num(n.image)) {
                        a.color = level2color(n.image);
                    }
                }

            var url = "./static2/images/warning_icon/" + n.image + ".jpg";
            var icon = {
                url: url,
                width: 25,
                height: 25
            };
            n.lon = 121.01+i*0.02;
            n.lat = 28.60;
            //气泡详细信息
            //var text = '';
            //text += "<div class='site_content'>" + name + "</div>";

            var marker = gis.addMarker({
                id: n.id,
                icon: icon,
                position: "br",
                lon: parseFloat(n.lon),
                lat: parseFloat(n.lat),
                content: n,
                //text: text
            }, markerlayer)[0];
            $(marker.icon.imageDiv).find("img").attr("onerror", "javascript:this.src='./static2/images/warning_icon/hyqx.png'");
            marker.events.register("click", marker, clickStation);
            //markerArr.push(marker);
        });
    }

    function level2color(str) {
        if (str.indexOf("蓝色") > -1) {
            return 'rgba(72,118,255,0.8)'
        } else if (str.indexOf("黄色") > -1) {
            return 'rgba(255,255,0,0.8)'
        } else if (str.indexOf("橙色") > -1) {
            return 'rgba(238,154,0,0.8)'
        } else if (str.indexOf("红色") > -1) {
            return 'rgba(255,0,0,0.8)'
        }
    }

    //判断颜色级别
    function level2Num(str) {
        var num = null;
        if (str.indexOf("蓝色") > -1) {
            num = 0;
        } else if (str.indexOf("黄色") > -1) {
            num = 1;
        } else if (str.indexOf("橙色") > -1) {
            num = 2;
        } else if (str.indexOf("红色") > -1) {
            num = 3;
        }
        return num;
    }

    function showScenic() {
        $.ajax({
            url: '/sen/townJxh/getScenic',
            dateType: 'json',
            type: 'get',
            success: function (data) {
                if (markerlayer) {
                    markerlayer.clearMarkers();
                } else {
                    markerlayer = gis.addMarkers();
                }
                $.each(data, function (i, n) {
                    var name = "";
                    var url = "";
                    var icon;
                    var text = '';
                    name = n.name;
                    url = "./static2/emergencyImages/scenicspot.png";
                    icon = {
                        url: url,
                        width: 25,
                        height: 25
                    };
                    text += "<div class='site_content'>" + name + "</div>";
                    var marker = gis.addMarker({
                        icon: icon,
                        position: "br",
                        lon: parseFloat(n.lon),
                        lat: parseFloat(n.lat),
                        content: n,
                        text: text
                    }, markerlayer)[0];
                    //点击站点事件
                    marker.events.register("click", marker, clickScenic);
                    markerArr.push(marker);
                });
            }
        })

    }

    function clickScenic(e) {
        getJxhData(e.object.content.name, "/sen/townJxh/getScenicData?name=");
    }

    //填充颜色
    function fillColor(color) {
        $.ajax({
            url: "../static2/data/hyq.xml",
            dataType: "xml",
            async: false,//同步请求数据
            type: 'get',
            success: function (data) {
                var nameN = $(data).find("Area").attr("Id");
                var borderColor = "#2077be"
                var borderW = 1.5;
                var areaBoundary = gis.drawBoundary("", data, {
                    fillOpacity: 0.5, //透明度
                    fillColor: color, //填充色
                    strokeColor: borderColor, //边界线条颜色灰色
                    strokeWidth: borderW,
                });
                boundaryA.push(areaBoundary);
            }
        })

    }

    //清除边界线
    function clearBoundary() {
        for (var i = 0; i < boundaryA.length; i++) {
            var boundary = boundaryA[i];
            if (boundary) {
                boundary.destroy();
            }
        }
        boundaryA = [];
    }

    //点击站点
    function clickStation(e) {
        var content = e.object.content.content;
        var titleAndContent = content.split("：");
        var typeName = "预警详情";
        var html = "<div class='layer_table'><table><tr><td>预警标题</td><td>" + titleAndContent[0] + "</td></tr>" +
            "<tr><td>发布时间</td><td>" + e.object.content.pubTime + "</td></tr><tr><td>时效</td><td>" + e.object.content.duration + "小时</td></tr><tr><td>发布内容</td><td>" + titleAndContent[1] + "</td></tr></table></div>"
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
    };


    //一周天气预报、天气气候公报、重要天气报告的搜索
    $("#confirm").click(function () {
        var type = $(".fzjz-save li.z-on").attr("data-type");
        var beginTime = document.getElementById("beginTime").innerText;
        var endTime = document.getElementById("endTime").innerText;
        var name = $("#fileName").val();
        getReport(1, beginTime, endTime, name, type);
    })

    //材料的点击事件
    $(".decision-detail table").on("click", "td span", function () {
        $(".decision-detail table span").removeClass("on");
        $(this).addClass("on");
        var pdfUrl = $(this).attr("data-path");
        //var pdfUrl="/hydata/20190603预报发布稿（上午08时）.pdf";
        $(".m-pdf iframe").attr("src", "./static2/js/lib/pdfjs/web/viewer.html?file=" + pdfUrl);
        $(".m-pdf").show();
        $(".decision-detail>i").show();
    })

    $(".decision-detail>i").click(function () {
        $(".m-pdf").hide();
        $(".decision-detail>i").hide();
    })
    $("body").on("click", ".m-nav ul ul>li", function () {
        $(".m-nav ul ul>li").removeClass("z-on");
        $(this).addClass("z-on");
        TYPE = $(this).attr("data-type");
        var type = $(this).attr("data-type");
        // gettimeAxios(type);
        return false;
    })
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
    })
    //点击上一个时次
    $(".m-prev").click(function () {
        stopPlay();
        var index = $(".m-axis-list li.z-on").index();
        if (index == 0) return;
        index--;
        timeAxisControl(index);
    });
    //直接点击时间轴
    $("body").on("click", ".m-axis-list li", function () {
        stopPlay();
        var index = $(this).index();
        timeAxisControl(index);
    })
    //点击播放
    $(".m-paly").click(function () {
        if ($(this).hasClass("stop")) {
            stopPlay();
        } else {
            $(".m-paly").attr("src", "./static2/images/stop_btn.png")
            $(this).addClass("stop")
            clearInterval(timerAxis);
            timerAxis = setInterval(function () {
                $(".m-next").click();
            }, 3000);
        }
    });

    //停止播放
    function stopPlay() {
        $(".m-paly").removeClass("stop");
        $(".m-paly").attr("src", "./static2/images/play_btn.png")
        if (timerAxis) {
            clearInterval(timerAxis);
        }
    }

//初始化地图
    // init();
    function init() {
        $(".mapwrapper").show();
        if (Ismapfirstload) { // 还未加载过地图的情况
            Ismapfirstload = false;

            requstWeatherData = function (type) {
                //降水等值线
                showLayer("./static2/data/" + type + ".xml");
                //获取标题
                getTitle("./static2/data/" + type + ".xml");
                // 加载站点
                loadStations(type);
            }

        } else {
            requstWeatherData(type);
        }
        ;
    }

    //自己请求等值线获取标题
    function getTitle(url) {
        var indexLayer;
        $.ajax({
            url: url,
            dataType: 'xml',
            type: 'post',
            beforeSend: function () {
                indexLayer = layer.load(2);
            },
            success: function (data) {
                if (data) {
                    var title = data.childNodes[0].getAttribute("Title");
                    $(".maptitle").text("杭州市" + title);
                }
            },
            complete: function () {
                layer.close(indexLayer);
            },
            error: function (e) {
                console.log(e);
            }
        })
    }

//等值线加载
    function showLayer(url) {
        if (!maplayer) {
            maplayer = Layer(gis);
        } else {
            maplayer.grid.destroy();
        }
        maplayer.add("grid", "ceshi", url, controllegend);
    }

    //等值线回调函数
    function controllegend() {
        maplayer.grid.setOpacity(0.8);
    }

//请求站点数据 
    function loadStations(type) {
        var indexLayer;
        $.ajax({
            url: "/dd/weather/zd?type=" + type,
            type: "get",
            dataType: "json",
            beforeSend: function () {
                indexLayer = layer.load(2);
            },
            success: function (stations) {
                loadMarker(stations);
            },
            complete: function () {
                layer.close(indexLayer);
            }
        });
    }

    //绘制站点
    function loadMarker(stationData) {
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
        $.each(stationData, function (i, item) {
            var textstr = item.Name + item.Value; //摄氏度或其他单位，可通过参数判断
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

    //获取精细化预报时间轴
    function getTimeAxisData(lowType, reportTime) {
        var postdata = {
            lowType: lowType,
            reportTime: reportTime
        };
        getData('/sen/refinement/getJxhTimeList', "get", postdata, "json", function (datalist) {
            var beginTimeValue;
            var selectStr = "";
            if (datalist) {
                //时间轴数据
                data = JSON.parse(datalist).fileList;
                //起报时间select下拉框值
                beginTimeValue = JSON.parse(datalist).timeList;
                $(".m-axis-list").empty();
                var str = '';
                for (var i = 0; i < data.length; i++) {
                    var time = data[i].time.substr(8, 10);
                    time = time.replace(" ", "");
                    var radartime = data[i].time.replace(/[^0-9]/ig, "");
                    if (i == 0)
                        str += '<li class="z-on" time=' + radartime + ' fileName=' + data[i].fileName + ' xmlPath=' + data[i].xmlPath + ' title=' + data[i].title.replace(/\s*/g, "") + "情况" + '><span>' + time + '</span></li>';
                    else {
                        str += '<li time=' + radartime + ' fileName=' + data[i].fileName + ' xmlPath=' + data[i].xmlPath + ' title=' + data[i].title.replace(/\s*/g, "") + "情况" + '><span>' + time + '</span></li>';
                    }
                }
                console.log(beginTimeValue);
                //select 展示预报起始时间
                for (var j = 0; j < beginTimeValue.length; j++) {
                    //去掉字符串最后一个"-"改为" ";
                    var timeSplit = beginTimeValue[j].split('');
                    timeSplit.splice(10, 1, " ");
                    selectStr += "<option value='" + beginTimeValue[j] + "'>" + timeSplit.join('') + "</option>";
                }
                $("#beginTimeSelect").html(selectStr);
                $("#selectDiv").show();
                $(".m-axis-list").html(str);
                $(".m-axis-list").width(data.length * 100);
                timeAxisControl(data.length - 1);
            }
        });
    }

    //点击起报时间 绑定新的时间轴
    $("#beginTimeSelect").change(function () {
        var reportTime = $("#beginTimeSelect").val();
        var lowType = $("#beginTimeSelect").attr("datatype");
        getTimeAxisData(lowType, reportTime);
    });


    //客观精细化预报时间轴滚动
    function timeAxisControl(index) {
        var contourData = null;
        var xmlPath = $(".m-axis-list li").eq(index).attr("xmlPath");
        var title = $(".m-axis-list li").eq(index).attr("title");
        $(".maptitle").text(title);
        $(".maptitle").show();
        var data = {
            xmlPath: xmlPath
        }
        getData('/sen/refinement/getRefinementData', "get", data, "json", function (d) {
            if (d) {
                if (JSON.parse(d).xml) {
                    contourData = JSON.parse(d).xml;
                }

                drawContour(contourData, 0.5);

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

    //乡镇预报点击弹窗
    function getJxhData(name, url) {
        if (name) {
            $.ajax({
                url: url + name,
                type: "get",
                dataType: "json",
                success: function (data) {
                    //初始化标签
                    var html = '';
                    var timeHtml = '<td style="color: #6F9BB7;">预报时间</td>';
                    var firstWeaHtnl = '<td></td>';
                    // var firstWindHtml = '<td style="color: #6F9BB7;">风向风速</td>';
                    var firstPreHtml = '<td style="color: #6F9BB7;" width="69">降水(毫米)</td>';
                    var secondWeaHtnl = '<td></td>';
                    // var secondWindHtml = '<td style="color: #6F9BB7;">风向风速</td>';
                    var secondPreHtml = '<td style="color: #6F9BB7;" width="69">降水(毫米)</td>';
                    var lowTemHtml = '<td style="color: #6F9BB7;">最低温</td>';
                    var highTemHtml = '<td style="color: #6F9BB7;">最高温</td>';
                    // var rhHtml = '<td style="color: #6F9BB7;" width="69">相对湿度(%)</td>';
                    var lowTem = [];
                    var highTem = [];
                    //处理数据
                    for (var i = 0; i < data.length; i++) {
                        lowTem.push(data[i].temperatureMin);
                        highTem.push(data[i].temperatureMax);
                        timeHtml += '<td style="color: #ffffff;">' + data[i].time + '</td>';
                        // firstWindHtml += '<td style="color: #ffffff;">' + data[i].firstWind + '</td>';
                        // secondWindHtml += '<td style="color: #ffffff;">' + data[i].secondWind + '</td>';
                        if (data[i].firstWeather == null) {
                            firstWeaHtnl += '<td style="color: #ffffff;" width="77">无数据</td>';
                            secondWeaHtnl += '<td style="color: #ffffff;" width="77"></td>';
                            firstPreHtml += '<td style="color: #ffffff;" width="77"></td>';
                            secondPreHtml += '<td style="color: #ffffff;" width="77"></td>';
                            lowTemHtml += '<td style="color: #ffffff;" width="77"></td>';
                            highTemHtml += '<td style="color: #ffffff;" width="77"></td>';
                        } else {
                            firstWeaHtnl += '<td style="color: #ffffff;" width="77">' + '<img src="./static2/weatherType/night/' + data[i].firstWeather + '.png" width="30" height="30"><br>' + data[i].firstWeather + '</td>';
                            secondWeaHtnl += '<td style="color: #ffffff;" width="77">' + '<img src="./static2/weatherType/day/' + data[i].secondWeather + '.png" width="30" height="30"><br>' + data[i].secondWeather + '</td>';
                            firstPreHtml += '<td style="color: #ffffff;" width="77">' + data[i].precipitation + '</td>';
                            secondPreHtml += '<td style="color: #ffffff;" width="77">' + data[i].secondPrecipitation + '</td>';
                            lowTemHtml += '<td style="color: #ffffff;" width="77">' + data[i].temperatureMin + '</td>';
                            highTemHtml += '<td style="color: #ffffff;" width="77">' + data[i].temperatureMax + '</td>';
                        }
                        // rhHtml += '<td style="color: #ffffff;" width="77">' + data[i].relativeHumidity + '</td>';
                    }
                    html += '<table align="center">' + '<tr>' + timeHtml + '</tr><tr>' + firstWeaHtnl + '</tr><tr>' + firstPreHtml + '</tr><tr>' + highTemHtml + '</tr></table><div id="container" style="width: 600px;height:80px;background-color: #183B53"></div><table align="center" style="float:right;margin-top:8px;margin-left:10px;"><tr>' + lowTemHtml + '</tr><tr>' + secondWeaHtnl + '</tr><tr>' + secondPreHtml + '</tr></table>';
                    $('.expoerDiv').html(html);
                    $('.title').text("智能网格：" + name);
                    // $('.title').text(name);
                    //生成Highcharts直线图
                    Highcharts.chart('container', {
                        chart: {
                            type: 'line',
                            backgroundColor: '#133246',
                            margin: [0, 0, 24, 24],
                            marginLeft: 70
                        },
                        colors: ["#FF7F00", "#fff100"],
                        exporting: {
                            enabled: false
                        },
                        credits: {
                            enabled: false
                        },
                        title: {
                            text: ''
                        },
                        subtitle: {
                            text: ''
                        },
                        xAxis: {
                            categories: ['一', '二', '三', '四', '五', '六', '七'],
                            labels: {
                                enabled: false
                            },
                            // tickInterval:20,
                            lineWidth: 0,  //去掉x轴线
                            tickWidth: 0   //去掉刻度
                        },
                        yAxis: {
                            title: {
                                text: ''
                            },
                            labels: {
                                enabled: false
                            },
                            gridLineWidth: 0, //隐藏y轴刻度线
                            tickWidth: 0,
                            min: Math.min.apply(null, lowTem) * 0.5,
                            max: Math.max.apply(null, highTem) * 1.3
                        },
                        series: [{
                            showInLegend: false,
                            name: '高温',
                            data: highTem,
                            lineWidth: '0.1111rem',
                            y: 0,
                            dataLabels: {
                                // 开启数据标签
                                // enabled: true,
                                // useHTML: true,
                                // style: {
                                //     "fontSize": "12px",
                                //     "color": "#fff"
                                // }
                            },
                            // 关闭鼠标跟踪，对应的提示框、点击事件会失效
                            enableMouseTracking: false
                        }, {
                            showInLegend: false,
                            name: '低温',
                            data: lowTem,
                            lineWidth: '0.1111rem',
                            // 关闭鼠标跟踪，对应的提示框、点击事件会失效
                            enableMouseTracking: false,
                            dataLabels: {
                                // 开启数据标签
                                // enabled: true,
                                // useHTML: true,
                                // verticalAlign: "bottom",
                                // style: {
                                //     "fontSize": "12px",
                                //     "color": "#fff"
                                // },
                                // crop:false,
                                // overflow:'none',
                                y: 40 * $(window).width() / 1920
                            }
                        }]
                    });
                    $('#export-last').show();
                },
                error: function (a, b, c) {
                    layer.msg("无数据");
                }
            });
        }
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

            if (!$(".menu-tool li.contour").hasClass("on")) {
                // contourLayer.setVisibility(false);
            }
        }
    }

    //画边界单独站点
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


//分页查询
function decisionMakingQuery(pages) {
    var type = $(".fzjz-save li.z-on").attr("data-type");
    var beginTime = document.getElementById("beginTime").innerText;
    var endTime = document.getElementById("endTime").innerText;
    var name = $("#fileName").val();
    getReport(pages, beginTime, endTime, name, type);
}

//一周天气预报、天气气候公报、重要天气报告
function getReport(pages, beginTime, endTime, name, type) {
    $.ajax({
        type: "GET",
        url: "/sen/townJxh/getReport",
        data: {"beginTime": beginTime, "endTime": endTime, "name": name, "type": type, "pages": pages},
        dataType: "JSON",
        success: function (data) {
            //alert(data)
            var html = "";
            if(data.fileList!=null){
                for (var i = 0; i < data.fileList.length; i++) {
                    html += '<tr>' +
                        '<td>' + data.fileList[i].fileName + '</td>' +
                        '<td>'+data.fileList[i].time+'</td>'+
                        '<td><span data-path=' + data.fileList[i].path + '>+查看</span></td>' +
                        '</tr>'
                }
            }

            $("tbody#decisionMake").html(html);
            getHtmlPager(data, 'pager', 'report_pagerTpl_feedback');
        },
        error: function (e) {
            console.log(e);
        }
    })
}

// 分页方法
function getHtmlPager(data, htmlId, templateId, displaypages) {
    var pageDiv = null;
    if (data && data.page.totalItemCount > 0) {
        var start, end;
        var showPage = 5;
        if (displaypages && displaypages > 0)
            showPage = displaypages;
        var halfOfPagesToShowAtOnce = Math.floor(showPage / 2);
        start = data.page.pageNumber - halfOfPagesToShowAtOnce;
        if (start < 1)
            start = 1;
        if ((start + showPage) > data.page.pageCount)
            end = data.page.pageCount;
        else
            end = start + showPage;
        data.page.Pages = [];
        for (var i = start; i <= end; i++) {
            data.page.Pages.push({
                pageNumber: i,
                Selected: i === data.page.pageNumber
            });
        }
        pageDiv = $("#" + htmlId);
        pageDiv.empty();
        pageDiv.html($("#" + templateId).render(data.page));
    } else {
        data.page.Pages = [];

        data.page.Pages.push({
            PageNumber: 1,
            Selected: 1 === data.page.pageNumber
        });

        pageDiv = $("#" + htmlId);
        pageDiv.empty();
        pageDiv.html($("#" + templateId).render(data.page));
    }
}




