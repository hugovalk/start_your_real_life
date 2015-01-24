package nl.ibridge.syrl.repositories.mock

import nl.ibridge.syrl.repositories.VacaturesRepository

import nl.ibridge.syrl.model.vacature.Vacature
import scala.concurrent.Future

import scala.concurrent.ExecutionContext.Implicits.global

/**
 * @author hv01016
 */
class MockVacaturesRepository extends VacaturesRepository {
  
  override def find: Future[List[Vacature]] = Future {
    List(
      new Vacature(1, "Software developer", "Wij zoeken een ervaren software developer... "),
      new Vacature(2, "Social media adviseur", "Is social media jouw passie? Wij zoeken een..."),
      new Vacature(3, "Winkelverkoper", "Voor boekhandel LekkerLezen zoeken wij een enthousiaste verkoper..."))
  }
      
  override def get(id: Int): Future[Option[Vacature]] = find.map(_.find(_.id == id))
}