import nl.ibridge.syrl.app._
import org.scalatra._
import javax.servlet.ServletContext
import nl.ibridge.syrl.repositories.mock.MockVacaturesRepository
import nl.ibridge.syrl.repositories.mock.MockHuizenRepository
import nl.ibridge.syrl.repositories.PostcodeRepository
import nl.ibridge.syrl.repositories.mock.MockPostcodeRepository
import nl.ibridge.syrl.repositories.elasticsearch._
import org.elasticsearch.node.NodeBuilder
import org.elasticsearch.node.Node
import java.net.InetAddress
import java.net.UnknownHostException
import com.sksamuel.elastic4s.ElasticClient
import nl.ibridge.syrl.repositories.mock.MockVacaturesRepository


class ScalatraBootstrap extends LifeCycle {
  
  val node: Node = NodeBuilder.nodeBuilder()
    .client(true)
    .clusterName("elasticsearch-" + getHostName().getOrElse("syrl")).node()
    
    private def getHostName(): Option[String] = Some("LP-70829")
    
//  private def getHostName(): Option[String] = {
//    try {
//      return Some(InetAddress.getLocalHost().getHostName)
//    } catch { 
//      case ex: UnknownHostException => 
//        println("Hostname can not be resolved")
//        return None
//    }
//  }
  
  override def init(context: ServletContext) {
    val client = ElasticClient.fromNode(node)
    val postcodeRepository = new ESPostcodeRepository(client)
    val vacaturesRepository = new ESVacaturesRepository(client)
    val huizenRepository = new ESHuizenRepository(client)
    context.mount(new VacaturesServlet(vacaturesRepository), "/services/vacatures")
    context.mount(new HuizenServlet(new MockHuizenRepository), "/services/huizen")
    context.mount(new ImportServlet(postcodeRepository, vacaturesRepository, huizenRepository), "/data/import")
    context.mount(new GeoDataServlet(postcodeRepository), "/services/geodata")
    context.mount(new AdviezenServlet(vacaturesRepository), "/services/advies")
  }
  
  override def destroy(context: ServletContext) {
    node.close()
  }
}
