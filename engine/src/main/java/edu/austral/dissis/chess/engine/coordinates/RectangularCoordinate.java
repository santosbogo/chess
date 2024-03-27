package edu.austral.dissis.chess.engine.coordinates;

public class RectangularCoordinate implements Coordinate {
    private final int x;
    private final int y;

    public RectangularCoordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

}
