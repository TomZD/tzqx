define(function(require) {
	//缩放平移工
	return OpenLayers.Class(OpenLayers.Control.PanZoomBar, {
		moveZoomBar: function() {
			var newTop =
				((this.map.getNumZoomLevels() - 1) - this.map.getZoom()) *
				11 + this.startTop + 1;
			this.slider.style.top = newTop + "px";
		},
		CLASS_NAME: "Gi.GiZoom"
	});
});