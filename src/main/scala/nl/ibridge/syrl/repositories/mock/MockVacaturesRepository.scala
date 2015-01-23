package nl.ibridge.syrl.repositories.mock

import nl.ibridge.syrl.repositories.VacaturesRepository
import nl.ibridge.syrl.model.Vacature

/**
 * @author hv01016
 */
class MockVacaturesRepository extends VacaturesRepository {
  
  override def find: List[Vacature] = List(
      new Vacature(1, "Software developer", "Wij zoeken een ervaren software developer... "),
      new Vacature(2, "Social media adviseur", "Is social media jouw passie? Wij zoeken een..."),
      new Vacature(3, "Winkelverkoper", "Voor boekhandel LekkerLezen zoeken wij een enthousiaste verkoper..."))
      
  override def get(id: Int): Option[Vacature] = find.find(_.id == id)
}