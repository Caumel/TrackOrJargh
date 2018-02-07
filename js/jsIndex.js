jQuery(document).ready(function( $ ) {
    $('.search').click(function(){
        $('#search-bar').css("visibility", "visible");
        $('.search').hide();
        
        return false;
    });
  
  


$(document).ready(function(){
    $("NotificationButton").click(function(){
        $("HideNotification").toggle();
    });
});

  $(document).ready(function(){
    $("MessageButton").click(function(){
        $("HideMessages").toggle();
    });
});



  
  
});


