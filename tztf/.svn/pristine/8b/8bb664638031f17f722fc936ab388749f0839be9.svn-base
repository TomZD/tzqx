define('components/gi-mask.js', function(require, exports, module){ /*
 *全屏带蒙层的弹出框插件
 *使用须知：
 *	1.如果需要兼容ie低版本，半透明背景不建议使用滤镜实现
 *	2.显示时会对父容器添加class控制父容器不出现滚动条,可能引发其他计算高度或布局问题
 *	3.在ie6中显示时,父级会添加height:100%,同样会引发问题
 */

function getClientHeight() {
	//可见高
	var clientHeight = document.body.clientHeight; //其它浏览器默认值
	if (navigator.userAgent.indexOf("MSIE 6.0") != -1) {
		clientHeight = document.body.clientHeight;
	} else if (navigator.userAgent.indexOf("MSIE") != -1) {
		//IE7 IE8
		clientHeight = document.documentElement.offsetHeight
	}

	if (navigator.userAgent.indexOf("Chrome") != -1) {
		clientHeight = document.body.scrollHeight;
	}

	if (navigator.userAgent.indexOf("Firefox") != -1) {
		clientHeight = document.documentElement.scrollHeight;
	}
	return clientHeight;
}
var closeTmpl = "<div class='gi-mask-close'><i></i></div>",
	loadingTmpl = "<div class='gi-mask-loading'></div>";
var boxTmpl = "<div class='gi-mask-box'>";
// 兼容ie6，7bug 当table宽度100%，父容器设置了宽度并且出现滚动条时，table会撑破父容器
boxTmpl += "<div style='*zoom:1;height:100%;'>"; //兼容ie6，7
boxTmpl += "<table class='gi-mask-table'><tr><td class='gi-mask-td'>";
boxTmpl += "<div class='gi-mask-content'>1</div>";
boxTmpl += "</td><tr></table>";
boxTmpl += "</div>"; //兼容ie6，7
boxTmpl += "</div>";
/*
 * options  配置说明
 * box          是否结构已经存在，默认：null
 * contentHtml  内容(html字符串)，默认:loadingTmpl
 * contentClass 控制内容的样式
 * vertical     垂直对齐方式
 * isHeightFull 高度是否等于可是区域 默认:false
 * target       触发按钮
 * hasClose     是否需要关闭按钮
 * quicklyClose 快速关闭（即点击内容以外区域自动关闭）默认：false
 * initEv       初始化执行事件
 * closeEv      关闭事件
 */
var Mask = function(options) {
	var that = this;
	this.options = options || {};
	this._first = true; //第一次执行

	//蒙层
	this.box = this.options.box;

	//查找父容器
	this.parent = $("html,body"); //兼容ie6，7，必须带上html

	//点击事件
	this.clickEv = this.options.clickEv;

	//触发的目标
	this.target = this.options.target;
	if (this.target) {
		this.showEv = function(event) {
			event.stopPropagation();
			typeof that.clickEv == "function" && that.clickEv(event);
			that.show();
		};
		this.target.bind("click", this.showEv);
	}
};


Mask.prototype._init = function() {
	var that = this,
		options = this.options || {},
		contentHtml = options.contentHtml,
		cls = options.contentClass,
		initEv = options.initEv;

	//容器与蒙层
	if (!this.box) {
		this.box = $(boxTmpl).appendTo("body");
		this.box.find(".gi-mask-td").css("vertical-align", options.vertical || "middle"); //设置垂直对齐方式
	}
	this.content = this.box.find(".gi-mask-content");
	cls && this.content.addClass(cls); //设置额外样式

	//计算高度
	if (options.isHeightFull) {
		var baseHeight = that.content.outerHeight(true) - that.content.height(); //初始高度
		this.resizeEv = function() {
			var wh = $(window).height();
			that.content.css({
				"height": wh - baseHeight - 1 + "px" //-1 兼容ie6，7中1px的bug
			});
			that.box.css({
				"height": wh + "px"
			});
		};
	} else {
		this.resizeEv = function() {
			that.box.css({
				"height": $(window).height() + "px"
			});
		};
	}
	this.resizeEv();
	$(window).bind("resize", this.resizeEv);

	//装载内容
	if (typeof contentHtml != "string" && typeof contentHtml != "number") {
		contentHtml = loadingTmpl;
	}
	this.setContent(contentHtml);

	//关闭
	this.closeEv = options.closeEv;
	this.quicklyClose = options.quicklyClose;

	if (options.hasClose) {
		this.close = $(closeTmpl).appendTo(this.box);
		this.close.bind("click", function(event) {
			event.stopPropagation();
			that.hide();
		});
	}

	this._first = false; //更新状态
	typeof initEv == "function" && initEv.call(this, event); //执行
};
Mask.prototype.show = function() {
	var that = this;

	//必须在初始化之前使用，否则，在body有横向滚动条的情况下，计算可视窗口高度不会包含滚动条
	this.parent.addClass('gi-mask-on');

	if (this._first) {
		this._init();
	}

	this.box.show();

	if (this.quicklyClose) {
		this.content.bind("click", function(event) {
			event.stopPropagation();//容易导致绑定content的冒泡事件禁用了
		});

		this._hide = function() {
			that.hide();
		};
		$(document).bind("click", this._hide);
	}
};
Mask.prototype.hide = function() {
	this.box.hide();
	this.parent.removeClass('gi-mask-on');
	typeof this.closeEv == "function" && this.closeEv();

	if (this.quicklyClose) {
		this.content.unbind("click");
		$(document).unbind("click", this._hide);
	}
};
Mask.prototype.setContent = function(html) {
	if (this.content) {
		this.content.html(html);
	} else {
		this.options.contentHtml = html;
	}
};
Mask.prototype.destroy = function() {
	if (this.box) {
		this.box.remove();
	}
	this.box = null;
	this.content = null;
	this.close = null;

	this.clickEv=null;

	$(document).unbind("click", this._hide);
	this._hide = null;

	if (this.target) {
		this.target.unbind("click", this.showEv);
	}
	this.target = null;
	this.showEv = null;

	this.resizeEv && $(window).unbind("resize", this.resizeEv);
	this.parent.removeClass('gi-mask-on');
	this.parent = null;
};

//接口
return function(options) {
	return new Mask(options);
}; 
});