define(function(require) {
	//自定义面板
	return OpenLayers.Class(OpenLayers.Control, {
        contentHTML: null,
        initialize: function(content) {
            this.displayClass = this.CLASS_NAME.replace("OpenLayers.", "ol").replace(/\./g, "");
            OpenLayers.Util.extend(this, optin);
            this.events = new OpenLayers.Events(this);
            if (this.eventListeners instanceof Object) this.events.on(this.eventListeners);
            null == this.id && (this.id = OpenLayers.Util.createUniqueID(this.CLASS_NAME +
                "_"));
            this.contentHTML = content;
        },
        draw: function(px) {
            OpenLayers.Control.prototype.draw.apply(this, arguments);
            var container = document.createElement("div");
            OpenLayers.Element.addClass(this.div, "olGiPanel");
            container.innerHTML = this.contentHTML;
            this.div.appendChild(container);
            return this.div;
        },
        CLASS_NAME: "Gi.Panel"
    });
});