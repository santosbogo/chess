package edu.austral.dissis.engine.components;

import edu.austral.dissis.engine.enums.PieceColor;

import java.util.Collections;
import java.util.Map;

public class Board {
  private final Map<Coordinates, Piece> pieceDistribution;
  private final int xSize;
  private final int ySize;

  public Board(int xSize, int ySize, Map<Coordinates, Piece> pieceDistribution) {
    this.pieceDistribution = Collections.unmodifiableMap(pieceDistribution);
    this.xSize = xSize;
    this.ySize = ySize;
  }

  public Piece getPieceAt(Coordinates coordinates) {
    return pieceDistribution.get(coordinates);
  }

  public boolean isInBounds(Coordinates coordinates) {
    return coordinates.getX() > 0
        && coordinates.getX() <= xSize
        && coordinates.getY() > 0
        && coordinates.getY() <= ySize;
  }

  public boolean isEmptySquare(Coordinates coordinates) {
    return !pieceDistribution.containsKey(coordinates);
  }

  public PieceColor getColorAt(Coordinates coordinates) {
    if (getPieceAt(coordinates) == null)
      throw new IllegalArgumentException("No piece at coordinates");
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

  //Debugging purposes
  @Override
  public String toString(){
    StringBuilder stringBuilder = new StringBuilder();
    for (int y = 1; y <= getYSize(); y++){
      for (int x = 1; x <= getXSize(); x++){
        Coordinates coordinates = new Coordinates(x, getYSize()-y+1);
       stringBuilder.append("|");
        if(isEmptySquare(coordinates)){
          stringBuilder.append("  ");
        }
        else {
          stringBuilder.append(getPieceColorInitial(coordinates));
          stringBuilder.append(getPieceNameInitial(coordinates));
        }
      }
      stringBuilder.append("|\n");
    }
    stringBuilder.append("\n\n");

    return stringBuilder.toString();
  }

  private String getPieceColorInitial(Coordinates coordinates){
    if(getColorAt(coordinates).equals(PieceColor.WHITE)){
      return "W";
    }
    else{
      return "B";
    }
  }

  private String getPieceNameInitial(Coordinates coordinates){
    return getPieceAt(coordinates).getPieceName().toString().substring(0, 1);
  }
}
