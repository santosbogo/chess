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

    public abstract boolean isBlocked(Board board, Move move);

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

    public boolean canMove(Board board, Coordinates from) { //Capaz se podria hacer de una manera mas sencilla con la lista de piece.possibleMoves()
        Piece piece = board.getPieceAt(from);

        for (int i = 0; i < board.getXSize(); i++)
            for (int j = 0; j < board.getYSize(); j++) {
                Coordinates tempToCoordinates = new Coordinates(i, j);

                if (board.getPieceAt(from) instanceof King) {
                    if (new Move(board, from, tempToCoordinates).isMoveValid() &&
                            !board.isSquareThreatened(tempToCoordinates))
                        return true; //Si es el rey, el movimiento es valido y no esta amenazado

                } else if (new Move(board, from, tempToCoordinates).isMoveValid())
                    return true; //Si no es el rey y el movimiento es valido
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
