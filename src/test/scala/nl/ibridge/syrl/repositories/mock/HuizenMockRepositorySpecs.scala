package nl.ibridge.syrl.repositories.mock

import nl.ibridge.syrl.CustomFlatSpec

/**
 * @author hv01016
 */
class HuizenMockRepositorySpecs extends CustomFlatSpec {
  
  "The mock huizenrepository" should "contain 3 huizen" in {
    new MockHuizenRepository().find.size should be(3)
  }
  
  it should "be able to find a huis by id" in {
    new MockHuizenRepository().get(2).get.id should be (2)
  }
  
}