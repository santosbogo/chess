package edu.austral.dissis.engine.validators.movevalidators.shared;

import edu.austral.dissis.engine.components.Board;
import edu.austral.dissis.engine.components.Coordinates;
import edu.austral.dissis.engine.validators.movevalidators.MoveValidator;

public class CantGoToAlliesSquareMoveValidator implements MoveValidator {

  @Override
  public boolean validMove(Coordinates from, Coordinates to, Board board) {
    if (board.isEmptySquare(to)) {
      return true;
    }

    return !board.getColorAt(from).equals(board.getColorAt(to));
  }
}
