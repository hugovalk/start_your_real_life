package nl.ibridge.syrl.repositories.mock

import nl.ibridge.syrl.CustomFlatSpec
import nl.ibridge.syrl.model.Vacature

/**
 * @author hv01016
 */
class MockVacaturesRepositorySpecs extends CustomFlatSpec {
  
  "The mock repository" should "contain 3 vacatures." in {
    new MockVacaturesRepository().find.size should be(3)
  }
  
  it should "be able to return vacature 1" in {
    new MockVacaturesRepository().get(1) should be(Some(Vacature(1)))
  }
  
}