package edu.austral.dissis.chess.engine.generator;

import edu.austral.dissis.chess.engine.enums.PieceColor;
import edu.austral.dissis.chess.engine.enums.PieceName;
import edu.austral.dissis.chess.engine.pieces.Piece;

public interface PieceGenerator {
        public Piece generatePiece(PieceName pieceName, PieceColor pieceColor);
}
