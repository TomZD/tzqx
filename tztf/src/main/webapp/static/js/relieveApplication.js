//$(function(){
//	/*左侧高度*/
//    var _h = $(window).height();
//	/*右侧时间轴每一格的位置*/
//	var eventWidth= $(".m-event").width()-10;
//	$(".m-event-schedule p span").width(eventWidth / 4 -5);
//	$(".m-timeAxis-bg dl").width((eventWidth ) / 4);
//	$(".m-timeAxis-con dl").width((eventWidth ) / 4);
//	$(".g-bd-right-modular").find(".m-timeAxis-con").each(function(i){
//		if($(this).find("dd").hasClass("alert")){
//			var _index = $(this).find("dd.alert").parent().index();
//			var conDlWidth = ($(this).find("dd.alert").parent().width() - 29) / 2;
//			$(this).find("dd.alert").parent().width($(this).find("dd.alert").parent().width() / 2 + 10);
//		}
//    });
//    
//    $(".m-timeAxis-con dl dd[class=finish]").parent("dl").width(eventWidth*1/8 +5);
//
//})