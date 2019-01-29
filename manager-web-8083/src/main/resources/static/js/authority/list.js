$(document).ready(function(){
    /**
     * fitcolumns="true" pagination="true"
     data-options="singleSelect:true,fitcolumns:true,collapsible:true,url:'../../common/user/queryAll',method:'get'"
     */
    var url  = getRootPath()+"/common/group/queryAll";
    $.fn.layout.defaults="center";
    $('#datagrid').datagrid({
        border:false,
        fitColumns:true,
        singleSelect: true,
        pagination:true,
        collapsible:true,
        url:url,
        method:"get",
        columns:[[
            {field:'name',title:'权限名',width:50},
            {field:'createTime',title:'创建时间',width:50},
            {field:'modifyTime',title:'修改时间',width:50},
            {field:'opt',title:'操作',width:50,align:'center',
                formatter:function(value,rec){
                    var btn = '<a class="editcls" onclick="modify(\''+rec.id+'\')" href="javascript:void(0)">编辑</a>';
                    return btn;
                }
            }
        ]],
        onLoadSuccess:function(data){
            $('.editcls').linkbutton({text:'编辑',plain:true,iconCls:'icon-edit'});
        }
    });
});
function register(){
    layer.open({
        type: 2,
        title: '新增权限组',
        shadeClose: true,
        shade: 0.8,
        area: ['480px', '90%'],
        content: getRootPath()+'/web/authority/add.html' //iframe的url
    });
}
function modify(id){
    layer.open({
        type: 2,
        title: '修改权限组',
        shadeClose: true,
        shade: 0.8,
        area: ['480px', '90%'],
        content: getRootPath()+'/web/authority/modify.html?id='+id //iframe的url
    });
}