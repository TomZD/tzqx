$(function(){
	$(".container").on("tap","div",function(){
		localStorage.setItem("ps",$(this).attr("ps"));
		localStorage.setItem("groupid",$(this).attr("groupid"));
		localStorage.setItem("groupname",$(this).attr("groupname"));
		parent.location.href="group-info.html";
	})
	$(".top-more",window.parent.document).tap(function(){
		parent.location.href="add-group.html";
	})
	/*添加群组或个人*/
	$(".add").tap(function(){
		$(".shadow").toggle();
	})
	$(".add-person").tap(function(){
		parent.location.href="emperson-list.html";
	})
	$(".add-group").tap(function(){
		parent.location.href="add-group.html";
	})
//	群列表信息
	function getGroupInfo(userid){
		$.ajax({
			type:'get',
			data:{userid:userid},
			url:'../../sev/pg/grouplist',
			dataType:'json',
			success:function(data){
				$(".container").empty();
				var tr="";
				if(data&&data.length>0){
					
					for(i=0;i<data.length;i++){
						var td='<div ps="'+data[i].ps+'" groupid="'+data[i].id+'" groupname="'+data[i].name+'"><img src="../DDstatic/images/touxiang.png">';
						td+='<div class="groupName">'+data[i].name+'</div>';
						td+='<div class="peopleNum">'+data[i].count+ '人</div></div>';
						tr+=td;
						/*localStorage.setItem("name",data[i].name);
						localStorage.setItem("count",data[i].count);
						localStorage.setItem("id",data[i].id);*/
					}
					$(".container").html(tr);
				}
			},
			error:function(e){
				console.log(e);
			}
		})
	}
	window.onload=function(){
		var userid=localStorage.getItem("userid");
		getGroupInfo(userid);
	}
	
})	
