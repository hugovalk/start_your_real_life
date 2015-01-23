package nl.ibridge.syrl.repositories

import nl.ibridge.syrl.model.Vacature

trait VacaturesRepository {
  
  def find: List[Vacature]
  
  def get(id: Int): Option[Vacature]

}