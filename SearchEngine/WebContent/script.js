$(document).ready(function(){
	// Focusing the input text box:
	$('#s').focus();

	$('#searchForm').submit(function(){
		doSearch();
		return false;
	});
});


function result(r){
	var arr = [];
	var authors='';
	for(var i in r.authors)
	{
		authors = + i.name + ',';
	}
	arr = [
			'<div class="webResult">',
			'<h2> id : ',r.id , ' - Title : ',r.title,'</h2>',
			'<p>',r.journal,'</p>',
			'<p>',authors,'</p>',
			'</div>'
		];
	// The toString method.
	this.toString = function(){
		return arr.join('');
	}
}

function doSearch(){
	
	//settings = $.extend({},config,settings);

	var resultsDiv = $('#resultsDiv');
	var apiURL = 'http://localhost:8080/SearchEngine/SearchAction';
	  var params = "";
	    params += "searchText=" + $('#s').val();
	    params += "&rowsInPage=" + $('#rowsInPage').val();
	    params += "&page=" + $('#pageNo').val();
	    $(resultsDiv).addClass('resultsDiv');
	    apiURL += "?" + params;
	    console.log('apiURL'+apiURL);
	 $.ajax({
	        url: apiURL,
	        method: "GET",
	        success: function (response) {

	        	 console.log(' response>>' + response);
	 
	        	var pageContainer = $('<div>',{className:'pageContainer'});
				
	        	if(response.length){
	        		resultsDiv.empty();
				for(var i=0;i<response.length;i++){
					// Creating a new result object and firing its toString method:
					pageContainer.append(new result(response[i]) + '');
					console.log(i+' >>> '+response[i]);
				}
				pageContainer.append('<div class="clear"></div>')
				 .hide().appendTo(resultsDiv)
				 .fadeIn('slow');
	        	}
				else {
					
					// No results were found for this search.
					
					resultsDiv.empty();
					$('<p>',{className:'notFound',html:'No Results Were Found!'}).hide().appendTo(resultsDiv).fadeIn();
				}
	        },
	        error: function (error) {
	            console.log('cartQuantityAdjust error>>' + error);
	        }

	    });
}
