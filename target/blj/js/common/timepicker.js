function init() {
    //定义locale汉化插件
    var locale = {
        "format": 'YYYY-MM-DD',
        "applyLabel": "确定",
        "cancelLabel": "取消",
        "fromLabel": "起始时间",
        "toLabel": "结束时间'",
        "weekLabel": "W",
        "daysOfWeek": ["日", "一", "二", "三", "四", "五", "六"],
        "monthNames": ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
        "firstDay": 1,
      
    };
    //初始化显示当前时间
    let date = new Date();
    let day = date.getDate();
    let month = date.getMonth()+1;
    let year = date.getFullYear();
    let minDate = year+"-"+month+"-"+day;
    //日期控件初始化
    $('#reservation').daterangepicker(
        {
            'locale': locale,
            showDropdowns:true,
            autoApply:false,
            singleDatePicker:true,
            opens:"left",
            startDate:minDate,
            minDate:minDate,
        }
   );
    $('#reservation').val("");

    $('#reservation2').daterangepicker(
            {
                'locale': locale,
                showDropdowns:true,
                autoApply:false,
                singleDatePicker:true,
                opens:"right",
                startDate:minDate,
                minDate: $('#reservation').val(),
            }
       );
    $('#reservation2').val("");

    $('#reservation1').daterangepicker(
            {
                'locale': locale,
                showDropdowns:true,
                autoApply:false,
                singleDatePicker:true,
                "opens":"left",
                startDate:minDate,
                minDate:minDate,
            }
       );
    $('#reservation3').daterangepicker(
            {
                'locale': locale,
                showDropdowns:true,
                autoApply:false,
                singleDatePicker:true,
                opens:"right",
                startDate:minDate,
                minDate:minDate,
            }
       );
}
$(document).ready(function() {
    init();
})