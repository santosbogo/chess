package edu.austral.dissis.engine.exam

import edu.austral.dissis.engine.components.Board
import edu.austral.dissis.engine.components.Coordinates
import edu.austral.dissis.engine.components.Piece
import edu.austral.dissis.engine.enums.PieceColor
import edu.austral.dissis.chess.enums.ChessPieceNames
import edu.austral.dissis.chess.games.classicChess.ClassicChessPieceFactory
import edu.austral.dissis.chess.test.TestBoard
import edu.austral.dissis.chess.test.TestPiece
import edu.austral.dissis.chess.test.TestPosition
import edu.austral.dissis.chess.test.TestSize

class Translator {
    // Translate my board to theirs board
    fun translateBoard(board: Board): TestBoard {
        val newPieceDistribution = HashMap<TestPosition, TestPiece>()

        for ((coordinate, piece) in board.pieceDistribution) {
            val testCoordinates = TestPosition(coordinate.y, coordinate.x)
            val testPiece = translatePiece(piece)
            newPieceDistribution[testCoordinates] = testPiece
        }

        return TestBoard(TestSize(board.ySize, board.xSize), newPieceDistribution)
    }

    // Translate my piece to theirs piece
    private fun translatePiece(testPiece: Piece): TestPiece {
        val playerColorSymbol = if (testPiece.color.equals(PieceColor.WHITE)) 'W' else 'B'
        val pieceTypeSymbol =
            when (testPiece.pieceName) {
                ChessPieceNames.PAWN -> 'P'
                ChessPieceNames.ROOK -> 'R'
                ChessPieceNames.KNIGHT -> 'N'
                ChessPieceNames.BISHOP -> 'B'
                ChessPieceNames.QUEEN -> 'Q'
                ChessPieceNames.KING -> 'K'
                else -> {
                    throw IllegalArgumentException("Invalid piece type: ${testPiece.pieceName}")
                }
            }
        return TestPiece(pieceTypeSymbol, playerColorSymbol)
    }

    // Translate theirs board to my board
    fun translateBoard(testBoard: TestBoard): Board {
        val newPieceDistribution = HashMap<Coordinates, Piece>()

        for ((testPosition, testPiece) in testBoard.pieces) {
            val coordinates = Coordinates(
                testPosition.col,
                testPosition.row
            )
            val piece = translatePiece(testPiece)
            newPieceDistribution[coordinates] = piece
        }

        return Board(
            testBoard.size.cols,
            testBoard.size.rows,
            newPieceDistribution
        )
    }

    // Translate theirs piece to my piece
    private fun translatePiece(testPiece: TestPiece): Piece {
        val color = translateColor(testPiece.playerColorSymbol)
        val type = translatePieceName(testPiece.pieceTypeSymbol)

        return ClassicChessPieceFactory()
            .generatePiece(type, color)
    }

    // Translate theirs piece name to my piece name
    private fun translatePieceName(pieceTypeSymbol: Char): ChessPieceNames {
        return when (pieceTypeSymbol) {
            'P' -> ChessPieceNames.PAWN
            'R' -> ChessPieceNames.ROOK
            'N' -> ChessPieceNames.KNIGHT
            'B' -> ChessPieceNames.BISHOP
            'Q' -> ChessPieceNames.QUEEN
            'K' -> ChessPieceNames.KING
            else -> throw IllegalArgumentException("Invalid piece type: $pieceTypeSymbol")
        }
    }

    // Translate theirs piece color to my piece color
    private fun translateColor(playerColorSymbol: Char): PieceColor {
        return when (playerColorSymbol) {
            'W' -> PieceColor.WHITE
            'B' -> PieceColor.BLACK
            else -> throw IllegalArgumentException("Invalid color symbol: $playerColorSymbol")
        }
    }
}
