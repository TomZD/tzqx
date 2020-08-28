$(function(){
  $(".table_tb").myScroll({
    speed:30, //数值越大，速度越慢var dom = document.getElementById("container");
    rowHeight:70 //tr的高度
  });
  var mySwiper = new Swiper ('.swiper-container', {
    slidesPerView: 'auto',
    loop: true,
    speed: 10000,
    autoplay: {
      delay: 0,
      disableOnInteraction: true,
    },
    slidesPerView : 3,
    spaceBetween : 40,
  })        
  
  drawMapajax();

  function drawMapajax(){	
	  var indexLayer;

      $.ajax({
          url : 'getAlarmByArea',
          type : 'get',
          beforeSend: function() {
            indexLayer=layer.load(2);               
          },
         // dataType : 'json',
          success: function (data) {
        	  drawMap(data);
          },
          complete:function(){
            layer.close(indexLayer);      
          },
             
      });
  }
  //使用d3绘制地图
  function drawMap(data){
	 console.log(data);
    var width  = $("#map").width();
    var height = $("#map").height();
    var svg = d3.select("#map").append("svg")
    .attr("width", width)
    .attr("height", height)
    .append("g")
    .attr("transform", "translate(0,0)");
    var projection = d3.geo.mercator()
    .center([119.6, 29.8])
    .scale(8000)
    .translate([width/2, height/2]);

    var path = d3.geo.path()
    .projection(projection);


    var color = d3.scale.category20();


    d3.json("../../data/hzs.json", function(error, root) {
      if (error) 
        return console.error(error);
      console.log(root.features);

      svg.selectAll("path")
      .data(root.features )
      .enter()
      .append("path")
      .attr("stroke","#000")
      .attr("stroke-width",1)
      .attr("fill", function(d,i){
    	  for(var a in data){
    		  if(d.properties.NAME==data[a].area){
    			  return data[a].color;
    		  }
    	  }
        return color(1);
      })
      .attr("d", path )
      .on("mouseover",function(d,i){
        d3.select(this)
        .attr("fill","yellow");
      })
      .on("mouseout",function(d,i){
        d3.select(this).attr("fill",color(1));
        for(var a in data){
  		  if(d.properties.NAME==data[a].area){
  			d3.select(this).attr("fill",data[a].color);
  			return;
  		  }
  	  }
        d3.select(this).attr("fill",color(1));
        
      });
      //添加文字元素
      var texts = svg.selectAll(".MyText")
      .data(root.features)
      .enter()
      .append("text")
      .attr("class","MyText")
      .attr("transform",function(d){
            //计算标注点的位置
            var coor = projection([d.properties.X, d.properties.Y]);
            return "translate("+ coor[0] + "," + coor[1] +")";
          })
      .text(function(d){
        return d.properties.NAME;
      })
      .attr("fill","#fff");
    });
  }

  function createExample(option, tooltipOption) {
        // 基于准备好的dom，初始化echarts图表
        var chart = echarts.init(document.getElementById("peace_chart"));
        // 为echarts对象加载数据
        chart.setOption(option);
        tools.loopShowTooltip(chart, option, tooltipOption );
      }
      function createExamples(options, tooltipOption) {
        // 基于准备好的dom，初始化echarts图表
        var charts = echarts.init(document.getElementById("live_chart"));
        // 为echarts对象加载数据
        charts.setOption(options);
        tools.loopShowTooltip(charts, options, tooltipOption );
      }
  //绘制highchart面积图
      getHighcharts();

      function getHighcharts(){	
        var indexLayer;
          $.ajax({
              url : 'getHighcharts',
              type : 'get',

              dataType : 'json',
              beforeSend: function() {
                indexLayer=layer.load(2);               
              },
              success: function (data) {
            	  drawDetailsChart(data);
              },
              complete:function(){
                layer.close(indexLayer);      
            },
                 
          });
      }
  
  function drawDetailsChart(data){
	  console.log(data);
    var doms = document.getElementById("live_chart");
    var myCharts = echarts.init(doms);
    var app = {};
    options = null;
    var options = {
      title : {
        text: '',
        subtext: ''
      },
      tooltip : {
        trigger: 'axis'
      },
      legend: {
        data:['杭州门户','杭州网','杭州发布'],
        textStyle: {fontSize:"22px",color: '#fff'},
        bottom:"10px",
      },
      toolbox: {
        show : true,
        feature : {
          mark : {show: false},
          dataView : {show: false, readOnly: false},
          magicType : {show: false, type: ['line', 'bar', 'stack', 'tiled']},
          restore : {show: false},
          saveAsImage : {show: false}
        }
      },
      calculable : true,
      color:['#ffa696','#1759fa','#64f8ff'],
      xAxis : [
      {
        type : 'category',
        boundaryGap : false,
        axisLabel: {
          show: true,
          textStyle: {
            color: '#e5e5e5'
          }
        },
        title:"时间",
        show:false,
        data : data[0]//['周一','周二','周三','周四','周五','周六','周日']
      }
      ],
      yAxis : [
      {
        type : 'value',
        axisLabel: {
          show: true,
          textStyle: {
            color: '#e5e5e5',
            fontSize:12
          }
        },
      }
      ],
      series : [
      {
        name:'杭州天气网',
        type:'line',
        smooth:true,
        itemStyle: {normal: {areaStyle: {type: 'default'}}},
        data:data[3]//[10, 12, 21, 54, 260, 780, 710]
      },
      {
        name:'微博',
        type:'line',
        smooth:true,
        itemStyle: {normal: {areaStyle: {type: 'default'}}},
        data:data[2]//[30, 182, 434, 791, 390, 30, 10]
      }
      ]
    };
    createExamples(options, {
      loopSeries: true
    });
  }

  //绘制环装图表
  drawAnnularChart()
  function drawAnnularChart() {
    var dom = document.getElementById("peace_chart");
    var myChart = echarts.init(dom);
    var app = {};
    option = null;
    var option = {
      tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b}: {c} ({d}%)",
        transitionDuration : 0.4
      },
      legend: {
        x: 'right',
        y:"bottom",
        textStyle:{
          fontSize:18,
        },
        data:[
          {name:'本月短信', textStyle: {color: 'fbf44f'}},
          {name:'当年短信',textStyle: {color: 'bc4ff6'}},
          ],
      },
      color:['#fbf44f', '#bc4ff6','#6b64f3','#6effb4'],
      series: [
      {
        name:'',
        type:'pie',
        radius: ['50%', '70%'],
        avoidLabelOverlap: false,
        label: {
          normal: {
            show: false,
            position: 'center'
          },
          emphasis: {
            show: true,
            textStyle: {
              fontSize: '24',
              fontWeight: 'bold'
            }
          }
        },
        labelLine: {
          normal: {
            show: false
          }
        },
        data:[
        {value:$("#dxMonth").val(), name:'本月短信'},
        {value:$("#dxYear").val(), name:'当年短信'},
        ]
      }
      ]
    };
    createExample(option, {
      loopSeries: true
    });
  }
})