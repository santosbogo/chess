package edu.austral.dissis.chess.engine.player;

import edu.austral.dissis.chess.engine.enums.PieceColor;

public class Player {
    private final PieceColor pieceColor;

    public Player(PieceColor pieceColor) {
        this.pieceColor = pieceColor;
    }

    public PieceColor getColor() {
        return pieceColor;
    }
}
