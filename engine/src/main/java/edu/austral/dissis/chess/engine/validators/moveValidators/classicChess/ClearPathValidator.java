package edu.austral.dissis.chess.engine.validators.moveValidators.classicChess;

import edu.austral.dissis.chess.engine.buenos.Board;
import edu.austral.dissis.chess.engine.buenos.Coordinates;
import edu.austral.dissis.chess.engine.validators.moveValidators.MoveValidator;

public class ClearPathValidator implements MoveValidator {

  @Override
  public boolean validMove(Coordinates from, Coordinates to, Board board) {
    if (from.getX() == to.getX()) {
      return hasPieceBetweenVertical(from, to, board);
    } else if (from.getY() == to.getY()) {
      return hasPieceBetweenHorizontal(from, to, board);
    } else {
      return hasPieceBetweenDiagonal(from, to, board);
    }
  }

  private boolean hasPieceBetweenVertical(Coordinates from, Coordinates to, Board board) {
    int direction = (to.getY() - from.getY()) > 0 ? 1 : -1;
    int distance = Math.abs(to.getY() - from.getY());

    for (int i = 1; i < distance; ++i) {
      if (!board.isEmptySquare(new Coordinates(from.getX(), from.getY() + i * direction))) {
        return false;
      }
    }
    return true;
  }

  private boolean hasPieceBetweenHorizontal(Coordinates from, Coordinates to, Board board) {
    int direction = (to.getX() - from.getX()) > 0 ? 1 : -1;
    int distance = Math.abs(to.getX() - from.getX());

    for (int i = 1; i < distance; ++i) {
      if (!board.isEmptySquare(new Coordinates(from.getX() + i * direction, from.getY()))) {
        return false;
      }
    }
    return true;
  }

  private boolean hasPieceBetweenDiagonal(Coordinates from, Coordinates to, Board board) {
    int verticalDirection = (to.getX() - from.getX()) > 0 ? 1 : -1;
    int horizontalDirection = (to.getY() - from.getY()) > 0 ? 1 : -1;
    int distance = Math.abs(to.getX() - from.getX());

    for (int i = 1; i < distance; i++) {
      int x = from.getX() + i * verticalDirection;
      int y = from.getY() + i * horizontalDirection;
      if (!board.isEmptySquare(new Coordinates(x, y))) {
        return false;
      }
    }
    return true;
  }
}
