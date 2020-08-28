define(function(require) {
    return OpenLayers.Class(OpenLayers.Control, {
        func: null,
        /**
         * 默认的显示状态
         * @type {String}
         */
        status: "min",
        /**
         * 自动激活控件
         * @type {Boolean}
         */
        autoActivate: true,
        /**
         * 关联对象
         * @type {Boolean}
         */
        layers: [],
        /**
         * 格式化数据
         */
        format: function() {
            if (!this.content) return;

            var arr = this.content.split(/[\r\n]+/);
            //获取标题,第一行第一部分包含非数字
            var title = arr[0].replace(/(^\s*)|(\s*$)/g, "");
            if (isNaN(title.split(" ")[0])) {
                this.title = title;
                arr.shift();
            } else {
                this.title = "图例";
            }

            //获得色标数组
            var colors = [];
            var i, l, un, rgb, color, v1, v2;
            for (i = 0, l = arr.length; i < l; i++) {
                un = arr[i].replace(/(^\s*)|(\s*$)/g, "").split(/\s+/); //根据空字符分割
                if (un.length > 1) {
                    rgb = un[1];
                    color = rgb.split(",");
                    if (color.length == 4) {
                        rgb = color[1] + "," + color[2] + "," + color[3];
                    }
                    colors.unshift({
                        value: un[0], //值
                        color: "rgb(" + rgb + ")", //颜色
                        alias: un[2], //别名
                        image: un[3] //图标
                    });
                }
            }

            //排序与边界值
            if (colors.length > 1) {
                v1 = colors[0].value;
                v2 = colors[colors.length - 1].value;
                if (v1 - v2 > 0) {
                    this.sort = false;
                    this.maxValue = v1;
                    this.minValue = v2;
                } else {
                    this.sort = true;
                    this.minValue = v1;
                    this.maxValue = v2;
                }
            }
            this.colors = colors;
        },
        /**
         * 内容模板
         */
        temtplate: function() {
            var html = "",
                i, l, n;
            if (this.colors) {
                for (i = 0, l = this.colors.length; i < l; i++) {
                    n = this.colors[i];
                    html += "<li>";
                    html += "<i style='background:" + n.color + "'></i>"; //颜色
                    html += n.alias == null ? n.value : n.alias; //名字或值
                    html += "</li>";
                }
            }
            return html;
        },
        /**
         * 添加关联图层
         * @param  {object} layer 图层
         */
        addLayer: function(layer) {
            if (!layer) return;
            layer.setRange(this.value, this.sort, this.maxValue, this.minValue);
            this.layers.push(layer);
        },
        /**
         * 批量添加关联图层
         * @param  {Array} layers 图层
         */
        addLayers: function(layers) {
            if (!(OpenLayers.Util.isArray(layers))) {
                layers = [layers];
            }
            for (var i = 0, l = layers.length; i < l; i++) {
                this.addLayer(layers[i]);
            }
        },
        /**
         * 阻止事件冒泡
         */
        stopPropagation: function(evt) {
            OpenLayers.Event.stop(evt, true);
        },
        /**
         * 初始化
         * @param  {object} options 配置
         */
        initialize: function(options) {
            if (!options) return;
            OpenLayers.Control.prototype.initialize.apply(this, [options]);
            this.format(); //格式化数据
        },
        /**
         * 启用
         */
        activate: function() {
            var bl = OpenLayers.Handler.prototype.activate.apply(this, arguments);

            if (bl) {
                this.eventsContent.register("click", this, this.select);
            }
            return bl;
        },
        /**
         * 禁用
         */
        deactivate: function() {
            var bl = OpenLayers.Handler.prototype.deactivate.apply(this, arguments);

            if (!bl) {
                this.eventsContent.unregister("click", this, this.select);
            }
            return bl;
        },
        /**
         * 绘制
         * @param  {object} px 位置
         */
        draw: function(px) {
            OpenLayers.Control.prototype.draw.apply(this, [px]);

            if (this.status != "max") {
                OpenLayers.Element.addClass(this.div, this.displayClass + "-min");
            }

            //标题
            this.divTitle = document.createElement("div");
            this.divTitle.innerText = this.title;
            OpenLayers.Element.addClass(this.divTitle, this.displayClass + "-title");
            this.div.appendChild(this.divTitle);

            //最小化按钮
            this.divMinButton = document.createElement("div");
            OpenLayers.Element.addClass(this.divMinButton, this.displayClass + "-button");
            this.divTitle.appendChild(this.divMinButton);

            //内容
            this.divContent = document.createElement("ul");
            OpenLayers.Element.addClass(this.divContent, this.displayClass + "-content");
            this.divContent.innerHTML = this.temtplate();
            this.div.appendChild(this.divContent);

            //注册事件
            this.events = new OpenLayers.Events(this, this.div);
            this.eventsTitle = new OpenLayers.Events(this, this.divTitle);
            this.eventsContent = new OpenLayers.Events(this, this.divContent);

            this.events.on({
                "click": this.stopPropagation,
                "dblclick": this.stopPropagation,
                "mousedown": this.stopPropagation,
                "mouseup": this.stopPropagation
            });
            if (this.status != "max") {
                this.eventsTitle.register("click", this, this.max);
            } else {
                this.eventsTitle.register("click", this, this.min);
            }

            return this.div;
        },
        /**
         * 面板最小化
         */
        min: function() {
            OpenLayers.Element.addClass(this.div, this.displayClass + '-min');
            this.eventsTitle.register("click", this, this.max);
            this.eventsTitle.unregister("click", this, this.min);
        },
        /**
         * 面板最大化
         */
        max: function() {
            OpenLayers.Element.removeClass(this.div, this.displayClass + '-min');
            this.eventsTitle.unregister("click", this, this.max);
            this.eventsTitle.register("click", this, this.min);
        },
        /**
         * 色标选择
         */
        select: function(e) {
            var target = e.target || e.srcElement;
            var colors = this.colors;
            var v, el, i, l, n;
            if (target.nodeName == 'LI') {
                el = target;
            } else if (target.parentNode.nodeName == 'LI') {
                el = target.parentNode;
            }

            if (el) {
                var arr = this.divContent.childNodes;

                var bl = false;
                var cla = this.displayClass + "-unselect";
                if (OpenLayers.Element.hasClass(el, cla)) {
                    el = el.previousSibling || el.previousElementSibling;
                    bl = el == null;
                }
                for (i = 0, l = arr.length; i < l; i++) {
                    n = arr[i];
                    if (bl) {
                        OpenLayers.Element.removeClass(n, cla);
                    } else {
                        OpenLayers.Element.addClass(n, cla);

                        if (n == el) {
                            bl = true;
                            v = colors[i] && colors[i].value;
                        }
                    }
                }
                //如果数值不存在
                if (v == null) {
                    v = this.sort ? this.minValue : this.maxValue;
                }
                //联动
                if (this.layers) {
                    for (i = 0, l = this.layers.length; i < l; i++) {
                        n = this.layers[i];
                        typeof n.changeRange == "function" && n.changeRange(v);
                    }
                }
                //点击事件
                typeof n.changeRange == "function" && this.func(v);
            }
        },
        /**
         * 销毁
         */
        destroy: function() {
            this.layers = null;
            this.colors = null;
            this.events.destroy();
            this.eventsTitle.destroy();
            this.eventsContent.destroy();
            OpenLayers.Control.prototype.destroy.apply(this);
        },
        CLASS_NAME: "OpenLayers.Legend"
    });
});