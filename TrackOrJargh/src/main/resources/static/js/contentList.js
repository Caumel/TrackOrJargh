var request = 1;
var numElements = 10;
var totalPages = null;

function loadContent() {
	if(request < totalPages){
		$.get("rest/" + typePage +"?page=" + request + "&size=" + numElements, function(data) {
			jQuery.each(data.content, function(count, item) {
				$("#elements").append(
						'<div class="col-lg-3 col-md-3 portfolio-item filter-app">'
								+ '<div class="portfolio-wrap">'
								+ '<figure style="background-image: url('
								+ item.image + ');">'
								+ '<p class="textPortfolio">' + item.synopsis
								+ '</p>' + '</figure>'
								+ '<div class="portfolio-info">' + '<h4>'
								+ '<a href="contentProfile.html">' + item.name
								+ '</a>' + '</h4>' + '<p>' + item.year + '</p>'
								+ '</div>' + '</div>' + '</div>');
			});
	
			request++;
			
			if(request == totalPages){
				$("#moreContent").remove();
			}
		});
	}
}

$(function() {
	$("#moreContent").click(loadContent);
	$.get("rest/" + typePage +"?page=" + request + "&size=" + numElements, function(data) {		
		totalPages = data.totalPages;
	});	
});