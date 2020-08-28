$(function(){
	/*左侧高度*/
    var _h = $(window).height();
	$(".g-bd-left").height(_h);
	/*右侧高度*/
	$(".g-bd-right").height(_h);
	
	//数据
	var windDs = [];

	function init(){
		getCalendar(2017);
		getData(2017,0);
		$.each(windDs,function(i,item){
			getDay(item.text,item.level,item.time);
		})
	}

	//按年筛选
	$("#calendar_year").change(function(){
		var val = $(this).val();
		var type = $("#type").find("option:selected").val(); 
		getCalendar(val);
		getData(val,type);
		$.each(windDs,function(i,item){
			getDay(item.text,item.level,item.time);
		})
	})
	
	
	$("#type").change(function(){
		var type = $(this).val();
		var year = $("#calendar_year").find("option:selected").val(); 
		getCalendar(year);
		getData(year,type);
		$.each(windDs,function(i,item){
			getDay(item.text,item.level,item.time);
		})
	})

	init();//初始化
	
	function getData(year,type) {  
		windDs = [];
		$.ajax({
			async : false,
			type : "POST",
			url : "/sev/alarm/getYear",
			data : {year:year,type:type},
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
	
	$(".m-table").on("click", ".m-warningBtn", function () {
		  var level = $(this).attr('id').substring(6);
		  var time = $(this).attr('data-id');
		  $.ajax({
				async : false,
				type : "POST",
				url : "/sev/alarm/getDetail",
				data : {level:level,time:time},
				dataType : "json",
				success : function(d) {
					var yjText = "";
					for(var j=0;j<d.lists.length;j++){
				        var dataList = d.lists[j];
				        if(dataList.pubState==5){
				        	yjText   += " <div class=\"multiple_cont\"><ul><li><span>预警类型:"+dataList.jy+"</span></li><li><span>发布单位:"+dataList.fbdw+"</span></li><li><span>发布时间:"+dataList.pub+" 持续:"+dataList.cxsj+"小时</span></li>"
                            +  " <li><span>解除时间:发布中</span></li></ul><button class=\"m-btn btn-detail\"  onclick=\"show('"+d.lists[0].id+"')\">详情</button></div>";
				        }else if(dataList.pubState==6){
				        	yjText   += " <div class=\"multiple_cont\"><ul><li><span>预警类型:"+dataList.jy+"</span></li><li><span>发布单位:"+dataList.fbdw+"</span></li><li><span>发布时间:"+dataList.pub+" 持续:"+dataList.cxsj+"小时</span></li>"
                            +  " <li><span>解除时间:"+dataList.cancel+"</span></li></ul><button class=\"m-btn btn-detail\"  onclick=\"show('"+d.lists[0].id+"')\">详情</button></div>";
				        }
				        
				     }
					$("#yjDeatil").html(yjText);
				}  
			}); 
	      // $(this).addClass("on_input_check").siblings();
	  });
})

function show(id){
	window.location.href = "/sev/check/detail?id="+id;
}

//预警等级
function getDay(text,level,time){
	var $td = $(".m-calendar .m-table td[data-id=" + time + "]");
	var el = document.createElement("div");
	el.className = "m-warningBtn warning-btn" + level;
	el.id = "level_" + level;
	el.setAttribute("data-id",time);
	switch (level) {
		case "01":
			el.innerHTML = text;
			$td.append(el);
			break;
		case "02":
			el.innerHTML = text;
			$td.append(el);
			break;
		case "03":
			el.innerHTML = text;
			$td.append(el);
			break;
		case '04':
			el.innerHTML = text;
			$td.append(el);
			break;
		default:
			// statements_def
			break;
	}
}




function getLocalTime(nS) {     
    return nS.replace("-","").replace("-","");      
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