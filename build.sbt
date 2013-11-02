name := "foobar"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  "postgresql" % "postgresql" % "9.1-901-1.jdbc4",
  jdbc,
  anorm,
  cache
)     

play.Project.playScalaSettings
