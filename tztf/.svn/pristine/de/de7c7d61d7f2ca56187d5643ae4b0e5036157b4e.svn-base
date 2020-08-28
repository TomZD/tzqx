$(function(){	
	
	$.ajax({
		async : false,
		type : "POST",
		url : "/sev/check/getText",
		data : {pubNo:pubNo,version:0},
		dataType : "json",
		success : function(d) {
			var text ="";
		   for(var i=0;i<d.data.length;i++){
		        var dataList = d.data[i];
		    	if(i%2==0){
		    		text += " <tr class=\"co1\"><td>"+dataList.channel+"</td><td>"+dataList.content+"</td>"
                         + "<td>递交完毕</td><td>100%</td><td><a class=\"details\">详情</a></td></tr>";
		    	}else{
		    		text += " <tr><td>"+dataList.channel+"</td><td>"+dataList.content+"</td>"
                    + "<td>递交完毕</td><td>100%</td><td><a class=\"details\">详情</a></td></tr>";
		    	}
		     }
		   $("#detail").html(text);
		}
		   
	}); 
//	/*左侧高度*/
//    var _h = $(window).height();    
//	$(".g-bd-left").height(_h -$(".m-event").height() - 24);
//	/*右侧高度*/
//	$(".g-bd-right").height(_h -$(".m-event").height() - 24);
//	$(".g-bd-right .h100").height(_h -$(".m-event").height() - 24);
//	/*input宽度*/
//	$(".g-bd-left-con").find("ul").each(function(n){
//		var conUlWidth = $(".g-bd-left-con ul").width() - 15;
//		var conliWidth = $(this).find("span").width() + 20;
//		if($(this).hasClass("m-sets")){
//			$(this).find("li").css("display","inline-block");
//			$(this).find(".m-text-input").width((conUlWidth - conliWidth * 2) / 2 - 46);
//		}else if($(this).hasClass("m-sets-sets")){
//			$(this).find("li").css("display","inline-block");
//			$(this).find(".m-text-input").width((conUlWidth - conliWidth * 3) / 3 - 51);
//		}else{
//			$(this).find(".m-text-input").width(conUlWidth - conliWidth - 30);
//			$(this).find(".m-text-textarea").width(conUlWidth - conliWidth - 30);
//		}
//	});

	/*日历加载*/
	

	/*左侧超出内容加滚动条*/
	if($(".g-bd-left-con").height() > $(".g-bd-left").height() - $(".g-bd-tit").height()){
		$(".g-bd-left-con").css("overflow-y","scroll");
		$(".g-bd-left-con").height($(".g-bd-left").height() - $(".g-bd-tit").height() - 25);
	}

	/*限制字数*/
	$("#limitWord").keyup(function(){
        var curLength=$("#limitWord").val().length;    
        if(curLength>=100){
            var num=$("#limitWord").val().substr(0,99);
            $("#limitWord").val(num);
            $("#textCount").text(0);
        }
        else{
            $("#textCount").text(99-$("#limitWord").val().length);
        }
    })

    /*审批单宽度*/
    $(".m-text-textarea img").width($(".m-text-textarea").width());
    $(".m-text-textarea img").css("margin-top","10px");

    /*右侧table的高度超过页面加滚动条*/
    if($(".m-table").height() > $(".g-bd-right .h100").height() - $(".g-bd-tit").height()){
    	$(".g-bd-right").css("overflow-y","hidden");
    	$(".m-table").css("overflow-y","scroll");
		$(".m-table").height($(".g-bd-right .h100").height() - $(".g-bd-tit").height() - 42);
    }
})