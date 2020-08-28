define(function(require) {
	var M4 = require("../draw/m4");
	/*
		四类格式数据-画多点
	 */
	return OpenLayers.Class(M4, {
		data: null, //传入的数据
		groups: null, //集合
		type: "line", //类型，line/polygon。默认：line
		/*
			获取线集合
			@param arr 传入的数据
			@return obj 集合
		 */
		getLines: function(arr) {
			if (arr && arr.length) {
				var group = [];
				var displayProjection = this.map.displayProjection;
				var getProjection = this.map.getProjection();
				for (var i = 0, l = arr.length; i < l; i++) {
					var data = arr[i];
					for (var j = 0, ll = data.content.length; j < ll; j++) {
						var line = this.getLine(data.content[j]);
						line.transform(
							new OpenLayers.Projection(displayProjection),
							new OpenLayers.Projection(getProjection)
						);
						group.push({
							value: data.value,
							feature: new OpenLayers.Feature.Vector(line, null, data.style)
						});
					}
				}
				return group;
			}
		},
		/*
			获取多边形集合
			@param arr 传入的数据
			@return obj 集合
		 */
		getPolygons: function(arr) {
			if (arr && arr.length) {
				var groups = [];
				var displayProjection = this.map.displayProjection;
				var getProjection = this.map.getProjection();
				for (var i = 0, l = arr.length; i < l; i++) {
					var ring = arr[i];
					var p = this.getPolygon(ring.group);
					p.transform(
						new OpenLayers.Projection(displayProjection),
						new OpenLayers.Projection(getProjection)
					);
					groups[i] = {
						value: ring.value,
						feature: new OpenLayers.Feature.Vector(p, null, ring.style)
					};
				}
				return groups;
			}
		},
		/*
			修改范围
			@param value 中间值
			@param sort 排序
		 */
		changeRange: function(value, sort) {
			this.setRange(value, sort); //更新范围

			if (this.groups && this.groups.length) {
				for (var i = 0, l = this.groups.length; i < l; i++) {
					var p = this.groups[i];
					var el = OpenLayers.Util.getElement(p.feature.geometry.id);
					el && (el.style.display = this.compareRange(p.value) ? "" : "none");
				}
			}
		},
		/*
			重绘制
		 */
		redraw: function() {
			M4.prototype.redraw.apply(this);

			if (this.groups) return; //如果存在了，不执行以下内容

			this.groups = this.type == "line" ? this.getLines(this.data) : this.getPolygons(this.data);
			if (!this.groups) return; //如果转化后依然不存在，也不执行以下内容

			var arr = [];
			for (var i = 0, l = this.groups.length; i < l; i++) {
				arr.push(this.groups[i].feature)
			}
			this.addFeatures(arr);
			this.changeRange();
		},
		/*
			销毁
		 */
		destroy: function() {
			OpenLayers.Layer.Vector.prototype.destroy.apply(this, arguments);
			this.groups = null;
			this.data = null;
		},
		CLASS_NAME: "Gi.Drawing.Collection"
	});
});