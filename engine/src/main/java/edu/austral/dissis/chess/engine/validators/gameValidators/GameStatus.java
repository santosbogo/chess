package edu.austral.dissis.chess.engine.validators.gameValidators;

import edu.austral.dissis.chess.engine.components.Board;
import edu.austral.dissis.chess.engine.coordinates.Player;

public interface GameStatus {

    boolean gameStatus(Player playerTurn, Board board);

}
