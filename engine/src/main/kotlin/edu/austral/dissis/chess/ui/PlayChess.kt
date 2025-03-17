package edu.austral.dissis.chess.ui

import edu.austral.dissis.chess.games.classic.ClassicChess
import javafx.application.Application

fun main() {
    GameProvider.game = ClassicChess().generateGame()
    Application.launch(GameApplication::class.java)
}
