
//时间引用函数
Date.prototype.addMinutes = function (val) {
    var minutes = this.getMinutes();
    this.setMinutes(minutes + val);
    return this;
};
Date.prototype.format = function (fmt) { //author: meizz  
    var o = {
        "M+": this.getMonth() + 1,                 //月份  
        "d+": this.getDate(),                    //日  
        "H+": this.getHours(),                   //小时  
        "m+": this.getMinutes(),                 //分  
        "s+": this.getSeconds(),                 //秒  
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度  
        "S": this.getMilliseconds()             //毫秒  
    };
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
};

$(document).ready(function () {
    $.timepicker.regional['zh-CN'] = {//中文化
        timeOnlyTitle: '选择时间',
        prevText: '上月',
        nextText: '下月',
        monthNames: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
        monthNamesShort: ['一', '二', '三', '四', '五', '六', '七', '八', '九', '十', '十一', '十二'],
        dayNames: ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'],
        dayNamesShort: ['周日', '周一', '周二', '周三', '周四', '周五', '周六'],
        dayNamesMin: ['日', '一', '二', '三', '四', '五', '六'],
        weekHeader: '周',
        timeText: '时间:',
        hourText: '小时:',
        minuteText: '分钟:',
        secondText: '秒钟',
        millisecText: '毫秒',
        microsecText: '微秒',
        timezoneText: '时区',
        currentText: '现在',
        closeText: '完成',
        timeFormat: 'HH:mm',
        timeSuffix: '',
        amNames: ['AM', 'A'],
        pmNames: ['PM', 'P'],
        isRTL: false
    };
    $.timepicker.setDefaults($.timepicker.regional['zh-CN']);//使用中文化

    var nowdate = new Date().format("yyyy-MM-dd HH:mm");//得到当前时间
    $('#basic_example_1').val(nowdate);
    $('#basic_example_2').val(nowdate);
    //获取最新数据的文件时间

    //绑定时间序列
    //getTimeList();


});


