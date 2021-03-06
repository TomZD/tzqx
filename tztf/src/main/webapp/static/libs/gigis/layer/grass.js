define(function(require) {
	var root = 'http://172.16.10.126:8080';
	var host = window.location.host;
	if(host.indexOf('122.224.20.202:8090')!=-1){
		root = 'http://122.224.20.202:8090';
	}	
	var list = [
		{
			name: "traffic",
			alias: "交通图",
			url: root+"/maps/GoogleMap_${z}/${x}_${y}.png",
			numZoomLevels: 16,
			group: "grass-traffic",
			isBaseLayer: true
		}, //交通
		{
			name: "terrain",
			alias: "地形图",
			url: root+"/maps/GoogleTer_${z}/${x}_${y}.jpg",
			group: "grass-terrain",
			numZoomLevels: 16,
			isBaseLayer: true
		}, //地形
		{
			name: "satellite",
			alias: "卫星图",
			url: root+"/maps/GoogleSat_${z}/${x}_${y}.png",
			group: "grass-satellite",
			numZoomLevels: 16,
			isBaseLayer: true
		}, //卫星
		{
			name: "road",
			alias: "路网",
			url: root+"/maps/GoogleHyb_${z}/${x}_${y}.png",
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
		} //白板
	];
	return function(wrapDateLine) {
		var layers = [];
		for (var i = 0, l = list.length; i < l; i++) {
			var options = list[i];
			layers.push(
				new OpenLayers.Layer.XYZ(options.name, options.url, {
					wrapDateLine:wrapDateLine,
					sphericalMercator: true,
					numZoomLevels: options.numZoomLevels,
					group: options.group,
					isBaseLayer: options.isBaseLayer || false,
					getURL: options.getURL,
					alias: options.alias,
					visibility:false
				})
			);
		}
		return layers
	}
});