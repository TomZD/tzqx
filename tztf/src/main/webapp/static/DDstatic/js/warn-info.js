$(function(){
	//获取预警数据单个预警数据
	$.ajax({
		url:"../../sev/alarm/indexData",
		type:"get",
		dataType:"jsonP",
		success:function(data){
			if(data){
				var data = JSON.parse(data);
				var num = localStorage.getItem("num");
				localStorage.removeItem("num");
				//放标题
				$(".department").text(data[num].deptName+data[num].alarmTypeName+"预警");
				//发布时间
				$(".public-time").text("发布时间："+data[num].reallyTime);
				//发布时效
				$(".public-during").text("发布时效："+data[num].duration+"小时");
				//发布内容
				$(".public-con").text(data[num].content);
			}
		},
		error:function(e){
			console.log(e);
		}
	})
})