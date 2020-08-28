$(function(){
	var info = decodeURI(window.location.search);
	var head = info.split("?");
	var end = head[1].split(",");
	for(var i=0;i<4;i++){
		$(".bg_2").find(">div").eq(i).find("span").text(end[i]);
	}
})