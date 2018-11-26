/*
	Custom checkbox and radio button - Jun 18, 2013
	(c) 2013 @ElmahdiMahmoud 
	license: http://www.opensource.org/licenses/mit-license.php
*/   
$('input[name="radio-btn"]').wrap('<div class="radio-btn"><i></i></div>');
$('input[name="rade"]').wrap('<div class="radio-btn"><i></i></div>');
$(".radio-btn").on('click', function () {
    var _this = $(this),
        block = _this.parent().parent();
    block.find('input:radio').attr('checked', false);
    block.find(".radio-btn").removeClass('checkedRadio');
    _this.addClass('checkedRadio');
    _this.find('input:radio').attr('checked', true);
});
$('input[name="check-box"]').wrap('<div class="check-box"><i></i></div>');
$.fn.toggleCheckbox = function () {
    this.attr('checked', !this.attr('checked'));
}
$('.check-box').on('click', function () {
    $(this).find(':checkbox').toggleCheckbox();
    $(this).toggleClass('checkedBox');
    var i=$(this).find(':checkbox').attr("wayid");
    
         $('.setting').eq(i).toggle();
   
});

/*----首页图表----*/
 if (document.getElementById('graph') != null) {
        var chart = echarts.init(document.getElementById('graph'));
        option = {

            legend: {
                data: ['新增', '修改', '删除', '占比'],
                left: 'right'
            },
            color: ['#94e1f1', '#66b1eb', '#f28986', '#66cc99'],
            xAxis: [{
                type: 'category',
                
                data: ['0:00','4:00',"8:00","12:00","16:00","20:00","24:00" ]
            }],
            yAxis: [{
                type: 'value',
                
                axisTick: {
                    show: false
                },
                axisLine: {
                    show: false
                },
                max:35,
                interval:5
            }, {
                type: 'value',

                splitLine: {
                    show: false
                },
                 axisLabel: {
                    show: false
                },
                axisLine: {
                    show: false
                },
                axisTick: {
                    show: false
                },
                min:1.95,
                max:2.02
            }],
            tooltip: {
                trigger: 'item',
                formatter: ' {b} {a} {c} ',
                extraCssText: 'text-align:center;',
                position: 'top'
            },
            series: [{
                name: '新增',
                type: 'bar',
                barWidth: 22,
                barGap: 0,
                data: [22, 35, 32, 17, 12, 30, 17]
            }, {
                name: '修改',
                type: 'bar',
                barWidth: 22,
                barGap: 0,
                data: [22, 16,32, 19, 14, 30,19]
            }, {
                name: '删除',
                type: 'bar',
                barWidth: 22,
                barGap: 0,
                data: [22, 22, 18, 21, 9, 18, 22]
            }, {
                name: '占比',
                type: 'line',
                showSymbol: false,
                yAxisIndex: 1,
                data: [1.968, 1.998, 1.988, 1.993, 1.99, 1.975, 1.98]
            }]
        };
         chart.setOption(option);
}
