package edu.austral.dissis.chess.engine.validators.moveValidators;

import edu.austral.dissis.chess.engine.components.Board;
import edu.austral.dissis.chess.engine.components.Coordinates;

public interface MoveValidator {

  public boolean validMove(Coordinates from, Coordinates to, Board board);
}
