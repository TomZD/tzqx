//$(function(){	
//	/*发布申请未通过时间轴*/
//	var eventWidth= $(".m-event").width();
//	if($(".m-timeAxis-con").find("dd").hasClass("alert")){
//		var _index = $(".m-timeAxis-con").find("dd.alert").parent().index();
//		var conDlWidth = ($(".m-timeAxis-con").find("dd.alert").parent().width() - 29) / 2;
//		$(".m-timeAxis-con").find("dd.alert").parent().width($(this).find("dd.alert").parent().width() / 2 + 10);
//		var timeAxisWidth = eventWidth / 4 * (_index + 1) - conDlWidth;
//		// $(".m-timeAxis-con").width(timeAxisWidth);
//		/*红点下面的文字也为红色*/
//		$(".m-timeAxis-con").parents(".m-event-timeAxis").next(".m-event-explain").find("span").eq(_index).css("color","#ff4747");
//		/*判断红字是否换行，如果换行就加上一定的高度*/
//		if($(".m-timeAxis-con").parents(".m-event-timeAxis").next(".m-event-explain").find("span").eq(_index).height() > 22){
//			$(".m-timeAxis-con").parents(".m-event-timeAxis").next(".m-event-explain").find("span").eq(_index).css("position","relative");
//			$(".m-timeAxis-con").parents(".m-event-timeAxis").next(".m-event-explain").find("span").eq(_index).css("top","11px");
//		}
//	}else if($(".m-timeAxis-con").find("dd").hasClass("finish")){
//		var _index = $(".m-timeAxis-con").find("dd.finish").parent().index();
//		var conDlWidth = ($(".m-timeAxis-con").find("dd.finish").parent().width() - 29) / 2;
//		$(".m-timeAxis-con").find("dd.finish").parent().width($(this).find("dd.finish").parent().width() / 2 + 10);
//		var timeAxisWidth = eventWidth / 4 * (_index + 1) - conDlWidth;
//		// $(".m-timeAxis-con").width(timeAxisWidth);
//		/*判断完成的说明是否换行，如果换行就加上一定的高度*/
//		if($(".m-timeAxis-con").parents(".m-event-timeAxis").next(".m-event-explain").find("span").eq(_index).height() > 22){
//			$(".m-timeAxis-con").parents(".m-event-timeAxis").next(".m-event-explain").find("span").eq(_index).css("position","relative");
//			$(".m-timeAxis-con").parents(".m-event-timeAxis").next(".m-event-explain").find("span").eq(_index).css("top","11px");
//		}
//	}
//});