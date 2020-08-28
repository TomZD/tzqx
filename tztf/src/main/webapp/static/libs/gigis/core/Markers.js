define(function(require) {
	var ClusterLayer = OpenLayers.Class(OpenLayers.Layer.Vector, {
		temp: [],
		initialize: function(distance, style, options) {
			//设置合并范围
			var strategy = new OpenLayers.Strategy.Cluster();
			strategy.distance = distance;
			this.strategies = [strategy];
			//样式规则
			this.styleMap = new OpenLayers.StyleMap(style);
			OpenLayers.Layer.Vector.prototype.initialize.apply(this, ["clusterlayer", options]);
		},
		updateFeatures: function(points) {
			this.removeAllFeatures();
			if (points && points.length) {
				var features = [];
				for (var i = 0, l = points.length; i < l; i++) {
					var n = points[i];
					var ll = n.lonlat;
					features.push(
						new OpenLayers.Feature.Vector(
							new OpenLayers.Geometry.Point(ll.lon, ll.lat),
							n
						)
					);
				}
				OpenLayers.Layer.Vector.prototype.addFeatures.apply(this, [features]);
			}
		},
		addPoints: function(points) {
			if (points && !(OpenLayers.Util.isArray(points))) {
				points = [points];
			}
			this.temp = this.temp.concat(points);
			this.updateFeatures(this.temp);
		},
		removePoints: function(points) {
			var temp = this.temp;
			if (temp && temp.length && points && points.length) {
				for (var i = 0, l = points.length; i < l; i++) {
					var p = points[i];
					for (var j = 0, ll = temp.length; j < ll; j++) {
						if (temp[j] == p) {
							temp = temp.slice(0, j).concat(temp.slice(j + 1, temp.length));
							break;
						}
					}
				}
				this.temp = temp;
			}
			this.updateFeatures(temp);
		}
	});
	var SelectFeature = OpenLayers.Class(OpenLayers.Control.SelectFeature, {
		clickFeature: function(feature) {
			var selected = (OpenLayers.Util.indexOf(
				feature.layer.selectedFeatures, feature) > -1);
			if (selected) {
				if (this.toggleSelect()) {
					this.unselect(feature);
				} else if (!this.multipleSelect()) {
					this.unselectAll({
						except: feature
					});
				}
			} else {
				if (!this.multipleSelect()) {
					this.unselectAll({
						except: feature
					});
				}
				this.select(feature);
			}
		},
		clickoutFeature: function(feature) {
			if (this.clickout) {
				this.unselectAll();
			}
		},
		//鼠标移入
		overFeature: function(feature) {
			if (this.hover) {
				var layer = feature.layer;
				if (this.highlightOnly) {
					this.highlight(feature);
				} else if (OpenLayers.Util.indexOf(layer.selectedFeatures, feature) == -1) {
					this.mOver(feature);
				}
			}
		},
		//鼠标移出
		outFeature: function(feature) {
			if (this.hover) {
				if (this.highlightOnly) {
					// we do nothing if we're not the last highlighter of the
					// feature
					if (feature._lastHighlighter == this.id) {
						// if another select control had highlighted the feature before
						// we did it ourself then we use that control to highlight the
						// feature as it was before we highlighted it, else we just
						// unhighlight it
						if (feature._prevHighlighter &&
							feature._prevHighlighter != this.id) {
							delete feature._lastHighlighter;
							var control = this.map.getControl(
								feature._prevHighlighter);
							if (control) {
								control.highlight(feature);
							}
						} else {
							this.unhighlight(feature);
						}
					}
				} else {
					this.mOut(feature);
				}
			}
		},
		mOver: function(feature) {
			var cont = this.onBeforeSelect.call(this.scope, feature);
			var layer = feature.layer;
			if (cont !== false) {
				cont = layer.events.triggerEvent("beforefeatureover", {
					feature: feature
				});
				if (cont !== false) {
					layer.selectedFeatures.push(feature);
					this.highlight(feature);
					if (!this.handlers.feature.lastFeature) {
						this.handlers.feature.lastFeature = layer.selectedFeatures[0];
					}
					layer.events.triggerEvent("featureover", {
						feature: feature
					});
					this.onSelect.call(this.scope, feature);
				}
			}
		},
		mOut: function(feature) {
			var layer = feature.layer;
			// Store feature style for restoration later
			this.unhighlight(feature);
			OpenLayers.Util.removeItem(layer.selectedFeatures, feature);
			layer.events.triggerEvent("featureout", {
				feature: feature
			});
			this.onUnselect.call(this.scope, feature);
		}
	});
	return OpenLayers.Class(OpenLayers.Layer.Markers, {
		showType: null, //展示类型
		showTypeOptions: null, //配置
		isMarkers: true, //将标记层设置成一个特殊层，层级从726开始
		initialize: function(name, type, options) {
			this.showType = type;
			this.showTypeOptions = options;
			OpenLayers.Layer.Markers.prototype.initialize.apply(this, [name]);
		},
		destory: function() {
			if (this.control) {
				this.map.removeControl(this.control);
				this.control = null;
			}
			this.clusterLayer = null;
			OpenLayers.Layer.Markers.prototype.destory.apply(this, arguments);
		},
		getOffset: function(position, width, height) {
			var x = -(width / 2),
				y = -(height / 2);
			if (typeof position == "string") {
				var arr = position.split("");
				var v = arr[0]; //垂直方向 top bottom middle
				if (v == "t") {
					y = -height;
				} else if (v == "b") {
					y = 0;
				}
				var h = arr[1]; //水平方向 left right center
				if (h == "l") {
					x = -width;
				} else if (h == "r") {
					x = 0;
				}
			}
			return {
				x: x,
				y: y
			}
		},
		setMap: function(map) {
			OpenLayers.Layer.Markers.prototype.setMap.apply(this, [map]);
			var type = this.showType;
			var options = this.showTypeOptions;
			if (type == "cluster" || type == "level") {
				options = options || {};
				//配置
				var distance = options.distance || 40;
				var events = options.events;
				var colors = options.colors || {
					low: "rgb(181, 226, 140)",
					middle: "rgb(241, 211, 87)",
					high: "rgb(253, 156, 115)"
				};
				var style;
				if (type == "cluster") {
					style = new OpenLayers.Style(null, {
						rules: [
							new OpenLayers.Rule({
								filter: new OpenLayers.Filter.Comparison({
									type: OpenLayers.Filter.Comparison.GREATER_THAN,
									property: "count",
									value: 1
								}),
								symbolizer: {
									fillColor: colors.high,
									fillOpacity: 0.9,
									strokeColor: colors.high,
									strokeOpacity: 0.5,
									strokeWidth: 12,
									pointRadius: 20,
									label: "${count}",
									labelOutlineWidth: 1,
									fontColor: "#ffffff",
									fontOpacity: 1,
									fontSize: "12px"
								}
							})
						],
						context: {
							count: function(feature) {
								return feature.attributes.count;
							}
						}
					});
				} else {
					style = new OpenLayers.Style({});
				}
				//聚合层
				var clusterLayer = new ClusterLayer(distance, style);
				map.addLayer(clusterLayer);
				//控件
				var select = new SelectFeature(clusterLayer);
				map.addControl(select);
				select.activate();

				//绑定事件
				if (type == "cluster") {
					clusterLayer.events.on({
						beforefeatureremoved: function(e) {
							var cluster = e.feature.cluster;
							if (cluster)
								for (var i = 0, l = cluster.length; i < l; i++) {
									cluster[i].attributes.show();
								}
						},
						beforefeatureadded: function(e) {
							var cluster = e.feature.cluster;
							if (cluster) {
								var l = cluster.length;
								if (l == 1) {
									cluster[0].attributes.show();
								} else {
									for (var i = 0; i < l; i++) {
										cluster[i].attributes.hide();
									}
								}
							}
						}
					});
				} else {
					clusterLayer.events.on({
						beforefeatureremoved: function(e) {
							var cluster = e.feature.cluster;
							if (cluster)
								for (var i = 0, l = cluster.length; i < l; i++) {
									cluster[i].attributes.show();
								}
						},
						beforefeatureadded: function(e) {
							var cluster = e.feature.cluster;
							if (cluster) {
								for (var i = 0, l = cluster.length; i < l; i++) {
									i && cluster[i].attributes.hide();
								}
							}
						}
					});
				}
				if (typeof events == "object") {
					if (type == "cluster") {
						//获取焦点
						if (typeof events.click == "function") {
							clusterLayer.events.on({
								featureselected: function(e) {
									var feature = e.feature;
									var cluster = feature && feature.cluster;
									cluster && events.click(cluster);
								}
							});
						}
						//失去焦点
						if (typeof events.clickout == "function") {
							clusterLayer.events.on({
								featureunselected: function(e) {
									var feature = e.feature;
									var cluster = feature && feature.cluster;
									cluster && events.clickout(cluster);
								},
								//点被移除
								featureremoved: function(e) {
									var feature = e.feature;
									var cluster = feature && feature.cluster;
									cluster && events.clickout(cluster);
								}
							});
						}
						//鼠标移入
						if (typeof events.mouseover == "function") {
							clusterLayer.events.on({
								featureover: function(e) {
									var feature = e.feature;
									var cluster = feature && feature.cluster;
									cluster && events.mouseover(cluster);
								}
							});
						}
						//鼠标移出
						if (typeof events.mouseout == "function") {
							clusterLayer.events.on({
								featureout: function(e) {
									var feature = e.feature;
									var cluster = feature && feature.cluster;
									cluster && events.mouseout(cluster);
								},
								featureremoved: function(e) {
									var feature = e.feature;
									var cluster = feature && feature.cluster;
									cluster && events.mouseout(cluster);
								}
							});
						}
						//更新
						if (typeof events.update == "function") {
							clusterLayer.events.on({
								featureadded: function(e) {
									var feature = e.feature;
									var cluster = feature && feature.cluster;
									cluster && events.update(cluster, e.object.hover);
								}
							});
						}
					}
				}

				this.clusterLayer = clusterLayer;
				this.control = select;
			}
		}
	});
});