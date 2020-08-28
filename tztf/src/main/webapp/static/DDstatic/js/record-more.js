$(function(){
	//点击返回按钮返回
	$(".toback",window.parent.document).tap(function(){
		// $(window.parent.document).contents().find(".showiframe").attr("src","record.html");
		$(".top-title",window.parent.document).text("日志记录");
	})
//	历史日志
	function historyLog(userId){
		$.ajax({
			type:'get',
			data:{userId:userId},
			url:'/dd/log/getLogHistoryData',
			dataType:'json',
			success:function(data){
				$(".record-detail").empty();
				var he="";
				if(data){
					for(var i=0;i<data.length;i++){
						var ht='<li><div>'+data[i].content+'</div>';
						ht+='<div>'+data[i].date+'</div></li>';
						he+=ht;
					}					
				}
				$(".record-detail").html(he);
			},
			error:function(e){
				console.log(e);
			}
			
		})
	}
	window.onload=function(){
		var userId=localStorage.getItem("userid");
		historyLog(userId);
	}
})