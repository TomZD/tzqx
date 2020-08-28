$(function(){
	(function(){
		var obj = {};
		obj.url = '/hydata/wechat/jtyb.txt';
		obj.type = 'get';
		obj.dataType = 'text';
		getData(obj,function(data){
			var title = "",txt="";
			if(data){
				title = data.split("：")[0];
				txt = data.split("：").slice(1).join("：");
			}
			$(".forecast-title").html(title);
			$(".m-detail span").html(txt);
		})
	})();
})