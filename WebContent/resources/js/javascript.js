$(document).ready(function() {
    var $menu = $("#mainMenu").clone();
    $menu.attr("id", "mainMenuMobile");
    $menu.mmenu({
        dragOpen: {
            open: true
        },
        extensions: ["theme-dark"],
        extensions: ["effect-zoom-menu", "effect-zoom-panels"],
        extensions: ["border-full"],
        extensions: ["pageshadow"]
    });
    var API = $("#mainMenuMobile").data("mmenu");
    $("#button-toggle-menu").click(function (e) {
        API.open();
    });
});

$(document).ready(function () {
    if ($('.list-banner').length) {
        $('.list-banner .off-seo').on('click',function(){
            if($('.list-banner').hasClass('show')){
                $('.list-banner').removeClass('show');
            }else{
                $('.list-banner').addClass('show');
            }
        });
    }
});