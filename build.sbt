import Build._

name := "tictactoe"

organization in ThisBuild := "vinh.vu"
version := "0.1"

resolvers += Resolver.sonatypeRepo("releases")

lazy val tictactoe =
  project
    .in(file("."))
    .settings(stdSettings("tictactoe"))
    .settings(libraryDependencies ++= tictactoeDeps)
