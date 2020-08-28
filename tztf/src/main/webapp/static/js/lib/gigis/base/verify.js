//验证库
define(function() {
	//删除两头的空白,返回字符串
	function trim(content) {
		if (isString(content)) {
			var m = (content + "").match(/^\s*(\S+(\s+\S+)*)\s*$/);
			return (m == null) ? "" : m[1];
		} else {
			return "";
		}
	}
	//有效字符串：默认包括数字，严格验证时不包含
	function isString(content, isStrict) {
		return typeof content == "string" || (!isStrict && typeof content == "number")
	}
	//是否是空字符串
	function isEmpty(content) {
		return trim(content) == "";
	}
	//是否包含中文
	function isChinese() {
		return /[\u4e00-\u9fa5]+/.test(content);
	}
	//是否是是有效的数值，包括：数字型（不包括NaN），字符串型（可转换数字型的,比如:"123","   123   "）
	function isNumber(content) {
		return !isNaN(content - 0);
	}
	//验证是否是整数
	function isInteger(content, isStrict) {
		if (isStrict && typeof content != "number") return;
		return /[0-9]|(^[0-9]*]*$)/.test(content);
	}
	//验证是否是正整数
	function isPositiveInteger(content, isStrict) {
		if (isStrict && typeof content != "number") return;
		return /^[1-9]+[0-9]*]*$/.test(content);
	}

	var verify = {
		"string": isString,
		"empty": isEmpty,
		"chinese": isChinese,
		"number": isNumber,
		"int":isInteger,
		"positiveInteger": isPositiveInteger
	};
	//content 被验证内容
	//type 验证类型
	//options 配置
	return function(content, type, options) {
		var fn = verify[type];
		return fn && fn(content, options);
	};
});