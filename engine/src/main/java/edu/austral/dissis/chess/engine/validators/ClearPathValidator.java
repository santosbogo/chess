package edu.austral.dissis.chess.engine.validators;

import edu.austral.dissis.chess.engine.components.Board;
import edu.austral.dissis.chess.engine.coordinates.Coordinates;

public class ClearPathValidator implements MoveValidator{
    private final Board board;

    public ClearPathValidator(Board board) {
        this.board = board;
    }

    @Override
    public boolean validMove(Coordinates from, Coordinates to) {
        if (from.getX() == to.getX()) {
            return hasPieceBetweenVertical(from, to);
        } else if (from.getY() == to.getY()) {
            return hasPieceBetweenHorizontal(from, to);
        } else {
            return hasPieceBetweenDiagonal(from, to);
        }
    }

    private boolean hasPieceBetweenVertical(Coordinates from, Coordinates to){
        int direction = (to.getY() - from.getY()) > 0 ? 1 : -1;
        int distance = Math.abs(to.getY() - from.getY());

        for(int i = 1; i < distance; ++i) {
            if (!board.isEmptySquare(new Coordinates(from.getX(), from.getY() + i * direction))) {
                return false;
            }
        }
        return true;
    }

    private boolean hasPieceBetweenHorizontal(Coordinates from, Coordinates to){
        int direction = (to.getX() - from.getX()) > 0 ? 1 : -1;
        int distance = Math.abs(to.getX() - from.getX());

        for(int i = 1; i < distance; ++i) {
            if (!board.isEmptySquare(new Coordinates(from.getX() + i * direction, from.getY()))) {
                return false;
            }
        }
        return true;
    }

    private boolean hasPieceBetweenDiagonal(Coordinates from, Coordinates to){
        int verticalDirection = (to.getX() - from.getX()) > 0 ? 1 : -1;
        int horizontalDirection = (to.getY() - from.getY()) > 0 ? 1 : -1;
        int distance = Math.abs(to.getX() - from.getX());

        for(int i = 1; i < distance; i++) {
            int x = from.getX() + i * verticalDirection;
            int y = from.getY() + i * horizontalDirection;
            if (!board.isEmptySquare(new Coordinates(x, y))) {
                return false;
            }
        }
        return true;
    }
}
