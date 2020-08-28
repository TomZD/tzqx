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
    })
    /*显示相对应数据*/
    $(".m-select a.label").click(function () {
        var index = $(".label").index($(this));
        $(this).parents(".m-select").find(".lable-title:eq(0)").html($(this).html());
    });

    /*复选框选中*/
    $(".checkbox").on("click", function () {
        $(this).toggleClass("on_check");
        if($(this).hasClass("on_check")){
            $(this).parent().find("input").prop("checked","checked");
        }else{
            $(this).parent().find("input").removeAttr("checked");
        }
    })
    /*单选框选中*/
    $(".radio").on("click", function () {
        $(".checkbox_bd").find(".radio").removeClass("on_input_check");
        $(".checkbox_bd").find("input").removeAttr("checked");
        $(this).addClass("on_input_check").siblings("input").prop("checked","checked");
    })
    
	//点击删除按钮tr删除
    $(".btn-delete").click(function() {
        // $(selector)通过选择器表示要删除的元素，remove()函数用以删除元素
        $(this).parents("tr").remove(); 
    });
    /*点击取消关闭弹窗*/
    $(".abandon").click(function () {
        $(".m-popup").hide();
    })
    /*点击确定关闭弹窗*/
    $(".m-event-button").click(function () {
        $(".m-popup").hide();
    })
})