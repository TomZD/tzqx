$(function(){
	//修改标题
	$(".top-title",window.parent.document).text("城镇选择");
	//地址选中样式改变
	$(".city-ele").on("tap","li",function(){
		$(this).parent().find("li").removeClass("selected");
		$(this).addClass("selected");
		if($(".city li").hasClass("selected")&&$(".town li").hasClass("selected")){
			$(".confirm").removeClass("not");
		}
	})
	//获取区信息
	$.ajax({
		url:"/dd/areaInfo/getCountryData",
		type:"get",
		success:function(data){
			if(data){
				data = JSON.parse(data);
				for(var i=0;i<data.length;i++){
					$(".city").append('<li data = "'+data[i]+'">'+data[i]+'</li>')
				}
				//读取本地item
				if(localStorage.getItem("big"))
				{
					var big = localStorage.getItem("big");
					$(".city li[data = "+big+"]").addClass("selected");
					querytown();
				}
			}
		},
		error:function(e){
			console.log(e);
		}
	})
	//区域乡镇联动显示
	$(".city").on("tap","li",function(){
		querytown();
	})
	//地址选择完毕确认返回
	$(".confirm").tap(function(){
		if(!$(".confirm").hasClass("not")){
			var big = $(".city li.selected").text();
			var sml = $(".town li.selected").text();
			localStorage.setItem("big",big);
			localStorage.setItem("sml",sml);
			localStorage.setItem("address",big+sml);
			window.location.href="index.html";
			localStorage.setItem("from-select",true);
		}
	})
	function querytown(){
		var city = $(".city li.selected").text();
		//请求对应乡镇
		$.ajax({
			url:"/dd/areaInfo/getTownData",
			type:"get",
			data:{
				country:city
			},
			dataType:"json",
			success:function(data){
				if(data){
					$(".town").html("");
					for(var i=0;i<data.length;i++){
						$(".town").append('<li data = "'+data[i]+'">'+data[i]+'</li>')
					}
					//读取本地item
					if(localStorage.getItem("sml"))
					{
						var sml = localStorage.getItem("sml");
						$(".town li[data = "+sml+"]").addClass("selected");
					}
					//控制按钮样式
					if($(".city li").hasClass("selected")&&$(".town li").hasClass("selected")){
						$(".confirm").removeClass("not");
					}
					else{
						$(".confirm").addClass("not");
					}
				}
			},
			error:function(e){
				console.log(e);
			}
		})
	}
})