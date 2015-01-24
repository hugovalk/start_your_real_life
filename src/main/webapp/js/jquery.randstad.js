(function($){
	
	$(document).ready(function(){
		
		var $document = $(this);
		
		//hide the [data-results]-div. 
		$(this).find("[data-results]").each(function(){
			$(this).hide(); 
		});
		
		$(this).find("[data-match]").each(function(){
			$(this).on('click', function(){
				
				$("body").waitMe({
					effect: 'bounce', 
					text: 'Een moment a.u.b. We zijn voor nu je perspectief aan het berekenen.',
					bg: 'rgba(255, 255, 255, 0.7)', color: '#000'
				});
				
				//1. Fetch results based on input
				var serviceUrl="/services/advies"
					
				$.ajax({
					type: "GET", 
					url: serviceUrl, 
					data: $document.find("form").first().serialize(),
					success: function(result){
						console.log(result);
					}
				});	
				
				//3. Show the results div via fade in.
				$document.find("[data-results]").toggle("fold",  {horizFirst: false });
				
				//delay force.
				setTimeout( function() { $("body").waitMe('hide');  }, 500);
				
//				$("body").waitMe('hide');
				
				
				//2. Render results
				
				
			});
		});
	});
	
}(jQuery));