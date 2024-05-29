package edu.austral.dissis.engine.games;

import edu.austral.dissis.engine.components.Piece;
import edu.austral.dissis.engine.enums.PieceColor;
import edu.austral.dissis.chess.enums.PieceName;
import edu.austral.dissis.engine.enums.PieceNameInterface;

public interface PieceFactory {

  public Piece generatePiece(PieceNameInterface pieceName, PieceColor pieceColor);
}
