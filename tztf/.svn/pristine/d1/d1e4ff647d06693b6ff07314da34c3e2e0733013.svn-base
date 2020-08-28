$(function(){
	localStorage.removeItem("groupid");
	//点击编辑按钮编辑群名和备注信息
	$(".cginfo i").tap(function(){
		var i = $(this).siblings("input");
		if(i.attr("readonly")){
			i.val("");
			i.removeAttr("readonly").addClass("gray");
			$(this).parent().addClass("editing");
		}
		else{
			i.attr("readonly","readonly").removeClass("gray");
			$(this).parent().removeClass("editing");
		}
	})
	//点击查看责任人列表
	$(".peo-list li:last-child").tap(function(){
		var str = [];
		var num = $(".peo-list").children().length-1;
		for(var i=0;i<num;i++){
			if(i!=num-1)
			str += $(".peo-list li").eq(i).attr("personid")+',';
			else{
			str += $(".peo-list li").eq(i).attr("personid");
			}
		}
		window.location.href="new-person.html?list="+str;
	})
	//检查是否有新增人员名单
	if(localStorage.getItem("newlist")){
		$.ajax({
			url:"../../sev/emergency-plan/personlist",
			data:{
				userid:localStorage.getItem("userid")
			},
			dataType:"json",
			type:"get",
			success:function(data){
				if(data){
					var list = localStorage.getItem("newlist").split(",");
					var tet="";
					for(var i=0;i<data.length;i++){
						for(var j=0;j<list.length;j++){
							if(list[j]==data[i].id){
								tet += '<li personid='+list[j]+'><a href="contact-info.html"><i class="horizontal-middle"></i><span class="horizontal-middle">'+data[i].name+'</span></a></li>';
							}
						}
					}
					$(".peo-list").prepend(tet);
				}
			},
			error:function(e){
				console.log(e);
			}
		})
	}
	//	新增群
	function addGroup(){
		var personids=localStorage.getItem("newlist");
		var name=$(".groupName").val();
		var ps=$(".psinfo").val();
		var id=localStorage.getItem("userid")
		if(personids&&name&&ps&&id&&name!='编辑群名')
		{
			$.ajax({
				url:'../../sev/pg/saveorupdate',
				type:'get',
				data:{
					id:"0",
					personids:personids,
					name:name,
					ps:ps,
					userid:id
				},
				dataType:'json',
				success:function(data){
					if(data){
						localStorage.removeItem("newlist");
						localStorage.setItem("jump-group",true);
						window.location.href="index.html";
					}
				},
				error:function(e){
					console.log(e);
				}			
			})
		}
		else{
			alert("信息不全请补充");
		}
	}
	$(".bulid").tap(function(){
		addGroup();
	})
})
