define('components/map-layer.js', function(require, exports, module) {
    var util = require("components/util");

    /**
     * 天气图层，构造函数
     */
    var Layer = function(gis, url) {
        this.gis = gis;
        this.url = url;
    };
    /**
     * 添加瓦片图：雷法、卫星
     * @param {string} name 图层名字    
     * @param {string} type 图层类型
     * @param {string} file 文件名：MOSAICHREF000.20160411.011000.latlon
     */
    Layer.prototype.addTile = function(title, type, file, url) {
        // 数据解释：
        // type:Radar
        // file:MOSAICHREF000.20161208.081000.latlon
        // name:2016年12月08日 16:10

        this.tile = this.gis.addTileLayer(
            serverUrl + "/GetTiledImage.ashx?Type=" + type + "&File=" + file + "&x=${x}&y=${y}&z=${z}&HaveStromTrack=true",
            title
        );
        this.tile.setOpacity(0.5);
    };
    /**
     * 添加四类文件图层：降水、温度等
     * @param {string} name 图层名字
     * @param {string} strxml 数据
     * @param {string} type 图层类型
     * @param {string} file 文件名
     */
    Layer.prototype.addGrid = function (name, strxml, type, opacity, file, callback) {
        var that = this;
        // var xml = util.string2XML(strxml);数据转换
        var xml = strxml;
        that.grid = that.gis.drawContours(
            name,
            $(xml).find("RS")[0],
            opacity
        );
        // 图例
        that.legend = that.gis.addControl("legend", -26, -20, {
            content: $(xml).find("Rainbow Content").text(),
            layers: that.grid,
            status: "max",
            extendClass: "legend" //外部传入的自定义样式
        });
        if (callback &&  typeof callback == "function") {
            callback();
        }
        return that.grid;
    };
    /**
     * 添加图层
     * @param {string} type 图层类型 type=grid：等值线。type!=grid：切片图
     */
    Layer.prototype.add = function (type, title, strXml, isLoadColor, opacity, legendName) {
        var that = this;
        //清除内容
        if (this.legend) {
            this.gis.removeControl(this.legend);
            this.legend = null;
        }
        var contourLayer;
        //标题
        $('.tgTitle').html('');
        //四类格式文件，等值线数据
        if (type == "grid") {
            //字符串数据转换xml
            var xml = strXml;
            //绘制等值线图层
            contourLayer = that.addGrid(title, xml, legendName, opacity);
            //添加标题
            var micapsTitle = $(xml).find('Micaps4Contour');
            if (micapsTitle) {
                $('.tgTitle').text(micapsTitle.attr('Title'));
            }
        };
        return contourLayer;
    }
    Layer.prototype.clear = function () {
        this.gis.removeLayer(Layer);
        Layer = null;
        if (legend) {
            gis.removeControl(legend);
        }
    }
    return function(gis) {
        return new Layer(gis);
    };
});
