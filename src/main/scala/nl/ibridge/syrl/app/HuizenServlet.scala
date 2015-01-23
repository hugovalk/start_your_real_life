package nl.ibridge.syrl.app

import nl.ibridge.syrl.repositories.HuizenRepository
import nl.ibridge.syrl.model.huis.Huis

import nl.ibridge.syrl.utils.StringUtils._


/**
 * @author hv01016
 */
class HuizenServlet(huizenRepository: HuizenRepository) extends JsonStack {
  
  get("/") {
    new HuizenResult(huizenRepository.find)
  }
  
  get("/:id") {
    params("id").toIntOpt.flatMap(id => huizenRepository.get(id)) match {
      case Some(huis) => new HuisResult(huis) 
      case None => halt(404)
    }
  }
}

case class HuisResult(val huis: Huis)
case class HuizenResult(val huizen: List[Huis])