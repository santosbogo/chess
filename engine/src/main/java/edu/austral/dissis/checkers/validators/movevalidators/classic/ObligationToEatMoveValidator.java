package edu.austral.dissis.checkers.validators.movevalidators.classic;

import edu.austral.dissis.checkers.CheckersHelper;
import edu.austral.dissis.engine.components.Board;
import edu.austral.dissis.engine.components.Coordinates;
import edu.austral.dissis.engine.enums.PieceColor;
import edu.austral.dissis.engine.validators.movevalidators.MoveValidator;

public class ObligationToEatMoveValidator implements MoveValidator {
  @Override
  public boolean validMove(Coordinates from, Coordinates to, Board board) {
    if (!isEatingMove(from, to, board)) {
      return !canAnyTeamPieceEat(board, board.getColorAt(from));
    }
    return true;
  }

  private boolean isEatingMove(Coordinates from, Coordinates to, Board board) {
    return new CheckersHelper().isOnlyOneEnemyPieceBetween(from, to, board);
  }

  private boolean canAnyTeamPieceEat(Board board, PieceColor color) {
    for (int x = 1; x <= board.getSizeX(); x++) {
      for (int y = 1; y <= board.getSizeY(); y++) {
        Coordinates from = new Coordinates(x, y);
        if (board.isEmptySquare(from)
            || !board.getColorAt(from).equals(color)
            || !new CheckersHelper().canPieceFromCoordinateEat(from, board)) {
          continue;
        }
        return true;
      }
    }
    return false;
  }
}
