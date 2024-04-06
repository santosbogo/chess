package edu.austral.dissis.chess.engine.pieces;

import edu.austral.dissis.chess.engine.boards.Board;
import edu.austral.dissis.chess.engine.coordinates.Coordinates;
import edu.austral.dissis.chess.engine.enums.PieceColor;
import edu.austral.dissis.chess.engine.moves.Move;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {
    private boolean dead = false;

    public Queen(PieceColor pieceColor, Coordinates coordinates){
        super(pieceColor, coordinates);
    }

    @Override
    public boolean canDoThisMove(Board board, Move move) {
        Coordinates from = move.getFrom();
        Coordinates to = move.getTo();
        int xDistance = Math.abs(to.getX() - from.getX());
        int yDistance = Math.abs(to.getY() - from.getY());

        int verticalDirection = (to.getY() - from.getY()) > 0 ? 1 : -1;
        int horizontalDirection = (to.getX() - from.getX()) > 0 ? 1 : -1;

        if (xDistance != 0 && yDistance == 0) {
            for(int x = 1; x < xDistance; x++) {
                if (!board.isEmptySquare(new Coordinates(from.getX() + x * horizontalDirection, from.getY()))) {
                    return false;
                }
            }
        }
        else if (xDistance == 0 && yDistance != 0) {
            for(int y = 1; y < yDistance; y++) {
                if (!board.isEmptySquare(new Coordinates(from.getX(), from.getY() + y * verticalDirection))) {
                    return false;
                }
            }
        }
        else if (xDistance == yDistance && xDistance != 0) {
            for(int i = 1; i < xDistance; ++i) {
                int x = from.getX() + i * horizontalDirection;
                int y = from.getY() + i * verticalDirection;
                if (!board.isEmptySquare(new Coordinates(x, y))) {
                    return false;
                }
            }
        }
        else {
            return false;
        }

        return true;
    }

    @Override
    public PieceColor getColor() {
        return pieceColor;
    }

    @Override
    public void kill() {
        dead = true;
    }

    @Override
    public boolean isDead() {
        return dead;
    }
}
