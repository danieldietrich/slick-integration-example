object EvolutionGenerator extends App {

  import models.DAL
  
  import play.api.test._
  import play.api.test.Helpers._

  running(FakeApplication(additionalConfiguration = inMemoryDatabase() ++
      Map("db.default.slick.driver" -> "scala.slick.driver.MySQLDriver", "evolutionplugin" -> "disabled"))) {

    val evolution = (
      """|# --- !Ups
         |""" + DAL.ddl.createStatements.mkString("\n", ";\n\n", ";\n") +
      """|
         |# --- !Downs
         |""" + DAL.ddl.dropStatements.mkString("\n", ";\n\n", ";\n")).stripMargin

    println(evolution)
    
  }

}
