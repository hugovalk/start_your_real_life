package nl.ibridge.syrl.model.huis

/**
 * @author hv01016
 */
sealed trait HuisType
case object Koop extends HuisType
case object Huur extends HuisType