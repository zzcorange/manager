$(function(){
	
	//$("#verify_btn").remove();
	//$("#verify_btn").hide(
	
	var grid = $('#dataTable').datagrid({
		//url:'js/data.action',
		//title:'��������',
		data:list,
		pagination:true,
        sortName:"verify_id",
        sortOrder:"asc",
        width: window.document.body.offsetWidth,
        height: 250,
        rownumbers:true,
        //fitColumns:true,
        singleSelect:false,
	    frozenColumns:[[//�̶���
	       {field:'ck',checkbox:true,width:80},
	       {field:'verifyId',title:'��������',width:180},
	       {field:'objName',title:'���Ա���',width:120},
	       {field:'objId',title:'��������',width:100}
	     ]],
	    columns:[[
	        {field:'nameOfVerifyObj',title:'���Է���',width:100},
	        {field:'nameOfVerifyType',title:'չʾ����',width:120,styler:cellStyler},
	        {field:'state',title:'�Ƿ�ɸѡ����',width:120,formatter:formatState},
	        {field:'verifyResult',title:'״̬',width:100,formatter:formatVerifyResult},
	        {field:'applyPeople',title:'���״̬',width:80},
	        {field:'applyTime',title:'�Ƿ���޸�',width:150},
	        {field:'null',title:'�޸ļ�¼',width:80,formatter:tipsInfo}
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
 * չ������
 * @param row
 * @returns {String}
 */
function getDetailInfo(row){
	/*var html = '<table class="dv-table" border="0"><tr>';
	html+='<td class="dv-label">�����: </td><td>'+row.verifyPeople+'<td>';
	html+='<td class="dv-label">����˵��: </td><td>'+row.applyExplain+'<td></tr><tr>';
	html+='<td class="dv-label">���ʱ��: </td><td>'+row.verifyTime+'<td>';
	html+='<td class="dv-label">������: </td><td>'+row.verifySuggestion+'<td></tr></table>';
	return html;*/
}

function resetStyle(index,row){
    if (row.rightId%2==1){
        return 'background-color:#6293BB;color:#fff;font-weight:bold;';
    }
}

function formatState(val,row){
	if(val=='S0A'){
		return '�����';
	}
	if(val=='S0X'){
		return '�����';
	}
	return '-';
}

function formatVerifyResult(val,row){
	var result = "";
	if('N'==row.verifyResult){
		result = "��˲�ͨ��";
	}else if('Y'==row.verifyResult){
		result = "���ͨ��";
	}
	return result;
}

function tipsInfo(val,row){
	return '<a name="mytips"  href="#" title="������:'+row.createPeople+'@@����ʱ��:'+row.createTime+'@@����޸���:'+row.updatePeople+'@@����޸�ʱ��:'+row.updateTime+'">����</a>';
}


/**
 * �޸ļ�¼����
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
 * ����˴���
 */
function toVerify(){
    var rows = $('#dataTable').datagrid('getSelections');
    if (rows && rows.length==1){
    	if(rows[0].state!='S0A'){
    		$.messager.alert('ע��','�ü�¼����Ҫ���!','info');
    		return;
    	}
    	var msg = openShowModal(contextPath + '/goods/verify/toVerify.action?verifyId='+rows[0].verifyId, 700,300);
    	if(msg){
    		$('#dataTable').datagrid('reload');
    	}
    }else{
    	$.messager.alert('ע��','��ѡ��һ��������!','info');
    }
}

/**
 * ��������˴���
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
		title:'�������',
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
 * ��������ύ
 */
function submitForm(){
	$("#subForm").form('submit', {
		url:contextPath+'/goods/verify/batchVerify.action',
		onSubmit: function(){
			return $('#subForm').form('validate');
		},
		success:function(data){
			var results = splitReturnValue(data);
			$.messager.alert('ע��',results[1],'info');
			if (BooleanHelper.isTrue(results[0])){
				$('#batchWin').window('close');
				$('#dataTable').datagrid('reload');
			}
		}
	});
}