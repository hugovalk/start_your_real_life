package nl.ibridge.syrl.repositories.mock

import nl.ibridge.syrl.repositories.VacaturesRepository
import nl.ibridge.syrl.model.Vacature

/**
 * @author hv01016
 */
class MockVacaturesRepository extends VacaturesRepository {
  
  override def find: List[Vacature] = List(
      new Vacature(1),
      new Vacature(2),
      new Vacature(3))
      
  override def get(id: Int): Option[Vacature] = find.find(_.id == id)
}