package edu.austral.dissis.chess.engine.validators.moveValidators.classicChess;

import edu.austral.dissis.chess.engine.components.Board;
import edu.austral.dissis.chess.engine.components.Coordinates;
import edu.austral.dissis.chess.engine.components.Piece;
import edu.austral.dissis.chess.engine.validators.moveValidators.MoveValidator;

public class DiagonalOneStepCaptureMoveValidator implements MoveValidator {

  @Override
  public boolean validMove(Coordinates from, Coordinates to, Board board) {
    if (Math.abs(to.getX() - from.getX()) != 1 || Math.abs(to.getY() - from.getY()) != 1) {
      return false;
    }

    Piece enemyPiece = board.getPieceAt(to);
    Piece movingPiece = board.getPieceAt(from);

    if (enemyPiece == null
        || movingPiece == null
        || enemyPiece.getColor().equals(board.getPieceAt(from).getColor())) {
      return false;
    }

    return true;
  }
}
