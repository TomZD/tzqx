define(function(require) {
	return OpenLayers.Control.MouseLonLat = OpenLayers.Class(OpenLayers.Control, {
		autoActivate: true,
		numDigits: 5,
		granularity: 10,
		lastXy: null,
		displayProjection: null,
		destroy: function() {
			this.deactivate();
			OpenLayers.Control.prototype.destroy.apply(this, arguments);
		},
		activate: function() {
			if (OpenLayers.Control.prototype.activate.apply(this, arguments)) {
				this.map.events.register(this.type, this, this.getLonLat);
				return true;
			} else {
				return false;
			}
		},
		deactivate: function() {
			if (OpenLayers.Control.prototype.deactivate.apply(this, arguments)) {
				this.map.events.unregister(this.type, this, this.getLonLat);
				this.type=null;
				this.listener = null;
				return true;
			} else {
				return false;
			}
		},
		getLonLat: function(evt) {
			var lonLat;
			if (evt == null) {
				return;
			} else {
				if (this.lastXy == null ||
					Math.abs(evt.xy.x - this.lastXy.x) > this.granularity ||
					Math.abs(evt.xy.y - this.lastXy.y) > this.granularity) {
					this.lastXy = evt.xy;
					return;
				}

				lonLat = this.map.getLonLatFromPixel(evt.xy);
				if (!lonLat) {
					// map has not yet been properly initialized
					return;
				}
				if (this.displayProjection) {
					lonLat.transform(this.map.getProjectionObject(),
						this.displayProjection);
				}
				this.lastXy = evt.xy;
			}
			var digits = parseInt(this.numDigits);
			var lon = lonLat.lon.toFixed(digits);
			var lat = lonLat.lat.toFixed(digits);

			//回调
			typeof this.listener == "function" && this.listener(lon, lat);
		},
		CLASS_NAME: "OpenLayers.Control.MouseLonLat"
	});
});