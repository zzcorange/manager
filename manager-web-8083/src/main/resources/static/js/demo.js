$(function(){
	
	//$("#verify_btn").remove();
	//$("#verify_btn").hide();
	
	var grid = $('#dataTable').datagrid({
		//url:'js/data.action',
		//title:'测试例子',
		data:list,
		pagination:true,
        sortName:"verify_id",
        sortOrder:"asc",
        width: window.document.body.offsetWidth,
        height: 250,
        rownumbers:true,
        //fitColumns:true,
        singleSelect:false,
	    frozenColumns:[[//固定列
	       {field:'ck',checkbox:true,width:80},
	       {field:'verifyId',title:'属性名称',width:180},
	       {field:'objName',title:'属性编码',width:120},
	       {field:'objId',title:'属性类型',width:100}
	     ]],
	    columns:[[
	        {field:'nameOfVerifyObj',title:'属性分类',width:100},
	        {field:'nameOfVerifyType',title:'展示类型',width:120,styler:cellStyler},
	        {field:'state',title:'是否筛选条件',width:120,formatter:formatState},
	        {field:'verifyResult',title:'状态',width:100,formatter:formatVerifyResult},
	        {field:'applyPeople',title:'审核状态',width:80},
	        {field:'applyTime',title:'是否可修改',width:150},
	        {field:'null',title:'修改记录',width:80,formatter:tipsInfo}
	   ]],
	   rowStyler: resetStyle,
	   onLoadSuccess:showTips,
	  /* view: detailview,
	   detailFormatter:function(index,row){
		   getDetailInfo(row);
           return getDetailInfo(row);
       },*/
       onSelect:changeBtn,
       onUnselect:changeBtn,
       onSelectAll:changeBtn,
       onUnselectAll:changeBtn,
	});
	
	$("#searchBtn").click(function(){
		commonSearch("dataTable","searchForm");
	});
});

function changeBtn(index,row){
	$("#grid_btn a").show();
	var rows = $('#dataTable').datagrid('getSelections');
	for(var i=0;i<rows.length;i++){
		if(rows[i].state!='S0A'){
			$("#verify_btn").hide();
			$("#batch_btn").hide();
		}
	}
}

/**
 * 展开详情
 * @param row
 * @returns {String}
 */
function getDetailInfo(row){
	/*var html = '<table class="dv-table" border="0"><tr>';
	html+='<td class="dv-label">审核人: </td><td>'+row.verifyPeople+'<td>';
	html+='<td class="dv-label">申请说明: </td><td>'+row.applyExplain+'<td></tr><tr>';
	html+='<td class="dv-label">审核时间: </td><td>'+row.verifyTime+'<td>';
	html+='<td class="dv-label">审核意见: </td><td>'+row.verifySuggestion+'<td></tr></table>';
	return html;*/
}

function resetStyle(index,row){
    if (row.rightId%2==1){
        return 'background-color:#6293BB;color:#fff;font-weight:bold;';
    }
}

function formatState(val,row){
	if(val=='S0A'){
		return '待审核';
	}
	if(val=='S0X'){
		return '已审核';
	}
	return '-';
}

function formatVerifyResult(val,row){
	var result = "";
	if('N'==row.verifyResult){
		result = "审核不通过";
	}else if('Y'==row.verifyResult){
		result = "审核通过";
	}
	return result;
}

function tipsInfo(val,row){
	return '<a name="mytips"  href="#" title="创建人:'+row.createPeople+'@@创建时间:'+row.createTime+'@@最近修改人:'+row.updatePeople+'@@最近修改时间:'+row.updateTime+'">详情</a>';
}


/**
 * 修改记录详情
 * @param data
 */
function showTips(data){
	var list = $('a[name=mytips]');
	$.each(list,function(index,val){
		var title = $(this).attr('title')
		var vals = title.split("@@");
		var html="";
		for(var i=0;i<vals.length;i++){
			html+=vals[i]+"<br/>"
		}
		$(this).tooltip({
			 position: 'right',
			 content: html,
			 onShow: function(){
			 $(this).tooltip('tip').css({
				 backgroundColor: 'white',
				 borderColor: '#666'
			 	});
			 }
		});
		
	});
}


function cellStyler(value,row,index){
    if (value == 'attr'){
        return 'background-color:#ffee00;color:red;';
    }
}

/**
 * 打开审核窗口
 */
function toVerify(){
    var rows = $('#dataTable').datagrid('getSelections');
    if (rows && rows.length==1){
    	if(rows[0].state!='S0A'){
    		$.messager.alert('注意','该记录不需要审核!','info');
    		return;
    	}
    	var msg = openShowModal(contextPath + '/goods/verify/toVerify.action?verifyId='+rows[0].verifyId, 700,300);
    	if(msg){
    		$('#dataTable').datagrid('reload');
    	}
    }else{
    	$.messager.alert('注意','请选择一项进行审核!','info');
    }
}

/**
 * 打开批量审核窗口
 */
function toBatchVerify(){
	 var rows = $('#dataTable').datagrid('getSelections');
	 
	 
	 var names = rows[0].objName;
	 var verifyIds = rows[0].verifyId;
	 for(var index=1;index<rows.length;index++){
		 names+=","+rows[index].objName;
		 verifyIds+=","+rows[index].verifyId;
	 }
	 
	$("#batchWin").window("clear");
	$("#batchWin").window({
		title:'批量审核',
		width:800,
		height:320,
		 href:'load.htm',
		 onLoad:function(){
			 	$("#batch_objName").text(names);
			 	$("#batch_verifyIds").val(verifyIds);
		 }
	});
}


/**
 * 批量审核提交
 */
function submitForm(){
	$("#subForm").form('submit', {
		url:contextPath+'/goods/verify/batchVerify.action',
		onSubmit: function(){
			return $('#subForm').form('validate');
		},
		success:function(data){
			var results = splitReturnValue(data);
			$.messager.alert('注意',results[1],'info');
			if (BooleanHelper.isTrue(results[0])){
				$('#batchWin').window('close');
				$('#dataTable').datagrid('reload');
			}
		}
	});
}