function initmenu(){
    $.ajax({
        url:getUrl(getRootPath()+"/common/menu/queryAll"),
        type:"GET",
        data:{},
        success:function(datas){
            var data = $.parseJSON(datas);
            var init="";
            $.each(data,function(i,n){
                init += '<div class="firstnav"> <a href="javascript:void(0)" class="leftnv2"  >'+n.name+'</a><ul class="secnav">';
                $.each(n.childMenu,function(j,m){
                    init +='<li class="nvhove"> <a href="javascript:goUrlInIframe(\''+m.url+'\');"  target="rightIfr">'+m.name+'</a> <span ></span> </li>';
                });
                init+="</ul></div>";
            });
            $("#leftc").append(init);
            <!------------左边导航效果----------------->
            $('.firstnav a').on("click",function(){
                if($(this).siblings('.secnav').is(':visible')){


                }
                else if($(this).siblings('.secnav').size() == 0){
                    $(this).parent('.firstnav').addClass('nvhove');
                    $(this).parent('.firstnav').siblings('.firstnav').removeClass('nvhove');
                    $(this).parent().siblings('.firstnav').children('.secnav').css('display','none');

                }
                else{
                    $(this).parent().siblings('.firstnav').children('.secnav').css('display','none');

                }
                $(this).siblings('.secnav').toggle(function(){});
                $(this).parent('.firstnav').siblings('.firstnav').removeClass('nvhove');
            });
            $('.secnav li').on("click",function(){
                $(this).addClass('nvhove').siblings('li').removeClass('nvhove');
                $(this).children('span').css('display','block');
                $(this).siblings('li').children('span').css('display','none');
            });
        }
    });

}
$(document).ready(function(){
    /**
     * init menu
     */
    initmenu();
});
