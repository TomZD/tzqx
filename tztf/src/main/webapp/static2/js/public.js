$(function(){
  var timer=setInterval(function(){
       var myDate = new Date();
       var str = myDate.toString();
       var year = str.slice(11,15) + '年';
       var month=myDate.getMonth()+1;
       var yue = month + '月';
       var date=myDate.getDate();
       var ri = date + '日'
       var sec = str.slice(16,24) ;
       var strs = year+yue+ri+' '+sec;
       $(".g-hd .hd_time").html(strs)
   },1000)
   //下拉框
	  $(".m-select-time>span").click(function(){
	    $(".m-select-time>ul").toggle();
	    return false;
	  });
	  $("body").on("click",".m-select-time>ul>li",function(){
	    var text = $(this).text();
	    $(".m-select-time>span").html(text);
	    $(this).parent().hide();
	    return false;
	  });
	  $(document).click(function(){
	    $(".m-select-time>ul").hide();
	  });
  //菜单栏的点击事件
    $("body").on("click",".fzjz-save>li",function () {
        $(this).addClass("z-on").siblings().removeClass("z-on");
        $(this).siblings().find("ul.meau_erji").hide();
		 $(this).find("ul.meau_erji").toggle();
        var li = $(this).find("ul li");
        if(!li || li.length == 0){
            $(this).removeClass("z-open");
        }
    })
})

Date.prototype.format = function (format) {
    var o = {
        "M+": this.getMonth() + 1, //month
        "d+": this.getDate(), //day
        "h+": this.getHours(), //hour
        "m+": this.getMinutes(), //minute
        "s+": this.getSeconds(), //second
        "q+": Math.floor((this.getMonth() + 3) / 3), //quarter
        "S": this.getMilliseconds() //millisecond
    };
    if (/(y+)/.test(format))
        format = format.replace(RegExp.$1,
            (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(format))
            format = format.replace(RegExp.$1,
                RegExp.$1.length == 1 ? o[k] :
                    ("00" + o[k]).substr(("" + o[k]).length));
    return format;
};
//ajax获取数据
function getData(url,type,data,dataType,callback) {
    $.ajax({
        url:url,
        type:type,
        data:data,
        dateType:dataType,
        success:function (data) {
            if(typeof(callback) == "function"){
                callback(data);
            }
        },
        error:function (e) {
            if(typeof(callback) == "function"){
                callback("");
            }
        }
    })
};
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
//级别转颜色
function level2color(str){
    if(str.indexOf("蓝色")>-1){
        return '#3364ff'
    }else if(str.indexOf("黄色")>-1){
        return '#fefe00'
    }
    else if(str.indexOf("橙色")>-1){
        return '#fd9a00'
    }
    else if(str.indexOf("红色")>-1){
        return '#d52d2a'
    }
}
/*
 *  经纬度数据转换
 * @param {float} lon:经度
 * @param {float} lat:纬度
 * @param {object} map:地图map对象
 */
function tranformTo900913(lon, lat, map) {
    var proj = new OpenLayers.Projection("EPSG:4326");
    var lonlat = new OpenLayers.LonLat(lon + 0.0042, lat - 0.00258);
    lonlat.transform(proj, map.getProjectionObject());
    return lonlat;
}