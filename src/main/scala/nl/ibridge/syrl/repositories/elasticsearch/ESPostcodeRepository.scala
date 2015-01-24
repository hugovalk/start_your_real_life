package nl.ibridge.syrl.repositories.elasticsearch

import nl.ibridge.syrl.repositories.PostcodeRepository
import nl.ibridge.syrl.model.Postcode
import scala.concurrent.Future
import com.sksamuel.elastic4s.ElasticDsl._
import com.sksamuel.elastic4s.mappings.FieldType._
import scala.concurrent.ExecutionContext.Implicits.global
import org.elasticsearch.node.Node
import com.sksamuel.elastic4s.ElasticClient


/**
 * @author hv01016
 */
class ESPostcodeRepository(val client: ElasticClient) extends PostcodeRepository with ElasticSearch {
  
  def save(postcode: Postcode): Future[Boolean] = client.execute {
    index into indexName doc postcode
  }.map(_.isCreated())
  
  def find: Future[List[Postcode]] = client.execute {
    search in indexName -> typeName
  }.map { response => 
    response.getHits.getHits.map { 
      hit => 
        new Postcode("", 1, "", "", "", "", 0.0, 0.0)
    }.toList
  }
  
  val indexName = "postcodes"
  
  val typeName = "postcode"
  
  val mapping = typeName as {
    "id" typed IntegerType
  }
  
  
}