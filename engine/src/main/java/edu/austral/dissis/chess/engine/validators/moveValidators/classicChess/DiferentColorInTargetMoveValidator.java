package edu.austral.dissis.chess.engine.validators.moveValidators.classicChess;

import edu.austral.dissis.chess.engine.board.Board;
import edu.austral.dissis.chess.engine.board.Coordinates;
import edu.austral.dissis.chess.engine.validators.moveValidators.MoveValidator;

public class DiferentColorInTargetMoveValidator implements MoveValidator {

  @Override
  public boolean validMove(Coordinates from, Coordinates to, Board board) {
    if (board.isEmptySquare(to)) return true;

    return !board.getColorAt(from).equals(board.getColorAt(to));
  }
}
