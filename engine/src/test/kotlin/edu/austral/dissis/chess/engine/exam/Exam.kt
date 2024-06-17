package edu.austral.dissis.chess.engine.exam

import edu.austral.dissis.chess.games.classic.ClassicChess
import edu.austral.dissis.chess.test.game.GameTester
import edu.austral.dissis.engine.exam.DummyTestGameRunner
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import java.util.stream.Stream

class Exam {
    @TestFactory
    fun `required exam tests`(): Stream<DynamicTest> {
        return GameTester(
            DummyTestGameRunner(
                ClassicChess().generateGame(),
            ),
        ).test()
    }
}
