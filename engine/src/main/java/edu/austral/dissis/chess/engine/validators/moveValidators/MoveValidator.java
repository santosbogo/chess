package edu.austral.dissis.chess.engine.validators.moveValidators;

import edu.austral.dissis.chess.engine.board.Board;
import edu.austral.dissis.chess.engine.board.Coordinates;

public interface MoveValidator {

  public boolean validMove(Coordinates from, Coordinates to, Board board);
}
