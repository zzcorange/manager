$(function() {
	//模拟登录
	$('#login').click(function () {
		var username = $.trim($('#username').val());
		if( username == ''){
			alert('请填写账号！');
			return
		}
		var password = $.trim($('#password').val());
		if( password == ''){
			alert('请填写密码！');
			return
		}
		$.cookie('loginMuser', username, {path: "/", expires: 0.02 });
		location.href = 'index.html';
	});

});