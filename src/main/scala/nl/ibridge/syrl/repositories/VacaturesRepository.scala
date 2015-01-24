package nl.ibridge.syrl.repositories

import nl.ibridge.syrl.model.vacature.Vacature
import scala.concurrent.Future
import nl.ibridge.syrl.model.AdviesCriteria

trait VacaturesRepository {
  
  def find: Future[List[Vacature]]
  
  def find(criteria: AdviesCriteria): Future[List[Vacature]]
  
  def get(id: Int): Future[Option[Vacature]]
  
  def save(postcode: Vacature): Future[Boolean]

}