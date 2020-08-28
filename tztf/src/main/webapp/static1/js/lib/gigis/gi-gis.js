/**
 * ------------------------------------------
 * @description 基于openlayers的Web GIS 客户端类库包
 * @update 2014-9-21
 * @author movinginfo ued team
 * ------------------------------------------
 */
define(function(require) {
	var Core = require("./core");
	var rPlugins = require("./plugins/resources");
	var rControls = require("./control/resources");
	var rLayers = require("./layer/resources");

	OpenLayers.Util.extend(Core.prototype, rPlugins);

	function GIS(id, from, options) {
		return new Core(id, from, options)
	}
	GIS.version = "1.1.0_alpha";
	GIS.loadPlugins = function(plugins) {
		if (typeof plugins == "object") {
			for (var a in plugins) {
				if (!Core.prototype[a]) {
					Core.prototype[a] = plugin;
				} else {
					OpenLayers.Console.log("插件名(" + a + ")重名!")
				}
			}
		}
	};
	GIS.loadControls = function(controls) {
		if (typeof controls == "object") {
			for (var a in controls) {
				if (!rControls[a]) {
					rControls[a] = controls[a];
				} else {
					OpenLayers.Console.log("控件名(" + a + ")重名!")
				}
			}
		}
	};
	GIS.loadLayers = function(layers) {
		if (typeof layers == "object") {
			for (var a in layers) {
				if (!rLayers[a]) {
					rLayers[a] = layers[a];
				} else {
					OpenLayers.Console.log("底图组(" + a + ")重名!")
				}
			}
		}
	};

	return GIS;
});