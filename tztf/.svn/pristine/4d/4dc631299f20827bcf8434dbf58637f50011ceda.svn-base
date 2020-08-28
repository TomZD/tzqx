$(function(){	
	
	$.ajax({
		async : false,
		type : "POST",
		url : "/sev/alarm/getText",
//		data : {pubNo:pubNo,
//			version:version},
		data : {alarmId:alarmId},
		dataType : "json",
		success : function(d) {
			var text ="";
		   for(var i=0;i<d.data.length;i++){
		        var dataList = d.data[i];
		        var state ="";
		        var resultDate = "";
		        var feedback = "";
		        if(dataList.releaseState =="0" || typeof(dataList.releaseState )=="undefined"){
		        	state ="未递交";
			        resultDate = "0%";
		        }else if(dataList.releaseState =="1"){
		        	state ="递交中";
			        resultDate = "0%";
		        }else if(dataList.releaseState =="2"){
		        	state ="递交完成";
			        resultDate = "80%";
		        }else if(dataList.releaseState =="3"){
		        	state ="递交失败";
			        resultDate = "0%";
		        }else if(dataList.releaseState =="4"){
		        	state ="反馈完成"+dataList.readState;
			        resultDate = "100%";
//			        feedback = "<a href='/sev/publish/query?instanceId="+dataList.id+"' target='_blank'>详情</a>";
		        }
		        
		    	if(i%2==0){
		    		text += " <tr class=\"co1\"><td>"+dataList.channel+"</td><td>"+dataList.content+"</td>"
                         + "<td>"+state+feedback+"</td><td>"+resultDate+"</td></tr>";
		    	}else{
		    		text += " <tr><td>"+dataList.channel+"</td><td>"+dataList.content+"</td>"
                    + "<td>"+state+feedback+"</td><td>"+resultDate+"</td></tr>";
		    	}
		     }
		   $("#detail").html(text);
		}
		   
	}); 
	
	/*左侧高度*/
    var _h = $(window).height();
	// $(".g-bd-left").height(_h -$(".m-event").height() - 24);
	// /*右侧高度*/
	// $(".g-bd-right").height(_h -$(".m-event").height() - 24);
	// $(".g-bd-right .h100").height(_h -$(".m-event").height() - 24);
	/*input宽度*/
	$(".g-bd-left-con").find("ul").each(function(n){
		var conUlWidth = $(".g-bd-left-con ul").width() - 15;
		var conliWidth = $(this).find("span").width() + 20;
		if($(this).hasClass("m-sets")){
			$(this).find("li").css("display","inline-block");
			$(this).find(".m-text-input").width((conUlWidth - conliWidth * 2) / 2 - 46);
		}else if($(this).hasClass("m-sets-sets")){
			$(this).find("li").css("display","inline-block");
			$(this).find(".m-text-input").width((conUlWidth - conliWidth * 3) / 3 - 51);
		}else{
			$(this).find(".m-text-input").width(conUlWidth - conliWidth - 30);
			$(this).find(".m-text-textarea").width(conUlWidth - conliWidth - 30);
		}
	});

	/*日历加载*/
	$('#basic_example_1').datetimepicker({//时间格式化
        timeFormat: "HH:mm",
        dateFormat: "yy-mm-dd",
        showMonthAfterYear: false,
        changeMonth: true,
        changeYear: true,
        yearRange: "1990:2050",
        setColorstyle: false,
        maxDate: new Date().format("yyyy-MM-dd HH:mm"),
        //maxDate: new Date($('#basic_example_1').val() + ":00").format("yyyy-MM-dd"),
        Colorstyle: { "topColor": "#900", "bodyColor": "#000", "activeColor": "#900", "sliderColor": "#900", "hoverColor": "#900" },//定义外观颜色
        onSelect: function () {//选择日期后执行的操作  
            //getTimeList();
            //getRadarMosaic();
        }
    });
//    /*左侧超出内容加滚动条*/
//    if ($(".g-bd-left-con").height() > $(".g-bd-left").height() - $(".g-bd-tit").height()) {
//        var obj=$(".g-bd-left-con");
//        obj.height($(".g-bd-left").height() - $(".g-bd-tit").height() - 25);
//        if(hasScroll(obj)) useNiceScroll(obj);
//    }
//    /*右侧超出内容加滚动条*/
//    if ($(".g-bd-right .g-bd-right-modular").height() > $(".g-bd-right").height() - $(".g-bd-tit").height()) {
//        var obj=$(".g-bd-right .g-bd-right-modular");
//        obj.height($(".g-bd-right").height() - $(".g-bd-tit").height());
//        if(hasScroll(obj)) useNiceScroll(obj);   
//    }
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
})