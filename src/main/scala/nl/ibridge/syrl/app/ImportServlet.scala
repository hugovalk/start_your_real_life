package nl.ibridge.syrl.app

import nl.ibridge.syrl.repositories.PostcodeRepository
import nl.ibridge.syrl.repositories.PostcodeRepository
import org.scalatra.servlet.FileUploadSupport
import org.scalatra.UrlGeneratorSupport
import java.io.InputStreamReader

/**
 * @author hv01016
 */
class ImportServlet(val postcodeRepository: PostcodeRepository) extends StartYourRealLifeStack 
    with FileUploadSupport with UrlGeneratorSupport {
  
  before() {
    contentType = "text/html"
  }
  
  val getPage = get("/") {  
    ssp("/import")
  }
  
  post("/") {
    postcodeRepository.importFile(fileParams("postcodeFile").getInputStream)
    redirect(url(getPage))
  }
}