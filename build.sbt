import java.io.File

lazy val commonSettings = Seq(
  name := "ia-books-archivespark",
  organization := "de.l3s",
  version := "1.0.1",
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
      "de.l3s" %% "archivespark" % "2.1.1" % "provided"
    ),
    resolvers ++= Seq(
      "internetarchive" at "http://builds.archive.org/maven2"
    ),
    publishTo := Some(Resolver.file("file",  new File(Path.userHome.absolutePath+"/.m2/repository"))),
    pomExtra :=
      <licenses>
        <license>
          <name>MIT License</name>
          <url>http://www.opensource.org/licenses/mit-license.php</url>
          <distribution>repo</distribution>
        </license>
      </licenses>
  )

assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false)

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case _ => MergeStrategy.first
}
