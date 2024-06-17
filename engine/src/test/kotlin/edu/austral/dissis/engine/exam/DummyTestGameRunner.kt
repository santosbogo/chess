package edu.austral.dissis.engine.exam

import edu.austral.dissis.chess.games.classic.ClassicChess
import edu.austral.dissis.chess.test.TestBoard
import edu.austral.dissis.chess.test.TestPosition
import edu.austral.dissis.chess.test.game.BlackCheckMate
import edu.austral.dissis.chess.test.game.TestGameRunner
import edu.austral.dissis.chess.test.game.TestMoveDraw
import edu.austral.dissis.chess.test.game.TestMoveFailure
import edu.austral.dissis.chess.test.game.TestMoveResult
import edu.austral.dissis.chess.test.game.TestMoveSuccess
import edu.austral.dissis.chess.test.game.WhiteCheckMate
import edu.austral.dissis.engine.components.Coordinates
import edu.austral.dissis.engine.components.Game
import edu.austral.dissis.engine.enums.StatusOptions

class DummyTestGameRunner(private var game: Game) : TestGameRunner {
    override fun executeMove(
        from: TestPosition,
        to: TestPosition,
    ): TestMoveResult {
        val newFrom = Coordinates(from.col, from.row)
        val newTo = Coordinates(to.col, to.row)

        game = game.playTurn(newFrom, newTo)

        return when (game.status) {
            StatusOptions.FAILURE -> {
                TestMoveFailure(Translator().translateBoard(game.getBoard()))
            }
            StatusOptions.WHITE_WIN -> {
                WhiteCheckMate(Translator().translateBoard(game.getBoard()))
            }
            StatusOptions.BLACK_WIN -> {
                BlackCheckMate(Translator().translateBoard(game.getBoard()))
            }
            StatusOptions.STALEMATE -> {
                TestMoveDraw(Translator().translateBoard(game.getBoard()))
            }
            else -> {
                TestMoveSuccess(this)
            }
        }
    }

    override fun getBoard(): TestBoard {
        return Translator().translateBoard(game.getBoard())
    }

    override fun withBoard(board: TestBoard): TestGameRunner { // getGameRunner
        return DummyTestGameRunner(ClassicChess().generateGame(Translator().translateBoard(board)))
    }

    override fun undo(): TestMoveResult {
        game = game.undo()
        return TestMoveSuccess(this)
    }

    override fun redo(): TestMoveResult {
        game = game.redo()
        return TestMoveSuccess(this)
    }
}
