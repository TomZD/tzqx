/**
 * @require "libs/jquery/jquery.js"
 * 计算布局
 */
var resizeLayout = (function(window, $) {
    var elMain = $(".g-main"),
        callbacks = [],
        h,
        resize = function() {
            h = elMain.height();
            $.each(callbacks, function(i, n) {
                n(h);
            });
        };

    $(window).bind("resize", resize);
    resize();

    return function(callback) {
        if (typeof callback == "function") {
            callbacks.push(callback);
            callback(h);
        }
    };
}(window, $));
