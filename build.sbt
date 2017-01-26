lazy val commonSettings = Seq(
  name := "ia-books-archivespark",
  organization := "de.l3s",
  version := "1.0.2",
  scalaVersion := "2.11.8",
  fork := true
)

lazy val archivespark = (project in file(".")).
  settings(commonSettings: _*).
  settings(
    libraryDependencies ++= Seq(
      "org.apache.spark" %% "spark-core" % "2.0.1" % "provided" excludeAll(
        ExclusionRule(organization = "org.apache.hadoop"),
        ExclusionRule(organization = "org.scala-lang"),
        ExclusionRule(organization = "com.google.guava")),
      "org.apache.hadoop" % "hadoop-client" % "2.5.0" % "provided",
      "org.scala-lang.modules" %% "scala-xml" % "1.0.6",
      "de.l3s" %% "archivespark" % "2.1.2" % "provided"
    ),
    resolvers ++= Seq(
      "internetarchive" at "http://builds.archive.org/maven2",
      Resolver.mavenLocal
    )
  )

assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false)

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case _ => MergeStrategy.first
}
