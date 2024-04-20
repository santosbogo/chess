package edu.austral.dissis.chess.engine.validators.classicChessMoveValidators;

import edu.austral.dissis.chess.engine.coordinates.Coordinates;
import edu.austral.dissis.chess.engine.enums.PieceColor;
import edu.austral.dissis.chess.engine.validators.MoveValidator;

public class JustForwardMoveValidator implements MoveValidator {
    PieceColor pieceColor;

    public JustForwardMoveValidator(PieceColor pieceColor) {
        this.pieceColor = pieceColor;
    }

    @Override
    public boolean validMove(Coordinates from, Coordinates to) {
        if(pieceColor == PieceColor.WHITE){
            return from.getY() < to.getY();
        } else {
            return from.getY() > to.getY();
        }
    }
}
