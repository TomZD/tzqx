define(function(require) {
	return {
		scaleLine: require("./scaleLine"),
		locationButton: require("./locationbutton"),
		panel: require("./panel"),
		movebar: require("./movebar"),
		zoom: require("./zoom"),
		overview: require("./overviewmap"),
		mousePosition: OpenLayers.Control.MousePosition,
		text: require("./textContainer"),
		legend: require("./legend"),
		layerManagement: require("./layerManagement"),
		simpleLegend: require("./simpleLegend")
	};
});