var $modalWin_ = {};
$modalWin_.win = null;
$modalWin_.event = null;
$modalWin_.type = null;
$modalWin_.iframeName = null;
$modalWin_.closeType = null;

$modalWin_.openWin = function(title,url,width,height,event,type,data){
	$modalWin_.win = null;
    $modalWin_.event = null;
    $modalWin_.type = null;
    $modalWin_.iframeName = null;
    $modalWin_.closeType  = null;
    
   if(type=="1"){
	   $modalWin_.win =window.parent.createWindowDiv(window.name,1);
	   $modalWin_.type=1;
    }else if(type==null || type=='0'){
		$modalWin_.win = createWindowDiv(window.location.href,0);
		$modalWin_.type=0;
	}
	if(event){
		$modalWin_.event = event;
	}
	var total_url = url+"&"+formateDateNoSplit(new Date());
	var frame = $("<iframe></iframe>");
	frame.attr('name',"sub_frame_"+formateDateNoSplit(new Date()));
	$modalWin_.iframeName = frame.attr('name');
	if(width){
		frame.css("width",width);
	}
	if(height){
		frame.css("height",height);
	}
	
	frame.appendTo($modalWin_.win);
	frame.attr('src',url);
	var tools_bar = null;
	if(data){
		tools_bar ={iconCls:'icon-help',
		    		handler:function(){
		    		if(data){
		    			var h_url=contextPath+"/help/help.html?url="+data['helpUrl']+"&code="+data['helpCode'];
			    		var h = $('<a target="_blank" href="'+h_url+'"></a>');
			    		var sp = $('<span>系统帮助</span>');
			    		sp.appendTo(h);
			    		var body = $(document.body);
			    		h.appendTo(body);
			    		h.hide();
			    		sp.click();
		    		}
		    	}
		};
	}
	
	$modalWin_.win.window({
	title:title||"new",
	collapsible:false,
	tools:tools_bar==null?null:[tools_bar],
	//noheader:true,
    modal:true,
	minimizable:false,
	maximizable:false,
	onClose:function(){
		$(this).window('clear');
		if($modalWin_.type=1){
	    	window.parent.removeWinDiv();
	    }else{
	    	removeWinDiv();
	    }
		if("manual"!=$modalWin_.closeType){
			executeWinEvent(null);
		}
	}
	});
}

function executeWinEvent(data){
	if($modalWin_.event){
		$modalWin_.event(data);
	}
}

$modalWin_.closeWin = function(data){
	$modalWin_.closeType = "manual";
	$modalWin_.win.window('close');
	executeWinEvent(data);
}

function openModalWin(title,url,width,height,event,type){
	var t_type="1";
	if(type){
		t_type = type;
	}
   $modalWin_.openWin(title,url,width,height,event,t_type);
}

/**
 * 
 * @param title 窗口标题
 * @param element  打开窗口的控件信息(按钮)
 * @param param 业务参数(可为null)
 * @param width  窗口宽度
 * @param height 窗口高度
 * @param event  回调函数
 */
function openWin(title,element,param,width,height,event){
	var data = element;
	var url  = contextPath+data['childUrl'];
	if(url.indexOf("?")>0){
		url+="&modId="+data['childModId'];
	}else{
		url+="?modId="+data['childModId'];
	}
	if(param){
		url+="&"+param;
	}
	 $modalWin_.openWin(title,url,width,height,event,"1",data);
}

/**
 * 获取打开窗口的iframe name
 * @returns
 */
function getOpenWinName(){
	return $modalWin_.iframeName;
}



function closeModalWin(data){
	var divs = $(window.parent.document).find("div[name=_common_win_]");
	var type = divs.eq(divs.length-1).attr('modal');//最后一个
	if(type=="1"){
		var name = decodeURIComponent(divs.eq(divs.length-1).attr('framename'));
		//$(window.parent.document).find("iframe:visible").eq(0)[0].contentWindow.$modalWin_.closeWin(data);
		var frames = $(window.parent.document).find("iframe");
		var target = null;
		for(var index=0;index<frames.length;index++){
			var t_name = $(frames[index]).attr('name');
			if(name==t_name){
				target = frames[index];
				break;
			}
		}
		target.contentWindow.$modalWin_.closeWin(data);
		
	}else if(type=="0"){
		window.parent.$modalWin_.closeWin(data);
	}else{
		printMsg('不支持的类型.','error');
	}
	
}

function removeWinDiv(){
	var divs = $("div[name=_common_win_]");
	var size = divs.length;
	divs.eq(size-1).remove();
	if(size==1){
		var   re_div = $("div[name=_all_win_div_]").nextAll("div");
		for(var index=0;index<re_div.length;index++){
			if($(re_div[index]).attr("lang")){
				//排除日期控件,其他div都删除
				continue;
			}
			$(re_div[index]).remove();
		}
	}
}

function createWindowDiv(name,type){
	var p_div = $("div[name=_all_win_div_]");
	if(p_div.length<1){
		p_div = $("<div name='_all_win_div_'></div>");
		p_div.appendTo($(document.body));
	}
	var div = $("<div name='_common_win_'  modal='"+type+"' framename='"+name+"'></div>");
	div.appendTo(p_div);
	return div;
}

function printMsg(msg,type){
	try{
		if("error"==type){
			console.error(msg);
		}else{
			console.warn(msg);
		}
		
	}catch(ex){}
}

/**
 * 跳转按钮页面
 * @param btn 按钮信息
 * @param param 业务参数
 */
function gotoBtnPage(btn,param){
	var frame = $(window.parent.document).find("iframe[name="+window.name+"]").eq(0);
	var url = contextPath+btn['childUrl']+"?modId="+btn['childModId']+"&parentModId="+btn['modId']+"&"+param;
	frame.attr('src',url);
	//window.location.href =url+"&"+param;
}