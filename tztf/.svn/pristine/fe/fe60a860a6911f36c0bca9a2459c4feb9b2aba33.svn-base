define("base/verify", [], function() {
	function e(e) {
		if (t(e)) {
			var n = (e + "").match(/^\s*(\S+(\s+\S+)*)\s*$/);
			return n == null ? "" : n[1]
		}
		return ""
	}
	function t(e, t) {
		return typeof e == "string" || !t && typeof e == "number"
	}
	function n(t) {
		return e(t) == ""
	}
	function r() {
		return /[\u4e00-\u9fa5]+/.test(content)
	}
	function i(e) {
		return !isNaN(e - 0)
	}
	function s(e, t) {
		if (t && typeof e != "number") return;
		return /[0-9]|(^[0-9]*]*$)/.test(e)
	}
	function o(e, t) {
		if (t && typeof e != "number") return;
		return /^[1-9]+[0-9]*]*$/.test(e)
	}
	var u = {
		string: t,
		empty: n,
		chinese: r,
		number: i,
		"int": s,
		positiveInteger: o
	};
	return function(e, t, n) {
		var r = u[t];
		return r && r(e, n)
	}
}), define("core/Popup", [], function() {
	return OpenLayers.Class(OpenLayers.Popup, {
		autoSize: !0,
		panMapIfOutOfView: !0,
		initialize: function(e, t, n, r, i, s, o) {
			this.offset = r, OpenLayers.Popup.prototype.initialize.apply(this, [e, t, n, i, s, o])
		},
		destroy: function() {
			this.div.removeChild(this.arrow), this.arrow = null, OpenLayers.Popup.prototype.destroy.apply(this)
		},
		draw: function(e) {
			return this.arrow = OpenLayers.Util.createDiv(null, null, null, null, "static"), this.arrow.className = "popup_tri", this.arrow.innerHTML = "<i class='popup_tri_a'></i><i class='popup_tri_b'></i>", this.div.appendChild(this.arrow), OpenLayers.Popup.prototype.draw.apply(this, [e])
		},
		setSize: function(e) {
			this.size = e.clone();
			var t = this.getContentDivPadding(),
				n = t.left + t.right,
				r = t.top + t.bottom;
			this.fixPadding(), n += this.padding.left + this.padding.right, r += this.padding.top + this.padding.bottom, this.size.w += n, this.size.h += r+20, OpenLayers.BROWSER_NAME == "msie" && (this.contentSize.w += t.left + t.right, this.contentSize.h += t.bottom + t.top), this.div != null && (this.div.style.width = this.size.w + "px", this.div.style.height = this.size.h + "px"), this.contentDiv != null && (this.contentDiv.style.width = e.w + "px", this.contentDiv.style.height = e.h + "px")
		},
		moveTo: function(e) {
			this.size && (e.x -= this.size.w / 2, e.y -= this.size.h, e.y -= this.arrow.offsetHeight, this.offset && (e.x += this.offset.x, e.y += this.offset.y), OpenLayers.Popup.prototype.moveTo.apply(this, [e]))
		},
		setLonlat: function(e, t, n, r) {
			this.lonlat = this.map.tanslateLonLat(new OpenLayers.LonLat(e, t)), this.setOffset(n, r), this.updatePosition()
		},
		setOffset: function(e, t) {
			this.offset ? (e != null && (this.offset.x = e), t != null && (this.offset.y = t)) : this.offset = new OpenLayers.Pixel(e || 0, t || 0)
		},
		CLASS_NAME: "Gi.Popup"
	})
}), define("core/Map", [], function() {
	return OpenLayers.Class(OpenLayers.Map, {
		Z_INDEX_BASE: {
			BaseLayer: 100,
			Overlay: 325,
			Boundary: 400,
			Feature: 725,
			Markers: 726,
			Popup: 750,
			Control: 1e3
		},
		initialize: function(e, t) {
			this.controls = [], OpenLayers.Control.Navigation ? this.controls.push(new OpenLayers.Control.Navigation) : OpenLayers.Control.TouchNavigation && this.controls.push(new OpenLayers.Control.TouchNavigation), OpenLayers.Map.prototype.initialize.apply(this, arguments)
		},
		setLayerZIndex: function(e, t) {
			var n;
			e.isBaseLayer ? n = "BaseLayer" : e.isFeature ? n = "Feature" : e.isBoundary ? n = "Boundary" : e.isMarkers ? n = "Markers" : n = "Overlay", e.setZIndex(this.Z_INDEX_BASE[n])
		},
		setBaseLayer: function(e) {
			OpenLayers.Map.prototype.setBaseLayer.apply(this, arguments);
			var t = e.group,
				n = this.getLayersBy("isBaseLayer", !1);
			for (var r = 0, i = n.length; r < i; r++) {
				var s = n[r];
				s.group != null && s.setVisibility(s.group == t)
			}
		},
		tanslateLonLat: function(e, t) {
			var n = this.displayProjection,
				r = this.getProjection();
			return n != r ? t ? e.transform(r, n) : e.transform(n, r) : e
		}
	})
}), define("core/Markers", ["require"], function(e) {
	var t = OpenLayers.Class(OpenLayers.Layer.Vector, {
		temp: [],
		initialize: function(e, t, n) {
			var r = new OpenLayers.Strategy.Cluster;
			r.distance = e, this.strategies = [r], this.styleMap = new OpenLayers.StyleMap(t), OpenLayers.Layer.Vector.prototype.initialize.apply(this, ["clusterlayer", n])
		},
		updateFeatures: function(e) {
			this.removeAllFeatures();
			if (e && e.length) {
				var t = [];
				for (var n = 0, r = e.length; n < r; n++) {
					var i = e[n],
						s = i.lonlat;
					t.push(new OpenLayers.Feature.Vector(new OpenLayers.Geometry.Point(s.lon, s.lat), i))
				}
				OpenLayers.Layer.Vector.prototype.addFeatures.apply(this, [t])
			}
		},
		addPoints: function(e) {
			e && !OpenLayers.Util.isArray(e) && (e = [e]), this.temp = this.temp.concat(e), this.updateFeatures(this.temp)
		},
		removePoints: function(e) {
			var t = this.temp;
			if (t && t.length && e && e.length) {
				for (var n = 0, r = e.length; n < r; n++) {
					var i = e[n];
					for (var s = 0, o = t.length; s < o; s++) if (t[s] == i) {
						t = t.slice(0, s).concat(t.slice(s + 1, t.length));
						break
					}
				}
				this.temp = t
			}
			this.updateFeatures(t)
		}
	}),
		n = OpenLayers.Class(OpenLayers.Control.SelectFeature, {
			clickFeature: function(e) {
				var t = OpenLayers.Util.indexOf(e.layer.selectedFeatures, e) > -1;
				t ? this.toggleSelect() ? this.unselect(e) : this.multipleSelect() || this.unselectAll({
					except: e
				}) : (this.multipleSelect() || this.unselectAll({
					except: e
				}), this.select(e))
			},
			clickoutFeature: function(e) {
				this.clickout && this.unselectAll()
			},
			overFeature: function(e) {
				if (this.hover) {
					var t = e.layer;
					this.highlightOnly ? this.highlight(e) : OpenLayers.Util.indexOf(t.selectedFeatures, e) == -1 && this.mOver(e)
				}
			},
			outFeature: function(e) {
				if (this.hover) if (this.highlightOnly) {
					if (e._lastHighlighter == this.id) if (e._prevHighlighter && e._prevHighlighter != this.id) {
						delete e._lastHighlighter;
						var t = this.map.getControl(e._prevHighlighter);
						t && t.highlight(e)
					} else this.unhighlight(e)
				} else this.mOut(e)
			},
			mOver: function(e) {
				var t = this.onBeforeSelect.call(this.scope, e),
					n = e.layer;
				t !== !1 && (t = n.events.triggerEvent("beforefeatureover", {
					feature: e
				}), t !== !1 && (n.selectedFeatures.push(e), this.highlight(e), this.handlers.feature.lastFeature || (this.handlers.feature.lastFeature = n.selectedFeatures[0]), n.events.triggerEvent("featureover", {
					feature: e
				}), this.onSelect.call(this.scope, e)))
			},
			mOut: function(e) {
				var t = e.layer;
				this.unhighlight(e), OpenLayers.Util.removeItem(t.selectedFeatures, e), t.events.triggerEvent("featureout", {
					feature: e
				}), this.onUnselect.call(this.scope, e)
			}
		});
	return OpenLayers.Class(OpenLayers.Layer.Markers, {
		showType: null,
		showTypeOptions: null,
		isMarkers: !0,
		initialize: function(e, t, n) {
			this.showType = t, this.showTypeOptions = n, OpenLayers.Layer.Markers.prototype.initialize.apply(this, [e])
		},
		destory: function() {
			this.control && (this.map.removeControl(this.control), this.control = null), this.clusterLayer = null, OpenLayers.Layer.Markers.prototype.destory.apply(this, arguments)
		},
		getOffset: function(e, t, n) {
			var r = -(t / 2),
				i = -(n / 2);
			if (typeof e == "string") {
				var s = e.split(""),
					o = s[0];
				o == "t" ? i = -n : o == "b" && (i = 0);
				var u = s[1];
				u == "l" ? r = -t : u == "r" && (r = 0)
			}
			return {
				x: r,
				y: i
			}
		},
		setMap: function(e) {
			OpenLayers.Layer.Markers.prototype.setMap.apply(this, [e]);
			var r = this.showType,
				i = this.showTypeOptions;
			if (r == "cluster" || r == "level") {
				i = i || {};
				var s = i.distance || 40,
					o = i.events,
					u = i.colors || {
						low: "rgb(181, 226, 140)",
						middle: "rgb(241, 211, 87)",
						high: "rgb(253, 156, 115)"
					},
					a;
				r == "cluster" ? a = new OpenLayers.Style(null, {
					rules: [new OpenLayers.Rule({
						filter: new OpenLayers.Filter.Comparison({
							type: OpenLayers.Filter.Comparison.GREATER_THAN,
							property: "count",
							value: 1
						}),
						symbolizer: {
							fillColor: u.high,
							fillOpacity: .9,
							strokeColor: u.high,
							strokeOpacity: .5,
							strokeWidth: 12,
							pointRadius: 20,
							label: "${count}",
							labelOutlineWidth: 1,
							fontColor: "#ffffff",
							fontOpacity: 1,
							fontSize: "12px"
						}
					})],
					context: {
						count: function(e) {
							return e.attributes.count
						}
					}
				}) : a = new OpenLayers.Style({});
				var f = new t(s, a);
				e.addLayer(f);
				var l = new n(f);
				e.addControl(l), l.activate(), r == "cluster" ? f.events.on({
					beforefeatureremoved: function(e) {
						var t = e.feature.cluster;
						if (t) for (var n = 0, r = t.length; n < r; n++) t[n].attributes.show()
					},
					beforefeatureadded: function(e) {
						var t = e.feature.cluster;
						if (t) {
							var n = t.length;
							if (n == 1) t[0].attributes.show();
							else for (var r = 0; r < n; r++) t[r].attributes.hide()
						}
					}
				}) : f.events.on({
					beforefeatureremoved: function(e) {
						var t = e.feature.cluster;
						if (t) for (var n = 0, r = t.length; n < r; n++) t[n].attributes.show()
					},
					beforefeatureadded: function(e) {
						var t = e.feature.cluster;
						if (t) for (var n = 0, r = t.length; n < r; n++) n && t[n].attributes.hide()
					}
				}), typeof o == "object" && r == "cluster" && (typeof o.click == "function" && f.events.on({
					featureselected: function(e) {
						var t = e.feature,
							n = t && t.cluster;
						n && o.click(n)
					}
				}), typeof o.clickout == "function" && f.events.on({
					featureunselected: function(e) {
						var t = e.feature,
							n = t && t.cluster;
						n && o.clickout(n)
					},
					featureremoved: function(e) {
						var t = e.feature,
							n = t && t.cluster;
						n && o.clickout(n)
					}
				}), typeof o.mouseover == "function" && f.events.on({
					featureover: function(e) {
						var t = e.feature,
							n = t && t.cluster;
						n && o.mouseover(n)
					}
				}), typeof o.mouseout == "function" && f.events.on({
					featureout: function(e) {
						var t = e.feature,
							n = t && t.cluster;
						n && o.mouseout(n)
					},
					featureremoved: function(e) {
						var t = e.feature,
							n = t && t.cluster;
						n && o.mouseout(n)
					}
				}), typeof o.update == "function" && f.events.on({
					featureadded: function(e) {
						var t = e.feature,
							n = t && t.cluster;
						n && o.update(n, e.object.hover)
					}
				})), this.clusterLayer = f, this.control = l
			}
		}
	})
}), define("core/Marker", [], function() {
	return OpenLayers.Class(OpenLayers.Marker, {
		getLonlat: function() {
			return {
				lon: this._lon,
				lat: this._lat
			}
		},
		getOffset: function(e) {
			var t = this.icon.size,
				n = t.h,
				r = t.w,
				i = -r / 2,
				s = -n / 2;
			if (typeof e == "string") {
				var o = e.split(""),
					u = o[0];
				u == "t" ? s = 0 : u == "b" && (s = -n);
				var a = o[1];
				a == "l" ? i = 0 : a == "r" && (i = -r)
			}
			var f = this.icon.offset.clone();
			return f.x -= i, f.y -= s, f
		},
		setText: function(e) {
			if (typeof e == "string" || typeof e == "number") this.text || (this.text = OpenLayers.Util.createDiv(null, {
				x: 0,
				y: 0
			}, null, null, "absolute", null, null), this.icon.imageDiv.appendChild(this.text)), this.text.innerHTML = e
		},
		setContent: function(e) {
			this.content = e
		},
		hide: function() {
			this.display(!1)
		},
		show: function() {
			this.display(!0)
		},
		hideText: function() {
			this.text && (this.text.style.display = "none")
		},
		showText: function() {
			this.text && (this.text.style.display = "")
		}
	})
}), define("core/BoxMarker", ["require"], function(e) {
	return OpenLayers.Class({
		events: null,
		id: "",
		lonlat: null,
		div: null,
		contentSize: null,
		size: null,
		contentHTML: null,
		backgroundColor: "",
		opacity: "",
		border: "",
		contentDiv: null,
		groupDiv: null,
		closeDiv: null,
		autoSize: !1,
		minSize: null,
		maxSize: null,
		displayClass: "olLabel",
		contentDisplayClass: "olLabelContent",
		padding: 0,
		disableFirefoxOverflowHack: !1,
		fixPadding: function() {
			typeof this.padding == "number" && (this.padding = new OpenLayers.Bounds(this.padding, this.padding, this.padding, this.padding))
		},
		panMapIfOutOfView: !1,
		keepInMap: !1,
		closeOnMove: !1,
		map: null,
		initialize: function(e, t, n, r, i) {
			e == null && (e = OpenLayers.Util.createUniqueID(this.CLASS_NAME + "_")), this.id = e, this.lonlat = t, this.offset = i, this.contentSize = r != null ? r : new OpenLayers.Size(OpenLayers.Popup.WIDTH, OpenLayers.Popup.HEIGHT), n != null && (this.contentHTML = n), this.backgroundColor = OpenLayers.Popup.COLOR, this.opacity = OpenLayers.Popup.OPACITY, this.border = OpenLayers.Popup.BORDER, this.div = OpenLayers.Util.createDiv(this.id, null, null, null, null, null, "hidden"), this.div.className = this.displayClass;
			var s = this.id + "_GroupDiv";
			this.groupDiv = OpenLayers.Util.createDiv(s, null, null, null, "relative", null, "hidden");
			var e = this.div.id + "_contentDiv";
			this.contentDiv = OpenLayers.Util.createDiv(e, null, this.contentSize.clone(), null, "relative"), this.contentDiv.className = this.contentDisplayClass, this.groupDiv.appendChild(this.contentDiv), this.div.appendChild(this.groupDiv), this.registerEvents()
		},
		setLonlat: function(e, t, n, r) {
			this.lonlat = new OpenLayers.LonLat(e, t), this.offset = {
				x: n,
				y: r
			}, this.updatePosition()
		},
		destroy: function() {
			this.id = null, this.lonlat = null, this.size = null, this.contentHTML = null, this.backgroundColor = null, this.opacity = null, this.border = null, this.closeOnMove && this.map && this.map.events.unregister("movestart", this, this.hide), this.events.destroy(), this.events = null, this.div.removeChild(this.groupDiv), this.groupDiv = null, this.map != null && this.map.removePopup(this), this.map = null, this.div = null, this.autoSize = null, this.minSize = null, this.maxSize = null, this.padding = null, this.panMapIfOutOfView = null
		},
		draw: function(e) {
			return e == null && this.lonlat != null && this.map != null && (e = this.map.getLayerPxFromLonLat(this.lonlat)), this.closeOnMove && this.map.events.register("movestart", this, this.hide), !this.disableFirefoxOverflowHack && OpenLayers.BROWSER_NAME == "firefox" && (this.map.events.register("movestart", this, function() {
				var e = document.defaultView.getComputedStyle(this.contentDiv, null),
					t = e.getPropertyValue("overflow");
				t != "hidden" && (this.contentDiv._oldOverflow = t, this.contentDiv.style.overflow = "hidden")
			}), this.map.events.register("moveend", this, function() {
				var e = this.contentDiv._oldOverflow;
				e && (this.contentDiv.style.overflow = e, this.contentDiv._oldOverflow = null)
			})), this.moveTo(e), !this.autoSize && !this.size && this.setSize(this.contentSize), this.setOpacity(), this.setContentHTML(), this.panMapIfOutOfView && this.panIntoView(), this.div.style.zIndex = 300, this.div
		},
		updatePosition: function() {
			if (this.lonlat && this.map) {
				var e = this.map.getLayerPxFromLonLat(this.lonlat);
				e && this.moveTo(e)
			}
		},
		moveTo: function(e) {
			e != null && this.div != null && (this.contentSize && (e.x -= this.contentSize.w / 2, e.y -= this.contentSize.h / 2), this.offset && this.offset.x && this.offset.y && (e.x += this.offset.x, e.y += this.offset.y), this.div.style.left = e.x + "px", this.div.style.top = e.y + "px")
		},
		visible: function() {
			return OpenLayers.Element.visible(this.div)
		},
		toggle: function() {
			this.visible() ? this.hide() : this.show()
		},
		show: function() {
			this.div.style.display = "", this.panMapIfOutOfView && this.panIntoView()
		},
		hide: function() {
			this.div.style.display = "none"
		},
		setSize: function(e) {
			this.size = e.clone();
			var t = this.getContentDivPadding(),
				n = t.left + t.right,
				r = t.top + t.bottom;
			this.fixPadding(), n += this.padding.left + this.padding.right, r += this.padding.top + this.padding.bottom, this.size.w += n, this.size.h += r, OpenLayers.BROWSER_NAME == "msie" && (this.contentSize.w += t.left + t.right, this.contentSize.h += t.bottom + t.top), this.div != null && (this.div.style.width = this.size.w + "px", this.div.style.height = this.size.h + "px"), this.contentDiv != null && (this.contentDiv.style.width = e.w + "px", this.contentDiv.style.height = e.h + "px")
		},
		updateSize: function() {
			var e = "<div class='" + this.contentDisplayClass + "'>" + this.contentDiv.innerHTML + "</div>",
				t = this.map ? this.map.div : document.body,
				n = OpenLayers.Util.getRenderedDimensions(e, null, {
					displayClass: this.displayClass,
					containerElement: t
				}),
				r = this.getSafeContentSize(n),
				i = null;
			if (r.equals(n)) i = n;
			else {
				var s = {
					w: r.w < n.w ? r.w : null,
					h: r.h < n.h ? r.h : null
				};
				if (s.w && s.h) i = r;
				else {
					var o = OpenLayers.Util.getRenderedDimensions(e, s, {
						displayClass: this.contentDisplayClass,
						containerElement: t
					}),
						u = OpenLayers.Element.getStyle(this.contentDiv, "overflow");
					if (u != "hidden" && o.equals(r)) {
						var a = OpenLayers.Util.getScrollbarWidth();
						s.w ? o.h += a : o.w += a
					}
					i = this.getSafeContentSize(o)
				}
			}
			this.setSize(i)
		},
		setBackgroundColor: function(e) {
			e != undefined && (this.backgroundColor = e), this.div != null && (this.div.style.backgroundColor = this.backgroundColor)
		},
		setOpacity: function(e) {
			e != undefined && (this.opacity = e), this.div != null && (this.div.style.opacity = this.opacity, this.div.style.filter = "alpha(opacity=" + this.opacity * 100 + ")")
		},
		setBorder: function(e) {
			e != undefined && (this.border = e), this.div != null && (this.div.style.border = this.border)
		},
		setContentHTML: function(e) {
			e != null && (this.contentHTML = e), this.contentDiv != null && this.contentHTML != null && this.contentHTML != this.contentDiv.innerHTML && (this.contentDiv.innerHTML = this.contentHTML, this.autoSize && (this.registerImageListeners(), this.updateSize()))
		},
		registerImageListeners: function() {
			var e = function() {
					if (this.popup.id === null) return;
					this.popup.updateSize(), this.popup.visible() && this.popup.panMapIfOutOfView && this.popup.panIntoView(), OpenLayers.Event.stopObserving(this.img, "load", this.img._onImgLoad)
				},
				t = this.contentDiv.getElementsByTagName("img");
			for (var n = 0, r = t.length; n < r; n++) {
				var i = t[n];
				if (i.width == 0 || i.height == 0) {
					var s = {
						popup: this,
						img: i
					};
					i._onImgLoad = OpenLayers.Function.bind(e, s), OpenLayers.Event.observe(i, "load", i._onImgLoad)
				}
			}
		},
		getSafeContentSize: function(e) {
			var t = e.clone(),
				n = this.getContentDivPadding(),
				r = n.left + n.right,
				i = n.top + n.bottom;
			this.fixPadding(), r += this.padding.left + this.padding.right, i += this.padding.top + this.padding.bottom, this.minSize && (t.w = Math.max(t.w, this.minSize.w - r), t.h = Math.max(t.h, this.minSize.h - i)), this.maxSize && (t.w = Math.min(t.w, this.maxSize.w - r), t.h = Math.min(t.h, this.maxSize.h - i));
			if (this.map && this.map.size) {
				var s = 0,
					o = 0;
				if (this.keepInMap && !this.panMapIfOutOfView) {
					var u = this.map.getPixelFromLonLat(this.lonlat);
					switch (this.relativePosition) {
					case "tr":
						s = u.x, o = this.map.size.h - u.y;
						break;
					case "tl":
						s = this.map.size.w - u.x, o = this.map.size.h - u.y;
						break;
					case "bl":
						s = this.map.size.w - u.x, o = u.y;
						break;
					case "br":
						s = u.x, o = u.y;
						break;
					default:
						s = u.x, o = this.map.size.h - u.y
					}
				}
				var a = this.map.size.h - this.map.paddingForPopups.top - this.map.paddingForPopups.bottom - i - o,
					f = this.map.size.w - this.map.paddingForPopups.left - this.map.paddingForPopups.right - r - s;
				t.w = Math.min(t.w, f), t.h = Math.min(t.h, a)
			}
			return t
		},
		getContentDivPadding: function() {
			var e = this._contentDivPadding;
			return e || (this.div.parentNode == null && (this.div.style.display = "none", document.body.appendChild(this.div)), e = new OpenLayers.Bounds(OpenLayers.Element.getStyle(this.contentDiv, "padding-left"), OpenLayers.Element.getStyle(this.contentDiv, "padding-bottom"), OpenLayers.Element.getStyle(this.contentDiv, "padding-right"), OpenLayers.Element.getStyle(this.contentDiv, "padding-top")), this._contentDivPadding = e, this.div.parentNode == document.body && (document.body.removeChild(this.div), this.div.style.display = "")), e
		},
		panIntoView: function() {
			var e = this.map.getSize(),
				t = this.map.getViewPortPxFromLayerPx(new OpenLayers.Pixel(parseInt(this.div.style.left), parseInt(this.div.style.top))),
				n = t.clone();
			t.x < this.map.paddingForPopups.left ? n.x = this.map.paddingForPopups.left : t.x + this.size.w > e.w - this.map.paddingForPopups.right && (n.x = e.w - this.map.paddingForPopups.right - this.size.w), t.y < this.map.paddingForPopups.top ? n.y = this.map.paddingForPopups.top : t.y + this.size.h > e.h - this.map.paddingForPopups.bottom && (n.y = e.h - this.map.paddingForPopups.bottom - this.size.h);
			var r = t.x - n.x,
				i = t.y - n.y;
			this.map.pan(r, i)
		},
		registerEvents: function() {
			function e(e) {
				OpenLayers.Event.stop(e, !0)
			}
			this.events = new OpenLayers.Events(this, this.div, null, !0), this.events.on({
				mousedown: this.onmousedown,
				mousemove: this.onmousemove,
				mouseup: this.onmouseup,
				click: this.onclick,
				mouseout: this.onmouseout,
				dblclick: this.ondblclick,
				touchstart: e,
				scope: this
			})
		},
		onmousedown: function(e) {
			this.mousedown = !0, OpenLayers.Event.stop(e, !0)
		},
		onmousemove: function(e) {
			this.mousedown && OpenLayers.Event.stop(e, !0)
		},
		onmouseup: function(e) {
			this.mousedown && (this.mousedown = !1, OpenLayers.Event.stop(e, !0))
		},
		onclick: function(e) {
			OpenLayers.Event.stop(e, !0)
		},
		onmouseout: function(e) {
			this.mousedown = !1
		},
		ondblclick: function(e) {
			OpenLayers.Event.stop(e, !0)
		},
		CLASS_NAME: "OpenLayers.Popup"
	})
}), define("layer/grass", ["require"], function(e) {
	var t = [{
		name: "traffic",
		alias: "交通图",
		url: "http://10.135.30.227/maps/GoogleMap_${z}/${x}_${y}.png",
		numZoomLevels: 16,
		group: "grass-traffic",
		isBaseLayer: !0
	}, {
		name: "terrain",
		alias: "地形图",
		url: "http://10.135.30.227/maps/GoogleTer_${z}/${x}_${y}.jpg",
		group: "grass-terrain",
		numZoomLevels: 16,
		isBaseLayer: !0
	}, {
		name: "satellite",
		alias: "卫星图",
		url: "http://10.135.30.227/maps/GoogleSat_${z}/${x}_${y}.png",
		group: "grass-satellite",
		numZoomLevels: 16,
		isBaseLayer: !0
	}, {
		name: "road",
		alias: "路网",
		url: "http://10.135.30.227/maps/GoogleHyb_${z}/${x}_${y}.png",
		group: "grass-satellite",
		numZoomLevels: 16
	}, {
		name: "board",
		alias: "白板图",
		url: "http://10.135.30.12/GetMaps/getmaps.ashx?type=zjqxmap_bound&z=${z}&x=${x}&y=${y}",
		group: "grass-white",
		numZoomLevels: 14,
		isBaseLayer: !0
	}];
	return function(e) {
		var n = [];
		for (var r = 0, i = t.length; r < i; r++) {
			var s = t[r];
			n.push(new OpenLayers.Layer.XYZ(s.name, s.url, {
				wrapDateLine: e,
				sphericalMercator: !0,
				numZoomLevels: s.numZoomLevels,
				group: s.group,
				isBaseLayer: s.isBaseLayer || !1,
				getURL: s.getURL,
				alias: s.alias,
				visibility: !1
			}))
		}
		return n
	}
}), define("layer/amap", ["require"], function(e) {
	var t = [{
		name: "traffic",
		alias: "交通图",
		url: "http://webrd04.is.autonavi.com/appmaptile?lang=zh_cn&size=1&scale=1&style=7&x=${x}&y=${y}&z=${z}",
		group: "amap-traffic",
		isBaseLayer: !0
	}, {
		name: "satellite",
		alias: "卫星图",
		numZoomLevels: 14,
		url: "http://webst03.is.autonavi.com/appmaptile?style=6&x=${x}&y=${y}&z=${z}",
		group: "amap-satellite",
		isBaseLayer: !0
	}, {
		name: "road",
		alias: "路网",
		url: "http://webst01.is.autonavi.com/appmaptile?style=8&x=${x}&y=${y}&z=${z}",
		group: "amap-satellite"
	}];
	return function(e) {
		var n = [];
		for (var r = 0, i = t.length; r < i; r++) {
			var s = t[r];
			n.push(new OpenLayers.Layer.XYZ(s.name, s.url, {
				wrapDateLine: e,
				sphericalMercator: !0,
				numZoomLevels: s.numZoomLevels,
				group: s.group,
				isBaseLayer: s.isBaseLayer || !1,
				getURL: s.getURL,
				alias: s.alias,
				visibility: !1
			}))
		}
		return n
	}
}), define("layer/bing", ["require"], function(e) {
	var t = OpenLayers.Class(OpenLayers.Layer.Bing, {
		loadMetadata: function() {
			this._callbackId = "_callback_" + this.id.replace(/\./g, "_"), window[this._callbackId] = OpenLayers.Function.bind(OpenLayers.Layer.Bing.processMetadata, this);
			var e = OpenLayers.Util.applyDefaults({
				key: this.key,
				jsonp: this._callbackId,
				include: "ImageryProviders"
			}, this.metadataParams),
				t = this.protocol + "//dev.ditu.live.com/REST/v1/Imagery/Metadata/" + this.type + "?" + OpenLayers.Util.getParameterString(e),
				n = document.createElement("script");
			n.type = "text/javascript", n.src = t, n.id = this._callbackId, document.getElementsByTagName("head")[0].appendChild(n)
		}
	});
	return function(e) {
		return [new t({
			wrapDateLine: e,
			name: "traffic",
			alias: "交通图",
			key: "Ak-dzM4wZjSqTlzveKz5u0d4IQ4bRzVI309GxmkgSVr1ewS6iPSrOvOKhA-CJlm3",
			tileOptions: {
				crossOriginKeyword: ""
			}
		})]
	}
}), define("layer/google", ["require"], function(e) {
	return function(e) {
		var t = document.createElement("script");
		return t.type = "text/javascript", t.src = "http://maps.google.com/maps/api/js?v=3&amp;sensor=false", document.getElementsByTagName("head")[0].appendChild(t), [new OpenLayers.Layer.Google({
			wrapDateLine: e
		})]
	}
}), define("layer/seamap", ["require"], function(e) {
    var t = [
        {
		name: "sae",
		alias: "海图",
		numZoomLevels: 14,
		url: " http://m13.shipxy.com/tile.c?l=Na&m=o&x=${x}&y=${y}&z=${z}",
		group: "seamap-satellite",
		isBaseLayer: !0
	},
    {
		name: "boat",
		alias: "船分布图",
		numZoomLevels: 14,
		url: " http://r2.shipxy.com/r2/sp.dll?cmd=112&scode=11111111&x=${x}&y=${y}&z=${z}",
		group: "seamap-satellite",
		//isBaseLayer: !0
    }
	];
	return function() {
		var e = [];
		for (var n = 0, r = t.length; n < r; n++) {
			var i = t[n];
			e.push(new OpenLayers.Layer.XYZ(i.name, i.url, {
				sphericalMercator: !0,
				numZoomLevels: i.numZoomLevels,
				group: i.group,
				isBaseLayer: i.isBaseLayer || !1,
				getURL: i.getURL,
				alias: i.alias
			}))
		}
		return e
	}
}), define("layer/resources", ["require", "./grass", "./amap", "./bing", "./google", "./seamap"], function(e) {
	return {
		grass: e("./grass"),
		amap: e("./amap"),
		bing: e("./bing"),
		google: e("./google"),
		seamap: e("./seamap")
	}
}), define("control/scaleLine", ["require"], function(e) {
	return OpenLayers.Class(OpenLayers.Control, {
		maxWidth: 100,
		outUnits: "km",
		inUnits: "m",
		geodesic: !1,
		getBarLen: function(e) {
			var t = parseInt(Math.log(e) / Math.log(10)),
				n = Math.pow(10, t);
			return parseInt(e / n) * n
		},
		draw: function() {
			OpenLayers.Control.prototype.draw.apply(this, arguments);
			if (!this.eBox) {
				this.eBox = document.createElement("div"), this.eBox.className = this.displayClass + "Top";
				var e = this.inUnits.length;
				this.div.appendChild(this.eBox), this.outUnits == "" || this.inUnits == "" ? this.eBox.style.visibility = "hidden" : this.eBox.style.visibility = "visible"
			}
			return this.map.events.register("moveend", this, this.update), this.update(), this.div
		},
		update: function() {
			var e = this.map.getResolution();
			if (!e) return;
			var t = this.map.getUnits(),
				n = OpenLayers.INCHES_PER_UNIT,
				r = this.maxWidth * e * n[t],
				i = 1;
			if (this.geodesic === !0) {
				var s = (this.map.getGeodesicPixelSize().w || 1e-6) * this.maxWidth,
					o = r / n.km;
				i = s / o, r *= i
			}
			var u, a;
			r > 1e5 ? (u = this.outUnits, a = "公里") : (u = this.inUnits, a = "米");
			var f = r / n[u],
				l = this.getBarLen(f);
			f = l / n[t] * n[u];
			var c = f / e / i;
			this.eBox.style.visibility == "visible" && (this.eBox.style.width = Math.round(c) + "px", this.eBox.innerHTML = l + " " + a)
		},
		initialize: function() {
			OpenLayers.Control.prototype.initialize.apply(this, arguments)
		},
		CLASS_NAME: "Gi.ScaleLine"
	})
}), define("control/locationbutton", ["require"], function(e) {
	return OpenLayers.Class(OpenLayers.Control, {
		btn: null,
		draw: function(e) {
			OpenLayers.Control.prototype.draw.apply(this), OpenLayers.Element.addClass(this.div, "olButton");
			var t = this.btn = document.createElement("span");
			return t.className = "olButton olButton_position", this.div.appendChild(t), this.map.events.register("buttonclick", this, this.onButtonClick), this.div
		},
		onButtonClick: function(e) {
			var t = e.buttonElement;
			t === this.btn && !this.status && (this.changeStatus(), this.getPosition(this.success, this.error, this))
		},
		changeStatus: function() {
			this.status ? (this.status = null, OpenLayers.Element.removeClass(this.btn, "olButton_position_loading")) : (this.status = "loading", OpenLayers.Element.addClass(this.btn, "olButton_position_loading"))
		},
		success: function(e) {
			var t = this.map,
				n = this.map.displayProjection,
				r = this.map.getProjection();
			this.changeStatus();
			var i = e.coords;
			t.setCenter((new OpenLayers.LonLat(i.longitude, i.latitude)).transform(n, r), this.zoom)
		},
		error: function(e) {
			this.changeStatus();
			var t;
			switch (e.code) {
			case e.PERMISSION_DENIED:
				t = "用户拒绝了Geolocation的请求";
				break;
			case e.POSITION_UNAVAILABLE:
				t = "本地信息无法获得";
				break;
			case e.TIMEOUT:
				t = "请求超时";
				break;
			case e.UNKNOWN_ERROR:
				t = "未知原因发生"
			}
			alert(t)
		},
		getPosition: function(e, t, n) {
			navigator.geolocation && navigator.geolocation.getCurrentPosition(function(t) {
				typeof e == "function" && e.call(n, t)
			}, function(e) {
				var r;
				switch (e.code) {
				case e.PERMISSION_DENIED:
					r = "用户拒绝了Geolocation的请求";
					break;
				case e.POSITION_UNAVAILABLE:
					r = "本地信息无法获得";
					break;
				case e.TIMEOUT:
					r = "请求超时";
					break;
				case e.UNKNOWN_ERROR:
					r = "未知原因发生"
				}
				typeof t == "function" && t.call(n, r)
			})
		},
		CLASS_NAME: "Gi.Location"
	})
}), define("control/panel", ["require"], function(e) {
	return OpenLayers.Class(OpenLayers.Control, {
		contentHTML: null,
		initialize: function(e) {
			this.displayClass = this.CLASS_NAME.replace("OpenLayers.", "ol").replace(/\./g, ""), OpenLayers.Util.extend(this, optin), this.events = new OpenLayers.Events(this), this.eventListeners instanceof Object && this.events.on(this.eventListeners), null == this.id && (this.id = OpenLayers.Util.createUniqueID(this.CLASS_NAME + "_")), this.contentHTML = e
		},
		draw: function(e) {
			OpenLayers.Control.prototype.draw.apply(this, arguments);
			var t = document.createElement("div");
			return OpenLayers.Element.addClass(this.div, "olGiPanel"), t.innerHTML = this.contentHTML, this.div.appendChild(t), this.div
		},
		CLASS_NAME: "Gi.Panel"
	})
}), define("control/movebar", ["require"], function(e) {
	return OpenLayers.Class(OpenLayers.Control.PanZoomBar, {
		moveZoomBar: function() {
			var e = (this.map.getNumZoomLevels() - 1 - this.map.getZoom()) * 11 + this.startTop + 1;
			this.slider.style.top = e + "px"
		},
		CLASS_NAME: "Gi.GiZoom"
	})
}), define("control/zoom", ["require"], function(e) {
	return OpenLayers.Class(OpenLayers.Control.Zoom, {
		draw: function(e) {
			var t = OpenLayers.Control.prototype.draw.apply(this),
				n = this.getOrCreateLinks(t),
				r = n.zoomIn,
				i = n.zoomOut,
				s = this.map.events;
			return i.parentNode !== t && (s = this.events, s.attachToElement(i.parentNode)), s.register("buttonclick", this, this.onZoomClick), this.zoomInLink = r, this.zoomOutLink = i, this.moveTo(e), t
		}
	})
}), define("control/overviewmap", ["require"], function(e) {
	return OpenLayers.Class(OpenLayers.Control.OverviewMap, {
		initialize: function(e) {
			this.minRatio = 10, this.maxRatio = 10, this.minRectSize = 10, this.size = new OpenLayers.Size(180, 120), this.maximized = !0, e = OpenLayers.Util.extend({
				mapOptions: {
					projection: new OpenLayers.Projection("EPSG:900913"),
					displayProjection: new OpenLayers.Projection("EPSG:4326"),
					units: "m",
					maxResolution: 156543.0339,
					maxExtent: new OpenLayers.Bounds(-20037508, -20037508, 20037508, 20037508)
				}
			}, e), OpenLayers.Control.OverviewMap.prototype.initialize.apply(this, [e])
		},
		setOvMapCenter: function() {
			this.ovmap.setCenter(this.map.getExtent().getCenterLonLat())
		},
		updateMapToRect: function() {
			OpenLayers.Control.OverviewMap.prototype.updateMapToRect.apply(this, arguments), this.ovmap.setCenter(this.map.getExtent().getCenterLonLat())
		},
		createMap: function() {
			OpenLayers.Control.OverviewMap.prototype.createMap.apply(this, arguments), this.ovmap.events.register("moveend", this, this.updateRectToMap), this.ovmap.events.register("zoomend", this, this.updateRectToMap)
		},
		draw: function() {
			OpenLayers.Control.prototype.draw.apply(this, arguments);
			if (!(this.layers.length > 0)) {
				if (!this.map.baseLayer) return this.map.events.register("changebaselayer", this, this.baseLayerDraw), this.div;
				var e = this.map.baseLayer.clone();
				this.layers = [e]
			}
			this.element = document.createElement("div"), this.element.className = this.displayClass + "Element", this.element.style.display = "none", this.mapDiv = document.createElement("div"), this.mapDiv.style.width = this.size.w + "px", this.mapDiv.style.height = this.size.h + "px", this.mapDiv.style.position = "relative", this.mapDiv.style.overflow = "hidden", this.mapDiv.id = OpenLayers.Util.createUniqueID("overviewMap"), this.extentRectangle = document.createElement("div"), this.extentRectangle.style.position = "absolute", this.extentRectangle.style.zIndex = 1e3, this.extentRectangle.className = this.displayClass + "ExtentRectangle", this.mapDiv.appendChild(this.extentRectangle), this.element.appendChild(this.mapDiv), this.div.appendChild(this.element);
			if (!this.outsideViewport) {
				this.div.className += " " + this.displayClass + "Container";
				var t = OpenLayers.Util.getImagesLocation(),
					n = "images/overexpand.gif";
				this.maximizeDiv = OpenLayers.Util.createAlphaImageDiv(this.displayClass + "MaximizeButton", null, new OpenLayers.Size(18, 18), n, "absolute"), this.maximizeDiv.style.display = "none", this.maximizeDiv.className = this.displayClass + "MaximizeButton", OpenLayers.Event.observe(this.maximizeDiv, "click", OpenLayers.Function.bindAsEventListener(this.maximizeControl, this)), this.div.appendChild(this.maximizeDiv);
				var n = "images/overcontract.gif";
				this.minimizeDiv = OpenLayers.Util.createAlphaImageDiv("OpenLayers_Control_minimizeDiv", null, new OpenLayers.Size(18, 18), n, "absolute"), this.minimizeDiv.style.display = "none", this.minimizeDiv.className = this.displayClass + "MinimizeButton", OpenLayers.Event.observe(this.minimizeDiv, "click", OpenLayers.Function.bindAsEventListener(this.minimizeControl, this)), this.div.appendChild(this.minimizeDiv);
				var r = ["dblclick", "mousedown"];
				for (var i = 0, s = r.length; i < s; i++) OpenLayers.Event.observe(this.maximizeDiv, r[i], OpenLayers.Event.stop), OpenLayers.Event.observe(this.minimizeDiv, r[i], OpenLayers.Event.stop);
				this.minimizeControl()
			} else this.element.style.display = "";
			return this.map.getExtent() && this.update(), this.map.events.register("moveend", this, this.update), this.map.events.register("zoomend", this, this.update), this.map.events.register("dragend", this, this.update), this.map.events.register("moveend", this, this.setOvMapCenter), this.map.events.register("zoomend", this, this.setOvMapCenter), this.map.events.register("dragend", this, this.setOvMapCenter), this.maximized && this.maximizeControl(), this.div
		},
		CLASS_NAME: "Gi.CtrlOverviewMap"
	})
}), define("control/textContainer", ["require"], function(e) {
	return OpenLayers.Class(OpenLayers.Control, {
		content: null,
		textClass: "olGiText",
		initialize: function(e) {
			typeof e == "object" && typeof e.content == "string" && (this.content = e.content, this.textClass = e.curClass)
		},
		draw: function(e) {
			OpenLayers.Control.prototype.draw.apply(this, arguments);
			var t = "<div class='" + this.textClass + "'>" + this.content + "</div>";
			return this.div.innerHTML = t, this.div
		},
		resetContent: function(e) {
			this.content = e, this.draw()
		},
		CLASS_NAME: "Gi.textContainer"
	})
}), define("control/legend", ["require"], function(e) {
	return OpenLayers.Class(OpenLayers.Control, {
		layers: null,
		colors: null,
		initialize: function(e) {
			if (typeof e != "object") return;
			this.moveTo = e.moveTo, this.showType = e.showType, this.func = e.func;
			var t = e.content && e.content.replace(/(^\s*)|(\s*$)/g, "");
			if (!t) return;
			var n = t.split(/[\r\n]+/),
				r = n[0].replace(/(^\s*)|(\s*$)/g, "");
			isNaN(r.split(" ")[0]) && (this.name = r, n.shift());
			var i = [];
			for (var s = 0, o = n.length; s < o; s++) {
				var u = n[s].replace(/(^\s*)|(\s*$)/g, "").split(/\s+/);
				if (u.length > 1) {
					var a = u[1],
						f = a.split(",");
					f.length == 4 && (a = f[1] + "," + f[2] + "," + f[3]), i.unshift({
						value: u[0],
						color: a,
						alias: u[2],
						image: u[3]
					})
				}
			}
			this.colors = i;
			if (i.length > 1) {
				var l = i[0].value,
					c = i[i.length - 1].value;
				l - c > 0 ? (this.sort = !1, this.maxValue = l, this.minValue = c) : (this.sort = !0, this.minValue = l, this.maxValue = c)
			}
			this.layers = [], this.addlayers(e.layers), OpenLayers.Control.prototype.initialize.apply(this)
		},
		draw: function(e) {
			this.displayClass = "olGiLegend", OpenLayers.Control.prototype.draw.apply(this, arguments), this.divTitle = document.createElement("b"), this.name != null && (this.divTitle.innerText = this.name), this.divColors = document.createElement("ul");
			if (this.colors) {
				var t = "";
				for (var n = 0, r = this.colors.length; n < r; n++) {
					var i = this.colors[n];
					t += "<li>", t += "<i style='background:rgb(" + i.color + ")'></i>", t += i.alias == null ? i.value : i.alias, t += "</li>"					
				}
				this.divColors.innerHTML = t
			}
			return this.div.appendChild(this.divTitle), this.div.appendChild(this.divColors), this.showType && OpenLayers.Element.addClass(this.div, "olGiLegend_v"), this.events = new OpenLayers.Events(this, this.div), this.events.attachToElement(this.div), this.events.register("dblclick", this, this.ondblclick), this.events.register("click", this, this.onclick), this.div
		},
		ondblclick: function(e) {
			var t = this.divColors.style,
				n = this.divTitle;
			t.display == "none" ? (t.display = "", n.innerText = this.name || "") : (t.display = "none", n.innerText = this.name || "色标"), OpenLayers.Event.stop(e)
		},
		onclick: function(e) {
			var t = e.target || e.srcElement,
				n = this.colors,
				r, i;
			t.nodeName == "LI" ? i = t : t.parentNode.nodeName == "LI" && (i = t.parentNode);
			if (i) {
				var s = this.divColors.childNodes,
					o = !1,
					u = "olGiLegend_hide";
				OpenLayers.Element.hasClass(i, u) || (i = i.previousSibling || i.previousElementSibling, o = i == null);
				for (var a = 0, f = s.length; a < f; a++) {
					var l = s[a];
					o ? OpenLayers.Element.addClass(l, u) : (OpenLayers.Element.removeClass(l, u), l == i && (o = !0, r = n[a] && n[a].value))
				}
				r == null && (r = this.sort ? this.minValue : this.maxValue);
				if (this.layers) for (var a = 0, f = this.layers.length; a < f; a++) {
					var c = this.layers[a];
					typeof c.changeRange == "function" && c.changeRange(r)
				}
				this.func && typeof this.func == "function" && this.func(r), OpenLayers.Event.stop(e)
			}
		},
		addlayers: function(e) {
			if (!e) return;
			OpenLayers.Util.isArray(e) || (e = [e]);
			for (var t = 0, n = e.length; t < n; t++) {
				var r = e[t];
				r.setRange(this.value, this.sort, this.maxValue, this.minValue), this.layers.push(r)
			}
		},
		destroy: function() {
			this.layers = null, this.colors = null, OpenLayers.Control.prototype.destroy.apply(this)
		},
		CLASS_NAME: "Gi.legend"
	})
}), define("control/layerManagement", ["require"], function(e) {
	return OpenLayers.Class(OpenLayers.Control.LayerSwitcher, {
		onButtonClick: function(e) {
			var t = e.buttonElement;
			if (t.parentElement && t.parentElement.parentElement === this.div) {
				var n = t.innerHTML,
					r = this.map,
					i = r.layers,
					s = r.baseLayer,
					o = null;
				if (i.length > 0) {
					for (var u = 0, a = i.length; u < a; u++) {
						var f = i[u];
						if (f.name.indexOf(n) > -1) {
							o = f;
							if (typeof f.enabled != "undefined" && f.group != s.group) return;
							if (typeof f.enabled != "undefined" && f.group == s.group && !s.visibility) return;
							f.visibility ? (f.setVisibility(!1), f.group && !f.isBaseLayer && f.group == s.group && (f.enabled = !1)) : (f.setVisibility(!0), f.isBaseLayer && this.map.setBaseLayer(f), f.group && !f.isBaseLayer && f.group == s.group && (f.enabled = !0));
							break
						}
					}
					for (var u = 0, a = i.length; u < a; u++) {
						var f = i[u];
						!f.isBaseLayer && !s.visibility && f.group == s.group && f.setVisibility(!1), o.isBaseLayer && o.visibility && f.group == o.group && typeof f.enabled != "undefined" && (f.enabled == 0 ? f.setVisibility(!1) : f.setVisibility(!0))
					}
				}
			}
		},
		draw: function(e) {
			return OpenLayers.Control.prototype.draw.apply(this, [e]), this.redraw(), this.div
		},
		redraw: function() {
			this.div.innerHTML = "";
			var e = this.map,
				t = e.layers,
				n = t.length,
				r = e.baseLayer,
				i = document.createElement("div"),
				s = document.createElement("div");
			i.innerHTML = "<h4>底图</h4>", s.innerHTML = "<h4>其他</h4>";
			if (n > 0) {
				for (var o = 0; o < n; o++) {
					var u = t[o],
						a = u.visibility,
						f = document.createElement("span");
					OpenLayers.Element.addClass(f, "olButton"), f.innerHTML = u.name, u.isBaseLayer ? i.appendChild(f) : (u.group && (u.group == r.group ? typeof u.enabled == "undefined" && (u.enabled = !0) : typeof u.enabled == "undefined" && (u.enabled = !1)), s.appendChild(f)), a == 0 ? (OpenLayers.Element.addClass(f, "z-un"), !u.isBaseLayer && u.group && (u.group != r.group || u.group == r.group && r.visibility == 0) && OpenLayers.Element.addClass(f, "z-on")) : f.className = f.className.replace(" z-un", "")
				}
				this.div.appendChild(i), this.div.appendChild(s)
			}
			return this.div
		},
		CLASS_NAME: "Gi.LayerManagement"
	})
}), define("control/simpleLegend", ["require"], function(e) {
	return OpenLayers.Class(OpenLayers.Control, {
		colors: null,
		initialize: function(e) {
			typeof e == "object" && (this.moveTo = e.moveTo, e.legend && e.legend.length && (this.legendType = e.lType, this.colors = e.legend, this.name = e.name)), OpenLayers.Control.prototype.initialize.apply(this)
		},
		draw: function(e) {
			var t = this.legendType == "point" ? "background" : "border-top-color";
			this.displayClass = "ol" + this.legendType + "Legend", OpenLayers.Control.prototype.draw.apply(this, arguments), this.divTitle = document.createElement("b"), this.name != null && (this.divTitle.innerText = this.name), this.divColors = document.createElement("ul");
			if (this.colors) {
				var n = "";
				for (var r = 0, i = this.colors.length; r < i; r++) {
					var s = this.colors[r];
					if (!s.alias) break;
					n += "<li>", n += "<i style='" + t + ":" + s.style + "'></i>", n += s.alias || "", n += "</li>"
				}
				this.divColors.innerHTML = n
			}
			return this.div.appendChild(this.divTitle), this.div.appendChild(this.divColors), this.showType && OpenLayers.Element.addClass(this.div, "olGiTyphoonLegend_v"), this.events = new OpenLayers.Events(this, this.div), this.events.attachToElement(this.div), this.events.register("dblclick", this, this.ondblclick), this.events.register("click", this, this.onclick), this.div
		},
		ondblclick: function(e) {
			var t = this.divColors.style,
				n = this.divTitle;
			t.display == "none" ? (t.display = "", n.innerText = this.name || "") : (t.display = "none", n.innerText = this.name || "色标"), OpenLayers.Event.stop(e)
		},
		destroy: function() {
			this.layers = null, this.colors = null, OpenLayers.Control.prototype.destroy.apply(this)
		},
		CLASS_NAME: "Gi.Typhoonlegend"
	})
}), define("control/resources", ["require", "./scaleLine", "./locationbutton", "./panel", "./movebar", "./zoom", "./overviewmap", "./textContainer", "./legend", "./layerManagement", "./simpleLegend"], function(e) {
	return {
		scaleLine: e("./scaleLine"),
		locationButton: e("./locationbutton"),
		panel: e("./panel"),
		movebar: e("./movebar"),
		zoom: e("./zoom"),
		overview: e("./overviewmap"),
		mousePosition: OpenLayers.Control.MousePosition,
		text: e("./textContainer"),
		legend: e("./legend"),
		layerManagement: e("./layerManagement"),
		simpleLegend: e("./simpleLegend")
	}
}), define("core", ["require", "./base/verify", "./core/Popup", "./core/Map", "./core/Markers", "./core/Marker", "./core/BoxMarker", "./layer/resources", "./control/resources"], function(e) {
	var t = e("./base/verify"),
		n = e("./core/Popup"),
		r = e("./core/Map"),
		i = e("./core/Markers"),
		s = e("./core/Marker"),
		o = e("./core/BoxMarker"),
		u = e("./layer/resources"),
		a = e("./control/resources");
	return OpenLayers.Class({
		initialize: function(e, t, n) {
			var i = this.map = new r(e, {
				displayProjection: "EPSG:4326"
			}),
				s = u[t];
			if (s) {
				this.map.addLayers(s(n.wrapDateLine)), typeof n != "object" && (n = {});
				var o = n.center || {};
				this.setCenter(o.lon, o.lat, n.zoom), this.setBaseLayer(n.baselayer)
			}
		},
		tanslateLonLat: function(e, t, n) {
			var r;
			return typeof e == "object" ? (r = e, n = t) : r = new OpenLayers.LonLat(e, t), this.map.tanslateLonLat(r, n)
		},
		getLonLatByPosition: function(e, t) {
			return this.tanslateLonLat(this.map.getLonLatFromPixel({
				x: e,
				y: t
			}), !0)
		},
		getZoom: function(e) {
			if (t(e, "number")) return e;
			if (e == null || typeof e != "object") return this.map.getZoom();
			var n = this.tanslateLonLat(e.left, e.bottom),
				r = this.tanslateLonLat(e.right, e.top);
			return this.map.getZoomForExtent(new OpenLayers.Bounds(n.lon, n.lat, r.lon, r.lat), e.closest)
		},
		getCenter: function() {
			return this.tanslateLonLat(this.map.getCenter(), !0)
		},
		setZoom: function(e) {
			return e = this.getZoom(e), this.map.zoomTo(e), e
		},
		setCenter: function(e, t, n) {
			this.map.setCenter(this.tanslateLonLat(e, t), this.getZoom(n))
		},
		removeEvent: function(e, t) {
			this.map.events.unregister(e, this, t)
		},
		click: function(e) {
			this._clickListeners || (this._clickListeners = [], this.map.events.register("click", this, this._click)), this._clickListeners.push(e)
		},
		_click: function(e) {
			e.lonlat = this.tanslateLonLat(this.map.getLonLatFromPixel(e.xy), !0);
			for (var t = 0, n = this._clickListeners.length; t < n; t++) this._clickListeners[t].call(this.map, e)
		},
		addMarkers: function(e, t) {
			var n = new i("标注层", e, t);
			return this.addLayer(n), n
		},
		addLayer: function(e) {
			return e && this.map.addLayer(e), e
		},
		removeLayer: function(e) {
			e && e.destroy()
		},
		setBaseLayer: function(e) {
			var n = this.map,
				r;
			t(e, "string") ? r = n.getLayersBy("alias", e)[0] || n.getLayersByName(e)[0] : typeof e == "object" && (r = e), r || (r = n.getLayersBy("isBaseLayer", !0)[0]), r && n.setBaseLayer(r)
		},
		addMarker: function(e, t) {
			if (t) {
				OpenLayers.Util.isArray(e) || (e = [e]);
				var n = [];
				for (var r = 0, i = e.length; r < i; r++) {
					var o = e[r],
						u = o.lon || 0,
						a = o.lat || 0,
						f = new s(this.tanslateLonLat(new OpenLayers.LonLat(u, a)), new OpenLayers.Icon(o.url || "", new OpenLayers.Size(o.width || 24, o.height || 24), new OpenLayers.Pixel(o.offsetX || 0, o.offsetY || 0)));
					f.setContent(o.content), f._lon = u, f._lat = a;
					var l = o.ev;
					typeof l == "function" && f.events.register("mousedown", f, function(e) {
						l.call(this), OpenLayers.Event.stop(e)
					}), n.push(f)
				}
				for (var r = 0, i = n.length; r < i; r++) t.addMarker(n[r]);
				return t.clusterLayer && t.clusterLayer.addPoints(n), n
			}
		},
		addLabelBox: function(e, t, n, r) {
			var i = this.map,
				s, u;
			typeof n != null && (s = n.w, u = n.h);
			var a = new o("labelBox", e, t, new OpenLayers.Size(s || 50, u || 50), {
				x: r && r.x,
				y: r && r.y
			});
			return i.addPopup(a), a
		},
		removeLabelBox: function(e) {
			this.map.removePopup(e)
		},
		addPopup: function(e, t, r, i, s, o, u) {
			var a = this.map,
				f = new n("", new OpenLayers.LonLat(0, 0), new OpenLayers.Size(20, 20), null, r, o, u);
			return a.addPopup(f), f.setLonlat(e, t, i, s), f
		},
		removePopup: function(e) {
			e && e.destory()
		},
		addControl: function(e, n, r, i) {
			var s = a[e];
			if (s) {
				i = i || {}, i.moveTo = function(e) {
					if (e != null && this.div != null) {
						var t = this.div.style,
							n = e.x,
							r = e.y;
						n >= 0 ? (t.left = n + "px", t.right = "auto") : (t.right = -n + "px", t.left = "auto"), r >= 0 ? (t.top = r + "px", t.bottom = "auto") : (t.bottom = -r + "px", t.top = "auto")
					}
				};
				var o = new s(i);
				return n = t(n, "number") ? n : 0, r = t(r, "number") ? r : 0, this.map.addControl(o, new OpenLayers.Pixel(n, r)), o
			}
		},
		removeControl: function(e) {
			e && e.destroy()
		},
		getMouseLonLat: function(e) {
			var t = this;
			t.addEvent("click", function(n) {
				var r = new OpenLayers.Pixel(n.xy.x, n.xy.y),
					i = t.map.getLonLatFromPixel(r),
					s = t.map.tanslateLonLat(i, !0);
				typeof e == "function" && e(s.lon, s.lat)
			})
		}
	})
}), define("draw/draw", [], function() {
	return OpenLayers.Class(OpenLayers.Layer.Vector, {
		getPoint: function(e) {
			return new OpenLayers.Geometry.Point(e.lon, e.lat)
		},
		getLine: function(e) {
			if (e) {
				var t = [];
				for (var n = 0, r = e.length; n < r; n++) {
					var i = this.getPoint(e[n]);
					t.push(new OpenLayers.Geometry.Point(i.x, i.y))
				}
				return new OpenLayers.Geometry.LineString(t)
			}
		},
		getLinearRing: function(e) {
			if (e && e.length > 2) {
				var t = [];
				for (var n = 0, r = e.length; n < r; n++) t.push(this.getPoint(e[n]));
				return new OpenLayers.Geometry.LinearRing(t)
			}
		},
		getPolygon: function(e) {
			if (e && e.length) {
				var t = [];
				for (var n = 0, r = e.length; n < r; n++) t.push(this.getLinearRing(e[n]));
				return new OpenLayers.Geometry.Polygon(t)
			}
		},
		getSector: function(e, t, n, r, i) {
			var s = Math.PI * (1 / n - .5);
			r && (s += r / 180 * Math.PI);
			var o, u, a, f = [];
			for (var l = 0; l < n; ++l) {
				var c;
				i ? c = l * (i / 360) : c = l * ((360 - r) / 360), o = s + c * 2 * Math.PI / n, u = e.x + t * Math.cos(o), a = e.y + t * Math.sin(o), f.push(new OpenLayers.Geometry.Point(u, a))
			}
			var h = new OpenLayers.Geometry.LinearRing(f);
			return h
		},
		CLASS_NAME: "Gi.Drawing"
	})
}), define("plugins/line", ["require", "../draw/draw"], function(e) {
	function n(e) {
		var t = [];
		for (var n = 0, r = e.length; n < r; n++) {
			var i = e[n].lon,
				s = e[n].lat,
				o = this.tanslateLonLat(i, s);
			t.push(o)
		}
		return t
	}
	var t = e("../draw/draw"),
		r = OpenLayers.Class(t, {
			setLine: function(e) {
				this.removeAllFeatures(), this.addFeatures(new OpenLayers.Feature.Vector(this.getLine(e), null, this.style))
			},
			setStyle: function(e) {
				if (e && typeof e == "object" && this.features.length) {
					this.style = e = e || this.style;
					var t = this.features[0].style,
						n = e.strokeColor;
					n != null && (t.strokeColor = n);
					var r = e.strokeOpacity;
					r != null && (t.strokeOpacity = r);
					var i = e.strokeWidth;
					i != null && (t.strokeWidth = i), this.redraw()
				}
			}
		});
	return function(e, t, i) {
		var s = new r(i || "线", {
			style: t
		});
		this.addLayer(s);
		var o = n.call(this, e);
		return s.setLine(o), s
	}
}), define("plugins/boundary", ["require", "../draw/draw"], function(e) {
	var t = e("../draw/draw");
	OpenLayers.Renderer.SVG.prototype.MAX_PIXEL = 15e7;
	var n = function(e) {
			var t = new Object;
			t.lines = new Array, t.center = new String;
			var n = [],
				r = e.getElementsByTagName("Line");
			if (!r.length) {
				var i = e.getElementsByTagName("Area");
				for (var s = 0, o = i[0].attributes.length; s < o; s++) i[0].attributes[s].name == "Coords" && (r = i[0].attributes[s]), i[0].attributes[s].name == "Center" && (t.center = i[0].attributes[s]);
				var u = r.childNodes[0].nodeValue.split(","),
					a = [];
				for (var f = 0, l = 0, c = u.length; f < c; f++) {
					var h = u[f],
						p = u[++f];
					a[l] = this.tanslateLonLat(h, p), l++
				}
				n.push(a)
			} else {
				var d = e.getElementsByTagName("Area");
				for (var s = 0, o = d[0].attributes.length; s < o; s++) if (d[0].attributes[s].name == "Center") {
					t.center = d[0].attributes[s];
					break
				}
				for (var v = 0, m = r.length; v < m; v++) {
					var u = r[v].childNodes[0].nodeValue.split(","),
						a = [];
					for (var f = 0, l = 0, c = u.length; f < c; f++) {
						var h = u[f],
							p = u[++f];
						a[l] = this.tanslateLonLat(h, p), l++
					}
					n.push(a)
				}
			}
			return n.push([this.tanslateLonLat(-180, 90), this.tanslateLonLat(-180, -90), this.tanslateLonLat(180, -90), this.tanslateLonLat(180, 90)]), t.lines = n, t
		},
		r = OpenLayers.Class(t, {
			setContours: function(e) {
				this.removeAllFeatures(), e && this.addFeatures(new OpenLayers.Feature.Vector(this.getPolygon(e), null, this.style))
			},
			setStyle: function(e) {
				if (e && typeof e == "object" && this.features.length) {
					this.style = e = e || this.style;
					var t = this.features[0].style,
						n = e.fillColor;
					n != null && (t.fillColor = n);
					var r = e.fillOpacity;
					r != null && (t.fillOpacity = r);
					var i = e.strokeColor;
					i != null && (t.strokeColor = i);
					var s = e.strokeOpacity;
					s != null && (t.strokeOpacity = s);
					var o = e.strokeWidth;
					o != null && (t.strokeWidth = o), this.redraw()
				}
			},
			CLASS_NAME: "Gi.Drawing.Boundary"
		});
	return function(e, t, i) {
		var s = new r(e || "Boundary", {
			style: i
		});
		s.isBoundary = !0, this.addLayer(s);
		var o = n.call(this, t);
		return s.setContours(o.lines), s
	}
}), define("draw/m4", ["require", "./draw"], function(e) {
	var t = e("./draw");
	return OpenLayers.Class(t, {
		setRange: function(e, t, n, r) {
			isNaN(e) || (this.value = e), n != null && (this.maxValue = n), r != null && (this.minValue = r), t != null && (this.sort = t)
		},
		compareRange: function(e) {
			return isNaN(this.value) ? !0 : this.sort ? e - this.value <= 0 || this.maxValue != null && this.maxValue == this.value : e - this.value >= 0 || this.minValue != null && this.minValue == this.value
		},
		changeRange: function(e) {},
		CLASS_NAME: "Gi.M4"
	})
}), define("base/rgb2hex", [], function() {
	var e = function(e, t) {
			var n = e.toString(16);
			while (n.length < t) n = "0" + n;
			return n
		};
	return function(t) {
		if (t && typeof t == "string") {
			if (t.charAt(0) == "#") return t;
			var n = ("0" + t + "0").split(/\D+/),
				r = Number(n[1]) * 65536 + Number(n[2]) * 256 + Number(n[3]);
			return "#" + e(r, 6)
		}
		return
	}
}), define("plugins/contours", ["require", "../draw/m4", "../base/rgb2hex"], function(e) {
	var t = e("../draw/m4"),
		n = e("../base/rgb2hex"),
		r = function(e) {
			var t = [],
				n = e.getElementsByTagName("R");
			for (var r = 0, i = n.length; r < i; r++) {
				var s = n[r],
					o = s.getAttribute("Color"),
					u = s.getAttribute("Value"),
					a = o.split(","),
					f = 1;
				a.length == 4 && (o = a[1] + "," + a[2] + "," + a[3], f = a[0] / 255);
				var l = {
					strokeWidth: 0
				};
				l.fillColor = "rgb(" + o + ")", l.fillOpacity = f;
				var c = [],
					h = n[r].getElementsByTagName("L");
				for (var p = 0, d = h.length; p < d; p++) {
					var v = [],
						a = h[p].childNodes[0].nodeValue.split(" ");
					for (var m = 0, g = a.length; m < g; m++) {
						var y = a[m].split(",");
						v.push({
							lon: y[0],
							lat: y[1]
						})
					}
					c.push(v)
				}
				t.push({
					value: u,
					style: l,
					group: c
				})
			}
			return t
		},
		i = OpenLayers.Class(t, {
			polygons: null,
			getPolygons: function(e) {
				if (e && e.length) {
					var t = [];
					for (var n = 0, r = e.length; n < r; n++) {
						var i = e[n],
							s = this.getPolygon(i.group);
						s.transform(new OpenLayers.Projection(this.map.displayProjection), new OpenLayers.Projection(this.map.getProjection())), t[n] = {
							value: i.value,
							polygon: new OpenLayers.Feature.Vector(s, null, i.style)
						}
					}
					return t
				}
			},
			changeRange: function(e, t) {
				this.setRange(e, t);
				if (this.polygons && this.polygons.length) for (var n = 0, r = this.polygons.length; n < r; n++) {
					var i = this.polygons[n],
						s = OpenLayers.Util.getElement(i.polygon.geometry.id);
					s && (s.style.display = this.compareRange(i.value) ? "" : "none")
				}
			},
			redraw: function() {
				t.prototype.redraw.apply(this);
				if (this.polygons) return;
				this.polygons = this.getPolygons(this.lines);
				if (!this.polygons) return;
				var e = [];
				for (var n = 0, r = this.polygons.length; n < r; n++) e.push(this.polygons[n].polygon);
				this.addFeatures(e), this.changeRange()
			},
			destroy: function() {
				OpenLayers.Layer.Vector.prototype.destroy.apply(this, arguments), this.polygons = null
			},
			CLASS_NAME: "Gi.Drawing.Contours"
		});
	return function(e, t, n, s, o) {
		var u = r.call(this, t),
			a = new i(e || "Contours", {
				lines: u,
				value: s,
				sort: o
			});
		return this.addLayer(a), n != null && a.setOpacity(n), a
	}
}), define("plugins/polygonfill", ["require", "../draw/m4", "../base/rgb2hex"], function(e) {
	var t = e("../draw/m4"),
		n = e("../base/rgb2hex"),
		r = function(e, t, n) {
			var r = e.split(","),
				i = [],
				s = {
					strokeWidth: 0
				};
			s.fillColor = t || "#000000", s.fillOpacity = n || 1;
			var o = [],
				u = [];
			for (var a = 0; a < r.length; a++) a % 2 == 1 && u.push({
				lon: r[a - 1],
				lat: r[a]
			});
			return o.push(u), i.push({
				style: s,
				group: o
			}), i
		},
		i = OpenLayers.Class(t, {
			polygons: null,
			getPolygons: function(e) {
				if (e && e.length) {
					var t = [];
					for (var n = 0, r = e.length; n < r; n++) {
						var i = e[n],
							s = this.getPolygon(i.group);
						s.transform(new OpenLayers.Projection(this.map.displayProjection), new OpenLayers.Projection(this.map.getProjection())), t[n] = {
							polygon: new OpenLayers.Feature.Vector(s, null, i.style)
						}
					}
					return t
				}
			},
			redraw: function() {
				t.prototype.redraw.apply(this), this.polygons = this.getPolygons(this.lines);
				var e = [];
				for (var n = 0, r = this.polygons.length; n < r; n++) e.push(this.polygons[n].polygon);
				this.addFeatures(e)
			},
			destroy: function() {
				OpenLayers.Layer.Vector.prototype.destroy.apply(this, arguments), this.polygons = null
			},
			CLASS_NAME: "Gi.Drawing.Contours"
		});
	return function(e, t, n, s) {
		var o = r(t, n, s),
			u = new i(e || "Contours", {
				lines: o
			});
		return this.addLayer(u), s != null && u.setOpacity(s), u
	}
}), define("plugins/dtext", ["require", "../draw/m4"], function(e) {
	var t = e("../draw/m4"),
		n = OpenLayers.Class(t, {
			formart: function(e) {
				var t = [];
				for (var n = 0; n < e.length; n++) t[n] = {
					content: e[n].n,
					lonlat: this.map.tanslateLonLat(new OpenLayers.LonLat(e[n].lon, e[n].lat))
				};
				return {
					group: t
				}
			},
			moveTo: function(e, t, n) {
				this.lonlats = this.lonlats || this.formart(this.text), t && this.calcData(), this.changeRange(), OpenLayers.Layer.Vector.prototype.moveTo.apply(this, arguments)
			},
			removeAllFeatures: function() {},
			changeRange: function() {
				this.removeAllFeatures();
				var e = [],
					t = this.map.calculateBounds();
				for (var n = 0, r = this.data.length; n < r; n++) {
					var i = this.data[n].lonlat,
						s = new OpenLayers.Feature.Vector(new OpenLayers.Geometry.Point(i.lon, i.lat), {
							text: this.data[n].content
						});
					e.push(s)
				}
				this.addFeatures(e)
			},
			calcData: function() {
				this.data = this.lonlats.group
			},
			CLASS_NAME: "Gi.Drawing.Grid"
		});
	return function(e, t, r, i) {
		var s = r || {
			fontSize: 14
		};
		s.label = "${text}";
		var o = new n(e, {
			text: t,
			styleMap: new OpenLayers.StyleMap(s),
			invalidValue: i
		});
		return this.addLayer(o), o
	}
}), define("plugins/grid", ["require", "../draw/m4"], function(e) {
	var t = e("../draw/m4"),
		n = OpenLayers.Class(t, {
			formart: function(e) {
				var t = e.split(/\s+/g),
					n = t[15] - 0,
					r = t[16] - 0,
					i = t.slice(22),
					s = t[13] - 0,
					o = t[11] - 0,
					u = t[14] - 0,
					a = t[12] - 0,
					f = t[9] - 0,
					l = t[10] - 0,
					c = [];
				for (var h = 0; h < r; h++) {
					c[h] = [];
					var p = s + l * h;
					for (var d = 0; d < n; d++) {
						var v = !0,
							m = i[h * n + d];
						if (this.invalidValue && this.invalidValue.length) for (var g = 0, y = this.invalidValue.length; g < y; g++) if (m == this.invalidValue[g]) {
							v = null;
							break
						}
						c[h][d] = v && {
							content: m,
							lonlat: this.map.tanslateLonLat(new OpenLayers.LonLat(o + f * d, p))
						}
					}
				}
				return {
					group: c,
					num: {
						lon: n,
						lat: r
					},
					start: this.map.tanslateLonLat(new OpenLayers.LonLat(o, s)),
					end: this.map.tanslateLonLat(new OpenLayers.LonLat(a, u))
				}
			},
			moveTo: function(e, t, n) {
				this.lonlats = this.lonlats || this.text && this.formart(this.text), t && this.calcData(), this.changeRange(), OpenLayers.Layer.Vector.prototype.moveTo.apply(this, arguments)
			},
			changeRange: function(e) {
				this.removeAllFeatures(), this.setRange(e);
				var t = [],
					n = this.map.calculateBounds();
				for (var r = 0, i = this.data.length; r < i; r++) {
					var s = this.data[r],
						o = s.content,
						u = s.lonlat;
					if (o != null && n.contains(u.lon, u.lat)) {
						var a = new OpenLayers.Feature.Vector(new OpenLayers.Geometry.Point(u.lon, u.lat));
						this.value != null && (o = this.compareRange(o) ? o : ""), a.attributes = {
							type: o
						}, t.push(a)
					}
				}
				this.addFeatures(t)
			},
			calcData: function() {
				var e = this.lonlats;
				if (!e) return;
				var t = [],
					n = e.group,
					r = this.map.getPixelFromLonLat(e.start),
					i = this.map.getPixelFromLonLat(e.end),
					s = e.num,
					o = s.lat,
					u = s.lon,
					a = Math.round(u * 50 / (i.x - r.x)) || 1,
					f = Math.round(o * 50 / (i.y - r.y)) || 1;
				for (var l = 0; l < o; l++) if (l % f == 0) for (var c = 0; c < u; c++) if (c % a == 0) {
					var h = n[l][c];
					h && t.push(h)
				}
				this.data = t
			},
			CLASS_NAME: "Gi.Drawing.Grid"
		});
	return function(e, t, r, i) {
		var s = {
			fontColor: "#000",
			fontFamily: "sans-serif",
			fontSize: 12
		};
		if (typeof r == "object") for (var o in r) s[o] = r[o];
		s.label = "${type}";
		var u = new n(e || "格点", {
			text: t,
			styleMap: new OpenLayers.StyleMap(s),
			invalidValue: i
		});
		return this.addLayer(u), u
	}
}), define("plugins/rail", ["require", "../draw/draw"], function(e) {
	var t = e("../draw/draw"),
		n = OpenLayers.Class(t, {
			linesFormat: function(e) {
				var t = new OpenLayers.Projection(this.map.displayProjection),
					n = new OpenLayers.Projection(this.map.getProjection());
				for (var r = 0, i = e.length; r < i; r++) {
					var s = e[r].geometry;
					for (var o = 0, u = s.components.length; o < u; o++) {
						var a = s.components[o];
						a.transform(t, n)
					}
					this.bounds = null
				}
			},
			redraw: function() {
				t.prototype.redraw.apply(this);
				if (this.json && this.json.length) {
					var e = this,
						n = [],
						r = [];
					for (var i = 0, s = this.json.length; i < s; i++) {
						var o = this.json[i].lines;
						for (var u = 0, a = o.length; u < a; u++) {
							line = o[u].line;
							var f = [];
							for (var l = 0, c = line.length; l < c; l++) f.push(line[l]);
							o[u].position == "overground" ? n.push(new OpenLayers.Feature.Vector(e.getLine(f), null, {
								strokeWidth: 5,
								strokeColor: "#00588b"
							})) : o[u].position == "underground" && r.push(new OpenLayers.Feature.Vector(e.getLine(f), null, {
								strokeWidth: 5,
								strokeColor: "#008000"
							}))
						}
					}
					this.linesFormat(n), this.addFeatures(n), this.linesFormat(r), this.addFeatures(r)
				}
			},
			destroy: function() {
				t.prototype.destroy.apply(this), this.markers && this.markers.destroy(), this.markers = null, this.ps = null
			},
			CLASS_NAME: "Gi.Drawing.Rail"
		}),
		r = new Array;
	return function(e, t, i) {
		var s = new n(e || "轨道线", {
			json: t.lines,
			style: {
				strokeWidth: 5,
				strokeColor: "#008000"
			}
		});
		this.addLayer(s), s.markers = this.addMarkers();
		for (var o = 0, u = t.stations.length; o < u; o++) r.push(t.stations[o]);
		var a = [],
			f, l;
		for (var o = 0, c = r.length; o < c; o++) f = r[o], f.position == "underground" ? l = "images/marker-g.png" : f.position == "overground" && (l = "images/marker-b.png"), a.push({
			lon: f.lonlat[1],
			lat: f.lonlat[0],
			url: l,
			width: 13,
			height: 13,
			position: "mc",
			offsetX: -5,
			offsetY: -5,
			text: "<div class='rail-sname'>" + f.name + "</div>",
			content: {
				name: f.name
			},
			ev: null
		});
		var h = this.addMarker(a, s.markers);
		return s.ps = h, s
	}
}), define("plugins/tile", ["require", "../base/verify"], function(e) {
	var t = e("../base/verify");
	return function(e, n, r, i) {
		if (!t(e, "empty")) {
			n = t(n, "empty") ? "tile layer" : n, i = typeof i == "object" ? i : {}, i.sphericalMercator = !0, i.isBaseLayer = !1, i.zIndex = t(r, "number") ? parseInt(r - 0) : null;
			var s = new OpenLayers.Layer.XYZ(n, e, i);
			return this.addLayer(s), s
		}
	}
}), define("plugins/typhoon/warningLineLayer", ["require", "../../draw/draw"], function(e) {
	var t = e("../../draw/draw"),
		n = [{
			strokeColor: "#ff0000",
			strokeWidth: 1,
			strokeDashstyle: "solid"
		}, {
			strokeColor: "blue",
			strokeWidth: 1,
			strokeDashstyle: "longdash"
		}],
		r = [{
			label: "24\n小\n时\n警\n戒\n线",
			fontColor: "#ff0000",
			fontSize: 13,
			labelXOffset: 12
		}, {
			label: "48\n小\n时\n警\n戒\n线",
			fontColor: "blue",
			fontSize: 13,
			labelXOffset: 12
		}],
		i = [
			["127,34", "127,22", "119,18", "119,11", "113,4.5", "105,0"],
			["132,34", "132,15", "120,0", "105,0"]
		];
	return OpenLayers.Class(t, {
		initialize: function() {
			t.prototype.initialize.apply(this, ["warningline"])
		},
		showNameByLevel: function(e) {
			if (e && e.zoomChanged) {
				var t = this.map.getZoom(),
					n, i, s;
				for (n = 0, i = this.names.length; n < i; n++) s = this.names[n], s.style = this.getNameStyle(t, r[n]), this.drawFeature(s)
			}
		},
		getNameStyle: function(e, t) {
			return e > 2 ? t : {
				display: "none"
			}
		},
		redraw: function() {
			t.prototype.redraw.apply(this);
			var e = [],
				s = [],
				o = this.map.getZoom(),
				u, a, f, l, c, h, p;
			for (u = 0, f = i.length; u < f; u++) {
				c = i[u], p = [];
				for (a = 0, l = c.length; a < l; a++) h = c[a].split(","), p.push(this.map.tanslateLonLat(new OpenLayers.LonLat(h[0], h[1])));
				e.push(new OpenLayers.Feature.Vector(this.getLine(p), null, n[u])), s.push(new OpenLayers.Feature.Vector(this.getPoint({
					lon: (p[0].lon + p[1].lon) / 2,
					lat: (p[0].lat + p[1].lat) / 2
				}), null, this.getNameStyle(o, r[u])))
			}
			this.names = s, this.addFeatures(e.concat(s)), this.events.register("moveend", this, this.showNameByLevel)
		},
		destroy: function() {
			t.prototype.destroy.apply(this), this.names = null
		}
	})
}), define("plugins/typhoon/PathsLayer", ["require", "../../draw/draw"], function(e) {
	var t = e("../../draw/draw");
	return OpenLayers.Class(t, {
		initialize: function(e) {
			t.prototype.initialize.apply(this), this.box = e
		},
		getStyle: function(e) {
			if (e) {
				var t = this.box.publishers[e];
				if (t) return {
					strokeColor: t.style,
					strokeWidth: 1,
					strokeDashstyle: "dash"
				};
				return
			}
			return {
				strokeColor: "#000",
				strokeWidth: 3
			}
		},
		add: function(e, t) {
			var n = this.getStyle(t);
			if (!n) return;
			var r = new OpenLayers.Feature.Vector(this.getLine(e), null, n);
			return this.addFeatures(r), r
		},
		remove: function(e) {
			this.removeFeatures(e)
		},
		redrawPath: function(e, t, n) {
			var r = e.geometry;
			r.addPoint(this.getPoint({
				lon: t,
				lat: n
			})), this.renderer.redrawNode(r.id, r, e.style || {}, this.id)
		},
		destroy: function() {
			t.prototype.destroy.apply(this), this.box = null
		},
		CLASS_NAME: "GI.Typhoon.Paths"
	})
}), define("plugins/typhoon/NamesLayer", ["require"], function(e) {
	return OpenLayers.Class({
		initialize: function(e, t) {
			this.box = e, this.namesLayerClass = t || "tptxt"
		},
		add: function(e, t, n) {
			return this.box.gis.addLabelBox(new OpenLayers.LonLat(t, n), "<div class='" + this.namesLayerClass + "'>" + e + "</div>", {
				w: 20,
				h: 50
			}, {
				x: 20,
				y: 10
			})
		},
		remove: function(e) {
			this.box.gis.removeLabelBox(e)
		},
		distroy: function() {
			this.box = null
		}
	})
}), define("plugins/typhoon/PointsLayer", ["require", "../../draw/draw"], function(e) {
	var t = e("../../draw/draw");
	return OpenLayers.Class(t, {
		eventListeners: {
			featureclick: function(e) {
				var t = e.feature.data,
					n = t.data,
					r = t.typhoon;
				return r.box.addPopup(r.data.name, r.data.type, n, t.isForecast), t.isForecast || r.changeTime(n.time), !1
			},
			featureover: function(e) {
				var t = e.feature.data,
					n = t.data,
					r = t.typhoon;
				return r.box.addPopup(r.data.name, r.data.type, n), this.drawFeature(e.feature, this.getStyle(n.windPower, t.isForecast, !0)), !1
			},
			featureout: function(e) {
				var t = e.feature.data,
					n = t.data,
					r = t.typhoon;
				return r.box.hidePopup(), this.drawFeature(e.feature, this.getStyle(n.windPower, t.isForecast)), !1
			}
		},
		initialize: function(e) {
			t.prototype.initialize.apply(this), this.box = e
		},
		getStyle: function(e, t, n) {
			e = parseFloat(e);
			var r = n ? 6 : 4,
				i = t ? "#666" : "#000",
				s = "#fff";
			if (!isNaN(e)) {
				var o = [6, 8, 10, 12, 14, 16];
				for (var u = o.length - 1; u >= 0; u--) if (e >= o[u]) {
					s = this.box.windPowers[u].style;
					break
				}
			}
			return {
				strokeWidth: 1,
				strokeColor: i,
				pointRadius: r,
				fillColor: s
			}
		},
		add: function(e, t, n) {
			var r = new OpenLayers.Feature.Vector(this.getPoint({
				lon: e._lon,
				lat: e._lat
			}), {
				typhoon: t,
				data: e,
				isForecast: n
			}, this.getStyle(e.windPower, n));
			return this.addFeatures(r), r
		},
		adds: function(e, t, n) {
			var r, i, s, o = [];
			for (r = 0, i = e.length; r < i; r++) o[r] = this.add(e[r], t, n);
			return o
		},
		remove: function(e) {
			this.removeFeatures(e)
		},
		destroy: function() {
			t.prototype.destroy.apply(this), this.box = null
		},
		CLASS_NAME: "GI.Typhoon.Points"
	})
}), define("plugins/typhoon/WindRingLayer", ["require", "../../draw/draw"], function(e) {
	var t = e("../../draw/draw"),
		n = [{
			fillColor: "#2ab746",
			fillOpacity: .5,
			strokeWidth: 1,
			strokeColor: "#2ab746"
		}, {
			fillColor: "#d7d232",
			fillOpacity: .5,
			strokeWidth: 1,
			strokeColor: "#d7d232"
		}, {
			fillColor: "red",
			fillOpacity: .5,
			strokeWidth: 1,
			strokeColor: "red"
		}];
	return OpenLayers.Class(t, {
		initialize: function(e, r) {
			t.prototype.initialize.apply(this), this.box = e, this.style = r || n
		},
		add: function(e, t, n, r, i) {
			var s = this.getPoint({
				lon: e,
				lat: t
			}),
				o = [];
			n && o.push(this._add(s, n, 0)), r && o.push(this._add(s, r, 1)), i && o.push(this._add(s, i, 2)), this.addFeatures(o);
			var u = this.box.gis.addLabelBox(new OpenLayers.LonLat(e, t), "<div class='wind-ring animation'></div>", {
				w: 50,
				h: 50
			});
			return {
				gif: u,
				features: o
			}
		},
		_add: function(e, t, n) {
			var r, i = new OpenLayers.LonLat(120, 30);
			i = this.map.tanslateLonLat(i), i = this.getPoint(i);
			var s = new OpenLayers.LonLat(119, 29);
			s = this.map.tanslateLonLat(s), s = this.getPoint(s);
			var o = (i.x - s.x) / 111.11;
			return t *= o, new OpenLayers.Feature.Vector(new OpenLayers.Geometry.Polygon.createRegularPolygon(e, t, 100, 0), {
				radius: t
			}, this.style[n])
		},
		remove: function(e) {
			if (!e) return;
			this.removeFeatures(e.features), this.box.gis.removeLabelBox(e.gif)
		},
		move: function(e, t, n) {
			if (!n) return;
			var r = n.features;
			for (var i = 0, s = r.length; i < s; i++) {
				var o = r[i],
					u = new OpenLayers.Geometry.Polygon.createRegularPolygon(this.getPoint({
						lon: e,
						lat: t
					}), o.data.radius, 100, 0);
				o.geometry.components = u.components, this.drawFeature(o)
			}
			n.gif.setLonlat(e, t)
		},
		destroy: function() {
			t.prototype.destroy.apply(this), this.box = null
		},
		CLASS_NAME: "GI.Typhoon.WindRing"
	})
}), define("plugins/typhoon/xml2json", ["require"], function(e) {
	var t = function(e, t, n) {
			var r = e.substr(0, 2),
				i = e.substr(2, 2),
				s = e.substr(4, 2);
			if (n && t) {
				var o = t.substr(0, 4),
					u = new Date(o + "/" + r + "/" + i + " " + s + ":00"),
					a = u.getTime(),
					f = new Date(a + n * 60 * 60 * 1e3);
				r = f.getMonth() + 1, r = r > 9 ? r : "0" + r, i = f.getDate(), i = i > 9 ? i : "0" + i, s = f.getHours(), s = s > 9 ? s : "0" + s, e = "" + r + i + s
			}
			return e
		},
		n = function(e, t) {
			var n = e.getElementsByTagName(t);
			return n = n && n[0], n = n && n.childNodes, n = n && n[0], n && n.nodeValue
		};
	return function(e) {
		var r, i, s, o, u, a, f, l, c, h, p, d, v, m, g, y, b, w, E = {},
			S = [],
			x = e.getElementsByTagName("TyphoonInfo"),
			T = x.length - 1,
			N = n(x[0], "NO");
		for (r = T; r >= 0; r--) i = x[r], o = n(i, "ForecastHours"), l = n(i, "Publisher"), f = n(i, "Time"), o && f && l && (o = +o, o ? (u = n(i, "ForecastLongitude"), a = n(i, "ForecastLatitude"), u && u != 9999 && a && a != 9999 && (p = n(i, "ForecastAirPressure"), c = n(i, "ForecastWindPower"), c = c && c != 9999 ? c : null, s = E[f], s || (s = E[f] = {}), s[l] || (s[l] = []), s[l].push({
			time: t(f, N, o),
			lon: u,
			lat: a,
			pressure: p && p != 9999 ? p : null,
			windPower: c,
			windVelocity: h
		}))) : l == "BABJ" && (b = n(i, "NameCn"), w = n(i, "Type"), u = n(i, "Longitude"), a = n(i, "Latitude"), u && u != 9999 && a && a != 9999 && (c = n(i, "WindPower"), c = c && c != 9999 ? c : 0, p = n(i, "AirPressure"), h = n(i, "WindVelocity"), d = n(i, "Level7Distance"), v = n(i, "Level10Distance"), m = n(i, "Level12Distance"), g = n(i, "MoveVelocity"), y = n(i, "Direction"), S.push({
			time: f,
			lon: u,
			lat: a,
			windPower: c,
			windVelocity: h,
			pressure: p,
			direction: y && y != 9999 ? y : null,
			moveVelocity: g && g != 9999 ? g : null,
			lv7: d && d != 9999 ? d : null,
			lv10: v && v != 9999 ? v : null,
			lv12: m && m != 9999 ? m : null
		}))));
		for (r = S.length - 1; r >= 0; r--) {
			s = S[r], s.forecasts = E[s.time];
			for (var C in s.forecasts) {
				var k = s.forecasts[C];
				k.sort(function(e, t) {
					return e.time < t.time ? 1 : -1
				})
			}
		}
		return {
			id: N,
			name: b,
			type: w,
			list: S
		}
	}
}), define("animation/animation", ["require"], function(e) {
	var t = OpenLayers.Class({
		initialize: function(e, t, n, r, i, s) {
			e && e.length > 1 && (this.frames = parseInt(t) || 1, this.second = n * 1e3 || 20, this.timer = null, this.lonlats = e, this.index = 0, this.maxNum = e.length, this.turnCallback = i, this.stopCallback = s, this.moveCallback = r, this.run())
		},
		destroy: function() {
			this.stop(), this.lonlats = null, this.turnCallback = null, this.stopCallback = null, this.moveCallback = null
		},
		_move: function() {
			++this.curFrames, this.curFrames < this.frames ? typeof this.moveCallback == "function" && this.moveCallback(this.start.lon + this.lonIncrease * this.curFrames, this.start.lat + this.latIncrease * this.curFrames) : this.curFrames == this.frames && (typeof this.moveCallback == "function" && this.moveCallback(this.end.lon, this.end.lat), this.stop(), this.run())
		},
		run: function() {
			if (this.index < this.maxNum - 1) {
				var e = this;
				if (!this.running) {
					this.running = !0;
					if (!this.curFrames || this.curFrames >= this.frames) this.start = this.lonlats[this.index], this.end = this.lonlats[this.index + 1], typeof this.turnCallback == "function" && this.turnCallback(this.start.lon, this.start.lat, this.index), this.lonIncrease = (this.end.lon - this.start.lon) / this.frames, this.latIncrease = (this.end.lat - this.start.lat) / this.frames, this.index++, this.curFrames = 0;
					this.timer = setInterval(function() {
						e._move()
					}, this.second)
				}
			} else this.running = !1, typeof this.stopCallback == "function" && this.stopCallback(this.end.lon, this.end.lat)
		},
		stop: function() {
			this.running = !1, this.timer && clearInterval(this.timer)
		}
	});
	return {
		linear: t
	}
}), define("plugins/typhoon/Typhoon", ["require", "../../animation/animation"], function(e) {
	var t = e("../../animation/animation");
	return OpenLayers.Class({
		initialize: function(e, t) {
			if (!e || !t) return;
			this.box = t, this.forecasts = {}, this.actual = {}, this.windring = null, this.data = this.format(e), this.id = e.id, this.list = e.list, this.index = null, this.loadActual()
		},
		format: function(e) {
			var t, n, r, i, s, o, u, a, f, l = this.box.map;
			f = e.list;
			for (n = 0, r = f.length; n < r; n++) {
				s = f[n], a = l.tanslateLonLat(new OpenLayers.LonLat(s.lon, s.lat)), s._lon = a.lon, s._lat = a.lat;
				for (t in s.forecasts) {
					u = s.forecasts[t];
					for (j = 0, i = u.length; j < i; j++) o = u[j], a = l.tanslateLonLat(new OpenLayers.LonLat(o.lon, o.lat)), o._lon = a.lon, o._lat = a.lat
				}
			}
			return e
		},
		getLonlats: function(e) {
			var t, n, r, i = [];
			if (e) for (t = 0, n = e.length; t < n; t++) r = e[t], i.push({
				lon: r._lon,
				lat: r._lat
			});
			return i
		},
		loadActual: function() {
			var e = this.box,
				n = e.pathsLayer,
				r = e.pointLayer,
				i = this,
				s = this.id,
				o = this.data.name,
				u = this.list,
				a = this.getLonlats(u),
				f = a[0],
				l = this.actual.points = [],
				c = this.actual.path = n.add([f]);
			this.nameLabel = e.namesLayer.add(o, f.lon, f.lat), a && a.length > 1 ? this.animation = new t.linear(a, 2, .02, function(e, t) {
				n.redrawPath(c, e, t), i.moveWindring(e, t)
			}, function(e, t, s) {
				var o = u[s];
				n.redrawPath(c, e, t), l.push(r.add(o, i)), i.addWindring(o)
			}, function(e, t) {
				l.push(r.add(u[u.length - 1], i)), i.animation.destroy(), i.animation = null, i.changeTime()
			}) : (l.push(r.add(u[0], this)), this.addWindring(f), this.changeTime())
		},
		getIndex: function(e) {
			var t, n = this.list.length;
			if (!e) return n - 1;
			for (t = 0; t < n; t++) if (this.list[t].time == e) return t
		},
		changeTime: function(e) {
			var t, n, r, i = this.box.publishersFilter;
			for (t in this.forecasts) this.removeForecast(t);
			this.index = this.getIndex(e);
			if (i) {
				n = i.split(",");
				for (r = n.length - 1; r >= 0; r--) this.addForecast(n[r])
			}
			this.addWindring(this.list[this.index])
		},
		addForecast: function(e) {
			var t, n, r, i, s, o, u = [],
				a = this.box,
				f = a.pointLayer,
				l = a.pathsLayer,
				c = this.index;
			for (t = c; t >= 0; t--) {
				s = this.list[t], o = s && s.forecasts && s.forecasts[e];
				if (o) {
					this.forecasts[e] = {
						points: f.adds(o, this, !0),
						path: l.add(this.getLonlats(o).concat([{
							lon: s._lon,
							lat: s._lat
						}]), e)
					};
					break
				}
			}
		},
		removeForecast: function(e) {
			var t, n, r = this.box,
				i = this.forecasts[e];
			i && (r.pathsLayer.remove(i.path), r.pointLayer.remove(i.points), delete this.forecasts[e])
		},
		addWindring: function(e) {
			this.removeWindring(), this.windring = this.box.windringLayer.add(e._lon, e._lat, e.lv7, e.lv10, e.lv12)
		},
		removeWindring: function() {
			this.windring && (this.box.windringLayer.remove(this.windring), this.windring = null)
		},
		moveWindring: function(e, t) {
			this.windring && this.box.windringLayer.move(e, t, this.windring)
		},
		destroy: function() {
			this.animation && (this.animation.stop(), this.animation = null), this.removeWindring();
			for (var e in this.forecasts) this.removeForecast(e);
			this.forecasts = null, this.box.pathsLayer.remove(this.actual.path), this.box.pointLayer.remove(this.actual.points), this.actual = null, this.box.namesLayer.remove(this.nameLabel), this.box = null, this.data = null
		}
	})
}), define("plugins/typhoon", ["require", "./typhoon/warningLineLayer", "./typhoon/PathsLayer", "./typhoon/NamesLayer", "./typhoon/PointsLayer", "./typhoon/WindRingLayer", "./typhoon/xml2json", "./typhoon/Typhoon"], function(e) {
	var t = e("./typhoon/warningLineLayer"),
		r = e("./typhoon/PathsLayer"),
		s = e("./typhoon/NamesLayer"),
		o = e("./typhoon/PointsLayer"),
		u = e("./typhoon/WindRingLayer"),
		a = e("./typhoon/xml2json"),
		f = e("./typhoon/Typhoon"),
		c = {
			BABJ: {
				alias: "中央台预报",
				style: "#aa1fd3"
			},
			HZHZ: {
				alias: "省台预报",
				style: "#ffae00"
			},
			RJTD: {
				alias: "东京预报",
				style: "#007130"
			},
			RKSL: {
				alias: "韩国预报",
				style: "#00fcff"
			},
			RCTP: {
				alias: "台北预报",
				style: "#0052f0"
			},
			VHHH: {
				alias: "香港预报",
				style: "#0062FE"
			},
			ZJRS: {
				alias: "省台预报",
				style: "#00FF03"
			}
		},
		h = [{
			name: "热带低压",
			style: "#00FF03"
		}, {
			name: "热带风暴",
			style: "#0062FE"
		}, {
			name: "强热带风暴",
			style: "#FDFA00"
		}, {
			name: "台风",
			style: "#FDAC03"
		}, {
			name: "强台风",
			style: "#F072F6"
		}, {
			name: "超强台风",
			style: "#FD0002"
		}],
		p = OpenLayers.Class({
			xml2json: a,
			initialize: function(e, n) {
				if (!n) return;
				this.gis = n, this.map = n.map, e = e || {}, this.windPowers = e.windPowers || h, this.publishers = e.publishers || c, this.publishersFilter = this.setPublishers(e.publishersFilter), this.warningLineLayer = new t, this.namesLayer = new s(this, e.namesLayerClas), this.windringLayer = new u(this, e.windRingStyle), this.pathsLayer = new r(this), this.pointLayer = new o(this), this.map.addLayers([this.warningLineLayer, this.windringLayer, this.pathsLayer, this.pointLayer]), this.typhoons = []
			},
			getTyphoon: function(e) {
				var t, n, r;
				for (t = 0, n = this.typhoons.length; t < n; t++) {
					r = this.typhoons[t];
					if (r.id === e) return r
				}
			},
			removeTyphoon: function(e) {
				for (i = 0, l = this.typhoons.length; i < l; i++) n = this.typhoons[i], n.id === e && (n.destroy(), this.typhoons.splice(i, 1));
				return this.typhoons
			},
			clearTyphoon: function() {
				for (i = 0, l = this.typhoons.length; i < l; i++) this.typhoons[i].destroy();
				this.typhoons.length = 0
			},
			addTyphoon: function(e) {
				if (!e || this.getTyphoon(e.id)) return;
				this.typhoons.push(new f(e, this))
			},
			setPublishers: function(e) {
				var t = e && e.split(","),
					n = [],
					r, i, s, o;
				if (t && t.length) for (r = 0, i = t.length; r < i; r++) s = t[r], this.publishers[s] && n.push(s);
				else for (o in this.publishers) n.push(o);
				return n.join(",")
			},
			addPopup: function(e, t, n, r) {
				var i = n.lon,
					s = n.lat,
					o = n.time,
					u = n.lv7,
					a = n.lv10,
					f = n.lv12,
					l = "<div class='typopup'><span>" + e + "</span>" + "<ul>" + "<li>过去时间：" + o.substr(0, 2) + "月" + o.substr(2, 2) + "日" + o.substr(4, 2) + "时</li>" + "<li>中心位置：" + i + "°E," + s + "°N</li>" + "<li>最大风力：" + n.windPower + "级</li>" + (n.windVelocity ? "<li>最大风速：" + n.windVelocity + "m/s</li>" : "") + "<li>中心气压：" + n.pressure + "hPa</li>" + "<li>移动速度：</li>" + "<li>移动方向：</li>" + (u ? "<li>7级风圈半径：" + u + "km</li>" : "") + (a ? "<li>10级风圈半径：" + a + "km</li>" : "") + (f ? "<li>12级风圈半径：" + f + "km</li>" : "") + "</ul>" + "</div>";
				this.popup ? (this.popup.setContentHTML(l), this.popup.setLonlat(i, s, 0, -15), this.popup.show()) : this.popup = this.gis.addPopup(i, s, l, 0, -15, !0)
			},
			hidePopup: function() {
				this.popup && this.popup.hide()
			},
			changeTime: function(e, t) {
				var n = this.getTyphoon(e);
				n && (n.changeTime(t), this.addPopup(n.data.name, n.data.type, n.list[n.getIndex(t)], !1))
			},
			showPubliserLengend: function(e, t) {
				if (!this.publiserLegend) {
					var n, r, i = [];
					for (n in this.publishers) r = this.publishers[n], i.push({
						style: r.style,
						alias: r.alias || r.name
					});
					this.publiserLegend = this.gis.addControl("simpleLegend", e, t, {
						legend: i,
						lType: "line",
						name: "预报机构"
					})
				}
			},
			showLevelLengend: function(e, t) {
				if (!this.levelLegend) {
					var n = [],
						r = this.windPowers;
					for (var i = 0; i < r.length; i++) n.push({
						style: r[i].style,
						alias: h[i].name || ""
					});
					this.levelLegend = this.gis.addControl("simpleLegend", e, t, {
						legend: n,
						lType: "point",
						name: "台风图例"
					})
				}
			},
			destroy: function() {
				this.clearTyphoon(), this.typhoons = null, this.popup && (this.gis.removePopup(this.popup), this.popup = null)
			},
			CLASS_NAME: "GI.Typhoon"
		});
	return function(e) {
		return new p(e, this)
	}
}), define("plugins/resources", ["require", "./line", "./boundary", "./contours", "./polygonfill", "./dtext", "./grid", "./rail", "./tile", "./typhoon"], function(e) {
	return {
		drawLine: e("./line"),
		drawBoundary: e("./boundary"),
		drawContours: e("./contours"),
		drawPolygonFill: e("./polygonfill"),
		drawText: e("./dtext"),
		drawGrid: e("./grid"),
		drawRail: e("./rail"),
		addTileLayer: e("./tile"),
		drawTyphoon: e("./typhoon")
	}
}), define("gi-gis", ["require", "./core", "./plugins/resources", "./control/resources", "./layer/resources"], function(e) {
	function s(e, n, r) {
		return new t(e, n, r)
	}
	var t = e("./core"),
		n = e("./plugins/resources"),
		r = e("./control/resources"),
		i = e("./layer/resources");
	return OpenLayers.Util.extend(t.prototype, n), s.version = "1.2.2_alpha", s.loadPlugins = function(e) {
		if (typeof e == "object") for (var n in e) t.prototype[n] ? OpenLayers.Console.log("插件名(" + n + ")重名!") : t.prototype[n] = plugin
	}, s.loadControls = function(e) {
		if (typeof e == "object") for (var t in e) r[t] ? OpenLayers.Console.log("控件名(" + t + ")重名!") : r[t] = e[t]
	}, s.loadLayers = function(e) {
		if (typeof e == "object") for (var t in e) i[t] ? OpenLayers.Console.log("底图组(" + t + ")重名!") : i[t] = e[t]
	}, s
});