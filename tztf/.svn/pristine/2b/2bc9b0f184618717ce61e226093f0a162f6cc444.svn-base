define(function(require) {
    var gis, layer, legend;
    var template = require("lib/template");
    var Draw = require("lib/gigis/draw/draw");
    var draw = new Draw();
    var lyLegend = $(template("legendTpl-ly")).appendTo($("#map").children());

    //加载等值线
    var add = function(xml, type) {
        layer = gis.drawContours("contours", xml, 0.5);
        legend = gis.addControl("legend", 10, -26, {
            layers: layer,
            sizeBtn: true,
            content: xml.getElementsByTagName("Content")[0].childNodes[0].nodeValue
        });

        return layer;
    };

    var clear = function() {
        layer && gis.map.removeLayer(layer);
        layer = null;
        lyLegend.hide();
        if (legend) {
            gis.removeControl(legend);
        }
    };

    var showOrHide = function (isTrue) {
        if (isTrue) {
            layer.setVisibility(true);
        } else {
            layer.setVisibility(false);
        }
    }
    var loadRiver = function(data) {
        lyLegend.show();

        var lines = [],
            coords;
        for (var i = 0, len = data.length; i < len; i++) {
            var line = [];
            coords = data[i].Coords[0];

            for (var j = 0, len = coords.length; j < len; j++) {
                var lon = coords[j][0];
                var lat = coords[j][1];
                line.push(gis.tanslateLonLat(lon, lat));
            }

            lines.push(line);
        }
        layer = new OpenLayers.Layer.Vector("layer");
        gis.addLayer(layer);
        layer.addFeatures(new OpenLayers.Feature.Vector(draw.getPolygon(lines)));
    };
    return function(g) {
        gis = g;

        return {
            add: add,
            addR: loadRiver,
            clear: clear,
            showOrHide: showOrHide
        };
    };
});
