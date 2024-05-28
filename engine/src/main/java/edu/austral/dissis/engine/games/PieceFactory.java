package edu.austral.dissis.engine.games;

import edu.austral.dissis.engine.components.Piece;
import edu.austral.dissis.engine.enums.PieceColor;
import edu.austral.dissis.engine.enums.PieceName;

public interface PieceFactory {

  public Piece generatePiece(PieceName pieceName, PieceColor pieceColor);
}
