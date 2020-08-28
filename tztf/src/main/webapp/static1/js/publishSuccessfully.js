$(function(){	
	/*限制字数*/
	$("#limitWord").keydown(function(){
        var curLength=$("#limitWord").val().length;    
        if(curLength>=100){
            var num=$("#limitWord").val().substr(0,99);
            $("#limitWord").val(num);
            $("#textCount").text(0);
        }
        else{
            $("#textCount").text(99-$("#limitWord").val().length);
        }
    })
})