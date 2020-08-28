$(function(){
	/*右侧切换*/
	var $li = $('.query_hd > li');
    var $ul = $('.form_text');

    $li.click(function () {
        var $this = $(this);
        var $t = $this.index();
        $li.removeClass();
        $this.addClass('on_query');
        $ul.css('display', 'none');
        $ul.eq($t).css('display', 'block');
   })
});