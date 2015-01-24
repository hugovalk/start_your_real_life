package nl.ibridge.syrl.repositories.mock

import nl.ibridge.syrl.repositories.VacaturesRepository

import nl.ibridge.syrl.model.vacature.Vacature
import scala.concurrent.Future

import scala.concurrent.ExecutionContext.Implicits.global

/**
 * @author hv01016
 */
class MockVacaturesRepository extends VacaturesRepository {
  
  var repo: List[Vacature] = List()
  
  override def save(vacature: Vacature): Future[Boolean] = {
    Future{
      repo = vacature :: repo
      true 
    }
  }
  
  override def find: Future[List[Vacature]] = Future {
    List(
      new Vacature(1, 
          "Software developer", 
          "Wij zoeken een ervaren software developer... ",
          "Administratief",
          "Zuid-Holland",
          "Gouda",
          2300),
      new Vacature(2, 
          "Social media adviseur", 
          "Is social media jouw passie? Wij zoeken een...",
          "Marketing & Communicatie",
          "Noord-Holland",
          "Diemen",
          2300),
      new Vacature(3, 
          "Winkelverkoper", 
          "Voor boekhandel LekkerLezen zoeken wij een enthousiaste verkoper...",
          "Overig",
          "Zeeland",
          "Middelburg",
          2300))
  }
      
  override def get(id: Int): Future[Option[Vacature]] = find.map(_.find(_.id == id))
}