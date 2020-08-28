define('components/map-wlsxsjs.js', function(require, exports, module) {
    var util = require("components/utls");
    //湖州中心经纬度
    var NBlon = 113.0463,
        NBlat = 23.1974;

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
    Layer.prototype.addGrid = function(name, strxml, type, file, callback) {
        var that = this;
        // var xml = util.string2XML(strxml);数据转换
        var xml = strxml;
        that.grid = that.gis.drawContours(
            name,
            $(xml).find("RS")[0],
            0.5
        );

        var micapsTitle = $(xml).find('Micaps4Contour');
        if (micapsTitle) {
        	var title = micapsTitle.attr('Title');
        	var time = title.substring(0,title.indexOf("未来"));
            $('.title').text("未来3小时降水客观预报");
            $('.time').text(time);
        }
        // 图例
        that.legend = that.gis.addControl("legend", -10, -20, {
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
    Layer.prototype.add = function(type, title, url, callback) {
        var that = this;
        //清除内容
        if (this.ajaxFile) {
            this.ajaxFile.abort();
            this.ajaxFile = null;
        }
        if (this.grid) {
            this.gis.removeLayer(this.grid);
            this.grid = null;
        }
        if (this.legend) {
            this.gis.removeControl(this.legend);
            this.legend = null;

        }
        if (this.ajaxGrid) {
            this.ajaxGrid.abort();
            this.ajaxGrid = null;
        }
        if (this.tile) {
            this.gis.removeLayer(this.tile);
            this.tile = null;
        }
        var contourLayer;
        //加载图层
        if (!type) {
            return;
        }
        //标题
        $('.tgTitle').html('');
        //四类格式文件
        if (type == "grid") {
            $.ajax({
                // type: "post",
                url: url,
                dataType: "xml",
                success: function(Data) {
                    if (Data) {
                    	contourLayer = that.addGrid(title, Data, '', '', callback);
                    	return contourLayer;
                    }
                },
                error: function(e) {}
            });

        } else {

            $('.maptitle').html(title);
            that.addTile(title, "radar", file, url); //MOSAICHREF000.20160411.011000.latlon
        }
    };

    return function(gis) {
        return new Layer(gis);
    };
});
