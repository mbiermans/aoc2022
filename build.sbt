val scala3Version = "3.1.0"

lazy val root = project
  .in(file("."))
  .settings(
    name         := "AOC 2022",
    version      := "0.1.0-SNAPSHOT",
    scalaVersion := scala3Version,
    libraryDependencies ++= Seq(
      "org.scalameta" %% "munit"     % "0.7.29" % Test,
      "org.scalatest" %% "scalatest" % "3.2.9"  % Test
    )
  )
