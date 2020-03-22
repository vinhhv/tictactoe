import sbt._
import sbt.Keys._

object Build {
  object v {
    // common
    val enumeratum = "1.5.13"
    val zio        = "1.0.0-RC16"
    val zioMacros  = "0.5.0"

    // tictactoe
    val atto = "0.7.2"

    // compiler plugins
    val kindProjector = "0.10.3"
    val silencer      = "1.6.0"
  }

  val commonDeps = Seq(
      "com.beachape" %% "enumeratum"      % v.enumeratum
    , "dev.zio"      %% "zio"             % v.zio
    , "dev.zio"      %% "zio-macros-core" % v.zioMacros
    , "dev.zio"      %% "zio-macros-test" % v.zioMacros
  )

  val tictactoeDeps = Seq(
      "org.tpolecat" %% "atto-core"    % v.atto
    , "org.tpolecat" %% "atto-refined" % v.atto
  )

  val pluginDeps = Seq(
    "com.github.ghik" % "silencer-lib_2.13.1" % v.silencer % Provided
  )

  val testDeps = Seq(
      "dev.zio" %% "zio-test"     % v.zio % "test"
    , "dev.zio" %% "zio-test-sbt" % v.zio % "test"
  )

  private val stdOptions = Seq(
    // see https://docs.scala-lang.org/overviews/compiler-options/index.html#Standard_Settings
    "-deprecation"
    , "-encoding", "UTF-8"
    , "-explaintypes"
    , "-feature"
    , "-language:existentials"
    , "-language:higherKinds"
    , "-language:implicitConversions"
    , "-language:postfixOps"
    , "-opt-warnings"
    , "-opt:l:inline"
    , "-opt-inline-from:<source>"
    , "-unchecked"
    , "-Ymacro-annotations"
    , "-Ywarn-extra-implicit"
    , "-Ywarn-numeric-widen"
    , "-Ywarn-self-implicit"
    , "-Ywarn-unused"
    , "-Ywarn-value-discard"
    , "-Xfatal-warnings"
    , "-Xlint"
    , "-Xlint:inaccessible"
  )

  private val libOptions = Seq(
    "-P:silencer:checkUnused"
  )

  def stdSettings(projectName: String) =
    Seq(
      name := s"big-daddy-$projectName"
      , scalacOptions := stdOptions ++ libOptions
      , scalaVersion in ThisBuild := "2.13.1"
      , connectInput in run := true
      , fork := true
      , logBuffered := false
      , outputStrategy := Some(StdoutOutput)
      , libraryDependencies ++= (commonDeps ++ testDeps ++ pluginDeps)
      , addCompilerPlugin("org.typelevel"   %% "kind-projector"  % v.kindProjector)
      , addCompilerPlugin("com.github.ghik" % "silencer-plugin_2.13.1" % v.silencer)
      , testFrameworks := Seq(new TestFramework("zio.test.sbt.ZTestFramework"))
    )
}
