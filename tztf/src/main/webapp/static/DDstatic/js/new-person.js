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
	var str = window.location.search;
	var one = str.split('=');
	var list = one[1].split(',');
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
					var tet = '<div personid="'+data[i].id+'"><i></i><input type="checkbox" name="" class="out input"><i class="out"></i><img src="../DDstatic/images/touxiang.png" class=""><div class="groupName">'+data[i].phone+'</div><div class="peopleName"><span>'+data[i].deptname+'</span></div></div>'
					$(".container").append(tet);
				}
				for(var i=0;i<list.length;i++)
				{
					$(".container div[personid='"+list[i]+"']").addClass("grey");
					$(".container div[personid='"+list[i]+"']").find("input").attr("disabled",'disabled');
				}
				total = data.length;
			}
		},
		error:function(e){
			console.log(e);
		}
	})
	//提交新增人员名单返回新建群组
	$(".add-out").tap(function(){
		var str="";
		for(var i=0;i<total;i++){
			j=i+1;
			if($(".container div:nth-child("+j+") input").is(":checked")){
				str = str+$(".container>div:nth-child("+j+")").attr("personid")+",";
			}
		}
		str = str.substring(0,str.length-1);
		if(localStorage.getItem("newlist"))
			{
			var old = localStorage.getItem("newlist")+',';
			localStorage.setItem("newlist",old+str);
			}
		else{
			localStorage.setItem("newlist",str);
		}
		window.history.back();
	})
})