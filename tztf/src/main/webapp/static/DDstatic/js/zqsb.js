	//定义图片名字
	var imgname="";
	//	上传图片
	function FileUpload(p) {
    	//显示图片
		var formData = new FormData();
		var files = p.files;
        var dom = p.files[0];
        formData.append("file",dom);
        if(files.length == 0){
            return;
        }
        var file = files[0];
        //把上传的图片显示出来
        var reader = new FileReader();
        // 将文件以Data URL形式进行读入页面
        reader.readAsBinaryString(file);
        reader.onload = function(f){	
            var src = "data:" + file.type + ";base64," + window.btoa(this.result);
            if(!localStorage.getItem("img1")){
            	localStorage.setItem("img1",src);
            }
            else if(!localStorage.getItem("img2")){
            	localStorage.setItem("img2",src);
            }
            else if(!localStorage.getItem("img3")){
            	localStorage.setItem("img3",src);
            }
            if(!$("#img1").html()){
            	document.getElementById("img1").innerHTML = '<img src ="'+src+'"/>';
            }else if(!$("#img2").html())
        	{
            	document.getElementById("img2").innerHTML = '<img src ="'+src+'"/>';
        	}else if(!$("#img3").html())
        	{
            	document.getElementById("img3").innerHTML = '<img src ="'+src+'"/>';
        	}
        }
		var filepath = p.value;
    	var extStart=filepath.lastIndexOf(".");
    	var ext=filepath.substring(extStart,filepath.length).toUpperCase();
    	if(ext!=".BMP"&&ext!=".PNG"&&ext!=".GIF"&&ext!=".JPG"&&ext!=".JPEG"){
	    	alert("图片限于bmp,png,gif,jpeg,jpg格式");
	    	return false;	
    	}
    	else{
    		$.ajaxFileUpload({
                url : '/dd/disaster/uploadPage',// 用于文件上传的服务器端请求地址
                dataType : "json",
                secureuri : false,// 一般设置为false
                fileElementId : p.id,// 文件上传空间的id属性 
                success : function(data) {
                    console.log(data.data);
                    imgname = imgname+data.data+",";
                },
                error : function(data) {
                    alert("请选择文件上传");
                }
            });
    	}
	}
$(function(){
	//如果有图片就放图片放完就销毁
	if(localStorage.getItem("img1")){
		$("#img1").append("<img src=''/>");
		$("#img1 img").attr("src",localStorage.getItem("img1"))
		localStorage.removeItem("img1");
	}
	if(localStorage.getItem("img2")){
		$("#img2").append("<img src=''/>");
		$("#img2 img").attr("src",localStorage.getItem("img2"))
		localStorage.removeItem("img2");
	}
	if(localStorage.getItem("img3")){
		$("#img3").append("<img src=''/>");
		$("#img3 img").attr("src",localStorage.getItem("img3"))
		localStorage.removeItem("img3");
	}
	var lon = localStorage.getItem("lon");
	var lat = localStorage.getItem("lat");
	if(localStorage.getItem("sb-address"))
	$(".address").text(localStorage.getItem("sb-address"));
	//	灾情上报
	function zqUpload(title,publisher,type,add,date,content,userId,imgname){
		$.ajax({
			type:'post',
			data:{
				title:title,
				publisher:publisher,
				type:type,
				add:add,
				date:date,
				content:content,
				userId:userId,
				imagePath:imgname,
				lon:localStorage.getItem("lon"),
				lat:localStorage.getItem("lat")
			},
			url:'/dd/disaster/saveDisa',
			dataType:'json',
			success:function(data){
				if(data){
					if(data.success==true){
						alert("上报成功");
						window.location.href="zqsb-list.html";
					}
				}
			},
			error:function(e){
				console.log(e);
			}
		})
	}
	$(".footer button").click(function(){
		var title=$(".title").val();
		var publisher=$(".name").val();
		var type=$(".zq_type").val();
		var add=$(".address").text();
		var date=$(".time").text();
		var content=$(".desc").val();
		var userId=localStorage.getItem("userid");
		imgname = imgname.substr(0,imgname.length-1);
		if(title&&publisher&&type&&add&&date&&content&&userId&&imgname){
			zqUpload(title,publisher,type,add,date,content,userId,imgname);
		}else{
			alert("输入信息不完整");
		}
	})
		//输入框提示可输入文字个数
	$(".desc").keyup(function(){
		var num = $(this).val().length;
		if(num>256){
			var con = $(this).val();
			$(this).val(con.substr(0,256));
			num = $(this).val().length;
		}
		$(".desc+span").text(num+"/256");
	})
	//跳转地图选择地址
	$(".map").click(function(){
		window.location.href="map.html";
	})
})