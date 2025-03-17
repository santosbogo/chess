package edu.austral.dissis.chess.ui

import edu.austral.dissis.checkers.games.classic.ClassicCheckers
import javafx.application.Application

fun main() {
    GameProvider.game = ClassicCheckers().generateGame()
    Application.launch(GameApplication::class.java)
}
