# colour-solid-maze
The unacceptable face of a wee text-adventure game

A text-adventure, where the player tries to escape from a maze. The colour-themes of the rooms and their locations form a discrete representation of a colour-solid.

The baseline-code from which this game was developed was provided by Aalto University (Helsinki, Finland) to students on the Scala-programming course: Ohjelmointi 1, in the autumn of 2015.

This repository only contains the components needed for converting the original JVM-Scala version of the game into a Scala.js version, plus a maze-map in pdf format, and this readme file. So in the repo you can see:

- index.html               :: loads the minified javascript file which contains all the game logic, sets up the page, and calls the relevant method of our Scala object to get the game started.
- colour-solid-maze-opt.js :: the aforesaid minified javascript file (built using SBT (Scala Build Tool)).
- build.sbt                :: the file in actual project root directory, which was used to Scala.js-ise the game (not all dependencies actually needed, e.g. the author avoided jQuery coding and used scalajs-dom, scalatags instead; also, the scala-swing dependency was to make sure a different UI.scala file in the same project would compile).
- plugins.sbt              :: a file in the ./project directory, to add the scala.js plugin.
- build.properties         :: a file in the ./project directory, to tell SBT which version of itself is wanted.
- AdventureCSMJSUI.scala   :: the connecting object between index.html and the game logic, it therefore handles both back-facing tasks such as calling appropriate methods and handling the responses, and front-facing tasks such as manipulating the DOM nodes according to user input and game responses. 
- README.md                :: obviously you can see this project descriptor, since you are reading it right now.
