/*

    /!*首页按钮
    * 函数功能1：如果总页数小于3（3表示有3个单独页标签），则去掉多余的单独页标签
    * 函数功能2：指定当前页（第一页）为高亮页,去除其他高亮页
    * 函数功能3：给剩下的单独页指定相应的页数*!/
    $("#page1").click(function () {
        //指定当前页（第一页）为高亮页
        var $page3=$("#page3");
        var $page4=$("#page4");
        var $page5=$("#page5");
        if(!$page3.parent().hasClass("active")){
            $page3.parent().addClass("active");
        }
        //移除其他单独页的高亮
        if($page4.parent().hasClass("active")){
            $page4.parent().removeClass("active");
        }
        if($page5.parent().hasClass("active")){
            $page5.parent().removeClass("active");
        }
        //判断总页数是否大于3
        if(${employee.pageTotal==1}){
            $page3.parent().css("display","none");
            $page4.parent().css("display","none");
            $page5.find("span").html(1);
            $page5.parent().addClass("active");
        }else if(${employee.pageTotal==2}){
            $page3.parent().css("display","none");
            $page4.find("span").html(1);
            $page5.find("span").html(2);
        }else{

        }
    })
*/
