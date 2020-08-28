$(function(){
	/*左侧高度*/
	 $.ajax({
			async : false,
			type : "POST",
			url : "/sev/alarm/getText",
			data : {alarmId:alarmId},
			dataType : "json",
			success : function(d) {
				var keyword=0;
				var content="";
				if(d.data.length>0){
					
						var reL = getEName(d.data[0].channel);
						   var text=" <textarea class=\"m-text-textarea form_text dpb on_auto\" name='"+reL+"' id=\"form1\">"+d.data[0].content.replace("发布","解除")+"</textarea>";
						   var channel = "<li class=\"on_query\">"+d.data[0].channel+"</li>";
					
					
				   for(var i=1;i<d.data.length;i++){
					   var temp=d.data[i];
					   var reL = getEName(temp.channel);
					   
					   

						   text +="<textarea class=\"m-text-textarea form_text on_auto\" name='"+reL+"' id=\"form2\">"+temp.content.replace("发布","解除")+"</textarea>";   
						   channel +="<li>"+temp.channel+"</li>";
					  
					   
				    }
				   
				    $("#contentAll").html(text);
				    $("#channelAll").html(channel);
				    
				}
				$("li[name='tohide']").hide();
				$(".form_text").width($(".form_right").width() - 45); 
			}
			 
		});  
	 
	 $("img").css("cursor","pointer").click(function(){
         window.open("/upload/"+imagePath);
     });
	 
//	 var _h = $(window).height();    
//		$(".g-bd-left").height(_h -$(".m-event").height() - 24);
//		/*右侧高度*/
//		$(".g-bd-right").height(_h -$(".m-event").height() - 24);
//		$(".g-bd-right .h100").height(_h -$(".m-event").height() - 24);
//		/*input宽度*/
//		$(".g-bd-left-con").find("ul").each(function(n){
//			var conUlWidth = $(".g-bd-left-con ul").width() - 15;
//			var conliWidth = $(this).find("span").width() + 20;
//			if($(this).hasClass("m-sets")){
//				$(this).find("li").css("display","inline-block");
//				$(this).find(".m-text-input").width((conUlWidth - conliWidth * 2) / 2 - 46);
//			}else if($(this).hasClass("m-sets-sets")){
//				$(this).find("li").css("display","inline-block");
//				$(this).find(".m-text-input").width((conUlWidth - conliWidth * 3) / 3 - 51);
//			}else{
//				$(this).find(".m-text-input").width(conUlWidth - conliWidth - 30);
//				$(this).find(".m-text-textarea").width(conUlWidth - conliWidth - 30);
//			}
//		});

	
	/*右侧切换*/
	var $li = $('.query_hd > li');
    var $ul = $('.form_text');

    $li.click(function () {
        var $this = $(this);
        var $t = $this.index();
        $li.removeClass();
        $this.addClass('on_query');
        $ul.css('display', 'none');
        $ul.eq($t).css('display', 'block');
   })
    	/*左侧超出内容加滚动条*/
	if($(".g-bd-left-con").height() > $(".g-bd-left").height() - $(".g-bd-tit").height()){
		$(".g-bd-left-con").css("overflow-y","scroll");
		$(".g-bd-left-con").height($(".g-bd-left").height() - $(".g-bd-tit").height() - 25);
	}

    /*审批单宽度*/
    $(".m-text-textarea img").width($(".m-text-textarea").width());
    $(".m-text-textarea img").css("margin-top","10px");

    /*右侧table的高度超过页面加滚动条*/
    if($(".m-table").height() > $(".g-bd-right .h100").height() - $(".g-bd-tit").height()){
    	$(".g-bd-right").css("overflow-y","hidden");
    	$(".m-table").css("overflow-y","scroll");
		$(".m-table").height($(".g-bd-right .h100").height() - $(".g-bd-tit").height() - 42);
    }

    /*弹窗*/
    $(".report").on("click",function(e){
    	
    	var pubtime=$("#basic_example_1").val();
    	var publicshName = $('input[name="publisher"]').val();
    	var isserName = $('input[name="issuer"]').val();
    	var time = $('input[name="duration"]').val();
    	
    	 if(pubtime== "" || pubtime=="undefined"){
    		layer.msg("请选择发布时间");
    		return false;
    	}else if(isserName== "" || isserName=="undefined"){
    		layer.msg("请选择签发人员");
    		return false;
    	}else if(publicshName== "" || publicshName=="undefined"){
    		layer.msg("请选择操作人员");
    		return false;
    	}
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
        $(".m-poplayer").find("div.popup_con").css("left", leftWidth).css("height", "390px").width(510).html(_body);
        /*添加popup_bd内容部分*/
        var _cent = '<div class="m-poplayer-table"><table><thead><tr><td colspan="2" style="text-align: center">解除申请</td></tr></thead>';
        var map = {};
        $(".query_hd.fr li").each(function(i,item){
        		var releaseCName = $(this).text();
        		if(releaseCName!="其他渠道"){
        			var releaseVal = $("#contentAll textarea").eq(i).val();
            		_cent+='<tr><td>'+releaseCName+'</td><td class="t-blue t-ellip"><span>'+ releaseVal+'</span></td></tr>';
            		map[""+releaseCName+""]=releaseVal;
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
	             + '<a href="javascript:void(0)" class="m-btn m-confirm" style="margin-left: 0px;">确定</a>'
	             + '</div>';
	         /*弹窗位置*/
//			 layer.msg("上报成功");
	         //发布弹窗在屏幕中间
//	         var leftWidth = ($(window).width() - 486) / 2; 
//	         $(".m-poplayer").find("div.popup_con").css("left", leftWidth).css("height", "150px").width(410).html(_body);
//	         /*添加popup_bd内容部分*/
//	         $(".m-poplayer").find("div.popup_bd").append("<ul><li class='publish'><span>上报成功！</span></li></ul>");
//	         $(".m-poplayer").find("div.popup_bd").find("li.publish").css("border-bottom", "none").css("line-height","25px").css("text-align","center");
//	         $(".m-poplayer").find("div.popup_bd").find(".m-text-textarea").css("margin-top","30px").css("width","66%");
//	         $(".m-poplayer").find("div.popup_bd").find("p.text").css("margin-left","45%");
//	         $(".m-btn.m-confirm").on("click", function () {
//
//
//	        	 $(".m-poplayer").fadeOut();
//	         });
	         setTimeout(function () { 
//	       	 $(".m-poplayer").fadeOut(200);
//	        	 var form = document.getElementById('form');   
//	             form.action = "/sev/alarm/detailsRemove";
//	             form.submit();
	             
	         	$.ajax({
	                cache: true,
	                type: "post",
	                url:"/sev/alarm/detailsRemove",
	                data:$.param({})+'&'+$('#form').serialize(),// 你的formid
	                async: false,

	                error: function(request) {
	                    alert("Connection error");
	                },
	                success: function(data) {
	               	 if(data=="pass"){
	               		 layer.msg("上报成功！");
	               		 setTimeout(function(){},1000);
	               		 window.location.href="/sev/alarm/relieveAlarmList";
	               	 }else{
	               		 layer.msg(data);
	               	 }
	               	 
	               	 
	                
	                                 
	                }
	            });
//	             $(window.parent.document).find(".m-nav").find("li").removeClass("on_nav");
//	         	 $(window.parent.document).find(".m-nav").find("li").eq(1).addClass("on_nav");
	       	
	         }, 2000);
	         
        	
        	
        });
        /*点击取消*/
        $(".m-btn.m-home-cancel").on("click", function () {
    		$(".m-poplayer").fadeOut(200);
        });

//        //气泡功能测试
//	    var td_ellip=$(".m-poplayer-table tr td.t-ellip");
//	    var td_tiplist=$(".m-poplayer-table tr td.t-tip");
//	    //省略号
//	    var num;
////	    td_ellip.on("mouseover", function() {
////	        num = $(this).parent().index();
////	        var isLoad = $(this).find(".bubble-box").length > 0;
////	        if (isLoad) {
////	            $(this).find(".bubble-box").show();
////	        } else {
////	        	var str = "";
////	        	var strName = $(this).prev().text();
////	        	for(var key in map){
////	        		if(key == strName){
////	        			str = map[key];
////	        		}
////	        	}
////	        	var content = "<div class='bubble-box bb-ellip'><p class='d-text'><span>"+str+"</span></p><i class='m-arrow'></i></div>";
////	            $(this).append(content);
////	        }
////	    });
////	    td_ellip.on("mouseout", function() {
////	        $(this).find(".bubble-box").hide();
////	    });
//		/*判断文字是否溢出*/
//	    $(".m-poplayer-table tr td span").each(function(i,item){
//	        var maxwidth = 400; 
//	            if($(item).width()> maxwidth){        
//	            $(item).text($(item).text().substring(0,1000));
//	            //$(item).html($(item).html()+'…');
//	        }
//	    });
    	return false;
    })
    
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
//				 _body = '<div class="popup_bd">'
//		             + '</div>'
//		             + '<div class="popup_bottom">'
////		             + '<a href="javascript:void(0)" class="m-btn m-confirm">确定</a>'
//		             + '</div>';
//		         /*弹窗位置*/
//
//		         //发布弹窗在屏幕中间
//		         var leftWidth = ($(window).width() - 486) / 2; 
//		         $(".m-poplayer").find("div.popup_con").css("left", leftWidth).css("min-height", "150px").width(486).html(_body);
//		         /*添加popup_bd内容部分*/
//		         $(".m-poplayer").find("div.popup_bd").append("<ul><li class='publish'><span>上报成功！</span></li></ul>");
//		         $(".m-poplayer").find("div.popup_bd").find("li.publish").css("border-bottom", "none").css("line-height","25px").css("text-align","center");
//		         $(".m-poplayer").find("div.popup_bd").find(".m-text-textarea").css("margin-top","30px").css("width","66%");
//		         $(".m-poplayer").find("div.popup_bd").find("p.text").css("margin-left","45%");
////		         $(".m-btn.m-confirm").on("click", function () {
////		        	 
////		         });
//		         setTimeout(function () { 
//		        	 $(".m-poplayer").fadeOut(200);
//		        	 var form = document.getElementById('form');   
//		             form.action = "/sev/alarm/detailsRemove";
//		             form.submit();
//		         }, 3000);
//		         
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
//    
    
    
    
    function oncL(){
    	document.frames('ifrmname').location.reload();
    }
    
    function getEName(str){
    	var backStr = "";
    	for(var i=0;i<releaseChannel.length;i++){
    		var data = releaseChannel[i];
    		if(str ==data.name){
    			backStr ="channel_"+data.nameEn;
    		}
    	}
    	return backStr;
    }
    
    /*日历加载*/
	
});