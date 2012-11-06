organization := "se.fishtank"

name := "simple-dag"

version := "0.1.0"

scalaVersion := "2.9.2"

javacOptions ++= Seq("-source", "1.6", "-target", "1.6", "-encoding", "UTF-8")

scalacOptions ++= Seq("-deprecation", "-unchecked", "-Xcheckinit", "-Xmigration", "-encoding", "UTF-8")

libraryDependencies ++= Seq("junit" % "junit" % "4.10" % "test")

