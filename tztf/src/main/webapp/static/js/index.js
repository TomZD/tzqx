var context="";
// window.onload = function(){
// 	$('#loading-mask').fadeOut();
// }

var onlyOpenTitle="管理控制台";//不允许关闭的标签的标题

$(function(){
	getMenu(_menus);
/* 选择TAB时刷新内容
	$('#tabs').tabs({
        onSelect: function (title) {
            var currTab = $('#tabs').tabs('getTab', title);
            var iframe = $(currTab.panel('options').content);

			var src = iframe.attr('src');
			if(src)
				$('#tabs').tabs('update', { tab: currTab, options: { content: createFrame(src)} });

        }
    });
*/
});

function getMenu(d){
	 var menuText = "";	
	    for(var j=0;j<d.menus.length;j++){
			var dataList = d.menus[j];
			if(Number(needLength)==0&& dataList.id==4){
				continue;
			}
			var style = dataList.style;
			if(style=='notice_btn'){
				if(hasNotice=='1'){
					style = 'has_notice_btn';
				}
				if(hasPublish=='1'){
					style = 'has_notice_btn';
				}
			}
			menuText += "<li><a href='"+dataList.url+"' target=\"view_frame\"><i class='"+style+"'></i><span>"+dataList.text+"</span></a></li>";
		}
	    menuText += "<li class=\"user\"><a href=\"/logout\"><img src=\"/static/images/head_img.png\" /><p><span class=\"name\">"+username+"</span><span class=\"time\">退出</span></p></a></li>"
	    $("#menuText").html(menuText);
	    $(".m-nav.fr").find("li").eq(0).addClass("on_nav");
	    var urls = $(".m-nav.fr").find("li a").eq(0).prop("href");
	    $("#iframeMonitoring").attr("src",urls);  
} 




//弹出信息窗口 title:标题 msgString:提示信息 msgType:信息类型 [error,info,question,warning]

