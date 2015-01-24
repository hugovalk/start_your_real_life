package nl.ibridge.syrl.app

import nl.ibridge.syrl.model.vacature.Vacature
import nl.ibridge.syrl.repositories.VacaturesRepository
import nl.ibridge.syrl.utils.StringUtils._
import scala.concurrent.Await
import scala.concurrent.duration._

/**
 * @author hv01016
 */
class VacaturesServlet(vacatureRepository: VacaturesRepository) extends JsonStack {
  get("/") {
    new VacaturesResult(Await.result(vacatureRepository.find, Duration("3 s")))
  }
  
  get("/:id") {
    params("id").toIntOpt.flatMap(id => Await.result(vacatureRepository.get(id), Duration("3 s"))) match {
      case Some(vacature) => new VacatureResult(vacature) 
      case None => halt(404)
    }
  }
}

case class VacatureResult(vacature: Vacature)
case class VacaturesResult(vacatures: List[Vacature])