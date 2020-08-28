define(function(require) {
	return OpenLayers.Class(OpenLayers.Control, {
		//回调函数，在onButtonClick事件中被调用，接收参数:底图图层对象
		onClick: null, 
		onButtonClick: function(evt) {
			var button = evt.buttonElement;
			if (this.labels) {
				for (var i = 0, l = this.labels.length; i < l; i++) {
					if (button == this.labels[i]) {
						var layer = button._layer;
						this.map.setBaseLayer(layer);
						typeof this.onClick == "function" && this.onClick(layer);
						return false;
					}
				}
			}
		},
		destroy: function() {
			this.map.events.un({
				buttonclick: this.onButtonClick,
				addlayer: this.redraw,
				removelayer: this.redraw,
				changebaselayer: this.changeLayer,
				scope: this
			});
			this.events.unregister("buttonclick", this, this.onButtonClick);
			OpenLayers.Control.prototype.destroy.apply(this, arguments);
		},
		setMap: function(map) {
			OpenLayers.Control.prototype.setMap.apply(this, arguments);

			this.map.events.on({
				addlayer: this.redraw,
				removelayer: this.redraw,
				changebaselayer: this.changeLayer,
				scope: this
			});
			if (this.outsideViewport) {
				this.events.attachToElement(this.div);
				this.events.register("buttonclick", this, this.onButtonClick);
			} else {
				this.map.events.register("buttonclick", this, this.onButtonClick);
			}
		},
		draw: function(px) {
			OpenLayers.Control.prototype.draw.apply(this, [px]);
			this.redraw();
			return this.div;
		},
		redraw: function() {
			var baselayers = this.map.getLayersBy("isBaseLayer", true);
			if (!this.baselayers || baselayers.length != this.baselayers.length) {
				this.div.innerHTML = "";
				this.baselayers = [];
				this.labels = [];
				for (var i = 0, l = baselayers.length; i < l; i++) {
					//缓存底图对象
					var layer = this.baselayers[i] = baselayers[i];
					//绘制
					var label = this.labels[i] = document.createElement("div");
					label.innerHTML = layer.alias || layer.name;
					OpenLayers.Element.addClass(label, "olButton olButton-" + layer.name);
					label._layer = layer;

					this.div.appendChild(label);
				}
				this.changeLayer();
			}
		},
		changeLayer: function() {
			var layer = this.map.baseLayer;
			if (this.labels) {
				//当前选中的按钮
				var cur;
				for (var i = 0, l = this.labels.length; i < l; i++) {
					var button = this.labels[i];
					if (button._layer == layer) {
						cur = i;
						OpenLayers.Element.addClass(this.labels[cur], "olButton-cur");
					} else {
						OpenLayers.Element.removeClass(button, "olButton-cur");
					}
					OpenLayers.Element.removeClass(button, "olButton-next");
				}
				//下一个按钮
				var next;
				if (cur < this.labels.length - 1) {
					next = cur + 1;
				} else {
					next = 0;
				}
				OpenLayers.Element.addClass(this.labels[next], "olButton-next");
			}
		},
		CLASS_NAME: "Gi.BaseLayerSwitcher"
	});
});