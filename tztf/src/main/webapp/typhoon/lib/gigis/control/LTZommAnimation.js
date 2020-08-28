define(function(require) {
return  OpenLayers.Class(OpenLayers.Control.Navigation, {
    size: null,
    offset: null,
    uricon: null,
    ulicon: null,
    bricon: null,
    blicon: null,
    // 拖动的滑动效果必须使enableKinetic为true
    dragPanOptions: { enableKinetic: true },
    isCenterMouseWheel: true,
    initialize: function (options) {
        OpenLayers.Control.Navigation.prototype.initialize.apply(this, arguments);
        this.size = new OpenLayers.Size(10, 6);
        this.offset = new OpenLayers.Pixel(-(this.size.w / 2), -(this.size.h / 2));
        this.uricon = new OpenLayers.Icon("img/ur.png", this.size, this.offset);
        this.ulicon = new OpenLayers.Icon("img/ul.png", this.size, this.offset);
        this.bricon = new OpenLayers.Icon("img/br.png", this.size, this.offset);
        this.blicon = new OpenLayers.Icon("img/bl.png", this.size, this.offset);
    },

    wheelUp: function (evt) {
        var newZoom = this.map.getZoom();
        this.map.moveTo(newZoom);
        return false;
        this.wheelChange(evt, 1);
        
    },

    wheelDown: function (evt) {

        var newZoom = this.map.getZoom();
        this.map.moveTo(newZoom);

        return false;
    
        this.wheelChange(evt, -1);
    },
    wheelChange: function (evt, deltaZ) {
        var currentZoom = this.map.getZoom();
        var newZoom = this.map.getZoom() + Math.round(deltaZ);
        newZoom = Math.max(newZoom, 0);
        newZoom = Math.min(newZoom, this.map.getNumZoomLevels());
        if (newZoom === currentZoom) {
            return;
        }
        var size = this.map.getSize();
        var deltaX = size.w / 2 - evt.xy.x;
        var deltaY = evt.xy.y - size.h / 2;
        var newRes = this.map.baseLayer.getResolutionForZoom(newZoom);
        var zoomPoint = this.map.getLonLatFromPixel(evt.xy);
        var newCenter = new OpenLayers.LonLat(
                            zoomPoint.lon + deltaX * newRes,
                            zoomPoint.lat + deltaY * newRes);
        if (this.isCenterMouseWheel)
            this.map.setCenter(newCenter, newZoom);
        else
            this.map.setCenter(this.map.getCenter(), this.map.getZoom() + Math.round(deltaZ));

    },
    cusClick:function(){
        console.log("cusClick");
    }
});
});