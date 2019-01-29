var zTree;
var rMenu;
$(document).ready(function(){
    $.ajax({
        url:getUrl("/common/group/queryOne"),
        type:"GET",
        data:{
            id:GetQueryString("id")
        },
        async:false,
        success:function(data){
            var  zNodes =$.parseJSON(data);
            if(zNodes.code&&zNodes.code<0){
                layer.msg(zNodes.message);
            }else{
                var group = $.parseJSON(zNodes.message);
                $("#name").val(group.name);
            }

        }
    });
    var setting = {
        check: {
            enable: true
        },
        data: {
            simpleData: {
                enable: true
            }
        }
    };
    $.ajax({
        url:getUrl("/common/group/queryAllMenu"),
        type:"GET",
        data:{
            id:GetQueryString("id")
        },
        async:false,
        success:function(data){
           var  zNodes =$.parseJSON(data);
            zNodes= openNode(zNodes);
            $.fn.zTree.init($("#treeDemo"), setting, zNodes);
            zTree = $.fn.zTree.getZTreeObj("treeDemo");

        }
    });


    rMenu = $("#rMenu");
    $("#save").click(function(){
        save();
    });
});
function openNode(node) {
    $.each(node,function(i,n){
      n.open=true;
    });
    return node;
}
function getSelect(){
    var data = [];
    data = getSelectNode(zTree.getNodes(),data);
    console.log(data);
    return data;
}
function getSelectNode(nodes,data){
    $.each(nodes,function(i,n){
        if(!n.children||n.children.length==0){
            if(n.checked){
               data.push(n.id);
            }
        }else{
            data = getSelectNode(n.children,data);
        }
    });
    return data;
}
function save(){
    var data = getSelect();
    if(data.length==0){
        layer.msg("请选择可访问的菜单");
        return;
    }
    var name = $("#name").val();
    if(!name||name.length==0){
        layer.tips("权限组名不能为空","#name");
        return;
    }
    $.ajax({
        type:"POST",
        data:JSON.stringify({
            menulist:data,
            name:name
        }),
        contentType: 'application/json',
        url:getUrl("/common/group/addGroup"),
        success:function(data){
            alert(data);
        }
    });


}