package edu.austral.dissis.chess.engine.validators.moveValidators;

import edu.austral.dissis.chess.engine.buenos.Board;
import edu.austral.dissis.chess.engine.buenos.Coordinates;

public interface MoveValidator {

  public boolean validMove(Coordinates from, Coordinates to, Board board);
}
