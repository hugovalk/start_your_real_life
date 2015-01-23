package nl.ibridge.syrl.app

import nl.ibridge.syrl.repositories.mock.MockHuizenRepository
import org.scalatra.test.scalatest.ScalatraFlatSpec

/**
 * @author hv01016
 */
class HuizenServletSpecs extends ScalatraFlatSpec {
	addServlet(new HuizenServlet(new MockHuizenRepository), "/services/huizen")
  
  "The huizen servlet" should "have a feed containing the huizen" in {
    get("/services/huizen") {
      status should be (200) 
      body should include("huizen")
    }
  }
  
  it should "be able to serve a huis based on id." in {
    get("/services/huizen/1") {
      status should be (200) 
      body should include("adres")
      body should include("postcode")
    }
  }
  
}