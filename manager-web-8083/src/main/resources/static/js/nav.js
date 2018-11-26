// JavaScript Document

$(function(){
	
	$('.tufu').mouseover(function(){
		$(this).find('span').show()
		 });
		$('.tufu').mouseout(function(){
		$(this).find('span').hide()
		 });
	
	
	<!------------左边导航效果----------------->
	$('.firstnav a').click(function(){
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
	$('.secnav li').click(function(){
		$(this).addClass('nvhove').siblings('li').removeClass('nvhove');
		$(this).children('span').css('display','block');
		$(this).siblings('li').children('span').css('display','none');
	});
	$(".logtab li",this).each(function(index){
		      
			$(this).click(function(){
				$(this).addClass("tabcur").siblings().removeClass("tabcur");
				$(".logtbl").hide().eq(index).show();
			});															 
		});
		$(".ewmxz li",this).each(function(index){
		      
			$(this).click(function(){
				$(this).addClass("on").siblings().removeClass("on");
				$(".selds").hide().eq(index).show();
			});															 
		});
		$(".payjg li",this).each(function(index){
		      
			$(this).hover(function(){
				$(this).addClass("on");
				
			});	
			$(this).mouseleave(function(){
				$(this).removeClass("on");
				
			});												 
		});
	$('#morecity').click(function(){
		$("#morecity").hide()
		$(".moreds").show()
		$("#city").show()
	});
	$('#city').click(function(){
		$("#city").hide()
		$(".moreds").hide()
		$("#morecity").show()
	});
});
<!---------------菜单--------------------->
$(function(){
		
	<!--------------------------------------->
	$('.firtml>li>a').click(function(e) {
		if($(this).parent('li').next('.secondml').is(':visible')){
			$(this).children('img').removeAttr('src').attr('src','../images/fiej.png');
		}
		else{
			$(this).children('img').attr('src','../images/cerj.png');
		}
		 $(this).parent('li').next('.secondml').toggle(function(){});
    }); 

	
	$('.secondml>li>a').click(function(e) {
		if($(this).next('.thirdml').is(':visible')){
			$(this).children('img').removeAttr('src').attr('src','../images/fiej.png');
		}
		else{
			$(this).children('img').attr('src','../images/cerj.png');
		}
        $(this).next('.thirdml').toggle(function(){});
    });
	
	/*$('.firtml>li>a').toggle(function(){
			$(this).children('img').attr('src','images/fiej.png');
		},function(){
			$(this).children('img').attr('src','images/cerj.png');
		});
		*/
		
});

$(function(){
	$('.xsp_ywx p').click(function(e) {
       
	    $(this).next('table').toggle().siblings('table').hide();
    });
	
});
<!---------------面包屑菜单--------------------->
$(function(){
		
	<!--------------------------------------->
	$('.firtmb>li>a').click(function(e) {
		if($(this).parent('li').next('.secondmb').is(':visible')){
			$(this).children('img').removeAttr('src').attr('src','../images/fiej.png');
		}
		else{
			$(this).children('img').attr('src','../images/cerj.png');
		}
		 $(this).parent('li').next('.secondmb').toggle(function(){});
    }); 

		
	$('.secondmb>li>a').click(function(e) {
		if($(this).parent('li').next('.thirdmb').is(':visible')){
			$(this).children('img').removeAttr('src').attr('src','../images/fiej.png');
		}
		else{
			$(this).children('img').attr('src','../images/cerj.png');
		}
		 $(this).parent('li').next('.thirdmb').toggle(function(){});
    });
	
	$('.thirdmb>li>a').click(function(e) {
		if($(this).parent('li').next('.fourmb').is(':visible')){
			$(this).children('img').removeAttr('src').attr('src','../images/fiej.png');
		}
		else{
			$(this).children('img').attr('src','../images/cerj.png');
		}
		 $(this).parent('li').next('.fourmb').toggle(function(){});
    });
	
	$('.fourmb>li>a').click(function(e) {
		if($(this).parent('li').next('.fivemb').is(':visible')){
			$(this).children('img').removeAttr('src').attr('src','../images/fiej.png');
		}
		else{
			$(this).children('img').attr('src','../images/cerj.png');
		}
		 $(this).parent('li').next('.fivemb').toggle(function(){});
    });
	
	/*$('.firtml>li>a').toggle(function(){
			$(this).children('img').attr('src','images/fiej.png');
		},function(){
			$(this).children('img').attr('src','images/cerj.png');
		});
		*/
});