$(function() {
	$.get("/rest/peliculas/graficomejorvaloradas", function(data) {
		var chart = c3.generate({
			bindto : '#bestFilmsPoints',
			data : {
				json : data,
				type : 'bar',
				keys : {
					x : 'name',
					value : [ 'points' ],
				}
			},
			axis : {
				x : {
					type : 'category'
				}
			}
		});
	});
	
	$.get("/rest/series/graficomejorvaloradas", function(data) {
		var chart = c3.generate({
			bindto : '#bestShowsPoints',
			data : {
				json : data,
				type : 'bar',
				keys : {
					x : 'name',
					value : [ 'points' ],
				}
			},
			axis : {
				x : {
					type : 'category'
				}
			}
		});
	});
	
	$.get("/rest/libros/graficomejorvaloradas", function(data) {
		var chart = c3.generate({
			bindto : '#bestBooksPoints',
			data : {
				json : data,
				type : 'bar',
				keys : {
					x : 'name',
					value : [ 'points' ],
				}
			},
			axis : {
				x : {
					type : 'category'
				}
			}
		});
	});
	
	$.get("/rest/graficogeneros", function(data) {
		var chart = c3.generate({
			bindto : '#gende',
			data : {
				json : data,
				type : 'bar',
				keys : {
					x : 'name',
					value : [ 'numItems' ],
				}
			},
			axis : {
				x : {
					type : 'category'
				}
			}
		});
	});
});
