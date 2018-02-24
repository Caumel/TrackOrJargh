function deleteContent(){ 
	var nameContent = $(this).val();
	var nameList = $(this).parents(".CreateList").find("[name='deleteListName']").text();
	alert(nameList);
	
	$.ajax({
	    url: "/rest/borrarContenido/"+nameList+"/"+nameContent,
	    type: 'DELETE',
	    success: function(result) {
	    	if (result == true){
	    		location.reload(true);
	    	
	    	}
	    }
	});
}

function deleteList(){ 
	var nameList = $(this).val();
	$.ajax({
	    url: "/rest/borrarLista/"+nameList,
	    type: 'DELETE',
	    success: function(result) {
	    	if (result == true){
	    		location.reload(true);
	    	}
	    }
	});
}

$(function() {
	$("[name='deleteList']").click(deleteList);
	$("[name='deleteContent']").click(deleteContent);
});