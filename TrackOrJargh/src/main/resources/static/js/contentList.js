var request = 1;
var numElements = 10;
var totalPages = null;
var listsUser = null;

function loadContent() {
	if(request < totalPages){
		$("#moreContent").attr("class","fa fa-spinner fa-pulse fa-3x fa-fw");
		$("#moreContent").unbind("click");
		
		$.get(typePage +"?page=" + request + "&size=" + numElements, function(data) {
			sleep(1000);
			
			jQuery.each(data.content, function(count, item) {
				if(loggedUserJS != "false"){
					var addedLists = '<div class="dropup float-left">' + 
										'<button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">' +
											'<i class="ion-plus-round"></i>' +
										'</button>' + 
										'<div class="dropdown-menu" aria-labelledby="dropdownMenu2">';
					
										jQuery.each(listsUser, function(count, item){
											addedLists += '<button name="buttonList" class="dropdown-item" type="button">' + item.name + '</button>'; 
										});
										
					addedLists +=			'<a href="/miperfil" class="dropdown-item">Crear lista</a>' +
										'</div>' + 
									'</div>';
				} else {
					addedLists = "";
				}
				
				$("#elements").append(
						'<div class="col-lg-3 col-md-3 portfolio-item filter-app wow fadeInUp">' +
							'<div class="portfolio-wrap">' +
								'<figure style="background-image: url(' + item.image + ');">' +
									'<p class="textPortfolio">item.synopsis</p>' +
								'</figure>' +
								'<div class="portfolio-info">' +
									'<h4>' + 
										'<a href="item.url + item.name +">' + item.name + '</a>' +
									'</h4>' + 
									'<p>' + item.year + '</p>' +
									addedLists + 
								'</div>' + 
							'</div>' + 
						'</div>');
			});
			
			$("#moreContent").attr("class","fa fa-plus-circle fa-3x fa-fw");
			$("#moreContent").click(loadContent);
			request++;
			
			if(request == totalPages){
				$("#moreContent").remove();
			}
		});
	}
}

function sleep(miliseconds) {
	  var start = new Date().getTime();
	  while (true) {
	    if ((new Date().getTime() - start) > miliseconds)
	      break;
	  }
}

$(function() {
	$("#moreContent").click(loadContent);
	$.get(typePage +"?page=" + request + "&size=" + numElements, function(data) {		
		totalPages = data.totalPages;
	});	
	
	$.get("/rest/listasusuario", function(data) {	
		listsUser = data;
	});		
});