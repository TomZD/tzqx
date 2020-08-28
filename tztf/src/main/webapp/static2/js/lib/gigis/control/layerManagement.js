define(function(require) {
	return OpenLayers.Class(OpenLayers.Control.LayerSwitcher, {
		onButtonClick: function(evt) {
			var button = evt.buttonElement;
			if (button.parentElement && button.parentElement.parentElement === this.div) {
				var txt = button.innerHTML;
				var map = this.map;
				var layers = map.layers;
				var baseLayer = map.baseLayer;
				var curLayer = null;
				if (layers.length > 0) {
					for (var i = 0, len = layers.length; i < len; i++) {
						var layer = layers[i];
						if (layer.name.indexOf(txt) > -1) {
							curLayer = layer;
							//如果不归属于当前的底图组，则返回
							if (typeof layer.enabled != "undefined" && layer.group != baseLayer.group) {
								return;
							}
							//如果底图不可见，则其所有成员的操作撤回
							if(typeof layer.enabled !="undefined" && layer.group==baseLayer.group && !baseLayer.visibility){
								return;
							}
							if (layer.visibility) {
								layer.setVisibility(false);
								//当非底图的当前组成员点击后添加 enabled标记
								if (layer.group && !layer.isBaseLayer && layer.group == baseLayer.group) {
									layer.enabled = false;
								}
							} else {
								layer.setVisibility(true);
								if (layer.isBaseLayer) {
									this.map.setBaseLayer(layer);
								}
								if (layer.group && !layer.isBaseLayer && layer.group == baseLayer.group) {
									layer.enabled = true;
								}
							}
							break;
						}
					}
					for (var i = 0, len = layers.length; i < len; i++) {
						var layer = layers[i];
						//底图隐藏，属于当前底图组的成员隐藏
						if (!layer.isBaseLayer && !baseLayer.visibility && layer.group == baseLayer.group) {
							layer.setVisibility(false);
						}
						//如果选中层为可见底图，判断其成员隐藏显示状态enabled 再显示
						if (curLayer.isBaseLayer && curLayer.visibility && layer.group == curLayer.group && typeof layer.enabled != "undefined") {
							if (layer.enabled == false) {
								layer.setVisibility(false);
							} else {
								layer.setVisibility(true);
							}
						}
					}
				}
			}
		},
		draw: function(px) {
			OpenLayers.Control.prototype.draw.apply(this, [px]);
			this.redraw();
			return this.div;
		},
		redraw: function() {
			this.div.innerHTML = "";
			var map = this.map;
			var layers = map.layers;
			var len = layers.length;
			var baseLayer = map.baseLayer;
			//底图div
			var baseBox = document.createElement("div");
			//其他div
			var otherBox = document.createElement("div");
			baseBox.innerHTML = "<h4>底图</h4>";
			otherBox.innerHTML = "<h4>其他</h4>";
			if (len > 0) {
				for (var i = 0; i < len; i++) {
					var layer = layers[i];
					var visable = layer.visibility;
					var label = document.createElement("span");
					OpenLayers.Element.addClass(label, "olButton");
					label.innerHTML = layer.name;
					if (layer.isBaseLayer) {
						baseBox.appendChild(label);
					} else {
						if (layer.group) {
							if (layer.group == baseLayer.group) {
								//如果enabled已经标记过 则再重绘时不再标记
								if (typeof layer.enabled == "undefined") {
									layer.enabled = true;
								}
							} else {
								if (typeof layer.enabled == "undefined") {
									layer.enabled = false;
								}
							}
						}
						otherBox.appendChild(label);
					}
					//如果图层不可见，给眼睛添加状态
					if (visable == false) {
						OpenLayers.Element.addClass(label, "z-un");
						//当联动组不属于当前底图 则成灰色状态
						if (!layer.isBaseLayer && layer.group && (layer.group != baseLayer.group || (layer.group == baseLayer.group && baseLayer.visibility == false))) {
							OpenLayers.Element.addClass(label, "z-on");
						}
					} else {
						label.className = label.className.replace(" z-un", "");
					}
				}
				this.div.appendChild(baseBox);
				this.div.appendChild(otherBox);
			}
			return this.div;
		},
		CLASS_NAME: "Gi.LayerManagement"
	});
});