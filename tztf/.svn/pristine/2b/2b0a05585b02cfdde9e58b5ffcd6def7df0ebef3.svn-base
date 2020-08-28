function getData(argument,callback) {
    $.ajax({
        url:argument.url,
        type:argument.type,
        dataType:argument.dataType,
        success:function(d){
            if(typeof callback === "function"){
                callback(d)
            }
        },
        error:function(e){
            if(typeof callback === "function"){
                callback('')
            }
        }
    })
}