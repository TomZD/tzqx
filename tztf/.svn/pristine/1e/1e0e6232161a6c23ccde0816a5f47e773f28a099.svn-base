$(function(){
	var id = localStorage.getItem("userid");
	$.ajax({
		url:"../../dd/disaster/getDisasterList",
		data:{
			userid:id
		},
		dataType:"json",
		type:"get",
		success:function(data){
			if(data){
				var tet="";
				for(var i=0;i<data.length;i++){
					tet += '<li><span>'+data[i].title+'</span><span>'+data[i].publisher+'</span><span>'+data[i].date+'</span><span class="address">'+data[i].pubAdd+'</span></li>';
				}
				$(".list").append(tet);
			}
		},
		error:function(e){
			console.log(e);
		}
	})
})