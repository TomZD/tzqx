//交通指数
define(function (require, exports, module){
    //构造函数
    function jtzs(gis) {
        this.gis = gis;
    }

    //获取对应的图片
    // jtzs.prototype.getImgae = function(){
    //     var view =this.gis.map.getExtent();
    //     var lonlat1 = new OpenLayers.LonLat(view.left,view.bottom);
    //     var lonlat2 = new OpenLayers.LonLat(view.right,view.top);
    // }

    //添加交通指数图层
    jtzs.prototype.addJtzs=function(){
        this.layerControl();
        var view =this.gis.map.getExtent();
        var lonlat1 = new OpenLayers.LonLat(view.left,view.bottom);
        var lonlat2 = new OpenLayers.LonLat(view.right,view.top);
        lonlat1 = this.gis.map.tanslateLonLat(lonlat1, true);
        lonlat2 = this.gis.map.tanslateLonLat(lonlat2, true);
        var area = lonlat1.lon+","+ lonlat1.lat+","+lonlat2.lon+","+lonlat2.lat;
        var url = '/jtzs?lonlat='+area;
        var zoom = this.gis.map.getZoom();
        var ImgW = (this.gis.map.size.w) / Math.pow(2,zoom);
        var ImgH = (this.gis.map.size.h) / Math.pow(2,zoom);
        //加载图片
        var graphic = new OpenLayers.Layer.Image(
            '道路实况',
            url,
            new OpenLayers.Bounds( new OpenLayers.LonLat(view.left,view.bottom).lon, new OpenLayers.LonLat(view.left,view.bottom).lat,new OpenLayers.LonLat(view.right,view.top).lon,new OpenLayers.LonLat(view.right,view.top).lat),
            new OpenLayers.Size(ImgW, ImgH),
            {
                sphericalMercator: true,
                numZoomLevels:19,
                isBaseLayer: false,
                projection: "EPSG:900913",
            }
        );
        return graphic;
    };
    //图层控制
    jtzs.prototype.layerControl=function(){
       var layers = this.gis.map.layers;
       for(var i=0;i<layers.length;i++){
            if(layers[i].name != "道路实况"){
                layers[i].setVisibility(false)
            }
            if(layers[i].name == "道路实况"){
                layers[i].destroy()
            }
       }
    };
    return function(gis) {
        var layer =  new jtzs(gis).addJtzs();
        gis.map.setBaseLayer(layer);//设置图层为基层
        gis.map.addLayer(layer);
        return layer;
    }
})