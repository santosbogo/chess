package edu.austral.dissis.chess.engine.validators.moveValidators.classicChess;

import edu.austral.dissis.chess.engine.components.Board;
import edu.austral.dissis.chess.engine.components.Coordinates;
import edu.austral.dissis.chess.engine.validators.moveValidators.MoveValidator;

public class ClassicKnightMoveValidator implements MoveValidator {

  @Override
  public boolean validMove(Coordinates from, Coordinates to, Board board) {
    int xDistance = Math.abs(to.getX() - from.getX());
    int yDistance = Math.abs(to.getY() - from.getY());

    return xDistance * yDistance == 2;
  }
}
