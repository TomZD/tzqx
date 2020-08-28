define(function() {
	return function(px) {
		if ((px != null) && (this.div != null)) {
			var style = this.div.style;
			var x = px.x;
			var y = px.y;
			if (x >= 0) {
				style.left = x + "px";
				style.right = "auto"
			} else {
				style.right = -x + "px";
				style.left = "auto"
			}
			if (y >= 0) {
				style.top = y + "px";
				style.bottom = "auto"
			} else {
				style.bottom = -y + "px";
				style.top = "auto"
			}
		}
	}
});