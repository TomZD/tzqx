/**
 * 台风发布者图例
 */
define(function(require) {
	var Legend = require("../../control/legend");
	return OpenLayers.Class(Legend, {
		autoActivate: false,
		format: function() {
			this.colors = this.content;
		},
		draw: function(px) {
			Legend.prototype.draw.apply(this, [px]);
			OpenLayers.Element.addClass(this.div, this.displayClass + "-typhoonPublisher");
			return this.div;
		}
	});
});