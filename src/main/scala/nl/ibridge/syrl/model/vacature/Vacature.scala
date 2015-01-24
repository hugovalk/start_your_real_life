package nl.ibridge.syrl.model.vacature

case class Vacature (
    val id: Int,
    val titel: String,
    val samenvatting: String,
    val vakgebied: String,
    val provincie: String,
    val plaats: String,
    val salaris: Int)