package nl.ibridge.syrl.repositories.elasticsearch

import nl.ibridge.syrl.repositories.PostcodeRepository
import nl.ibridge.syrl.model.Postcode
import scala.concurrent.Future
import com.sksamuel.elastic4s.ElasticDsl._
import com.sksamuel.elastic4s.mappings.FieldType._
import scala.concurrent.ExecutionContext.Implicits.global
import org.elasticsearch.node.Node
import com.sksamuel.elastic4s.ElasticClient
import org.elasticsearch.search.facet.terms.TermsFacet
import scala.collection.JavaConverters._


/**
 * @author hv01016
 */
class ESPostcodeRepository(val client: ElasticClient) extends PostcodeRepository with ElasticSearch {
  
  def save(postcode: Postcode): Future[Boolean] = client.execute {
    index into indexName -> typeName doc postcode
  }.map(_.isCreated())
  
  def find: Future[List[Postcode]] = client.execute {
    search in indexName -> typeName
  }.map { response => 
    response.getHits.getHits.map { 
      hit => 
        new Postcode("", 1, "", "", "", "", 0.0, 0.0)
    }.toList
  }
  
  override def findProvincieForPostcode(postcode: String): Future[String] = client.execute {
    search in indexName -> typeName query postcode.replaceAll("\\s", "") fields "provincie"
//    query {
//      bool {
//        should {
//          query("", "")
////          query("postcodes.waarde", postcode.replaceAll("\\s", ""))
//        }
//      }
//    }
  }.map{response => 
    val hits = response.getHits
    if (hits.getTotalHits > 0) {
      val hit = response.getHits.getAt(0)
      val field = hit.field("provincie")
      field.getValue.toString()
    } else ""
  }
  
  override def findProvincies: Future[List[String]] = client.execute {
    val query = search in indexName -> typeName facets {
      facet terms "provincie" field "provincie"
    }
    println(query.build)
    query
  }.map { response => 
    val facets = response.getFacets
    println(response)
    val facet = facets.facet("provincie").asInstanceOf[TermsFacet]
    facet.getEntries.asScala.toList.asInstanceOf[List[TermsFacet.Entry]].map(_.getTerm.toString())}
  
  val indexName = "postcodes"
  
  val typeName = "postcodes"
  
  val mapping = typeName as {
    "id" typed IntegerType
  }
  
  
}