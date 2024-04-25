package edu.austral.dissis.chess.engine.validators.moveValidators.classicChess;

import edu.austral.dissis.chess.engine.buenos.Board;
import edu.austral.dissis.chess.engine.buenos.Coordinates;
import edu.austral.dissis.chess.engine.buenos.Piece;
import edu.austral.dissis.chess.engine.enums.PieceColor;
import edu.austral.dissis.chess.engine.enums.PieceName;
import edu.austral.dissis.chess.engine.validators.moveValidators.MoveValidator;

public class EnPassantMoveValidator implements MoveValidator {
  @Override
  public boolean validMove(Coordinates from, Coordinates to, Board board) {
    if (Math.abs(to.getX() - from.getX()) != 1 || Math.abs(to.getY() - from.getY()) != 1) {
      return false;
    }

    int enemyY =
        board.getPieceAt(from).getColor() == PieceColor.WHITE ? from.getY() - 1 : from.getY() + 1;
    Coordinates enemyPosition = new Coordinates(to.getX(), enemyY);
    Piece movingPiece = board.getPieceAt(from);
    Piece enemyPiece = board.getPieceAt(enemyPosition);
    if (enemyPiece.getPieceName() != PieceName.PAWN
        || enemyPiece.getColor() == movingPiece.getColor()) {
      return false;
    }

    return board.wasLastMoveDoubleStepPawn(enemyPosition);
  }
}
