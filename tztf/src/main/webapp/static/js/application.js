$(function() {


    /*左侧高度*/
    var _h = $(window).height();
    $(".g-bd-left").height(_h - $(".m-event").height() - 24);
    /*右侧高度*/
    $(".g-bd-right").height(_h - $(".m-event").height() - 24);
    $(".g-bd-right .h100").height(_h - $(".m-event").height() - 24);
    /*input宽度*/
    $(".g-bd-left-con").find("ul").each(function(n) {
        var conUlWidth = $(".g-bd-left-con ul").width() - 15;
        var conliWidth = $(this).find("span").width() + 20;
        if ($(this).hasClass("m-sets")) {
            $(this).find("li").css("display", "inline-block");
            $(this).find(".m-text-input").width((conUlWidth - conliWidth * 2) / 2 - 46);
            $(this).find(".m-text-input-text").width((conUlWidth - conliWidth * 2) / 2 - 86);

        } else if ($(this).hasClass("m-sets-sets")) {
            $(this).find("li").css("display", "inline-block");
            $(this).find(".m-text-input").width((conUlWidth - conliWidth * 3) / 3 - 51);
            $(this).find(".m-text-input-text").width((conUlWidth - conliWidth * 2) / 2 - 61);
        } else {
            $(this).find(".m-text-input").width(conUlWidth - conliWidth - 30);
            $(this).find(".m-text-textarea").width(conUlWidth - conliWidth - 30);
            $(this).find(".m-text-input-text").width(conUlWidth - conliWidth - 40);
        }
    });

    /*左侧超出内容加滚动条*/
    if ($(".g-bd-left-con").height() > $(".g-bd-left").height() - $(".g-bd-tit").height()) {
        var obj=$(".g-bd-left-con");
        obj.height($(".g-bd-left").height() - $(".g-bd-tit").height() - 25);
//        if(hasScroll(obj)) useNiceScroll(obj);
    }
    /*右侧超出内容加滚动条*/
    if ($(".g-bd-right .g-bd-right-modular").height() > $(".g-bd-right").height() - $(".g-bd-tit").height()) {
        var obj=$(".g-bd-right .g-bd-right-modular");
        obj.height($(".g-bd-right").height() - $(".g-bd-tit").height());
//        if(hasScroll(obj)) useNiceScroll(obj);   
    }
    /*限制字数*/
    //	$("#limitWord").keydown(function(){
    //        var curLength=$("#limitWord").val().length;    
    //        if(curLength>=100){
    //            var num=$("#limitWord").val().substr(0,99);
    //            $("#limitWord").val(num);
    //            $("#textCount").text(0);
    //        }
    //        else{
    //            $("#textCount").text(99-$("#limitWord").val().length);
    //        }
    //    })

    /*审批单宽度*/
    $(".m-text-textarea img").width($(".m-text-textarea").width());
    $(".m-text-textarea img").css("margin-top", "10px");

    /*右侧table的高度超过页面加滚动条*/
    if ($(".m-table").height() > $(".g-bd-right .h100").height() - $(".g-bd-tit").height()) {
        $(".g-bd-right").css("overflow-y", "hidden");
        $(".m-table").css("overflow-y", "scroll");
        $(".m-table").height($(".g-bd-right .h100").height() - $(".g-bd-tit").height() - 42);
    }
    //19-1-17修改
    $("#prescription").width($(".m-select.m-fbsx.m-text-input").width()-30);
    $(window).resize(function(){
    	$("#prescription").width($(".m-select.m-fbsx.m-text-input").width()-30);
    });
})
