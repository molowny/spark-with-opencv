name := "sparkWithOpenCV"
version := "0.0.1"

scalaVersion := "2.11.12"

libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.4.4" % "provided"
libraryDependencies += "org.apache.spark" %% "spark-mllib" % "2.4.4" % "provided"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.1" % "test"

javaOptions ++= Seq(
  "-Xms512M",
  "-Xmx2048M",
  "-XX:MaxPermSize=2048M",
  "-XX:+CMSClassUnloadingEnabled"
)

scalacOptions ++= Seq(
  "-Ywarn-unused-import"
)

fork in Test := true
testOptions in Test += Tests.Argument(TestFrameworks.ScalaTest, "-oD")
