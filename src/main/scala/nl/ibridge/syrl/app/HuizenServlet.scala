package nl.ibridge.syrl.app

import nl.ibridge.syrl.repositories.HuizenRepository
import nl.ibridge.syrl.model.huis.Huis
import nl.ibridge.syrl.utils.StringUtils._
import scala.concurrent.Await
import scala.concurrent.duration._


/**
 * @author hv01016
 */
class HuizenServlet(huizenRepository: HuizenRepository) extends JsonStack {
  
  get("/") {
    new HuizenResult(Await.result(huizenRepository.find, Duration("3 s")))
  }
  
  get("/:id") {
    params("id").toIntOpt.flatMap(id => Await.result(huizenRepository.get(id), Duration("3 s"))) match {
      case Some(huis) => new HuisResult(huis) 
      case None => halt(404)
    }
  }
}

case class HuisResult(val huis: Huis)
case class HuizenResult(val huizen: List[Huis])