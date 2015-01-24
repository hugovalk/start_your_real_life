package nl.ibridge.syrl.app

import nl.ibridge.syrl.model.AdviesCriteria
import nl.ibridge.syrl.repositories.VacaturesRepository
import scala.concurrent.Await
import scala.concurrent.duration.Duration
import nl.ibridge.syrl.model.vacature.Vacature
import nl.ibridge.syrl.model.huis.Huis
import scala.concurrent.ExecutionContext.Implicits.global
import nl.ibridge.syrl.model.huis.Koop
import nl.ibridge.syrl.model.huis.Adres
import nl.ibridge.syrl.model.huis.Huur


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
      case Some(vacature) => new Result(vacature, generateHuis(criteria, vacature.plaats), criteria.isFlexHyptheek)
      case None => halt(404)
    }
  }
  
  private def generateHuis(criteria: AdviesCriteria, plaats: String): Huis = criteria.isFlexHyptheek match {
    case true => generateKoopHuis(plaats)
    case false => generateHuurHuis(plaats)
  }
  
  private def generateHuurHuis(plaats: String): Huis = {
    new Huis(
        1,
        Huur,
        "€ 600 p.m.",
        4,
        """Halfvrijstaande eengezinswoning, met bijkeuken en schuur, drie slaapkamers en een badkamer. 
Met tuin rondom. Gelegen op 169 m2 eigen grond.""", 
        new Adres(
            "Beatrixlaan 7",
            plaats,
            "3248 AB"
        )
    )
  }
  
  private def generateKoopHuis(plaats: String): Huis = {
    new Huis(
        1,
        Koop,
        "€ 125.000 k.k.",
        6,
        """Aan het einde van de historische Schiedamseweg ligt dit charmante hoekherenhuis met garage uit 1925. 
Deze karakteristieke woning heeft veel authentieke details en is gelegen op 115 m2 eigen grond.
Bent u de koper welke deze woning naar eigen wens wil verbouwen? Dan maken wij graag een afspraak met u voor een bezichtiging.
""", 
        new Adres(
            "Schiedamseweg 158",
            plaats,
            "3134 BV"
        )
    )
  }
}

case class Result(val vacature: Vacature, val huis: Huis, val flexHypotheek: Boolean)