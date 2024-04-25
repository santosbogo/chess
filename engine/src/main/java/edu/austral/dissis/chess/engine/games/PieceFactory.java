package edu.austral.dissis.chess.engine.games;

import edu.austral.dissis.chess.engine.buenos.Piece;
import edu.austral.dissis.chess.engine.enums.PieceColor;
import edu.austral.dissis.chess.engine.enums.PieceName;

public interface PieceFactory {

  public Piece generatePiece(PieceName pieceName, PieceColor pieceColor);
}
