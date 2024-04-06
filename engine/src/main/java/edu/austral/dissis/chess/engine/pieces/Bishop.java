package edu.austral.dissis.chess.engine.pieces;

import edu.austral.dissis.chess.engine.boards.Board;
import edu.austral.dissis.chess.engine.coordinates.Coordinates;
import edu.austral.dissis.chess.engine.enums.PieceColor;
import edu.austral.dissis.chess.engine.moves.Move;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {
    private boolean dead = false;

    public Bishop(PieceColor pieceColor, Coordinates coordinates){
        super(pieceColor, coordinates);
    }

    @Override
    public boolean canDoThisMove(Board board, Move move) {
        Coordinates from = move.getFrom();
        Coordinates to = move.getTo();
        int xDistance = Math.abs(to.getX() - from.getX());
        int yDistance = Math.abs(to.getY() - from.getY());

        int verticalDirection = (to.getX() - from.getX()) > 0 ? 1 : -1;
        int horizontalDirection = (to.getY() - from.getY()) > 0 ? 1 : -1;

        if(!(xDistance == yDistance && xDistance != 0)) {
            return false;
        }

        for(int i = 1; i < xDistance; i++) {
            int x = from.getX() + i * verticalDirection;
            int y = from.getY() + i * horizontalDirection;
            if (!board.isEmptySquare(new Coordinates(x, y))) {
                return false;
            }
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
