package edu.austral.dissis.chess.engine.games.testChess;

import edu.austral.dissis.chess.engine.buenos.Piece;
import edu.austral.dissis.chess.engine.enums.PieceColor;
import edu.austral.dissis.chess.engine.enums.PieceName;
import edu.austral.dissis.chess.engine.games.PieceFactory;
import edu.austral.dissis.chess.engine.validators.moveValidators.classicChess.*;

public class TestChessPieceFactory implements PieceFactory {
    @Override
    public Piece generatePiece(PieceName pieceName, PieceColor pieceColor) {
        return switch (pieceName) {
            case PAWN -> generatePawn(pieceColor);
            case ROOK -> null;
            case KNIGHT -> null;
            case BISHOP -> null;
            case QUEEN -> generateQueen(pieceColor);
            case KING -> generateKing(pieceColor);
        };
    }

    private Piece generatePawn(PieceColor pieceColor) {
        Piece piece = new Piece(PieceName.PAWN, pieceColor);


        return piece;
    }

    private Piece generateKing(PieceColor pieceColor) {
        Piece piece = new Piece(PieceName.KING, pieceColor);

        return piece;
    }

    private Piece generateQueen(PieceColor pieceColor) {
        Piece piece = new Piece(PieceName.QUEEN, pieceColor);

        return piece;
    }


}
