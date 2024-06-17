package edu.austral.dissis.engine.components;

import edu.austral.dissis.engine.enums.PieceColor;
import java.util.Collections;
import java.util.Map;

public class Board {
  private final Map<Coordinates, Piece> pieceDistribution;
  private final int sizeX;
  private final int sizeY;

  public Board(int sizeX, int sizeY, Map<Coordinates, Piece> pieceDistribution) {
    this.pieceDistribution = Collections.unmodifiableMap(pieceDistribution);
    this.sizeX = sizeX;
    this.sizeY = sizeY;
  }

  public Piece getPieceAt(Coordinates coordinates) {
    return pieceDistribution.get(coordinates);
  }

  public boolean isInBounds(Coordinates coordinates) {
    return coordinates.getX() > 0
        && coordinates.getX() <= sizeX
        && coordinates.getY() > 0
        && coordinates.getY() <= sizeY;
  }

  public boolean isEmptySquare(Coordinates coordinates) {
    return !pieceDistribution.containsKey(coordinates);
  }

  public PieceColor getColorAt(Coordinates coordinates) {
    return getPieceAt(coordinates).getColor();
  }

  public int getSizeX() {
    return sizeX;
  }

  public int getSizeY() {
    return sizeY;
  }

  public Map<Coordinates, Piece> getPieceDistribution() {
    return pieceDistribution;
  }

  // Debugging purposes
  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    for (int y = 1; y <= getSizeY(); y++) {
      for (int x = 1; x <= getSizeX(); x++) {
        Coordinates coordinates = new Coordinates(x, getSizeY() - y + 1);
        stringBuilder.append("|");
        if (isEmptySquare(coordinates)) {
          stringBuilder.append("  ");
        } else {
          stringBuilder.append(getPieceColorInitial(coordinates));
          stringBuilder.append(getPieceNameInitial(coordinates));
        }
      }
      stringBuilder.append("|\n");
    }
    stringBuilder.append("\n\n");

    return stringBuilder.toString();
  }

  private String getPieceColorInitial(Coordinates coordinates) {
    if (getColorAt(coordinates).equals(PieceColor.WHITE)) {
      return "W";
    } else {
      return "B";
    }
  }

  private String getPieceNameInitial(Coordinates coordinates) {
    return getPieceAt(coordinates).getPieceName().toString().substring(0, 1);
  }
}
