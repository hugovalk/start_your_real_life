(function($){
	
	$(document).ready(function(){
		
		var $document = $(this);
		
		//hide the [data-results]-div. 
		$(this).find("[data-results]").each(function(){
//			$(this).hide(); 
		});
		
		$(this).find("[data-match]").each(function(){
			$(this).on('click', function(){
				
				//1. Fetch results based on input
				
				//2. Render results
				
				//3. Show the results div via fade in.
				$document.find("[data-results]").toggle("fold",  {horizFirst: false });
			});
		});
	});
	
}(jQuery));