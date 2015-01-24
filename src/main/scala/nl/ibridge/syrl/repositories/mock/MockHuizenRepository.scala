package nl.ibridge.syrl.repositories.mock

import nl.ibridge.syrl.repositories.HuizenRepository
import nl.ibridge.syrl.model.huis._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
 * @author hv01016
 */
class MockHuizenRepository extends HuizenRepository {
  
  override def find: Future[List[Huis]] = Future {
    List(
      new Huis(1, Koop, "200.000 k.k.", 4, "Omschrijving", new Adres("Johannes Poststraat 94", "Gouda", "2806 KC")),
      new Huis(2, Koop, "200.000 k.k.", 4, "Omschrijving", new Adres("Diemermere 25", "Diemen", "1112 TC")),
      new Huis(3, Koop, "200.000 k.k.", 4, "Omschrijving", new Adres("Lange ademstraat", "Rotterdam", "2894 AB"))
      )
  }
  
  override def get(id: Int): Future[Option[Huis]] = find.map(_.find(_.id == id))
}