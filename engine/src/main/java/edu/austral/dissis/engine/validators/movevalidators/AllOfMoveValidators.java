package edu.austral.dissis.engine.validators.movevalidators;

import edu.austral.dissis.engine.components.Board;
import edu.austral.dissis.engine.components.Coordinates;
import java.util.List;

public class AllOfMoveValidators implements MoveValidator {

  private final List<MoveValidator> moveValidators;

  public AllOfMoveValidators(List<MoveValidator> moveValidators) {
    this.moveValidators = moveValidators;
  }

  @Override
  public boolean validMove(Coordinates from, Coordinates to, Board board) {
    for (MoveValidator moveValidator : moveValidators) {
      if (!moveValidator.validMove(from, to, board)) {
        return false;
      }
    }
    return true;
  }
}
