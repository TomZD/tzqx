define(function(require) {
	//缩放工具
	return OpenLayers.Class(OpenLayers.Control.Zoom, {
		draw: function(px) {
			var div = OpenLayers.Control.prototype.draw.apply(this),
				links = this.getOrCreateLinks(div),
				zoomIn = links.zoomIn,
				zoomOut = links.zoomOut,
				eventsInstance = this.map.events;

			if (zoomOut.parentNode !== div) {
				eventsInstance = this.events;
				eventsInstance.attachToElement(zoomOut.parentNode);
			}
			eventsInstance.register("buttonclick", this, this.onZoomClick);

			this.zoomInLink = zoomIn;
			this.zoomOutLink = zoomOut;
			this.moveTo(px);
			return div;
		}
	});
});