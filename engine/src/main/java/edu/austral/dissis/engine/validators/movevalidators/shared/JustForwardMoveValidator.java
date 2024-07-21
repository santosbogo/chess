package edu.austral.dissis.engine.validators.movevalidators.shared;

import edu.austral.dissis.engine.components.Board;
import edu.austral.dissis.engine.components.Coordinates;
import edu.austral.dissis.engine.enums.PieceColor;
import edu.austral.dissis.engine.validators.movevalidators.MoveValidator;

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
