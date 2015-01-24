package nl.ibridge.syrl.repositories.elasticsearch

import scala.xml.XML
import nl.ibridge.syrl.repositories.VacaturesRepository
import nl.ibridge.syrl.model.vacature.Vacature
import nl.ibridge.syrl.repositories.PostcodeRepository
import scala.concurrent.Await
import scala.concurrent.duration.Duration

class VacaturesIndexer(vacaturesRepository: ESVacaturesRepository, postcodeRepository: PostcodeRepository) {
  
  def index = {
    val xml = XML.load("http://www.randstad.nl/mobile/jobSearchService?pageSize=4000")
    val jobs = xml \\ "job"
    jobs.foreach { node =>
      val idNode = node \ "id"
      val id = idNode.text
      println(id)
      val jobXml = XML.load("http://www.randstad.nl/mobile/jobDetailsService?jobId=" + id)
      jobXml.take(10).foreach { node  =>
        val description = (node \ "description").text
        val salary = node \ "salary"
        val salaryFrom = if (salary.isEmpty) 0 else (salary \ "from").text.toDouble.toInt
        val postcodeNode = node \ "postalCodeWorkLocation"
        val postcode = if (postcodeNode.isEmpty) None else Some[String](postcodeNode.text)
        val vacature = new Vacature(
            (node \ "reference").text.toInt,
            (node \ "title").text,
            if (description.length() > 200) description.substring(0, 200) else description,
            ((node \ "sector") ++ List()).head.text,
            postcode match {
              case Some(pc) => Await.result(postcodeRepository.findProvincieForPostcode(pc), Duration("2 s"))
              case None => ""
            },
            (node \ "location").text,
            salaryFrom
            )
        vacaturesRepository.save(vacature)
      }
    }
  }

}