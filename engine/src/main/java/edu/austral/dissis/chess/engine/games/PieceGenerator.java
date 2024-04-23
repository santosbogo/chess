package edu.austral.dissis.chess.engine.games;

import edu.austral.dissis.chess.engine.enums.PieceColor;
import edu.austral.dissis.chess.engine.enums.PieceName;
import edu.austral.dissis.chess.engine.buenos.Piece;

public interface PieceGenerator {
        public Piece generatePiece(PieceName pieceName, PieceColor pieceColor);
}
