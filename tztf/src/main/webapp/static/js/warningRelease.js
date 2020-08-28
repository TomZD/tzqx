$(function(){
	 $("#select_area").change(function(){
		 var value = $("#select_area").val(); //获取Select选择的Value\
		 window.location.href = "/sev/alarm/warning?areaId="+value
		 var deptId = $("#deptId").html();
	 }); 
	
	
//	/*左侧高度*/
//    var _h = $(window).height();
//	$(".g-bd-left").height(_h);
//	/*右侧高度*/
//	$(".g-bd-right").height(_h);

	//数据
	/*var data = [{
		level:"1",
		text:"1",
		time:"20170801"
	},{
		level:"1",
		text:"1",
		time:"20170805"
	}];*/

	var windDs =[];
	function init(){
		var nstr = new Date();
		var ynow = nstr.getFullYear();
		var mnow = nstr.getMonth();
		var dnow = nstr.getDate();
		var mnow_real = mnow + 1;
		var areaId = "";
		//var value = $("#select_area").val(); //获取Select选择的Value
		var deptId = $("#deptId").html();
		if(deptId == 18){
			areaId = $("#select_area").val()
		}
		
		calendar(ynow,mnow);//初始化
		getData(ynow,mnow_real,0,areaId);
		var calendar_month = parseInt(mnow+1);
		$("#calendar_month").find("option[value=" + calendar_month + "]").attr("selected",true); 
		$("#calendar_year").find("option[value=" + ynow + "]").attr("selected",true); 
		$.each(windDs,function(i,item){
			getDay(item.text,item.level,item.time);
		})
	}
	//按年筛选
	$("#calendar_year").change(function (){
		var year = $(this).val();
		var month = $("#calendar_month").find("option:selected").val().split("月"); 
		var type = $("#type").find("option:selected").val(); 
		var mnow = parseInt(month[0]) - 1;
		var areaId = "";
		//var value = $("#select_area").val(); //获取Select选择的Value
		var deptId = $("#deptId").html();
		if(deptId == 18){
			areaId = $("#select_area").val()
		}
		
		calendar(parseInt(year),mnow);
		getData(year,parseInt(month[0]),type,areaId);
		$.each(windDs,function(i,item){
			getDay(item.text,item.level,item.time);
		})
		$("#detail").html("");
		$("#um").html("");
		$("#yjDeatil").html("");
	})
	//按月筛选
	$("#calendar_month").change(function (){
		var month = $(this).val().split("月");
		var year = $("#calendar_year").find("option:selected").val(); 
		var type = $("#type").find("option:selected").val(); 
		var mnow = parseInt(month[0]) - 1;
		calendar(parseInt(year),mnow);
		var areaId = "";
		//var value = $("#select_area").val(); //获取Select选择的Value
		var deptId = $("#deptId").html();
		if(deptId == 18){
			areaId = $("#select_area").val()
		}
		
		
		getData(year,parseInt(month[0]),type,areaId);
		$.each(windDs,function(i,item){
			getDay(item.text,item.level,item.time);
		})
		$("#detail").html("");
		$("#um").html("");
		$("#yjDeatil").html("");
	})
	
	$("#type").change(function (){
		var type = $(this).val();
		var month = $("#calendar_month").find("option:selected").val().split("月"); 
		var year = $("#calendar_year").find("option:selected").val(); 
		var mnow = parseInt(month[0]) - 1;
		var areaId = "";
		//var value = $("#select_area").val(); //获取Select选择的Value
		var deptId = $("#deptId").html();
		if(deptId == 18){
			areaId = $("#select_area").val()
		}
		
		
		calendar(parseInt(year),mnow);
		getData(year,parseInt(month[0]),type,areaId);
		$.each(windDs,function(i,item){
			getDay(item.text,item.level,item.time);
		})
		$("#detail").html("");
		$("#um").html("");
		$("#yjDeatil").html("");
	})

	//点击预警 信息
	$(".m-calendar .m-table").on("click",".m-warningBtn",function(){
		var $this = $(this);
		var time = $this.attr("data-id");
	})
	
	function getData(year,month,type,areaId) {  
		windDs = [];
		$.ajax({
			async : false,
			type : "POST",
			url : "/sev/alarm/getData",
			data : {year:year,month:month,type:type,areaId:areaId},
			dataType : "json",
			success : function(d) {
			for(var i=0;i<d.lists.length;i++){
			        var dataList = d.lists[i];
			    	windDs.push({
			    		level : dataList.alarmTypeName,
			    		text: dataList.num,
			    		time:getLocalTime(dataList.time)
			        });
			     }
			}
			   
		}); 
}
	
    $(".m-table").on("click", ".warning-btn", function () {
		  var level = $(this).attr('id').substring(6);
		  var time = $(this).attr('data-id');
		  $.ajax({
				async : false,
				type : "POST",
				url : "/sev/alarm/getDetail",
				data : {level:level,time:time},
				dataType : "json",
				success : function(d) {
					var text = "";
					var um = "<li class=\"cur\">1</li>";
					var yjText ="";
					if(d.lists[0].pubState==5){
						var yjText = "<div class=\"hm-tabCon\"><ul><li><span>预警类型:"+d.lists[0].jy+"</span></li><li><span>发布单位:"+d.lists[0].fbdw+"</span></li>" +
					                 "<li><span>发布时间:"+d.lists[0].pub+" 持续:"+d.lists[0].cxsj+"小时</span></li><li><span>解除时间:发布中</span></li></ul><button class=\"m-btn btn-detail\" onclick=\"show('"+d.lists[0].id+"')\">详情</button></div>";
					}else if(d.lists[0].pubState==6){
					var yjText = "<div class=\"hm-tabCon\"><ul><li><span>预警类型:"+d.lists[0].jy+"</span></li><li><span>发布单位:"+d.lists[0].fbdw+"</span></li>" +
							     "<li><span>发布时间:"+d.lists[0].pub+" 持续:"+d.lists[0].cxsj+"小时</span></li><li><span>解除时间:"+d.lists[0].cancel+"</span></li></ul><button class=\"m-btn btn-detail\" onclick=\"show('"+d.lists[0].id+"')\">详情</button></div>";
					}
					for(var i=0;i<d.lists.length;i++){
				        var dataList = d.lists[i];
				        var content = dataList.title;
				        if(content.length>17){
				        	content = content.substring(0,17)+"...";
				        }
				    	text += "<li><span>"+dataList.time+" "+content+"</span><button class=\"m-btn btn-detail btn-rt\" onclick=\"show('"+dataList.id+"')\">详情</button></li>"
				     }
					for(var j=1;j<d.lists.length;j++){
				        var dataList = d.lists[j];
				        var a = j+1;
				        um += "<li>"+a+"</li>";
				        if(dataList.pubState==5){
				        	yjText += "<div class=\"hm-tabCon\" style=\"display:none\"><ul><li><span>预警类型:"+dataList.jy+"</span></li><li><span>发布单位:"+dataList.fbdw+"</span></li>"
	                	       + "<li><span>发布时间:"+dataList.pub+" 持续:"+dataList.cxsj+"小时</span></li><li><span>解除时间:发布中</span></li></ul><button class=\"m-btn btn-detail\" onclick=\"show('"+dataList.id+"')\">详情</button></div>";
				        }else if(dataList.pubState==6){
				        	yjText += "<div class=\"hm-tabCon\" style=\"display:none\"><ul><li><span>预警类型:"+dataList.jy+"</span></li><li><span>发布单位:"+dataList.fbdw+"</span></li>"
	                	       + "<li><span>发布时间:"+dataList.pub+" 持续:"+dataList.cxsj+"小时</span></li><li><span>解除时间:"+dataList.cancel+"</span></li></ul><button class=\"m-btn btn-detail\" onclick=\"show('"+dataList.id+"')\">详情</button></div>";
				        } 
				     }
					$("#detail").html(text);
					$("#um").html(um);
					$("#yjDeatil").html(yjText);
				}  
			}); 
	      // $(this).addClass("on_input_check").siblings();
	  })

	init();//初始化
})




function getLocalTime(nS) {     
    return nS.replace("-","").replace("-","");      
 }     


function show(id){
//	window.location.href = "/sev/check/detail?id="+id;
	window.location.href = "/sev/alarm/releaseNowAlarm?alarmId="+id;
}



//预警等级
function getDay(text,level,time){
	var $td = $(".m-calendar .m-table td[data-id=" + time + "] .m-warningBtn");
	var span = document.createElement('span');
	span.className = "warning-btn warnBtn_" + level;
	span.id = "level_" + level;
	span.setAttribute("data-id",time);
	switch (level) {
	case "01":
		span.innerHTML = text;
		break;
	case "02":
		span.innerHTML = text;
		break;
	case "03":
		span.innerHTML = text;
		break;
	case "04":
		span.innerHTML = text;
		break;
	default:
		// statements_def
		break;
}
     $td.append(span);
}

//日历 月份筛选算法
function monDetail(ynow,mnow){
	mnow_real = mnow+1;
	$(".month-detail").html(ynow+"-"+ mnow_real); //显示当前年月
}
function is_leap(year) {
	return (year%100==0?res=(year%400==0?1:0):res=(year%4==0?1:0));// 判断是否为闰年
}

$(".g-bd-tit").on("click",".hm-tab ul li", function(){
	var $this = $(this);
	var index = $this.index();
	$this.addClass("cur").siblings().removeClass("cur");
	$(".hm-tabCon").eq(index).show().siblings().hide();
})

//加载日历数据
function calendar(ynow,mnow){
	$(".calendar-month .m-table tbody").empty(); //改变月份时，先移除旧的日期
	var nlstr = new Date(ynow,mnow,1); //当月第一天
	var firstday = nlstr.getDay(); //第一天星期几

	var m_days=new Array(31,28+is_leap(ynow),31,30,31,31,30,31,30,31,30,31); //每个月的天数

	var tr_str=Math.ceil((m_days[mnow] + firstday)/7); //当前月天数+第一天是星期几的数值 获得 表格行数
	var i,k,idx,date_str;
	var html = "";
	var real_mon = mnow + 1;
	for(i=0;i<tr_str;i++) { //表格的行
		html += "<tr class='date'>"
	for(k=0;k<7;k++) { //表格每行的单元格
		idx=i*7+k; //单元格自然序列号
		date_str=idx-firstday+1; //计算日期
		var dataMonw = real_mon.toString().length < 2? "0" + real_mon : real_mon;
		// 过滤无效日期（小于等于零的、大于月总天数的）
		if(date_str<=0 || date_str>m_days[mnow]){
			date_str=" "	
			dataId = "";
		}else{
			date_str=idx-firstday+1; 
			var dataDate = date_str.toString().length < 2? "0" + date_str : date_str;
			dataId = "data-id='" + ynow + dataMonw + dataDate + "'";
		}
		html +="<td class='day'" + dataId + ">" + date_str + "<div class='m-warningBtn'></div></td>"
	 }
	 	html += "</tr>"
	}
	$(".calendar-month .m-table tbody").html(html);
}