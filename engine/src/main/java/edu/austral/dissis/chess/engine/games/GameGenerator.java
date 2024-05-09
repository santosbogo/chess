package edu.austral.dissis.chess.engine.games;

import edu.austral.dissis.chess.engine.Game;
import edu.austral.dissis.chess.engine.board.Board;
import edu.austral.dissis.chess.engine.validators.endOfGameValidators.EndOfGameValidator;

import java.util.List;

public interface GameGenerator {

  public Game generateGame();
}
