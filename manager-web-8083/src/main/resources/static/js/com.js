// JavaScript Document
//创建html5元素 for IE6,7,8
var elems = ['section','article','nav','header','footer','aside','menu','figure','figcaption','time','mark','details','summary','hgroup','dialog'];
for (var i = 0, j = elems.length; i < j; i++) {
	document.createElement(elems [i]);
}

$(function(){
	//检查登录cookie
	/*
	if($.cookie('loginMuser')){
		$('#muser').attr('title',$.cookie('loginName'));
	}else{
		alert('您还未登录，请先登录！');
		location.href="login.html";
	}
	
	//退出登录
	$('#login_out').click(function () {
		if(confirm('确定要退出吗？')){
			$.cookie('loginMuser', null, {path: "/"});
			location.href="login.html";
		}
	})
	*/
	
	$(".left_tuw2").hover(function(){
	
		$(this).children(".tuhover").show();
 },function(){
  $(this).children(".tuhover").hide();

	});
	
	$('.moret').click(function(e) {
       
		$('#shoq').show();
		$('.moret1').show();
		 $('.moret').hide();
    });
	
	$('.moret1').click(function(e) {
       $('#shoq').hide();
		$('.moret').show();
		 $('.moret1').hide();
    });
	$('.qu_st').click(function(e) {
       
		$('.stepCon').hide();
		$('.qu_st1').show();
		 $('.qu_st').hide();
    });
	
	$('.qu_st1').click(function(e) {
       $('.stepCon').show();
		$('.qu_st').show();
		 $('.qu_st1').hide();
    });
	$('.morexq').hover(function(){
		$(this).children('.xq').show();
	},function(){
		$(this).children('.xq').hide();
	});
	
	$('.moresj').hover(function(){
		$(this).children('.wgsj').show();
	},function(){
		$(this).children('.wgsj').hide();
	});
	//下拉选择
		$('.addSSel').hover(function(){
			 $(this).children('dl').show();
		
	},function(){
		$('.addSSel dl').hide();
	})
	$('.addSSel dd').click(function(){
		$(this).parent().parent().find('span').html($(this).text());
		$('.addSSel dl').hide();
	})
	//多选下拉选择
	//多选下拉选择
	$('.moreSel').hover(function(){
			 $(this).children('dl').show();
		
	},function(){
		$('.moreSel dl').hide();
	})
	$('.moreSel dd').click(function(){
		var tmp = $(this).parent().parent().find('input').val();
		var tmpNew = "";
		if(tmp != ""){
			var tmps = tmp.split(",");
			
			var flag = false;
			for(var i=0;i<tmps.length;i++){
				if($(this).text() == tmps[i]){
					tmps[i]="";	
					flag = true;				
					break;
				}
			}
			
			for(var i=0;i<tmps.length;i++){
				if(tmpNew == "" && tmps[i]!=""){
					tmpNew = tmps[i];
					}
				else if(tmpNew != "" && tmps[i]!=""){
					tmpNew += "," +tmps[i];
					}
				
			}
			}
			if(!flag){
				$(this).removeClass('on');
				if(tmpNew == ""){
					tmpNew = $(this).text();
				}else{
					tmpNew += ","+$(this).text()
				}
			} else {
				$(this).removeClass('on');
			}
			$(this).parent().parent().find('input').val(tmpNew);
			var tmpNews = tmpNew.split(',');
			for(var j=0;j<tmpNews.length;j++){
				var $dl = $(this).parent().parent().find('dd').each(function(){
					if($(this).find('em').html()==tmpNews[j]){
						$(this).addClass('on');
					};
				});
			}

			
	})
	$('.lccont .open').click(function(){//展开
		$(this).toggleClass('up').next('.openInfo').toggle();
	});
	//多选下拉选择结束
	<!-------------属性组弹窗------------->
	$('.poptit h2').click(function(e) {
		if($(this).parent('.poptit').next('.shuxz').is(':visible')){
			$(this).children('a').children('img').removeAttr('src').attr('src','images/left2.png');
			
		}else{
			$(this).children('a').children('img').removeAttr('src').attr('src','images/down2.png');
			$(this).parent('.poptit').siblings('.poptit').children('h2').children('a').children('img').removeAttr('src').attr('src','images/left2.png');	
		}
		$(this).parent('.poptit').siblings('.shuxz').toggle(function(){});
    });
	
	$('.addedtpz').click(function(e) {
        $(this).parent().parent('.checkif').siblings('.addedt').show().children('h1').html();
		var til = $(this).children('img').attr('title').valueOf();
		$(this).parent().parent('.checkif').siblings('.addedt').children('h1').html(til)
		$(this).parent().parent('.checkif').siblings('.addedt').children('h2').html(til)
    });
	
	$('.xzcpgl li').hover(function(){
		$(this).children('.tub').show();	
	},function(){
		$(this).children('.tub').hide();
	});
	
	$('.editsd').click(function(e) {
        $('.addedt').show();
    });
	$('.xzglsp').click(function(e) {
        $('.addedt').show();
    });
	
	$('.selectDown').click(function(){
			$(this).next('.selectDropDown').toggle();
		});
	$('.selectDropDown a').click(function(){
		$(this).parent().hide().siblings('.gangw').val($(this).text());
		
	});
	
	$('#selfimg').click(function(){
			$(this).next('.selfimg').toggle();
		});
		
	$('.chobtn em').click(function(e) {
        if($(this).parent('.chobtn').next('.spzcon').is(':visible')){
			$(this).children('img').attr('src','images/left2.png');
		}
		else{
			$(this).children('img').attr('src','images/down2.png');
		}
		
		$(this).parent('.chobtn').next('.spzcon').toggle(function(){});
    });
	
	$('#fz1 em').click(function(e) {
        if($(this).parent('.chobtns').parent('td').parent('tr').next('tr').is(':visible')){
			$(this).children('img').attr('src','images/left2.png');
		}
		else{
			$(this).children('img').attr('src','images/down2.png');
		}
		
		$(this).parent('.chobtns').parent('td').parent('tr').siblings('.fzo').toggle(function(){});
    });
	
	$('#fz2 em').click(function(e) {
        if($(this).parent('.chobtns').parent('td').parent('tr').next('tr').is(':visible')){
			$(this).children('img').attr('src','images/left2.png');
		}
		else{
			$(this).children('img').attr('src','images/down2.png');
		}
		
		$(this).parent('.chobtns').parent('td').parent('tr').siblings('.fzt').toggle(function(){});
    });
	
	$('.tw_tab li a').click(function(e) {
        var i = $('.tw_tab li a').index(this);
		$(this).parent('li').parent('.tw_tab').siblings('.twzsx').eq(i).show().siblings('.twzsx').hide();
		$(this).addClass('sphover').parent('li').siblings('li').children('a').removeClass('sphover');
    });
	$(".tw_tab li a",this).each(function(index){
		      
			$(this).click(function(){
				$(this).addClass("sphover").siblings().removeClass("sphover");
				$(".twzsx").hide().eq(index).show();
			});															 
		});
	$('.b_suox').click(function(e) {
       // $('.suox').toggle(function(){});
	   $(this).parent('h1').siblings().toggle(function(){});
    });
	$('.suof').click(function(e) {
       // $('.suox').toggle(function(){});
	   if($(this).parent('h1').siblings().is(':visible')){
		   $(this).children('img').attr('src','images/left2.png');
		}
		else{
	  	   $(this).children('img').attr('src','images/down2.png');
		}
	   $(this).parent('h1').siblings().toggle();
    });
	
	//点击关闭
	$('.sptis').click(function(){
		$('#titsp').show();
		
	});
	
	//点击关闭
	$('.b_close').click(function(){
		$(this).parent().parent().hide();
		
	});
	
	$('.sharefx').click(function(e) {
        $('.qqkf').toggle(function(){});
    });
	$(".ewmxz li",this).each(function(index){
		      
			$(this).click(function(){
				$(this).addClass("on").siblings().removeClass("on");
				$(".selds").hide().eq(index).show();
			});															 
		});
});

//弹出层
function openBox(_box,_ifr){
	//添加半透明遮罩背景层
	if(0 == $('#mask').length){$('body').append('<div id="mask" style="position:absolute;top:0;left:0;z-index:300;background:#000;width:100%;filter:alpha(opacity=80);opacity:0.8;"></div>');}
	
	//显示层
	$('#mask,#'+_box+',#'+_ifr).show();
	//定位弹出层
	var domHtml = $('html')[0];// domHtml = document.documentElement
	var domBody = $('body')[0];// domBody = document.body
	$('#'+_box).css({position : 'absolute',zIndex : '9999'});
		$('#'+_box).css('top',Math.floor(domHtml.clientHeight / 2)-Math.floor($('#'+_box)[0].offsetHeight / 2)+Math.max(domHtml.scrollTop,domBody.scrollTop)+'px');
	$('#'+_box).css('left',Math.floor(domHtml.clientWidth / 2)-Math.floor($('#'+_box)[0].offsetWidth / 2)+Math.max(domHtml.scrollLeft,domBody.scrollLeft)+'px');
	//设置半透明遮罩背景层高度
	$('#mask').css('height',Math.max(Math.ceil(domHtml.clientHeight),Math.max(domHtml.scrollHeight,domBody.scrollHeight))+'px');
	
	//点击关闭
	$('.b_close,.b_close1').click(function(){
		$('#mask,#'+_box+',#'+_ifr).hide();
		
	});
}



//热门业务滚动
jQuery(function(){
	var indexLen=jQuery("#zdrems li").length;
	var showingNow=3;
	var myWidth=224;//就是BLACKQUOTE加上PADDING的宽度
	var myTotal=indexLen-showingNow;
	var myCount=0;
	
	jQuery("#ywright").click(function(){
		if (myCount<Math.ceil(myTotal/3)){
			myCount++;
			jQuery("#zdrems").animate({"margin-left":-myCount*myWidth*3},672);
		}
	});
	jQuery("#yeleft").click(function(){
		if (myCount>0){
			myCount--;	
			jQuery("#zdrems").animate({"margin-left":-myCount*myWidth*3},672);
		}
	});
});



//热门业务滚动
jQuery(function(){
	var indexLen=jQuery("#zdrems1 li").length;
	var showingNow=3;
	var myWidth=224;//就是BLACKQUOTE加上PADDING的宽度
	var myTotal=indexLen-showingNow;
	var myCount=0;
	
	jQuery("#ywright1").click(function(){
		if (myCount<Math.ceil(myTotal/3)){
			myCount++;
			jQuery("#zdrems1").animate({"margin-left":-myCount*myWidth*3},672);
		}
	});
	jQuery("#yeleft1").click(function(){
		if (myCount>0){
			myCount--;	
			jQuery("#zdrems1").animate({"margin-left":-myCount*myWidth*3},672);
		}
	});
});