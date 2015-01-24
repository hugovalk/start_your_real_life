(function($){
	
	$(document).ready(function(){
		
		var $document = $(this);
		
		//hide the [data-results]-div. 
		$(this).find("[data-results]").each(function(){
			$(this).hide(); 
		});
		
		$(this).find("[data-flex-hypotheek]").each(function(){
			$(this).hide(); 
		});
		
		$(this).find("[data-match]").each(function(){
			$(this).on('click', function(){
				
				$("body").waitMe({
					effect: 'bounce', 
					text: 'Een moment a.u.b. We zijn nu voor jou je perspectief aan het berekenen.',
					bg: 'rgba(255, 255, 255, 0.7)', color: '#000'
				});
				
				//1. Fetch results based on input
				var serviceUrl="/services/advies";
					
				$.ajax({
					type: "GET", 
					url: serviceUrl, 
					data: $document.find("form").first().serialize(),
					success: function(advies){
						console.log(advies);
						setAdvicedCity(advies);
						setHuisInformation(advies.huis);
						setVacatureInformation(advies.vacature);
						showFlexHypotheekContent(advies.flexHypotheek);
					}
				});	
				
				//3. Show the results div via fade in.
				if($(document).find("[data-results").is(":hidden")){
					$document.find("[data-results]").toggle("fold",  {horizFirst: false });									
				}
				
				//delay force.
				setTimeout( function() { $("body").waitMe('hide');  }, 500);
				
				//animate to the results
				 $('html, body').animate({
				        scrollTop: $("#results-location").offset().top
				 }, 2000);
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
	
	function showFlexHypotheekContent(show){
		if (show) {
			$(document).find("[data-flex-hypotheek='true']").show();
			$(document).find("[data-flex-hypotheek='false']").hide();
		} else {
			$(document).find("[data-flex-hypotheek='true']").hide();
			$(document).find("[data-flex-hypotheek='false']").show();
		}
	}
	
}(jQuery));