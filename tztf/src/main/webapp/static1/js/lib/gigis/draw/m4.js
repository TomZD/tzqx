define(function(require) {
	var Draw = require("./draw");
	//四类格式
	return OpenLayers.Class(Draw, {
		setRange: function(value, sort, max, min) {
			//是否有默认值
			if (!isNaN(value)) {
				this.value = value;
			}
			//最大边界值
			if (max != null) {
				this.maxValue = max;
			}
			//最小边界值
			if (min != null) {
				this.minValue = min;
			}
			//是否有默认排序
			if (sort != null) {
				this.sort = sort;
			}
		},
		compareRange: function(value) {
			if (isNaN(this.value)) {
				return true
			} else {
				if (this.sort) {
					return (value - this.value <= 0) || (this.maxValue != null && this.maxValue == this.value)
				} else {
					return (value - this.value >= 0) || (this.minValue != null && this.minValue == this.value)
				}
			}
		},
		changeRange: function(value) {
			//抽象方法,子类实现
		},
		CLASS_NAME: "Gi.M4"
	});
});