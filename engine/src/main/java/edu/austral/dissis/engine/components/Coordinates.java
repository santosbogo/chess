package edu.austral.dissis.engine.components;

import java.util.Objects;

public class Coordinates {
  private final int coordinateX;
  private final int coordinateY;

  public Coordinates(char x, int y) {
    this.coordinateX = x - 64;
    this.coordinateY = y;
  }

  public Coordinates(int x, int y) {
    this.coordinateX = x;
    this.coordinateY = y;
  }

  public int getX() {
    return coordinateX;
  }

  public int getY() {
    return coordinateY;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Coordinates that = (Coordinates) obj;
    return coordinateX == that.coordinateX && coordinateY == that.coordinateY;
  }

  @Override
  public int hashCode() {
    return Objects.hash(coordinateX, coordinateY);
  }
}
