import nl.ibridge.syrl.app._
import org.scalatra._
import javax.servlet.ServletContext
import nl.ibridge.syrl.repositories.mock.MockVacaturesRepository

class ScalatraBootstrap extends LifeCycle {
  override def init(context: ServletContext) {
    context.mount(new VacaturesServlet(new MockVacaturesRepository), "/services/vacatures")
  }
}
