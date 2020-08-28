 $(function(){
	//取到地址放地址到标题
	if(localStorage.getItem("sml")){
		$(".location span").text(localStorage.getItem("sml"));
	}
	
	//点击避难场所点
	$(".cre-con").on("tap","li",function(){
		// 检查所属类别
		var url="";
		if($(this).parent().hasClass("stuff")){
			url="sem/emergency/getproductbyid";
		}
		else if($(this).parent().hasClass("transport")){
			url="sem/emergency/gettransportbyid";
		}
		else if($(this).parent().hasClass("refuge")){
			url="sem/emergency/getrefugebyid";
		}
		var id = $(this).attr("data");
		localStorage.setItem("url",url);
		localStorage.setItem("point-id",id);
		window.location.href="../DDpages/info.html";
	})
	//点击地址选择地址
	$(".location span").tap(function(){
		parent.location.href="../DDpages/city-select.html";
	})
	//点击向下按钮显示多条信息
	$(".movedown").tap(function(){
		if($(this).siblings("ul").children().length>6){
			if($(this).siblings("ul").hasClass("cre-show")){
				$(this).siblings("ul").removeClass("cre-show");
				$(this).find("i").removeClass("cre-rotate");
			}
			else{
				$(this).siblings("ul").addClass("cre-show");
				$(this).find("i").addClass("cre-rotate");
			}
		}
	})
	//获取列表信息
	//物资保障
	$.ajax({
		url:"/sem/emergency/product",
		type:"get",
		dataType:"json",
		success:function(data){
			if(data){
				var tet = '';
				for(var i=0;i<data.length;i++){
					tet += ' <li data="'+data[i].id+'"><i class="vertical-middle"></i><span>'+data[i].name+'</span></li>';
				}
				$(".stuff").append(tet);
				//如果没有就隐藏
				if(JSON.stringify(data)=="[]"){
					$(".stuff").parent().hide();
				}
			}
		},
		error:function(e){
			console.log(e);
		}
	})
	//运输保障
	$.ajax({
		url:"../../sem/emergency/transport",
		type:"get",
		dataType:"json",
		success:function(data){
			if(data){
				var tet = '';
				for(var i=0;i<data.length;i++){
					tet += ' <li data="'+data[i].id+'"><i class="vertical-middle"></i><span>'+data[i].name+'</span></li>';
				}
				$(".transport").append(tet);
				//如果没有就隐藏
				if(JSON.stringify(data)=="[]"){
					$(".transport").parent().hide();
				}
			}
		},
		error:function(e){
			console.log(e);
		}
	})
	//避灾点保障
	$.ajax({
		url:"../../sem/emergency/townbyrefuge",
		type:"get",
		dataType:"json",
		success:function(data){
			if(data){
				var tet = '';
				for(var i=0;i<data.length;i++){
					tet += ' <li data="'+data[i].id+'"><i class="vertical-middle"></i><span>'+data[i].name+'</span></li>';
				}
				$(".refuge").append(tet);
				//如果没有就隐藏
				if(JSON.stringify(data)=="[]"){
					$(".refuge-title").parent().hide();
				}
			}
		},
		error:function(e){
			console.log(e);
		}
	})
})