package edu.austral.dissis.chess.engine.pieces;

import edu.austral.dissis.chess.engine.enums.PieceColor;
import edu.austral.dissis.chess.engine.enums.PieceName;
import edu.austral.dissis.chess.engine.validators.MoveValidator;

import java.util.ArrayList;
import java.util.List;

public class Piece {
    List<MoveValidator> moveValidators = new ArrayList<>();
    PieceColor pieceColor;
    boolean isFirstMove = true;

    public Piece(PieceName pieceName , PieceColor pieceColor){
        this.pieceColor = pieceColor;
    }

    public PieceColor getColor() {
        return pieceColor;
    }

    public void addMoveValidator(MoveValidator moveValidator){
        moveValidators.add(moveValidator);
    }

    public void setFirstMove(){
        isFirstMove = false;
    }

    public boolean isFirstMove(){
        return isFirstMove;
    }
}
