$(function(){
	//返回input的选中成员个数
	var total=0;//一共几个
	var sum=0;//选中几个
	$(".container").on("tap","div input",function(){
		if($(this).is(":checked")){
			sum++;
		}
		else{
			sum--;
		}
		/*if(sum!=0)
			$(".top-more",window.parent.document).text("删除("+sum+")");
		else{
			$(".top-more",window.parent.document).text("删除(0)");
		}*/
	})
	//获取群成员信息
	var id = localStorage.getItem("groupid");
	$.ajax({
		url:"../../sev/pg/personlist",
		data:{
			id:id
		},
		dataType:"json",
		type:"get",
		success:function(data){
			if(data){
				for(var i=0;i<data.length;i++){
					var tet = '<div userid="'+data[i].id+'"><i></i><input type="checkbox" name="" class="out input"><i class="out"></i><img src="../DDstatic/images/touxiang.png" class=""><div class="groupName">'+data[i].phone+'</div><div class="peopleName"><span>'+data[i].deptname+'</span></div></div>'
					$(".container").append(tet);
					
				}
				total = data.length;
			}
		},
		error:function(e){
			console.log(e);
		}
	})
	//提交删除人员名单
	
	$(".add-out").tap(function(){
		var str="";
		for(var i=0;i<total;i++){
			j=i+1;
			if(!$(".container div:nth-child("+j+") input").is(":checked")){
				str = str+$(".container div:nth-child("+j+")").attr("userid")+",";
			}
		}
		str = str.substring(0,str.length-1);
		var deletedata = {
			id:id,
			personids:str,
			name:localStorage.getItem("groupname"),
			ps:localStorage.getItem("ps"),
			userid:localStorage.getItem("userid")
		};
		$.ajax({
			url:"../..//sev/pg/saveorupdate",
			data:deletedata,
			type:"get",
			dataType:"json",
			success:function(data){
				if(data){
					window.location.href="group-info.html";
				}
			},
			error:function(e){
				console.log(e);
			}
		})
	})
})