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
	 
	arr = [
			'<div class="webResult">',
			'<p>',r.term,'</p>',	
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
	var apiURL = 'http://localhost:8080/SearchEngine/TopResearchTopics';
	  var params = "";
	    params += "year=" + $('#year').val();
	     
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
	        		pageContainer.append('<h2>Popular Reseach Topics for the Year '+$('#year').val()+'</h2>');
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
