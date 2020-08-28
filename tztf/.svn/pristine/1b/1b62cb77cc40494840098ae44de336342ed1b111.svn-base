define(function() {
	return OpenLayers.Class(OpenLayers.Popup, {
		autoSize: true,
		panMapIfOutOfView: true,
		initialize: function(id, lonlat, contentSize, offset, contentHTML, closeBox, closeBoxCallback) {
			this.offset = offset;
			OpenLayers.Popup.prototype.initialize.apply(this, [id, lonlat, contentSize, contentHTML, closeBox, closeBoxCallback])
		},
		destroy: function() {
			this.div.removeChild(this.arrow);
			this.arrow = null;
			OpenLayers.Popup.prototype.destroy.apply(this);
		},
		draw: function(px) {
			//三角形箭头
			this.arrow = OpenLayers.Util.createDiv(null, null, null, null, "static");
			this.arrow.className = "popup_tri";
			this.arrow.innerHTML = "<i class='popup_tri_a'></i><i class='popup_tri_b'></i>"
			this.div.appendChild(this.arrow);

			return OpenLayers.Popup.prototype.draw.apply(this, [px]);
		},
		//在原基础上，删除对close的宽度计算
		setSize: function(contentSize) {
			this.size = contentSize.clone();

			var contentDivPadding = this.getContentDivPadding();
			var wPadding = contentDivPadding.left + contentDivPadding.right;
			var hPadding = contentDivPadding.top + contentDivPadding.bottom;

			this.fixPadding();
			wPadding += this.padding.left + this.padding.right;
			hPadding += this.padding.top + this.padding.bottom;

			this.size.w += wPadding;
			this.size.h += hPadding;

			if (OpenLayers.BROWSER_NAME == "msie") {
				this.contentSize.w +=
					contentDivPadding.left + contentDivPadding.right;
				this.contentSize.h +=
					contentDivPadding.bottom + contentDivPadding.top;
			}

			if (this.div != null) {
				this.div.style.width = this.size.w + "px";
				this.div.style.height = this.size.h + "px";
			}
			if (this.contentDiv != null) {
				this.contentDiv.style.width = contentSize.w + "px";
				this.contentDiv.style.height = contentSize.h + "px";
			}
		},
		moveTo: function(px) {
			if (this.size) {
				px.x -= this.size.w / 2;
				px.y -= this.size.h;

				px.y -= this.arrow.offsetHeight;

				if (this.offset) {
					px.x += this.offset.x;
					px.y += this.offset.y;
				}
				OpenLayers.Popup.prototype.moveTo.apply(this, [px]);
			}
		},
		setLonlat: function(lon, lat, offsetX, offsetY) {
			this.lonlat = this.map.tanslateLonLat(new OpenLayers.LonLat(lon, lat));
			this.setOffset(offsetX, offsetY);
			this.updatePosition();
		},
		setOffset: function(offsetX, offsetY) {
			if (this.offset) {
				offsetX != null && (this.offset.x = offsetX);
				offsetY != null && (this.offset.y = offsetY);
			} else {
				this.offset = new OpenLayers.Pixel(offsetX || 0, offsetY || 0)
			}
		},
		CLASS_NAME: "Gi.Popup"
	});
});