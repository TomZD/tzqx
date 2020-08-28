//颜色转换 rgb 转 16进制
define(function() {
	var zero_fill_hex = function(num, digits) {
		var s = num.toString(16);
		while (s.length < digits)
			s = "0" + s;
		return s;
	};
	return function(rgb) {
		if (rgb && typeof rgb == "string") {
			if (rgb.charAt(0) == '#') {
				return rgb;
			}
			var ds = ("0" + rgb + "0").split(/\D+/); //兼容低版本ie
			var decimal = Number(ds[1]) * 65536 + Number(ds[2]) * 256 + Number(ds[3]);
			return "#" + zero_fill_hex(decimal, 6);
		}
		return
	};
});