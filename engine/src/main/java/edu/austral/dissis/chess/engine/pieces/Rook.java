package edu.austral.dissis.chess.engine.pieces;

import edu.austral.dissis.chess.engine.boards.Board;
import edu.austral.dissis.chess.engine.coordinates.Coordinates;
import edu.austral.dissis.chess.engine.enums.PieceColor;
import edu.austral.dissis.chess.engine.moves.Move;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {
    private boolean dead = false;

    public Rook(PieceColor pieceColor, Coordinates coordinates){
        super(pieceColor, coordinates);
    }

    @Override
    public boolean canDoThisMove(Board board, Move move) {
        Coordinates from = move.getFrom();
        Coordinates to = move.getTo();
        int xDistance = Math.abs(to.getX() - from.getX());
        int yDistance = Math.abs(to.getY() - from.getY());

        int direction;

        if (xDistance != 0 && yDistance == 0) {
            direction = (to.getX() - from.getX()) > 0 ? 1 : -1;

            for(int i = 1; i < xDistance; ++i) {
                if (!board.isEmptySquare(new Coordinates(from.getX() + i * direction, from.getY()))) {
                    return false;
                }
            }
        }
        else if (xDistance == 0 && yDistance != 0) {
            direction = (to.getY() - from.getY()) > 0 ? 1 : -1;

            for(int i = 1; i < yDistance; ++i) {
                if (!board.isEmptySquare(new Coordinates(from.getX(), from.getY() + i * direction))) {
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
