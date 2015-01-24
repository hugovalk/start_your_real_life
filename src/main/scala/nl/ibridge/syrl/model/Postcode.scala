package nl.ibridge.syrl.model

case class Postcode(
    waarde: String, 
    nummers: Int,
    letters: String, 
    provincie: String,
    plaats: String,
    straat: String,
    latitude: Double,
    longitude: Double)