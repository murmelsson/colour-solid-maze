package adventure.ui

import adventure._
//import org.scalajs.dom.html.Div
//import org.scalajs.jquery.{JQueryEventObject, jQuery}
//import scala.scalajs.js
import org.scalajs.dom
import dom.html
import scalajs.js.annotation.JSExport
//import scalatags.JsDom
import scalatags.JsDom.all._

@JSExport
object AdventureCSMJSUI {

  private var game = new Adventure
  private var player = game.player

  private var theHtmlTarget = dom.document.getElementById("main-container")
  private val gameTitleDiv = dom.document.getElementById("game-title")
  private val executeButton = dom.document.getElementById("button").asInstanceOf[html.Button]
  private val inputField = dom.document.getElementById("input").asInstanceOf[html.Input]
  private val areaDiv = dom.document.getElementById("area")

  private val playAgainButton = button( id:="play-again-button",
    "Click to restart"
    ).render
  //playAgainButton.setAttribute("style", "background-color: red")
  //playAgainButton.style.display = "block"
  //playAgainButton.style.backgroundColor = "red"
  //theHtmlTarget.insertBefore(playAgainButton, areaDiv)
  theHtmlTarget.parentNode.insertBefore(playAgainButton, theHtmlTarget)
  //playAgainButton.style.setProperty("background-color", "red", "!important")
  //playAgainButton.setAttribute("style", "background-color: red")
  playAgainButton.onclick = (e: dom.Event) => {
    game = new Adventure
    player = game.player
    inputField.value = ""
    inputField.focus()
    dom.document.getElementById("output").textContent = "[Turns played: 0/80]"
    main(theHtmlTarget)
  }


  private def start() = {
    areaDiv.textContent = this.game.welcomeMessage + "\n" + "\n"
    this.printAreaInfo()
  }

  private def printAreaInfo() = {
    val area = this.player.location
    //val theAreaTarget = theHtmlTarget.firstElementChild
    areaDiv.textContent += area.name + "\n"
    areaDiv.textContent += "-" * area.name.length + "\n"
    areaDiv.textContent += area.fullDescription + "\n"
  }

  private def playTurn() = {
    //println("in playTurn!")
    val inputBox = dom.document.getElementById("input").asInstanceOf[html.Input]
    val outputBox = dom.document.getElementById("output")
    if (!this.game.isOver) {
        //println("this is ON!")
        val command = inputBox.value
        inputBox.value = ""

        val turnReport = this.game.playTurn(command)
        
        if (!turnReport.isEmpty) {
          outputBox.textContent = turnReport
          outputBox.textContent += "\n[Turns played: " + this.game.turnCount + "/80]"
        }        

        areaDiv.textContent = ""
        printAreaInfo()

        if (this.game.isOver) {
           val lastMove = outputBox.textContent
           outputBox.textContent = "History is mostly imaginary, though murky traces " +
             "(such as your last move) remain, " +
             "like the fossilised patterns in paleontological seas..." +
             "\n" + this.game.goodbyeMessage +
             "\n\n[Refresh the page or press 'Click to restart'-button to play again.]" +
             "\nYour last move was: \n" + lastMove

           val shellButton = dom.document.getElementById("button").asInstanceOf[html.Button]
           shellButton.disabled = true
           playAgainButton.setAttribute("style", "visibility: visible; background-color: cyan")
        }
    }
    //println("Game is over?: " + this.game.isOver)
  }

  private def playTurnOnHittingEnter(e: dom.KeyboardEvent) {
    if (e.keyCode == 13) {
        playTurn()
    }
  }

  @JSExport
  def main(target: dom.Element): Unit = {
    gameTitleDiv.textContent = this.game.title
    //println("In UI-main method!")
    playAgainButton.setAttribute("style", "visibility: hidden")
    executeButton.disabled = false
    executeButton.onclick = (e: dom.Event) => playTurn()
    inputField.onkeyup = (e: dom.KeyboardEvent ) => playTurnOnHittingEnter(e)
    theHtmlTarget = target
    this.start()
  }
}
