define(function() {
	return OpenLayers.Class(OpenLayers.Marker, {
		getLonlat: function() {
			return {
				lon: this._lon,
				lat: this._lat
			}
		},
		getOffset: function(position) {
			var size = this.icon.size;
			var height = size.h;
			var width = size.w;
			var x = -width / 2,
				y = -height / 2;
			if (typeof position == "string") {
				var arr = position.split("");
				var v = arr[0]; //垂直方向 top bottom middle
				if (v == "t") {
					y = 0;
				} else if (v == "b") {
					y = -height;
				}
				var h = arr[1]; //水平方向 left right center
				if (h == "l") {
					x = 0;
				} else if (h == "r") {
					x = -width;
				}
			}

			var offset = this.icon.offset.clone();
			offset.x -= x;
			offset.y -= y;
			return offset;
		},
		setText: function(text) {
			if (typeof text == "string" || typeof text == "number") {
				if(!this.text){
					this.text = OpenLayers.Util.createDiv(null, {
						x: 0,
						y: 0
					}, null, null, "absolute", null,null);
					this.icon.imageDiv.appendChild(this.text);
				}
				this.text.innerHTML = text;
			}
		},
		setContent: function(content) {
			this.content = content;
		},
		hide: function() {
			this.display(false);
		},
		show: function() {
			this.display(true);
		},
		hideText: function() {
			this.text && (this.text.style.display = "none");
		},
		showText: function() {
			this.text && (this.text.style.display = "");
		}
	});
});