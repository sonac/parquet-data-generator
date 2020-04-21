name := "parquet-data-generator"

version := "0.1"

scalaVersion := "2.12.11"

resolvers += Resolver.sonatypeRepo("snapshots")

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "2.4.5" % "provided",
  "org.apache.spark" %% "spark-sql" % "2.4.5" % "provided",
  "com.danielasfregola" %% "random-data-generator" % "2.9-SNAPSHOT",
  "org.scala-lang" % "scala-reflect" % "2.12.11"
)
