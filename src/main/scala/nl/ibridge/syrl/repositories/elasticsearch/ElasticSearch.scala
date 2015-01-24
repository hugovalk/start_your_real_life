package nl.ibridge.syrl.repositories.elasticsearch

import org.elasticsearch.node.Node
import com.sksamuel.elastic4s.ElasticClient
import com.sksamuel.elastic4s.mappings.MappingDefinition
import com.sksamuel.elastic4s.ElasticDsl._
import com.sksamuel.elastic4s.mappings.FieldType._
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

trait ElasticSearch {
  
  def client: ElasticClient
  
  def indexName: String
  
  def typeName: String
  
  def mapping: MappingDefinition
  
  def createIndex(): Future[Boolean] = client.execute {
    create index indexName mappings {mapping}
  }.map(_.isAcknowledged())
  
  def deleteIndex(): Future[Boolean] = client.execute {
    delete index (indexName)
  }.map(_.isAcknowledged())
}