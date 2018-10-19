name := "bborzoi_bot"


lazy val commonSettings = Seq(
  version := "0.1",
  organization := "com.texnodog",
  scalaVersion := "2.12.7",
  test in assembly := {}
)


lazy val appHelloWorld = (project in file("."))
  .settings(commonSettings: _*)