package nl.ibridge.syrl.model.huis

case class Huis(
    val id: Int,
    val huisType: HuisType,
    val prijs: String,
    val aantalKamers: Int,
    val omschrijving: String,
    val adres: Adres
    )
    
