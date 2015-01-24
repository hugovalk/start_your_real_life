package nl.ibridge.syrl.repositories.mock

import nl.ibridge.syrl.repositories.PostcodeRepository
import nl.ibridge.syrl.model.Postcode
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.Random

/**
 * @author hv01016
 */
class MockPostcodeRepository extends PostcodeRepository {
  
  var repo: List[Postcode] = List()
  
  override def save(postcode: Postcode): Future[Boolean] = {
    Future{
      repo = postcode :: repo
      true 
    }
  }
  
  override def find: Future[List[Postcode]] = Future{repo}
  
  override def findProvincieForPostcode(postcode: String): Future[String] = 
    findProvincies().map(Random.shuffle(_).head)
  
  override def findProvincies: Future[List[String]] = Future(List(
      "Groningen",
      "Friesland",
      "Drenthe", 
      "Overrijssel",
      "Flevoland",
      "Gelderland",
      "Noord-Braband",
      "Noord-Holland",
      "Zuid-Holland",
      "Utrecht",
      "Zeeland",
      "Limburg"))
}