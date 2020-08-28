define(function(require) {
	return {
		drawLine:require("./line"),
		drawBoundary: require("./boundary"),
		drawContours: require("./contours"),
		drawGrid: require("./grid"),
		drawRail: require("./rail"),
		addTileLayer: require("./tile"),
		drawTyphoon: require("./typhoon"),
        drawTools:require("./drawTools"),
		markerDrag:require("./markerDrag")
	};
});