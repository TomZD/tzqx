$(document).ready(function(){
	getajax();
});
//请求ajax
function getajax(){
	$.ajax({
	    url: base+"/sev/reminder/type",
	    type: "POST",
	    async: false,
	    success: function(result) {
	    	for(var key in result){
	    		if(key=="alarm"){
	    			var datas = result[key];
	    			var html = "";
	    			for(var d in datas) {
	    				if(d=="alarm") {
	    					var data = datas[d];
	    					var root = "<ul>";
	    	    	    	var q=1;
	    	    	    	for(var i=0;i<data.length;i++){
	    	    	    		var info = data[i];
	    	    	    		var content = info.namecn;
	    	    	    		var vals = "";
	    	    	    		for(var key in alarmType) {
	    	    	                if (key == content) {
	    	    	                    vals = alarmType[key];
	    	    	                }
	    	    	            }
	    	    	    		root+="<li><a href='"+base+"/sev/alarm/createAlarm?name="+content+"'><img src='/static/images/publishApplication/" +vals+ "'/><span>"+ content +"</span></a></li>";
	    	    	    		if(q%5==0 && q!=data.length){
	    	    	    			root+="</ul><ul>";
	    	    	    		}else if(q%5==0 && data.length ==q){
	    	    	    			root+="</ul>";
	    	    	    		}
	    	    	    		q++;
	    	    	    	}
	    	    	    	q = q-1;
	    	    	    	if(q%5!=0){
	    	    	    		var j = 5-q%5;
	    	    	    		for(var x=0;x<j;x++){
	    	    	    			root+=" <li><img /><span></span></li>";
	    	    	    		}
	    	    	    		root+="</ul>";
	    	    	    	}
	    	    	    	html += root;
	    				}else if (d=="event") {
	    					var data = datas[d];
	    					var root = "<ul>";
	    	    	    	var q=1;
	    	    	    	for(var i=0;i<data.length;i++){
	    	    	    		var info = data[i];
	    	    	    		var content = info.displayName;
	    	    	    		root+="<li><a href='"+base+"/sev/alarm/createEvent?name="+info.id+"'><img src='/static/images/publishApplication/other0.png'/><span>"+ content +"</span></a></li>";
	    	    	    		if(q%5==0 && q!=data.length){
	    	    	    			root+="</ul><ul>";
	    	    	    		}else if(q%5==0 && data.length ==q){
	    	    	    			root+="</ul>";
	    	    	    		}
	    	    	    		q++;
	    	    	    	}
	    	    	    	q = q-1;
	    	    	    	if(q%5!=0){
	    	    	    		var j = 5-q%5;
	    	    	    		for(var x=0;x<j;x++){
	    	    	    			root+=" <li><img /><span></span></li>";
	    	    	    		}
	    	    	    		root+="</ul>";
	    	    	    	}
	    	    	    	if(data.length == 0){
	    	    	    		root+="<li><a href='/sm/r-department-envent-type/choiceType'><img src='/static/images/publishApplication/other0.png'/><span>"+"设置类型"+"</span></a></li></ul>";
	    	    	    	}
	    	    	    	html += root;
	    				}
	    			}
	    	    	
	    	    	
	    	    	$("#leftTable").empty();
	    	    	$("#leftTable").append(html);
	    		}
	    		if(key=="event"){
	    			var data = result[key];
	    	    	var root = "<ul>";
	    	    	var q=1;
	    	    	for(var i=0;i<data.length;i++){
	    	    		var info = data[i];
	    	    		var content = info.displayName;
	    	    		root+="<li><a href='"+base+"/sev/alarm/createEvent?name="+info.id+"'><img src='/static/images/publishApplication/other0.png'/><span>"+ content +"</span></a></li>";
	    	    		if(q%5==0 && q!=data.length){
	    	    			root+="</ul><ul>";
	    	    		}else if(q%5==0 && data.length ==q){
	    	    			root+="</ul>";
	    	    		}
	    	    		q++;
	    	    	}
	    	    	q = q-1;
	    	    	if(q%5!=0){
	    	    		var j = 5-q%5;
	    	    		for(var x=0;x<j;x++){
	    	    			root+=" <li><img /><span></span></li>";
	    	    		}
	    	    		root+="</ul>";
	    	    	}
	    	    	if(data.length == 0){
	    	    		root+="<li><a href='/sm/r-department-envent-type/choiceType'><img src='/static/images/publishApplication/other0.png'/><span>"+"设置类型"+"</span></a></li></ul>";
	    	    	}
	    	    	$("#leftTable").empty();
	    	    	$("#leftTable").append(root);
	    		}
	    	}
	    	
	    }
	});
}
function ach(){
	
	
}



var alarmType = {
        "台风": "typhoon_icon.png",
        "暴雨": "rainstorm_icon.png",
        "暴雪": "blizzard_icon.png",
        "雷电": "thunder_icon.png",
        "大风": "gale_icon.png",
        "寒潮": "coldCurrent_icon.png",
        "沙尘暴": "sandStorm_icon.png",
        "高温": "highTemperature_icon.png",
        "冰雹": "hail_icon.png",
        "霜冻": "frost_icon.png",
        "大雾": "fog_icon.png",
        "霾": "haze_icon.png",
        "干旱": "drought_icon.png",
        "道路结冰": "freeze_icon.png",
    };