package edu.austral.dissis.chess.engine.buenos;

public class Coordinates {
    private final int x;
    private final int y;

    public Coordinates(char x, int y){
        this.x = x - 'A';
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates tile = (Coordinates) o;
        return this.x == tile.getX() && this.y == tile.getY();
    }

}
