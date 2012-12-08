package models

import scala.slick.integration.PlayProfile
import scala.slick.integration._DAL
import scala.slick.lifted.DDL
import play.api.Play.current

class DAL(dbName: String) extends _DAL with ProductComponent with UserComponent with PlayProfile {

  // trait Profile implementation
  val profile = loadProfile(dbName)
  def db = dbProvider(dbName)

  // _DAL.ddl implementation
  lazy val ddl: DDL = Products.ddl ++ Users.ddl

}

object DAL extends DAL("default")
