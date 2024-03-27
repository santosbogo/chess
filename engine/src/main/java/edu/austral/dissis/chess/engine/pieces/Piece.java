package edu.austral.dissis.chess.engine.pieces;

public class Piece {
    private final Pieces piece;
    private final boolean isWhite;

    public Piece(Pieces piece, boolean isWhite) {
        this.piece = piece;
        this.isWhite = isWhite;
    }

    public Pieces getPiece() {
        return piece;
    }

    public boolean isWhite() {
        return isWhite;
    }
}
