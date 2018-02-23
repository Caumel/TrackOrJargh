var request = 1;
var numElements = 10;
var totalPages = null;

function loadContent() {
	if(request < totalPages){
		$("#moreContent").attr("class","fa fa-spinner fa-pulse fa-3x fa-fw");
		$("#moreContent").unbind("click");
		
		$.get(typeSearch + "?page=" + request + "&size=" + numElements, function(data) {
			sleep(1000);
			
			jQuery.each(data.content, function(count, item) {
				$("#elements").append(
						'<div class="col-lg-3 col-md-3 portfolio-item filter-app">'
								+ '<div class="portfolio-wrap">'
								+ '<figure style="background-image: url('
								+ item.image + ');">'
								+ '<p class="textPortfolio">' + item.synopsis
								+ '</p>' + '</figure>'
								+ '<div class="portfolio-info">' + '<h4>'
								+ '<a href="' + item.url + item.name + '">' + item.name
								+ '</a>' + '</h4>' + '<p>' + item.year + '</p>'
								+ '</div>' + '</div>' + '</div>');
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

function changeLinksInputSearch(){
	var inputSearch = $('[name="inputSearch"]').val();
	var optionSearch = $('[name="optionSearch"] option:selected').val();
	var linkFilm;
	var linkShow;
	var linkBook;
	
	if(inputSearch == ""){
		linkFilm = "/busqueda";
		linkShow = "/busqueda";
		linkBook = "/busqueda";
	} else{
		linkFilm = "/busqueda/" + optionSearch + "/pelicula/" + inputSearch;
		linkShow = "/busqueda/" + optionSearch + "/serie/" + inputSearch;
		linkBook = "/busqueda/" + optionSearch + "/libro/" + inputSearch;
	}
	
	$("#linkFilm").attr("href", linkFilm);
	$("#linkShow").attr("href", linkShow);
	$("#linkBook").attr("href", linkBook);	
}

$(function() {
	$("[name='inputSearch']").keyup(changeLinksInputSearch);
	$("[name='optionSearch']").change(changeLinksInputSearch);
	changeLinksInputSearch();
	
	$("#moreContent").click(loadContent);
	$.get(typeSearch + "?page=" + request + "&size=" + numElements, function(data) {		
		totalPages = data.totalPages;
	});	
});