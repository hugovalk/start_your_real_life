import sbt._
import Keys._
import org.scalatra.sbt._
import org.scalatra.sbt.PluginKeys._
import com.mojolly.scalate.ScalatePlugin._
import ScalateKeys._

object StartYourRealLifeBuild extends Build {
  val Organization = "nl.ibridge"
  val Name = "Start Your Real Life"
  val Version = "0.1.0-SNAPSHOT"
  val ScalaVersion = "2.11.5"
  val ScalatraVersion = "2.3.0"

  lazy val project = Project (
    "start-your-real-life",
    file("."),
    settings = ScalatraPlugin.scalatraWithJRebel ++ scalateSettings ++ Seq(
      organization := Organization,
      name := Name,
      version := Version,
      scalaVersion := ScalaVersion,
      resolvers += Classpaths.typesafeReleases,
      libraryDependencies ++= Seq(
        "org.scalatra" %% "scalatra" % ScalatraVersion,
        "org.scalatra" %% "scalatra-scalate" % ScalatraVersion,
        "org.scalatra" %% "scalatra-specs2" % ScalatraVersion % "test",
        "org.scalatra" %% "scalatra-scalatest" % ScalatraVersion % "test",        "org.scalatra" %% "scalatra-json" % ScalatraVersion,
        "com.fasterxml.jackson.core" % "jackson-databind" % "2.4.2",
        "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.4.2",
        "org.json4s"   %% "json4s-jackson" % "3.2.11",
        "org.json4s"   %% "json4s-native" % "3.2.11",
        "org.slf4j" % "slf4j-api" % "1.7.6",
        "com.sksamuel.elastic4s" %% "elastic4s" % "1.4.10",    
        "ch.qos.logback" % "logback-classic" % "1.1.2" % "runtime",
        "org.eclipse.jetty" % "jetty-webapp" % "9.1.5.v20140505" % "container",
        "org.eclipse.jetty" % "jetty-plus" % "9.1.5.v20140505" % "container",
        "javax.servlet" % "javax.servlet-api" % "3.1.0",
        "com.github.tototoshi" %% "scala-csv" % "1.1.2"
      ),
      scalateTemplateConfig in Compile <<= (sourceDirectory in Compile){ base =>
        Seq(
          TemplateConfig(
            base / "webapp" / "WEB-INF" / "templates",
            Seq.empty,  /* default imports should be added here */
            Seq(
              Binding("context", "_root_.org.scalatra.scalate.ScalatraRenderContext", importMembers = true, isImplicit = true)
            ),  /* add extra bindings here */
            Some("templates")
          )
        )
      }
    )
  )
}
