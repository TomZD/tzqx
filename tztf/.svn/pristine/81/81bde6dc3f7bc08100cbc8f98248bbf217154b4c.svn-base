/**
 * @require "libs/jquery/jquery.js"
 */
$.fn.tabs = function(options) {
    // 默认配置
    var defaults = {
        tabBtns: ".tab-btns > .btn", // 选项按钮
        tabUns: ".tab-uns > .tab-un", // 选项主体[切换的主体内容]
        event: "mouseover", // 默认选项按钮的切换事件
        btnClass: "on", // 按钮当前样式
        eventFn: null, //额外的触发事件
        speed: 3000, //切换速度
        auto: false, //自动切换
        autoBtnsHtml: false, //是否自动生成切换按钮
        hasNum: false //自动生成切换按钮是否带数字
    };

    options = $.extend(defaults, options);

    //生成切换按钮
    var getBtnsHtml = function(total, hasNum) {
        var i, html = "";
        if (total) {
            html += "<div class='btns'>";
            if (hasNum) {
                for (i = 0; i < total; i++) {
                    html += "<i>" + (i + 1) + "</i>";
                }
            } else {
                for (i = 0; i < total; i++) {
                    html += "<i></i>";
                }
            }
            html += "</div>";
        }
        return html;
    };

    return this.each(function() { // 对每个元素进行操作
        var that = $(this),
            timer, //自动切换定时器
            num = 0;

        var uns = that.find(options.tabUns);
        var maxNum = uns.length; // 最大数值
        var btnClass = options.btnClass;
        var eventFn = options.eventFn;
        var speed = options.speed;
        var btns;

        if (options.autoBtnsHtml) {
            btns = $(getBtnsHtml(maxNum, options.hasNum)).appendTo(that).find("i");
        } else {
            btns = that.find(options.tabBtns);
        }

        // 切换的核心代码
        var core = function(that, ev) {
            btns.removeClass(btnClass).eq(num).addClass(btnClass);
            uns.hide().eq(num).show();
            //额外的触发事件
            if (typeof eventFn == "function") {
                eventFn.call(btns[num]);
            }
        };

        // 选项按钮切换
        btns.bind(options.event, function(e) {
            num = btns.index(this);
            core();
        });

        // 定时执行
        var change = function() {
            num = ++num < maxNum ? num : 0;
            core();
        };
        // 开启自动切换
        if (maxNum > 1 && options.auto) {
            timer = setInterval(change, speed);
            btns.mouseenter(function() {
                clearInterval(timer);
            });
            btns.mouseleave(function() {
                timer = setInterval(change, speed);
            });
            uns.mouseenter(function() {
                clearInterval(timer);
            });
            uns.mouseleave(function() {
                timer = setInterval(change, speed);
            });
        }
        //初始化
        core();
    });
};