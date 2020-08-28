$(function(){
	
	
	

	 
	 $("img").css("cursor","pointer").click(function(){

	        window.open("/disaUpload/"+$(this).attr("value"));
	    });
	 
	 

	
	/*右侧切换*/
	var $li = $('.query_hd > li');
    var $ul = $('.form_text');

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
    $(".back").on("click",function(){
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
        $(".m-poplayer").find("div.popup_con").css("left", leftWidth).css("min-height", "270px").width(486).html(_body);
        /*添加popup_bd内容部分*/
        $(".m-poplayer").find("div.popup_bd").append("<ul><li class='publish'><h3>退回原因</h3><textarea class='m-text-textarea' id='limitWord' placeholder='请输入' style='color:#0f0f0f'></textarea><p class='text'>限制100字,还剩<span id='textCount'>0</span>字</p></li></ul>");
        $(".m-poplayer").find("div.popup_bd").find("li.publish").css("border-bottom", "none").css("line-height","25px").css("text-align","center");
        $(".m-poplayer").find("div.popup_bd").find(".m-text-textarea").css("margin-top","30px").css("width","66%");
        $(".m-poplayer").find("div.popup_bd").find("p.text").css("margin-left","45%");
        /*限制字数*/
		$("#textCount").text('100');
        $("#limitWord").keyup(function(){
            var curLength=$("#limitWord").val().length;    
            if(curLength>=100){
                var num=$("#limitWord").val().substr(0,100);
                $("#limitWord").val(num);
                $("#textCount").text(0);
            }
            else{
                $("#textCount").text(100-$("#limitWord").val().length);
            }
			//if($("#limitWord").val()==''){
			//	$("#textCount").text('100');
			//}
        })
        /*点击确定*/
        $(".m-btn.m-confirm").off().on("click", function () {
    		$(".m-poplayer").fadeOut(200);
    		$.ajax({
    			async : false,
    			type : "POST",
    			url : "/dd/disaCheck/noConfirm",
    			data : {id:id,version:version,checkContent:$("#limitWord").val()},
    			dataType : "json",
    			success : function(d) {
    				if (d.success) {
      					$(".m-poplayer").fadeIn(200);
      					 _body = '<div class="popup_bd">'
      			             + '</div>'
      			             + '<div class="popup_bottom advice">'
      			             + '<a href="javascript:void(0)" class="m-btn m-confirm">确定</a>'
      			             + '</div>';
      			         /*弹窗位置*/

      			         //发布弹窗在屏幕中间
      			         var leftWidth = ($(window).width() - 486) / 2; 
      			         $(".m-poplayer").find("div.popup_con").css("left", leftWidth).css("min-height", "150px").width(486).html(_body);
      			         /*添加popup_bd内容部分*/
      			         $(".m-poplayer").find("div.popup_bd").append("<ul><li class='publish'><span>回退成功！</span></li></ul>");
      			         $(".m-poplayer").find("div.popup_bd").find("li.publish").css("border-bottom", "none").css("line-height","25px").css("text-align","center");
      			         $(".m-poplayer").find("div.popup_bd").find(".m-text-textarea").css("margin-top","30px").css("width","66%");
      			         $(".m-poplayer").find("div.popup_bd").find("p.text").css("margin-left","45%");
      			         $(".m-btn.m-confirm").on("click", function () {
      			     		$(".m-poplayer").fadeOut(200);
      			     		window.location.href = "/dd/disaCheck/check";
      			        });
      				 }
    			}
    		});
        });
        /*点击取消*/
        $(".m-btn.m-home-cancel").on("click", function () {
    		$(".m-poplayer").fadeOut(200);
        });
    	return false;
    })
    
    
    

    
    /*弹窗*/
    $(".report").on("click",function(e){
    	$(".m-poplayer").fadeIn(200);
			 _body = '<div class="popup_bd">'
	             + '</div>'
	             + '<div class="popup_bottom">'
	             + '<a href="javascript:void(0)" class="m-btn m-confirm">确定</a>'
	             + '<a href="javascript:void(0)" class="m-btn m-home-cancel">取消</a>'
	             + '</div>';
	         /*弹窗位置*/

	         //发布弹窗在屏幕中间
	         var leftWidth = ($(window).width() - 486) / 2; 
	         $(".m-poplayer").find("div.popup_con").css("left", leftWidth).css("min-height", "150px").width(486).html(_body);
	         /*添加popup_bd内容部分*/
	         $(".m-poplayer").find("div.popup_bd").append("<ul><li class='publish'><span>确认通过？</span></li></ul>");
	         $(".m-poplayer").find("div.popup_bd").find("li.publish").css("border-bottom", "none").css("line-height","25px").css("text-align","center");
	         $(".m-poplayer").find("div.popup_bd").find(".m-text-textarea").css("margin-top","30px").css("width","66%");
	         $(".m-poplayer").find("div.popup_bd").find("p.text").css("margin-left","45%");
	         $(".m-btn.m-confirm").on("click", function () {
	        	 $.ajax({
	         		async : false,
	      			type : "POST",
	      			url : "/dd/disaCheck/confirm",
	      			//data : {id:id,version:version,informations:informations},
	      			data :$('#form').serialize(),
	      			dataType : "json",
	      			success : function(d) {
	     				 if (d.success) {
	       					$(".m-poplayer").fadeIn(200);
	       					 _body = '<div class="popup_bd">'
	       			             + '</div>'
	       			             + '<div class="popup_bottom advice">'
	       			             + '<a href="javascript:void(0)" class="m-btn m-confirm">确定</a>'
	       			             + '</div>';
	       			         /*弹窗位置*/
	 
	       			         //发布弹窗在屏幕中间
	       			         var leftWidth = ($(window).width() - 486) / 2; 
	       			         $(".m-poplayer").find("div.popup_con").css("left", leftWidth).css("min-height", "150px").width(486).html(_body);
	       			         /*添加popup_bd内容部分*/
	       			         $(".m-poplayer").find("div.popup_bd").append("<ul><li class='publish'><span>发布成功！</span></li></ul>");
	       			         $(".m-poplayer").find("div.popup_bd").find("li.publish").css("border-bottom", "none").css("line-height","25px").css("text-align","center");
	       			         $(".m-poplayer").find("div.popup_bd").find(".m-text-textarea").css("margin-top","30px").css("width","66%");
	       			         $(".m-poplayer").find("div.popup_bd").find("p.text").css("margin-left","45%");
	       			         $(".m-btn.m-confirm").on("click", function () {
	       			     		$(".m-poplayer").fadeOut(200);
	       			     		window.location.href = "/dd/disaCheck/check";
	       			        });
	       				 }
	     				
	     			}
	     			 
	     		});
		         
	        });
        /*点击取消*/
        $(".m-btn.m-home-cancel").on("click", function () {
    		$(".m-poplayer").fadeOut(200);
        });

        //气泡功能测试
	    var td_ellip=$(".m-poplayer-table tr td.t-ellip");
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
	    });
		/*判断文字是否溢出*/
	    $(".m-poplayer-table tr td span").each(function(i,item){
	        var maxwidth = 400; 
	            if($(item).width()> maxwidth){        
	            $(item).text($(item).text().substring(0,33));
	            $(item).html($(item).html()+'…');
	        }
	    });
    	return false;
    })
});


