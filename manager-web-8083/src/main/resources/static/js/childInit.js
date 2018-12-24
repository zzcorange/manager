$(document).ready(function(){
    var menuLocation = "<a href='javascript:goIndex()'>首页</a>/";
   menuLocation +=  window.parent.getMenuLocation();
   $("#menulocation").html(menuLocation);
});
function goIndex(){
    window.parent.window.location.href=window.parent.getRootPath()+"/web/index.html";
}