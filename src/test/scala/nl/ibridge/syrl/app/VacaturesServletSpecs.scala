package nl.ibridge.syrl.app

import org.scalatra.test.scalatest.ScalatraFlatSpec
import nl.ibridge.syrl.repositories.mock.MockVacaturesRepository
import nl.ibridge.syrl.repositories.mock.MockVacaturesRepository

/**
 * @author hv01016
 */
class VacaturesServletSpecs extends ScalatraFlatSpec {
  
	addServlet(new VacaturesServlet(new MockVacaturesRepository), "/services/vacatures")
  
  "The vacature servlet" should "have a feed containing the vacatures" in {
    get("/services/vacatures") {
      status should be (200) 
      body should include("vacatures")
    }
  }
  
  it should "be able to serve a vacature on id" in {
    get("/services/vacatures/1") {
      status should be (200) 
      body should include("id")
      body should include("titel")
    }
  }
  
}