define(function(require) {
	return OpenLayers.Class(OpenLayers.Control, {
		colors: null,
		initialize: function(options) {
			if (typeof options == "object") {
				this.moveTo = options.moveTo;
				if (options.legend && options.legend.length) {

					this.legendType = options.lType;
					this.colors = options.legend;
					this.name = options.name;
				}
			}
			OpenLayers.Control.prototype.initialize.apply(this);
		},
		draw: function(px) {
			var style = this.legendType == "point" ? "background" : "border-top-color";
			this.displayClass = "ol" + this.legendType + "Legend";
			OpenLayers.Control.prototype.draw.apply(this, arguments);
			//标题
			this.divTitle = document.createElement("b");
			if (this.name != null) {
				this.divTitle.innerText = this.name;
			}
			//颜色列表
			this.divColors = document.createElement("ul");
			if (this.colors) {
				var html = "";
				for (var i = 0, l = this.colors.length; i < l; i++) {
					var un = this.colors[i];
					if (!un.alias) break;
					html += "<li>";
					html += "<i style='" + style + ":" + (un.style) + "'></i>"; //颜色
					html += un.alias || ""; //名字或值
					html += "</li>";
				}
				this.divColors.innerHTML = html;
			}

			this.div.appendChild(this.divTitle);
			this.div.appendChild(this.divColors);
			this.showType && OpenLayers.Element.addClass(this.div, "olGiTyphoonLegend_v");
			//注册事件
			this.events = new OpenLayers.Events(this, this.div);
			this.events.attachToElement(this.div);
			this.events.register("dblclick", this, this.ondblclick);
			this.events.register("click", this, this.onclick);

			return this.div;
		},
		ondblclick: function(e) {
			var divColorsStyle = this.divColors.style;
			var divTitle = this.divTitle
			if (divColorsStyle.display == "none") {
				divColorsStyle.display = "";
				divTitle.innerText = this.name || "";
			} else {
				divColorsStyle.display = "none";
				divTitle.innerText = this.name || "色标";
			}
			OpenLayers.Event.stop(e);
		},
		destroy: function() {
			this.layers = null;
			this.colors = null;
			OpenLayers.Control.prototype.destroy.apply(this);
		},
		CLASS_NAME: "Gi.Typhoonlegend"
	});
});