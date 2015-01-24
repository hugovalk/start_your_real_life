package nl.ibridge.syrl.repositories.mock

import nl.ibridge.syrl.repositories.VacaturesRepository
import nl.ibridge.syrl.model.vacature.Vacature
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import nl.ibridge.syrl.model.AdviesCriteria

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
          2300,
          "Q Logic",
          "MBO,HAVO"),
      new Vacature(2, 
          "Social media adviseur", 
          "Is social media jouw passie? Wij zoeken een...",
          "Marketing & Communicatie",
          "Noord-Holland",
          "Diemen",
          2300,
          "Randstad",
          "MBO,HAVO"),
      new Vacature(3, 
          "Winkelverkoper", 
          "Voor boekhandel LekkerLezen zoeken wij een enthousiaste verkoper...",
          "Overig",
          "Zeeland",
          "Middelburg",
          2300,
          "Boekhandel LekkerLezen",
          "MBO,HAVO"))
  }
  
  def find(criteria: AdviesCriteria): Future[List[Vacature]] = find
      
  override def get(id: Int): Future[Option[Vacature]] = find.map(_.find(_.id == id))
}