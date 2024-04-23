package edu.austral.dissis.chess.engine.components.referee;

import edu.austral.dissis.chess.engine.buenos.Board;
import edu.austral.dissis.chess.engine.buenos.Coordinates;
import edu.austral.dissis.chess.engine.buenos.Piece;
import edu.austral.dissis.chess.engine.validators.moveValidators.MoveValidator;

import java.util.List;

public class MoveReferee {
    private final Board board;
    private final Coordinates from;
    private final Coordinates to;
    private final Piece piece;

    public MoveReferee(Board board, Coordinates from, Coordinates to){
        this.board = board;
        this.piece = board.getPieceAt(from);
        this.from = from;
        this.to = to;
    }

    public boolean isValid() {
        if (board.isOutOfBounds(from) || board.isOutOfBounds(to)) // Check if the coordinates are in bounds
            return false;
        if (board.isEmptySquare(from)) //Check if the from square is empty
            return false;
        if (from.equals(to)) //Check if the from and to squares are the same
            return false;

        return iterateValidators(piece);
    }

    private boolean iterateValidators(Piece piece){
        List<MoveValidator> validators = piece.getMoveValidators();

        for (MoveValidator validator : validators){
            if (!validator.validMove(from, to))
                return false;
        }
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

    public Board getBoard(){
        return board;
    }

}
