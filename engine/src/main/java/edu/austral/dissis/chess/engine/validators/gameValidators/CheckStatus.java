package edu.austral.dissis.chess.engine.validators.gameValidators;

import edu.austral.dissis.chess.engine.buenos.Board;
import edu.austral.dissis.chess.engine.buenos.Coordinates;
import edu.austral.dissis.chess.engine.buenos.Player;

public class CheckStatus implements GameStatus {

  @Override
  public boolean gameStatus(Player playerTurn, Board board) {
    return isCheck(playerTurn, board);
  }

  private boolean isCheck(Player playerTurn, Board board) {
    Coordinates kingCoordinates = board.getKingLocation(playerTurn.getColor());
    return board.isSquareThreatened(kingCoordinates);
  }
}
