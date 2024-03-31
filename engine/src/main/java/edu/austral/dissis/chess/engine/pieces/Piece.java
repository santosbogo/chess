package edu.austral.dissis.chess.engine.pieces;

import edu.austral.dissis.chess.engine.boards.Board;
import edu.austral.dissis.chess.engine.coordinates.Coordinates;
import edu.austral.dissis.chess.engine.enums.PieceColor;
import edu.austral.dissis.chess.engine.moves.Move;

import java.util.List;

public abstract class Piece {
    PieceColor pieceColor;
    boolean dead = false;

    public Piece(PieceColor pieceColor) {
        this.pieceColor = pieceColor;
    }

    public boolean isSameColour(Piece piece) {
        return this.pieceColor == piece.pieceColor;
    }

    public List<Coordinates> possibleMoves(){
        //TODO: Implement
        return null;
    }

    public boolean canMove(Board board, Coordinates from) { //Capaz se podria hacer de una manera mas sencilla con la lista de piece.possibleMoves()
        Piece piece = board.getPieceAt(from);

        for (int i = 0; i < board.getXSize(); i++)
            for (int j = 0; j < board.getYSize(); j++) {
                Coordinates tempToCoordinates = new Coordinates(i, j);

                if (board.getPieceAt(from) instanceof King) {
                    if (new Move(board, piece, from, tempToCoordinates).isMoveValid() &&
                            !board.isSquareThreatened(tempToCoordinates))
                        return true; //Si es el rey, el movimiento es valido y no esta amenazado

                } else if (new Move(board, piece, from, tempToCoordinates).isMoveValid())
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
