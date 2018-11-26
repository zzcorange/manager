//列表查询函数
function commonSearch(gridId, formId, url) {
	if(typeof(url)!="undefined"){
		$('#'+gridId).datagrid({ 
			url:url,
			queryParams:serializeObject($('#' + formId)),
			method:"post"
		});
	}else{
		$('#'+gridId).datagrid('load',serializeObject($('#' + formId)));
	}
}

/**
 * 将form表单元素的值序列化成对象
 * 
 * @returns object
 */
serializeObject = function(form) {
	var o = {};
	$.each(form.serializeArray(), function(index) {
		if (o[this['name']]) {
			o[this['name']] = o[this['name']] + "," + this['value'];
		} else {
			o[this['name']] =this['value'];
		}
	});
	return o;
};