define(function(require) {
	var Bing = OpenLayers.Class(OpenLayers.Layer.Bing, {
		//原地址不再支持中文地图,只支持交通图
		loadMetadata: function() {
			this._callbackId = "_callback_" + this.id.replace(/\./g, "_");
			window[this._callbackId] = OpenLayers.Function.bind(
				OpenLayers.Layer.Bing.processMetadata, this
			);
			var params = OpenLayers.Util.applyDefaults({
				key: this.key,
				jsonp: this._callbackId,
				include: "ImageryProviders"
			}, this.metadataParams);
			var url = this.protocol + "//dev.ditu.live.com/REST/v1/Imagery/Metadata/" +
				this.type + "?" + OpenLayers.Util.getParameterString(params);
			var script = document.createElement("script");
			script.type = "text/javascript";
			script.src = url;
			script.id = this._callbackId;
			document.getElementsByTagName("head")[0].appendChild(script);
		}
	});
	return function(wrapDateLine) {
		return [new Bing({
			wrapDateLine:wrapDateLine,
			name: "traffic",
			alias: "交通图",
			key: 'Ak-dzM4wZjSqTlzveKz5u0d4IQ4bRzVI309GxmkgSVr1ewS6iPSrOvOKhA-CJlm3',
			tileOptions: {
				crossOriginKeyword: ''
			}
		})]
	}
});