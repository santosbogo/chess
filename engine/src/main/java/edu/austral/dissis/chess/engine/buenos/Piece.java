package edu.austral.dissis.chess.engine.buenos;

import edu.austral.dissis.chess.engine.enums.PieceColor;
import edu.austral.dissis.chess.engine.enums.PieceName;
import edu.austral.dissis.chess.engine.validators.moveValidators.MoveValidator;

import java.util.ArrayList;
import java.util.List;

public class Piece {
    List<MoveValidator> moveValidators = new ArrayList<>();
    PieceColor pieceColor;
    boolean isFirstMove = true;

    public Piece(PieceName pieceName , PieceColor pieceColor){
        this.pieceColor = pieceColor;
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

    public PieceColor getColor() {
        return pieceColor;
    }

    public PieceName getPieceName(){
        return null;
    }

    public List<MoveValidator> getMoveValidators(){
        return moveValidators;
    }
}
