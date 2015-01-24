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
    index into indexName doc vacature id vacature.id
  }.map(_.isCreated())
  
  def find: Future[List[Vacature]] = client.execute {
    search in indexName -> typeName fields(
        "id", "titel", "samenvatting", "vakgebied", "provincie", "plaats", "salaris")
  }.map { response => 
    response.getHits.getHits.map { 
      hit => 
        new Vacature(
            hit.field("id").getValue.asInstanceOf[Int], 
            hit.field("titel").getValue.toString(), 
            hit.field("samenvatting").getValue.toString(), 
            hit.field("vakgebied").getValue.toString(), 
            hit.field("provincie").getValue.toString(), 
            hit.field("plaats").getValue.toString(), 
            hit.field("salaris").getValue.asInstanceOf[Int])
    }.toList
  }
 
  def get(id: Int): Future[Option[Vacature]] = Future(Some(new Vacature(1, "", "", "", "", "", 1)))
  
  val indexName = "vacatures"
  
  val typeName = "vacatures"
  
  val mapping = typeName as {
    "id" typed IntegerType
  }  
}