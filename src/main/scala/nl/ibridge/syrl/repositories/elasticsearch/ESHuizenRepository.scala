package nl.ibridge.syrl.repositories.elasticsearch


import com.sksamuel.elastic4s.ElasticDsl._
import com.sksamuel.elastic4s.mappings.FieldType._
import scala.concurrent.ExecutionContext.Implicits.global
import org.elasticsearch.node.Node
import nl.ibridge.syrl.repositories.VacaturesRepository
import nl.ibridge.syrl.model.huis.Huis
import nl.ibridge.syrl.model.huis.Koop
import nl.ibridge.syrl.model.huis.Adres
import scala.concurrent.Future
import nl.ibridge.syrl.repositories.HuizenRepository
import org.elasticsearch.client.Client
import com.sksamuel.elastic4s.ElasticClient

/**
 * @author hv01016
 */
class ESHuizenRepository(val client: ElasticClient) extends HuizenRepository with ElasticSearch {
  
  def save(vacature: Huis): Future[Boolean] = client.execute {
    index into indexName doc vacature
  }.map(_.isCreated())
  
  def find: Future[List[Huis]] = client.execute {
    search in indexName -> typeName
  }.map { response => 
    response.getHits.getHits.map { 
      hit => 
        new Huis(1, Koop, "200.000 k.k.", 4, "Omschrijving", new Adres("","",""))
    }.toList
  }
  
  def get(id: Int): Future[Option[Huis]] = Future(Some(new Huis(1, Koop, "200.000 k.k.", 4, "Omschrijving", new Adres("","",""))))
  
  val indexName = "huizen"
  
  val typeName = "huis"
  
  val mapping = typeName as {
    "id" typed IntegerType
  }  
  
}