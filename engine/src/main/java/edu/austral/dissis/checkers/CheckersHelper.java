package edu.austral.dissis.checkers;

import edu.austral.dissis.engine.components.Board;
import edu.austral.dissis.engine.components.Coordinates;
import edu.austral.dissis.engine.components.Piece;
import edu.austral.dissis.engine.enums.PieceColor;
import edu.austral.dissis.engine.referees.MoveReferee;

public class CheckersHelper {

  public Coordinates getOnlyEnemyPieceBetween(Coordinates from, Coordinates to, Board board) {
    int piecesBetween = 0;
    PieceColor allyColor = board.getColorAt(from);
    Coordinates enemyCoordinates = null;
    int verticalDirection = (to.getX() - from.getX()) > 0 ? 1 : -1;
    int horizontalDirection = (to.getY() - from.getY()) > 0 ? 1 : -1;
    int distance = Math.abs(to.getX() - from.getX());

    for (int i = 1; i < distance; i++) {
      int x = from.getX() + i * verticalDirection;
      int y = from.getY() + i * horizontalDirection;
      Coordinates tempEnemyCoordinates = new Coordinates(x, y);
      if (!board.isEmptySquare(tempEnemyCoordinates)
          && !board.getColorAt(tempEnemyCoordinates).equals(allyColor)) {
        piecesBetween++;
        enemyCoordinates = tempEnemyCoordinates;
      }
    }

    if (piecesBetween == 1) {
      return enemyCoordinates;
    }
    return null;
  }

  public boolean isOnlyOneEnemyPieceBetween(Coordinates from, Coordinates to, Board board) {
    return getOnlyEnemyPieceBetween(from, to, board) != null;
  }

  public boolean canPieceFromCoordinateEat(Coordinates from, Board board) {
    for (int x = 1; x <= board.getSizeX(); x++) {
      for (int y = 1; y <= board.getSizeY(); y++) {
        Coordinates to = new Coordinates(x, y);
        if (!isOnlyOneEnemyPieceBetween(from, to, board)) {
          continue;
        }
        MoveReferee moveReferee = new MoveReferee(board.getColorAt(from), board);
        if (moveReferee.isValidMove(from, to)) {
          return true;
        }
      }
    }
    return false;
  }

  public boolean colorDoesntHaveValidMoves(Board board, PieceColor colorTurn) {
    for (int fromX = 1; fromX <= board.getSizeX(); fromX++) {
      for (int fromY = 1; fromY <= board.getSizeY(); fromY++) {
        Coordinates tempFrom = new Coordinates(fromX, fromY);
        Piece fromPiece = board.getPieceAt(tempFrom);

        if (fromPiece == null || !board.getColorAt(tempFrom).equals(colorTurn)) {
          continue;
        }

        for (int toX = 1; toX <= board.getSizeX(); toX++) {
          for (int toY = 1; toY <= board.getSizeY(); toY++) {
            Coordinates tempTo = new Coordinates(toX, toY);

            if (!board.isEmptySquare(tempTo)) {
              continue;
            }

            if (new MoveReferee(colorTurn, board).isValidMove(tempFrom, tempTo)) {
              return false;
            }
          }
        }
      }
    }

    return true;
  }
}
