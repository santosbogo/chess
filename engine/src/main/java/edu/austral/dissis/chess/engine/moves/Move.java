package edu.austral.dissis.chess.engine.moves;

import edu.austral.dissis.chess.engine.boards.Board;
import edu.austral.dissis.chess.engine.coordinates.Coordinates;
import edu.austral.dissis.chess.engine.game.Status;
import edu.austral.dissis.chess.engine.pieces.Piece;

public class Move {
    private final Board board;
    private final Coordinates from;
    private final Coordinates to;
    private final Piece piece;

    public Move(Board board, Coordinates from, Coordinates to){
        this.board = board;
        this.piece = board.getPieceAt(from);
        this.from = from;
        this.to = to;
    }

    public boolean isMoveValid() {
        if (!board.isInBounds(from) || !board.isInBounds(to)) // Check if the coordinates are in bounds
            return false;
        if (board.isEmptySquare(from)) //Check if the from square is empty
            return false;
        if (from.equals(to)) //Check if the from and to squares are the same
            return false;
        if(fromColorEqualsTo()) //Check if the from and to squares have pieces of the same color
            return false;
        if(leavesKingThreatened()) //Check if the move leaves the king in check
            return false;
        if(piece.canDoThisMove(board, this))
            return false;
//        if(!piece.possibleMoves().contains(to)) //Check if the move is in the possible moves list of the piece
//            return false;

        return true;
    }

    private boolean fromColorEqualsTo(){
        return board.getPieceAt(from).getColor() == board.getPieceAt(to).getColor();
    }

    private boolean leavesKingThreatened() {
        Board newBoard = new Board(board.getXSize(), board.getYSize(), board.getBoard(), new Status(board, board.getStatus().getPlayerTurn()));
        newBoard.movePiece(this);
        return newBoard.isSquareThreatened(newBoard.getKingLocation(piece.getColor()));
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
