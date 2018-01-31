jQuery(document).ready(function( $ ) {
    $('.search').click(function(){
        $('html, body').animate({scrollTop : 0},1500, 'easeInOutExpo');
        return false;
    });
});    