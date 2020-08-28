define(function(require) {
	return OpenLayers.Class(OpenLayers.Control, {
		layers: null,
		colors: null,
		initialize: function(option) {

			if (typeof option != "object") return;
			this.moveTo = option.moveTo;
			this.showType = option.showType; //排列方式
			this.func = option.func;
			this.extendClass = option.extendClass || "";
			this.closeBtn = option.closeBtn;
			this.sizeBtn = option.sizeBtn;
			this.name = option.name;
			//格式化数据

			var content = option.content && option.content.replace(/(^\s*)|(\s*$)/g, "");
			if (!content) return;
			var arr = content.split(/[\r\n]+/);
			//获取标题,第一行第一部分包含非数字
			var name = arr[0].replace(/(^\s*)|(\s*$)/g, "");
			if (isNaN(name.split(" ")[0])) {
				this.title = name;
				arr.shift();
			}
			//获得色标数组
			var colors = [];
			for (var i = 0, l = arr.length; i < l; i++) {
				var un = arr[i].replace(/(^\s*)|(\s*$)/g, "").split(/\s+/); //根据空字符分割
				if (un.length > 1) {
					var rgb = un[1];
					var color = rgb.split(",");
					if (color.length == 4) {
						rgb = color[1] + "," + color[2] + "," + color[3];
					}
					colors.unshift({
						value: un[0], //值
						color: rgb, //颜色
						alias: un[2], //别名
						image: un[3] //图标
					});
				}
			}
			this.colors = colors;
			//排序与边界值
			if (colors.length > 1) {
				var v1 = colors[0].value;
				var v2 = colors[colors.length - 1].value;
				if (v1 - v2 > 0) {
					this.sort = false;
					this.maxValue = v1;
					this.minValue = v2;
				} else {
					this.sort = true;
					this.minValue = v1;
					this.maxValue = v2;
				}
			}

			//关联图层
			this.layers = [];
			this.addlayers(option.layers);

			OpenLayers.Control.prototype.initialize.apply(this);
		},
		draw: function(px) {

			this.displayClass = "olGiLegend " + this.extendClass;
			OpenLayers.Control.prototype.draw.apply(this, arguments);

			var scroll = document.createElement("div");
			scroll.className = "scrollbar";
			var span1 = document.createElement("span");
			var span2 = document.createElement("span");

			span1.className = "sb-hover";
			span2.className = "sb-circle";

			scroll.appendChild(span1);
			scroll.appendChild(span2);

			scroll.setAttribute("len", this.colors.length);
			//标题
			this.wrapBox = document.createElement("div");
			this.wrapBox.className = this.extendClass || "";

			this.headBox = document.createElement("span");
			this.divTitle = document.createElement("b");

			this.divTitle.innerText = this.title || "色标";
			this.headBox.appendChild(this.divTitle);

			//控制按钮
			if (this.closeBtn || this.sizeBtn) {
				this.addControlBtns();
			}
			//颜色列表
			this.divColors = document.createElement("ul");
			if (this.colors) {
				var html = "";
				for (var i = 0, l = this.colors.length; i < l; i++) {
					var un = this.colors[i];
					html += "<li>";
					html += "<i style='background:rgb(" + un.color + ")'></i>"; //颜色
					html += un.alias == null ? un.value : un.alias; //名字或值
					html += "</li>";
				}
				this.divColors.innerHTML = html;
			}
			this.wrapBox.appendChild(this.headBox);
			this.wrapBox.appendChild(this.divColors);
			this.div.appendChild(this.wrapBox);
			this.div.appendChild(scroll);
			this.showType && OpenLayers.Element.addClass(this.div, "olGiLegend_v");
			//注册事件
			this.events = new OpenLayers.Events(this, this.div);
			this.events.attachToElement(this.div);
			//	this.events.register("dblclick", this, this.ondblclick);
			this.events.register("touchend", this, this.onclick);
			this.events.register("click", this, this.onclick);

			return this.div;
		},

		// ondblclick: function(e) {
		// 	var divColorsStyle = this.divColors.style;
		// 	var divTitle = this.divTitle
		// 	if (divColorsStyle.display == "none") {
		// 		divColorsStyle.display = "";
		// 		divTitle.innerText = this.name || "";
		// 	} else {
		// 		divColorsStyle.display = "none";
		// 		divTitle.innerText = this.name || "色标";
		// 	}
		// 	OpenLayers.Event.stop(e);
		// },
		onclick: function(e) {
			var target = e.target || e.srcElement;
			var colors = this.colors;
			var v;
			var el;
			if (target.nodeName == 'LI') {
				el = target;
			} else if (target.parentNode.nodeName == 'LI') {
				el = target.parentNode;
			}

			if (el) {
				var arr = this.divColors.childNodes;

				var bl = false;
				var cla = "olGiLegend_hide";
				if (!OpenLayers.Element.hasClass(el, cla)) {
					el = el.previousSibling || el.previousElementSibling;
					bl = el == null;
				}
				for (var i = 0, l = arr.length; i < l; i++) {
					var n = arr[i];
					if (bl) {
						OpenLayers.Element.addClass(n, cla);
					} else {
						OpenLayers.Element.removeClass(n, cla);

						if (n == el) {
							bl = true;
							v = colors[i] && colors[i].value;
						}
					}
				}
				//如果数值不存在
				if (v == null) {
					v = this.sort ? this.minValue : this.maxValue;
				}
				//联动
				if (this.layers) {
					for (var i = 0, l = this.layers.length; i < l; i++) {
						var layer = this.layers[i];
						typeof layer.changeRange == "function" && layer.changeRange(v);
					}
				}
				//点击事件
				if (this.func && typeof this.func == "function") {
					this.func(v);
				}
				OpenLayers.Event.stop(e);
			}
		},
		addlayers: function(layers) {
			if (!layers) return;
			if (!(OpenLayers.Util.isArray(layers))) {
				layers = [layers];
			}
			for (var i = 0, l = layers.length; i < l; i++) {
				var layer = layers[i];
				layer.setRange(this.value, this.sort, this.maxValue, this.minValue);
				this.layers.push(layer);
			}
		},
		destroy: function() {
			this.layers = null;
			this.colors = null;
			OpenLayers.Control.prototype.destroy.apply(this);
		},
		addControlBtns: function() {
			var that = this;
			if (this.sizeBtn && !this.sizeBox) {
				this.sizeBox = document.createElement("i");
				this.sizeBox.className = "sizeBtn";
				this.headBox.appendChild(this.sizeBox);
				this.sizeBox.ontouchend = function () {
				    var status = this.className;
				    if (status.indexOf("on") > -1) {
				        that.maxSize.call(that);
				        this.className = this.className.replace("on", "");
				    } else {
				        that.minSize.call(that);
				        this.className = status + " on";
				    }
				};
				this.sizeBox.onclick = function() {
					var status = this.className;
					if (status.indexOf("on") > -1) {
						that.maxSize.call(that);
						this.className = this.className.replace("on", "");
					} else {
						that.minSize.call(that);
						this.className = status + " on";
					}
				}
			}
			if (this.closeBtn && !this.closeBox) {
				this.closeBox = document.createElement("i");
				this.closeBox.className = "closeBtn";
				this.closeBox.onclick = function() {
					that.close.call(that);
				};
				this.headBox.appendChild(this.closeBox);
			}
		},
		close: function(e) {
			this.destroy();
		},
		minSize: function() {
			var divColorsStyle = this.divColors.style;
			divColorsStyle.display = "none";
		},
		maxSize: function() {
			var divColorsStyle = this.divColors.style;
			divColorsStyle.display = "";
		},
		CLASS_NAME: "Gi.legend"
	});
});