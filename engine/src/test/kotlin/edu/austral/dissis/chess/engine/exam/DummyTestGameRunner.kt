package edu.austral.dissis.chess.engine.exam

import edu.austral.dissis.chess.test.TestBoard
import edu.austral.dissis.chess.test.TestPosition
import edu.austral.dissis.chess.test.TestSize
import edu.austral.dissis.chess.test.game.TestGameRunner
import edu.austral.dissis.chess.test.game.TestMoveResult
import edu.austral.dissis.chess.test.game.TestMoveSuccess

class DummyTestGameRunner : TestGameRunner {
    override fun executeMove(from: TestPosition, to: TestPosition): TestMoveResult {
        return TestMoveSuccess(this)
    }

    override fun getBoard(): TestBoard {
        return TestBoard(TestSize(8, 8), emptyMap())
    }

    override fun withBoard(board: TestBoard): TestGameRunner {
        return this
    }

}
