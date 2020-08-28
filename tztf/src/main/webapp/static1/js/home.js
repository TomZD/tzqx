$(function(){
	/*左侧高度*/
    var _h = $(window).height();
	$(".g-bd-left").height(_h);
	/*右侧高度*/
	$(".g-bd-right").height(_h);
	
	//数据
	var data = [{
		level:"1",
		text:"1",
		time:"20170801"
	},{
		level:"2",
		text:"1",
		time:"20170305"
	},{
		level:"3",
		text:"1",
		time:"20170106"
	}];

	function init(){
		getCalendar(2017);
		$.each(data,function(i,item){
			getDay(item.text,item.level,item.time);
		})
	}

	//按年筛选
	$("#calendar_year").change(function(){
		var val = $(this).val();
		getCalendar(val);
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
//日历表格 年
function getCalendar(year){
	var month = 12;
	var html ="";
	var tdLen = $(".m-calendar .m-table thead td").length;
	for(var i = 1;i<month+1;i++){
		html += "<tr>"
		html += "<td>" + i +"</td>";
		for(j = 1;j<tdLen;j++){
			var mothday = i.toString().length < 2? "0" + i : i;
			var day = j.toString().length < 2? "0" + j : j;
			html += "<td data-id='" + year + mothday + day + "'>" + "</td>"
		}
		html +="</tr>"
	}
	$(".m-calendar .m-table tbody").html(html);
}