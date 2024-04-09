package edu.austral.dissis.chess.engine.pieces;

import edu.austral.dissis.chess.engine.boards.Board;
import edu.austral.dissis.chess.engine.coordinates.Coordinates;
import edu.austral.dissis.chess.engine.enums.PieceColor;
import edu.austral.dissis.chess.engine.moves.Move;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {
    private boolean itsFirstMove = true;

    public King(PieceColor pieceColor, Coordinates coordinates){
        super(pieceColor, coordinates);
    }

    @Override
    public boolean canDoThisMove(Board board, Move move) {
        if(isCastlingMove(move))
            return canCastle(board, move);

        Coordinates from = move.getFrom();
        Coordinates to = move.getTo();
        int xDistance = Math.abs(to.getX() - from.getX());
        int yDistance = Math.abs(to.getY() - from.getY());

        return xDistance <= 1 && yDistance <= 1;
    }

    public boolean isCastlingMove(Move move){
        return Math.abs(move.getFrom().getX() - move.getTo().getX()) == 2 && move.getFrom().getY() == move.getTo().getY();
    }

    private boolean canCastle(Board board, Move move){
        if (!itsFirstMove)
            return false;

        if (isCastlingMove(move)) {
            Coordinates from = move.getFrom();
            Coordinates to = move.getTo();
            int direction = to.getX() - from.getX() > 0 ? 1 : -1;

            // Ensure the king's current position is not under threat
            if (board.isSquareThreatened(from))
                return false;

            Coordinates rookCoordinates = new Coordinates(direction == 1 ? 8 : 1, from.getY()); //FIXME: this only works for standard chess
            Piece rook = board.getPieceAt(rookCoordinates);

            if (!(rook instanceof Rook) || ((Rook)rook).hasMoved())
                return false;

            int pathLength = direction == 1 ? 2 : 3; // Kingside has 2 squares to check, queenside has 3
            for (int i = 1; i <= pathLength; i++) {
                Coordinates pathCoordinates = new Coordinates(from.getX() + i * direction, from.getY());
                if (!board.isEmptySquare(pathCoordinates) || board.isSquareThreatened(pathCoordinates))
                    return false;
            }

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
        throw new IllegalStateException("King can't be killed!");
    }

    @Override
    public boolean isDead() {
        return false;
    }

    public void alreadyMoved() {
        itsFirstMove = false;
    }

    public boolean hasMoved() {
        return !itsFirstMove;
    }
}
