package nl.ibridge.syrl.app

/**
 * @author hv01016
 */
class ImportServlet() extends StartYourRealLifeStack {
  
  before() {
    contentType = "text/html"
  }
  
  get("/") {  
    ssp("/import")
  }
}