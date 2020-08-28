//可移动的标注点
define(function() {
	OpenLayers.Control.DragMarker = OpenLayers.Class(OpenLayers.Control, {
		/**
		 * 标注被选中时的触发事件
		 */
		onStart: function() {},
		/**
		 * 标注移动时的触发事件
		 * @return {[type]} [description]
		 */
		onDrag: function() {},
		/**
		 * 鼠标done事件时的触发事件
		 * @return {[type]} [description]
		 */
		onComplete: function() {},
		/**
		 * 鼠标进入标注时的触发事件
		 * @return {[type]} [description]
		 */
		onEnter: function() {},
		/**
		 * 鼠标离开标注时的触发事件
		 * @return {[type]} [description]
		 */
		onLeave: function() {},
		/**
		 * 鼠标弹起时的触发事件
		 * @return {[type]} [description]
		 */
		onStop: function() {},
		/**
		 * 标注被点击时的触发事件
		 */
		onClick: function() {},
		/**
		 * 自动激活控件
		 * @type {Boolean}
		 */
		autoActivate: true,
		/**
		 * 初始化
		 * @param  {object} marker  标注
		 * @param  {object} options 配置
		 */
		initialize: function(marker, options) {
			OpenLayers.Control.prototype.initialize.apply(this, [options]);

			marker.events = new OpenLayers.Events(this, marker.icon.imageDiv, null, true); //重新注册事件，增加冒泡过程
			this.marker = marker;

			this.handler = new OpenLayers.Handler.Drag(
				this, {
					down: this.downMap,
					move: this.moveMap,
					up: this.upMap,
					out: this.upMap,
					done: this.doneMap
				}, {
					documentDrag: true
				}
			);
		},
		/**
		 * 激活控件
		 * @return {boolean} 是否激活
		 */
		activate: function() {
			this.marker.events.register("mousedown", this, this.downMarker);
			this.marker.events.register("mouseup", this, this.upMarker);
			// this.marker.events.register("mouseover", this, this.enterMarker);
			// this.marker.events.register("mouseout", this, this.leaveMarker);

			return OpenLayers.Control.prototype.activate.apply(this, arguments);
		},
		/**
		 * 禁用控件
		 * @return {boolean} 是否激活
		 */
		deactivate: function() {
			this.marker.events.unregister("mousedown", this, this.downMarker);
			this.marker.events.unregister("mouseup", this, this.upMarker);
			// this.marker.events.unregister("mouseover", this, this.enterMarker);
			// this.marker.events.unregister("mouseout", this, this.leaveMarker);

			// the return from the handlers is unimportant in this case
			OpenLayers.Element.removeClass(
				this.marker.map.viewPortDiv, this.displayClass + "Over"
			);
			return OpenLayers.Control.prototype.deactivate.apply(this, arguments);
		},
		/**
		 * 鼠标经过标注
		 */
		enterMarker: function() {
			// if (!this.isEnter) {
			// 	console.log("enterMarker");
			// 	this.isEnter = true;
			// 	this.onEnter();
			// }
		},
		/**
		 * 鼠标离开标注
		 */
		leaveMarker: function() {
			// if (this.isEnter) {
			// 	console.log("leaveMarker");
			// 	this.isEnter = false;
			// 	this.onLeave();
			// }
		},
		/**
		 * 按下标注 -- 激活地图的drag事件
		 */
		downMarker: function(evt) {
			if (!this.isActivate) {
				this.isActivate = true; //激活状态
				this.handler.activate();
			}
		},
		/**
		 * 点击或者放开标注
		 */
		upMarker: function(evt) {
			//不在移动，可以认为是一次点击事件的完成状态。
			if (!this.moving) {
				this.handler.deactivate(); //禁用
				this.isActivate = false;
				this.onClick(this.marker);
				OpenLayers.Event.stop(evt, true); //阻止冒泡
			}
		},
		/**
		 * 按下地图
		 * @param  {object} pixel 当前位置 
		 * 注意：返回值一般是OpenLayers.Pixel实例，如果鼠标离开map容器或者document可能返回一个包含x,y属性的对象
		 */
		downMap: function(pixel) {
			if (this.isActivate) {
				// console.log("downMap")

				//计算出初始移动偏差值
				var position = this.marker.icon.px;
				this.offset = {
					x: pixel.x - position.x,
					y: pixel.y - position.y
				};

				this.onStart();
			}
		},
		/**
		 * 在地图上移动
		 * @param  {object} pixel 当前位置 
		 * 注意：同上
		 */
		moveMap: function(pixel) {
			if (this.isActivate) {
				// console.log("moveMap")
				var position = this.marker.map.layerContainerOriginPx; //地图容器的偏移量
				var x = pixel.x  - this.offset.x;
				var y = pixel.y  - this.offset.y;
				this.marker.moveTo(new OpenLayers.Pixel(x, y));
				this.moving = true;
				this.onDrag();
			}
		},
		/**
		 * 一次有效移动完成时
		 * @return {[type]} [description]
		 */
		doneMap: function(pixel) {
			if (this.isActivate) {
				this.onComplete(this.marker);
			}
		},
		/**
		 * 松开鼠标
		 * @param  {object} pixel 当前位置 注意：同上
		 */
		upMap: function(pixel) {
			if (this.isActivate) {
				// console.log("upMap")
				this.isActivate = false;
				this.moving = false;
				this.handler.deactivate();
				this.onStop(this.marker);
			}
		},
		/**
		 * 注销
		 */
		destroy: function() {
			this.isActivate = false;
			this.marker = null;
			this.deactivate();
			OpenLayers.Control.prototype.destroy.apply(this, []);
		},
		CLASS_NAME: "OpenLayers.Control.DragFeature"
	});

	return function(marker, options) {
		if (!marker) return;
		var ctr = new OpenLayers.Control.DragMarker(marker, options);
		marker.map.addControl(ctr);
		return ctr;
	};
});