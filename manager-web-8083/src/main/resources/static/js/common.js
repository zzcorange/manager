function getRootPath() {
    var curWwwPath = window.document.location.href;
    var pathName = window.document.location.pathname;
    var pos = curWwwPath.indexOf(pathName);
    return curWwwPath.substring(0, pos);
}
function getUrl(url){
    if(url.indexOf("?")!=-1){
        return url+"&token="+GetQueryString("token");
    }
    return url+"?token="+GetQueryString("token");
}
function goUrlInIframe(url){
    $("#iframe").attr("src",getUrl(url));
}
function GetQueryString(name)
{
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)return  unescape(r[2]); return null;
}