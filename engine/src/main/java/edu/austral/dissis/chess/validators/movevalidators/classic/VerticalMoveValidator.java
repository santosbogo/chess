package edu.austral.dissis.chess.validators.movevalidators.classic;

import edu.austral.dissis.engine.components.Board;
import edu.austral.dissis.engine.components.Coordinates;
import edu.austral.dissis.engine.validators.movevalidators.MoveValidator;

public class VerticalMoveValidator implements MoveValidator {
  private final int maxSquares;
  private final boolean justOnFirstMove;

  public VerticalMoveValidator(int maxSquares) {
    this.maxSquares = maxSquares;
    this.justOnFirstMove = false;
  }

  public VerticalMoveValidator(int maxSquares, boolean justOnFirstMove) {
    this.maxSquares = maxSquares;
    this.justOnFirstMove = justOnFirstMove;
  }

  public VerticalMoveValidator() {
    this.maxSquares = 2147483647;
    this.justOnFirstMove = false;
  }

  @Override
  public boolean validMove(Coordinates from, Coordinates to, Board board) {
    boolean isFirstMove = true;
    if (justOnFirstMove) {
      isFirstMove = board.getPieceAt(from).isFirstMove();
    }
    return isVerticalMove(from, to) && isInRange(from, to) && isFirstMove;
  }

  private boolean isVerticalMove(Coordinates from, Coordinates to) {
    return from.getX() == to.getX();
  }

  private boolean isInRange(Coordinates from, Coordinates to) {
    return Math.abs(to.getY() - from.getY()) <= maxSquares;
  }
}
