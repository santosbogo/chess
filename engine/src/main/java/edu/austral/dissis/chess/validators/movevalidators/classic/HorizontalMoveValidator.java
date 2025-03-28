package edu.austral.dissis.chess.validators.movevalidators.classic;

import edu.austral.dissis.engine.components.Board;
import edu.austral.dissis.engine.components.Coordinates;
import edu.austral.dissis.engine.validators.movevalidators.MoveValidator;

public class HorizontalMoveValidator implements MoveValidator {
  private final int maxSquares;

  public HorizontalMoveValidator(int maxSquares) {
    this.maxSquares = maxSquares;
  }

  public HorizontalMoveValidator() {
    this.maxSquares = 2147483647;
  }

  @Override
  public boolean validMove(Coordinates from, Coordinates to, Board board) {
    return isHorizontalMove(from, to) && isInRange(from, to);
  }

  private boolean isHorizontalMove(Coordinates from, Coordinates to) {
    return from.getY() == to.getY();
  }

  private boolean isInRange(Coordinates from, Coordinates to) {
    return Math.abs(to.getX() - from.getX()) <= maxSquares;
  }
}
