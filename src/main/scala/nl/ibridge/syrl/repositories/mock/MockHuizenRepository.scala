package nl.ibridge.syrl.repositories.mock

import nl.ibridge.syrl.repositories.HuizenRepository
import nl.ibridge.syrl.model.huis._

/**
 * @author hv01016
 */
class MockHuizenRepository extends HuizenRepository {
  
  override def find: List[Huis] = List(
      new Huis(1, Koop, new Adres("Johannes Poststraat 94", "Gouda", "2806 KC")),
      new Huis(2, Koop, new Adres("Diemermere 25", "Diemen", "1112 TC")),
      new Huis(3, Koop, new Adres("Lange ademstraat", "Rotterdam", "2894 AB"))
      )
  
  override def get(id: Int): Option[Huis] = find.find(_.id == id)
}