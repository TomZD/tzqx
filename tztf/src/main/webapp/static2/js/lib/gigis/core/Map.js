define(function() {
	return OpenLayers.Class(OpenLayers.Map, {
		Z_INDEX_BASE: {
			BaseLayer: 100, //底图
			Overlay: 325, //普通层
			Boundary: 400, //边界
			Feature: 725, //要素层
			Markers: 726, //点marker 防止与聚合点重叠
			Popup: 750, //弹框
			Control: 1000 //控件
		},
		initialize: function(id, options) {
			//需要修改源码，删除 this.theme = OpenLayers._getScriptLocation() + 'theme/default/style.css'; 
			this.controls = [];
			//禁用缩放
			var op = {
				documentDrag: options.documentDrag,
				restrictedExtent:options.restrictedExtent
			};
			if (options.disableZoom) {
				op.zoomWheelEnabled=false;
				op.defaultDblClick=null;

				
			}
			if (OpenLayers.Control.Navigation) {
				this.controls.push(new OpenLayers.Control.Navigation(op));
			} else if (OpenLayers.Control.TouchNavigation) {
				this.controls.push(new OpenLayers.Control.TouchNavigation(op));
			}
			OpenLayers.Map.prototype.initialize.apply(this, arguments);
		},
		setLayerZIndex: function(layer, zIdx) {
			var type;
			if (layer.isBaseLayer) {
				type = 'BaseLayer';
			} else if (layer.isFeature) {
				type = "Feature";
			} else if (layer.isBoundary) {
				type = "Boundary";
			} else if (layer.isMarkers) {
				type = "Markers";
			} else {
				type = "Overlay";
			}
			layer.setZIndex(this.Z_INDEX_BASE[type]);
		},
		// addLayer: function(layer) {
		// 	OpenLayers.Map.prototype.addLayer.apply(this, arguments);
		// 	if (layer.zIndex) {
		// 		this.events.on({
		// 			addlayer: function() {
		// 				layer.setZIndex(layer.zIndex)
		// 			}
		// 		});
		// 	}
		// },
		setBaseLayer: function(layer) {
			OpenLayers.Map.prototype.setBaseLayer.apply(this, arguments);
			//显示同组类型瓦片，隐藏非同组带组别的瓦片
			var group = layer.group;
			var overlayers = this.getLayersBy("isBaseLayer", false);
			for (var i = 0, l = overlayers.length; i < l; i++) {
				var overlayer = overlayers[i];
				if (overlayer.group != null) {
					overlayer.setVisibility(overlayer.group == group);
				}
			}
		},
		//------------转换经纬度
		tanslateLonLat: function(lonlat, isDisplay) {
			var dPro = this.displayProjection, //输出投影
				bPro = this.getProjection(); //底图投影
			if (dPro != bPro) {
				if (isDisplay) {
					return lonlat.transform(bPro, dPro)
				} else {
					return lonlat.transform(dPro, bPro)
				}
			} else {
				return lonlat;
			}
		}
	});
});