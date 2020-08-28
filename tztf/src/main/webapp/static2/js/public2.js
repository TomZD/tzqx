$(function(){
  var timer=setInterval(function(){
       var myDate = new Date();
       var str = myDate.toString();
       var year = str.slice(11,15) + '年';
       var month=myDate.getMonth()+1;
       var yue = month + '月';
       var date=myDate.getDate();
       var ri = date + '日'
       var sec = str.slice(16,24) ;
       var strs = year+yue+ri+' '+sec;
       $(".g-hd .hd_time").html(strs)
   },1000)
})