package nl.ibridge.syrl.repositories.mock

import nl.ibridge.syrl.CustomFlatSpec
import nl.ibridge.syrl.model.vacature.Vacature
import scala.concurrent.Await
import scala.concurrent.duration.Duration

/**
 * @author hv01016
 */
class MockVacaturesRepositorySpecs extends CustomFlatSpec {
  
  "The mock repository" should "contain 3 vacatures." in {
    Await.result(new MockVacaturesRepository().find, Duration("3 s")).size should be(3)
  }
  
  it should "be able to return vacature 1" in {
    Await.result(new MockVacaturesRepository().get(1), Duration("3 s")).get.id should be(1)
  }
  
}