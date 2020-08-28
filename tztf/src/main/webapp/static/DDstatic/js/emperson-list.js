$(function(){
	var total=0;//一共几个
	var sum=0;//选中几个
	$(".container div input").tap(function(){
		if($(this).attr("checked")==true)
			sum++;
		else{
			sum--;
		}
	})
	//获取责任人信息
	var id = localStorage.getItem("userid");
	$.ajax({
		url:"../../sev/emergency-plan/personlist",
		data:{
			userid:id
		},
		dataType:"json",
		type:"get",
		success:function(data){
			if(data){
				for(var i=0;i<data.length;i++){
					var tet = '<div personid="'+data[i].id+'"><img src="../DDstatic/images/touxiang.png" class=""><div class="groupName">'+data[i].phone+'</div><div class="peopleName"><span>'+data[i].name+'</span></div></div>'
					$(".container").append(tet);
				}
				total = data.length;
			}
		},
		error:function(e){
			console.log(e);
		}
	})
	$(".add-out button").tap(function(){
		window.location.href="add-emperson.html";
	})
})