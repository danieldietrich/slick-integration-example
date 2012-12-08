import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "slick-integration-example"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    jdbc,
    // "mysql" % "mysql-connector-java" % "5.1.21",
    "com.typesafe" % "slick_2.10.0-RC5" % "0.11.2",
    "net.danieldietrich" %% "slick-integration" % "1.0-SNAPSHOT"
  )


  val main = play.Project(appName, appVersion, appDependencies).settings(
    resolvers += "Daniel's Repository" at "http://danieldietrich.net/repository/snapshots"
  ).settings(
      scalacOptions := Seq("-encoding", "UTF-8", "-target:jvm-1.6", "-deprecation", "-feature", "-unchecked", "-Ywarn-adapted-args")
  )

}
