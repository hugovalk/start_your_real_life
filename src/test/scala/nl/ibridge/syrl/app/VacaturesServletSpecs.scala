package nl.ibridge.syrl.app

import org.scalatra.test.scalatest.ScalatraFlatSpec
import nl.ibridge.syrl.repositories.mock.MockVacaturesRepository
import nl.ibridge.syrl.repositories.mock.MockVacaturesRepository

/**
 * @author hv01016
 */
class VacaturesServletSpecs extends ScalatraFlatSpec {
  
  
  "The vacature servlet" should "have a feed containing the vacatures" in {
	  addServlet(new VacaturesServlet(new MockVacaturesRepository), "/services/vacatures")
    get("/services/vacatures") {
      status should be (200) 
      body should include("vacatures")
    }
  }
  
}