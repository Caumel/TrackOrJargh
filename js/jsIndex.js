jQuery(document).ready(function( $ ) {
    $('.search').click(function(){
        $('#search-bar').css("visibility", "visible");
        $('.search').hide();
        
        return false;
    });
