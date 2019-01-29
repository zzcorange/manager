$(document).ready(function(){
    $.ajax({
        url:getRootPath()+"/common/user/query",
        method:"get",
        data:{
            id:GetQueryString("id")
        },
        success:function(data){
            var user = $.parseJSON(data);
            $("#username").val(user.username);
            $("#password").val(user.password);
        }
    });
    $("#save").click(function(){
        var username = $("#username").val();
        var password = $("#password").val();
        var status = true;
        var confiredPassword = $("#confiredPassword").val();
        if(!username||username.length==0){
            layer.tips('用户名不能为空', '#username',{tipsMore:true});
            status = false;
        }
        if(!password||password.length==0){
            layer.tips('密码不能为空', '#password',{tipsMore:true});
            status = false;
            console.log("密码/密码不能为空");
        }
        if(!confiredPassword||confiredPassword.length==0){
            layer.tips('确认密码不能为空', '#confiredPassword',{tipsMore:true});
            status = false;
            console.log("密码/确认密码不能为空");
        }
        if(password&&confiredPassword&&password!=confiredPassword){
            layer.tips('密码/确认密码不匹配', '#password',{tipsMore:true});
            layer.tips('密码/确认密码不匹配', '#confiredPassword',{tipsMore:true});
            status = false;
        }
        console.log(status);
        if(!status) return;
        $.ajax({
            url:getRootPath()+'/common/user/modify',
            type:"POST",
            data:JSON.stringify({
                username:username,
                password:password,
                id:GetQueryString("id")
            }),
            contentType: 'application/json',
            success:function(data){
                alert("返回值："+data);
                var json = $.parseJSON(data);
                if(json.code.substr(0,1)!='-')
                window.parent.window.location.reload();
            }
        });
    });
});