$(function(){
	(function(){
		var obj = {};
		obj.url = '/hydata/wechat/lyyb.txt';
		obj.type = 'get';
		obj.dataType = 'text';
		getData(obj,function(data){
			var title = "",txt="";
			if(data){
				title = data.split("：")[0];
				txt = data.split("\r\n").slice(1).join("\r\n");
			}
			$(".forecast-title").html(title);
			$(".m-detail span").html(txt);
		})
	})();
})