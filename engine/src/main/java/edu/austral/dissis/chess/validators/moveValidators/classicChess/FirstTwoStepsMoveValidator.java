package edu.austral.dissis.chess.validators.moveValidators.classicChess;

import edu.austral.dissis.engine.components.Board;
import edu.austral.dissis.engine.components.Coordinates;
import edu.austral.dissis.engine.components.Piece;
import edu.austral.dissis.engine.validators.moveValidators.MoveValidator;

public class FirstTwoStepsMoveValidator implements MoveValidator {

  @Override
  public boolean validMove(Coordinates from, Coordinates to, Board board) {
    Piece piece = board.getPieceAt(from);
    if(!board.isEmptySquare(to))
      return false;

    return piece.isFirstMove() && isTwoStepsMove(from, to);
  }

  private boolean isTwoStepsMove(Coordinates from, Coordinates to) {
    return Math.abs(to.getY() - from.getY()) == 2;
  }
}
