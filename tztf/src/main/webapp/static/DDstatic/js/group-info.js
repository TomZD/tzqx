$(function(){
	//新增人员
	$(".peo-list").on("tap","li:last-child",function(){
		var str = [];
		var num = $(".peo-list").children().length-1;
		for(var i=0;i<num;i++){
			if(i!=num-1)
			str += $(".peo-list li").eq(i).attr("personid")+',';
			else{
			str += $(".peo-list li").eq(i).attr("personid");
			}
		}
		window.location.href="add-person.html?list="+str;
	})
	//查看人员信息
	$(".peo-list").on("tap","li",function(){
		if($(this).index()!=$(".peo-list li:last-child").index()){
			var name = $(this).attr("name");
			var phone = $(this).attr("phone");
			var area = $(this).attr("area");
			var depart = $(this).attr("depart");
			window.location.href="contact-info.html?"+name+","+phone+","+area+","+depart;
		}
	})
	//点击编辑按钮编辑群名和备注信息
	$(".cginfo i").tap(function(){
		var i = $(this).siblings("input");
		if(i.attr("readonly")){
			i.removeAttr("readonly").addClass("gray");
			$(this).parent().addClass("editing");
		}
		else{
			i.attr("readonly","readonly").removeClass("gray");
			$(this).parent().removeClass("editing");
			//编辑完成上传新数据
			var name = $(".groupName").val();
			var ps = $(".psinfo").val();
			cginfo(name,ps);
		}
	})
	//点击删除删除群组
	$(".delete").tap(function(){
		var id = localStorage.getItem("groupid");
		$.ajax({
			url:"../../sev/pg/del",
			data:{
				id:id
			},
			type:"get",
			dataType:"json",
			success:function(data){
				if(data){
					localStorage.setItem("jump-group",true);
					window.location.href="index.html";
				}
			},
			error:function(e){
				console.log(e);
			}
		})
	})
	//接口 群名 人数
	function getPeopleInfo(id){
		$.ajax({
			type:'get',
			data:{id:id},
			url:'../../sev/pg/personlist',
			dataType:'json',
			success:function(data){
				$(".peopleNum").text("共"+data.length+"人");
				var tr="";
				if(data&&data.length>0){
					var plist="";
					for(var i=0;i<data.length;i++){
						var td='<li name="'+data[i].name+'" phone="'+data[i].phone+'" depart="'+data[i].deptname+'" area="'+data[i].areaname+'" personid="'+data[i].id+'"><i class="horizontal-middle"></i>';
						td+='<span class="horizontal-middle">'+data[i].name+'</span></li>';
						tr+=td;
						plist = plist + data[i].id+",";
					}
					localStorage.setItem("plist",plist);
				}
				tr+='<li><i class="horizontal-middle"></i>';
				tr+='<span class="horizontal-middle">添加</span></li>';
				$(".peo-list").html(tr);
				
			},
			error:function(e){
				console.log(e);
			}
		})
	}
	//修改群名和备注信息
	function cginfo(name,ps){
		localStorage.setItem("groupname",name);
		localStorage.setItem("ps",ps);
		var str = localStorage.getItem("plist");
		var updata = {
			id:localStorage.getItem("groupid"),
			personids:str.substring(0,str.length-1),
			name:name,
			ps:ps,
			userid:localStorage.getItem("userid")
		};
		$.ajax({
			url:"../../sev/pg/saveorupdate",
			data:updata,
			type:"get",
			dataType:"json",
			success:function(data){
				if(data){
					alert("修改成功");
				}
			},
			error:function(e){
				console.log(e);
			}
		})
	}
	window.onload=function(){
		var name=localStorage.getItem("groupname");
		var id=localStorage.getItem("groupid");
		$(".groupName").val(name);
		getPeopleInfo(id);
	}
	
})