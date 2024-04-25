package edu.austral.dissis.chess.engine.validators.endGameValidators;

import edu.austral.dissis.chess.engine.buenos.Board;
import edu.austral.dissis.chess.engine.buenos.Player;

public interface endGameValidator {
  boolean isEndGame(Player playerTurn, Board board);
}
