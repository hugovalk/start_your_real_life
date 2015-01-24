package nl.ibridge.syrl.repositories

import nl.ibridge.syrl.model.vacature.Vacature
import scala.concurrent.Future

trait VacaturesRepository {
  
  def find: Future[List[Vacature]]
  
  def get(id: Int): Future[Option[Vacature]]

}