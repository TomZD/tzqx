define(function(require) {
	//比例尺
	return OpenLayers.Class(OpenLayers.Control, {
		maxWidth: 100,
		outUnits: "km",
		inUnits: "m",
		geodesic: false,
		getBarLen: function(maxLen) {
			var digits = parseInt(Math.log(maxLen) / Math.log(10));
			var pow10 = Math.pow(10, digits);
			return parseInt(maxLen / pow10) * pow10;
		},
		draw: function() {
			OpenLayers.Control.prototype.draw.apply(this, arguments);
			if (!this.eBox) {
				this.eBox = document.createElement("div");
				this.eBox.className = this.displayClass + "Top";
				var theLen = this.inUnits.length;
				this.div.appendChild(this.eBox);
				if ((this.outUnits == "") || (this.inUnits == "")) {
					this.eBox.style.visibility = "hidden";
				} else {
					this.eBox.style.visibility = "visible";
				}
			}
			this.map.events.register('moveend', this, this.update);
			this.update();
			return this.div;
		},
		update: function() {
			var res = this.map.getResolution();
			if (!res) {
				return;
			}

			var curMapUnits = this.map.getUnits();
			var inches = OpenLayers.INCHES_PER_UNIT;

			// convert maxWidth to map units
			var maxSizeData = this.maxWidth * res * inches[curMapUnits];
			var geodesicRatio = 1;
			if (this.geodesic === true) {
				var maxSizeGeodesic = (this.map.getGeodesicPixelSize().w || 0.000001) * this.maxWidth;
				var maxSizeKilometers = maxSizeData / inches["km"];
				geodesicRatio = maxSizeGeodesic / maxSizeKilometers;
				maxSizeData *= geodesicRatio;
			}

			// decide whether to use large or small scale units     
			var topUnits;
			var topUnitsFormat;
			if (maxSizeData > 100000) {
				topUnits = this.outUnits;
				topUnitsFormat = "公里";
			} else {
				topUnits = this.inUnits;
				topUnitsFormat = "米";
			}

			// and to map units units
			var topMax = maxSizeData / inches[topUnits];

			// now trim this down to useful block length
			var topRounded = this.getBarLen(topMax);

			// and back to display units
			topMax = topRounded / inches[curMapUnits] * inches[topUnits];

			// and to pixel units
			var topPx = topMax / res / geodesicRatio;

			// now set the pixel widths
			// and the values inside them
			if (this.eBox.style.visibility == "visible") {
				this.eBox.style.width = Math.round(topPx) + "px";
				this.eBox.innerHTML = topRounded + " " + topUnitsFormat;
			}
		},
		initialize: function() {
			OpenLayers.Control.prototype.initialize.apply(this, arguments);
		},
		CLASS_NAME: "Gi.ScaleLine"
	});
});