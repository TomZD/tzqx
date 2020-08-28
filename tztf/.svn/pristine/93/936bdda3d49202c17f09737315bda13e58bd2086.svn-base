$(function () {
    //文字过多时的文字处理
    var dealtr = $(".m-table table tbody").find("tr");
    dealTrtext(dealtr, 4);

    function dealTrtext(dealtr, index) {
        $.each(dealtr, function (i, item) {
        // 获取该td的内容
        var valueall = $(".m-table table tbody").find("tr").eq(i).find("td").eq(index).html();
        var tdWidth = $(".m-table").find(".release_content").width();
        var showWordnum = parseInt(tdWidth / 12) - 4;//12为字体字号，显示多少个字
        var ellipsis;
        if (valueall.length > showWordnum) {
        	
            ellipsis = valueall.substr(0, showWordnum) + "...";
            $(item).find("td").eq(index).addClass("layerui_tag").html(ellipsis);
            $(item).find("td").eq(index).attr("id", "trpop_" + i);
            $(item).find("td").eq(index).append("<input type='hidden' value='" + valueall + "'>");
        } else {
            return;
            // ellipsis = valueall;
        }

    });
    // tip的使用
    $(".release_content").on("mouseover", function () {
        var id = $(this).attr("id");
        var content1 = $(this).find("input").val();
        layer.tips(content1, '#' + id, { tips: [2, "#55b4e7"] });
    });
    }

    /*详情弹窗*/
    $(".details").on("click",function(){
        $(".m-popup").show();
    })

    $('#information').highcharts({
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
　　          verticalAlign: 'bottom',//垂直标的目标地位
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
           enabled:true  
        },   
        categories: ['06:19:05', '06:19:10',  '06:19:15', '06:19:20','06:19:25','06:19:30']
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
           enabled:true  
        },        
    },
    series: [{
        name: '需要发送',
        color: '#ffa07a',
        zones: [{
            color: '#ffa07a'
        }],
        data: [1, 2, 3, 4, 5, 6],
         },{
        name: '已递交',
        color: '#ffa01a',
        zones: [{
            color: '#ffa01a'
        }],
        data: [0, 0, 1, 1, 0, 0],
         }, {
        name: '已发出',
        color: '#f0a01a',
        zones: [{
            color: '#f0a01a'
        }],
        data: [1, 2, 3, 4, 5, 6],
         },{
        name: '已确认',
        color: '#70aa1a',
        zones: [{
            color: '#70aa1a'
        }],
        data: [0, 0, 1, 1, 0, 0],
         },{
        name: '已完成',
        color: '#23a01a',
        zones: [{
            color: '#23a01a'
        }],
        data: [0, 0, 1, 1, 0, 0],
         },{
        name: '出错',
        color: '#00a01a',
        zones: [{
            color: '#00a01a'
        }],
        data: [0, 0, 1, 1, 0, 0],
         },{
        name: '状态', 
        color: '#f0a01a',               
        zones: [{
            color: '#f0a01a'
        }],
        data: [0, 0, 1, 0, 0, 1]
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
    
});