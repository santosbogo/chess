package edu.austral.dissis.engine.validators.moveValidators.sharedMoveValidators;

import edu.austral.dissis.engine.components.Board;
import edu.austral.dissis.engine.components.Coordinates;
import edu.austral.dissis.engine.validators.moveValidators.MoveValidator;

public class DiagonalMoveValidator implements MoveValidator {
  private final int maxSquares;

  public DiagonalMoveValidator(int maxSquares) {
    this.maxSquares = maxSquares;
  }

  public DiagonalMoveValidator() {
    this.maxSquares = 2147483647;
  }

  @Override
  public boolean validMove(Coordinates from, Coordinates to, Board board) {
    return isDiagonalMove(from, to) && isInRange(from, to);
  }

  private boolean isDiagonalMove(Coordinates from, Coordinates to) {
    return Math.abs(from.getX() - to.getX()) == Math.abs(from.getY() - to.getY());
  }

  private boolean isInRange(Coordinates from, Coordinates to) {
    return Math.abs(to.getX() - from.getX()) <= maxSquares;
  }
}
