package nl.ibridge.syrl.repositories.mock

import nl.ibridge.syrl.repositories.PostcodeRepository
import nl.ibridge.syrl.model.Postcode
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

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
}