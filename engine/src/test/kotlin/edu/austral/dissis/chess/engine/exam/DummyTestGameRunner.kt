package edu.austral.dissis.chess.engine.exam

import edu.austral.dissis.chess.engine.Game
import edu.austral.dissis.chess.test.TestBoard
import edu.austral.dissis.chess.test.TestPosition
import edu.austral.dissis.chess.test.TestSize
import edu.austral.dissis.chess.test.game.TestGameRunner
import edu.austral.dissis.chess.test.game.TestMoveResult
import edu.austral.dissis.chess.test.game.TestMoveSuccess

class DummyTestGameRunner(val game: Game) : TestGameRunner {

    override fun executeMove(from: TestPosition, to: TestPosition): TestMoveResult {
        return TestMoveSuccess(this)//TODO
    }

    override fun getBoard(): TestBoard {
        return Translator().translateBoard(game.peekBoard())
    }

    override fun withBoard(board: TestBoard): TestGameRunner { //getGameRunner
        return this // De su board a mi board
    }
}
