package nl.ibridge.syrl.app

import org.scalatra.json.JacksonJsonSupport
import org.json4s.Formats
import org.json4s.DefaultFormats
import org.scalatra.ScalatraServlet


/**
 * @author hv01016
 */
class JsonStack extends ScalatraServlet with JacksonJsonSupport {
  protected implicit val jsonFormats: Formats = DefaultFormats

  before() {
    contentType = formats("json")
  }
}