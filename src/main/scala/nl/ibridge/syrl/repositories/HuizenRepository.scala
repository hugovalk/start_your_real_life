package nl.ibridge.syrl.repositories

import nl.ibridge.syrl.model.huis.Huis
import scala.concurrent.Future

trait HuizenRepository {
  
  def find: Future[List[Huis]]
  def get(id: Int): Future[Option[Huis]]

}