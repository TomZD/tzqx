var ctime;
define('components/map-typhoon.js', function(require, exports, module) {
    var template = require("lib/template");
    var mode = require("mode/typhoons");
    var tplInfoBox = "<!-- 台风信息面板 -->\r\n<div class=\"m-typhooninfo\">\r\n    <div class=\"m-typhooninfo-hd menu\">\r\n        <div class=\"menu-title\">\r\n            <span>活动台风</span>\r\n       \r\n  <p></p>\r\n   </div>\r\n        <ul class=\"menu-list\">\r\n            <li>正在加载...</li>\r\n        </ul>\r\n    </div>\r\n    <div class=\"m-typhooninfo-bd\">\r\n        正在加载数据...\r\n    </div>\r\n</div>";
    var oneInfoBox = "<!-- 台风信息面板 -->\r\n<div class=\"m-typhooninfo\">\r\n    <div class=\"m-typhooninfo-hd menu\">\r\n        <div class=\"menu-title\">\r\n            <span>活动台风</span>\r\n       \r\n  <p></p>\r\n   </div>\r\n        </div>\r\n    <div class=\"m-typhooninfo-bd\">\r\n        正在加载数据...\r\n    </div>\r\n</div>";
    var tplInfo = "<%if($data){%>\r\n    <table>\r\n        <colgroup>\r\n            <col style=\"width: 80px;\" />\r\n            <col />\r\n        </colgroup>\r\n        <tbody>\r\n            <tr>\r\n                <td class=\"mark\">\r\n                    <span>发布时间</span>\r\n                </td>\r\n                <td>\r\n                    <%=date%>\r\n                </td>\r\n            </tr>\r\n            <tr>\r\n                <td class=\"mark\">\r\n                    <span>中心位置</span>\r\n                </td>\r\n                <td>\r\n                    <%=lat%>°N, <%=lon%>°E\r\n                </td>\r\n            </tr>\r\n            <tr>\r\n                <td class=\"mark\">\r\n                    <span>台风强度</span>\r\n                </td>\r\n                <td>\r\n                    <%=type%>\r\n                </td>\r\n            </tr>\r\n            <tr>\r\n                <td class=\"mark\">\r\n                    <span>中心气压</span>\r\n                </td>\r\n                <td>\r\n                    <%=AirPressure%>百帕</td>\r\n            </tr>\r\n            <tr>\r\n                <td class=\"mark\">\r\n                    <span>最大风力</span>\r\n                </td>\r\n                <td>\r\n                    <%=WindPower%>级(<%=WindVelocity%>米/秒)\r\n                </td>\r\n            </tr>\r\n            <%if(direction){%>\r\n                <tr>\r\n                    <td class=\"mark\">\r\n                        <span>移动方向</span>\r\n                    </td>\r\n                    <td>\r\n                        <%=direction%>\r\n                    </td>\r\n                </tr>\r\n            <%}%>\r\n            <%if(moveVelocity){%>\r\n                <tr>\r\n                    <td class=\"mark\">\r\n                        <span>移动速度</span>\r\n                    </td>\r\n                    <td>\r\n                        <%=moveVelocity%>米/秒\r\n                    </td>\r\n                </tr>\r\n            <%}%>\r\n            <%if(Level7Distance){%>\r\n                <tr>\r\n                    <td class=\"mark\">\r\n                        <span>7级风圈</span>\r\n                    </td>\r\n                    <td>半径<%=Level7Distance%>公里</td>\r\n                </tr>\r\n            <%}%>\r\n            <%if(Level10Distance){%>\r\n                <tr>\r\n                    <td class=\"mark\">\r\n                        <span>10级风圈</span>\r\n                    </td>\r\n                    <td>半径<%=Level10Distance%>公里</td>\r\n                </tr>\r\n            <%}%>\r\n            <%if(Level12Distance){%>\r\n                <tr>\r\n                    <td class=\"mark\">\r\n                        <span>12级风圈</span>\r\n                    </td>\r\n                    <td>半径\r\n                        <%=Level12Distance%>公里\r\n                    </td>\r\n                </tr>\r\n            <%}%>\r\n   <%if(distance){%>\r\n                <tr>\r\n                    <td class=\"mark\">\r\n                        <span>离黄岩距离</span>\r\n                    </td>\r\n                    <td>                        <%=distance%>公里\r\n                    </td>\r\n                </tr>\r\n            <%}%>\r\n      </tbody>\r\n    </table>\r\n<%}else{%>\r\n    当前无活动台风\r\n<%}%>";
    var tplMenu = "<%\r\n    for(var i=0,l=$data.length;i<l;i++){\r\n        var n=$data[i];\r\n%>\r\n    <li data-id=\"<%=n.no%>\">\r\n        <%=n.name%>\r\n    </li>\r\n<%}%>";


    /**
     * 台风
     * @param {object} gis         gis
     * @param {object} box         容器
     * @param {string} ids         台风编号列表，如果为空则加载最新活动台风
     * @param {boolean} showInfo   是否显示台风信息
     * @param {boolean} flag       是否显示台风列表
     */
    var Typhoons = function(gis, box, ids, showInfo,flag,clear) {
        var that = this;

        this.ajaxs = [];
        //台风请求队列

        this.gis = gis;
        //判断是移除台风还是加载台风
        if(clear){
            that.loadTyphoon(ids, true);
            return ;
        }
        this.typhoonBox = gis.drawTyphoon({
            publishersFilter : "BABJ,VHHH,PGTW,RJTD,RKSL"
        });

        this.showInfo = showInfo;
        this.flag = flag;
        //是否显示信息面板

        this.typhoonBox.showLevelLengend(-10, -10);
        //加载等级色标



        //如果未指定台风，默认加载最新活动台风
        if (ids) {
            this.loadList(ids.split(","));
        } else {
            mode.active(function(arr) {
                var data = [];
                $.each(arr, function(i, n) {
                    data.push(n.no);
                });
                that.loadList(data);
            });
        }

        //是否要显示台风信息
        if (box && box.length && showInfo) {
            if(flag==false){
                $(".m-typhooninfo").remove();
                this.elBox = $(oneInfoBox).appendTo(box);
            }else{
                $(".m-typhooninfo").remove();
                this.elBox = $(tplInfoBox).appendTo(box);
            }
            this.elList = this.elBox.find(".menu-list");
            this.elTitle = this.elBox.find(".menu-title span");
            this.elView = this.elBox.find('.menu-title p');
            this.elContent = this.elBox.find(".m-typhooninfo-bd");

            //加载数据
            mode.all(function(arr) {
                that.elList.html(template.compile(tplMenu)(arr));
            });

            //切换台风路径
            // this.elList.delegate('li', 'click', function() {
            //     $(this).addClass('z-on').siblings().removeClass('z-on');
            //     that.loadTyphoon($(this).attr("data-id"), true);
            // });
            //新增或移除台风路径
            $(".typhoon_list").delegate("input","click",function(){
                var inputNo = $(this).parent().next().text();
                if($(this).is(":checked")){
                    that.loadTyphoon(inputNo, false);
                    // $(".menu-title span").attr("data-id",inputNo);
                }else{
                    that.loadTyphoon(inputNo, true);
                }
            });
            //移除或新增预报
            $(".m-forecasting-model ul li input").click(function() {
                var pubitem = $(this).parent().parent().attr('data-id');
                if ($(this).attr('checked')) {
                    //添加各预报路径
                    for (var i = that.typhoonBox.typhoons.length - 1; i >= 0; i--) {
                        var n = that.typhoonBox.typhoons[i];
                        n.addForecast(pubitem);
                    }
                } else {
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

            //清空台风
            $("#clear_typhoon").click(function(){
                that.loadTyphoon("", true);
                $(".typhoon_list input").removeProp("checked");
            })
        }
    };

    /**
     * 清除所有加载好的和正在请求中的台风
     */
    Typhoons.prototype.clearTyphoon = function(id) {
        this.cur = null;
        this.typhoonBox.clearTyphoon(id);
        if(id){
            for(var i=0,l=this.ajaxs.length;i<l;i++){
                if(id == this.ajaxs[2*i]){
                    this.ajaxs[2*i +1]&&this.ajaxs[2*i +1].abort();
                    this.ajaxs.splice(i,2);
                    break;
                }
            }
        }else{
            $.each(this.ajaxs, function(i, n) {
                if(i%2 == 1){
                    n && n.abort();
                }
            });
            this.ajaxs.length = 0;
        }
    };

    /**
     * 加载一条台风数据
     * @param  {string}  id         台风编号
     * @param  {boolean} clearCache 是否要清除缓存
     */
    Typhoons.prototype.loadTyphoon = function(id, clearCache, callback) {
        var that = this;

        if (clearCache) {
            this.clearTyphoon(id);//清空当前一条台风
            return;
        }
        this.ajaxs.push(id)
        this.ajaxs.push(mode.find(id, function(xml) {
            if (!xml)
                return;
           // var data = that.typhoonBox.xml2json(xml);
            var data =xml;
            that.typhoonBox.addTyphoon(data);

            //如果是最近的台风
            if (!that.cur || mode.isNearest(that.cur, data)) {
                //计算地图中心
                var _list = data.list?data.list:data.typhoonInfoItems;
                var a = _list[_list.length - 1];
                var ll = mode.getCenter(a.lon, a.lat);

                that.gis.setCenter(ll.lon, ll.lat, 4);

                //加载台风信息
                if (that.showInfo) {
                    that.loadInfo(data);
                }
                //缓存
                that.cur = data;
            }
        }));
    };

    /**
     * 加载台风信息
     * @param  {object} data 台风数据
     */
    Typhoons.prototype.loadInfo = function(data) {
        if (!data)
            return;
        var _list = data.list?data.list:data.typhoonInfoItems;
        var list = _list[_list.length - 1], time = list.time;
        ctime = time;

        this.elContent.html(template.compile(tplInfo)({
            name : data.name,
            type : data.type,
            no : data.id,
            lon : list.lon,
            lat : list.lat,
            date : time.substr(0, 2) + "月" + time.substr(2, 2) + "日" + time.substr(4, 2) + "时",
            AirPressure : list.pressure,
            WindVelocity : list.windVelocity,
            WindPower : list.windPower,
            Level7Distance : list.lv7,
            Level10Distance : list.lv10,
            Level12Distance : list.lv12,
            moveVelocity : list.moveVelocity,
            direction : list.direction,
            distance: list.distance
        }));
        var _year=data.id.substr(0,4);
        var _month=data.id.substr(4,2);
        this.elView.text(_year+'年第'+_month+'号台风');
        this.elTitle.text(data.name);
        this.elTitle.attr("data-id",data.id);
    };

    /**
     * 加载台风列表
     * @param  {array} list 台风编号列表
     */
    Typhoons.prototype.loadList = function(list) {
        var that = this;
        if (list && list.length) {
            $.each(list, function(i, n) {
                that.loadTyphoon(n);
            });
        } else {
            if(this.elContent){
                this.elContent.html("当前无活动台风");
            }else{
                $(".menu-title span").html("暂无活动台风")
                $(".m-typhooninfo-bd").html("当前无活动台风");
            }
        }
    };

    return function(gis, box, ids, showInfo,flag,clear) {
        return new Typhoons(gis, box, ids, showInfo,flag,clear);
    };

});
