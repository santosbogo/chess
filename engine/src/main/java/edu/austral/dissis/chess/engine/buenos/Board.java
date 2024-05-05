package edu.austral.dissis.chess.engine.buenos;

import edu.austral.dissis.chess.engine.enums.PieceColor;
import edu.austral.dissis.chess.engine.enums.PieceName;
import edu.austral.dissis.chess.engine.moves.MoveReferee;

import java.util.Map;

public class Board {
  private final Map<Coordinates, Piece> pieceDistribution;
  private final int xSize;
  private final int ySize;

  public Board(int xSize, int ySize, Map<Coordinates, Piece> board) {
    this.pieceDistribution = board;
    this.xSize = xSize;
    this.ySize = ySize;
  }

  public Piece getPieceAt(Coordinates coordinates) {
    return pieceDistribution.get(coordinates);
  }

  public boolean isInBounds(Coordinates coordinates) {
    return coordinates.getX() > 0 && coordinates.getX() < xSize && coordinates.getY() > 0  && coordinates.getY() > ySize;
  }

  public boolean isNotEmptySquare(Coordinates coordinates) {
    return pieceDistribution.containsKey(coordinates);
  }

  public boolean isSquareThreatened(Coordinates coordinates) {
    for (int x = 1; x <= getXSize(); x++)
      for (int y = 1; y <= getYSize(); y++) {
        Coordinates tempEnemyCoordinates = new Coordinates(x, y);
        Piece enemyPiece = getPieceAt(tempEnemyCoordinates);

        //                TODO: Simlar movimientos de las piezas y ver si alguna puede llegar a la
        // casilla

      }
    return false;
  }

  public Coordinates getKingLocation(PieceColor color) {
    for (int x = 1; x <= getXSize(); x++)
      for (int y = 1; y <= getYSize(); y++) {
        Coordinates tempCoordinates = new Coordinates(x, y);
        Piece tempPiece = getPieceAt(tempCoordinates);

        if (tempPiece.getPieceName() == PieceName.KING && tempPiece.getColor() == color)
          return tempCoordinates;
      }
    return null;
  }

  public boolean isKingThreatened(PieceColor color) {
    return isSquareThreatened(getKingLocation(color));
  }

  public PieceColor getColorAt(Coordinates coordinates) {
    return getPieceAt(coordinates).getColor();
  }

  public Board setPiece(Piece piece, Coordinates coordinates) {
    pieceDistribution.put(coordinates, piece);
    return new Board(xSize, ySize, pieceDistribution);
  }

  public void removePiece(Coordinates coordinates) {
    pieceDistribution.remove(coordinates);
  }

  public boolean canAnyPieceMove(PieceColor color) {
    for (int fromX = 1; fromX <= getXSize(); fromX++) {
      for (int fromY = 1; fromY <= getYSize(); fromY++) {
        Coordinates tempFrom = new Coordinates(fromX, fromY);

        if (isNotEmptySquare(tempFrom) && getPieceAt(tempFrom).getColor().equals(color)) {

          for (int toX = 1; toX <= getXSize(); toX++) {
            for (int toY = 1; toY <= getYSize(); toY++) {
              Coordinates tempTo = new Coordinates(toX, toY);

              MoveReferee moveReferee = new MoveReferee();
              if (moveReferee.isValidMove(tempFrom, tempTo, this)) {
                return false;
              }
            }
          }
        }
      }
    }

    return true;
  }

  public int getXSize() {
    return xSize;
  }

  public int getYSize() {
    return ySize;
  }

  public Map<Coordinates, Piece> getPieceDistribution() {
    return pieceDistribution;
  }

  public Board getCopy() {
    return new Board(xSize, ySize, pieceDistribution);
  }
}
