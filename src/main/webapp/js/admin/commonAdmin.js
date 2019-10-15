

//登出
function logout() {
    delCookie("username");	//用户名，根据此值获取权限
    delCookie("isLogin");	//已登录标记
    delCookie("userId");	//用户编号
    goWebpage("./login.html");
}


//设置左侧菜单栏默认选中项，默认为第一个（index=1）
function setItemSelected(itemId){
    console.log("leftMenu itemId:"+itemId);
    var menus = $('#left-sidemenu');
    var opts = menus.sidemenu("options");
    // if(itemId==null||itemId==undefined||itemId==""){
    //     var itemId = '_easyui_sidemenu_2';//默认选中第一个，如果id未自行定义，easyUI将默认使用_easyui_sidemenu_2作为第一个菜单项的id值
    // }
    changeMenuSelect(menus, opts, itemId);
}

//管理员后台首页easyui左侧菜单栏选中方法（其他页面自行覆盖此方法）
function selectItem(item) {
    console.log("selectItem:");
    console.log(item.id);
    console.log("permission="+permission);
//alert('点击了'+item.text);
    switch (item.id) {
        case '11':
            //页面跳转
            goWebpage('./main.html');
            break;
        case '12':
            goWebpage('./contentEdit.html');
            break;
        default:
            goWebpage('#');
            break;
    }
}



//初始化管理系统首页左侧菜单栏数据（权限交给后端处理，前端不再根据权限显示UI）
function initLeftItemData(){
    var leftMenuData;
    // switch(permission){
    //     case -1:
            leftMenuData = [{
                text: '内容管理',
                iconCls: 'icon-more',
                state: 'open',
                children: [{
                    id:'11',
                    text: '内容列表'
                },{
                    id:'12',
                    text: '新增内容'
                }]
            },{
                text: '留言管理',
                iconCls: 'icon-more',
                state: 'open',
                children: [{
                    id:'21',
                    text: '留言列表'
                }]
            },{
                text: '系统管理',
                iconCls: 'icon-more',
                state: 'open',
                children: [{
                    id:'31',
                    text: '访客记录'
                }]
            }];
            // break;
        // default:
        //     leftMenuData = [{
        //         text: '内容管理',
        //         iconCls: 'icon-more',
        //         state: 'open',
        //         children:[{
        //             id:'11',
        //             text: '内容列表'
        //         },{
        //             id:'12',
        //             text: '新增内容'
        //         }]
        //     }];
        //     break;
    // }
    console.log("左侧菜单内容为：");
    console.log(leftMenuData);
    return leftMenuData;

}