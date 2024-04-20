package edu.austral.dissis.chess.engine.generators;

import edu.austral.dissis.chess.engine.enums.PieceColor;
import edu.austral.dissis.chess.engine.enums.PieceName;
import edu.austral.dissis.chess.engine.coordinates.Piece;

public interface PieceGenerator {
        public Piece generatePiece(PieceName pieceName, PieceColor pieceColor);
}
