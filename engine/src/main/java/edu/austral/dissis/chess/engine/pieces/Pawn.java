package edu.austral.dissis.chess.engine.pieces;

import edu.austral.dissis.chess.engine.coordinates.Coordinates;
import edu.austral.dissis.chess.engine.enums.PieceColor;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {
    private boolean dead = false;
    boolean itsFirstMove = true;


    public Pawn(PieceColor pieceColor){
        super(pieceColor);
    }

    @Override
    public List<Coordinates> possibleMoves() {
        List<Coordinates> posibleMoves = new ArrayList<>();

        //TODO RULES

        return posibleMoves;
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
