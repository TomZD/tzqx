$(function(){
	//显示详情
	var id = localStorage.getItem("alarmId");
	$.ajax({
		url:"../../dd/approval/detail",
		data:{
			id:id
		},
		type:"get",
		dataType:"json",
		success:function(data){
			if(data){
				$(".title").text(data.title);
				$(".area").text(data.pubrange);
				var str = '';
				for(var i=0;i<data.channelname.length;i++)
					{
						if(i!=data.channelname.length-1)
						str += data.channelname[i]+'、';
						else{
						str += data.channelname[i];
						}
					}
				$(".detailway").text(str);
				$(".time").text(data.date);
				$(".during").text(data.duration+'小时');
				$(".content").text(data.content);
				$(".top").height($(".top ul").height());
				$(".show").attr("src",'../../upload/'+data.imagepath);
			}
		},
		error:function(e){
			console.log(e);
		}
	})

})