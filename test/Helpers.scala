import models.DAL._
import play.api.test.FakeApplication
import play.api.test.Helpers._
import scala.slick.session.Session

object Helpers {

  def fysellFakeApplication() = FakeApplication(additionalConfiguration = inMemoryDatabase() ++
      Map("slick.default.driver" -> "scala.slick.driver.H2Driver",
        "evolutionplugin" -> "disabled"))
  
  def fakeApp[T](block: => T): T =
    running(fysellFakeApplication) {
      try {
        db.withSession { implicit s: Session =>
          try {
            create
            block
          } finally {
            drop
          }
        }
      }
    }

}
