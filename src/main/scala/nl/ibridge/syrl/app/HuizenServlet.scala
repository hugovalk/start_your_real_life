package nl.ibridge.syrl.app

import nl.ibridge.syrl.repositories.HuizenRepository
import nl.ibridge.syrl.model.huis.Huis

/**
 * @author hv01016
 */
class HuizenServlet(huizenRepository: HuizenRepository) extends JsonStack {
  
  get("/") {
    new HuizenResult(huizenRepository.find)
  }
  
}

case class HuizenResult(
    val huizen: List[Huis])