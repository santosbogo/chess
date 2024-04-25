package edu.austral.dissis.chess.engine.validators.gameValidators;

import edu.austral.dissis.chess.engine.buenos.Board;
import edu.austral.dissis.chess.engine.buenos.Coordinates;
import edu.austral.dissis.chess.engine.buenos.Player;

public class CheckmateStatus implements GameStatus {
  @Override
  public boolean gameStatus(Player playerTurn, Board board) {
    Coordinates kingCoordinates = board.getKingLocation(playerTurn.getColor());

    if (isCheck(playerTurn, board, kingCoordinates))
      if (isCheckmate(playerTurn, board, kingCoordinates)) return true;

    return false;
  }

  private boolean isCheckmate(Player playerTurn, Board board, Coordinates kingCoordinates) {
    for (int fromX = 1; fromX <= board.getXSize(); fromX++) {
      for (int fromY = 1; fromY <= board.getYSize(); fromY++) {

        Coordinates tempFrom = new Coordinates(fromX, fromY);

        if (!board.isEmptySquare(tempFrom)
            && board.getPieceAt(tempFrom).getColor().equals(playerTurn.getColor())) {
          for (int toX = 1; toX <= board.getXSize(); toX++) {
            for (int toY = 1; toY <= board.getYSize(); toY++) {
              Coordinates tempTo = new Coordinates(toX, toY);

              // TODO: SIMULAR UN MOVIMIENTO PARA CADDA PIEZA Y VER SI CON ESE MOVIMIENTO SE SALE
              // DEL JAQUE
            }
          }
        }
      }
    }
    return true;
  }

  private boolean isCheck(Player playerTurn, Board board, Coordinates kingCoordinates) {
    return board.isSquareThreatened(kingCoordinates);
  }
}
