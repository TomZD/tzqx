
var _hmt = _hmt || [];
(function () {
    var hm = document.createElement("script");
    hm.src = "https://hm.baidu.com/hm.js?dbefcf76a010564778622024d9886be6";
    var s = document.getElementsByTagName("script")[0];
    s.parentNode.insertBefore(hm, s);
})();


function myformatter(date) {
    var y = date.getFullYear();
    var m = date.getMonth() + 1;
    var d = date.getDate();
    return y + '-' + (m < 10 ? ('0' + m) : m) + '-' + (d < 10 ? ('0' + d) : d);
}

function myformatterAll(datetime) {
    var year = datetime.getFullYear();
    var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
    var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
    var hour = datetime.getHours() < 10 ? "0" + datetime.getHours() : datetime.getHours();
    var minute = datetime.getMinutes() < 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
    var second = datetime.getSeconds() < 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();
    return year + "" + month + "" + date + "" + hour + "" + minute;
}

function myparser(s) {
    if (!s) return new Date();
    var ss = (s.split('-'));
    var y = parseInt(ss[0], 10);
    var m = parseInt(ss[1], 10);
    var d = parseInt(ss[2], 10);
    var H = parseInt(ss[3], 10);
    var mi = parseInt(ss[4], 10);
    if (!isNaN(y) && !isNaN(m) && !isNaN(d)) {
        return new Date(y, m - 1, d, H, mi);
    } else {
        return new Date();
    }
}

function getLasttoday(date, intday) {
    var yesterday_milliseconds = date.getTime() - 1000 * 60 * 60 * 24 * intday;
    var yesterday = new Date();
    yesterday.setTime(yesterday_milliseconds);

    var strYear = yesterday.getFullYear();
    var strDay = yesterday.getDate();
    var strMonth = yesterday.getMonth() + 1;
    if (strMonth < 10) {
        strMonth = "0" + strMonth;
    }
    datastr = strYear + "-" + strMonth + "-" + strDay;
    return datastr;
}

var weatherClass = { "\u6674": "icon-qing", "\u591a\u4e91": "icon-duoyun", "\u9634": "icon-yintian", "\u96fe": "icon-wu", "\u51b0\u96f9": "icon-bingbao", "\u626c\u6c99": "icon-shachen", "\u6c99\u5c18": "icon-shachen", "\u6d6e\u5c18": "icon-shachen", "\u973e": "icon-shachen", "\u5927\u96e8-\u66b4\u96e8": "icon-dayu", "\u96f7\u9635\u96e8": "icon-leizhenyu", "\u9635\u96e8": "icon-zhenyu", "\u96e8": "icon-xiaoyu", "\u5c0f\u96e8": "icon-xiaoyu", "\u4e2d\u96e8": "icon-zhongyu", "\u5927\u96e8": "icon-dayu", "\u66b4\u96e8": "icon-baoyu", "\u96e8\u5939\u96ea": "icon-yujiaxue", "\u9635\u96ea": "icon-chenxue", "\u5c0f\u96ea": "icon-xiaoxue", "\u96ea": "icon-xiaoxue", "\u4e2d\u96ea": "icon-zhongxue", "\u5927\u96ea": "icon-daxue", "\u66b4\u96ea": "icon-baoxue", "\u9635\u96ea": "icon-baoyu" };

function getweatherdata() {
    $.ajax({
        type: "get",
        cache: "false",
        url: "ashx/getWeather.ashx",
        dataType: "json",
        error: function () {
            $("#weather").html("");
        },
        success: function (json) {
            //var t = '('+json+')';

            //var result = json.weather;;
            //var getinfo = "";

            //var info = result.realtime.weather.info;
            //var wind = result.realtime.wind.direct;
            //var temperature = result.realtime.weather.temperature;

            //var classname = '';
            //$.each(weatherClass, function (key, value) {
            //    if (key == info) {
            //        classname = value;
            //    }
            //});

            //var html = '<p class="icon-tu"><i class="' + classname + '"></i></p><p class="otherinfo"><span class="weathertitle">' + info + '</span><br><span>' + temperature + '°</span><br>' + wind + '</p>';

            var result = json.weather;;
            var getinfo = "";
            var json_data = JSON.parse(result);
            var info = json_data.weather0;
            var wind = json_data.wind0;
            var temperature = json_data.temp0;

            var imageurl = json_data.pic01;

            var html = "<a class='weather-item' title='" + info + "'><img alt='天气' src='" + imageurl + "'><span class='wtext'>" + temperature + "</span> </a>";

            //html = '<p class="icon-tu"><img alt="天气" src=" ' + imageurl + '"></p><p class="otherinfo"><span class="weathertitle">' + info + '</span><br><span>' + temperature + '</span></p>';

            $("#myweather").html(html);
        }
    });
}

function getlayer(name, map) {
    for (var i = 0, len = map.graphicsLayerIds.length; i < len; i++) {
        var layer = map.getLayer(map.graphicsLayerIds[i]);
        if (layer.label == name) {
            return layer;
        }
    }

    return null;
};

function refreshmap() {
    for (var i = 0, len = map.graphicsLayerIds.length; i < len; i++) {
        var layer = map.getLayer(map.graphicsLayerIds[i]);
        layer.refresh();
    }
}

function refreshmap2(map) {
    for (var i = 0, len = map.graphicsLayerIds.length; i < len; i++) {
        var layer = map.getLayer(map.graphicsLayerIds[i]);
        layer.refresh();
    }
}



function MillisecondToDate(msd) {
    var time = parseFloat(msd) / 1000;
    if (null != time && "" != time) {
        if (time > 60 && time < 60 * 60) {
            time = parseInt(time / 60.0) + "分钟" + parseInt((parseFloat(time / 60.0) -
            parseInt(time / 60.0)) * 60) + "秒";
        } else if (time >= 60 * 60 && time < 60 * 60 * 24) {
            time = parseInt(time / 3600.0) + "小时" + parseInt((parseFloat(time / 3600.0) -
            parseInt(time / 3600.0)) * 60) + "分钟" +
            parseInt((parseFloat((parseFloat(time / 3600.0) - parseInt(time / 3600.0)) * 60) -
            parseInt((parseFloat(time / 3600.0) - parseInt(time / 3600.0)) * 60)) * 60) + "秒";
        } else {
            time = parseInt(time) + "秒";
        }
    } else {
        time = "0 时 0 分0 秒";
    }
    return time;

}



/**********kindeditor富文本编辑控件**********/
function KeShow(editID, formID) {
    KE.show({
        id: editID,
        allowFileManager: true,
        imageUploadJson: '/Handlers/upload_json.ashx',
        fileManagerJson: '../Handlers/file_manager_json.ashx',
        afterCreate: function (id) {
            KE.event.ctrl(KE.g[id].iframeDoc, 13, function () {
                KE.util.setData(id);
                document.forms[formID].submit();
            });
        }
    });
}

function closeAsyncbox(boxId) {
    $.close(boxId);
}

/*******************AsyncBox弹出窗口************************/
function AsyncBox_Adapter(id, url, width, height, title) {
    $.close(id);
    asyncbox.open({
        id: id,
        url: url,
        width: width,
        height: height,
        title: title,
        btnsbar: $.btn.OKCANCEL,
        callback: function (action, iframe) {
            if (action == "ok") {
                iframe.autoSubmit();
                return false;
            }
        }
    })
}

/*******************AsyncBox弹出窗口************************/
function AsyncBox_AdapterSaveWithDelete(id, url, width, height, title) {
    $.close(id);
    asyncbox.open({
        id: id,
        url: url,
        width: width,
        height: height,
        title: title,
        btnsbar: [
           {
               text: '保&nbsp;&nbsp;&nbsp;存',
               action: 'ok'
           },
          {
              text: '删&nbsp;&nbsp;&nbsp;除', action: 'Delete'
          },
          {
              text: '取&nbsp;&nbsp;&nbsp;消', action: 'cancel'
          }
        ],
        callback: function (action, iframe) {
            if (action == "ok") {
                iframe.autoSubmit();
                return false;
            }
            if (action == "Delete") {
                iframe.DeleteRecord();
                return false;
            }
        }
    })
}
/*******************AsyncBox弹出窗口************************/
function AsyncBox_AdapterDeleteWithClose(id, url, width, height, title) {
    $.close(id);
    asyncbox.open({
        id: id,
        url: url,
        width: width,
        height: height,
        title: title,
        btnsbar: [
          {
              text: '删&nbsp;&nbsp;&nbsp;除', action: 'Delete'
          },
          {
              text: '关&nbsp;&nbsp;&nbsp;闭', action: 'cancel'
          }
        ],
        callback: function (action, iframe) {
            if (action == "Delete") {
                iframe.DeleteRecord();
                return false;
            }
        }
    })
}
/*******************AsyncBox弹出窗口 没有按钮************************/
function AsyncBox_AdapterNoButton(id, url, width, height, title) {
    $.close(id);
    asyncbox.open({
        id: id,
        url: url,
        width: width,
        height: height,
        title: title
    })
}
//只有一个关闭按钮供查看使用
function AsyncBox_AdapterClose(id, url, width, height, title) {
    asyncbox.open({
        id: id,
        url: url,
        width: width,
        height: height,
        title: title,
        btnsbar: [{
            text: '关  闭',
            action: ''
        }]
    })
}

$(document).ready(function () {
    $("#pageSize").val($("#hidPageSize").val());
});

function _PagePostSubmit(href) {
    document.forms[0].action = href;
    document.forms[0].submit();
}




function AsyncBox_YesOrNo(id, url, backurl, width, height) {
    $.close(id);
    asyncbox.open({
        id: id,
        url: url,
        width: width,
        height: height,
        title: "提示",
        btnsbar: [
          {
              text: '是', action: 'yes'
          },
          {
              text: '否', action: 'no'
          }
        ],
        callback: function (action) {
            if (action == "yes") {
                window.location.reload(true);
            }
            if (action == "no") {
                window.location.href = backurl;
            }
        }
    })
}