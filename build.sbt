lazy val root = (project in file(".")).
  enablePlugins(ScalaJSPlugin).
  settings(
    name := "Colour Solid Maze",
    scalaVersion := "2.11.7"
  )

libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "0.8.2"
libraryDependencies += "be.doeraene" %%% "scalajs-jquery" % "0.8.1"
libraryDependencies += "com.lihaoyi" %%% "scalatags" % "0.4.6"
libraryDependencies += "org.scala-lang" % "scala-swing" % "2.11.0-M7"
