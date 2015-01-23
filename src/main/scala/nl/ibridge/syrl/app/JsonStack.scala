package nl.ibridge.syrl.app

import org.scalatra.json.JacksonJsonSupport
import org.json4s.Formats
import org.json4s.DefaultFormats
import org.scalatra.ScalatraServlet
import nl.ibridge.syrl.utils.HuisTypeSerializer

/**
 * @author hv01016
 */
class JsonStack extends ScalatraServlet with JacksonJsonSupport {
  protected implicit val jsonFormats: Formats = 
    DefaultFormats + new HuisTypeSerializer()

  before() {
    contentType = formats("json")
  }
  
  notFound {
    // remove content type in case it was set through an action
    contentType = null
    resourceNotFound()
  }
}