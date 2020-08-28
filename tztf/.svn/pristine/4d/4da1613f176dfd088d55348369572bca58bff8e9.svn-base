define('components/warnsBox.js', function(require, exports, module){ var template = require("lib/template");
var tpl = "<div class=\"m-warnsbox\">\r\n    <ul>\r\n        <%\r\n            for(var i=0,l=$data.length;i<l;i++){\r\n                var n=$data[i];\r\n                var len=n.content.length;\r\n        %>\r\n        <li>\r\n            <img src=\"<%=n.url%>\" alt=\"<%=n.type%><%=n.level%>\" />\r\n            <p\r\n                <%if(len>27){%>\r\n                    style=\"font-size:15px;line-height:16px;\"\r\n                <%}%>\r\n            >\r\n                <%=n.content%>\r\n            </p>\r\n        </li>\r\n        <%}%>\r\n    </ul>\r\n    <div class=\"btns\">\r\n        <i class=\"prev-btn\"></i>\r\n        <i class=\"next-btn\"></i>\r\n    </div>\r\n</div>";
var mode = require("mode/warns");
var el,
	list,
	size,
	timer,
	cur;

var prev = function() {
	cur && cur.stop();
	cur = el.find("ul").find("li:last");
	cur.css({
			'height': '0px',
			'opacity': '0'
		})
		.prependTo(el.find("ul"))
		.animate({
			'height': size + 'px',
			'opacity': '1'
		}, '100', function() {
			$(this).removeAttr('style');
		});
};
var next = function() {
	cur && cur.stop();
	cur = el.find("ul").find("li:first");
	cur.animate({
		'height': 0 + 'px',
		'opacity': '1'
	}, '100', function() {
		$(this).removeAttr('style');
		$(this).appendTo(el.find("ul"));
	});
};

return function(box) {
	mode(function(json) {
		if(json){
	        box.addClass('haswarns');
			el = $(template.compile(tpl)(json)).prependTo(box);
			list = el.find("li");
			size = 68;
			timer = setInterval(prev, 3000);

			el.mouseenter(function() {
				clearInterval(timer);
				cur && cur.finish();
			});
			el.find(".prev-btn").click(prev);
			el.find(".next-btn").click(next);
		}
	});
}; 
});