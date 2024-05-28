package edu.austral.dissis.chess.validators.moveValidators.classicChess;

import edu.austral.dissis.engine.components.Board;
import edu.austral.dissis.engine.components.Coordinates;
import edu.austral.dissis.engine.enums.PieceColor;
import edu.austral.dissis.engine.validators.moveValidators.MoveValidator;

public class JustForwardMoveValidator implements MoveValidator {
  PieceColor pieceColor;

  public JustForwardMoveValidator(PieceColor pieceColor) {
    this.pieceColor = pieceColor;
  }

  @Override
  public boolean validMove(Coordinates from, Coordinates to, Board board) {
    if (pieceColor == PieceColor.WHITE) {
      return from.getY() < to.getY();
    } else {
      return from.getY() > to.getY();
    }
  }
}
