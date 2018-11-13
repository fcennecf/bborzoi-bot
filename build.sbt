name := "bborzoi_bot"

addCompilerPlugin(
  "org.scalamacros" % "paradise" % "2.1.1" cross CrossVersion.full
)

lazy val commonSettings = Seq(
  scalaVersion := "2.12.7",
  organization := "com.github.tdMuninn",
  version := "0.1",
  libraryDependencies ++= Seq(
    "com.typesafe.scala-logging" %% "scala-logging" % "3.9.0",
    "ch.qos.logback" % "logback-classic" % "1.2.1",
    "org.backuity.clist" %% "clist-core" % "3.4.0",
    "org.backuity.clist" %% "clist-macros" % "3.4.0" % "provided",
    //    "com.github.nscala-time" %% "nscala-time" % "2.20.0",
    "org.scala-lang.modules" %% "scala-xml" % "1.1.1",
    "org.scalatest" %% "scalatest" % "3.0.4" % Test
  ),
  scalacOptions ++= Seq(
    "-deprecation",
    "-feature",
    "-Xfatal-warnings",
    "-language:postfixOps",
    "-language:implicitConversions",
    "-language:higherKinds",
  ),
  test in assembly := {}
)

lazy val borzoiTelegramBot = (project in file("./com/bborzoi/bot"))
  .settings(commonSettings: _*)
  .settings(normalizedName := "borzoi-telegram-bot")
  .settings(
    libraryDependencies ++= Seq(
      "com.bot4s" %% "telegram-core" % "4.0.0-RC1",
      "com.bot4s" %% "telegram-akka" % "4.0.0-RC1",
      "io.circe" %% "circe-core" % "0.10.1",
      "io.circe" %% "circe-generic" % "0.10.1",
      "io.circe" %% "circe-parser" % "0.10.1"
    )
  )

lazy val borzoiExchanges = (project in file("./com/bborzoi/exchanges"))
  .settings(commonSettings: _*)
  .settings(normalizedName := "borzoi-exchanges")
  .settings(
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-actor" % "2.5.9",
      "com.typesafe.akka" %% "akka-stream" % "2.5.12",
      "com.softwaremill.sttp" %% "akka-http-backend" % "1.3.8",
      "com.lucidchart" %% "xtract" % "2.0.1",
    )
  )


lazy val root = project.in(file("."))
  .settings(commonSettings: _*)
  .dependsOn(
    borzoiTelegramBot,
    borzoiExchanges
  )
  .aggregate(
    borzoiTelegramBot,
    borzoiExchanges
  )
