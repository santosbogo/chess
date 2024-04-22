package edu.austral.dissis.chess.engine.exam

import edu.austral.dissis.chess.test.game.GameTester
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import java.util.stream.Stream

class Exam {

    @TestFactory
    fun `required exam tests`(): Stream<DynamicTest> {

        return GameTester(DummyTestGameRunner()).test()
    }

}
