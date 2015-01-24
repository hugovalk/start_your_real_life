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
					success: function(advies){
						console.log(advies);
						setAdvicedCity(advies);
						setHuisInformation(advies.huis);
						setVacatureInformation(advies.vacature);
					}
				});	
				
				//3. Show the results div via fade in.
				$document.find("[data-results]").toggle("fold",  {horizFirst: false });				
				//delay force.
				setTimeout( function() { $("body").waitMe('hide');  }, 500);
			});
		});
	});
	
	function setHuisInformation(huis){
		var $title = $(document).find("[data-huis-title]"),
			$summary = $(document).find("[data-huis-summary"),
			$optionTitle = $(document).find("[data-huis-optiontitle]"),
			$typeIcon = $(document).find("[data-huis-typeicon]");
		
		$optionTitle.text("Passende " + huis.huisType);
		$title.text(huis.adres.straatEnHuisnummer + ", " + huis.adres.woonPlaats);
		$summary.text(huis.omschrijving);
		
		if(huis.huisType === "huurwoning"){
			$typeIcon.attr("src", "/img/rent.png");
		}else{
			$typeIcon.attr("src", "/img/buy.png");
		}
	}
	
	function setVacatureInformation(vacature){
		var $title = $(document).find("[data-vacature-title]"),
			$summary = $(document).find("[data-vacature-summary]"),
			$vacatureLink = $(document).find("[data-vacature-link]");
		
		$title.text(vacature.titel);
		$summary.text(vacature.samenvatting);
		$vacatureLink.attr("href", "http://www.randstad.nl/vacatures/" + vacature.id);
	}
	
	function setAdvicedCity(advies){
		$(document).find("[data-advice-city]").text(advies.huis.adres.woonPlaats);
	}
	
}(jQuery));