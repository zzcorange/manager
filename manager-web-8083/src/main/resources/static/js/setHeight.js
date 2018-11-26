// JavaScript Document
$(function(){
	setHeight();
	setPpt('navtop');
	window.onresize=function(){
		setPpt('navtop');
		setHeight();
	}
});
function setHeight(){
	var cheight = document.documentElement.clientHeight;
	var conHeig = $('.h_top').height()+5;
	var cheight = cheight - conHeig;
	$('.content').css('height',cheight);
	$('.leftc').css('height',cheight);
	/*var leftn = 0;
	var leftnavH = 0;
		leftn = $('.leftc').is(':visible').children('.firstnav').size()
	   leftnavH =  $('.leftc').is(':visible').children('.firstnav').children('a').height()+2;	
	   alert(leftn);
	
	var leftHeight = leftn * leftnavH;
	var cheight1 = cheight -leftHeight;*/
	//$('.secnav').css('height',cheight1);
	$('.secnav').css('height','auto');
	cheight2 = cheight -0;
	$('.rightc').css('height',cheight2);
	$('.rigcon').css('height',cheight2);
	$('.rigcon iframe').css('height',cheight2);
	var mlHeignt = cheight2 - 45;
	$('.contentml').css('height',mlHeignt);
	$('.muleftc').css('height',mlHeignt);
	$('.murightc').css('height',mlHeignt);
}

function setPpt(ppt_id){	
	var indexLen=$('#'+ppt_id+' ul li').length;
	var danc = $('#'+ppt_id+' ul').width();
	var myWidth=parseInt($('#'+ppt_id+' ul li').css('width'))+parseInt($('#'+ppt_id+' ul li').css('paddingLeft'))+parseInt($('#'+ppt_id+' ul li').css('paddingRight'))+parseInt($('#'+ppt_id+' ul li').css('marginRight'));
	//alert(indexLen);
	var showingN= danc/myWidth;
	var showingN = parseInt(showingN);
	if(indexLen >= showingN){
		var showingNow = showingN;
		var myTotal=indexLen-showingNow;
		$('#'+ppt_id+' .prev').show();
		$('#'+ppt_id+' .next').show()
	}
	else{
		showingNow= $('#'+ppt_id+' ul li').length;
		var myTotal=0;
		$('#'+ppt_id+' .prev').hide();
		$('#'+ppt_id+' .next').hide();
	}
	var count = 0; 
	$('#'+ppt_id+' .next').click(function(){
		if(myTotal<=0){
			return;
		}else if (myTotal<showingN){
			$('#'+ppt_id+' ul').animate({'margin-left':-showingNow*myWidth},1000);
		}else{
			myCount = Math.ceil(myTotal/showingNow);
			if(count <= myCount){
				count++;
				$('#'+ppt_id+' ul').animate({'margin-left':-showingNow*myWidth*count},1000);
			}
		}
	});
	$('#'+ppt_id+' .prev').click(function(){
		if(myTotal<=0){
			return;
		}else if (myTotal<showingN){
			$('#'+ppt_id+' ul').animate({'margin-left':0},1000);
		}else{
			myCount = Math.ceil(myTotal/showingNow);
			if(count >= 1){
				count--;
				$('#'+ppt_id+' ul').animate({'margin-left':-showingNow*myWidth*count},1000);
			}
		}
	});
}


