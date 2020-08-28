$(function(){
	
	 $.ajax({
		    type:"get",
			url:"/sys/dept/departzt",
			dataType:"json",
			success:function(json1){	
				json1=json1.departList;
				var lin = "";
				for(var i=0; i<json1.length;i++){
					var namer = json1[i];
					    
						//var math = "<option value='"+ namer+ "'>" + namer+ "</option>";
				        var math = "<a class=\"label\" href=\"javascript:void(0)\">" + namer+ "</a>"
						lin+=math;
				}
				console.log(lin);
				$(".dropdown-menu-3").html(lin);
			},
			error:function (){
			
			alert("s");
			}
		}); 
	 
	 $.ajax({
		    type:"get",
			url:"/sys/dept/departzx",
			dataType:"json",
			success:function(json2){	
				json2=json2.departList;
				var lin1 = "";
				for(var i=0; i<json2.length;i++){
					var namer = json2[i];
					    
						//var math = "<option value='"+ namer+ "'>" + namer+ "</option>";
				        var math = "<a class=\"label\" href=\"javascript:void(0)\">" + namer+ "</a>"
				        lin1+=math;
				}
				console.log(lin1)
				$(".dropdown-menu-1").html(lin1);
			},
			error:function (){
			
			alert("s");
			}
		}); 
	 $.ajax({
		    type:"get",
			url:"/sys/dept/departdw",
			dataType:"json",
			success:function(json3){	
				json3=json3.departList;
				var lin2 = "";
				for(var i=0; i<json3.length;i++){
					var namer = json3[i];
					    
						//var math = "<option value='"+ namer+ "'>" + namer+ "</option>";
				        var math = "<a class=\"label\" href=\"javascript:void(0)\">" + namer+ "</a>"
				        lin2+=math;
				}
				console.log(lin2)
				$(".dropdown-menu-2").html(lin2);
			},
			error:function (){
			
			alert("s");
			}
		}); 
	 
	 $.ajax({
		 type:"get",
		 url:"/sys/dept/departyjb",
		 dataType:"json",
		 success:function(json4){	
			 json4=json4.departList;
			 var lin2 = "";
			 for(var i=0; i<json4.length;i++){
				 var namer = json4[i];
				 		//var math = "<option value='"+ namer+ "'>" + namer+ "</option>";
				 var math = "<a class=\"label\" href=\"javascript:void(0)\">" + namer+ "</a>"
				 lin2+=math;
			 }
			 $(".dropdown-menu-4").html(lin2);
		 },
		 error:function (){
			 
			 alert("s");
		 }
	 }); 
	 
	 
	 
		/*$(".m-login").on("click","div.login_li",function(){
	        $(this).siblings().removeClass("on_login_li").end().addClass('on_login_li');
	        $("#username").val($(this).find("input").val());
	        如果发布责任单位没有选择，则下拉框隐藏
	        if(!$(".responsibility").hasClass("on_login_li")){
	            $(".dropdown-menu").hide();
	        }
	    });*/
		
		
	 /*登录界面单位切换*/
		$(".m-login").on("click","div.login_li",function(){
	        $(this).siblings().removeClass("on_login_li").end().addClass('on_login_li');
	        var v = $(this).find("a span").html();
	        if(v == '市应急办' || v == '预警发布中心') {
	        	$("#username").val(v);
	        }else {
	        	var p = $(this).parent("li").find("#username").val();
	        	$("#username").val(p);
	        }
	        /*如果发布责任单位没有选择，则下拉框隐藏*/
	        if(!$(".responsibility").hasClass("on_login_li")){
	            $(".dropdown-menu").hide();
	        }
	    });
		if($("#cavename").val()){
			$("#username").val($("#cavename").val());
		}
	
		 
		 
		
		/*$(".btn-submit").on("click",function(){
			 $.ajax({
				    type:"post",
				    async : false,
					url:"/login",
					data : {username:"杭州市气象局",password:111111,captcha:1111},
					dataType:"json",
					success:function(json){	
                        alert(json);
					},
					error:function (){
						alert(json);
					}
					
					
				}); 
		})*/
})


      