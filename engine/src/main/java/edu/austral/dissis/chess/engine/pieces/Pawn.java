package edu.austral.dissis.chess.engine.pieces;

import edu.austral.dissis.chess.engine.components.Board;
import edu.austral.dissis.chess.engine.coordinates.Coordinates;
import edu.austral.dissis.chess.engine.enums.PieceColor;
import edu.austral.dissis.chess.engine.moves.Move;


public class Pawn extends Piece {
    private boolean dead = false;
    private boolean itsFirstMove = true;


    public Pawn(PieceColor pieceColor, Coordinates coordinates){
        super(pieceColor, coordinates);
    }

    @Override
    public boolean canDoThisMove(Board board, Move move) {
        Coordinates from = move.getFrom();
        Coordinates to = move.getTo();
        int xMovement = to.getX() - from.getX();
        int yMovement = to.getY() - from.getY();

        int side = super.getColor().equals(PieceColor.WHITE) ? 1 : -1;

        if (yMovement == side && xMovement == 0 && board.isEmptySquare(to)) {
            return true;
        }
        if (yMovement == 2 * side && xMovement == 0 && board.isEmptySquare(new Coordinates(from.getX(), from.getY() + side)) && itsFirstMove) {
            return true;
        }
        if (yMovement == side && xMovement == 1 && !board.isEmptySquare(to) && !board.getColorAt(to).equals(super.getColor())) {
            return true;
        }

        return false;
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

    public void firstMoveSet() {
        itsFirstMove = false;
    }

    public boolean isFirstMove() {
        return itsFirstMove;
    }
}
