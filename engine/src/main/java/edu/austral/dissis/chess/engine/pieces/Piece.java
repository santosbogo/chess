package edu.austral.dissis.chess.engine.pieces;

import edu.austral.dissis.chess.engine.enums.PieceColor;
import edu.austral.dissis.chess.engine.enums.PieceName;
import edu.austral.dissis.chess.engine.validators.MoveValidator;

import java.util.ArrayList;
import java.util.List;

public class Piece {

    List<MoveValidator> moveValidators = new ArrayList<>();
    PieceColor pieceColor;

    public Piece(PieceName pieceName , PieceColor pieceColor){
        this.pieceColor = pieceColor;
    }

    public PieceColor getColor() {
        return pieceColor;
    }
}
