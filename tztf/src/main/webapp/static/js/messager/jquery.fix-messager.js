;(function($) {
    $.extend({
        "showMessage":function(message){

            var $divToRemove = $('.jquery-fix-messager');
            if($divToRemove.length > 0)
                $divToRemove.remove();
            
            var messagerDom = '<div class="jquery-fix-messager"><div>';
            $(document.body).append(messagerDom);
            var $messageDiv = $('.jquery-fix-messager');
            $messageDiv.hide(0);
            $messageDiv.html(message);
            $messageDiv.fadeIn(800).delay(3000);

            $messageDiv.fadeOut(800);
        },

        "showSuccess":function(message) {
            var $divToRemove = $('.jquery-fix-messager');
            if($divToRemove.length > 0)
                $divToRemove.remove();

            var messagerDom = '<div class="jquery-fix-messager"><div>';
            $(document.body).append(messagerDom);
            var $messageDiv = $('.jquery-fix-messager');
            $messageDiv.hide(0);
            message = '<span class="jquery-fix-messager-success icon-ok-sign"></span> ' + message;
            $messageDiv.html(message);
            $messageDiv.fadeIn(800).delay(3000);

            $messageDiv.fadeOut(800);
        },
        
        "showWarning": function (message) {
            var $divToRemove = $('.jquery-fix-messager');
            if($divToRemove.length > 0)
                $divToRemove.remove();

            var messagerDom = '<div class="jquery-fix-messager"><div>';
            $(document.body).append(messagerDom);
            var $messageDiv = $('.jquery-fix-messager');
            $messageDiv.hide(0);
            message = '<span class="jquery-fix-messager-warning icon-exclamation-sign"></span> ' + message;
            $messageDiv.html(message);
            $messageDiv.fadeIn(800).delay(3000);

            $messageDiv.fadeOut(800);
        },

        "showError" : function(message) {
            var $divToRemove = $('.jquery-fix-messager');
            if($divToRemove.length > 0)
                $divToRemove.remove();

            var messagerDom = '<div class="jquery-fix-messager"><div>';
            $(document.body).append(messagerDom);
            var $messageDiv = $('.jquery-fix-messager');
            $messageDiv.hide(0);
            message = '<span style="color:red" class="icon-minus-sign"></span> ' + message;
            $messageDiv.html(message);
            $messageDiv.fadeIn(800).delay(3000);

            $messageDiv.fadeOut(800);
        },
        
        "showLeftMessageBox" : function(message){
        	 var $messageBoxDom = $('#jquery-left-message-box');
			 if($messageBoxDom.length > 0)
				 $messageBoxDom.remove();
			 
			 var tpl = 
				 '<div class="jquery-fix-message-box jquery-left-message-box" style="display:none" id="jquery-left-message-box">'
				 +    '<div class="jquery-fix-message-box-title">'
				 +        '<i class="icon-info-sign" style="line-height: 12px; padding-left:5px;width:80px">&nbsp;提示</i>'
				 +        '<a class="close_warn" id="jquery-left-message-box-close">×</a>'
				 +    '</div>'
				 +    '<p>' + message +'</p>'
				 +'</div>';
			 
			 var domHtml = tpl;
			 $(document.body).append(domHtml);
			 
			 $('#jquery-left-message-box').show(500);
			 
			 $('#jquery-left-message-box-close' ).click(function(){
				 var messageBoxdom = $('#jquery-left-message-box');
				 if(messageBoxdom.length > 0)
					 messageBoxdom.remove();
			 });
			 
			 $('#jquery-left-message-box').delay(1000*7).fadeOut(800);
        },
        
        "showListMessageBox" : function(message){
       	 var $messageBoxDom = $('#jquery-list-message-box');
			 if($messageBoxDom.length > 0)
				 $messageBoxDom.remove();
			 
			 var tpl = 
				 '<div class="jquery-fix-message-box jquery-list-message-box" style="display:none" id="jquery-list-message-box">'
				 +    '<div class="jquery-fix-message-box-title">'
				 +        '<i class="icon-info-sign" style="line-height: 12px; padding-left:5px;width:80px">&nbsp;解除提醒</i>'
				 +        '<a class="close_warn" id="jquery-list-message-box-close">×</a>'
				 +    '</div>'
				 +    '<p>' + message +'</p>'
				 +'</div>';
			 
			 var domHtml = tpl;
			 $(document.body).append(domHtml);
			 
			 $('#jquery-list-message-box').show(500);
			 
			 $('#jquery-list-message-box-close' ).click(function(){
				 var messageBoxdom = $('#jquery-list-message-box');
				 if(messageBoxdom.length > 0)
					 messageBoxdom.remove();
			 });
			 
			 $('#jquery-left-message-box').delay(1000*7).fadeOut(800);
       }
    });
})(jQuery);

;(function($) {
	$.extend({
		"showMessageBox" : function(message, okHandler, cancelHandler) {
			var $messageBoxDom = $('#jquery-message-box');
			 if($messageBoxDom.length > 0)
				 $messageBoxDom.remove();
			 
			 var tpl = 
				 '<div class="jquery-fix-message-box" style="display:block" id="jquery-message-box">'
				 +    '<div class="jquery-fix-message-box-title">'
				 +        '<i class="icon-info-sign" style="line-height: 12px; padding-left:5px;width:80px">&nbsp;提示</i>'
				 +        '<a class="close_warn" id="jquery-message-box-close">×</a>'
				 +    '</div>'
				 +    '<p>' + message + '</p>'
				 +    '<div class="jquery-fix-message-button-wrapper" style="margin-left:30%;">'
				 +        '<input type="button" value="确定" class="jquery-fix-message-button" id="jquery-message-box-ok">'
				 +        '<input type="button" value="取消" class="jquery-fix-message-button" id="jquery-message-box-cancel">'
				 +    '</div>'
				 +'</div>';
			 
//			 var data = {};
//			 data.message = message;
//			 var render = template.compile(tpl);
 //		     var domHtml = render(data);
			 var domHtml = tpl;
			 $(document.body).append(domHtml);
			 
			 $('#jquery-message-box-ok'    ).click(okHandler);
			 $('#jquery-message-box-cancel').click(cancelHandler);
			 $('#jquery-message-box-close' ).click(function(event){
				 var messageBoxdom = $('#jquery-message-box');
				 if(messageBoxdom.length > 0)
					 messageBoxdom.remove();
				 event.stopPropagation();
			 });
			 
			 $.draggable('#jquery-message-box');
		},
		
		"closeMessageBox": function(){
			var $messageBoxDom = $('#jquery-message-box');
			if($messageBoxDom.length > 0)
				 $messageBoxDom.remove();
		},
		
		"showInputBox":function(message, maxCount, okHandler){
			var $inputBox = $('#jquery-input-box');
			if($inputBox.length>0){
				$inputBox.remove();
			}
			
			var tpl = 
				'<div id="jquery-input-box" class="warn_block" style="display:block;height: 196px; position: fixed;top: 25%;left: 25%; z-index:100;">'
		      +      '<div class="wb_tit">'
		      +         '<a class="close-btn" id="jquery-input-box-close-btn">×</a>'
		      +      '</div>'
		      +     '<textarea id="jquery-input-box-text-area" placeholder="'+ message +'" style="margin: 10px 0px 0px; width: 269px; height: 89px;"></textarea>'
		      +     '<br>'
		      +     '<input type="button" class="jquery-fix-message-button" value="确定" id="jquery-input-box-btn">'
	          +  '</div>';
			
			 $(document.body).append(tpl);
			 $('#jquery-input-box-btn').click(okHandler);
			 
			 $('#jquery-input-box-close-btn').click(function(){
				 $('#jquery-input-box').remove();
			 });
			 
			 $('#jquery-input-box-text-area').keyup(function(){
				var text = $(this).val();
				if(text.length>maxCount){
					$(this).val(text.substr(0,maxCount));
					$.showWarning("输入字数不能超过" + maxCount + "字！");
					return;
				}
			 });
		},
		
		"inputBoxText":function(){
			return $('#jquery-input-box-text-area').val();
		},
		
		"closeInputBox":function(){
			var $inputBox = $('#jquery-input-box');
			if($inputBox.length>0){
				$inputBox.remove();
			}
		},
		
        "showWaiting":function(message) {
            var $divToRemove = $('.jquery-fix-messager');
            if($divToRemove.length > 0)
                $divToRemove.remove();

            var messagerDom = '<div class="jquery-fix-messager"><div>';
            $(document.body).append(messagerDom);
            var $messageDiv = $('.jquery-fix-messager');
            $messageDiv.hide(0);
            message = '<span class="jquery-fix-messager-success icon-spinner" style="display:inline-block;" id="icon-spinner-roll"></span> ' + message;
            $messageDiv.html(message);
            $messageDiv.fadeIn(800).delay(3000);
            
            window.setInterval("$('#icon-spinner-roll').css('transform', 'rotate(' + new Date().getTime()/20 + 'deg)');", 50);
        },
        
		"closeWaiting":function(){
			 var $divToRemove = $('.jquery-fix-messager');
             if($divToRemove.length > 0) {
            	 $divToRemove.fadeIn(800).delay(500).remove();
             }
		},
		
		"draggable": function(target){
            var $target = $(target);
            $target.prop('moving', false);
            $target.mousedown(function(e){
            	if(e.which !==1 || e.currentTarget!==e.target) {
            		return;
            	}
                $(this).prop('moving', true);
                $(this).prop('deltaX', e.offsetX);
                $(this).prop('deltaY', e.offsetY);
                $(this).prop('opacity', $(this).css('opacity'));
                $(this).css('opacity', 0.7);
            })
            
            $target.mousemove(function(e){
                if($target.prop('moving')===true) {
                    var offsetX = $target.prop('deltaX');
                    var offsetY = $target.prop('deltaY'); 

                    var deltaX = e.pageX  - offsetX + $target.width()/2;
                    var deltaY = e.pageY - offsetY + $target.height()/2;
                    
//                    if(deltaX === 0) {
//                        deltaX = e.pageX;
//                    }
//                    
//                    if(deltaY === 0) {
//                        deltaY = e.pageY;
//                    }
                    
                    $target.css('left', deltaX + 'px');
                    $target.css('top',  deltaY  + 'px');
                }
            })
            
            $target.mouseup(function(e){
               	if(e.which !==1) {
            		return;
            	}
            	
                $target.prop('moving', false);
                $target.css('opacity', $(this).prop('opacity'));
            })
            
            $target.mouseleave(function(){
                $target.prop('moving', false);
                $target.css('opacity', $(this).prop('opacity'));
            })
        }
		
	})

})(jQuery);
