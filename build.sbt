name := """Tracks"""
organization := "Alok"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.12"

resolvers += "Typesafe repository" at "https://repo.typesafe.com/typesafe/releases/"


libraryDependencies += guice
libraryDependencies += "com.typesafe.play" %% "play-json-joda" % "2.6.10"
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test
libraryDependencies += "org.mockito" % "mockito-core" % "3.2.4" % Test
libraryDependencies += "org.scalamock" %% "scalamock-scalatest-support" % "3.6.0" % Test
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.3" % Test



// Adds additional packages into Twirl
//TwirlKeys.templateImports += "Alok.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "Alok.binders._"
