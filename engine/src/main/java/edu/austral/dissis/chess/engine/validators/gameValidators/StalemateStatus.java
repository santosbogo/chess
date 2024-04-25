package edu.austral.dissis.chess.engine.validators.gameValidators;

import edu.austral.dissis.chess.engine.buenos.Board;
import edu.austral.dissis.chess.engine.buenos.Player;

public class StalemateStatus implements GameStatus {
  @Override
  public boolean gameStatus(Player playerTurn, Board board) {
    return false;
  }

  private boolean isStalemate(Player playerTurn, Board board) {
    //        TODO: PARA TODAS LAS PIEZAS DEL JUGADOR QUE TIENE EL TURNO, SI NO HAY MOVIMIENTOS
    // POSIBLES, ES STALEMATE
    return false;
  }
}
