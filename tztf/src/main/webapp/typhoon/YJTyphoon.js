//当前台风数据
var TYdefaultID = "";//默认加载的台风数据
var actPathObj = null;//当前的台风详情数据
var TyphoonList = [];//所有台风列表
var voffset = "";//时间滚轴初始位置
var IsfirstLoad = true;//是否第一次加载
var TyphoonSelect = [];//右上角显示选择的台风信息集合
//台风脚本定义
$(function () {
	
	//获取预警信号
	getWarnList();
	//定义时间轴宽度
	var WindW = $("#map").width();
	$(".typhoonTimeLine").width(WindW - 233 + "px");
	//生成时间轴
	confirmTimeLine();
	//时间绑定
	timeBind();
	//年份鼠标悬停事件绑定，实现年份选择框的淡入淡出
	$(".YearSelect").hover(function () {
		$("#bindYear").fadeIn(200);
	}, function () {
		$("#bindYear").fadeOut(200);
	});
	//年份选择事件绑定
	$("#bindYear li").click(function () {
		var newYear = $(this).attr("data-id");
		var nowbindyear = $("span.bindYear").html().substr(0, 4);
		$("span.bindYear").html(newYear + "年：");
		$("#bindYear").fadeOut(200);
		if (nowbindyear == newYear) { //判断新选择年份是否为当前年份
			return;
		} else {
			// getTyListAll(newYear, null);
			$("#TYname").attr("value", ""); //清空搜索框，原先内容为上一年份数据
		}
	});
	//获取当前年份的台风列表
	var CurrentYear = (new Date()).getFullYear();
	getTyListAll(CurrentYear, null);
	//时间轴滚动
	$("#arrowLastYear").click(function () {//向前
		var conW = $("#TimeS").find(".myscale").width();
		voffset -= conW;
		var w = $('.timeLine #TimeS').width();
		$('.TyShowBlock').animate({ "scrollLeft": voffset });
		if (voffset > 0) {
			$('.TyShowBlock').animate({ "scrollLeft": voffset });
		} else {
			$('.TyShowBlock').animate({ "scrollLeft": 0 });
			voffset = 0;
			$(".warnTips .warnTxt").html("已经到底了！");
			$(".warnTips").css({ "left": "40px", "right": "" });
			$(".warnTips").fadeIn(600);
			var time = setTimeout(function () {
				$(".warnTips").fadeOut(600);
			}, 3000);
		}
	});
	$("#arrowNextYear").click(function () {//向后
		var conW = $("#TimeS").find(".myscale").width();
		var outsideW = $(".TyShowBlock").width();
		voffset += conW;
		var w = $('.timeLine #TimeS').width();
		$('.TyShowBlock').animate({ "scrollLeft": voffset });
		if (voffset > w - outsideW) {
			voffset = w - outsideW; //已经是最后一个
			$('.TyShowBlock').animate({ "scrollLeft": voffset });
			$(".warnTips").css({ "left": "", "right": "40px" });
			$(".warnTips .warnTxt").html("已经到底了！");
			$(".warnTips").fadeIn(600);
			var time = setTimeout(function () {
				$(".warnTips").fadeOut(600);
			}, 3000);
		}
	});
	// 显示、隐藏图例
	$(".msgTyDiv .disTitle").click(function () {
		var text = $(this).html();
		if (text == "隐藏") {
			$(".msgTyphoon").fadeOut(200);
			$(this).html("<span class='legendS'></span>");
			$(".msgTyDiv").addClass("hidebg");
		} else {
			$(".msgTyphoon").fadeIn(200);
			$(this).html("隐藏");
			$(".msgTyDiv").removeClass("hidebg");
		}
	});
	//点击搜索按钮
	$("#Tysearch").click(function () {
		var searchP = $("#TYname").attr("value");
		var searchId = $("#TYname").attr("data-id");
		$(".typhoonBox[data-id='" + searchId + "']").click(); //等同点击台风列表加载该台风
		//$(".typhoonBox[data-id='" + searchId + "']").addClass("current").siblings().removeClass("current");
		var startMonth = $(".typhoonBox[data-id='" + searchId + "']").attr("data-month");
		timeLinePos(startMonth); //时间轴定位到选中台风
	});
	//模糊搜索
	$("#TYname").keyup(function () {
		$("#TYnameList").fadeIn(200);
		var nowText = $(this).attr("value");
		var listArr = $("#TYnameList li");
		$.each(listArr, function (i, item) {
			var ObjTxt = $(item).html();
			if (ObjTxt.indexOf(nowText) > -1) {
				$(item).show();
			} else {
				$(item).hide();
			}
		});
	});
	//搜索框获取焦点后显示下拉
	$("#TYname").focus(function () {
		$("#TYnameList").fadeIn(200);
	})
	//搜索框失去焦点后隐藏下拉
	$("#TYname").blur(function () {
		$("#TYnameList").fadeOut(200);
	});
	
});

//-------------------------方法定义-------------------------------------------//
//获取预警信号
function getWarnList() {
	$.ajax({
		async : true,
		type : "POST",
		url : "/sen/menu/warnList",
		data : {},
		dataType : "json",
		success : function(d) {
			var arr = new Array();
			for(var i=0;i<d.length;i++){
				if(d[i].title.substr(0,6)=='杭州市气象台'){
					arr.push(d[i]);
				}
			}
			var text = "";
			for (var i = 0; i < d.length; i++) {
				text +="<div class=\"carousel_con\"><img src=\"/hztq/r/cms/static/images/ico-warn/"    
					+ d[i].code
					+ ".jpg\" class=\"carousel_btn\" />"	
					+"<span class=\"carousel_txt\">" 
					+ d[i].title +"</span></div>";
			}
			if (text != "") {
				$('.m-carousel marquee').append(text);
			} 
		},
		error : function(d) {
			// alert("wrong");
		}
	});
}

//时间绑定
function timeBind() {
	var year = (new Date()).getFullYear();
	$(".YearSelect .bindYear").html(year + "年：");
	var yearStr = "";
	for (var i = year; i >= 2004; i--) {
		yearStr += " <li data-id='" + i + "'>" + i + "</li>";
	}
	$('#bindYear').html(yearStr);
}

//获取台风列表
function getTyListAll(year, callback) {
	$.ajax({
//		url: "/hztq/data/typhoon/GetHistoryTyphoon_" + year + ".json",
		url: "/sen/menu/typhoonYear",
		type: "get",
		dataType: "json",
		data: { "year": year },
		timeout: 30000, //超时时间：30秒
		beforeSend: function () { //请求成功前触发的局部事件
			$(".ajaxnotice").html("加载中...");
			$(".ajaxnotice").show();
			setajaxNoticeCenter();//居中
			$(".TyShowBlock .timelineBg").html("");
		},
		success: function (item) {
			if (item != null && typeof (item) == "object") {
				$(".ajaxnotice").fadeOut(100);
				//第一次请求台风数据时调用
				if (IsfirstLoad) {
					var objNo = item[0].no;
					TYdefaultID = objNo;//默认加载的台风ID
					TyphoonLoadCur(objNo, null);//请求默认台风详情并加载台风地图
				}
				TyphoonList = item;
				for (var i = 0; i < TyphoonList.length; i++) {//过滤掉台风名为null的数组
					if (TyphoonList[i].nameCn == null) {
						TyphoonList.splice(i, 1);
					}
				}
				
				dealTyphoonList(TyphoonList);
				if (year != "" && typeof callback == "function") {
					//年份调用
					callback(TyphoonList);
				}
			} else {
				if (IsfirstLoad) {
					IsfirstLoad = false;
					actPathObj = null;
					seajs.use("YJtyphoonMap");//第一次加载地图且请求的台风为空的情况
				}
				//resetSelect();//初始化右侧台风信息列表
				$(".ajaxnotice").html("当前年份台风数据为空，请重试或切换年份查看！");
				setajaxNoticeCenter();//居中
				console.log("YJtyphoon.js中getTyListAll未获取到台风数据");
				return;
			}
		},
		error: function (XMLHttpRequest, textStatus, errorThrown) {
			if (textStatus == "timeout") {
				$(".ajaxnotice").html("请求超时，请重试或切换年份查看...");
				$(".ajaxnotice").show();
				setajaxNoticeCenter();//居中
				return;
			}
			$(".ajaxnotice").html("加载失败...");
			$(".ajaxnotice").show();
			setajaxNoticeCenter();//居中
		}
	});
}

//获取当前台风信息详情
function TyphoonLoadCur(currentNo, callback) {
	$.ajax({
//		url: "/hztq/data/typhoon/GetTyphoonDetail_"  +currentNo + ".json",
		url: "/sen/menu/typhoonMonth",
		type: "get",
		dataType: "json",
		data: { "typhoonNo": currentNo },
		success: function (typhoonData) {
			if (typhoonData == "" || typhoonData == null) {
				console.log("YJtyphoon.js中TyphoonLoadCur未获取到当前台风数据");
				return;
			}
			actPathObj = typhoonData;
			if (IsfirstLoad) {
				IsfirstLoad = false;
				seajs.use("YJtyphoonMap");//第一次加载地图且请求的台风actPathObj不为空的情况
			}
			var Tyname = actPathObj.nameCn;
			addTyphoonBox(currentNo, Tyname);//右侧添加当前台风
			$(".mapLoading").fadeOut(200);//加载完毕隐藏加载中图层
			$(".msgTyDiv").fadeIn(200);
			if (currentNo != "" && typeof callback == "function") {
				//年份调用
				callback(actPathObj);
			}
		}
	});
}

/**
 * 对指定年份的台风列表数据进行处理
 * @param TyphoneByYear 某一年的台风数据列表
 */
function dealTyphoonList(TyphoneByYear) {
	var TYnameHtml = "";
	var timeLineTY = "";
	var colors = ["#bc43d7", "#fcb321", "#f7292c", "#47c4ff"];
	var offTop = ["10px", "30px", "50px", "70px"];
	var num = TyphoneByYear.length;
	var finalMonth = "";
	for (var i = num - 1, j = 0; i >= 0, j < num; i--, j++) {//源数据为时间由近及远，需重新排序
		var item = TyphoneByYear[i];
		var id = item.no;
		var timescale, startMonth, startDay;
		if (item.livePaths.length != 0 && item.livePaths) {//考虑livePaths长度为0的情况
			timescale = getTimeScale(id, item.livePaths);//获得时间跨度,计算台风容器的宽度
			var jsonItem = item.livePaths[0];
			startMonth = jsonItem.nowString.substr(0, 2);//获得所属的月份
			startDay = jsonItem.nowString.substr(2, 2);//获得所属的日期
		}
		startMonth = parseInt(startMonth);
		startDay = parseInt(startDay);
		finalMonth = startMonth;
		var monthOffset = $("#TimeS").find(".myscale").width() * (startMonth - 1);//计算月份偏移
		var dayOffset = $("#TimeS").find(".scaleIndex_" + startMonth).width() / 30 * startDay;//计算日期偏移
		var styleLeft = monthOffset + dayOffset + "px";
		var stylewidth = timescale / 30 * $("#TimeS").find(".scaleIndex_" + startMonth).width();
		var finalPl = styleLeft + stylewidth;
		var Tyindex = id.slice(4, id.length);
		var TYname = Tyindex + "号" + item.nameCn;
		stylewidth = "auto";//解决连成线的问题
		
		TYnameHtml += "<li data-id='" + id + "'>" + TYname + "</li>";
		var index = j % colors.length;
		var styleC = colors[index];
		var styleTop = offTop[index];
		timeLineTY += "<div data-id='" + id + "' data-month='" + startMonth + "'class='typhoonBox TYBox_" + j + "' style='background:" + styleC + ";top:" + styleTop + ";left:" + styleLeft + ";width:" + stylewidth + "'>" + TYname + "</div>";
	}
	$("#TYnameList").html(TYnameHtml);//填充下拉框数据
	$(".TyShowBlock .timelineBg").html(timeLineTY);
	$(".TyShowBlock .timelineBg").width($("#TimeS").width());
	//台风列表选择功能
	$("#TYnameList li").click(function () {
		var SelText = $(this).html();
		var SelId = $(this).attr("data-id");
		$("#TYname").attr("value", SelId);
		document.getElementById("TYname").value = SelText;
		$("#TYname").attr("data-id", SelId);
		$("#TYnameList").fadeOut(200);
	});
	timeLinePos(finalMonth);//使时间轴最始定位到最新台风
}

//添加选择的台风
function addTyphoonBox(addID, addName) {
	//判断数据集中是否存在这个台风信息
	//如果存在则选中这个台风
	//如果不存在，则添加台风信息
	var isCurrent = -1;//默认没有选中的台风信息
	if (TyphoonSelect && TyphoonSelect.length > 0) {
		for (var i = 0; i < TyphoonSelect.length; i++) {
			if (TyphoonSelect[i]) {
				if (TyphoonSelect[i].indexOf(addName) > 0) {
					isCurrent = i; //获得已有的台风信息的位置
					i = TyphoonSelect.length;//强制结束循环
				}
			}
			//如果最后一个还是没有找到已存在的台风信息，则添加
			if (isCurrent < 0 && i == TyphoonSelect.length - 1) {
				TyphoonSelect.push(addID + "," + addName);
				isCurrent = i + 1; //标记新增的台风信息为选中
			}
		}
	} else {
		TyphoonSelect.push(addID + "," + addName);
		isCurrent = 0;//标记新增的台风信息为选中
	}
	
	//先清空原有的右上角展示图层
	$("#selectTyObj").html("");
	var tyboxHtml = "";
	
	//将台风信息显示到页面上
	for (var i = 0; i < TyphoonSelect.length; i++) {
		if (TyphoonSelect[i]) {
			var id = TyphoonSelect[i].split(',')[0];
			var name = TyphoonSelect[i].split(',')[1];
			//如果是标记选中的台风，则样式加上current
			if (isCurrent == i) {
				tyboxHtml += "<div class='windBox current' data-id='" + id + "'> <span class='windName'>" + name + "</span><span class='formateTime'>" + id + "</span><i class='closeBtn'></i></div>";
			} else {
				tyboxHtml += "<div class='windBox' data-id='" + id + "'> <span class='windName'>" + name + "</span><span class='formateTime'>" + id + "</span><i class='closeBtn'></i></div>";
			}
		}
	}
	$("#selectTyObj").append(tyboxHtml);
}

//删除所选择台风
function delectcurTy(delId) {
	//循环遍历数据集，找到要删除的索引，并清空
	for (var i = 0; i < TyphoonSelect.length; i++) {
		if (TyphoonSelect[i]) {
			if (TyphoonSelect[i].split(',')[0] == delId) {
				//delete TyphoonSelect[i];//这种删除方法会保留原有索引位置
				TyphoonSelect.splice(i, 1);
				i = TyphoonSelect.length;//强制结束循环
			}
		}
	}
	if (TyphoonSelect.length == 0) {
		$("#selectTyObj").html("");//清空台风卡片窗口
		$("#BindTyname").html("");//没有台风卡片时清空标题
		$("#TydetailTab").html("");//没有台风卡片时清空时次信息
		// 2017-2-15 pancb修改 没有台风卡片时清空地图上未关闭的台风信息弹窗
		$("#popdivclose").click();
		return;
	}
	// 2017-2-15 pancb修改 所删除的台风有未关闭的台风信息弹窗时关闭该弹窗
	var popid = $("#popdivclose").attr("data-val");
	if (delId == popid) {
		$("#popdivclose").click();
	}
	var tyarr = $("#selectTyObj .windBox");
	$.each(tyarr, function (i, item) {
		var indexId = $(item).attr("data-id");
		if (indexId == delId) {
			$(item).remove();
		}
	});
	var hasCurrent = $("#selectTyObj").find(".windBox.current");
	if (hasCurrent.length > 0) {
		return;
	} else {//删除后若没有选中项则默认最后一个为选中项
	var num = $("#selectTyObj").find(".windBox").length;
		$("#selectTyObj").find(".windBox").eq(num - 1).click();
		var newSelctId = $("#selectTyObj").find(".windBox").eq(num - 1).attr("data-id");
		$(".timelineBg").find(".typhoonBox[data-id=" + newSelctId + "]").addClass("current").siblings().removeClass("current");
		var startTime = $(".timelineBg").find(".typhoonBox[data-id=" + newSelctId + "]").attr("data-month");
		//时间轴定位
		if (startTime) {//保证时间轴在有台风对象时滚动
			timeLinePos(startTime);
		}
	}
}

//重置右侧台风选择
function resetSelect() {
	$("#selectTyObj").empty();
	$("#TydetailTab").empty();
	$(".mapLoading").fadeIn(200);//加载完毕隐藏加载中图层 
}

//右侧对应的当前选中的台风数据列表
function rightTyList(showData, id, name) {//需要按时间分组
	$("#BindTyname").html(name + id);//绑定标题
	var detailTabHtml = "";
	var sortByDate = [];
	$.each(showData, function (i, item) {
		var timeStr = item.time;
		var temp = timeStr.substr(0, 4);
		sortByDate.push(temp);
	});
	var Dategroup = getUnique(sortByDate);
	$.each(Dategroup, function (i, item) {
		var Datetime = item.substr(0, 2) + "." + item.substr(2, 2);
		detailTabHtml += "<tr><td colspan='4'><span class='timeBind'>" + Datetime + "</span></td></tr>";
		for (var j = 0; j < showData.length; j++) {
			var timeStr = showData[j].time;
			var temp = timeStr.substr(0, 4);
			var Htime = timeStr.substr(4, 2) + "时";
			if (temp == item) {
				detailTabHtml += "<tr data-id='" + id + "&" + timeStr + "'><td><em class='trBg'></em><span class='bg01'>" + Htime + "</span></td><td><span class='bg02'>" + showData[j].pressure + "</span></td><td><span class='bg02'>" + showData[j].windPower + "</span></td><td><span class='bg02'>" + showData[j].windVelocity + "</span></td></tr>";
			}
		}
	});
	$("#TydetailTab").html(detailTabHtml);
	//计算滚动条高度后去掉浏览器自带的滚动条
//	$('.scroller_block').scroll_absolute({
//		arrows: false
//	});
	ListHoverChange();//鼠标移上去事件绑定
	ListClickChange();//鼠标点击联动效果
}

//排重分组
function getUnique(data) {
	tempArray = data.slice(0);//复制数组到临时数组
	for (var i = 0; i < tempArray.length; i++) {
		for (var j = i + 1; j < tempArray.length;) {
			//后面的元素若和待比较的相同，则删除并计数；
			//删除后，后面的元素会自动提前，所以指针j不移动
			if (tempArray[j] == tempArray[i]) {
				tempArray.splice(j, 1);
			}
			else {
				j++;
			}
			//不同，则指针移动
		}
	}
	return tempArray;
}
	
//列表移上去背景
function ListHoverChange() {
	//右侧菜单鼠标移上去效果
	$("#TydetailTab tr").hover(function () {
		var bgW = $(this).width();
		$(this).find("em").animate({ "width": bgW + "px" });
	}, function () {
		$(this).find("em").animate({ "width": "0px" });
	});
}

//右侧列表点击与地图弹窗联动效果start
function ListClickChange() {
	$("#TydetailTab tr").on("click", function () {
		$(this).addClass("current").siblings().removeClass("current");
		var tag = $(this).attr("data-id").split("&");
		var tydetailbox = gisobject.typhoonDetail.typhoonBox.typhoons;//为数组显示页面加载的台风集合，gismap为YJtyphoonMap.js上定义的变量
		var tagnameid = tag[0];
		var tagtimestr = tag[1];
		$.each(tydetailbox, function (i, item) {
			if (tagnameid == item.id) {//遍历查找id相等的台风数据
				var pointarr = item.actual.points;
				for (var j = 0; j < pointarr.length; j++) {
					if (pointarr[j].data.data.time == tagtimestr) {//遍历查找时间相同的数据
						var typeObject = pointarr[j].data.data;
						formpopdiv(typeObject);//在地图上生成弹窗,formpopdiv为YJtyphoonMap.js上定义的方法
					}
				}
			}
		});
	});
}

//时间轴逻辑处理--------------------------start----------------------------
function getTimeScale(id, TimeObj) {
	var year = id.substr(0, 4);
	var Tlength = TimeObj.length;
	var startStr = TimeObj[0].nowString;
	var endStr = TimeObj[Tlength - 1].nowString;
	var startTime = year + "-" + startStr.substr(0, 2) + "-" + startStr.substr(2, 2);
	var endTime = year + "-" + endStr.substr(0, 2) + "-" + endStr.substr(2, 2);
	var timeDifference = DateDiff(startTime, endTime);
	return timeDifference;
}

//计算时间差
function DateDiff(sDate1, sDate2) {//sDate1和sDate2是2006-12-18格式
	var aDate, oDate1, oDate2, iDays;
	aDate = sDate1.split("-");
	oDate1 = new Date(aDate[1] + '-' + aDate[2] + '-' + aDate[0]);//转换为12-18-2006格式
	aDate = sDate2.split("-");
	oDate2 = new Date(aDate[1] + '-' + aDate[2] + '-' + aDate[0]);
	iDays = parseInt(Math.abs(oDate1 - oDate2) / 1000 / 60 / 60 / 24);//把相差的毫秒数转换为天数
	return iDays;
}

//生成时间轴
function confirmTimeLine() {
	var timeScale = "";
	var widthOne = $(".TyShowBlock").width() / 3;
	for (var i = 1; i <= 12; i++) {
		timeScale += "<span class='myscale scaleIndex_" + i + "' style='width:" + widthOne + "px'>" + i + "月<i class='tag'></i></span>";
	}
	var Timewidth = $(".TyShowBlock").width() / 3 * 12 ;
	$("#Timescale").html("<div id='TimeS' style='width:" + Timewidth + "px'>" + timeScale + "</div>");
}

//防止冒泡事件
function stopPropagation(e) {
	e = e || window.event;
	if (e.stopPropagation) { //W3C阻止冒泡方法
		e.stopPropagation();
	} else {
		e.cancelBubble = true; //IE阻止冒泡方法
	}
}

//时间轴定位
function timeLinePos(startMonth) {
	startMonth = startMonth - 1;
	var nearOffset = startMonth * $("#TimeS").find(".myscale").width();
	var maxOffset = $("#TimeS").width() - $(".TyShowBlock").width();
	if (nearOffset >= maxOffset) {
		nearOffset = maxOffset;
	}
	voffset = nearOffset;//设置时间滚轴初始位置
	$('.TyShowBlock').animate({ "scrollLeft": voffset });
}

//台风提示信息居中显示
function setajaxNoticeCenter() {
	var noticeW = $(".ajaxnotice").width();
	var containW = $(".typhoonTimeLine").width();
	var centerpos = (containW - noticeW) / 2;
	$(".ajaxnotice").animate({ "left": centerpos + "px" });
}
