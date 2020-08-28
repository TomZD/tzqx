$(function(){
	//跳转群组列表
	if(localStorage.getItem("jump-group")){
		$(".bottom-nav li").removeClass("on");
		$(".bottom-nav li:nth-child(2)").addClass("on");
		$(".showiframe").attr("src","../../../../static/DDpages/group.html");
		localStorage.removeItem("jump-group");
	}
	//跳转city-con
	if(localStorage.getItem("from-select")){
		$(".bottom-nav li").removeClass("on");
		$(".bottom-nav li:nth-child(4)").addClass("on");
		$(".showiframe").attr("src","../../../../static/DDpages/city-con.html");
		localStorage.removeItem("from-select");
	}
	//点击更多的icon
	$(".more-icon").click(function(){
		$(".hide-icon").toggle();
		$(".hide-back").toggle();
		if($(this).hasClass("cgon"))
			$(this).removeClass("cgon");
		else{
			$(this).addClass("cgon");
		}
	})
	$(".bottom-nav li").click(function(){
		if(localStorage.getItem("userid")&&localStorage.getItem("userid")!="undefined"){
			$(".bottom-nav li").removeClass("on");
			$(this).addClass("on");
			if($(this).hasClass("index")){
				$(".top-nav").removeClass("blue");
				$(".index-content").addClass("movetop");
			}
			else
			{
				$(".top-nav").addClass("blue");
				$(".index-content").removeClass("movetop");
			}
			
			var name = $(this).children("span").text();
			if (name=="首页"){
				$(".showiframe").attr("src","../../../../static/DDpages/home.html");
			} 
			else if (name=="责任信息") 
			{
				//如果没有选择地址就跳转到选择地址
				if(!localStorage.getItem("address")){
					$(".showiframe").attr("src","../../../../static/DDpages/city-select.html");
				}
				else{
					$(".showiframe").attr("src","../../../../static/DDpages/city-con.html");
				}	
			}
			else if (name=="突发上报"){
				$(".showiframe").attr("src","../../../../static/DDpages/zqsb.html");
			} 
			else if (name=="审批单上传"){
				$(".showiframe").attr("src","../../../../static/DDpages/emer-mgr.html");
			} 
			else if(name=="群组管理") 
			{
				$(".showiframe").attr("src","../../../../static/DDpages/group.html");
			}
			else if(name=="日志记录") 
			{
				$(".showiframe").attr("src","../../../../static/DDpages/record.html");
			}
		}
		else{
			var name = $(this).children("span").text();
			if (name=="首页"){
				$(".showiframe").attr("src","../../../../static/DDpages/home.html");
			} else{
				alert("请先登录!");
				window.location.href="login.html";
			}
		}
	})

})