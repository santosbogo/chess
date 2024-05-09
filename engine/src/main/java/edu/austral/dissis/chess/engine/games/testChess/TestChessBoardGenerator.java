package edu.austral.dissis.chess.engine.games.testChess;

import edu.austral.dissis.chess.engine.board.Board;
import edu.austral.dissis.chess.engine.board.Coordinates;
import edu.austral.dissis.chess.engine.buenos.Piece;
import edu.austral.dissis.chess.engine.enums.PieceColor;
import edu.austral.dissis.chess.engine.enums.PieceName;
import edu.austral.dissis.chess.engine.games.BoardGenerator;
import edu.austral.dissis.chess.engine.games.classicChess.ClassicChessPieceFactory;

import java.util.HashMap;
import java.util.Map;

public class TestChessBoardGenerator implements BoardGenerator {

    @Override
    public Board generateBoard() {
        return new Board(8, 8, generateStartingPosition());
    }

    private Map<Coordinates, Piece> generateStartingPosition() {
        ClassicChessPieceFactory pieceGenerator = new ClassicChessPieceFactory();

        Map<Coordinates, Piece> startingPosition = new HashMap<>();

        startingPosition.put(new Coordinates('A', 1), pieceGenerator.generatePiece(PieceName.KING, PieceColor.WHITE));
        startingPosition.put(new Coordinates('D', 1), pieceGenerator.generatePiece(PieceName.QUEEN, PieceColor.WHITE));

        return startingPosition;
    }
}
