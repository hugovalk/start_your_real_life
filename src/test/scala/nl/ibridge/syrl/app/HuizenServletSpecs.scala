package nl.ibridge.syrl.app

import nl.ibridge.syrl.repositories.mock.MockHuizenRepository
import org.scalatra.test.scalatest.ScalatraFlatSpec

/**
 * @author hv01016
 */
class HuizenServletSpecs extends ScalatraFlatSpec {
  
  "The huizen servlet" should "have a feed containing the huizen" in {
    addServlet(new HuizenServlet(new MockHuizenRepository), "/services/huizen")
    get("/services/huizen") {
      status should be (200) 
      body should include("huizen")
    }
  }
  
}