define(function(require) {
	var root = 'http://10.135.144.124';
	var host = window.location.host;
	if(host.indexOf('122.224.20.202:8090')!=-1){
		root = 'http://122.224.20.202:8090';
	} else if(host.indexOf('localhost')!=-1) {
		root = 'http://10.135.144.124';
	} else if(host.indexOf('192.168')!=-1) {
		root = 'http://10.135.144.124';
	}
	var list = [
	    {
			name: "traffic",
			alias: "交通图",
			url: root+"/GoogleMap/Google_Maps/GoogleMap_${z}/${x}_${y}.jpg",
			numZoomLevels: 16,
			group: "grass-traffic",
			isBaseLayer: true
		}, //交通
		{
			name: "terrain",
			alias: "地形图",
			url: root+"/GoogleMap/Google_Terrain/GoogleTer_${z}/${x}_${y}.jpg",
			group: "grass-terrain",
			numZoomLevels: 16,
			isBaseLayer: true
		}, //地形
		{
			name: "satellite",
			alias: "卫星图",
			url: root+"/GoogleMap/Google_Satellite/GoogleSat_${z}/${x}_${y}.jpg",
			group: "grass-satellite",
			numZoomLevels: 16,
			isBaseLayer: true
		}, //卫星
		{
			name: "road",
			alias: "路网",
			url: root+"/GoogleMap/Google_Hybrid/GoogleHyb_${z}/${x}_${y}.jpg",
			group: "grass-satellite",
			numZoomLevels: 16
		}, //路网（非底图）
		{
			name: "board",
			alias: "白板图",
			url: "http://10.135.30.12/GetMaps/getmaps.ashx?type=zjqxmap_bound&z=${z}&x=${x}&y=${y}",
			group: "grass-white",
			numZoomLevels: 14,
			isBaseLayer: true
		}, //白板
        {
        name: "sea",
        alias: "海图",
        numZoomLevels: 14,
        url: " http://m13.shipxy.com/tile.c?l=Na&m=o&x=${x}&y=${y}&z=${z}",
        group: "grass-ocean",
        isBaseLayer: 0
    },
    {
        name: "sea",
        alias: "船分布图",
        numZoomLevels: 13,
        url: " http://r2.shipxy.com/r2/sp.dll?cmd=112&scode=11111111&x=${x}&y=${y}&z=${z}",
        group: "grass-ocean",
        // isBaseLayer: !0
    }
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
                layers.push(item);
            }
        }
        return layers
    }
});
