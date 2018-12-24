function initmenu(){
    $.ajax({
        url:getUrl(getRootPath()+"/common/menu/queryAll"),
        type:"GET",
        data:{},
        success:function(datas){
            var data = $.parseJSON(datas);
            var init="";
            $.each(data,function(i,n){
                init += '<div class="firstnav" menu_id="'+n.id+'" parent_id="'+n.parent_id+'" name="'+n.name+'"> <a href="javascript:void(0)" class="leftnv2"  >'+n.name+'</a><ul class="secnav">';
                $.each(n.childMenu,function(j,m){
                    init +='<li class="nvhove"  menu_id="'+m.id+'" parent_id="'+m.parent_id+'" name="'+m.name+'"> <a href="javascript:goUrlInIframe(getRootPath() +\''+m.url+'\');"  target="rightIfr">'+m.name+'</a> <span ></span> </li>';
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
function getMenuLocation(){
    var menu = [];
    var tempmenu = $(".nvhove");
    if(!tempmenu||tempmenu.length>1)
        return "null";
    if(tempmenu&&tempmenu.length>0){
        menu[0]=tempmenu.attr("name");
    }
    console.log("--"+tempmenu.attr("parent_id"));
    if(tempmenu&&tempmenu.attr("parent_id")&&tempmenu.attr("parent_id")!='0')
        tempmenu = $("[menu_id='"+tempmenu.attr("parent_id")+"']");
    else tempmenu = null;
    while(tempmenu&&tempmenu.length>0){
        menu[menu.length] = tempmenu.attr("name");
        if(tempmenu&&tempmenu.attr("parent_id")&&tempmenu.attr("parent_id")!='0')
        tempmenu = $("[menu_id='"+tempmenu.attr("parent_id")+"']");
        else break;
    }
    var answer = "";
    for(var i = menu.length;i>0;i--){
        answer+= menu[i-1]+"/";
    }
    if(answer.length>0) answer  = answer.substr(0,answer.length-1);
    return answer;
}
$(document).ready(function(){
    /**
     * init menu
     */
    initmenu();
});
