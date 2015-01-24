package nl.ibridge.syrl.app

import nl.ibridge.syrl.repositories.PostcodeRepository
import nl.ibridge.syrl.repositories.PostcodeRepository
import org.scalatra.servlet.FileUploadSupport
import org.scalatra.UrlGeneratorSupport
import java.io.InputStreamReader
import nl.ibridge.syrl.repositories.elasticsearch.ElasticSearch
import nl.ibridge.syrl.repositories.elasticsearch.ElasticSearch
import nl.ibridge.syrl.repositories.HuizenRepository
import nl.ibridge.syrl.repositories.VacaturesRepository
import nl.ibridge.syrl.repositories.elasticsearch.ESVacaturesRepository
import nl.ibridge.syrl.repositories.elasticsearch.VacaturesIndexer

/**
 * @author hv01016
 */
class ImportServlet(
    val postcodeRepository: PostcodeRepository with ElasticSearch,
    val vacatureRepository: ESVacaturesRepository,
    val huizenRepository: HuizenRepository with ElasticSearch) extends StartYourRealLifeStack 
    with FileUploadSupport with UrlGeneratorSupport {
  
  before() {
    contentType = "text/html"
  }
  
  val getPage = get("/") {  
    ssp("/import")
  }
  
  get("/postcodes/createindex") {
    postcodeRepository.createIndex()
    redirect(url(getPage))
  }
  
  get("/postcodes/deleteindex") {
    postcodeRepository.deleteIndex()
    redirect(url(getPage))
  }
  
  get("/vacatures/createindex") {
    vacatureRepository.createIndex()
    redirect(url(getPage))
  }
  
  get("/vacatures/deleteindex") {
    vacatureRepository.deleteIndex()
    redirect(url(getPage))
  }
  
  get("/vacatures/indexall") {
    new VacaturesIndexer(vacatureRepository, postcodeRepository).index
    redirect(url(getPage))
  }
  
  get("/huizen/createindex") {
    huizenRepository.createIndex()
    redirect(url(getPage))
  }
  
  get("/huizen/deleteindex") {
    huizenRepository.deleteIndex()
    redirect(url(getPage))
  }
  
 
  post("/") {
    //postcodeRepository.importFile(fileParams("postcodeFile").getInputStream)
    redirect(url(getPage))
  }
}