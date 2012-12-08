import Helpers._
import models._
import models.DAL._
import org.specs2.mutable.Specification
import scala.slick.session.Session

class DomainModelSpec extends Specification {

  "Slick" should {
    "be testable with in-memory h2 driver" in fakeApp {
      db.withSession { s: Session =>
        s.metaData.getDatabaseProductName must equalTo("H2")
      }
    }
  }

  "Product model" should {
    "be retrieved by id" in fakeApp {

      val product = Products.insert(Product("name", "description"))
      product.id must not equalTo (None) // AutoInc id correct
      val id = product.id.get

      Products.findById(id) must equalTo(Some(Product("name", "description", Some(id)))) // product found
      Products.delete(id) must equalTo(true) // one row deleted 
      Products.findById(id) must equalTo(None) // product not found

    }
  }

}
