package edu.austral.dissis.chess.engine.moves;

import edu.austral.dissis.chess.engine.boards.Board;
import edu.austral.dissis.chess.engine.coordinates.Coordinates;
import edu.austral.dissis.chess.engine.pieces.King;
import edu.austral.dissis.chess.engine.pieces.Piece;

public class Move {
    private final Coordinates from;
    private final Coordinates to;
    private final Piece piece;
    private final Board board;

    public Move(Board board, Piece piece, Coordinates from, Coordinates to){
        this.board = board;
        this.piece = piece;
        this.from = from;
        this.to = to;
    }

    public boolean isMoveValid() {
        if (!board.isInBounds(from) || !board.isInBounds(to))
            return false;
        if (board.isEmptySquare(from))
            return false;
        if (from.equals(to))
            return false;
        if(board.getPieceAt(from).getColor() == board.getPieceAt(to).getColor())
            return false;
        if(!piece.possibleMoves().contains(to))
            return false;

        return true;
    }

    public Coordinates getFrom() {
        return from;
    }

    public Coordinates getTo() {
        return to;
    }

    public Piece getPiece() {
        return piece;
    }
}
