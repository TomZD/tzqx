define(function(require) {
	return OpenLayers.Class(OpenLayers.Control, {
		content: null,
		textClass: "olGiText",
		initialize: function(option) {
			if (typeof option == "object" && typeof option.content == "string") {
				this.content = option.content;
				this.textClass = option.curClass;
			}
		},
		draw: function(px) {
			OpenLayers.Control.prototype.draw.apply(this, arguments);
			var html = "<div class='" + this.textClass + "'>" + this.content + "</div>"
			this.div.innerHTML=html;
			return this.div;
		},
		resetContent: function(content) {
			this.content = content;
			this.draw();
		},
		CLASS_NAME: "Gi.textContainer"
	});
})