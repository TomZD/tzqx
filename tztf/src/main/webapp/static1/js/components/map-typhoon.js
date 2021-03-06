define('components/map-typhoon.js', function(require, exports, module) {
    var template = require("lib/template");
    var mode = require("mode/typhoons");
    /**
     * 台风加载
     * @param {object} gis         gis
     * @param {object} box         容器
     * @param {string} ids         台风编号列表，如果为空则加载最新活动台风
     * @param {boolean} showInfo   是否显示台风信息
     */
    var Typhoons = function(gis, box, ids, showInfo) {
        var that = this;
        //$('.olControlZoom').css({ 'bottom': '', 'top': '10px', 'left': '180px', 'right': '' });

        this.ajaxs = []; //台风请求队列

        this.gis = gis;

        this.typhoonBox = gis.drawTyphoon({
            publishersFilter: "BABJ,BEHZ,RJTD,PGTW,RCTP,VHHH,RKSL"
        });

        this.showInfo = showInfo; //是否显示信息面板

        this.typhoonBox.showLevelLengend(10, -10); //加载等级色标

        //如果未指定台风，默认加载最新活动台风
        if (ids) {
            this.loadList(ids.split(","));
        } else {
            that.loadList();
        }
        //加载作图工具
        /*if ($("#tool") && gis) {
            gis.drawTools("tool", "./images/addr.png", 10000, this);
        }
        //切换台风路径
        this.typhoonListChebox = function typhoonListChebox() {
            $(box).find('#tyTimeTab input:[type="checkbox"]').each(function(i, item) {
                $(item).bind('click', function() {
                    typhoonNo = $(this).attr('value');
                    if ($(this).attr('checked')) {
                        //台风报告单
                        //gettyphoonWords(typhoonNo);
                        TyphoonLoadCurrent(typhoonNo, function(json) { //TyphoonLoadCurrent为typhoonmap定义的方法
                            //台风路径信息
                            that.loadTyphoon(typhoonNo, false, json);
                        });

                    } else {
                        for (var i = that.typhoonBox.typhoons.length - 1; i >= 0; i--) {
                            var n = that.typhoonBox.typhoons[i];
                            if (n && n.id == typhoonNo) {
                                n && n.destroy();
                                that.typhoonBox.typhoons.splice(i, 1);
                                break;
                            }
                        }
                    }
                });
            });
        }
        //年份切换
        $(box).find('.yearList .bindYear').bind('change', function() {
            getTyphoonListByYear($(this).attr('value'), "", function(json) {
                that.typhoonListChebox();
                var Jsonid = json[0].No;
                TyphoonLoadCurrent(Jsonid, function(json) { //TyphoonLoadCurrent为typhoonmap定义的方法
                    that.loadTyphoon(Jsonid, true, json);
                })
            });
        });
        //查找相似台风start
        $(box).find("#getsimilarData").bind("click", function() {
            $("#tool").find("li[data-val='drag']").click(); //查看后使地图回到可拖拽状态
            var layer = gis.map.getLayersByName("绘图工具")[0];
            if (layer) {
                var geom = layer.features[0].geometry.components[0];
                var points = '';
                for (var i = 0; i < geom.components.length; i++) {
                    points += geom.components[i].x + ',' + geom.components[i].y + ';';
                }
                points = points.substr(0, points.length - 1);
                //console.info("所有点的集合：" + points);
                //调用接口传递墨卡托数据，需接口支持,现在用本地数据写死
                getTyphoonListByYear("", points, function(json) {
                    that.typhoonListChebox();
                    var Jsonid = json[0].No;
                    TyphoonLoadCurrent(Jsonid, function(json) { //TyphoonLoadCurrent为typhoonmap定义的方法
                        that.loadTyphoon(Jsonid, true, json);
                    })

                });
            }
        });*/
        //查找相似台风end   
        //是否要显示台风信息
        if (box && box.length && showInfo) {
            //this.typhoonListChebox();
            //预报单位面板（图例控制各类预报路径是否显示）
            $(box).find('.msgTyphoon input[type="checkbox"]:checked').each(function(index, item) {
                $(item).bind('click', function() {
                    var pubitem = $(this).attr('value');
                    if ($(this).attr('checked')) {
                        if (that.typhoonBox.publishersFilter.indexOf(pubitem) == -1) {
                            that.typhoonBox.publishersFilter += "," + pubitem;

                            //添加各预报路径
                            for (var i = that.typhoonBox.typhoons.length - 1; i >= 0; i--) {
                                var n = that.typhoonBox.typhoons[i];
                                n.addForecast(pubitem);
                            }
                        }
                    } else {
                        if (that.typhoonBox.publishersFilter.indexOf(pubitem) > -1) {
                            that.typhoonBox.publishersFilter = that.typhoonBox.publishersFilter.replace(pubitem, "");
                        }

                        for (var i = that.typhoonBox.typhoons.length - 1; i >= 0; i--) {
                            var n = that.typhoonBox.typhoons[i];
                            var fs = n.forecasts[pubitem];
                            if (fs) {
                                that.typhoonBox.pathsLayer.remove(fs.path);
                                that.typhoonBox.pointLayer.remove(fs.points);
                                delete n.forecasts[pubitem];
                            }
                        }
                    }
                });
            });   
        }
    };
    /**
     * 清除所有加载好的和正在请求中的台风
     */
    Typhoons.prototype.clearTyphoon = function() {
        this.cur = null;
        this.typhoonBox.clearTyphoon();
        $.each(this.ajaxs, function(i, n) {
            n && n.abort();
        });
        this.ajaxs.length = 0;
    };
    /**
     * 加载一条台风数据
     * @param  {string}  id         台风编号
     * @param  {boolean} clearCache 是否要清除缓存
     * @param  {object}  json 当前加载的台风数据
     */
    Typhoons.prototype.loadTyphoon = function(id, clearCache, json) {
        var that = this;

        if (clearCache) {
            this.clearTyphoon();
        }

        this.ajaxs.push(
            (function() {
                if (!json) return;
                var data = that.typhoonBox.xml2json(json);
                that.typhoonBox.addTyphoon(data);
                /*rightTyList(data.list, data.id, data.name);*/ //加载台风时同步右侧台风详情，rightTyList为typhoonmap.js定义的函数
                bindNearty(json.LivePaths[0], data.id, data.name); //绑定最近的台风详情，bindNearty为typhoonmap.js定义的函数
                //如果是最近的台风
                if (!that.cur || mode.isNearest(that.cur, data)) {
                    //计算地图中心
                    var a = data.list[data.list.length - 1];
                    var ll = mode.getCenter(a.lon, a.lat);
                    //that.gis.setCenter(ll.lon, ll.lat, 4);
                    //缓存
                    that.cur = data;
                }
            })()
        );
    };
    /**
     * 加载默认台风
     * @param  {array} list 台风编号列表
     */
    Typhoons.prototype.loadList = function(list) {
        var that = this;
        //详细信息
        if (actPathObj) {
            that.loadTyphoon(actPathObj.No, false, actPathObj);
        }
    };
    return function(gis, box, ids, showInfo) {
        return new Typhoons(gis, box, ids, showInfo);
    };
});