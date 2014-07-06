import sbt._
import Keys._
import play.Project._

object ProfileServiceBuild extends Build {

  object V {
    val astyanax = "1.56.44"
  }

  val appName         = "crunchero"
  val appVersion      = "1.0.0-SNAPSHOT"

  val appDependencies = Seq(
    "com.netflix.astyanax" % "astyanax-core" % V.astyanax exclude("org.slf4j", "slf4j-log4j12"),
    "com.netflix.astyanax" % "astyanax-thrift" % V.astyanax exclude("org.slf4j", "slf4j-log4j12"),
    "com.netflix.astyanax" % "astyanax-entity-mapper" % V.astyanax exclude("org.slf4j", "slf4j-log4j12"),
    "com.google.code.findbugs" % "jsr305" % "1.3.+",
    "landscape" %% "landscape" % "1.1.3-SNAPSHOT",
    "org.scala-lang.modules" %% "scala-async" % "0.9.1",
    "com.tuplejump" %% "calliope" % "0.9.0-U1-EA",
    "org.apache.spark" %% "spark-core" % "0.9.0-incubating",
    "com.typesafe.akka" %% "akka-actor" % "2.2.3",
    "com.typesafe.akka" %% "akka-slf4j" % "2.2.3"
  )

  val main = play.Project(appName, appVersion, appDependencies).settings(
    scalaVersion        := "2.10.3",
    routesImport        ++= Seq("extensions.Binders._", "com.eaio.uuid.UUID"),
    scalacOptions       := Seq("-unchecked", "-deprecation", "-feature", "-language:implicitConversions", "-language:postfixOps"),
    javaOptions in Test += "-Dconfig.file=conf/test-application.conf")

}
