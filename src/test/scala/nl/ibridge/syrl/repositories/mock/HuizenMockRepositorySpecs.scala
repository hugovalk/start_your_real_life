package nl.ibridge.syrl.repositories.mock

import nl.ibridge.syrl.CustomFlatSpec
import scala.concurrent.Await
import scala.concurrent.duration.Duration

/**
 * @author hv01016
 */
class HuizenMockRepositorySpecs extends CustomFlatSpec {
  
  "The mock huizenrepository" should "contain 3 huizen" in {
    Await.result(new MockHuizenRepository().find, Duration("3 s")).size should be(3)
  }
  
  it should "be able to find a huis by id" in {
    Await.result(new MockHuizenRepository().get(2), Duration("3 s")).get.id should be (2)
  }
  
}