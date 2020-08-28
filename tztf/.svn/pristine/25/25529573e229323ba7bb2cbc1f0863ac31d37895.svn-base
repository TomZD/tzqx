define('components/map-layer.js', function(require, exports, module) {
    var util = require("components/util");
    //富阳中心经纬度
    var NBlon = 122,
        NBlat = 28.973;

    /**
     * 天气图
     * @param {[type]} gis [description]
     */
    var Layer = function(gis) {
        this.gis = gis;
    };
    /**
     * 添加瓦片图
     * @param {string} name 图层名字    
     * @param {string} type 图层类型
     * @param {string} file 文件名
     */
    Layer.prototype.addTile = function(name, type, file) {
        //var zoom = 8;
        //if (type.indexOf("FY2_IR1") > -1) {
        //    zoom = 2;
        //    NBlat=19.87
        //} else {
        //    zoom = 8;
        //    NBlat = 29.87;
        //}
        //this.gis.setCenter(NBlon, NBlat, zoom);
        this.tile = this.gis.addTileLayer(
            serverUrl + "/GetTiledImage.ashx?Type=" + type + "&File=" + file + "&x=${x}&y=${y}&z=${z}&HaveStromTrack=true",
            name
        );
        this.tile.setOpacity(0.5);
    };
    /**
     * 添加四类文件图层
     * @param {string} name 图层名字    
     * @param {string} type 图层类型
     * @param {string} file 文件名
     */
    Layer.prototype.addGrid = function(name, strxml, type, file) {
        var that = this;
        //this.gis.setCenter(NBlon, NBlat, 8);

        // var xml = util.string2XML(strxml);
var xml=strxml;
        that.grid = that.gis.drawContours(
            name,
            $(xml).find("RS")[0],
            0.5
        );

        var marginR = -10;
        var micapsTitle=$(xml).find('Micaps4Contour');
        if(micapsTitle){
          $('.tgTitle').text(micapsTitle.attr('Title'));   
        }

        that.legend = that.gis.addControl("legend", marginR, -20, {
            content: $(xml).find("Rainbow Content").text(),
            layers: that.grid,
            status: "max",
            extendClass: "legend" //外部传入的自定义样式
        });

    };
    /**
     * 添加图层
     * @param {string} type 图层类型   
     */
    Layer.prototype.add = function(type, title,i) {
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

        //加载图层
        if (!type) {
            $('.weatherTitle').css('display', 'none');
            return;
        }
        //标题
        if (title) {
            $('.weatherTitle').html(title);
            if ($('.weatherTitle').css('display') != 'block') {
                $('.weatherTitle').css('display', 'block');
            }
        } else {
            if ($('.weatherTitle').css('display') == 'block') {
                $('.weatherTitle').css('display', 'none');
            }
        }
        //lonlat_HREF/Radar
        //satellite_IR1/FY2_IR1
        //grid/16041008_000.zip
        // var s = type.split("/")[0],
        //            file = type.split("/")[1];
        // if ($('.moveDiv').is(':hidden')) {
        //     $('.moveDiv').show();
        // } else {
        //     return;
        // }
        //四类格式文件
        if (type == "grid") {
          

            $.ajax({
               // type: "post",
               url: "/hztq/data/tgwd/tgwd_"+i+".xml",
               // url: "https://www.hzqx.com/hztq/data/IndexForecast/Tg-4/Xml/16080505_147_ZJOCF_Tg_dat.xml",
               dataType: "xml",
               success: function (Data) {
                   if ($('.moveDiv').is(':visible')) {
                       $('.moveDiv').hide();
                   }
                   if (Data) {
                       that.addGrid(title, Data);
                   }
               },
               error: function (e) {
                   //alert(e);
               }
            });

        } else {
            //切片图：雷达，卫星
            if ($('.moveDiv').is(':visible')) {
                $('.moveDiv').hide();
            }
            //if (Data) {
            $('.weatherTitle').html(title);
            //type = file;
            //2016-04-11 01:10:00
            that.addTile(title, s, file); //MOSAICHREF000.20160411.011000.latlon
            //}
            //});
        }
    };
    return function(gis) {
        return new Layer(gis);
    };
});
