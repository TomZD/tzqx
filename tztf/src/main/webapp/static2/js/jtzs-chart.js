var timespace = 5;//
var timeinter = 60 / timespace;
var myChart;
var myChart_bar;
var myChart_zd;


var now = new Date();
var year = now.getFullYear();
var month = now.getMonth() + 1;
var day = now.getDate();
var hours = now.getHours();
var minutes = now.getMinutes();

if (minutes < 10) minutes = '0' + minutes;

//  var timetitle = "" + year + "年" + (month + 1) + "月" + day + "日 " + hours + ":" + minutes;
var timetitle = "" + year + "-" + month + "-" + day + "";
var timelasttitle = getLasttoday(now, 7);

//时间数组
var x_Array = [];
var y_Array = [];
var y_Array_bar = [];
var y_Array_ZD = [];
var x_Array_bar = ['Z1', 'Z2', 'H2', 'H1', 'H3', 'H4', 'Z4', 'Z3', 'J1', 'H5'];
var ksl_pm;
for (var i = 0; i <= (20 * timeinter); i++) {
    var value = (i + 48);
    var ys = value % (60 / timespace);

    var showys = "00";
    if (ys == 0) {
        showys = "00";
    } else {
        showys = ys * timespace;
        if (showys < 10) showys = "0" + showys;
    }

    var lasttime = Math.floor(value / (60 / timespace)) + ':' + showys;
    x_Array.push(lasttime);

    y_Array.push(null);
}
//总体指标
var myOption = {
    title: {
        show: false,
        padding: [5, 5],
        text: '快速路拥堵指数   ' + timetitle,
        left: 'left',
        textStyle: {
            fontSize: 16,
            fontFamily: 'Microsoft YaHei'
        }
    },
    grid: {
        top: '20',
        left: '35',
        right: '25',
        bottom: '60'
    },
    tooltip: {
        trigger: 'axis',
        formatter: function (params) {
            var params0 = params[0];
            var params1 = params[1];
            if (params0.value) {
                if (params1.value) {
                    //return '上周 ' + params0.name + ' 指数 ' + params0.value + "<br />今日 " + params1.name + ' 指数 ' + params1.value;
                    return '今日 ' + params1.name + ' 指数 ' + params1.value + "<br />上周 " + params0.name + ' 指数 ' + params0.value;
                } else {
                    return '上周 ' + params0.name + ' 指数 ' + params0.value;
                }
            } else {
                return '今日 ' + params1.name + ' 指数 ' + params1.value;
            }
        },
        axisPointer: {
            animation: false
        }
    },
    legend: {
        show: true,
        bottom: 10,
        data: ['今日（' + timetitle + '）', '上周同期（' + timelasttitle + '）']
    },
    toolbox: {
        show: false,
        orient: 'vertical',
        x: 'top',
        y: 'center',
        feature: {
            mark: { show: false },
            dataView: { show: false },
            magicType: { show: true, type: ['line', 'bar'] },
            restore: { show: true },
            saveAsImage: { show: true }
        }
    },
    xAxis:
        {
            type: 'category',
            position: 'bottom',
            boundaryGap: true,
            axisLine: {
                // 轴线
                show: false,
                lineStyle: {
                    color: 'green',
                    type: 'solid',
                    width: 1
                }
            },
            axisTick: {
                // 轴标记
                show: true,
                length: 6,
                lineStyle: {
                    color: 'red',
                    type: 'solid',
                    width: 1
                }
            },
            axisLabel: {
                show: true,
                interval: 47, // {number}
                rotate: 0,
                margin: 8,
                //formatter: '{value}月',
                textStyle: { color: '#000' }
            },
            splitLine: {
                show: false,
                lineStyle: {
                    color: '#483d8b',
                    type: 'dashed',
                    width: 1
                }
            },
            data: x_Array
        },
    yAxis:
        {
            type: 'value',
            position: 'left',
            min: 0,
            max: 10,
            splitNumber: 5,
            // boundaryGap: [0, 0.1],
            axisLine: {
                // 轴线
                show: false,
                lineStyle: {
                    color: 'red',
                    type: 'solid',
                    width: 1
                }
            },
            axisTick: {
                // 轴标记
                show: true,
                length: 6,
                lineStyle: {
                    color: 'green',
                    type: 'solid',
                    width: 1
                }
            },
            axisLabel: {
                show: true,
                interval: 'auto', // {number}

                margin: 8,

                textStyle: {
                    color: '#1e90ff',
                    fontFamily: 'verdana',
                    fontSize: 12,
                    fontStyle: 'normal',
                    fontWeight: 'bold'
                }
            },
            splitLine: {
                show: false,
                lineStyle: {
                    color: '#483d8b',
                    type: 'dotted',
                    width: 1
                }
            },
            splitArea: {
                show: true,
                areaStyle: {
                    color: ['rgba(56,168,0,1)', 'rgba(139,209,0,1)', 'rgba(255,255,0,1)', 'rgba(255,128,0,1)', 'rgba(255,0,0,1)']
                }
            }
        },
    series: [
        {
            name: '上周同期（' + timelasttitle + '）',
            type: 'line',

            itemStyle: {
                normal: { color: '#456646' }
            },
            data: y_Array
        }, {
            name: '今日（' + timetitle + '）',
            type: 'line',
            label: {
                normal: {
                    textStyle: { color: '#000000' }
                }
            },
            itemStyle: {
                normal: { color: '#456646' }
            },
            data: y_Array
        }
    ]
};
//快速路排名
var myOption_KSL = {
    title: {
        text: '快速路拥堵路段排名   ' + timetitle
    },
    tooltip: {
        trigger: 'axis',
        formatter: function (params) {

            params = params[0];
            var curindex = params.dataIndex;
            if (ksl_pm) {
                var curinfo = ksl_pm[curindex];
                if (curinfo) {
                    var rhtml = "长度：" + (curinfo.line_length / 1000).toFixed(2) + " km <br />";
                    rhtml += "拥堵里程：" + (curinfo.ydlength / 1000).toFixed(2) + " km <br />";
                    rhtml += "平均速度：" + curinfo.averagev + " km/h <br />";
                    rhtml += "通行时间：" + MillisecondToDate(curinfo.sumtime * 3600000) + " <br />";
                    return rhtml;
                } else {
                    if (params.value) {
                        return '' + params.name + ' : ' + params.value;
                    } else {
                        return '' + params.name + ' : -';
                    }
                }
            } else {
                if (params.value) {
                    return '' + params.name + ' : ' + params.value;
                } else {
                    return '' + params.name + ' : -';
                }
            }
        },
        axisPointer: {
            type: 'shadow'
        }
    },
    legend: {
        show: true,
        bottom: 0,
        data: ['通行速度拥堵前十排名']
    },
    grid: {
        left: '0',
        right: '20',
        bottom: '10',
        containLabel: true
    },
    toolbox: {
        show: false,
        orient: 'vertical',
        x: 'top',
        y: 'center',
        feature: {
            mark: { show: false },
            dataView: { show: false },
            magicType: { show: true, type: ['line', 'bar'] },
            restore: { show: true },
            saveAsImage: { show: true }
        }
    },
    xAxis:
        {
            show: false,
            type: 'value',
            boundaryGap: [0, 0.01],
            //min: 0,
            //max: 10,
            splitNumber: 5,
            textStyle: { color: '#000' }
        },
    yAxis:
        {
            type: 'category',
            data: ['10', '9', '8', '7', '6', '5', '4', '3', '2', '1']
        },
    series: [
        {
            name: '通行速度拥堵前十排名',
            label: {
                normal: {
                    show: true,
                    position: 'insideLeft',
                    formatter: function (params) {
                        var curindex = params.dataIndex;
                        if (ksl_pm) {
                            var curinfo = ksl_pm[curindex];
                            if (curinfo) {
                                return curinfo.line_name;
                            } else
                                return params.value;
                        }
                        else
                            return params.value;
                    }
                }
            },
            type: 'bar',
            data: [1.8203, 2.3489, 2.9034, 9.4970, 8.1744, 6.30230, 2.3489, 2.9034, 9.4970, 8.1744]
        }
    ]
};

var myOption_BAR = {
    title: {
        show: false,
        padding: [5, 5],
        text: '各条快速路对比',
        left: 'left',
        textStyle: {
            fontSize: 16,
            fontWeight: 700,
            fontFamily: 'Microsoft YaHei'
        }
    },
    tooltip: {
        trigger: 'axis',
        axisPointer: {
            type: 'shadow'
        }
    },
    legend: {
        show: true,
        bottom: 0,
        selectedMode: 'single',
        x: 'center',
        inactiveColor: 'gray',
        data: ['平均车速(KM/H)', '拥堵路段长度(KM)'],
        selected: {
            '平均车速(KM/H)': true, '拥堵路段长度(KM)': false
        }
    },
    grid: {
        top: '20',
        left: '35',
        right: '25',
        bottom: '115'
    },
    toolbox: {
        show: false,
        orient: 'vertical',
        x: 'top',
        y: 'center',
        feature: {
            mark: { show: false },
            dataView: { show: false },
            magicType: { show: true, type: ['line', 'bar'] },
            restore: { show: true },
            saveAsImage: { show: true }
        }
    },
    yAxis:
        {
            show: true,
            type: 'value',
            boundaryGap: [0, 0.01],
            min: 0,
            max: 80,
            splitNumber: 5,
            axisTick: {
                // 轴标记
                show: true,
                length: 6,
                lineStyle: {
                    color: 'green',
                    type: 'solid',
                    width: 1
                }
            },
            axisLine: {
                // 轴线
                show: false,
                lineStyle: {
                    color: 'red',
                    type: 'solid',
                    width: 1
                }
            },
            axisLabel: {
                show: true,
                interval: 'auto', // {number}

                margin: 8,

                textStyle: {
                    color: '#1e90ff',
                    fontFamily: 'verdana',
                    fontSize: 12,
                    fontStyle: 'normal',
                    fontWeight: 'bold'
                }
            }
        },
    xAxis:
        {
            type: 'category',
            data: ['中河上塘高架', '秋石高架', '德胜高架', '留石高架', '天目环北艮山快速路', '彩虹快速路', '紫之快速路', '东湖通城快速路', '秋涛南路高架', '机场快速路'],
            axisLabel: {
                show: true,
                interval: 0, // {number}
                rotate: 45,
                margin: 8,
                textStyle: { color: '#000' }
                //formatter: '{value}月',

            }, axisLine: {
                // 轴线
                show: false,
                lineStyle: {
                    color: 'green',
                    type: 'solid',
                    width: 1
                }
            },
            axisTick: {
                // 轴标记
                show: true,
                length: 6,
                lineStyle: {
                    color: 'red',
                    type: 'solid',
                    width: 1
                }
            },
            splitLine: {
                show: true,
                lineStyle: {
                    color: '#ccc',
                    type: 'solid',
                    width: 1
                }
            }
        },
    series: [
        {
            name: '平均车速(KM/H)',
            type: 'bar',
            itemStyle: {
                normal: { color: '#007eb8' }
            },
            data: [2.0, 4.9, 7.0, 8.2, 10.6, 7.7, 13.6, 12.2, 16.6, 18.0],
            barWidth: 20,
            markPoint: {
                data: [
                    { type: 'min', name: '最小值' }
                ]
            }
        },
        {
            name: '拥堵路段长度(KM)',
            type: 'bar',
            itemStyle: {
                normal: { color: '#001852' }
            },
            barWidth: 20,
            data: [2.0, 4.9, 7.0, 8.2, 10.6, 7.7, 13.6, 12.2, 16.6, 18.0]
        }
    ]
};

$(document).ready(function () {

    myChart = echarts.init(document.getElementById('chart'), 'helianthus');
    myChart.setOption(myOption);

    myChart_bar = echarts.init(document.getElementById('chart_bar'), 'roma');
    myChart_bar.setOption(myOption_BAR);

    myChart_bar.on('legendselectchanged', function (params) {
        if (params.name == "平均车速(KM/H)") {
            myChart_bar.setOption({
                yAxis: {
                    min: 0,
                    max: 80,
                    splitNumber: 5
                }
            });
        } else {
            myChart_bar.setOption({
                yAxis: {
                    min: 0,
                    max: 15,
                    splitNumber: 5
                }
            });
        }
    });
    createZB(true);

    //隔一段时间执行
//       setInterval("startRequest()", 50000);
//       setInterval("startRequest_map()", 50000);
});

function createZB(firsttime) {

    if (firsttime) {
        myChart_bar.showLoading();
    }

    //获取数据
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/dd/weather/jtzsKsJson",
        data: "{}",
        dataType: 'json',
        error: function () {
            myChart.showLoading();
            myChart_bar.showLoading();
        },
        success: function (result) {
            // console.log(result.d);
            var josntext = eval('(' + result.d + ')');

            //获取指标情况
            if (josntext.totallength) {
                $("#zlc").html(josntext.totallength.toFixed(1) + " km");
            }
            if (josntext.averagev) {
                $("#pjcs").html(josntext.averagev.toFixed(1) + " km/h");
            }
            if (josntext.ydzs) {
                $("#ydzs").html((josntext.ydzs).toFixed(2) + " ");
            }
            //if (josntext.ydtotallength) {
            //    $("#ydcd").html(josntext.ydtotallength.toFixed(1) + " km");
            //}
            var charttitle = '';
            if (josntext.lasttime) {
                charttitle = josntext.lasttime;
            }

            //总体指标折线图
            y_Array = josntext.expresswayydzs;
            var numnul = 20 * timeinter - y_Array.length;

            if (y_Array.length < (20 * timeinter)) {
                for (var i = 0; i < numnul; i++) {
                    y_Array.push(null);
                }
            }

            y_Array.push(null);

            var y_Array2 = josntext.pageonebefore;
            var numnul2 = 20 * timeinter - y_Array2.length;
            if (y_Array2.length < (20 * timeinter)) {
                for (var i = 0; i < numnul2; i++) {
                    y_Array2.push(null);
                }
            }

            myChart.setOption({
                title: {
                    show: false,
                    text: '快速路拥堵指数   ' + charttitle
                },
                xAxis: {
                    data: x_Array
                },
                series: [{
                    name: '上周同期（' + timelasttitle + '）',
                    type: 'line',
                    lineStyle: {
                        normal: { width: 2 }
                    },
                    itemStyle: {
                        normal: { color: '#456646' }
                    },

                    data: y_Array2
                }, {
                    name: '今日（' + timetitle + '）',
                    type: 'line',
                    lineStyle: {
                        normal: { width: 2 }
                    },
                    itemStyle: {
                        normal: { color: '#01ffff' }
                    },
                    markPoint: {
                        label: {
                            normal: {
                                textStyle: { color: '#000000' }
                            }
                        },
                        data: [
                            { type: 'max', name: '最大值' }
                        ]
                    },
                    data: y_Array
                }]
            });

            myChart.hideLoading();

            $("#mypietitle").html('数据更新：   ' + charttitle);
        }
    });

    //获取数据
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/dd/weather/jtzsKsPmJson",
        data: "{}",
        dataType: 'json',
        success: function (result) {
            // console.log(result.d);
            var josntext = eval('(' + result.d + ')');

            //快速路排名
            y_Array_bar = new Array();
            ksl_pm = new Array();
            var arrayksl = josntext;
            var ydcd_sum = 0;

            var maxpmNum = 0;
            for (var j = 0, ll = x_Array_bar.length; j < ll; j++) {
                y_Array_bar[j] = null;
                ksl_pm[j] = null;
                //遍历获取值
                for (var i = 0, l = arrayksl.length; i < l; i++) {
                    if (arrayksl[i].line_id == x_Array_bar[j]) {
                        y_Array_bar[j] = arrayksl[i].averagev;
                        ksl_pm[j] = arrayksl[i].ydlength;
                        ydcd_sum += parseFloat(arrayksl[i].ydlength);
                        if (arrayksl[i].ydlength > maxpmNum) { maxpmNum = arrayksl[i].ydlength; }
                        break;
                    }
                }
            }

            $("#ydcd").html(ydcd_sum.toFixed(1) + " km");

            if (maxpmNum < 2) {
                myChart_bar.setOption({
                    series: [
                        {
                            name: '平均车速(KM/H)',
                            data: y_Array_bar
                        }, {
                            name: '拥堵路段长度(KM)',
                            data: ksl_pm,
                            markPoint: {
                                data: [
                                ]
                            }
                        }
                    ]
                });
            } else {
                myChart_bar.setOption({
                    series: [{
                        name: '平均车速(KM/H)',
                        data: y_Array_bar
                    }, {
                        name: '拥堵路段长度(KM)',
                        data: ksl_pm,
                        markPoint: {
                            data: [
                                { type: 'max', name: '最大值' }
                            ]
                        }
                    }]
                });
            }

            myChart_bar.hideLoading();
        }
    });

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
    var titlestr=$(".maptitle").html();
    console.log(titlestr);
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
// 	      	var textstr=item.Name+item.Value; //摄氏度或其他单位，可通过参数判断
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
// 		    	typetag+="级（米/秒）";
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
// 	  		console.log(thisHtml);
        var thisText=$(this).text();
// 	  		console.log(thisText);
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

var windFieldImgLayer;
function drawWindFieldImg(windData) {
    //加载风杆数据，有风杆图层，先清除风杆图层
    if(windFieldImgLayer){
        windFieldImgLayer.destroy();
        windFieldImgLayer=undefined;
    }
    var layer_style = {
        fill: false,
        graphicName: "square",
        pointRadius: 30,
        strokeWidth: 0,
        fillOpacity: 1,
        backgroundWidth: 30,
        backgroundHeight: 30,
        cursor: "default",
        backgroundGraphic: 'data:image/png;base64,'
    };
    windFieldImgLayer = windFieldImg("windField", windData, layer_style, [-9999]);
    if(windFieldImgLayer){
        gis.map.addLayer(windFieldImgLayer);
    }
}