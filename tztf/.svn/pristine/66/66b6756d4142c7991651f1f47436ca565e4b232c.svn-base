$(function(){
	//取到地址放地址
	if(localStorage.getItem("address")){
		$(".address-info td:last-child").text(localStorage.getItem("address"));
	}
	var id = localStorage.getItem("point-id");
	var url = localStorage.getItem("url");
	//获取详情信息
	$.ajax({
		url:"../../"+url,
		type:"get",
		data:{
			id:id
		},
		dataType:"json",
		success:function(data){
			if(data){
				$(".box-title").find("span").text(data.name);
				$(".address-info td:last-child").text(data.address);
				$(".lon-info td:last-child").text(data.longitude);
				$(".lat-info td:last-child").text(data.latitude);
				$(".mgr-info td:last-child").text(data.type);
				$(".gover-info td:last-child").text(data.area);
				$(".population-info td:last-child").text(data.linkman);
				$(".phone-info td:last-child").text(data.officeTel);
				$(".acreage-info td:last-child").text(data.phone);
			}
		},
		error:function(e){
			console.log(e);
		}
	})
})