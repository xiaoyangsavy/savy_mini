//改变左侧菜单选中样式
    function changeMenuStyle(menuItem, opts, selectId) {
        menuItem.find("div.tree-node-selected").removeClass("tree-node-selected");
        var node = menuItem.tree("find", selectId);
        if (node) {
            $(node.target).addClass("tree-node-selected");
            opts.selectedItemId = node.id;
            menuItem.trigger("mouseleave.sidemenu");
        }

        changeMenuSelect(menuItem);
    }
	
	function changeMenuSelect(menus, opts, selectId) {
        var menutrees = menus.find(".sidemenu-tree");
        menutrees.each(function () {
            var menuItem = $(this);
            changeMenuStyle(menuItem, opts, selectId);
        });

        var tooltips = menus.find(".tooltip-f");
        tooltips.each(function () {
            var menuItem = $(this);
            var tip = menuItem.tooltip("tip");
            if (tip) {
                tip.find(".sidemenu-tree").each(function () {
                    changeMenuStyle($(this), opts, selectId);
                });
                menuItem.tooltip("reposition");
            }
        });
    }


//时间输入框格式化日期
	function myformatter(date){
			var y = date.getFullYear();
			var m = date.getMonth()+1;
			var d = date.getDate();
			return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
		}
		function myparser(s){
			if (!s) return new Date();
			var ss = (s.split('-'));
			var y = parseInt(ss[0],10);
			var m = parseInt(ss[1],10);
			var d = parseInt(ss[2],10);
			if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
				return new Date(y,m-1,d);
			} else {
				return new Date();
			}
		}