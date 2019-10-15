var permission = '-1';	//权限
//var itemId = '';        //菜单栏所选选项编号
var serverUrl = "http://localhost:8080/savy"	//服务器地址
var fileUrl = "http://localhost:8080"	//文件地址
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

//设置用户编号
function setUserId(userId) {
    setCookie("userId", userId);	//用户编号
}

//设置用户权限
// function setPermission(permission) {
//     setCookie("permission", permission);	//用户编号
// }

//设置用户名
function setUserName(username) {
    setCookie("username", username);	//用户名
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
    if(username == null || username == undefined || username == ''){
        username = "访客";    //访客
    }
    return username;
}

//获取用户编号
function getUserId(){
    var userId = getCookie("userId");
    if(userId == null || userId == undefined || userId == 0){
        userId = 0; //访客
    }
    return userId;
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

//跳转到指定网页,并制定是否打开为新页面
function goWebpage(url,isOpenNewPage){
    if(isOpenNewPage){
        window.open(url);
    }else{
    window.location.href=url;
    }
}



//将引号转义
function replaceQuotationMark(value){
    var value=value.toString().replace(new RegExp('(["\"])', 'g'),"\\\"");
    return value;
}


//获取首页顶部菜单项
function getHeadMenuItem() {
    //获取技术博客菜单项
    $.ajax({
        type: "GET",
        url: serverUrl + "/content/getClass?contentTypeId=1",
        contentType: "application/json",
        success: function (data) {
            console.log("call content/getClass");
            console.log(data);
            if (data.status == 200) {	//调用成功
                console.log("调用成功!");
                console.log("接口数据长度:" + data.data.length);
                var html = "";
                for (var i = 0, len = data.data.length; i < len; i++) {
                    console.log(data.data[i]);
                    var item = data.data[i];
                    //通过js跳转页面时需要加入项目二级地址savy，无法通过相对路径之间跳转
                    html += '<li id="' + item.contentClassId + '" class="menu-sub-item"><a href="'+serverUrl+'/view/admin/contentList.html?contentClassId='+item.contentClassId+'">' + item.contentClassName + '</a></li>';
                }
                document.getElementById("technologyBlogUl").innerHTML = html;
            } else {
                console.log(data.message);
            }
        },
        error: function (e) {
            console.log(e);
            console.log("接口调用失败,请重试!");
        }
    });
    //获取技术字典菜单项
    $.ajax({
        type: "GET",
        url: serverUrl + "/content/getClass?contentTypeId=2",
        contentType: "application/json",
        success: function (data) {
            console.log("call content/getClass");
            console.log(data);
            if (data.status == 200) {	//调用成功
                console.log("调用成功!");
                console.log("接口数据长度:" + data.data.length);
                var html = "";
                for (var i = 0, len = data.data.length; i < len; i++) {
                    console.log(data.data[i]);
                    var item = data.data[i];
                    html += '<li id="' + item.contentClassId + '" class="menu-sub-item"><a href="'+serverUrl+'/view/admin/contentList.html?contentClassId='+item.contentClassId+'">' + item.contentClassName + '</a></li>';
                }
                document.getElementById("technologyDictionaryUl").innerHTML = html;
            } else {
                console.log(data.message);
            }
        },
        error: function (e) {
            console.log(e);
            console.log("接口调用失败,请重试!");
        }
    });
}