$(function(){
	//数据
	var data = [{
		level:"1",
		text:"1",
		time:"20170801"
	},{
		level:"2",
		text:"1",
		time:"20170805"
	},{
		level:"3",
		text:"1",
		time:"20170806"
	}];

	function init(){
		var nstr = new Date();
		var ynow = nstr.getFullYear();
		var mnow = nstr.getMonth();
		var dnow = nstr.getDate();
		var mnow_real = mnow + 1;
		calendar(ynow,mnow_real);//初始化
		var calendar_month = parseInt(mnow+1);
		$("#calendar_month").find("option[value=" + calendar_month + "]").attr("selected",true); 
		$("#calendar_year").find("option[value=" + ynow + "]").attr("selected",true); 
		$.each(data,function(i,item){
			getDay(item.text,item.level,item.time);
		})
	}
	//按年筛选
	$("#calendar_year").change(function (){
		var year = $(this).val();
		var month = $("#calendar_month").find("option:selected").val().split("月"); 
		var mnow = parseInt(month[0]) - 1;
		calendar(parseInt(year),mnow);
	})
	//按月筛选
	$("#calendar_month").change(function (){
		var month = $(this).val().split("月");
		var year = $("#calendar_year").find("option:selected").val(); 
		var mnow = parseInt(month[0]) - 1;
		calendar(parseInt(year),mnow);
	})

	//点击预警 信息
	$(".m-calendar .m-table").on("click",".m-warningBtn",function(){
		var $this = $(this);
		var time = $this.attr("data-id");
		
	})

	init();//初始化
})

//预警等级
function getDay(text,level,time){
	var $td = $(".m-calendar .m-table td[data-id=" + time + "]");
	var el = document.createElement("div");
	el.className = "m-warningBtn warning-btn" + level;
	el.id = "level_" + level;
	switch (level) {
		case "1":
			el.innerHTML = text;
			$td.append(el);
			break;
		case "2":
			el.innerHTML = text;
			$td.append(el);
			break;
		case "3":
			el.innerHTML = text;
			$td.append(el);
			break;
		default:
			// statements_def
			break;
	}
}

//日历 月份筛选算法
function monDetail(ynow,mnow){
	mnow_real = mnow+1;
	$(".month-detail").html(ynow+"-"+ mnow_real); //显示当前年月
}
function is_leap(year) {
	return (year%100==0?res=(year%400==0?1:0):res=(year%4==0?1:0));// 判断是否为闰年
}

//加载日历数据
function calendar(ynow,mnow){
	$(".calendar-month .m-table tbody").empty(); //改变月份时，先移除旧的日期
	var nlstr = new Date(ynow,mnow,1); //当月第一天
	var firstday = nlstr.getDay(); //第一天星期几

	var m_days=new Array(31,28+is_leap(ynow),31,30,31,31,30,31,30,31,30,31); //每个月的天数

	var tr_str=Math.ceil((m_days[mnow] + firstday)/7); //当前月天数+第一天是星期几的数值 获得 表格行数
	var i,k,idx,date_str;
	var html = "";
	for(i=0;i<tr_str;i++) { //表格的行
		html += "<tr class='date'>"
	for(k=0;k<7;k++) { //表格每行的单元格
		idx=i*7+k; //单元格自然序列号
		date_str=idx-firstday+1; //计算日期
		var dataMonw = mnow.toString().length < 2? "0" + mnow : mnow;
		// 过滤无效日期（小于等于零的、大于月总天数的）
		if(date_str<=0 || date_str>m_days[mnow]){
			date_str=" "	
			dataId = "";
		}else{
			date_str=idx-firstday+1; 
			var dataDate = date_str.toString().length < 2? "0" + date_str : date_str;
			dataId = "data-id='" + ynow + dataMonw + dataDate + "'";
		}
		html +="<td class='day'" + dataId + ">" + date_str + "</td>"
	 }
	 	html += "</tr>"
	}
	$(".calendar-month .m-table tbody").html(html);
}