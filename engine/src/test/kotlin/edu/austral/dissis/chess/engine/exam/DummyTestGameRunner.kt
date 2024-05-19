package edu.austral.dissis.chess.engine.exam

import edu.austral.dissis.chess.engine.Game
import edu.austral.dissis.chess.engine.board.Board
import edu.austral.dissis.chess.engine.board.Coordinates
import edu.austral.dissis.chess.engine.enums.StatusOptions
import edu.austral.dissis.chess.engine.games.classicChess.ClassicChess
import edu.austral.dissis.chess.test.TestBoard
import edu.austral.dissis.chess.test.TestPosition
import edu.austral.dissis.chess.test.game.BlackCheckMate
import edu.austral.dissis.chess.test.game.TestGameRunner
import edu.austral.dissis.chess.test.game.TestMoveDraw
import edu.austral.dissis.chess.test.game.TestMoveFailure
import edu.austral.dissis.chess.test.game.TestMoveResult
import edu.austral.dissis.chess.test.game.TestMoveSuccess
import edu.austral.dissis.chess.test.game.WhiteCheckMate

class DummyTestGameRunner(private val game: Game) : TestGameRunner {
    override fun executeMove(
        from: TestPosition,
        to: TestPosition,
    ): TestMoveResult {
//        return TestMoveSuccess(this)//TODO

        val newFrom = Coordinates(from.col, from.row)
        val newTo = Coordinates(to.col, to.row)

        return when (game.playTurn(newFrom, newTo)) {
            StatusOptions.FAILURE -> {
                TestMoveFailure(Translator().translateBoard(game.peekBoard()))
            }
            StatusOptions.WHITE_CHECKMATE -> {
                WhiteCheckMate(Translator().translateBoard(game.peekBoard()))
            }
            StatusOptions.BLACK_CHECKMATE -> {
                BlackCheckMate(Translator().translateBoard(game.peekBoard()))
            }
            StatusOptions.STALEMATE -> {
                TestMoveDraw(Translator().translateBoard(game.peekBoard()))
            }
            else -> {
                TestMoveSuccess(this)
            }
        }
    }

    override fun getBoard(): TestBoard {
        return Translator().translateBoard(game.peekBoard())
    }

    override fun withBoard(board: TestBoard): TestGameRunner { // getGameRunner
        return DummyTestGameRunner(ClassicChess().generateGame(Translator().translateBoard(board)))
    }
}
