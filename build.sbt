name := "bborzoi_bot"


lazy val commonSettings = Seq(
  scalaVersion := "2.12.7",
  organization := "com.github.tdMuninn",
  version := "0.1",
  libraryDependencies ++= Seq(
    "org.backuity.clist" %% "clist-core" % "3.4.0",
    "org.backuity.clist" %% "clist-macros" % "3.4.0" % "provided",
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

lazy val borzoiTelegramBot = project
  .settings(commonSettings: _*)
  .settings(normalizedName := "borzoi-telegram-bot")
  .settings(
    libraryDependencies ++= Seq(
      "com.bot4s" %% "telegram-core" % "4.0.0-RC1",
      "com.bot4s" %% "telegram-akka" % "4.0.0-RC1"
    )
  )

lazy val borzoiExchanges = project
  .settings(commonSettings: _*)
  .settings(normalizedName := "borzoi-exchanges")
  .settings(
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-actor" % "2.5.9",
      "com.typesafe.akka" %% "akka-stream" % "2.5.12",
    )
  )


lazy val root = project.in(file("."))
  .settings(commonSettings: _*)
  .aggregate(
    borzoiTelegramBot,
    borzoiExchanges
  )
