$(document).ready(function(){
    /**
     * fitcolumns="true" pagination="true"
     data-options="singleSelect:true,fitcolumns:true,collapsible:true,url:'../../common/user/queryAll',method:'get'"
     */
    var url  = getRootPath()+"/common/user/queryAll";
    $('#datagrid').datagrid({
        border:false,
        fitColumns:true,
        singleSelect: true,
        pagination:true,
        collapsible:true,
        url:url,
        method:"get",
        columns:[[
            {field:'username',title:'用户名',width:50},
            {field:'createTime',title:'创建时间',width:50},
            {field:'opt',title:'操作',width:50,align:'center',
                formatter:function(value,rec){
                    var btn = '<a class="editcls" onclick="modify(\''+rec.id+'\',\''+rec.createTime+'\')" href="javascript:void(0)">编辑</a>';
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
        title: '新增用户',
        shadeClose: true,
        shade: 0.8,
        area: ['480px', '90%'],
        content: getRootPath()+'/web/user/register.html' //iframe的url
    });
}
function modifyColumn(val,row,index){
    // return '<button >修改</button>';
    console.log(row);
    return '<a href="javascript:void(0)" class="easyui-menubutton " data-options="menu:\'#mm1\',iconCls:\'icon-edit\'" onclick="modify(\''+row.id+'\')"><span class="l-btn-icon icon-edit">&nbsp;</span>Edit</a>';
}
function modify(id){
    layer.open({
        type: 2,
        title: '修改用户',
        shadeClose: true,
        shade: 0.8,
        area: ['480px', '90%'],
        content: getRootPath()+'/web/user/modify.html'+"?id="+id //iframe的url
    });
}