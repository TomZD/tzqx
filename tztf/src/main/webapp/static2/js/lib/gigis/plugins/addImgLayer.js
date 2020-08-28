define(function(require) {
	var LayerImg = OpenLayers.Class(OpenLayers.Layer, {
		isBaseLayer: false,
		url: null,
		extent: null,
		size: null,
		aspectRatio: null,
		initialize: function(name, url, extent, options) {
			this.url = url;
			this.extent = extent;
			this.maxExtent = extent;
			this.size = new OpenLayers.Size(0, 0);
			this.opacity = options.opacity;
			OpenLayers.Layer.prototype.initialize.apply(this, [name, options]);

			this.aspectRatio = (this.extent.getHeight() / this.size.h) /
				(this.extent.getWidth() / this.size.w);
		},
		setMap: function(map) {
			if (this.options.maxResolution == null) {
				this.options.maxResolution = this.aspectRatio *
					this.extent.getWidth() /
					this.size.w;
			}
			OpenLayers.Layer.prototype.setMap.apply(this, arguments);
		},
		moveTo: function(bounds, zoomChanged, dragging) {
			OpenLayers.Layer.prototype.moveTo.apply(this, arguments);

			var firstRendering = (this.tile == null);

			if (zoomChanged || firstRendering) {
				this.draw();
			}
		},
		draw: function() {
			if (!this.imgDiv) {
				this.imgDiv = document.createElement("div");
				this.img = document.createElement("img");
				this.img.src = this.url;
				this.img.style.opacity = this.opacity;
				this.imgDiv.appendChild(this.img);
				this.div.innerHtml = "";
				this.div.appendChild(this.imgDiv);
			}
			this.setTileSize();
			this.renderSize();
			this.renderPosition();
		},
		setTileSize: function() {
			var tileWidth = this.extent.getWidth() / this.map.getResolution();
			var tileHeight = this.extent.getHeight() / this.map.getResolution();
			this.tileSize = new OpenLayers.Size(tileWidth, tileHeight);
		},
		renderSize: function() {
			var style = this.img.style;
			var size = this.getImageSize(this.tileSize);
			style.width = Math.round(size.w) + "px";
			style.height = Math.round(size.h) + "px";
		},
		renderPosition: function() {
			this.position = this.map.getLayerPxFromLonLat({
				lon: this.extent.left,
				lat: this.extent.top
			});
			var style = this.div.style,
				size = this.getImageSize(this.tileSize);
			style.left = this.position.x + "px";
			style.top = this.position.y + "px";
			style.width = Math.round(size.w) + "px";
			style.height = Math.round(size.h) + "px";
		},
		hide: function() {
			this.imgDiv && (this.imgDiv.style.display = "none");
		},
		show: function() {
			this.imgDiv && (this.imgDiv.style.display = "block");
			this.draw();
		},
		removeImg: function() {
			this.div.innerHtml = "";
			this.imgDiv = null;
		},
		CLASS_NAME: "Gi.Pligins.Image"
	});

	//imgUrl图片路径 pos位置 opacity 透明度
	return function(imgUrl, pos, opacity) {

		if (!pos) return;
		var left = pos.left || 0;
		var bottom = pos.bottom || 0;
		var right = pos.right || 0;
		var top = pos.top || 0;

		var l1 = this.map.tanslateLonLat(new OpenLayers.LonLat(left, bottom));
		var l2 = this.map.tanslateLonLat(new OpenLayers.LonLat(right, top));
		var bounds = new OpenLayers.Bounds(l1.lon, l1.lat, l2.lon, l2.lat);
		var graphicLayer = new LayerImg('City Lights',
			imgUrl,
			bounds, {
				opacity: opacity || 1
			});
		this.addLayer(graphicLayer);
		return graphicLayer;
	}
});