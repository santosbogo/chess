package edu.austral.dissis.ui

import edu.austral.dissis.checkers.games.classic.ClassicCheckers
import edu.austral.dissis.engine.GameProvider
import javafx.application.Application

fun main() {
    GameProvider.game = ClassicCheckers().generateGame()
    Application.launch(GameApplication::class.java)
}
