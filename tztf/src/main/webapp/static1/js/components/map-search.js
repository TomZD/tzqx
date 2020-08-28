define('components/map-search.js', function(require, exports, module){ //高德搜索
var webSearch = (function() {
    var ajax;
    var url = "http://restapi.amap.com/v3/place/text?s=rsv3&offset=10&page=1&language=zh_cn";
    var region = "0574"; //辅助城市宁波区号
    var key = "e391324389dc6b9749f7f5a61c1943df"; //应用key
    var tpl = "<%\r\n\tfor(var i=0,l=$data.length;i<l;i++){\r\n\t\tvar n=$data[i];\r\n%>\r\n\t<li data-lonlat=\"<%=n.location%>\">\r\n\t\t<b><%=n.name%></b>\r\n\t\t<%if(n.address){%>\r\n\t\t\t<span>\r\n\t\t\t\t<%=n.address%>\r\n\t\t\t</span>\r\n\t\t<%}%>\r\n\t</li>\r\n<%}%>";

    return function(query, box) {
        if (ajax) {
            ajax.abort();
            ajax = null;
        }
        ajax = $.ajax({
            dataType: "jsonp",
            url: url,
            data: {
                output: "json",
                keywords: query,
                city: region,
                key: key
            },
            success: function(json) {
                if (json && json.status) {
                    box.html(template.compile(tpl)(json.pois));
                } else {
                    box.html("");
                }
            }
        });
    };
}());

return function(box, callback) {
    var timer;
    var el = box.find(".m-mapsearch");
    var input = el.find('input');
    var tipsbox = el.find('ul');

    callback = callback || function() {};

    //实时查询
    input.bind("input propertychange", function(event) {
        var txt = $(this).val();
        if (txt) {
            tipsbox.html("");
            tipsbox.show();

            if (timer) {
                window.clearTimeout(timer);
            }
            timer = setTimeout(function() {
                webSearch(txt, tipsbox);
            }, 500);
        } else {
            tipsbox.hide();
        }
    });

    input.bind("blur", function() {
        tipsbox.hide();
    });

    //查询按钮
    el.find("button").bind("click", function(event) {
        input.trigger("input");
    });

    //选择
    tipsbox.delegate('li', 'click', function(event) {
        var v = $(this).find("b").text();
        var ll = $(this).attr("data-lonlat");
        //输入框的值
        input.val(v);
        //定位
        if (ll) {
            ll = ll.split(",");
            callback(ll[0], ll[1], v);
        }
        //
        tipsbox.hide();
    });
};
 
});