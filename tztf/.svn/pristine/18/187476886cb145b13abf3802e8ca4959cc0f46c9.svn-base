define(function(require) {
    var list = [{
            name: "traffic",
            alias: "交通图",
            url: "http://webrd04.is.autonavi.com/appmaptile?lang=zh_cn&size=1&scale=1&style=7&x=${x}&y=${y}&z=${z}",
            //url: "http://webst03.is.autonavi.com/appmaptile?style=7&x=${x}&y=${y}&z=${z}",
            group: "amap-traffic",
            isBaseLayer: true
        }, //交通
        {
            name: "satellite",
            alias: "卫星图",
            //numZoomLevels: 18,
            url: "http://webst03.is.autonavi.com/appmaptile?style=6&x=${x}&y=${y}&z=${z}",
            group: "amap-satellite",
            isBaseLayer: true
        }, //卫星
        {
            name: "road",
            alias: "路网",
            url: "http://webst01.is.autonavi.com/appmaptile?style=8&x=${x}&y=${y}&z=${z}",
            group: "amap-road"
        } //路网（非底图）
    ];
    return function(wrapDateLine, name) {
        var layers = [];
        for (var i = 0, l = list.length; i < l; i++) {
            var options = list[i];
            var item = new OpenLayers.Layer.XYZ(options.name, options.url, {
                wrapDateLine: wrapDateLine,
                sphericalMercator: true,
                numZoomLevels: options.numZoomLevels,
                group: options.group,
                isBaseLayer: options.isBaseLayer || false,
                getURL: options.getURL,
                alias: options.alias,
                visibility: false
            });
            if (name && options.name == name) {
                layers.splice(0, 0, item);
            } else {
                layers.push(
                    //OpenLayers.Layer.XYZ默认投影"EPSG:900913",
                    item
                );
            }

        }
        return layers
    }
});
