define('mode/typhoons.js', function(require, exports, module){ /**
 * 加载台风列表数据
 * @param  {Function} callback 回调函数
 */
var loadList = (function() {
        var callbacks = []; //回调函数列表
        var curData; //缓存的数据
        var curAjax; //正在ajax
        /**
         * 统一执行回调
         */
        var done = function() {
            $.each(callbacks, function(i, n) {
                n(curData);
            });
            callbacks.length = 0; //清空
        };
        /**
         * 格式化数据
         * @param  {object} xml	台风列表数据
         * @return {array}     台风列表
         */
        var format = function(xml) {
            var list = [];

            var d = new Date();
            var year = d.getFullYear();

            $.each($(xml).find("TyphoonItem"), function(i, n) {
                var el = $(n),
                    no = el.find("NO").text();
                //只取本年的
                if (no.substr(0, 4) == year) {
                    list.push({
                        no: no,
                        name: el.find("NameCn").text(),
                        isHistory: el.find("IsHistory").text() != "false"
                    });
                }
            });

            return list;
        };
        /**
         * 中断执行
         */
        var abort = function(callback) {
            $.each(callbacks, function(i, n) {
                if (n === callback) {
                    callbacks.splice(i, 1);
                    return false;
                }
            });
        };
        return function(callback) {
            if (typeof callback != "function") return;

            if (curData) {
                callback(curData);
            } else {
                callbacks.push(callback);

                if (curAjax) return; //正在请求中

                curAjax = $.ajax({
                    dataType: "xml",
                    cache: false,
                    url:  "/hnqx/data/typhoon/typhoonItems.xml",
                    success: function(xml) {
                        curData = format(xml);
                        curAjax = null;
                        done();
                    },
                    error: function() {
                        curData = null;
                        curAjax = null;
                        done();
                    }
                });

                return abort;
            }

        };
    }());

    /**
     * 查找台风详情
     * @param  {string} id 台风编号
     * @param  {Function} callback 回调函数
     * @return {object}  ajax对象
     */
    var loadInfo = function(id, callback) {
        var baseUrl= location.origin;
        return $.ajax({
            url:baseUrl + "/hnqx/data/typhoon/typhoonInfo_" + id + ".json",
            cache: false,
            dataType:"json",
            success: function(xml) {
                callback(xml);
            },
            error: function(e) {
                callback();
            }
        });
    };

    /**
     * 正在活动的台风
     * @param  {Function} callback 回调函数
     * @return {Function} 中断请求的函数
     */
    var active = function(callback) {
        return loadList(function(arr) {
            var list = [];
            $.each(arr, function(i, n) {
                if (!n.isHistory) {
                    list.push(n);
                }
            });
            typeof callback == "function" && callback(list);
        });
    };

    /**
     * 距离宁波中心距离
     * @param  {number} lon 经度
     * @param  {number} lat 纬度
     * @return {number}     距离
     */
    var getDistance = function(lon, lat) {
        var dx2 = Math.pow(121.54 - lon, 2);
        var dy2 = Math.pow(29.87 - lat, 2);
        return Math.sqrt(dx2 + dy2);
    };
    /**
     * 是否是离宁波最近的台风
     * @param  {object} d1 台风1
     * @param  {object} d2 台风2
     * @return {boolean} 是否离宁波最近
     */
    var isNearest = function(d1, d2) {
        var _list1 =d1.list?d1.list:d1.typhoonInfoItems;
        var _list2 = d2.list?d2.list:d2.typhoonInfoItems;
        var a = _list1[_list1.length - 1];
        var b = _list2[_list2.length - 1];
        return getDistance(a.lon, a.lat) > getDistance(b.lon, b.lat);
    };

    /**
     * 台风实况与宁波中心之间的中心点
     * @param  {number} lon 经度
     * @param  {number} lat 纬度
     * @return {object}     经纬度
     */
    var getCenter = function(lon, lat) {
        return {
            lon: (+lon + 121.54) / 2,
            lat: (+lat + 29.87) / 2
        };
    };
    return {
        all: loadList,
        active: active,
        find: loadInfo,
        isNearest: isNearest,
        getCenter: getCenter,
        getDistance: getDistance
    };

});