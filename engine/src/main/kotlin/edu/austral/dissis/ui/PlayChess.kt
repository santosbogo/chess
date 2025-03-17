package edu.austral.dissis.ui

import edu.austral.dissis.chess.games.classic.ClassicChess
import edu.austral.dissis.engine.GameProvider
import javafx.application.Application

fun main() {
    GameProvider.game = ClassicChess().generateGame()
    Application.launch(GameApplication::class.java)
}
