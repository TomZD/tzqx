$(function(){
	var lon;
	var lat;
	var geoc = new BMap.Geocoder(); 
	// 百度地图API功能
	var map = new BMap.Map("allmap");
	var point = new BMap.Point(120.147975,30.26658);
	map.centerAndZoom(point,12);
	var geolocation = new BMap.Geolocation();
	geolocation.getCurrentPosition(function(r){
		if(this.getStatus() == BMAP_STATUS_SUCCESS){
			var mk = new BMap.Marker(r.point);
			map.addOverlay(mk);
			map.panTo(r.point);
			localStorage.setItem("lon",r.point.lng);
			localStorage.setItem("lat",r.point.lat);
			geoc.getLocation(r.point, function (rs) {
				 var addComp = rs.addressComponents;  
		         var address = addComp.province + addComp.city + addComp.district + addComp.street + addComp.streetNumber;
		         $(".province").val(addComp.province);
		         $(".city").val(addComp.city);
		         $(".district").val(addComp.district);
		         $(".detail").val(addComp.street+addComp.streetNumber);
		         localStorage.setItem("sb-address",address);
			})
		}
		else {
			alert('failed'+this.getStatus());
		}        
	},{enableHighAccuracy: true})
	map.addEventListener("click",function(e){
		lon=e.point.lng;
		lat=e.point.lat;
		localStorage.setItem("lon",lon);
		localStorage.setItem("lat",lat);
		map.clearOverlays();
		var point = new BMap.Point(lon,lat);
		var mk = new BMap.Marker(point);
		map.addOverlay(mk);
		geoc.getLocation(e.point, function (rs) {
			 var addComp = rs.addressComponents;  
	         var address = addComp.province + addComp.city + addComp.district + addComp.street + addComp.streetNumber;
	         $(".province").val(addComp.province);
	         $(".city").val(addComp.city);
	         $(".district").val(addComp.district);
	         $(".detail").val(addComp.street+addComp.streetNumber);
		})
	})
	$(".confirm").click(function(){
		localStorage.setItem("sb-address",$(".province").val()+$(".city").val()+$(".district").val()+$(".detail").val());
		window.history.back();
	})
})