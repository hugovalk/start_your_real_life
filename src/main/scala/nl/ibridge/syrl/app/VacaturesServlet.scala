package nl.ibridge.syrl.app

import nl.ibridge.syrl.model.Vacature
import nl.ibridge.syrl.repositories.VacaturesRepository

/**
 * @author hv01016
 */
class VacaturesServlet(vacatureRepository: VacaturesRepository) extends JsonStack {
  get("/") {
    new VacaturesResult(vacatureRepository.find)
  }
}
case class VacaturesResult(
    vacatures: List[Vacature])