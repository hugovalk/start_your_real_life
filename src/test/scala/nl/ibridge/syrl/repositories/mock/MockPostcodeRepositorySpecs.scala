package nl.ibridge.syrl.repositories.mock

import nl.ibridge.syrl.CustomFlatSpec
import scala.io.Source
import java.io.File
import java.io.InputStream
import java.io.FileInputStream
import scala.concurrent._

class MockPostcodeRepositorySpecs extends CustomFlatSpec {
  
  "Parsing a postcode CSV file" should "result in the postcodes added to the repository" in {
    val repo = new MockPostcodeRepository
    val input = this.getClass().getClassLoader().getResourceAsStream("testpostcodes.csv")
    //val input = this.getClass().getClassLoader().getResourceAsStream("classpath:*testpostcodes.csv")
    //val source = new FileInputStream(new File("testpostcodes.csv"))
    blocking(repo.importFile(input))
    repo.find.size should be(3)
  }

}