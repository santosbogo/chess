package edu.austral.dissis.engine.validators.moveValidators;

import edu.austral.dissis.engine.components.Board;
import edu.austral.dissis.engine.components.Coordinates;

public interface MoveValidator {

  public boolean validMove(Coordinates from, Coordinates to, Board board);
}
