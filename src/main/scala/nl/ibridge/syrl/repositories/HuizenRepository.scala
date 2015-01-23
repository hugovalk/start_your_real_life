package nl.ibridge.syrl.repositories

import nl.ibridge.syrl.model.huis.Huis

trait HuizenRepository {
  
  def find: List[Huis]
  def get(id: Int): Option[Huis]

}