define(function() {
	//专业绘图
	return OpenLayers.Class(OpenLayers.Layer.Vector, {
		//点
		getPoint: function(point) {
			return new OpenLayers.Geometry.Point(point.lon, point.lat)
		},
		//线
		//points 点集
		getLine: function(points) {
			if (points) {
				var arr = [];
				for (var i = 0, l = points.length; i < l; i++) {
					var point = this.getPoint(points[i]);
					arr.push(
						new OpenLayers.Geometry.Point(point.x, point.y)
					);
				}
				return new OpenLayers.Geometry.LineString(arr);
			}
		},
		//闭合环
		//points 点集
		getLinearRing: function(points) {
			if (points && points.length > 2) {
				var arr = [];
				for (var i = 0, l = points.length; i < l; i++) {
					arr.push(
						this.getPoint(points[i])
					);
				}
				return new OpenLayers.Geometry.LinearRing(arr);
			}
		},
		//多边形
		//lines 点集的分组
		getPolygon: function(lines) {
			if (lines && lines.length) {
				var arr = [];
				for (var i = 0, l = lines.length; i < l; i++) {
					arr.push(
						this.getLinearRing(lines[i])
					);
				}
				return new OpenLayers.Geometry.Polygon(arr);
			}
		},
		//扇形
		//origin 点 radius 各个方向影响距离 sides 多边形边数 rotation 起始角度 fixedAn扇形角度
		getSector: function(origin, radius, sides, rotation, fixedAn) {
			var angle = Math.PI * ((1 / sides) - (1 / 2));
			if (rotation) {
				angle += (rotation / 180) * Math.PI;
			}
			var rotatedAngle, x, y;
			var points = [];
			for (var i = 0; i < sides; ++i) {
				var an;
				if (!fixedAn) {
					an = i * ((360 - rotation) / 360); // 注: 主要是这里
				} else {
					an = i * (fixedAn / 360); // 注: 主要是这里
				}
				rotatedAngle = angle + (an * 2 * Math.PI / sides);
				x = origin.x + (radius * Math.cos(rotatedAngle));
				y = origin.y + (radius * Math.sin(rotatedAngle));
				points.push(new OpenLayers.Geometry.Point(x, y));
			}　　 //当rotation!=0时,要将圆心与扇形的起点和终点连接起来,构成扇形的两个边
			// if (fixedAn) {
			// 	points.push(origin);
			// }
			var ring = new OpenLayers.Geometry.LinearRing(points);
			return ring;
			//return new OpenLayers.Geometry.Polygon([ring]);
		},
		CLASS_NAME: "Gi.Drawing"
	});
});