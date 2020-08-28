$(function(){
	$(".skim-more").tap(function(){
		window.location.href="record-more.html";
	})
		//输入框提示可输入文字个数
	$(".desc").keyup(function(){
		var num = $(this).val().length;
		if(num>256){
			var con = $(this).val();
			$(this).val(con.substr(0,256));
			num = $(this).val().length;
		}
		$(".desc+span").text(num+"/256");
	})
	//提交接口绑定
	function submitInfo(userId,content,date){
		$.ajax({
			url:'/dd/log/saveLog',
			data:{
				userId:userId,
				content:content,
				date:date
			},
			type:'post',
			dataType:'json',
			success:function(data){
				if(data){
					if(data.result="success"){
						alert(data.msg);
						window.location.href="record-more.html";
					}
				}
			},
			error:function(e){
				console.log(e);
			}
		})
	}
	$(".footer button").click(function(){
		var userId=localStorage.getItem("userid");
		var content=$(".record-detail textarea").val();
		var date=$(".layui-input").text();
		if(userId&&content){
			submitInfo(userId,content,date);
		}else{
			alert("请输入正文");
		}
	})
})