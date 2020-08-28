//	上传图片
	function FileUpload(p){
		var filepath = p.value;
    	var extStart=filepath.lastIndexOf(".");
    	var ext=filepath.substring(extStart,filepath.length).toUpperCase();
    	var id = p.parentElement.attributes[1].value;
    	if(ext!=".BMP"&&ext!=".PNG"&&ext!=".GIF"&&ext!=".JPG"&&ext!=".JPEG"){
	    	alert("图片限于bmp,png,gif,jpeg,jpg格式");
	    	return false;	
    	}
    	else{
    		$.ajaxFileUpload({
                url : '../../dd/approval/uploadPage',// 用于文件上传的服务器端请求地址
                dataType : "json",
                data:{
                	alarmId:id
                },
                secureuri : false,// 一般设置为false
                fileElementId : p.id,// 文件上传空间的id属性 
                success : function(data) {
                    console.log(data.data);
                    $(".con li[alarmid="+id+"]").removeClass("need").find("input").hide();
                    window.location.reload();
                },
                error : function(data) {
                    alert("请选择文件上传");
                }
            });
    	}
	}
$(function(){
	//显示审批单列表
	var userid = localStorage.getItem("userid");
	$.ajax({
		url:"../../dd/approval/alarmListToApproval",
		data:{
			userId:userid
		},
		type:"get",
		dataType:"json",
		success:function(data){
			if(data){
				var tet = '';
				for(var i=0;i<data.length;i++){
					if(data[i].isImage){
						tet += '<li alarmId="'+data[i].alarmId+'"><i></i><span>'+data[i].typeName+'</span><span>'+data[i].pubDate+'</span><i></i></li> ';
					}
					else{
						tet +='<li class="need" alarmId="'+data[i].alarmId+'"><i></i><span>'+data[i].typeName+'</span><span>'+data[i].pubDate+'</span><input type="file" name="file" onchange="FileUpload(this)" id="file1"/><i></i></li> ';
					}
				}
				$(".upload .con").append(tet);
			}
		},
		error:function(e){
			console.log(e);
		}
	})
	//显示已发布列表
	$.ajax({
		url:"../../dd/approval/alarmList",
		data:{
			userId:userid
		},
		type:"get",
		dataType:"json",
		success:function(data){
			if(data){
				var tet = '';
				for(var i=0;i<data.length;i++){
					tet += '<li alarmId="'+data[i].alarmId+'"><i></i><span>'+data[i].typeName+'</span><span>'+data[i].pubDate+'</span><i></i></li>';
				}
				$(".publiced .con").append(tet);
			}
		},
		error:function(e){
			console.log(e);
		}
	})
	//点击查看详情
	$(".publiced ul").on("tap","li",function(){
		var index = $(this).attr("alarmId");
		localStorage.setItem("alarmId",index);
		window.location.href="emer-detail.html";
	})
})