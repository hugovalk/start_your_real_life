package nl.ibridge.syrl.app

import nl.ibridge.syrl.repositories.PostcodeRepository
import scala.concurrent.Await
import scala.concurrent.duration.Duration

/**
 * @author hv01016
 */
class GeoDataServlet(postcodeRepository: PostcodeRepository) 
  extends JsonStack {
  
  get("/provincies") {
    new Results(Await.result(postcodeRepository.findProvincies(), Duration("3 s")))
  }
  
  get("/provincie") {
    println(params("postcode"))
    new Results(List(Await.result(postcodeRepository.findProvincieForPostcode(params("postcode")), Duration("3 s"))))
  }
  
}

case class Results(val results: List[String])