package nl.ibridge.syrl.repositories

import scala.concurrent.Future
import java.io.File
import scala.concurrent.ExecutionContext.Implicits.global
import com.github.tototoshi.csv.CSVReader
import java.io.InputStream
import java.io.InputStreamReader
import nl.ibridge.syrl.model.Postcode
import com.github.tototoshi.csv.CSVFormat
import com.github.tototoshi.csv.DefaultCSVFormat


/**
 * @author hv01016
 */
trait PostcodeRepository {
  implicit object PostcodeFormat extends DefaultCSVFormat {
    override val delimiter = ';'
  }
  
  def importFile(inputStream: InputStream): Future[Boolean] = {
    val reader = CSVReader.open(new InputStreamReader(inputStream))
    val results = reader.toStream().map{ line =>
      val postcode = new Postcode(
          line(1), 
          line(3).toInt, 
          line(4), 
          line(13), 
          line(9), 
          line(8),
          line(15).toDouble,
          line(16).toDouble)
      Thread.sleep(10)
      save(postcode)
    }
    Future.reduce(results)((a,b)=> a && b)
  }
  
  def save(postcode: Postcode): Future[Boolean]
  
  def find: Future[List[Postcode]]
  
}