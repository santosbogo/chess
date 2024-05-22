package edu.austral.dissis.chess.engine.components;

import java.util.Objects;

public class Coordinates {
  private final int x;
  private final int y;

  public Coordinates(char x, int y) {
    this.x = x - 64;
    this.y = y;
  }

  public Coordinates(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Coordinates that = (Coordinates) obj;
    return x == that.x && y == that.y;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }
}
