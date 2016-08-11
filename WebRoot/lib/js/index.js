/**
 * Created by 池长城 on 14-5-1.
 */

$(function(){
    //取消长按弹出系统功能菜单效果
    document.body.style.webkitUserSelect='none';

    $(".attention h3").length>0 && $(".attention h3").unbind("click").click(function(){
        $(this).removeClass().addClass('curr')
        var arrow = $(this).find('b');
        if(arrow.text() == '+')
            arrow.text('-');
        else
            arrow.text('+');
        $(this).next().toggle();
    });
    $(".tabbox").length>0 && $(".tabbox li").unbind('click').click(function(){
        $(this).addClass('select').siblings().removeClass();
        $('.tablist ul').eq($(this).index()).show().siblings().hide();
    });
    //点赞功能ajax
    $(".btn-like").unbind('click').click(function(){
        var _this = $(this);
        $.post("/ajax/userlike", { itemid: _this.attr("item-id"), r:Math.random() }, function(data){
            if(data>0){
                $('.num-like').text( parseInt($('.num-like').text())+1 );   //点赞数加1
            }else
            {
                alert(data);
            }
        });
    });

    //应用下载数ajax
    $(".btn-down2").unbind('click').click(function(){
        var _this = $(this);
        $.post("/ajax/userdown", { itemid: _this.attr("item-id"), r:Math.random() }, function(data){
            if(data>0){
                $('.num-down').text( parseInt($('.num-down').text())+1 );   //下载数加1
            }
            //if(_this.attr("item-url").length>0)
            //    location.href = _this.attr("item-url");
        });
    });





    bindAttention();
});
//关注新闻栏目
function bindAttention()
{
    $(".btn-attention").unbind('click').click(function(){
        var _this = $(this);
        $.post("/ajax/userattention", { latticeid: _this.attr("item-id"), r:Math.random() }, function(data){
            if(data>0){
                _this.text('已关注');
            }else if(data == -3){
                location.href='/user/login?showType=600016';
            }else{
                _this.text('+关注');
            }
        });
    });
}

function ajaxFn(jsonConfig) {
    var config = {
        obj: null,
        action: null,
        pageno:1,
        rand: Math.random()
    }
    $.extend(config, jsonConfig);
    var pagebtn = $(".btn-more");
    var currPage = parseInt(pagebtn.attr("currpage"));
    config.pageno = parseInt(currPage);
    pagebtn.text("数据加载中，请稍候...");
    $.ajax({
        type: 'POST',
        url: config.action,
        data: config,
        success: function (response) {
            if(response == "unlogin"){
                $(config.obj).append('<div class="unlogin">你还为登录，请先<a href="/user/login">登录</a>！</div>');
            }else if (response != "") {
                $(config.obj).append(response);
                pagebtn.attr("currpage", currPage + 1).text("点击加载更多").show();
            } else {
                pagebtn.text("已经到最后一页了").unbind("click");
            }
        },
        error: function () {
            pagebtn.text("数据添加失败");
        },
        complete: function () {
            bindAttention();
        }
    });
};

function ajaxFn_toutiao(jsonConfig) {
    var config = {
        obj: null,
        action: null,
        pageno:1,
        rand: Math.random()
    }
    $.extend(config, jsonConfig);
    var pagebtn = $(".btn-more");
    var currPage = parseInt(pagebtn.attr("currpage"));
    config.pageno=currPage;
    pagebtn.text("数据加载中，请稍候...");
    $.ajax({
        type: 'POST',
        url: '/ajax/'+ config.action,
        data: config,
        success: function (response) {
            if(response == "unlogin"){
                $(config.obj).append('<div class="unlogin">你还为登录，请先<a href="/user/login">登录</a>！</div>');
            }else if (response != "") {
                var htmlSplit = response.split("###");
                $(config.obj).append(htmlSplit[1]);
                pagebtn.attr("currpage", htmlSplit[0]).text("点击加载更多").show();
            } else {
                pagebtn.text("已经到最后一页了").unbind("click");
            }
        },
        error: function () {
            pagebtn.text("数据添加失败");
        },
        complete: function () { }
    });
};