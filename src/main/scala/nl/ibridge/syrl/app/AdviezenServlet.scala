package nl.ibridge.syrl.app

import nl.ibridge.syrl.model.AdviesCriteria
import nl.ibridge.syrl.repositories.VacaturesRepository
import scala.concurrent.Await
import scala.concurrent.duration.Duration
import nl.ibridge.syrl.model.vacature.Vacature
import nl.ibridge.syrl.model.huis.Huis
import scala.concurrent.ExecutionContext.Implicits.global


/*import main.scala.nl.ibridge.syrl.model.advies

*
 * @author hv01016
 */
class AdviezenServlet(vacaturesRepository: VacaturesRepository) extends JsonStack {
  
  get("/") {
    val opleidingsNiveau = multiParams("opleidingsNiveau").headOption
    val provincie = multiParams("provincie").headOption
    val vakgebied = multiParams("vakgebied").headOption
    val criteria = new AdviesCriteria(opleidingsNiveau, provincie, vakgebied)
    val vacatureOption = Await.result(vacaturesRepository.find(criteria).map(_.headOption), Duration("1 s"))
    vacatureOption match {
      case Some(vacature) => new Result(vacature)
      case None => halt(404)
    }
  }
}

case class Result(val vacature: Vacature)