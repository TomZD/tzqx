$(function(){
	/*head切换*/
	$(".m-nav").on("click","li",function(){
        $(this).siblings().removeClass("on_nav").end().addClass('on_nav');
    });

    //页码切换
    $(".m-page").on("click","a",function(e) {
        $(this).siblings().removeClass("on_page").end().addClass('on_page');
        e.preventDefault();
    });
    
    /*下拉框*/
    $(".m-select").click(function () {
        $(this).find(".dropdown-menu").fadeToggle(200);
        $(this).siblings().find(".dropdown-menu").hide();
    })
    /*显示相对应数据*/
      $(".m-select a.label").click(function () {
        var index = $(".label").index($(this));
        $(this).parents(".m-select").find(".lable-title:eq(0)").html($(this).html());
        departname = $(this).html();
        $("#username").val(departname);
    });

    /*复选框选中*/
    $(".checkbox").on("click", function () {
    	//jinb
//    	if(($(this).parent().find("input").attr("name") == "channelCheckBoxes")){
//    		var color = $('#typeColor option:selected').text();
//    		var channel=$(this).parent().find("input").val();
//    		if(color=='蓝色'||color=='黄色'){
//    			if(channel=='channel_smsVIP'||channel=='channel_smsAll'||channel=='channel_wasu'){
//    				layer.msg("预警级别不够无法选择");
//    				return;
//    			}
//    		}
//    		//refreshChannelBtns();
//        }
        $(this).toggleClass("on_check");

        if($(this).is('.on_check')){
        	$(this).parent().find(".radioclass").prop("checked","true");
        	
        	//jinb
        	var countryButton = $(this).attr("data-id");
        	if(countryButton!=undefined){
        		$('.checkboxAreaAll[data-id="'+countryButton+'"]').trigger("click");
        	}
        	//jinb
        	if(($(this).parent().find("input").attr("name") == "channelCheckBoxes")){
        		refreshChannelBtns();
            }
        	
        	 //17-09-05-倪佳慧-如果子项没有全选时，全选按钮不选中
    	    //jinb start
    	    var dataId = $(this).parents(".checkbox_bd").find(".checkboxAreaAll").attr("data-id");
    	    if( dataId != undefined){
        	    if($('.checkboxArea .'+dataId+':checked').length==$('.checkboxArea .'+dataId+'').length ){
                	$(this).parents(".checkbox_bd").find(".checkboxAreaAll").addClass("on_check");
                	$(this).parents(".checkbox_bd").find(".checkboxAreaAll").parent().find(".radioclass").prop("checked", true);
            		$('.checkbox[data-id="'+dataId+'"]').parents(".m-checkbox.checkboxArea").find(".radioclass").prop("checked", true);
                    $('.checkbox[data-id="'+dataId+'"]').parents(".m-checkbox.checkboxArea").find(".checkbox").addClass("on_check");
        	    }else{
        	    	$('.checkbox[data-id="'+dataId+'"]').parents(".m-checkbox.checkboxArea").find(".radioclass").prop("checked", true);
                    $('.checkbox[data-id="'+dataId+'"]').parents(".m-checkbox.checkboxArea").find(".checkbox").addClass("on_check");
        	    }
        	    
        	  //jinb start
                if($('input[name="pubRangeNameTown"]:checked').length==$('input[name="pubRangeNameTown"]').length){
                	  $(".checkboxAreaAll").parents(".checkbox_bd").find(".radioclass").prop("checked", true);
                      $(".checkboxAreaAll").parents(".checkbox_bd").find(".checkbox").addClass("on_check");
                      $(".checkboxAreaAll").addClass("on_check");
                }else if($('input[name="pubRangeNameTown"]:checked').length==0){
                	 $(".checkboxAreaAll").parents(".checkbox_bd").find(".radioclass").prop("checked", false);
   	        	     $(".checkboxAreaAll").parents(".checkbox_bd").find(".checkbox").removeClass("on_check");
                     $(".checkboxAreaAll").removeClass("on_check");
                }
                //jinb end
        	    return;
    	    }
    	    //jinb end
        	
        	//17-09-05-倪佳慧-如果子项没有全选时，全选按钮不选中
    	    if($('.checkboxArea input[name="pubRangeName"]:checked').length==$('.checkboxArea input[name="pubRangeName"]').length ){
            	$(this).parents(".checkbox_bd").find(".checkboxAreaAll").addClass("on_check");
            	$(this).parents(".checkbox_bd").find(".checkboxAreaAll").parent().find(".radioclass").prop("checked", true);
            	
    	    }else{
            	$(this).parents(".checkbox_bd").find(".checkboxAreaAll").removeClass("on_check");
            	$(this).parents(".checkbox_bd").find(".checkboxAreaAll").parent().find(".radioclass").removeAttr("checked");
    	    }
    	    if($('.checkbox_style input[name="channelCheckBoxes"]:checked').length==$('.checkbox_style input[name="channelCheckBoxes"]').length){
            	$(this).parents(".checkbox_bd").find(".checkboxChannelAll").addClass("on_check");
            	$(this).parents(".checkbox_bd").find(".checkboxChannelAll").parent().find(".radioclass").prop("checked", true);
            	
    	    }else{
            	$(this).parents(".checkbox_bd").find(".checkboxChannelAll").removeClass("on_check");
            	$(this).parents(".checkbox_bd").find(".checkboxChannelAll").parent().find(".radioclass").removeAttr("checked");
    	    }
        	 
        }else{
        	//17-09-05-倪佳慧-如果子项没有全选时，全选按钮不选中
        	$(this).parent().find(".radioclass").removeAttr("checked");
        	$(this).parents(".checkbox_bd").find(".checkboxAreaAll").removeClass("on_check");
        	$(this).parents(".checkbox_bd").find(".checkboxAreaAll").parent().find(".radioclass").removeAttr("checked");
        	$(this).parents(".checkbox_bd").find(".checkboxChannelAll").removeClass("on_check");
        	$(this).parents(".checkbox_bd").find(".checkboxChannelAll").parent().find(".radioclass").removeAttr("checked");
        	//17-09-05-倪佳慧-如果子项没有全选时，全选按钮不选中
        	//jinb start
        	var countryButton = $(this).attr("data-id");
        	if(countryButton!=undefined){
        		$('.checkboxAreaAll[data-id="'+countryButton+'"]').trigger("click");
        	}
        	
        	var dataId = $(this).parents(".checkbox_bd").find(".checkboxAreaAll").attr("data-id");
      	    if(dataId != undefined){
                  	$(this).parents(".checkbox_bd").find(".checkboxAreaAll").removeClass("on_check");
                  	$(this).parents(".checkbox_bd").find(".checkboxAreaAll").parent().find(".radioclass").removeAttr("checked");
                  	if($('.checkboxArea .'+dataId+':checked').length==0){
                  		 $('.checkbox[data-id="'+dataId+'"]').parents(".m-checkbox.checkboxArea").find(".radioclass").prop("checked", false);
                         $('.checkbox[data-id="'+dataId+'"]').parents(".m-checkbox.checkboxArea").find(".checkbox").removeClass("on_check");
                  	}
      	    }
      	 //jinb end
        	if(($(this).parent().find("input").attr("name") == "channelCheckBoxes")){
        		var color = $('#typeColor option:selected').text();
        		var channel=$(this).parent().find("input").val();
        		if(color=='蓝色'||color=='黄色'){
        			if(channel=='channel_smsVIP'||channel=='channel_smsAll'||channel=='channel_wasu'){
        				return;
        			}
        		}

        		refreshChannelBtns();
            }
        }
        //jinb start
        if($('input[name="pubRangeNameTown"]:checked').length==$('input[name="pubRangeNameTown"]').length){
      	  $(".checkboxAreaAll").parents(".checkbox_bd").find(".radioclass").prop("checked", true);
            $(".checkboxAreaAll").parents(".checkbox_bd").find(".checkbox").addClass("on_check");
            $(".checkboxAreaAll").addClass("on_check");
      }else if($('input[name="pubRangeNameTown"]:checked').length==0){
      	 $(".checkboxAreaAll").parents(".checkbox_bd").find(".radioclass").prop("checked", false);
     	     $(".checkboxAreaAll").parents(".checkbox_bd").find(".checkbox").removeClass("on_check");
           $(".checkboxAreaAll").removeClass("on_check");
      }
        //jinb end
    })
    /*单选框选中*/
    $(".radio").on("click", function () {
        $(".checkbox_bd").find(".radio").removeClass("on_input_check")
        $(this).addClass("on_input_check").siblings();
    })
    
	//点击删除按钮tr删除
    //$(".btn-delete").click(function() {
        // $(selector)通过选择器表示要删除的元素，remove()函数用以删除元素
     //   $(this).parents("tr").remove(); 
    //});
    /*点击取消关闭弹窗*/
    $(".abandon").click(function () {
        $(".m-popup").hide();
    })
    /*点击确定关闭弹窗*/
    $(".m-event-button").click(function () {
        //$(".m-popup").hide();
    })
});
// 使用滚动条插件
//判断是否存在滚动条
var hasScroll=function(obj){
    var flag;
    obj.scrollTop(10);//控制滚动条下移10px  
    if(obj.scrollTop()>0 ){  
        flag=true; 
    }else{  
        flag=false;  
    }  
    obj.scrollTop(0);//滚动条返回顶部 
    return flag; 

}
//使用滚动条
var useNiceScroll=function(obj){
    obj.niceScroll({
        cursorcolor: "#59617c", //滚动条的颜色
        cursoropacitymax: 1, //滚动条的透明度，从0-1
        touchbehavior: false, //使光标拖动滚动像在台式电脑触摸设备
        cursorwidth: "5px", //滚动条的宽度
        cursorborder: "0", // 游标边框css定义
        cursorborderradius: "5px", //以像素为光标边界半径  圆角
        autohidemode: false, //是否隐藏滚动条  true的时候默认不显示滚动条，当鼠标经过的时候显示滚动条
        zindex: "auto", //给滚动条设置z-index值
        railpadding: { top: 0, right: 0, left: 0, bottom: 0 } //滚动条的位置
    });
}