define(function(require) {
    var verify = require("../base/verify");
    return function(url, name, zIndex, options) {
        if (!verify(url, "empty")) {
            name = verify(name, "empty") ? "tile layer" : name;
            options = typeof options == "object" ? options : {};
            options.sphericalMercator = true;
            options.isBaseLayer = false; //非底图
            options.zIndex = verify(zIndex, "number") ? parseInt(zIndex - 0) : null; //层级必须是整数

            var layer = new OpenLayers.Layer.XYZ(name, url, options);
            this.addLayer(layer)
            return layer;
        }
    };
});
