package nl.ibridge.syrl.utils

import org.json4s.CustomSerializer
import nl.ibridge.syrl.model.huis.HuisType
import org.json4s.JsonAST.JString
import nl.ibridge.syrl.model.huis.Koop
import nl.ibridge.syrl.model.huis.Huur


/**
 * @author hv01016
 */
class HuisTypeSerializer extends CustomSerializer[HuisType](
    format => ({
      case v: JString => v.toString match {
        case "koop" => Koop
        case "huur" => Huur
      }
    }, {
      case ht: HuisType => ht match {
        case Koop => JString("koop")
        case Huur => JString("huur")
      }
    })
    ){}