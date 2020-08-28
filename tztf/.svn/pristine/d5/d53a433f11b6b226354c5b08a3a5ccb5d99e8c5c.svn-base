$(function(){
//	/*填写发布申请时间轴*/
//	var eventWidth= $(".m-event").width();
//	var conDlWidth = ($(".m-timeAxis-con").find("dl").width() - 29) / 2;
//	$(".m-timeAxis-con").find("dl").width($(".m-timeAxis-con").find("dl").width() / 2 + 10);
//	var timeAxisWidth = eventWidth / 4 - conDlWidth;
//	// $(".m-timeAxis-con").width(timeAxisWidth);
//	$(".m-timeAxis-con").find("dd").css("margin-right","0");
//
//	/*宽度计算*/
//	$(".checkbox_bd").width($(".g-bd-left-con").width()-$(".g-bd-left-con ul li span").width() - 30);$(".checkbox_bd").width($(".g-bd-left-con").width()-$(".g-bd-left-con ul li span").width() - 35);
//
//	$(".form_text").width($(".form_right").width() - 45);

	/*右侧切换*/
	var $li = $('.query_hd > li');
    var $ul = $('.form_text');

//    $li.click(function () {
//        var $this = $(this);
//        var $t = $this.index();
//        $li.removeClass();
//        $this.addClass('on_query');
//        $ul.css('display', 'none');
//        $ul.eq($t).css('display', 'block');
//   })
    /*弹窗*/
//    $(".report").on("click",function(e){
//    	$(".m-poplayer").fadeIn(200);
//			 _body = '<div class="popup_bd">'
//	             + '</div>'
//	             + '<div class="popup_bottom">'
//	             + '<a href="javascript:void(0)" class="m-btn m-confirm">确定</a>'
//	             + '<a href="javascript:void(0)" class="m-btn m-home-cancel">取消</a>'
//	             + '</div>';
//	         /*弹窗位置*/
//
//	         //发布弹窗在屏幕中间
//	         var leftWidth = ($(window).width() - 486) / 2; 
//	         $(".m-poplayer").find("div.popup_con").css("left", leftWidth).css("min-height", "150px").width(486).html(_body);
//	         /*添加popup_bd内容部分*/
//	         $(".m-poplayer").find("div.popup_bd").append("<ul><li class='publish'><span>是否上报？</span></li></ul>");
//	         $(".m-poplayer").find("div.popup_bd").find("li.publish").css("border-bottom", "none").css("line-height","25px").css("text-align","center");
//	         $(".m-poplayer").find("div.popup_bd").find(".m-text-textarea").css("margin-top","30px").css("width","66%");
//	         $(".m-poplayer").find("div.popup_bd").find("p.text").css("margin-left","45%");
//	         $(".m-btn.m-confirm").on("click", function () {
//	        	 $(".m-poplayer").fadeIn(200);
//					 _body = '<div class="popup_bd">'
//			             + '</div>'
//			             + '<div class="popup_bottom">'
//			             + '</div>';
//			         /*弹窗位置*/
//
//			         //发布弹窗在屏幕中间
//			         var leftWidth = ($(window).width() - 486) / 2; 
//			         $(".m-poplayer").find("div.popup_con").css("left", leftWidth).css("min-height", "150px").width(486).html(_body);
//			         /*添加popup_bd内容部分*/
//			         $(".m-poplayer").find("div.popup_bd").append("<ul><li class='publish'><span>上报成功！</span></li></ul>");
//			         $(".m-poplayer").find("div.popup_bd").find("li.publish").css("border-bottom", "none").css("line-height","25px").css("text-align","center");
//			         $(".m-poplayer").find("div.popup_bd").find(".m-text-textarea").css("margin-top","30px").css("width","66%");
//			         $(".m-poplayer").find("div.popup_bd").find("p.text").css("margin-left","45%");
////			         $(".m-btn.m-confirm").on("click", function () {
////			        
////			        });
//			    	
//		             setTimeout(function () { 
//		            	 $(".m-poplayer").fadeOut(200);
//			        	 var form = document.getElementById('form');   
//			             form.action = "/sev/alarm/updateAlarm";
//			             form.submit();
//			         }, 3000);
//	        });
//        /*点击取消*/
//        $(".m-btn.m-home-cancel").on("click", function () {
//    		$(".m-poplayer").fadeOut(200);
//        });
//
//        //气泡功能测试
//	    var td_ellip=$(".m-poplayer-table tr td.t-ellip");
//	    var td_tiplist=$(".m-poplayer-table tr td.t-tip");
//	    //省略号
//	    var num;
//	    td_ellip.on("mouseover", function() {
//	        num = $(this).parent().index();
//	        var isLoad = $(this).find(".bubble-box").length > 0;
//	        if (isLoad) {
//	            $(this).find(".bubble-box").show();
//	        } else {
//	        	var str = "";
//	        	var strName = $(this).prev().text();
//	        	for(var key in map){
//	        		if(key == strName){
//	        			str = map[key];
//	        		}
//	        	}
//	        	var content = "<div class='bubble-box bb-ellip'><p class='d-text'><span>"+str+"</span></p><i class='m-arrow'></i></div>";
//	            $(this).append(content);
//	        }
//	    });
//	    td_ellip.on("mouseout", function() {
//	        $(this).find(".bubble-box").hide();
//	    });
//		/*判断文字是否溢出*/
//	    $(".m-poplayer-table tr td span").each(function(i,item){
//	        var maxwidth = 400; 
//	            if($(item).width()> maxwidth){        
//	            $(item).text($(item).text().substring(0,33));
//	            $(item).html($(item).html()+'…');
//	        }
//	    });
//    	return false;
//    })
    $(".report").on("click",function(e){
    	$(".m-poplayer").fadeIn(200);
        /*添加popup内容*/
        var _body = '<div class="popup_bd">'
            + '</div>'
            + '<div class="popup_bottom">'
            + '<a href="javascript:void(0)" class="m-btn m-confirm">确定</a>'
            + '<a href="javascript:void(0)" class="m-btn m-home-cancel">取消</a>'
            + '</div>';
        /*弹窗位置*/

        //发布弹窗在屏幕中间
        var leftWidth = ($(window).width() - 486) / 2; 
        $(".m-poplayer").find("div.popup_con").css("left", leftWidth).css("height", "390px").width(486).html(_body);
        /*添加popup_bd内容部分*/
        //var _cent = '<div class="m-poplayer-table"><table>';
        var _cent = '<div class="m-poplayer-table"><table><thead><tr><td colspan="2" style="text-align: center">预警发布申请</td></tr></thead>';
        var map = {};
        var informations = '';
        $(".checkbox_style").each(function(i,item){
        	if($(item).find(".radioclass").is(':checked')){
        		var releaseEName = $(item).find(".radioclass").val();
            	var releaseCName = $(item).find("span").text();
            	var channelId = $(item).find(".radioclass").attr("id");
        		var releaseVal = $(".channelContentSel[name='"+releaseEName+"']").val();
        		informations += channelId+','+releaseVal+'&';
        		map[""+releaseCName+""] = releaseVal;
        		_cent+='<tr><td>'+releaseCName+'</td><td class="t-blue t-ellip"><span>'+ releaseVal+'</span></td></tr>';
        	}
	    });
        _cent+= '</table></div>';
        $(".m-poplayer").find("div.popup_bd").append(_cent);
        $(".m-poplayer").find("div.popup_bd").find("li.publish").css("border-bottom", "none").css("line-height","25px").css("text-align","center");
        $(".m-poplayer").find("div.popup_bd").find(".m-text-textarea").css("margin-top","30px").css("width","66%");
        $(".m-poplayer").find("div.popup_bd").find("p.text").css("margin-left","45%");
        $(".m-poplayer").find(".popup_bottom").css("bottom","10px");

        /*点击确定*/
        $(".m-btn.m-confirm").on("click", function () {
        	$(".m-poplayer").fadeIn(200);
			 _body = '<div class="popup_bd">'
	             + '</div>'
	             + '<div class="popup_bottom">'
//	             + '<a href="javascript:void(0)" class="m-btn m-confirm">确定</a>'
	             + '</div>';
	         /*弹窗位置*/
	         //发布弹窗在屏幕中间
	         var leftWidth = ($(window).width() - 486) / 2; 
	         $(".m-poplayer").find("div.popup_con").css("left", leftWidth).css("min-height", "150px").width(350).html(_body);
	         /*添加popup_bd内容部分*/
	         $(".m-poplayer").find("div.popup_bd").append("<ul><li class='publish'><span>上报成功！</span></li></ul>");
	         $(".m-poplayer").find("div.popup_bd").find("li.publish").css("border-bottom", "none").css("line-height","25px").css("text-align","center");
	         $(".m-poplayer").find("div.popup_bd").find(".m-text-textarea").css("margin-top","30px").css("width","66%");
	         $(".m-poplayer").find("div.popup_bd").find("p.text").css("margin-left","45%");
//	         $(".m-btn.m-confirm").on("click", function () {
//	        	 
//	         });
	         setTimeout(function () { 
            	 $(".m-poplayer").fadeOut(200);
	        	 var form = document.getElementById('form');   
	             form.action = "/sev/alarm/updateAlarm";
	             form.submit();
	         }, 2000);
        });
        /*点击取消*/
        $(".m-btn.m-home-cancel").on("click", function () {
    		$(".m-poplayer").fadeOut(200);
        });

        //气泡功能测试
	    /*var td_ellip=$(".m-poplayer-table tr td.t-ellip");
	    var td_tiplist=$(".m-poplayer-table tr td.t-tip");
	    //省略号
	    var num;
	    td_ellip.on("mouseover", function() {
	        num = $(this).parent().index();
	        var isLoad = $(this).find(".bubble-box").length > 0;
	        if (isLoad) {
	            $(this).find(".bubble-box").show();
	        } else {
	        	var str = "";
	        	var strName = $(this).prev().text();
	        	for(var key in map){
	        		if(key == strName){
	        			str = map[key];
	        		}
	        	}
	        	var content = "<div class='bubble-box bb-ellip'><p class='d-text'><span>"+str+"</span></p><i class='m-arrow'></i></div>";
	            $(this).append(content);
	        }
	    });
	    td_ellip.on("mouseout", function() {
	        $(this).find(".bubble-box").hide();
	    });*/
		/*判断文字是否溢出
	    $(".m-poplayer-table tr td span").each(function(i,item){
	        var maxwidth = 400; 
	            if($(item).width()> maxwidth){        
	            $(item).text($(item).text().substring(0,33));
	            $(item).html($(item).html()+'…');
	        }
	    });*/
    	return false;
    })
});