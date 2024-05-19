package edu.austral.dissis.chess.engine.validators.moveValidators.classicChess;

import edu.austral.dissis.chess.engine.board.Board;
import edu.austral.dissis.chess.engine.board.Coordinates;
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
        board.getColorAt(from).equals(PieceColor.BLACK) ? from.getY() - 1 : from.getY() + 1;
    Coordinates enemyPosition = new Coordinates(to.getX(), enemyY);
    Piece movingPiece = board.getPieceAt(from);
    Piece enemyPiece = board.getPieceAt(enemyPosition);
    if (enemyPiece.getPieceName() != PieceName.PAWN
        || enemyPiece.getColor() == movingPiece.getColor()) {
      return false;
    }

    return wasLastMoveDoubleStepPawn(enemyPosition);
  }

  public boolean wasLastMoveDoubleStepPawn(Coordinates enemyPosition) {
    //        TODO: Implementar
    // Necesito obtener el Board anterior y ver si el ultimo movimiento fue un doble paso de peon
    return true;
  }
}
