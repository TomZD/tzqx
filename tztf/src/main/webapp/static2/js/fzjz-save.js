define(
		'fzjz-save.js',
		function(require, exports, module) {
			var GIS = require("lib/gigis/gi-gis");
			var Layer = require("components/map-layer");
			var timerAxis = null;
			var warningMarkerlayers;
			var maplayer;
			var popupTogether, featureTogether; // 聚合站点弹窗
			var renderer = OpenLayers.Util.getParameters(window.location.href).renderer;
			renderer = (renderer) ? [ renderer ]
					: OpenLayers.Layer.Vector.prototype.renderers;
			var lons;
			var lats;
			var zooms = 11;
			var mainCityArea;// 主城区
			var gisData = {}, gisMap = {};

			var tifangLayer = [];
			// 初始化gis：此处可以设置默认图层：baselayer
			var gis = GIS("map", "amap", {
				center : {
					lon : 121.095,
					lat : 28.610
				},
				zoom : 11,
				baselayer : "traffic",
				disableZoom : false, // 禁用滚轮事件
				documentDrag : false
			});

			// 图层切换
			gis.addControl("baseLayerSwitcher", -20, 20);
			// 图层缩放
			gis.addControl("zoom", -70, 140);
			gis.addControl("mousePosition", 85, 20, {
			prefix: "经度：",
			separator: " 纬度：",
			numDigits: 3
			});

			// 地图右上角圆形地图切换
			(function() {
				$(".olMapViewport")
						.append(
								'<img class="map-shaw" src="./static2/images/map_control.png">');
				// var mapControlAdd ="<a herf='javascript:void(0)'
				// class='olButton'>+</a>";
				// mapControlAdd += "<a herf='javascript:void(0)'
				// class='olButton'>-</a>"
				mapControlAdd = "<a herf='javascript:void(0)' class='olButton' id='clearSite' style='cursor: pointer'><img src='./static2/images/map_tool/clear.png' title='清空地图'></a>"
				mapControlAdd += "<a herf='javascript:void(0)' class='olButton' id='returnSite' style='cursor: pointer'><img src='./static2/images/map_tool/location.png' title='地图复原'></a>"
				mapControlAdd += "<a herf='javascript:void(0)' class='olButton' id='leadingOut' style='cursor: pointer'><img src='./static2/images/map_tool/export.png' title='导出'></a>"
				$(".olControlZoom").append(mapControlAdd);
				// 中心点横坐标
				var dotLeft = ($(".olControlZoom>a").width() / 2);
				// 中心点纵坐标
				var dotTop = -70 - ($(".olControlZoom>a").height() / 2);
				// 起始角度
				var stard = 0;
				// 半径
				var radius = 70;
				// 每一个BOX对应的角度;
				var avd = 360 / 12;
				// 每一个BOX对应的弧度;
				var ahd = avd * Math.PI / 180;
				$(".olControlZoom .olButton").each(function(index, element) {
					$(this).css({
						"left" : -dotLeft - Math.sin((ahd * index)) * radius,
						"top" : Math.cos((ahd * index)) * radius + dotTop
					});
				});
			})();
			// 地图点击事件
			gis.map.events.on({
				"click" : function() {
					$(".map-left>li").removeClass("click");
				},
				"zoomend" : function(e) {
                    darwMarker()
				}
			});

			gis.markerData = {};

			if (!maplayer) {
				maplayer = Layer(gis);
			}

			// 2019/6/10
			// 地图复原
			$(".olMapViewport").on("click", "#returnSite", function() {
				gis.setCenter(121.095, 28.610, 11);
			})

			//导出地图点击事件
			$(".olMapViewport").on("click", "#leadingOut", function() {
				//在这里获取所有地图上的图标位置和图标经纬度

				var data = gis.markerData;
				var list = [];
				for ( var j in data) {
					var dataN = data[j];
					if (dataN) {
						for ( var k in dataN) {
							var dataNN = dataN[k];
							for (var m = 0, mm = dataNN.length; m < mm; m++) {
								list.push(dataNN[m]);
							}
						}
					}
				}
				var bArr = [];
				for(var i=0;i<list.length;i++){
					var specTr = {};
					if(list[i].pointlist!=null){
						specTr.pointList = list[i].pointlist;
					}
					specTr.icon = list[i].icon;
					specTr.lat = list[i].latitude;
					specTr.lon = list[i].longitude;
					bArr.push(specTr);
				}
                var indexLayer = layer.load(2);
				// window.location.href="http://"+window.location.host+"/sen/rainstormFlood/getImage?parm="+JSON.stringify(bArr);
				$.ajax({
					type : "post",
					data:{'parm':JSON.stringify(bArr)},
					dataType:"json",
					url : "../sen/rainstormFlood/getImage2",
					success : function(data) {
						if(data.status ==1){
							var path = data.imagePath;
							path=path.replace(/\\/g,"/");
							window.location.href="http://"+window.location.host+"/sen/rainstormFlood/export?imagePath="+path;
						}
                        layer.close(indexLayer);
				   },error:function(data){
						alert("错误！");
                        layer.close(indexLayer);
					}
				})
			})


			$("body").on("click",".fzjz-save>li",function(){
				if($(this).attr("value")=="5" ||$(this).attr("value")=="6"){
					$(".m-popup").hide();
				}else{
					$(".m-popup").show();
				}
			});
			$(".fzjz-save")
					.on(
							"click",
							"li",
							function() {
								if ($(this).hasClass("decision_icon")) {

									var beginTime = document
											.getElementById("beginTime").innerText;
									var endTime = document
											.getElementById("endTime").innerText;
									var name = $("#fileName").val();
									var type = $(this).attr("data-type");
									getDecisionMaking(1, beginTime, endTime, name,
											type);
									$(".m-decision").show();
									$(".m-map").hide();
								} else {
									$(".m-decision").hide();
									$(".m-map").show();
								}
							})
			// 2019/7/5 决策材料点击事件
			$(".decision-detail table").on(
					"click",
					"td span",
					function() {
						$(".decision-detail table span").removeClass("on");
						$(this).addClass("on");
						var pdfUrl = $(this).attr("data-path");
						// var pdfUrl="/hydata/20190603预报发布稿（上午08时）.pdf";
						$(".m-pdf iframe").attr(
								"src",
								"./static2/js/lib/pdfjs/web/viewer.html?file="
										+ pdfUrl);
						$(".m-pdf").show();
						$(".decision-detail>i").show();
					})
			// $(".m-fenye ul").on("click","li",function(){
			// $(this).addClass("on").siblings().removeClass("on");
			// })
			//
			$(".decision-detail>i").click(function() {
				$(".m-pdf").hide();
				$(".decision-detail>i").hide();
			})

			$(document)
					.on(
							"change",
							".emV input",
							function(e) {
								$(".convergepop>i").click();
								var value = $(this).parents(".meau_erji")
										.parent().val();
								var type = $(this).parents(".emV").attr(
										"data-type");
								var id = $(this).next().attr("value");
								var icon = $(this).parent().attr("icon");
								var name =$(this).parent().text();
								var url = "";
								switch (value) {
								case 1: {
									url = "/sen/station/getDangerPoints?dangerTypeId="
											+ id;
									break;
								}
								case 2: {
									url = "/sen/station/getKeyPlaces?typeId="
											+ id;
									break;
								}
								case 3: {
									url = "/sen/station/getFacilityEquipment?equipmentId="
											+ id;
									break;
								}
								case 4: {
									url = "/sen/station/getKeyPeoples?personId="
											+ id;
									break;
								}
								}
								;
								if ($(this).is(":checked")) {
									markerDataHandle(type, id, url, true)
									$(".save-detail ul").append('<li class="'+icon+'"> <img src="./static2/emergencyImages/'+icon+'.png"> <span>'+name+'</span> </li>');
								} else {
									markerDataHandle(type, id, url, false)
									var iconClass = "."+icon;
									$(".save-detail ul>li").remove(iconClass);
								}
							});

			// 地图数据处理
			function markerDataHandle(type, id, url, bool) {
				if (bool) {
					// 添加该类型的站点
					var indexLayer = layer.load(2);
					getData(url, "get", '', "json", function(data) {
						if (!gis.markerData[type]) {
							gis.markerData[type] = {};
						}
                        if(data.length>0 && data[0].pointlist){
                            for(var i=0,ii=data.length;i<ii;i++){
                                var lineData = data[i].pointlist.split(",");
                                data[i].longitude = (parseFloat(lineData[0]) + parseFloat(lineData[2]))/2
                                data[i].latitude = (parseFloat(lineData[1]) + parseFloat(lineData[3]))/2
							}
						}
						gis.markerData[type][id] = data;
						darwMarker();
						layer.close(indexLayer);
					})
				} else {
					// 去除该类型站点
					var data = gis.markerData;
					for ( var i in data) {
						if (type == i) {
							var dataN = data[i];
							for ( var n in dataN) {
								if (n == id) {
									gis.markerData[type][id] = "";
								}
							}
						}
					}
					darwMarker();
				}
			}

			// 获取菜单
			getitem();
			function getitem() {
				var data = {
					id : 4
				};
				$(".fzjz-save").empty();
				var indexLayer = layer.load(2);
				getData(
						'/sen/station/getOneMenu',
						"get",
						data,
						"json",
						function(data) {
							layer.close(indexLayer);
							if (data) {
								var sum = '';
								for (var i = 0; i < data.length; i++) {
									if (data[i].dataType) {
										var str = '<li class="' + data[i].icon
												+ '" value="' + data[i].value
												+ '" data-icon="'
												+ data[i].icon
												+ '" data-type="'
												+ data[i].type
												+ '"><i></i><span>'
												+ data[i].name + '</span></li>';
										if (data[i].liMenu != null) {
											var limune = '<ul class="meau_erji">';
											for (var j = 0; j < data[i].liMenu.length; j++) {
												if (data[i].liMenu[j].dataType)
													limune += '<li class="emV" data-type="'
															+ data[i].dataType
															+ '"><label icon="'+data[i].liMenu[j].icon+'"><input type="checkbox"><em value="'
															+ data[i].liMenu[j].typeId
															+ '"></em>'
															+ data[i].liMenu[j].name
															+ '</label></li>';
												else {
													limune += '<li class="emV" data-type="'
															+ data[i].dataType
															+ '"><label icon="'+data[i].liMenu[j].icon+'"><input type="checkbox"><em value="'
															+ data[i].liMenu[j].typeId
															+ '"></em>'
															+ data[i].liMenu[j].name
															+ '</label></li>';
												}
											}
											limune += "</ul>";
											str = '<li class="' + data[i].icon
													+ '" value="'
													+ data[i].value
													+ '" data-type="'
													+ data[i].dataType
													+ '"><i></i><span>'
													+ data[i].name + '</span>'
													+ limune + '</li>';
										}
									} else {
										var str = '<li class="' + data[i].icon
												+ '" value="' + data[i].value
												+ '"><i></i><span>'
												+ data[i].name + '</span></li>';
									}
									sum += str;
								}
								$(".fzjz-save").append(sum);
							} else {
								layer.close(indexLayer);
							}

						});
			}

			getBoundaryData();
			function getBoundaryData() {
				var indexLayer = layer.load(2);
				getData('/static2/data/hyq.xml', "get", '', "xml", function(
						data) {
					// 蒙层
					var boundary = gis.drawBoundary("通州区", data, {
						fillOpacity : 0.68,// 透明度
						fillColor : "#2077be",// 填充色
						strokeColor : "red",// 边界线条颜色灰色
						strokeWidth : "2",
						isCut : true,
					});
					boundary.setZIndex(100);
					layer.close(indexLayer);
				})
			}

			// 点击下一个时次
			$(".m-next").click(function(e) {
				if (e.which) {// 是手动点击
					stopPlay();
				}
				var index = $(".m-axis-list li.z-on").index();
				var num = $(".m-axis-list li").length;
				if (index == num - 1)
					return;
				index++;
				timeAxisControl(index);
			})
			// 点击上一个时次
			$(".m-prev").click(function() {
				stopPlay();
				var index = $(".m-axis-list li.z-on").index();
				if (index == 0)
					return;
				index--;
				timeAxisControl(index);
			});
			// 直接点击时间轴
			$("body").on("click", ".m-axis-list li", function() {
				stopPlay();
				var index = $(this).index();
				timeAxisControl(index);
			})
			// 点击播放
			$(".m-paly").click(function() {
				if ($(this).hasClass("stop")) {
					stopPlay();
				} else {
					$(this).addClass("stop")
					clearInterval(timerAxis);
					timerAxis = setInterval(function() {
						$(".m-next").click();
					}, 3000);

				}
			});
			// 停止播放
			function stopPlay() {
				$(".m-play").removeClass("stop");
				if (timerAxis) {
					clearInterval(timerAxis);
				}
			}

			// 时间轴滚动
			function timeAxisControl(index) {
				$(".m-axis-list li").eq(index).addClass("z-on").siblings()
						.removeClass("z-on");
				var timeAxix = $(".m-time-axis-box");
				var timeW = timeAxix.width();
				var liW = $(".m-axis-list li").eq(0).width();
				var pageSize = Math.floor(timeW / liW);
				pageSize = pageSize / 2;
				var curPage = index - pageSize;
				if (curPage > 0) {
					timeAxix.animate({
						scrollLeft : liW * curPage
					}, 300);
				} else {
					timeAxix.animate({
						scrollLeft : 0
					}, 300);
				}
			}

			// 绘制站点
			function darwMarker() {
				var data = gis.markerData;
				var list = [];
                for(var i=0,ii=tifangLayer.length;i<ii;i++){
                    var line = tifangLayer[i];
                    line.destroy();
                }
                tifangLayer = [];
				for ( var j in data) {
					var dataN = data[j];
					if (dataN) {
						for ( var k in dataN) {
							var dataNN = dataN[k];
							for (var m = 0, mm = dataNN.length; m < mm; m++) {
								list.push(dataNN[m]);
							}
							//绘制提防线
							if(dataNN.length>0&&dataNN[0].pointlist){
                                for(var i=0,ii=dataNN.length;i<ii;i++){
                                    var lineData = dataNN[i].pointlist.split(",");
                                    var newData = [
                                        {lon:lineData[0],lat:lineData[1]},{lon:lineData[2],lat:lineData[3]}
                                    ]
                                    var line = gis.drawLine(newData, {
                                        strokeColor: 'blue',
                                        strokeWidth: 2,
                                        strokeOpacity: 0.9,
                                    }, true);
                                    tifangLayer.push(line);
                                }
							}
						}
					}
				}
                var features = [];
				var yuzhi = 99;
				if (list) {
					// var markerLayer = gis.layers[type] = gis.addMarkers();
					$.each(
									list,
									function(index, item) {
										// 站点聚合
										if (!item.longitude || !item.latitude)
											return;
										var ll = new OpenLayers.LonLat(
												item.longitude, item.latitude);
										ll = tranformTo900913(item.longitude,
												item.latitude, gis.map);
										// 为站点设置基础数据
										if (list.dataType == "disaster" || list[0].dataType == "equipment" ) {
											var Html = "<div class='on-site'><img src='/static2/emergencyImages/"
												+ list[index].icon
												+ ".png'><span>"
												+ item.depart
												+ "</span><a class='showchartDetail' href='javascript:void(0)'>查看</a></div>"
										}else {
											var Html = "<div class='on-site'><img src='/static2/emergencyImages/"
													+ list[index].icon
													+ ".png'><span>"
													+ item.name
													+ "</span><a class='showchartDetail' href='javascript:void(0)'>查看</a></div>"
										}

										var featureItem = new OpenLayers.Feature.Vector(
												new OpenLayers.Geometry.Point(
														ll.lon, ll.lat),
												{
													clustervaule : "",
													x : ll.lon,
													y : ll.lat,
													html : Html,
													img : "/static2/emergencyImages/"
															+ list[index].icon
															+ ".png",
													name : "",
													// text: "<div
													// class='site_txt
													// on_site'>" + displayvalue
													// + "</div><div
													// class='site_name'>" +
													// displayName + "</div>",
													graphicZIndex : 9999,
													content : item
												});
										features.push(featureItem);
									})
					// 标记点显示的字体颜色
					var style = new OpenLayers.Style(
							{
								fontSize : "12pt",
								fontWeight : "bold",
								fontColor : "#fff"
							},
							{
								rules : [
										new OpenLayers.Rule(
												{
													filter : new OpenLayers.Filter.Comparison(
															{ // 设置单点时的图标
																type : OpenLayers.Filter.Comparison.EQUAL_TO,
																property : "count", // 获取合并点数
																value : 1
															}),
													symbolizer : {
														graphicWidth : 50,
														graphicHeight : 50,
                                                        graphicXOffset : -25, // 设置显示文字的y轴偏移量
                                                        graphicYOffset : -50, // 设置显示文字的x轴偏移量
														label : "${name}", // 获取显示文字
														externalGraphic : "${img}" // 获取显示图标
													}
												}),
										new OpenLayers.Rule(
												{
													elseFilter : true, // 是否显示设置的文本or显示聚合count
													symbolizer : {
														graphicWidth : 30,
														graphicHeight : 30,
														pointRadius : "${radius}", // 计算圆点半径
														fillColor : "blue",
														fillOpacity : 1, // 透明度
														strokeColor : "#0E79DC", // 聚合后边框的颜色
														strokeWidth : "${width}", // 聚合后边框的宽度
														labelYOffset : "0", // 设置显示文字的偏移量
														label : "${count}", // 获取合并点数(${count})
														strokeOpacity : 1.0, // 边框透明度
														externalGraphic : "/static2/images/emergency-save/juhe.png" // 获取显示图标
													}
												}) ],
								// 结合基础数据，构建站点默认显示数据及显示样式
								context : {
									width : function(feature) {
										return (feature.cluster) ? 2 : 1;
									},
									radius : function(feature) {
										var pix = 2;
										if (feature.cluster) {
											pix = Math
													.min(
															feature.attributes.count,
															7) + 10;
										}
										return pix;
									},
									count : function(feature) {
										return feature.attributes.count; // 聚合后显示的数字如果追加数字，则会在count上+数字
									},
									img : function(feature) {
										return feature.cluster[0].data.img;
									},
									name : function(feature) {
										return feature.cluster[0].data.name; // 此处可追加文本
									}
								}
							});
					addClusterLayer(style, yuzhi, features, list.dataType);
				}
			}
			/*
			 * 聚合函数：站点聚合显示函数-弹框信息 @param {Array} style:站点数组 @param {int}
			 * yuzhi:聚合的触发：保证阈值大于value中最大的值，聚合才会发生 @param {Array} features:特征数组
			 */
			function addClusterLayer(style, yuzhi, features, type) {
				var clusters; // 站点图层，聚合站点的基础数据
				var strategy = [];
				OpenLayers.Strategy.RuleCluster = OpenLayers
						.Class(
								OpenLayers.Strategy.Cluster,
								{
									rule : null,
									shouldCluster : function(cluster, feature) {
										var superProto = OpenLayers.Strategy.Cluster.prototype;
										return this.rule
												.evaluate(cluster.cluster[0])
												&& this.rule.evaluate(feature)
												&& superProto.shouldCluster
														.apply(this, arguments);
									},
									distance : 50, // 设置聚合范围。单位为px
									CLASS_NAME : "OpenLayers.Strategy.RuleCluster"
								});
				var option = OpenLayers.Filter.Comparison.LESS_THAN; // 默认小于阈值的聚合

				strategy.push(new OpenLayers.Strategy.RuleCluster({
					rule : new OpenLayers.Rule({
						filter : new OpenLayers.Filter.Comparison({
							type : option,
							property : "clustervaule",
							value : yuzhi
						})
					})
				}));
				var stationLayer = new OpenLayers.Layer.Vector("站点信息图层", {
					strategies : strategy,
					styleMap : new OpenLayers.StyleMap(style)
				});
				// 如果是空对象就不执行清除效果否则先清除聚合站点
				if (!$.isEmptyObject(gisMap)) {
					gisMap.destroyFeatures();
				}
				clusters = gisMap = stationLayer;
				var select = new OpenLayers.Control.SelectFeature(clusters); // , {
																				// hover:
																				// true
																				// }
				gis.map.addControl(select);
				select.activate();
				var conlayer = gis.map.addLayer(clusters);
				// 画信息弹窗
				clusters.events
						.on({
							featureselected : function(e) {
								if(popupTogether){
									popupTogether.setContentHTML("");
								}

								clearPopupTogether();
								featureTogether = e.feature;
								if (featureTogether.cluster.length > 1) {
									var fHtml = "";
									// 聚合之后会有多个信息合成一组数据
									for (var i = 0; i < featureTogether.cluster.length; i++) {
										fHtml += featureTogether.cluster[i].data.html;
									}
									fHtml = "<div class='convergepop'><i class='close'></i>" + fHtml
											+ "</div>";
									var pointLonLat = featureTogether.cluster[0].data.content;
									popupTogether = gis.addPopup(0, 0, "", 0,
											0, false);
									popupTogether.setContentHTML(fHtml);
									popupTogether.setLonlat(
											pointLonLat.longitude,
											pointLonLat.latitude);
									featureTogether.popup = popupTogether;
									popupTogether.feature = featureTogether;
									$(popupTogether.div).css("z-index", 10000)
									// 绑定点击事件
									$("a.showchartDetail")
											.on(
													"click",
													function(obj) {
														var index = $(this)
																.parent()
																.index('div.on-site');
														markerClick(featureTogether.cluster[index]);
													});
									$("i.close").on("click",function(e){
										$(this).parents(".olPopup").hide();

									})
								} else {
									markerClick(featureTogether.cluster[0]);
								}
							},
							featureunselected : function(e) {
								clearPopupTogether();
							}
						});
				// strategy.threshold = strategy.threshold;
				clusters.removeFeatures(clusters.features);
				clusters.addFeatures(features);
				clusters.setZIndex(9999)

				clusters.redraw();
			}
			/*
			 * 清空显示的弹框 包含聚合站点时，如果缩放地图或拖动地图，popup会失去事件，所以要清空
			 */
			function clearPopupTogether() {
				if (featureTogether && featureTogether.popup && popupTogether) {
					popupTogether.feature = null;
					gis.map.removePopup(featureTogether.popup);
					featureTogether.popup.destroy();
					featureTogether.popup = null;
				}
			}
			// 站点点击事件
			function markerClick(e) {
				if (!e)
					return;
				var test = e.data.content.pointlist;
				var url = "";
				var value = $(
						".fzjz-save li[data-type='"
								+ e.attributes.content.dataType + "']").val();
				// var index=$(".fzjz-save
				// li[data-type='"+e.attributes.content.dataType+"']").attr("data-index");
				// var typeName = $(".fzjz-save
				// li").eq(index).find("span").text();
				var id = e.data.content.id;
				switch (value) {
				case 1: {
					url = '/sen/station/getOneDangerPoint?id=' + id;
					break;
				}
				case 2: {
					url = "/sen/station/getOneKeyPlace?id=" + id;
					break;
				}
				case 3: {
					url = "/sen/station/getOneFacilityEquipment?id=" + id;
					break;
				}
				case 4: {
					url = "/sen/station/getOneKeyPeople?id=" + id;
					break;
				}
				}
				var indexLayer;
				$.ajax({
							type : "GET",
							url : url,
							dataType : "JSON",
							beforeSend : function() {
								indexLayer = layer.load(2);
							},
							success : function(data) {
								layer.close(indexLayer);
								var typeName;
								var html = "<div class='layer_table'><table>";
								if (value == 4) {// 人员
									typeName = data.personType;
									html += "<tr><td>姓名</td><td>" + data.name
											+ "</td></tr><tr><td>单位</td><td>"
											+ data.depart+ "</td></tr>"
											+ "<tr><td>职务</td><td>" + data.job
											+ "</td></tr><tr><td>地址</td><td>"
											+ data.address
											+ "</td></tr><tr><td>联系方式</td><td>"
											+ data.phone + "</td></tr>";
								} else {
									if(test!=undefined){
										var result = test.split(",");
										html += "<tr><td>位置</td><td>"
											+ data.location + "</td></tr>"
											+ "<tr><td>起始经度</td><td>"
											+ result[0]
											+ "</td></tr><tr><td>起始纬度</td><td>"
											+ result[1]+ "</td></tr>"
											+ "<tr><td>结束经度</td><td>"
											+ result[2]
											+ "</td></tr><tr><td>结束纬度</td><td>"
											+ result[3]+ "</td></tr>";
									}else {
										html += "<tr><td>位置</td><td>"
											+ data.location + "</td></tr>"
											+ "<tr><td>经度</td><td>"
											+ data.longitude
											+ "</td></tr><tr><td>纬度</td><td>"
											+ data.latitude + "</td></tr>";
									}
									if (value == 2) {// 重点单位
										typeName = data.type;
										html += "<tr><td>单位</td><td>"
												+ data.depart
												+ "</td></tr><tr><td>联系人</td><td>"
												+ data.peoson + "</td></tr><tr><td>联系方式</td><td>"
												+ data.phone + "</td></tr><tr><td>职位</td><td>"
												+ data.position + "</td></tr>";
									} else if (value == 3) {// 设施设备
										typeName = data.equipmentName;
										html += "<tr><td>设施用户</td><td>"
												+ data.peoson
									} else if (value == 1) {
										typeName = data.dangerTypeName;
										html +="<tr><td>行政编号</td><td>"
											+ data.code
											+"<tr><td>单位</td><td>"
												+ data.depart
												+ "</td></tr><tr><td>联系人</td><td>"
												+ data.linker
												// + "</td></tr><tr><td>历史灾情</td><td>"
												// + data.historicalDisaster
												// + "</td></tr>"
												// + "<tr><td>历史灾情</td><td>"
												// + data.historicalDisaster
												+ "</td></tr><tr><td>预警条件</td><td>"
												+ data.meteorological
												+ "</td></tr>"
												+ "<tr><td>灾害临界值</td><td>"
												+ data.threshold + "</td></tr>"
									}
								}
								html += "</table></div>";

								layer
										.open({
											title : [
													"<div class='popup_hd_bk'>"
															+ typeName
															+ "</div><div>"
															+ data.depart
															+ "</div>",
													'font-size:14px;padding:0;background:#1c4560;color:#fff;height:auto;border-bottom: 1px solid rgba(255,255,255,0.28);' ],
											type : 1,
											skin : 'layui-layer-rim',
											closeBtn : 2,
											shade : [ 0 ],
											area : [ '400px', '330px' ],
											content : html,
											resize : false,
										});
							},
							error : function(e) {
								console.log(e);
							}
						});
			}
			// 决策材料的搜索
			$("#confirm").click(function() {
				var type = $(".fzjz-save li.z-on").attr("data-type");
				var beginTime = document.getElementById("beginTime").innerText;
				var endTime = document.getElementById("endTime").innerText;
				var name = $("#fileName").val();
				getDecisionMaking(1,beginTime, endTime, name, type);
			});
			//增加图例
			$(".popup-h").click(function (e) {
				$(".m-popup").addClass("hide");
				window.event ? window.event.cancelBubble = true : e.stopPropagation();
			});
			$("body").on("click", ".m-popup.hide", function () {
				$(".m-popup").removeClass("hide");
			});
			//getSaveLegend();
			function getSaveLegend(){


				var url = "/sen/station/getAllIcon";
				getData(url, "get", "", "json", function (data) {
					$(".save-detail ul").empty();
					var text='';
					for(var key in data){
						text+='<li> <img src="./static2/emergencyImages/'+data[key]+'.png"> <span>'+key+'</span> </li>';
					}
					$(".save-detail ul").html(text);

				})
			}


		});

		//分页查询
		function decisionMakingQuery(pages) {
			var type = $(".fzjz-save li.z-on").attr("data-type");
			var beginTime = document.getElementById("beginTime").innerText;
			var endTime = document.getElementById("endTime").innerText;
			var name = $("#fileName").val();
			getDecisionMaking(pages,beginTime, endTime, name, type);
		}

		// 决策服务材料
		function getDecisionMaking(pages,beginTime, endTime, name, type) {
			$.ajax({
				type : "GET",
				url : "/sen/townJxh/getReporter",
				data : {
					"beginTime" : beginTime,
					"endTime" : endTime,
					"name" : name,
					"type" : type,
					"pages" : pages
				},
				dataType : "JSON",
				success : function(data) {
					// alert(data)
					var html = "";
					if(data.fileList!=null){
						for (var i = 0; i < data.fileList.length; i++) {
							html += '<tr>' +
								'<td>' + data.fileList[i].fileName + '</td>' +
								'<td>'+data.fileList[i].time+'</td>'+
								'<td><span data-path=' + data.fileList[i].path + '>+查看</span></td>' +
								'</tr>'
						}
					}

					$("tbody#decisionMake").html(html);
					getHtmlPager(data, 'pager', 'report_pagerTpl_feedback');
				},
				error : function(e) {
					console.log(e);
				}
			})
		}

		// 分页方法
		function getHtmlPager(data, htmlId, templateId, displaypages) {
		    var pageDiv = null;
		    if (data && data.page.totalItemCount > 0) {
		        var start, end;
		        var showPage = 5;
		        if (displaypages && displaypages > 0)
		            showPage = displaypages;
		        var halfOfPagesToShowAtOnce = Math.floor(showPage / 2);
		        start = data.page.pageNumber - halfOfPagesToShowAtOnce;
		        if (start < 1)
		            start = 1;
		        if ((start + showPage) > data.page.pageCount)
		            end = data.page.pageCount;
		        else
		            end = start + showPage;
		        data.page.Pages = [];
		        for (var i = start; i <= end; i++) {
		            data.page.Pages.push({
		                pageNumber: i,
		                Selected: i === data.page.pageNumber
		            });
		        }
		        pageDiv = $("#" + htmlId);
		        pageDiv.empty();
		        pageDiv.html($("#" + templateId).render(data.page));
		    } else {
		        data.page.Pages = [];

		        data.page.Pages.push({
		            PageNumber: 1,
		            Selected: 1 === data.page.pageNumber
		        });

		        pageDiv = $("#" + htmlId);
		        pageDiv.empty();
		        pageDiv.html($("#" + templateId).render(data.page));
		    }
		}


$(window).resize(
		function() {
			var hei = Math.max(document.documentElement.scrollHeight,
					document.documentElement.clientHeight);
			$(".m-nav>ul>li>ul").height(hei);
		})