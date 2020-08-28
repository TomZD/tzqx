define(function (require) {
    var list = [
		{
		    name: "traffic",
		    alias: "交通图",
		    url: "http://t6.tianditu.cn/vec_w/wmts?SERVICE=WMTS&REQUEST=GetTile&VERSION=1.0.0&LAYER=vec&STYLE=default&TILEMATRIXSET=w&FORMAT=tiles&TILECOL=${x}&TILEROW=${y}&TILEMATRIX=${z}",
		    numZoomLevels: 18,
		    group: "traffic",
		    isBaseLayer: true
		}, //交通
        {
            name: "traffic-note",
            alias: "交通注记",
            url: "http://t1.tianditu.cn/cva_w/wmts?SERVICE=WMTS&REQUEST=GetTile&VERSION=1.0.0&LAYER=cva&STYLE=default&TILEMATRIXSET=w&FORMAT=tiles&TILECOL=${x}&TILEROW=${y}&TILEMATRIX=${z}",
            numZoomLevels: 18,
            group: "traffic",
            isBaseLayer: false
        },
        {
            name: "terrain",
            alias: "地形图",
            url: "http://t0.tianditu.cn/ter_w/wmts?SERVICE=WMTS&REQUEST=GetTile&VERSION=1.0.0&LAYER=ter&STYLE=default&TILEMATRIXSET=w&FORMAT=tiles&TILECOL=${x}&TILEROW=${y}&TILEMATRIX=${z}",
            numZoomLevels: 18,
            group: "terrin",
            isBaseLayer: true
        }, //交通
        {
            name: "terrain-note",
            alias: "地形注记",
            url: "http://t7.tianditu.cn/cta_w/wmts?SERVICE=WMTS&REQUEST=GetTile&VERSION=1.0.0&LAYER=cta&STYLE=default&TILEMATRIXSET=w&FORMAT=tiles&TILECOL=${x}&TILEROW=${y}&TILEMATRIX=${z}",
            numZoomLevels: 18,
            group: "terrain",
            isBaseLayer: false
        },
        {
            name: "satellite",
            alias: "卫星图",
            url: "http://t3.tianditu.cn/img_w/wmts?SERVICE=WMTS&REQUEST=GetTile&VERSION=1.0.0&LAYER=img&STYLE=default&TILEMATRIXSET=w&FORMAT=tiles&TILECOL=${x}&TILEROW=${y}&TILEMATRIX=${z}",
            numZoomLevels: 18,
            group: "satellite",
            isBaseLayer: true
        }, //交通
        {
            name: "satellite-note",
            alias: "卫星注记",
            url: "http://t3.tianditu.cn/cia_w/wmts?SERVICE=WMTS&REQUEST=GetTile&VERSION=1.0.0&LAYER=cia&STYLE=default&TILEMATRIXSET=w&FORMAT=tiles&TILECOL=${x}&TILEROW=${y}&TILEMATRIX=${z}",
            numZoomLevels: 18,
            group: "satellite",
            isBaseLayer: false
        },
		
    ];
    return function (wrapDateLine) {
        var layers = [];
        for (var i = 0, l = list.length; i < l; i++) {
            var options = list[i];
            layers.push(
				new OpenLayers.Layer.XYZ(options.name, options.url, {
				    wrapDateLine: wrapDateLine,
				    sphericalMercator: true,
				    numZoomLevels: options.numZoomLevels,
				    group: options.group,
				    isBaseLayer: options.isBaseLayer || false,
				    getURL: options.getURL,
				    alias: options.alias,
				    visibility: false
				})
			);
        }
        return layers
    }
});