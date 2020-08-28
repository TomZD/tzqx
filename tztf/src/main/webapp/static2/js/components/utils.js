define('components/utils.js', function(require, exports, module){ /**
 * 字符串转xml
 * @param  {string} xmlString xml字符串
 * @return {object}           xml对象
 */
var string2XML = function(xmlString) {
    var xmlDoc = null;
    //支持IE浏览器 
    if (!window.DOMParser && window.ActiveXObject) { //window.DOMParser 判断是否是非ie浏览器
        var xmlDomVersions = ['MSXML.2.DOMDocument.6.0', 'MSXML.2.DOMDocument.3.0', 'Microsoft.XMLDOM'];
        for (var i = 0; i < xmlDomVersions.length; i++) {
            try {
                xmlDoc = new ActiveXObject(xmlDomVersions[i]);
                xmlDoc.async = false;
                xmlDoc.loadXML(xmlString); //loadXML方法载入xml字符串
                break;
            } catch (e) {}
        }
    }
    //支持Mozilla浏览器
    else if (window.DOMParser && document.implementation && document.implementation.createDocument) {
        try {
            /* DOMParser 对象解析 XML 文本并返回一个 XML Document 对象。
             * 要使用 DOMParser，使用不带参数的构造函数来实例化它，然后调用其 parseFromString() 方法
             * parseFromString(text, contentType) 参数text:要解析的 XML 标记 参数contentType文本的内容类型
             * 可能是 "text/xml" 、"application/xml" 或 "application/xhtml+xml" 中的一个。注意，不支持 "text/html"。
             */
            domParser = new DOMParser();
            xmlDoc = domParser.parseFromString(xmlString, 'text/xml');
        } catch (e) {}
    } else {
        return null;
    }

    return xmlDoc;
};

/**
 * 获取8向风图标url
 * @param  {string} windd 风向
 * @return {string}       风向图标url
 */
var getUrlByWindd = (function() {
    var arr = ["西北", "东北", "西南", "东南", "西", "东", "北", "南"]; //从自多的开始匹配
    return function(windd) {
        var index;
        $.each(arr, function(i, n) {
            if (windd.indexOf(n) >= 0) {
                index = 8 - i;
                return false;
            }
        });
        return "/static/images/ico-wind/f" + index + ".png";
    };
}());
/**
 * 风速转风力
 * @param  {number} windSpeed 风速
 * @param  {number} windPower 风力
 */
var windSpeed2Power = (function() {
    var arr = [0.2, 1.5, 3.3, 5.4, 7.9, 10.7, 13.8, 17.1, 20.7, 24.4, 28.4, 32.6, 36.9];
    return function(s) {
        for (var i = 0, l = arr.length; i < l; i++) {
            if (s < arr[i]) {
                return i;
            }
        }
        return 13;
    };
}());

/**
 * 日期格式化,毫秒数转成字符串
 * @param  {number} times 毫秒数
 * @param  {string} fmt   格式化模板
 * @return {string}       格式化后的日期
 */
var formatDateByTimes = function(times, fmt) {
    var d = new Date(times);
    var o = {
        "M+": d.getMonth() + 1, //月份 
        "d+": d.getDate(), //日 
        "h+": d.getHours(), //小时 
        "m+": d.getMinutes(), //分 
        "s+": d.getSeconds(), //秒 
        "q+": Math.floor((d.getMonth() + 3) / 3), //季度 
        "S": d.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (d.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
};

/**
 * 日期格式化,字符串转成毫秒数
 * @param  {string} str   日期字符串 例如:20150102030405
 */
var formatDateByStr = function(str) {
    if (str) {
        var len = str.length,
            y = str.substr(0, 4),
            m = str.substr(4, 2),
            d = str.substr(6, 2),
            h = len > 8 ? str.substr(8, 2) : 0,
            mm = len > 8 ? str.substr(10, 2) : 0;
        return +new Date(y + "/" + m + "/" + d + " " + h + ":" + mm + ":00");
    }
};

/**
 * 获取aqi等级对应数值
 * @param  {string} levelName 等级名
 * @return {number}           等级数值
 */
var aqiLevelVal = (function() {
    var arr = ["优", "良", "轻度污染", "中度污染", "重度污染"];
    return function(levelName) {
        var index = 0;
        if (typeof levelName == "string") {
            $.each(arr, function(i, n) {
                if (levelName.indexOf(n) >= 0) {
                    index = i + 1;
                    return false;
                }
            });
        }
        return index; 
    };
}());

return {
    string2XML: string2XML,
    getUrlByWindd: getUrlByWindd,
    windSpeed2Power: windSpeed2Power,
    formatDateByTimes: formatDateByTimes,
    formatDateByStr: formatDateByStr,
    aqiLevelVal: aqiLevelVal
};
 
});