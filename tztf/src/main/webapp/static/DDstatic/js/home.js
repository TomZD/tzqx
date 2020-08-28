$(function(){
	//如果已经登录则显示退出
	if(localStorage.getItem("userid"))
	{
		$(".login").text("退出");
	}
	//添加名字
	if(localStorage.getItem("username"))
	{
		$(".username").text(localStorage.getItem("username"));
	}
	//点击登录或者退出
	$(".login").tap(function(){
		if($(this).text()=="登录")
		{
			parent.location.href="../DDpages/login.html";
		}
		else{
			//alert(1);
			localStorage.clear();
			$(".login").text("登录");
			$(".username").text("");
		}
	})
	//获取首页预警数据swiper数据
	$.ajax({
		url:"../../sev/alarm/indexData",
		type:"get",
		dataType:"jsonP",
		success:function(data){
			if(data){
				var data = JSON.parse(data);
				for(var i=0;;i++){
					if(data[i]){
						if(data.length==1){
							var tet = '<li class="swiper-slide middle-li"><i style="background-image:url(../DDstatic/images/warning_icon/'+data[i].alarmTypeName+'.jpg)"></i><span>'+data[i].alarmTypeName+'</span><span>'+data[i].deptName+'</span><span>'+data[i].realTime+'发布</span></li>';
							$(".swiper-wrapper").append(tet);
						}
						else{
							var tet = '<li class="swiper-slide"><i style="background-image:url(../DDstatic/images/warning_icon/'+data[i].alarmTypeName+'.jpg)"></i><span>'+data[i].alarmTypeName+'</span><span>'+data[i].deptName+'</span><span>'+data[i].realTime+'发布</span></li>';
							$(".swiper-wrapper").append(tet);
						}
					}
					else{
						break;
					}
				}
			}
			//加载数据完成之后加载swiper
			var mySwiper = new Swiper('.swiper-container', {
				autoplay: false,//可选选项，不自动滑动
				slidesPerView : 3,
		  		slidesPerGroup : 3,
		  		preventClicksPropagation : true,
			})
		},
		error:function(e){
			console.log(e);
		}
	})
	//加载其他天气信息
	$.ajax({
		url:"/dd/weather/index",
		type:"get",
		dataType:"jsonP",
		success:function(data){
			if(data){
				var data = JSON.parse(data);
				var realdata = JSON.parse(data.realdata);
				var future24hours = JSON.parse(data.future24hours);
				var future7days = JSON.parse(data.future7days);
				var rain = realdata.rain;
				var wind = realdata.windd+realdata.windv;
				var future7days = JSON.parse(data.future7days);
				var today = future7days[0];
				var tomorrow = future7days[1];
				var afterto = future7days[2];
				//展示温度
				$(".today-temp").text(realdata.temp+"℃")
				//展示当天天气
				$(".show-weather span").text(future24hours[0].weatherStr);
				var date = new Date()
				var date = String(date).substr(16,2);
				
				if(date>=6&&date<18){
					$(".show-weather i").css("background-image","url(../DDstatic/images/weather/day/"+future24hours[0].weatherStr+".png)");
				}
				else{
					$(".show-weather i").css("background-image","url(../DDstatic/images/weather/day/"+future24hours[0].weatherStr+".png)");
				}
				//展示降水风力
				$(".rain").text(rain+"mm");
				$(".wind").text(wind);
				for(var j=0;j<3;j++){
					var num = j+1;
					var tep = future7days[j].Tmin+"~"+future7days[j].Tmax+"°C";
					if(future7days[j].wStrD=='阴')
						future7days[j].wStrD='阴天';
					if(date>=6&&date<18){
						$(".index-con-bottom li:nth-child("+num+") i").css("background-image","url(../DDstatic/images/weather/day/"+future7days[j].wStrD+".png)");
					}
					else{
						$(".index-con-bottom li:nth-child("+num+") i").css("background-image","url(../DDstatic/images/weather/day/夜"+future7days[j].wStrD+".png)");
					}
					$(".index-con-bottom li:nth-child("+num+") span:nth-child(3)").text(tep);
				}
			}
		},
		error:function(e){
			console.log(e);
		}
	})
	//点击单个预警信息模块跳转查看详情
	$(".swiper-wrapper").on("click","li",function(){
		var num = $(this).index();
		localStorage.setItem("num",num);
		window.location.href="warn-info.html";
		$(".index-content",window.parent.document).removeClass("movetop");
	})
})