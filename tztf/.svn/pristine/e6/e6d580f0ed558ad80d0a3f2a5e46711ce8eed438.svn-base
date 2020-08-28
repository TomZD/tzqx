define('components/map-hzqxsk.js', function(require, exports, module) {
    var util = require("components/util");
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
        	var time= title.substring(title.indexOf("("),title.indexOf(")")+1);
        	title =title.substring(0,title.indexOf("("))+title.substring(title.indexOf(")")+1);
        	//标题替换
        	if(title.indexOf("5分钟")>-1){//5分钟气温标题替换、时间替换
        		title="杭州市当前实况气温分布图";
        		time="("+time.substring(time.indexOf("-")+1);
        	}
        	else if(title.indexOf("当天以来最高温")>-1){//今日最高温标题替换
        		title="杭州市今日最高温分布图";
        	}
        	else if(title.indexOf("当天以来最低温")>-1){//今日最低温标题替换
        		title="杭州市今日最低温分布图";
        	}
        	else if(title.indexOf("24小时最高温")>-1){//近24小时最高温标题替换
        		title="杭州市近24小时最高温分布图";
        	}
        	else if(title.indexOf("24小时最低温")>-1){//近24小时最低温标题替换
        		title="杭州市近24小时最低温分布图";
        	}
            $('.title').text(title);
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
