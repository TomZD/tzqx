define('components/giScroll.js', function(require, exports, module){ /*
 * giScroll - 基于jquery滚动插件
 */
$.fn.extend({
    giScroll: function(options) {
        //参数和默认值
        options = $.extend({
            sizeAuto: false, //是否自适应宽度
            speedMove: 500, //每次滚动速度
            oneMove: null, //一次移动的距离
            elLeft: null,
            elRight: null,
            canOverflow: true //如果容器没有设置overlow:hidden/auto/scroll,那么要使用替代方案
        }, options);

        var eventFn = options.eventFn;

        //上一页
        function prevPage() {
            move.call(this, -(options.oneMove || this.boxWidth));
        }

        //下一页
        function nextPage() {
            move.call(this, options.oneMove || this.boxWidth);
        }

        //翻页
        function move(distance) {
            this.distance += distance;
            this.distance = checkVal.call(this, this.distance);
            if (options.canOverflow) {
                this.box
                    .stop()
                    .animate({
                            scrollLeft: this.distance
                        },
                        options.speedMove
                    );
            } else {
                this.fChild
                    .stop()
                    .animate({
                            "margin-left": -this.distance
                        },
                        options.speedMove
                    );
            }
        }
        //重置
        function resize() {
            this.boxWidth = this.box.width();
            this.max = this.sWidth <= this.box.innerWidth() ? 0 : this.sWidth - this.boxWidth; //可移动极限
            move.call(this, this.distance);
        }

        //验证值是否超出范围
        function checkVal(val) {
            if (this.max <= val) {
                return this.max;
            }
            if (0 >= val) {
                return 0;
            }
            return val;
        }

        return this.each(function() {
            this.box = $(this); //容器
            this.sWidth = this.box[0].scrollWidth;
            this.distance = 0;
            this.fChild = this.box.children().eq(0);

            resize.call(this);

            //绑定事件
            var elLeft = options.elLeft,
                elRight = options.elRight;

            //上一页
            if (elLeft && elLeft.length) {
                elLeft.bind("click", $.proxy(prevPage, this));
            }
            //下一页
            if (elRight && elRight.length) {
                elRight.bind("click", $.proxy(nextPage, this));
            }

            options.sizeAuto && $(window).bind("resize", $.proxy(resize, this)); //绑定resize事件
        });
    }
}); 
});