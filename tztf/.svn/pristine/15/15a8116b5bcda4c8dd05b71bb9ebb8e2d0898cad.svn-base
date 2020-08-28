/**
 * @require "components/tabs/tabs.js"
 * @require "libs/gigis.js"
 */
define(function (require) {
	var keyi=0;
   
    var template = require("lib/template");
    var GIS = require("lib/gigis/gi-gis");
    var markers = [];
    var arr = [];
    //gis实例
    
    var gis = GIS("map", "grass", {
        center: {
            lon: 120.28,
            lat: 30.22
        },
        zoom:11,
        type: "traffic",
        wrapDateLine: true,
        documentDrag: true
    });
    
    // 经纬度
    gis.addControl("mousePosition", 24, -1, {
        prefix: "经度：",
        separator: " 纬度：",
        numDigits: 5
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

        
        

        var arr=new Array();

        
         arr[1]=["hangzhou/q/","bjq.xml","gsq.xml","jgq.xml","scq.xml","xhq.xml","xcq.xml","djdq.xml"];
         arr[2]=["tonglu/","bjz.xml","cnjd.xml","esszx.xml","fcjd.xml","fcjz.xml","fsz.xml","hcx.xml","hcz.xml","jnz.xml","jxjd.xml","tjjd.xml","xhx.xml","ylz.xml","zsx.xml"];
         arr[3]=["chunan/","ayx.xml","dsz.xml","fkz.xml","fslz.xml","fwx.xml","jfx.xml","jjz.xml","jsx.xml","lcx.xml","lqz.xml","lsx.xml","pmx.xml","qdhz.xml","scx.xml","slz.xml","wfx.xml","wcz.xml","wpz.xml","ysx.xml","zkx.xml","jkx.xml","ztz.xml","zzz.xml"];
         arr[4]=["jiande/","dcyz.xml","dtz.xml","dyz.xml","gljd.xml","qtz.xml","htz.xml","lhz.xml","ljz.xml","mcz.xml","qtx.xml","scz.xml","sdz.xml","xajjd.xml","xyz.xml","ycqz.xml","yxjd.xml"];
         arr[5]=["xiaoshan/","bgjd.xml","cxjd.xml","dcz.xml","dwz.xml","glz.xml","hsz.xml","jhz.xml","jjjd.xml","lpz.xml","ltz.xml","nwz.xml","nyjd.xml","pyz.xml","sqz.xml","ssjd.xml","wyz.xml","xjz.xml","xtjd.xml","ynz.xml","yqz.xml","yqz2.xml"];
         arr[6]=["yuhang/","bzz.xml","cqjd.xml","cxjd.xml","dhjd.xml","hhz.xml","jsz.xml","lnz.xml","lpjd.xml","lzjd.xml","nyjd.xml","pyz.xml","qsjd.xml","rhjd.xml","tqz.xml","wcjd.xml","xljd.xml","xqjd.xml","yhjd.xml","yhjd2.xml","ztjd.xml"];
         arr[7]=["fuyang/","caz.xml","cjjd.xml","cjx.xml","ckz.xml","clz.xml","dqz.xml","dyz.xml","dzjd.xml","fcjd.xml","hsx.xml","hyx.xml","lmz.xml","lqz.xml","lsjd.xml","lsz.xml","lzz.xml","sgx.xml","wsz.xml","xdz.xml","xkz.xml","xtx.xml","ycz.xml","yhjd.xml","ysx.xml"];
         arr[8]=["linan/","bqz.xml","chz.xml","dsz.xml","ghz.xml","hqz.xml","jbjd.xml","jcjd.xml","jnjd.xml","lgz.xml","lljd.xml","qcz.xml","qlfz.xml","qshjd.xml","thyz.xml","tkz.xml","tmsz.xml","tyz.xml","wqz.xml"];

        for(var i=1;i<arr[areaId].length;i++){	
            	$.ajax({
                    url: "/static/js/areaXML/"+arr[areaId][0]+arr[areaId][i],
                    type: "get",
                    dataType: "xml",
                    success: function (xml) {
                    	b = gis.drawBoundary("", xml, {
                            fillOpacity: 0,
                            strokeColor: "#f00"
                    		/*fillOpacity: 0,
                            strokeColor: "#f00"*/
                        });
                        boundrys.push(b);
                    }
                });
             
        }

        
        
//        $("[name='pubRangeName1']").each(function() {
//            
//        	if($(this).is('.on_check')){
//        		var name = $(this).attr('value');
//        		$.ajax({
//                    url: "/static/js/areaXML/hangzhou/q/"+name+ ".xml",
//                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
//                    type: "get",
//                    dataType: "xml",
//                    success: function(xml) {
//                        b = mapstol.drawBoundary("", xml, {
//                        	fillOpacity: 0.3,
//                            strokeColor: "#f00",
//                            fillColor: "#e627cf"
//                        });
//                        bounderList.push(b);
//                    },
//                    error: function() {
//                        alert("网络请求失败！无法绘制地图区域！");
//                    }
//                });
//        	}
//        });
        



      

    }
}
var warningMarkerlayers;
$("#getCommunction").click(function(){
	var url = "/sem/emergency/communction";
	var icon = "contact";
	var clickUrl = "/sem/emergency/findCommunction"; 
	var name = document.getElementById("getCommunction");
	getMarkerData(url,icon,clickUrl,name);
})
$("#getProduct").click(function(){
	var url = "/sem/emergency/product";
	var icon = "emergency-stuff";
	var clickUrl = "/sem/emergency/findProduct";
	var name = document.getElementById("getProduct");
	getMarkerData(url,icon,clickUrl,name);
})
$("#getExport").click(function(){
	var url = "/sem/emergency/export";
	var icon = "professional";
	var clickUrl = "/sem/emergency/findExport"; 
	var name = document.getElementById("getExport");
	getMarkerData(url,icon,clickUrl,name);
})
$("#getMedic").click(function(){
	var url = "/sem/emergency/medic";
	var icon = "hygiene";
	var clickUrl = "/sem/emergency/findMedic"; 
	var name = document.getElementById("getMedic");
	getMarkerData(url,icon,clickUrl,name);
})



$("#btnPolygen").click(function(){
	keyi=1;
})
$("#btnSave").click(function(){
	keyi=0;
})

         
            function getMarkerData(url,icon,clickUrl,name){
	
               	$.ajax({
                       type:"GET",
                       url:url,
                       dataType:"JSON",
                       success:function(data){
                       	if (!warningMarkerlayers) {
                       		warningMarkerlayers = gis.addMarkers();
                       	} else {
                               // 清空图层
                               warningMarkerlayers.clearMarkers();
                           }
                       	
                       	$.each(data, function(i,item){
                       		var a = {
                       			 lon: item.longitude,
                       			 lat: item.latitude,	
                       			 name: item.name,
                       			 id: item.id,
                       			}
                       		darwMarker(a,icon,clickUrl,name);
                       	});
                       	
                       },
                       error:function(e){
                       	alert("接口报错");
                       }
                       });
                  }

                  function darwMarker(data,icon,clickUrl,name) {
                    var icon = {
                     url: "/static2/images/emergency-save/"+icon+".png",
                     width:45,
                     height: 50
                   };
                   var avgLon = data.lon,
                   avgLat = data.lat;
                   var areaName = data.name;
                   var marker = gis.addMarker({
                     icon: icon,
                     lon: avgLon,
                     lat: avgLat,
                     content: { 'data': data, 'lon': avgLon, 'lat': avgLat },
                   }, warningMarkerlayers)[0];
                   var id= data.id;
                   markerClick(clickUrl,name,id,marker);
                };
                
           function markerClick(clickUrl,name,id,marker) {
        	   
        	   marker.events.register("click", marker, function clickCommunction(){
        		   if(keyi==1){
            		   return;
            	   }
              	 $.ajax({
                       type:"GET",
                       url:clickUrl+"?id="+id,
                       dataType:"JSON",
                       success:function(data){
                    	   var newName = data.name;
                    	   if(name == document.getElementById("getExport")) {
                    		   newName = data.workCompany;
                    	   }
                layer.open({
                  title:["<div class='popup_hd_bk'>"+name.innerHTML+"</div><div>"+newName+"</div>",'font-size:14px;padding:0;background:#1c4560;color:#fff;height:auto;border-bottom: 1px solid rgba(255,255,255,0.28);'],
                  type:1,
                  skin:'layui-layer-rim',
                  closeBtn:2,
                  shade: [0],
                  area:['400px','300px'],
                  content:"<div class='layer_table'><table><tr><td>地址</td><td>"+data.address+"</td></tr><tr><td>联系人</td><td>"+data.chargeMan+"</td></tr><tr><td>电话</td><td>"+data.phone+"</td></tr></table></div>",
                  resize:false,
                });
                       },
              	 });
              });
           }     

//$('#btnPolygen').click(function (){
//	if (!warningMarkerlayers) {
//		
//	}else{
//		warningMarkerlayers.clearMarkers();
//	}
//	 
//	clearAll();
//	isSelectLonLat = false;
//});
//
//$('#selectAreas').click(function (){
//	clearAll();
//	isSelectLonLat = false;
//	
//});
//
//$('#btnPosition').click(function(){
//	isSelectLonLat = true;
//})
//
//$('#selectLonLat').click(function (e){
//	clearAll();
//	
//});



drawFYBoundary();

gis.drawTools("btnPolygen", "./images/addr.png", 10000, this);
//
$("#share").tabs({
    tabBtns: ".types span",
    tabUns: ".un",
    event: "click"
});
//定位坐标


    //清除图层数据
    var clearAll = function () {
        drawFYBoundary();//重绘上虞边界线
        //setPoints.clear(); //站点集合
        // if (grid.length != 0) {
        //     grid.destroy();
        // }
        // $("#rankInfo").html("");//清除排名
        // if (tileCollection && tileCollection.length > 0) {
        //     tileCollection[0].destroy();
        //     tileCollection = [];//瓦片图集合
        // }
        //
        // if ($('.g-right').hasClass('right-300')) {
        //     $('.button-r').click();//显示右侧统计信息菜单
        // }

                }

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
    
});