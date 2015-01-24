package nl.ibridge.syrl.repositories.elasticsearch

import org.elasticsearch.node.Node
import nl.ibridge.syrl.repositories.VacaturesRepository
import scala.concurrent.Future
import nl.ibridge.syrl.model.vacature.Vacature
import com.sksamuel.elastic4s.ElasticDsl._
import com.sksamuel.elastic4s.mappings.FieldType._
import scala.concurrent.ExecutionContext.Implicits.global
import com.sksamuel.elastic4s.ElasticClient
import nl.ibridge.syrl.model.vacature.Vacature


/**
 * @author hv01016
 */
class ESVacaturesRepository(val client: ElasticClient) extends VacaturesRepository with ElasticSearch {
  
  def save(vacature: Vacature): Future[Boolean] = client.execute {
    index into indexName doc vacature
  }.map(_.isCreated())
  
  def find: Future[List[Vacature]] = client.execute {
    search in indexName -> typeName
  }.map { response => 
    response.getHits.getHits.map { 
      hit => 
        new Vacature(1, "", "", "", "", "", 1)
    }.toList
  }
 
  def get(id: Int): Future[Option[Vacature]] = Future(Some(new Vacature(1, "", "", "", "", "", 1)))
  
  val indexName = "vacatures"
  
  val typeName = "vacature"
  
  val mapping = typeName as {
    "id" typed IntegerType
  }  
}