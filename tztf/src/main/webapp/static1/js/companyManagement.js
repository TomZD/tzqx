$(function() { 
    //单位管理-列表编辑删除按钮操作
    $(".edit_btn").on("click",function() { 
        //点击编辑
        var typeTxt = $(this).parents("tr").find(".type").text();
        var companyTxt = $(this).parents("tr").find(".company").text(); 
        var orderTxt = $(this).parents("tr").find(".order").text();
        var codeTxt = $(this).parents("tr").find(".code").text();

        var typeInput = $("<select class='this-select'><option>" + typeTxt + "</option><option>" + typeTxt + "</option></select>");
        var companyInput = $("<input type='text'value='" + companyTxt + "'/>"); 
        var orderInput = $("<input type='text'value='" + orderTxt + "'/>"); 
        var codeInput = $("<input type='text'value='" + codeTxt + "'/>"); 
        
        $(this).parents("tr").find(".type").html(typeInput); 
        $(this).parents("tr").find(".company").html(companyInput); 
        $(this).parents("tr").find(".order").html(orderInput); 
        $(this).parents("tr").find(".code").html(codeInput);
        //下拉框选值后变成不可编辑
        $(".this-select").change(function(){
            var newType=$(".this-select").val();
            $(this).parents("tr").find(".type").html(newType); 
        });
        //失去焦点后不可编辑
        var input = $(this).parents("tr").find(".input_edit").find('input');
        input.blur(function(){
            
            var newCompany = $(this).parents("tr").find(".company").find('input').val(); 
            var newOrder = $(this).parents("tr").find(".order").find('input').val(); 
            var newCode = $(this).parents("tr").find(".code").find('input').val(); 
            $(this).parents("tr").find(".company").html(newCompany); 
            $(this).parents("tr").find(".order").html(newOrder);  
            $(this).parents("tr").find(".code").html(newCode);
        });
    });

       
        
});