val scala3Version = "3.2.1"

scalacOptions ++= Seq(
  "-encoding", "utf8", 
  // "-source", "future",  // remove this if you want to allow old Scala 2 syntax
  "-Xfatal-warnings",  
  "-deprecation",
  "-unchecked",

  "-feature"
)

lazy val root = project
  .in(file("."))
  .settings(
    name := "doobietest",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,

    libraryDependencies += "org.scalameta" %% "munit" % "0.7.29" % Test,

    libraryDependencies ++= Seq(
    // Start with this one
    "org.tpolecat" %% "doobie-core"      % "1.0.0-RC1",

    "org.tpolecat" %% "doobie-postgres"  % "1.0.0-RC1"
    // ,          // Postgres driver 42.3.1 + type mappings.
    // "org.tpolecat" %% "doobie-specs2"    % "1.0.0-RC1" % "test", // Specs2 support for typechecking statements.
    // "org.tpolecat" %% "doobie-scalatest" % "1.0.0-RC1" % "test"  // ScalaTest support for typechecking statements.
    )
  )
