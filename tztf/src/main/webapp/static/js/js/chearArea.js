   var areamap = {"百官街道":"bgjd","曹娥街道":"cejd","长塘镇":"ctz","陈溪乡":"cxx","道墟镇":"dxz","丁宅乡":"dzx","东关街道":"dgjd",
    		   "丰惠镇":"fhz","盖北镇":"gbz","沥海镇":"lhz","梁湖镇":"lianghz","岭南乡":"lnx","上浦镇":"spz","上虞围垦区":"syqwgq",
    		   "汤浦镇":"tpz","下管镇":"xgz","小越镇":"xyz","谢塘镇":"xtz","永和镇":"yhz","章镇镇":"zzz","崧厦镇":"sxz","驿亭镇":"ytz"};
        function need(list){
        	 $('#map').css('display', '');
    	    $('#cloudImg').html('');
    	    var twon='';
    	    var l = boundrys.length;
    	    if (l > 1 || l == 0) {
    	        for (var i = 0 ; i < l; i++) {
    	            boundrys[i].destroy();
    	        }
    	        boundrys = [];
    	        for(var key in areamap){
    	        	if(key==list){
    	        	twon=areamap[key];	
    	        	}
    	        }
    	        
    	        $.ajax({
    	            url: "../static/js/syareatwo/"+twon+".xml",
    	            contentType: "application/x-www-form-urlencoded; charset=utf-8",
    	            type: "get",
    	            dataType: "xml",
    	            success: function (xml) {
    	                b = mapstol.drawBoundary("上虞", xml, {
    	                    fillOpacity: 0.3,
    	                    strokeColor: "#f00",
    	                    fillColor:"#e627cf"
    	                });
    	               // boundrys.push(b);
    	            },
    	            error:function(){
    	            	alert("无法进入");
    	            }
    	        });
    	    }
        }