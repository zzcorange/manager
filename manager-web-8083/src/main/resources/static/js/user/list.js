function register(){
    layer.open({
        type: 2,
        title: '新增用户',
        shadeClose: true,
        shade: 0.8,
        area: ['480px', '90%'],
        content: window.parent.getRootPath()+'/web/user/register.html' //iframe的url
    });
}