// 动画
define(function(require) {
	//线性匀速运动
	var linear = OpenLayers.Class({
		initialize: function(lonlats, frames, second, moveCallback, turnCallback, stopCallback) {
			if (lonlats && lonlats.length > 1) {
				this.frames = parseInt(frames) || 1; //动画帧数
				this.second = second * 1000 || 20; //动画间隔时间

				this.timer = null; //定时器
				this.lonlats = lonlats; //关键位置集合
				this.index = 0;
				this.maxNum = lonlats.length; //关键位置长度

				this.turnCallback = turnCallback; //转折点位置回调函数(包括开始点，不包含结束点)
				this.stopCallback = stopCallback; //结束回调函数
				this.moveCallback = moveCallback; //运动轨迹回调函数

				this.run();
			}
		},
		destroy: function() {
			this.stop();
			this.lonlats = null;
			this.turnCallback = null;
			this.stopCallback = null;
			this.moveCallback = null;
		},
		_move: function() {
			++this.curFrames;
			if (this.curFrames < this.frames) {
				typeof this.moveCallback == "function" && this.moveCallback(
					this.start.lon + this.lonIncrease * this.curFrames,
					this.start.lat + this.latIncrease * this.curFrames
				);
			} else if (this.curFrames == this.frames) {
				typeof this.moveCallback == "function" && this.moveCallback(
					this.end.lon,
					this.end.lat
				);
				this.stop(); //停止此次动画
				this.run(); //开始下次新的动画
			}
		},
		run: function() {
			//是否到达终点
			if (this.index < this.maxNum - 1) {
				var that = this;
				if (!this.running) {
					this.running = true;
					//是否中停
					if (!this.curFrames || this.curFrames >= this.frames) {
						this.start = this.lonlats[this.index];
						this.end = this.lonlats[this.index + 1];

						typeof this.turnCallback == "function" && this.turnCallback(this.start.lon, this.start.lat, this.index);

						this.lonIncrease = (this.end.lon - this.start.lon) / this.frames; //经度增量
						this.latIncrease = (this.end.lat - this.start.lat) / this.frames; //纬度增量

						this.index++; //当前节点
						this.curFrames = 0; //当前帧
					}

					this.timer = setInterval(function() {
						that._move();
					}, this.second);
				}
			} else {
				this.running = false;
				typeof this.stopCallback == "function" && this.stopCallback(this.end.lon, this.end.lat);
			}
		},
		stop: function() {
			this.running = false;
			this.timer && clearInterval(this.timer);
		}
	});

	return {
		linear: linear
	};
});