define(function(require) {
	return function(wrapDateLine) {
		var script = document.createElement("script");
		script.type = "text/javascript";
		script.src = "http://maps.google.com/maps/api/js?v=3&amp;sensor=false";
		document.getElementsByTagName("head")[0].appendChild(script);
		return [new OpenLayers.Layer.Google({
			wrapDateLine:wrapDateLine
		})];
	};
});