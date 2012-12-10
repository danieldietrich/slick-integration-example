package models

import scala.slick.integration._DAL
import scala.slick.integration.SimpleProfile
import scala.slick.lifted.DDL
import scala.slick.session.Database
import play.api.Play.{current => app}
import play.api.db.DB

class DAL(dbName: String) extends _DAL with ProductComponent with UserComponent with SimpleProfile {

  override val profile = {
    val conf = app.configuration
    val path = "db." + dbName + ".slick.driver"
    val driver = conf.getString(path).getOrElse(throw conf.reportError(path, "Missing configuration [" + path + "]"))
    load(driver)
  }
  
  override def db = Database.forDataSource(DB.getDataSource(dbName))

  // _DAL.ddl implementation
  lazy val ddl: DDL = Products.ddl ++ Users.ddl

}

object DAL extends DAL("default")
