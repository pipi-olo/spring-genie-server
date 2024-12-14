ThisBuild / version           := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion      := "2.13.15"
ThisBuild / scalafmtOnCompile := true

lazy val versions = new {
  val finatra = "24.2.0"

  val breeze = "2.1.0"

  val logback = "1.4.12"

  val scalaTest = "3.2.19"
}

lazy val root = (project in file("."))
  .settings(
    name         := "spring-genie-server",
    organization := "com.pipiolo",
    libraryDependencies ++= Seq(
      // finatra
      "com.twitter" %% "finatra-http-server" % versions.finatra,

      // vector db
      "org.scalanlp" %% "breeze" % versions.breeze,

      // log
      "ch.qos.logback" % "logback-classic" % versions.logback,

      // for Test
      "org.scalatest" %% "scalatest"           % versions.scalaTest % Test,
      "com.twitter"   %% "finatra-http-server" % versions.finatra   % Test classifier "tests",
      "com.twitter"   %% "inject-server"       % versions.finatra   % Test classifier "tests",
      "com.twitter"   %% "inject-app"          % versions.finatra   % Test classifier "tests",
      "com.twitter"   %% "inject-core"         % versions.finatra   % Test classifier "tests",
      "com.twitter"   %% "inject-modules"      % versions.finatra   % Test classifier "tests"
    ),
    scalacOptions ++= Seq("-deprecation", "-feature", "-unchecked", "-encoding", "UTF-8"),
    fork := true
  )
