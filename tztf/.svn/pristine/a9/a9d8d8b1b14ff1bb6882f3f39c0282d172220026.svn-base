$(function () {
	var categories;
	var series;
	var humidity;
	var temp;
	var windlevel;
	var windirection;
	var directionList;
	var rain;
	
    // 表一
    $.post('/sev/monitor/highchartMonitor?stationNo='+stationNo,function(data) {
        ecData = data;      
    //    categories = ["16日09时", "08时", "07时", "06时", "05时", "04时", "03时", "02时", "01时", "00时", "15日23时", "22时", "21时", "20时", "19时", "18时", "17时", "16时", "15时", "14时", "13时", "12时", "11时", "10时", "09时", "08时", "07时", "06时", "05时", "04时", "03时", "02时", "01时", "00时", "14日23时", "22时", "21时", "20时", "19时", "18时", "17时", "16时", "15时", "14时", "13时", "12时", "11时", "10时"];
   //     series = ecData.weatherSeries;
        categories = ecData.categories;
        humidity = ecData.weatherSeries[4].data;
        temp =ecData.weatherSeries[3].data;
        rain = ecData.weatherSeries[1].data;
        windlevel = ecData.weatherSeries[0].data;
        windirection =ecData.weatherSeries[2].data;
        directionList =ecData.weatherSeries[5].data;
        newcharts($('#chart3'),'line',categories,windlevel,windirection,directionList);
     //   newcharts($('#temp-container'),ecData.categories,ecData.weatherSeries);

	
    $('#chart1').highcharts({
        chart: {
            type: 'line'
           
        },
        title: {
            text: false,
        },
        credits:{
            enabled:false,
        },
        xAxis: {
        	tickmarkPlacement: 'between',
        	categories:categories,
            lineWidth :0,
            tickWidth:0,
            labels:{  
               enabled:false  
            },
        },
        yAxis: {
            categories: false,
            title: {
                text: false,
            },
            gridLineWidth: 0,
            labels:{
                enabled: false,
            }
        },
        legend: {  //图例   
            layout: 'vertical',  //图例显示的样式：水平（horizontal）/垂直（vertical）    
            align: 'right',  //图例水平对齐方式   
            verticalAlign: 'middle',  //图例垂直对齐方式   
            x: 0,  //相对X位移   
            y: 0,   //相对Y位移 
            reversed: true,  
        },
        plotOptions: {
            line: {
            	color : '#9D9D9D',
                dataLabels: {
                    enabled: true
                },
            }
        },
        series: [{
            name: '湿度(%)',
            data: humidity
        }]
    }); 
    // 表二
    $('#chart2').highcharts({
        chart: {
            type: 'line'
        },
        title: {
            text: false,
        },
        credits:{
            enabled:false,
        },
        xAxis: {
        	tickmarkPlacement: 'between',
        	categories: categories,
            lineWidth :0,
            tickWidth:0,
            labels:{  
                enabled:false  
             }
        },
        yAxis: {
            categories: false,
            title: {
                text: false,
            },
            gridLineWidth: 0,
            labels:{
                enabled: false,
            }
        },
        legend: {  //图例   
            layout: 'vertical',  //图例显示的样式：水平（horizontal）/垂直（vertical）    
            align: 'right',  //图例水平对齐方式   
            verticalAlign: 'middle',  //图例垂直对齐方式   
            x: 0,  //相对X位移   
            y: 0,   //相对Y位移 
            reversed: true,  
        },
        plotOptions: {
            line: {
                color : '#00DB00',
                dataLabels: {
                    enabled: true
                },
            }
        },
        series: [{
            name: '气温(°C)',
            data: temp
        }]
    }); 
    // 表三
    function newcharts(container,type,categories,windlevel,windirection,directionList){
    	var windDs =[];
    	var winds =[];
    	var forecastWinds =[];
    	var fwinds =[];
    	var slevel = [];
    	var cate = [];
    	var directions =[];
//    	var sign = categories[0].substr(1, 4);
    	for(var i=0;i<windlevel.length;i++){
    		var windValue=windlevel[i];
    	    var	windDirection = windirection[i];
    		var direction =directionList[i];
    		winds.push(windValue);
    		var windIcon = "";
    		if(windDirection!=null){
            windIcon = "../../static/images/wind-img/" + windDirection+".png";
    		}
    		if(windValue!=null){
            windDs.push({
                y : windValue,
                windd:direction,
                marker : {
                    symbol : 'url('+windIcon+')'
                 
                }
            });
    	  }
    	}
    	 container.highcharts({
    		   chart: {
    	            type: 'line'
    	        },
    	        title: {
    	            text: false,
    	        },
    	        credits:{
    	            enabled:false,
    	        },
    	        xAxis: {
    	        	tickmarkPlacement: 'between',
    	        	categories: categories,
    	            lineWidth :0,
    	            tickWidth:0,
    	            labels:{  
    	                enabled:false  
    	             }
    	        },
    	        yAxis: {
    	            categories: false,
    	            title: {
    	                text: false,
    	            },
    	            gridLineWidth: 0,
    	            labels:{
    	                enabled: false,
    	            }
    	        },
    	        legend: {  //图例   
    	            layout: 'vertical',  //图例显示的样式：水平（horizontal）/垂直（vertical）    
    	            align: 'right',  //图例水平对齐方式   
    	            verticalAlign: 'middle',  //图例垂直对齐方式   
    	            x: 0,  //相对X位移   
    	            y: 0,   //相对Y位移 
    	            reversed: true,  
    	        },
    	        tooltip: {
                	useHTML: true,
                	formatter: function () {
                    	if(this.point.series.name=="风向"){
                    		return this.x+this.point.windd+'风'+this.y+"级" ;
                    	}else{
                    		return false;
                    		
                    	}
                    }
                },
    	        plotOptions: {
    	            line: {
    	                color : '#00DB00',
    	                dataLabels: {
    	                    enabled: true
    	                },
    	            }
    	        },
    	        series: [{
    	            name: '风力(级)',
    	            data: winds,
    	            color : '#FF0000'
    	        },{
    	        	name :"风向",
    	        	type : "scatter",
    	            data : windDs,
    	            color : '#000000'
    	        }]
        });
    } 
    // 表四
    $('#chart4').highcharts({
        chart: {
            type: 'column'
        },
        title: {
            text: false,
        },
        credits:{
            enabled:false,
        },
        xAxis: {
        	tickInterval: 4,
            categories: categories
        },
        yAxis: {
            categories: false,
            title: {
                text: false,
            },
            gridLineWidth: 0,
            labels:{
                enabled: false,
            }
        },

        legend: {  //图例   
            layout: 'vertical',  //图例显示的样式：水平（horizontal）/垂直（vertical）    
            align: 'right',  //图例水平对齐方式   
            verticalAlign: 'middle',  //图例垂直对齐方式   
            x: 0,  //相对X位移   
            y: 0,   //相对Y位移 
            reversed: true,  
        },  
        plotOptions: {
            column: {
                dataLabels: {
                    enabled: true,
                    formatter: function() {  
                        if (this.y > 0)  
                            return this.y; //这里进行判断（看这里）  
                    },  
                    
                },
          
            }
        },
        series: [{
            name: '降水(mm)',
            data: rain
        }]
    });
   });
 });