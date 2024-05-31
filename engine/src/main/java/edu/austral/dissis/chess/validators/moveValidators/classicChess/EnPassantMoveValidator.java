package edu.austral.dissis.chess.validators.moveValidators.classicChess;

import edu.austral.dissis.engine.components.Board;
import edu.austral.dissis.engine.components.Coordinates;
import edu.austral.dissis.engine.components.Piece;
import edu.austral.dissis.chess.enums.ChessPieceNames;
import edu.austral.dissis.engine.validators.moveValidators.MoveValidator;

public class EnPassantMoveValidator implements MoveValidator {

  @Override
  public boolean validMove(Coordinates from, Coordinates to, Board board) {
    if (Math.abs(to.getX() - from.getX()) != 1 || Math.abs(to.getY() - from.getY()) != 1) {
      return false;
    }

    Coordinates enemyPosition = new Coordinates(to.getX(), from.getY());

    Piece movingPiece = board.getPieceAt(from);
    Piece enemyPiece = board.getPieceAt(enemyPosition);

    if (enemyPiece == null
        || enemyPiece.getPieceName() != ChessPieceNames.PAWN
        || enemyPiece.getColor().equals(movingPiece.getColor())
        || !enemyPiece.isFirstMove()) {
      return false;
    }

    return true;
  }
}
