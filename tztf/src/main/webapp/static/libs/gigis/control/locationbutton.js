define(function(require) {
    //自动定位
    return OpenLayers.Class(OpenLayers.Control, {
        btn: null,
        draw: function(px) {
            OpenLayers.Control.prototype.draw.apply(this);
            OpenLayers.Element.addClass(this.div, "olButton");
            var btn = this.btn = document.createElement("span");
            btn.className = "olButton olButton_position";
            this.div.appendChild(btn);
            this.map.events.register("buttonclick", this, this.onButtonClick);
            return this.div;
        },
        onButtonClick: function(evt) {
            var button = evt.buttonElement;
            if (button === this.btn && !this.status) {
                this.changeStatus();
                this.getPosition(this.success, this.error, this); //获取地理信息
            }
        },
        changeStatus: function() {
            if (this.status) {
                this.status = null;
                OpenLayers.Element.removeClass(this.btn, "olButton_position_loading");
            } else {
                this.status = "loading";
                OpenLayers.Element.addClass(this.btn, "olButton_position_loading");
            }
        },
        success: function(position) {
            var map = this.map;
            var dPro = this.map.displayProjection, //输出投影
                bPro = this.map.getProjection(); //底图投影
            this.changeStatus();
            var coords = position.coords;
            map.setCenter(
                new OpenLayers.LonLat(coords.longitude, coords.latitude).transform(dPro, bPro),
                this.zoom
            );
        },
        error: function(error) {
            this.changeStatus();
            var txt;
            switch (error.code) {
                case error.PERMISSION_DENIED:
                    txt = "用户拒绝了Geolocation的请求"
                    break;
                case error.POSITION_UNAVAILABLE:
                    txt = "本地信息无法获得"
                    break;
                case error.TIMEOUT:
                    txt = "请求超时"
                    break;
                case error.UNKNOWN_ERROR:
                    txt = "未知原因发生"
                    break;
            }
            alert(txt)
        },
        getPosition: function(success, error, scope) {
            if (navigator.geolocation) {
                navigator.geolocation.getCurrentPosition(
                    function(position) {
                        typeof success === "function" && success.call(scope, position);
                    },
                    function(e) {
                        var txt;
                        switch (e.code) {
                            case e.PERMISSION_DENIED:
                                txt = "用户拒绝了Geolocation的请求"
                                break;
                            case e.POSITION_UNAVAILABLE:
                                txt = "本地信息无法获得"
                                break;
                            case e.TIMEOUT:
                                txt = "请求超时"
                                break;
                            case e.UNKNOWN_ERROR:
                                txt = "未知原因发生"
                                break;
                        }
                        typeof error === "function" && error.call(scope, txt);
                    }
                );
            }
        },
        CLASS_NAME: "Gi.Location"
    });
});