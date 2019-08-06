var permission = '-1';	//权限
var itemId = '';        //菜单栏所选选项编号
var serverUrl = "http://210.30.128.69:8080/message/"	//服务器地址
var fileUrl = "http://210.30.128.69:8080/"	//文件地址
//用户名：username
//用户编号：userId
//是否已登录：isLogin

//设置网页缓存
function setCookie (name, value)
{
    //设置名称为name,值为value的Cookie
    var expdate = new Date();   //初始化时间
    expdate.setTime(expdate.getTime() + 30 * 60 * 1000);   //时间
    document.cookie = name+"="+value+";expires="+expdate.toGMTString()+";path=/";
    //即document.cookie= name+"="+value+";path=/";   时间可以不要，但路径(path)必须要填写，因为JS的默认路径是当前页，如果不填，此cookie只在当前页面生效！~
}

//获取网页缓存
function getCookie(cookieName)
{
    if (document.cookie.length>0)
    {
        cookieStart=document.cookie.indexOf(cookieName + "=")
        if (cookieStart!=-1)
        {
            cookieStart=cookieStart + cookieName.length+1
            cookieEnd=document.cookie.indexOf(";",cookieStart)
            if (cookieEnd==-1) cookieEnd=document.cookie.length
            return unescape(document.cookie.substring(cookieStart,cookieEnd))
        }
    }
    return ""
}

//删除用户缓存
function delCookie(name)
{
    var expdate = new Date();   //初始化时间
    expdate.setTime(expdate.getTime()-1);   //时间
    document.cookie = name+"="+""+";expires="+expdate.toGMTString()+";path=/";
}

//获取用户权限
function getPermission(){
    var permission = getCookie("permission");
    permission = '-1';
    return permission;
}

//获取用户名
function getUsername(){
    var username = getCookie("username");
    return username;
}

//获取页面跳转时传递的值
function getUrlParams(key) {
    var reg = new RegExp("(^|&)" + key + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) {
        return unescape(r[2]);
    }
    return null;
};

//线程休眠
function sleep(numberMillis) { var now = new Date(); var exitTime = now.getTime() + numberMillis; while (true) { now = new Date(); if (now.getTime() > exitTime) return true; } }


//跳转到指定网页
function goWebpage(url){
    window.location.href=url;
}


//管理员后台easyui左侧菜单栏选中方法
function selectItem(item) {
    console.log("selectItem:");
    console.log(item.id);
    console.log("permission="+permission);
//alert('点击了'+item.text);
    switch (item.text) {
        case '内容列表':
            //页面跳转
            window.location.href='./main.html';
            break;
        case '新增内容':
            window.location.href='./contentEdit.html';
            break;
        default:
            window.location.href='#';
            break;
    }
}