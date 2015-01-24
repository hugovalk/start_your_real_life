package nl.ibridge.syrl.model

/**
 * @author hv01016
 */
case class AdviesCriteria( 
    val opleidingsNiveau: Option[String],
    val provincie: Option[String],
    val vakgebied: Option[String])