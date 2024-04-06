package edu.austral.dissis.chess.engine.pieces;

import edu.austral.dissis.chess.engine.boards.Board;
import edu.austral.dissis.chess.engine.coordinates.Coordinates;
import edu.austral.dissis.chess.engine.enums.PieceColor;
import edu.austral.dissis.chess.engine.moves.Move;

import java.util.LinkedList;
import java.util.List;

public abstract class Piece {
    PieceColor pieceColor;
    boolean dead = false;
    private final Coordinates coordinates;

    public Piece(PieceColor pieceColor, Coordinates coordinates) {
        this.pieceColor = pieceColor;
        this.coordinates = coordinates;
    }

    public boolean isSameColour(Piece piece) {
        return this.pieceColor == piece.pieceColor;
    }

    public abstract boolean canDoThisMove(Board board, Move move);

    public List<Coordinates> possibleMoves(Board board){
        List<Coordinates> possibleMoves = new LinkedList<>();
        for(int i = 1; i < board.getXSize(); i++){
            for (int j = 1; j < board.getYSize(); j++){
                Coordinates possibleDestiny = new Coordinates(i, j);
                if(new Move(board, this.coordinates, possibleDestiny).isMoveValid())
                    possibleMoves.add(possibleDestiny);
            }
        }
        return possibleMoves;
    }

    public boolean isBlocked(Board board, Coordinates from) { //Capaz se podria hacer de una manera mas sencilla con la lista de piece.possibleMoves()
        Piece piece = board.getPieceAt(from);

        for (int x = 0; x < board.getXSize(); x++)
            for (int y = 0; y < board.getYSize(); y++) {
                Coordinates tempToCoordinates = new Coordinates(x, y);

                if (new Move(board, from, tempToCoordinates).isMoveValid())
                    return true;
            }
        return false;
    }

    public PieceColor getColor(){
        return pieceColor;
    }

    public void kill(){
        this.dead = true;
    }

    public boolean isDead(){
        return dead;
    }


}
