package edu.austral.dissis.chess.engine.predefined

import edu.austral.dissis.chess.test.TestBoard
import edu.austral.dissis.chess.test.TestPosition
import edu.austral.dissis.chess.test.Validity
import edu.austral.dissis.chess.test.move.TestMoveRunner

object DummyTestMoveRunner : TestMoveRunner {
    private val validMoves = listOf<TestPosition>(
        TestPosition(3, 3),
        TestPosition(3, 5),
        TestPosition(4, 2),
        TestPosition(4, 6),
        TestPosition(6, 2),
        TestPosition(6, 6),
        TestPosition(7, 3),
        TestPosition(7, 5),
    )

    override fun executeMove(testBoard: TestBoard, from: TestPosition, to: TestPosition): Validity {
        return if (to in validMoves) Validity.VALID else return Validity.INVALID
    }
}