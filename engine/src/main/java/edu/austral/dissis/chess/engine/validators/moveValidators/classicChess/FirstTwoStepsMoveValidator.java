package edu.austral.dissis.chess.engine.validators.moveValidators.classicChess;

import edu.austral.dissis.chess.engine.board.Board;
import edu.austral.dissis.chess.engine.board.Coordinates;
import edu.austral.dissis.chess.engine.buenos.Piece;
import edu.austral.dissis.chess.engine.validators.moveValidators.MoveValidator;

public class FirstTwoStepsMoveValidator implements MoveValidator {

  @Override
  public boolean validMove(Coordinates from, Coordinates to, Board board) {
    Piece piece = board.getPieceAt(from);

    return piece.isFirstMove() && isTwoStepsMove(from, to);
  }

  private boolean isTwoStepsMove(Coordinates from, Coordinates to) {
    return Math.abs(to.getY() - from.getY()) == 2;
  }
}
