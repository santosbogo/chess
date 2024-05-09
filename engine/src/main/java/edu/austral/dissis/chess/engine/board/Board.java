package edu.austral.dissis.chess.engine.board;

import edu.austral.dissis.chess.engine.buenos.Piece;
import edu.austral.dissis.chess.engine.enums.PieceColor;
import edu.austral.dissis.chess.engine.enums.PieceName;
import edu.austral.dissis.chess.engine.referee.MoveReferee;

import java.util.Map;
import java.util.NoSuchElementException;

public class Board {
  private final Map<Coordinates, Piece> pieceDistribution;
  private final int xSize;
  private final int ySize;

  public Board(int xSize, int ySize, Map<Coordinates, Piece> pieceDistribution) {
    this.pieceDistribution = pieceDistribution;
    this.xSize = xSize;
    this.ySize = ySize;
  }

  public Piece getPieceAt(Coordinates coordinates) {
    return pieceDistribution.get(coordinates);
  }

  public boolean isInBounds(Coordinates coordinates) {
    return coordinates.getX() > 0 && coordinates.getX() <= xSize && coordinates.getY() > 0  && coordinates.getY() <= ySize;
  }

  public boolean isEmptySquare(Coordinates coordinates) {
    return !pieceDistribution.containsKey(coordinates);
  }

  public boolean isSquareThreatened(Coordinates coordinates) {
    PieceColor targetColor = getColorAt(coordinates);
    for (int x = 1; x <= getXSize(); x++) {
      for (int y = 1; y <= getYSize(); y++) {
        Coordinates from = new Coordinates(x, y);
        if (!pieceDistribution.containsKey(from) || getColorAt(from).equals(targetColor)) continue;
        MoveReferee moveReferee = new MoveReferee(getColorAt(from), this);
        if (moveReferee.isValidMove(from, coordinates)) {
          return true;
        }
      }
    }
    return false;
  }

  public Coordinates getKingLocation(PieceColor color) {
    for (int x = 1; x <= getXSize(); x++)
      for (int y = 1; y <= getYSize(); y++) {
        Coordinates tempCoordinates = new Coordinates(x, y);
        Piece tempPiece = getPieceAt(tempCoordinates);

        if (tempPiece == null) continue;

        if (tempPiece.getPieceName().equals(PieceName.KING) && tempPiece.getColor().equals(color))
          return tempCoordinates;
      }

    throw new NoSuchElementException("King not found");
  }

  public boolean isKingThreatened(PieceColor color) {
    return isSquareThreatened(getKingLocation(color));
  }

  public PieceColor getColorAt(Coordinates coordinates) {
    return getPieceAt(coordinates).getColor();
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
}
