package edu.austral.dissis.chess.engine.validators.gameValidators;

import edu.austral.dissis.chess.engine.buenos.Board;
import edu.austral.dissis.chess.engine.buenos.Player;

public interface GameStatus {

    boolean gameStatus(Player playerTurn, Board board);

}
