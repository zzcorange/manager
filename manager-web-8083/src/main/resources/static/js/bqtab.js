
// 当前显示的iframe
var currentFrame = 'iframe1';
// 当前显示的tab页
var currentTab = 'tab1'; 

var blankPage = 'Indexwel.jsp';
var context = '';

var haveVisitedUrls = {}; // 格式：{iframeId:url}

$(function() {
	var $workpost = $('#workpostSelect option:selected');
	var contextPath = $('#contextPath').val();
	//  本地版启用
//	(function getCityId() {
//		DWREngine.setAsync(false);   
//		loginDwr.getCityId(function(cityIdMap) {		
//			$('#cityNamePlace').html(cityIdMap.nameOfCityId);
//		});
//		DWREngine.setAsync(true);  
//	})();
	$('#postOrgNamePlace').html($workpost.attr('orgName'));
//	$('#cityNamePlace').html($workpost.attr('cityName'));
	var currentPostId = $('#workpostSelect').val();
	$('#workpostSelect').change(function(e) {
		if (!window.confirm("切换岗位，将关闭所有已打开的窗口，是否真的要切换岗位？")) {
			$('#workpostSelect').val(currentPostId);
			return;
		}
		var $workpost = $('#workpostSelect option:selected');
		$('#mainForm').attr('action', contextPath + '/sysmgr/login/localLogin.action');
		$('#mainForm').submit();
	});
	
	$('#logout').click(function() {
		if(confirm('确定要退出系统?')) {
			$('#mainForm').submit();
		}
	});
	$('#mainForm').attr('action', contextPath + '/sysmgr/login/localLogout.action');
	
	
	//----------------TAB 页功能 -----------------------
	context = contextPath + '/';
	blankPage = context + blankPage;
	
	var capacity = 6; // 受限的iframe数量
	var currentNum = 1; // 当前显示的tab的数量
	var frames = ['iframe2', 'iframe3', 'iframe4', 'iframe5', 'iframe6']; // 待显示的iframe队列
	var tabs = ['tab1', 'tab2', 'tab3', 'tab4', 'tab5', 'tab6']; // 待显示的tab队列
	
	// 新增标签页
	$('#addIframe').click(function() {
		increaseCurrentNum();
		hideAddButtonWhenAbound();
		var iframe = frames.shift();
		var tab = tabs[currentNum - 1];
		$('#' + tab).attr('forFrame', iframe);
		changeShowTabClass(tab, true);
		changeShowIFrame(iframe);
		$.removeData($('#' + tab)[0], 'elems');
		$.data($('#' + tab)[0], 'elems', false);
		localizer(false);
	});
	
	$('#tabs li').dblclick(function(e) {
		$('.close', this).click();
	});
	// 关闭
	// FIXME 写得太乱了！！！
	$('#tabs .close').click(function(e) {
		e = e || window.event;
		e.stopPropagation();
		if (cannotCloseWhenOnlyOne()) {
			return;
		}
		
		var tab = $(this).parent().parent().attr('id');
		var iframe = $('#' + tab).attr('forFrame');
		$('#' + iframe).hide().attr('src', blankPage);
					
		var index = getTabIndex(tab);
		var currentIndex = getTabIndex(currentTab);
		
		if (index == currentIndex) { // 关闭正在查看的当前页面
			if (index == (currentNum - 1)) {
				changeShowTabClass(tabs[0], false);
			}
		} else if (index < currentIndex) { // 正在查看的页面在被关闭的页面的右边	
			changeShowTabClass(tabs[currentIndex - 1], false);
		}
		
		// tab左移, 只是最终关闭最左边的
		for (; index < currentNum - 1; index++) {
			$('span', $('#' + tabs[index])).text($('span', $('#' + tabs[index + 1])).text());
			$('#' + tabs[index]).attr('forFrame', $('#' + tabs[index + 1]).attr('forFrame'));
			$.data($('#' + tabs[index])[0], 'elems', $.data($('#' + tabs[index + 1])[0], 'elems'));
		}
		$('#' + tabs[currentNum - 1]).removeClass().hide();
		$.data($('#' + tabs[currentNum - 1])[0], 'elems', false);
		$('span', $('#' + tabs[currentNum - 1])).text('新标签页');
		var nextframe = $('#' + currentTab).attr('forFrame');
		currentFrame = nextframe;
		$('#' + currentFrame).show();
		
		decreaseCurrentNum();
		showAddButtonWhenHidden();
		frames.push(iframe);
		delete haveVisitedUrls[iframe];
		
		return false;
	});
	$('#tabs li').click(function() {
		var tab = $(this).attr('id');
		if (currentTab == tab) {
			return;
		}
		var iframe = $(this).attr('forFrame');
		changeShowIFrame(iframe, false);
		changeShowTabClass(tab, false);
		//resizeFrame(iframe);
		localizer($.data($('#' + tab)[0], 'elems'));
	});
	
	function increaseCurrentNum() {
		currentNum++;
	}
	function decreaseCurrentNum() {
		currentNum--;
	}
	function cannotCloseWhenOnlyOne() {
		return currentNum == 1;
	}
	function hideAddButtonWhenAbound() {
		if (currentNum < capacity) {
			return;
		}
		$('#addIframe').hide();
	}
	function showAddButtonWhenHidden() {
		if (currentNum < (capacity - 1)) {
			return;
		}
		$('#addIframe').show();
	}
	function getTabIndex(tab) {
		for (var i = 0; i < capacity; i++) {
			if (tab == tabs[i]) {
				return i;
			}
		}
		return 0;
	}	
	
	//首页展现
	 $("#mainbtn").click(function(){	 	
		var urllink='Indexwelnew.jsp';
		goToUrl("首页信息", urllink, false);
     });
	//载入首页
     $("#mainbtn").click();
   
     var quickSeachTip = '输入号码或客户名称';
     //处理快速搜索
     $("#searchImg").click(function(){
     	var textstr=$.trim($("#quickText").val());
     	if(textstr == quickSeachTip || textstr == ''){//按空处理
     		window.alert('请输入查询号码或名称!');
     	  	return false;
     	} else {
     	   $("#quickText").val(textstr);
     	}
        var urllink='webpage/quickSearch.action';
        var params ="?quickSearchType="+$("#quickSearchType").val()+"&quickText="+encodeURIComponent($("#quickText").val());
		goToUrlWithParams("快速搜索", urllink, params, false);
    }); 
    $('#quickText').keypress(function(e) {		
		if(e.keyCode == 13) { // the 'enter' key is pressed
			$("#searchImg").click();
			return false;
		}
	});
    $("#quickText").focus(function(){
    	var quicktext=$.trim($("#quickText").val());
    	if(quicktext!='' && quicktext!= quickSeachTip) {
         	return ;
       	}	
       $("#quickText").val('');
    });
    $("#quickText").blur(function(){
    	if($.trim($("#quickText").val())) {
         	return;
       	}	
       $("#quickText").val(quickSeachTip);
    });
	$('#myLoginInformation').click(function() {
		var $apDivInfo = $('#apDivInfo');
		if ($('#apDivInfo').is(':visible')) {
			$apDivInfo.hide();
		} else {
			$apDivInfo.show();
		}
	});
	 $(".header .user h1 span").mouseover(function(){
		$(this).addClass("hover");
			$(".gang").show();
		});
		
		
		$(".header .user h1").mouseleave(function(){
		$(".header .user h1 span").removeClass("hover");		
			$(".gang").hide();
		})
    $(".gang li",this).each(function(index){
			$(this).mouseover(function(){
				$(this).addClass("hover").siblings().removeClass("hover");
				
			});															 
		});	
		 $(".header .mes li").mouseover(function(){
		$(this).addClass("hover");

		});
		
		
		$(".header .mes li").mouseleave(function(){
		$(this).removeClass("hover");		

		})	
});

function getIFrameByUrl(url) {
	for (var iframeId in haveVisitedUrls) {
		if (url == haveVisitedUrls[iframeId]) {
			return iframeId;
		}
	}
	return null;
}

function goToUrl(name, url, elems) {
	goToUrlWithParams(name, url, false, elems);
}

/**
 * 在tab页里打开url地址
 * 
 * @param {String}
 *            name 显示在tab里的标题
 * @param {String}
 *            url 用于缓存打开过的该url; tab的链接是url+params 
 * @param {String}
 *            params 链接的参数, 主要是由于作用是前面的url相同时, 不管params, 都是在同一个tab页面里
 * @param {Array}
 *            elems 用于菜单项的反色定位处理
 */
function goToUrlWithParams(name, url, params, elems) {
	// 不重复打开相同的菜单项
	var iframe = getIFrameByUrl(url);
	if (!iframe) {
		if ($('#' + currentFrame).attr('src').indexOf(blankPage) < 0) { // 自动打开新的tab页面 
			$('#addIframe:visible').click();
		}
		iframe = currentFrame;
	}
	haveVisitedUrls[iframe] = url;
	
	var $tab = $('#tabs li[forFrame=' + iframe + ']');
	$.data($tab[0], 'elems', elems);
	changeShowIFrame(iframe, params ? url + params : url);
	changeShowTabClass($tab.attr('id'), false);
	$('span', $tab).text(name).attr('title', name);
	localizer(elems);
}

function changeShowIFrame(iframe, url) {
	$('#' + currentFrame).hide();
	currentFrame = iframe;
	if (url) {
		$('#' + currentFrame).attr('src', context + url);
	}
	$('#' + currentFrame).show();
}

function changeShowTabClass(tab, show) {
	$('#' + currentTab).removeClass('on');
	currentTab = tab;
	if (show) {
		$('#' + currentTab).show();
	}
	$('#' + currentTab).addClass('on');
}

//----------------------------others------------------------------------------
function resizeFrame(obj){
	
	
	$('#' + obj.id).height($(window).height() - 122);
}

// For poson:jdMenuTag
function jdMenuTagClicked(elem, url) {
	var elems = $(elem).parents('li');
	var span = $('span', elems[0]).get(0);
	goToUrl($(span).text(), url, elems);
}
// 定位菜单
function localizer(elems) {
	$('li', '#_menu_Div_').removeClass('jd_menu_selected');
	if (elems) {
		$(elems).filter('id^="topMenuItem"').addClass('jd_menu_selected');
	}
}

function reloginFromMainJsp() {
	$('#mainForm').attr('action', context + '/index.jsp');
	$('#mainForm').submit();
}

	

