$(function(){
	/*网络情况*/
    $('#network').highcharts({
            chart: {
                type:'line',                
                backgroundColor: 'rgba(0,0,0,0)'
            },
            title: {
                text: '',
                x: -500,//center
                y:15,
                style:{                    
                    color: '#0e0e0e', 
                    fontSize:'12px'
                }
            },
            legend: {
                enabled: true,
                align: 'center', //程度标的目标地位
　　    		verticalAlign: 'bottom', //垂直标的目标地位
				itemStyle:{
					color:"#333"
				}
    },
    credits: {
        enabled: false//取消默认highchart官网地址
    },
    xAxis: {   
        tickWidth: 0,//刻度的宽度  
        lineWidth: 0,
        tickWidth: 0,//横轴1px的网格
        lineColor:'#f2f2f2',//网格颜色
        gridLineWidth: 0,//纵轴1px的网格 
        labels:{  
           enabled:false  
        },   
    },
    yAxis: {   
    	scaleColor:'#FFF',  
    	scaleLabel : "<%=value%>ms",         
        tickPixelInterval:30, 
        title: {
            text: ''
        }, 
        tickWidth:0,        //设置刻度标签宽度  
        lineColor:'#ffffff',//设置坐标颜色  
        lineWidth:0,        //设置坐标宽度  
        labels:{  
           enabled:true,
           color:"#fff"  
        },        
    },
    series: [{
        name: '标准值',
        color: '#a6cf4b',
        zones: [{
            color: '#a6cf4b'
        }],
        data: [400, 400, 400, 400, 400, 400],
         }, {
        name: '响应值', 
        color: '#0f8ffe',               
        zones: [{
            color: '#0f8ffe'
        }],
        data: [200, 200, 200, 200, 200, 200]
    }],
    tooltip: {
        backgroundColor:'rgba(98,103,115,.75)',
        borderWidth: 1,
        borderColor: '#494f5e',
        width:50,
        pointFormat: '<span style="color:#FFF">{series.name}:{point.y:,.0f} 级</span><br/>',
        shared: true
    }, 
	plotOptions: {
        series: {
            marker: {
                enabled: false
            }
        },
	}
});

	/*发布渠道*/
	$('#channel1').highcharts({
            chart: {
                type:'line',                
                backgroundColor: 'rgba(0,0,0,0)'
            },
            title: {
                text: '',
                x: -500,//center
                y:15,
                style:{                    
                    color: '#0e0e0e', 
                    fontSize:'12px'
                }
            },
            legend: {
                enabled: true,
                align: 'center', //程度标的目标地位
　　    		verticalAlign: 'bottom', //垂直标的目标地位
				itemStyle:{
					color:"#333"
				}
    },
    credits: {
        enabled: false//取消默认highchart官网地址
    },
    xAxis: {   
        tickWidth: 0,//刻度的宽度  
        lineWidth: 0,
        tickWidth: 0,//横轴1px的网格
        lineColor:'#f2f2f2',//网格颜色
        gridLineWidth: 0,//纵轴1px的网格 
        labels:{  
           enabled:false  
        },   
    },
    yAxis: {   
    	scaleColor:'#FFF',  
    	scaleLabel : "<%=value%>ms",         
        tickPixelInterval:30, 
        title: {
            text: ''
        }, 
        tickWidth:0,        //设置刻度标签宽度  
        lineColor:'#ffffff',//设置坐标颜色  
        lineWidth:0,        //设置坐标宽度  
        labels:{  
           enabled:true,
           color:"#fff"   
        },        
    },
    series: [{
        name: '标准值',
        color: '#a6cf4b',
        zones: [{
            color: '#a6cf4b'
        }],
        data: [400, 400, 400, 400, 400, 400],
         }, {
        name: '响应值', 
        color: '#0f8ffe',               
        zones: [{
            color: '#0f8ffe'
        }],
        data: [200, 200, 200, 200, 200, 200]
    }],
    tooltip: {
        backgroundColor:'rgba(98,103,115,.75)',
        borderWidth: 1,
        borderColor: '#494f5e',
        width:50,
        pointFormat: '<span style="color:#FFF">{series.name}:{point.y:,.0f} 级</span><br/>',
        shared: true
    }, 
	plotOptions: {
        series: {
            marker: {
                enabled: false
            }
        },
	}
});
    /*网络情况切换*/
	var $li = $('.network_tab > li');
    var $ul = $('.network-highchart > div');

    $li.click(function () {
        var $this = $(this);
        var $t = $this.index();
        $li.removeClass();
        $this.addClass('on_highchartTab');
        $ul.css('display', 'none');
        $ul.eq($t).css('display', 'block');
   })
    /*发布渠道切换*/
	var $tab = $('.channel_tab > li');
    var $div = $('.channel-highchart > div');

    $tab.click(function () {
        var $this = $(this);
        var $t = $this.index();
        $tab.removeClass();
        $this.addClass('on_highchartTab');
        $div.css('display', 'none');
        $div.eq($t).css('display', 'block');
   })


})