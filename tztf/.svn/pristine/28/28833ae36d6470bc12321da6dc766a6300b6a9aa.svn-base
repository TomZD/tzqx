define(function() {
    var gis, popup;

    /**
     * 初始化
     */
    var init = function(lon, lat, content) {
        if (content === "undefined") {
            content = "loading";
        }
        popup = gis.addPopup(lon || 0, lon || 0, content, 0, 0, true);
    };

    /**
     * 显示或隐藏弹框
     * @param  {Number}  lon     经度
     * @param  {Number}  lat     纬度
     * @param  {String}  content 显示内容
     */
    var show = function(lon, lat, content) {
        if (!popup) {
            init(lon, lat, content);
        } else {
            popup.setContentHTML(content);
            popup.setLonlat(lon, lat);
            popup.show();
        }
    };

    /**
     * 隐藏
     */
    var hide = function() {
        if (popup) {
            popup.hide();
        }
    };

    return function(g) {
        gis = g;
        return {
            show: show,
            hide: hide
        };
    };
});
