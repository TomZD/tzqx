$(function(){
	var areaData;
	//联动效果
	$(".area").on("tap","li",function(){
		for(var i=0;i<areaData.length;i++){
			if($(this).text()==areaData[i].result.content){
				$(".depart").siblings("span").attr("dataid",areaData[i].depart[0].id);
				$(".depart").html("");
				for(var j=0;j<areaData[i].depart.length;j++){
					var depart = areaData[i].depart[j].name;
					var bid = areaData[i].depart[j].id;
					$(".depart").append("<li dataid='"+bid+"'>"+depart+"</li>");
				}
			}
		}
		//显示第一个li的文本
		$(".depart").siblings("span").text($(".depart li:first-child").text());
	})
	//点击显示下拉信息
	$(".select-down").tap(function(){
		$(this).find("ul").toggle();
	})
	$(".select-down ul").on("tap","li",function(){
		$(this).parent().siblings("span").text($(this).text());
		$(this).parent().siblings("span").attr("dataid",$(this).attr("dataid"));
	})
	//获取区域部门信息
	$.ajax({
		url:"/sev/emergency-plan/getArea",
		data:{
			userid:localStorage.getItem("userid")
		},
		dataType:"json",
		type:"get",
		success:function(data){
			if(data){
				areaData=data;
				$(".area").siblings("span").text(areaData[0].result.content);
				$(".area").siblings("span").attr("dataid",areaData[0].result.value);
				$(".depart").siblings("span").text(areaData[0].depart[0].name);
				$(".depart").siblings("span").attr("dataid",areaData[0].depart[0].id);
				for(var i=0;i<areaData.length;i++){
					var area = areaData[i].result.content;
					var aid =  areaData[i].result.value;
					$(".area").append("<li dataid='"+aid+"'>"+area+"</li>")
				}
				for(var j=0;j<areaData[0].depart.length;j++){
					var depart = areaData[0].depart[j].name;
					var bid = areaData[0].depart[j].id;
					$(".depart").append("<li dataid='"+bid+"'>"+depart+"</li>")
				}
				
			}
		},
		error:function(e){
			console.log(e);
		}
	})
	//点击提交新增
	$(".add-em").tap(function(){
		var data = {
			name:$(".name").val(),
			phone:$(".phone").val(),
			areaId:$(".area").siblings("span").attr("dataid"),
			departmentid:$(".depart").siblings("span").attr("dataid"),
		}
		$.ajax({
			url:"/sev/emergency-plan/saveorupdate",
			data:data,
			dataType:"json",
			type:"get",
			success:function(data){
				if(data){
				//	window.location.go(-1);
					window.location.href=document.referrer;
				}
			},
			error:function(e){
				console.log(e);
			}
		})
	})
})