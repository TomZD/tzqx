$(function(){
	
	$(".dept").click(function(){
		$(this).find("ul").fadeToggle(200);
	})
	$(".dept ul li").click(function(){
		var tet = $(this).text();
		$(this).parent().siblings("span").text(tet);
	});

	$(".dept").click(function(){
		$(this).find("img").attr("src","../DDstatic/images/login_1.png");
	})

	$(document).click(function(){
		$(".dept ul").hide();
	})

	$(".m-dept").click(function(event){
		event.stopPropagation();
	})

	//绑定登录接口
	function userLogin(name,password){
		$.ajax({
			type:"get",
			url:"../../sys/user/logins",
			data:{name:name,password:password},
			dataType:"json",
			success:function(data){				
				if(data){
					var userid=data.userid;
					localStorage.setItem("userid",userid);
					if(data.result=="success"){
						localStorage.setItem("username",data.username);
						window.location.href="index.html";
//						$(".showiframe",parent.document).attr("src","../../../../static/DDpages/home.html");
					}else{
						alert("用户名或密码错误");
					}
				}
			},
			error:function(e){
				console.log(e);
			}
		})	
	}
	$(".footer button").click(function(){
		var name=$("#userName").val();
		var password=$("#passWord").val();
		userLogin(name,password);
	})
	
	
})