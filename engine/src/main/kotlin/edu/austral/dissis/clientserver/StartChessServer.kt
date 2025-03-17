package edu.austral.dissis.clientserver

import edu.austral.dissis.chess.games.classic.ClassicChess

fun main() {
    ServerEngine.initialize(ClassicChess().generateGame())
    ServerEngine.start()
}