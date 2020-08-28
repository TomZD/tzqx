
$(function(){
	
	$.ajax({
		async : false,
		type : "POST",
		url : "/sev/check/getDoCheck",
		data : {departId:departId},
		dataType : "json",
		success : function(d) {
		   
		}
		   
	}); 
})	