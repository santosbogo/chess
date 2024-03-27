package edu.austral.dissis.chess.engine.boards;

import edu.austral.dissis.chess.engine.pieces.Piece;

public class BoardSquare {
    private final Piece piece;

    public BoardSquare(Piece piece) {
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;
    }

    public boolean isEmpty() {
        return piece == null;
    }

    public BoardSquare setPiece(Piece piece) {
        return new BoardSquare(piece);
    }
}
