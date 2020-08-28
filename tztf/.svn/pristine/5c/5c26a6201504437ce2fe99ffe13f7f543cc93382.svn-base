define(function (require) {
    var gis, markerlayer, onMouseover, onClick, bFilter, list = {};
    /**
     * 加载站点
     * @param  {array}  data  标注信息
     * @param  {string} type  类型
     */
    var add = function (data, type, popup_name, icon, isShow,isRecord) {
        var arr = [];
        //创建标注层
        if (!markerlayer) {
            markerlayer = gis.addMarkers();
        }

        //获取icon信息
        var w = icon.width,
            h = icon.height,
            url = icon.url;

        //加载点
        $.each(data, function (i, n) {
        	if(n===null || n===undefined)
        		n = {};
            n.popup_name = popup_name;
            //var str;
            //if (type == "地质灾害点") {
            //    icon.url = url.replace(/{value}/, n.Level + 1);
            //} else {
            //    icon.url = url;
            //}
            if (icon.condition && icon.meteThresholds) {
                var l1 = icon.meteThresholds.split('|')[0] - 0,
                    l2 = icon.meteThresholds.split('|')[1] - 0,
                    l3 = icon.meteThresholds.split('|')[2] - 0,
                    l4 = icon.meteThresholds.split('|')[3] - 0;
                if (icon.condition == ">=") {
                    if (n.Value >= l4) {
                        icon.url = url.replace(/{qxzd}/, "气象站点-红色.png");
                    } else if (n.Value >= l3) {
                        icon.url = url.replace(/{qxzd}/, "气象站点-橙色.png");
                    } else if (n.Value >= l2) {
                        icon.url = url.replace(/{qxzd}/, "气象站点-黄色.png");
                    } else if (n.Value >= l1) {
                        icon.url = url.replace(/{qxzd}/, "气象站点-蓝色.png");
                    } else {
                        icon.url = url.replace(/{qxzd}/, "气象站点.png");
                    }
                } else if (icon.condition == "<=") {
                    if (n.Value <= l4) {
                        icon.url = url.replace(/{qxzd}/, "气象站点-红色.png");
                    } else if (n.Value <= l3) {
                        icon.url = url.replace(/{qxzd}/, "气象站点-橙色.png");
                    } else if (n.Value <= l2) {
                        icon.url = url.replace(/{qxzd}/, "气象站点-黄色.png");
                    } else if (n.Value <= l1) {
                        icon.url = url.replace(/{qxzd}/, "气象站点-蓝色.png");
                    } else {
                        icon.url = url.replace(/{qxzd}/, "气象站点.png");
                    }
                }
            }

            var txt = n.Value;
            if (!isShow || txt == undefined) {
                txt = "";
            }
            var marker = gis.addMarker({
                //url: str,
                //width: w,
                //height: h,
                icon: icon,
                lon: n.Lon || n.Lontitude || n.Longitude || n.longtitude,
                lat: n.Lat || n.Latitude || n.latitude,
                offsetX: -w / 2,
                offsetY: -h / 2,
                position: "br",
                content: n,
                text: "<div class='popup-txt'>" + txt + "</div>"
            }, markerlayer)[0];

            marker.events.register("mouseover", marker, onMouseover);
            marker.events.register("click", marker, onClick);
            //$(".text").html("根据20时以来累计降水统计：面雨量超过25毫米的乡镇共6个，受影响面积为179.68平方公里，受影响人口5.8万人；受影响区域内有4个水库实时水位超过警戒水位，0个农业点，2个养殖基地，1个林区，5个水产基地，1个地质灾害隐患点，5个小流域有山洪风险；该区域内共有14个救灾物资存放点，2个避灾点.<br/>&emsp;&emsp;根据未来1小降水预报统计：面雨量超过10毫米的乡镇1个，受影响面积30.45平方公里，受影响人口1.2万人；受影响区域内有0个水库水位超过警戒水位，1个农业点，1个养殖基地，0个林区，0个水产基地，2个地质隐患点，1个小流域有山洪风险。");
            arr.push(marker);
            if(isRecord){//允许标点记录时
             mapMarkerArr.push(marker);   
            }else{
               mapMarkerArr=[];//清空记录 
            }   
        });
        gis.map.mapMarkers=mapMarkerArr;

        //是否过滤
        if (bFilter) {
            filter(bFilter);
        }

        list[type] = arr;
    };
    var addNew = function (data, type, popup_name, icon) {
        var arr = [];

        //创建标注层
        if (!markerlayer) {
            markerlayer = gis.addMarkers();
        }

        //获取icon信息
        var w = icon.width,
            h = icon.height,
            url = icon.url;

        //加载点
        $.each(data, function (i, n) {
            n.popup_name = popup_name;
            var str;
            if (type == "地质灾害点") {
                str = url.replace(/{value}/, n.Level + 1);
            } else {
                str = url;
            }

            var marker = gis.addMarker({
                url: str,
                width: w,
                height: h,
                lon: n.Lon || n.Lontitude || n.Longitude,
                lat: n.Lat || n.Latitude,
                offsetX: -w / 2,
                offsetY: -h / 2,
                content: n
            }, markerlayer)[0];
            if (n.Method)
                marker.events.register("click", marker, function () {
                    $(".text").html(n.Text);
                });
            else
                marker.events.register("click", marker, onMouseover);

            arr.push(marker);
        });

        //是否过滤
        if (bFilter) {
            filter(bFilter);
        }

        list[type] = arr;
    };

    var addLightningPoints = function (data, type, popup_name, icon) {
        var arr = [];

        //创建标注层
        if (!markerlayer) {
            markerlayer = gis.addMarkers();
        }

        //获取icon信息
        var w = icon.width,
            h = icon.height,
            url = icon.url;
        //加载点
        $.each(data, function (i, n) {
            n.popup_name = popup_name;
            if (n.Value == -1) {
                icon.url = url.replace(/{value}/, "负闪");
            } else if (n.Value) {
                icon.url = url.replace(/{value}/, "正闪");
            }

            var marker = gis.addMarker({
                icon: icon,
                lon: n.Lon || n.Lontitude || n.Longitude,
                lat: n.Lat || n.Latitude,
                offsetX: -w / 2,
                offsetY: -h / 2,
                position: "br",
                content: n
                //, //text: "<div class='popup-txt'>" + txt + "</div>"
            }, markerlayer)[0];
            //if (n.Method)
            //    marker.events.register("click", marker, function () {
            //        $(".text").html(n.Text);
            //    });
            //else
            //    marker.events.register("click", marker, onMouseover);

            arr.push(marker);
        });

        //是否过滤
        if (bFilter) {
            filter(bFilter);
        }

        list[type] = arr;
    };
    /* 清除站点 */
    var clear = function (type) {
        if (!markerlayer) return;

        var markers;
        if (type) {
            markers = list[type] || [];
            list[type] = null;
            $.each(markers, function (i, n) {
                markerlayer.removeMarker(n);
            });
        } else {
            markers = markerlayer.markers;
            markerlayer.clearMarkers();
            list = {};
        }
    };

    /**
     * 过滤站点
     */
    var filter = function (bl) {
        bFilter = bl;

        if (!markerlayer) return;

        $.each(markerlayer.markers, function (i, n) {
            if (!n.content.Select) {
                if (bFilter) {
                    n.display(false);
                } else {
                    n.display(true);
                }
            }
        });
    };
    var showOrHide = function (isTrue) {
        var markers = list["micaps4Station"] || [];
        if (isTrue) {
            markerlayer.display(true);
        } else {
            markerlayer.display(false);
        }
    }
    return function (g, ev, ev_click) {
        gis = g;
        onMouseover = ev;
        onClick = ev_click;
        return {
            add: add,
            addNew: addNew,
            addLightningPoints: addLightningPoints,//闪电定位 作 "+" "-"
            clear: clear,
            filter: filter,
            showOrHide: showOrHide
        };
    };
});
