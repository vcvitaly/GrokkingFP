ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.2.2"

lazy val root = (project in file("."))
  .settings(
    name := "GrokkingFP",
    idePackagePrefix := Some("io.github.vcvitaly.grokkingfp")
  )

libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.16"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.16" % "test"
libraryDependencies += "org.scalatestplus" %% "scalacheck-1-17" % "3.2.16.0" % "test"