package edu.austral.dissis.chess.validators.movevalidators.classic;

import edu.austral.dissis.engine.components.Board;
import edu.austral.dissis.engine.components.Coordinates;
import edu.austral.dissis.engine.validators.movevalidators.MoveValidator;

public class ClassicKnightMoveValidator implements MoveValidator {

  @Override
  public boolean validMove(Coordinates from, Coordinates to, Board board) {
    int x = Math.abs(to.getX() - from.getX());
    int y = Math.abs(to.getY() - from.getY());

    return x * y == 2;
  }
}
