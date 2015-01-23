package nl.ibridge.syrl.app

import nl.ibridge.syrl.model.vacature.Vacature
import nl.ibridge.syrl.repositories.VacaturesRepository

import nl.ibridge.syrl.utils.StringUtils._

/**
 * @author hv01016
 */
class VacaturesServlet(vacatureRepository: VacaturesRepository) extends JsonStack {
  get("/") {
    new VacaturesResult(vacatureRepository.find)
  }
  
  get("/:id") {
    params("id").toIntOpt.flatMap(id => vacatureRepository.get(id)) match {
      case Some(vacature) => new VacatureResult(vacature) 
      case None => halt(404)
    }
  }
}

case class VacatureResult(vacature: Vacature)
case class VacaturesResult(vacatures: List[Vacature])