var count1=0;
var count2=0;
$(function(){
	var $list = $("#fileList");
	// 初始化Web Uploader
    var uploader = WebUploader.create({
        // 选完文件后，是否自动上传。
        auto: true,
        // swf文件路径
        swf: '/resources/static/lib/webuploader/0.1.5/Uploader.swf',
        // 文件接收服务端。
        server: '/upload',
        // 重复上传图片，true为可重复false为不可重复
        duplicate :true,
        // 选择文件的按钮。可选。
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        //pick: '#filePicker',
        pick: {
        	id:$("#filePicker"), // id
            multiple: false  // false  单选 
        },
        // 只允许选择图片文件。
        accept: {
            title: 'Images',
            extensions: 'gif,jpg,jpeg,bmp,png',
            mimeTypes: 'image/jpg,image/jpeg,image/png'
        },
        fileSingleSizeLimit: 0.5 * 1024 * 1024//0.5M
    });
    
    if($("#imagePathList").val()!=""){
	    count1=$("#imagePathList").val().split(',').length;
	    count2=$("#imagePathList").val().split(',').length;
    }
    
    // 当有文件添加进来的时候
    uploader.on( 'fileQueued', function( file ) {
    	//count1=count2;
    	if(count1>=5){
    		layer.msg('最多上传五张照片!', {icon: 2, time: 1000});
    		return false;
    	}else{
    		count1++;
//	        var $li = $(
//	            '<div id="' + file.id + '" class="file-item thumbnail">' +
//	            '<img>' +
//	            '<div class="info">' + file.name + '</div>' +
//	            '<em></em>' +
//	            '</div>'
//	        ),
//	
//	        $img = $li.find('img');
//	        $list.append( $li );
//	
//	        // 创建缩略图
//	        // 如果为非图片文件，可以不用调用此方法。
//	        // thumbnailWidth x thumbnailHeight 为 100 x 100
//	        uploader.makeThumb( file, function( error, src ) {
//	            if ( error ) {
//	                $img.replaceWith('<span>不能预览</span>');
//	                return;
//	            }
//	
//	            $img.attr( 'src', src)
//	        }, 100, 100 );
    	}
    });

    // 文件上传成功
    uploader.on( 'uploadSuccess', function( file ,data) {
        $( '#'+file.id ).find('p.state').text('上传成功！');
        if(count2>=5){
    		//layer.msg('最多上传五张照片!', {icon: 2, time: 1000});
    		return false;
    	}else{
    		count2++;
	        if($("#imagePathList").val()!=''){
	            $("#imagePathList").val($("#imagePathList").val()+","+data.data);
	        }else{
	            $("#imagePathList").val(data.data);
	        }
	        
	        var $li = $(
		            '<div id="' + file.id + '" class="file-item thumbnail">' +
		            '<img>' +
		            '<div class="info">' + file.name + '</div>' +
		            '<em></em>' +
		            '</div>'
		        ),
		
		        $img = $li.find('img');
		        $list.append( $li );
		
		        // 创建缩略图
		        // 如果为非图片文件，可以不用调用此方法。
		        // thumbnailWidth x thumbnailHeight 为 100 x 100
		        uploader.makeThumb( file, function( error ) {
		            if ( error ) {
		                $img.replaceWith('<span>不能预览</span>');
		                return;
		            }
		
		            $img.attr( 'src', "/upload/"+data.data)
		        }, 100, 100 );
    	}
    });
    
    // 文件上传失败，显示上传出错。
    uploader.on( 'uploadError', function( file ) {
        $( '#'+file.id ).find('p.state').text('上传出错!');
    });
});

/**
 * [remove 移除图片]
 * @post  [file]  [文件名 （必填）]
 * @post  [obj]  [操作对象] [前台操作数据 移除dom标志]
 */
$("body").on("click","#fileList>div.file-item em",function(){
    $(this).parent().remove();
    var imgUrl = $(this).parent().find("img").attr("src").substring(8);
    var $url =  $("#imagePathList").val();
    $ul = $url.split(",");
    var urlStr ="";
    count2--;
    count1--;
    if($ul.length==1){
    	$("#imagePathList").val("");
    }else{
	    for(var i=0,ii=$ul.length;i<ii;i++){
	        if(imgUrl.indexOf($ul[i])>-1){
	
	        }else{
	            urlStr +=$ul[i]+",";
	        }
	    }
	    urlStr = urlStr.substring(0,urlStr.length -1);
	    $("#imagePathList").val(urlStr);
    }
})