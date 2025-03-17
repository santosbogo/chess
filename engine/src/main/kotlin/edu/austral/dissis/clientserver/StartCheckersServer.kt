package edu.austral.dissis.clientserver

import edu.austral.dissis.checkers.games.classic.ClassicCheckers

fun main() {
    ServerEngine.initialize(ClassicCheckers().generateGame())
    ServerEngine.start()
}
