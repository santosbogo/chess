package edu.austral.dissis.chess.engine.pieces;

import edu.austral.dissis.chess.engine.boards.Board;
import edu.austral.dissis.chess.engine.coordinates.Coordinates;
import edu.austral.dissis.chess.engine.enums.PieceColor;
import edu.austral.dissis.chess.engine.moves.Move;

import java.util.ArrayList;
import java.util.List;

public class Horse extends Piece {
    private boolean dead = false;

    public Horse(PieceColor pieceColor, Coordinates coordinates){
        super(pieceColor, coordinates);
    }

    @Override
    public boolean canDoThisMove(Board board, Move move) {
        Coordinates from = move.getFrom();
        Coordinates to = move.getTo();
        int xDistance = Math.abs(to.getX() - from.getX());
        int yDistance = Math.abs(to.getY() - from.getY());

        return xDistance * yDistance == 2;
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
