package edu.austral.dissis.chess.engine.validators.moveValidators.classicChess;

import edu.austral.dissis.chess.engine.buenos.Board;
import edu.austral.dissis.chess.engine.buenos.Coordinates;
import edu.austral.dissis.chess.engine.enums.PieceColor;
import edu.austral.dissis.chess.engine.validators.moveValidators.MoveValidator;

public class JustForwardMoveValidator implements MoveValidator {
    PieceColor pieceColor;

    public JustForwardMoveValidator(PieceColor pieceColor) {
        this.pieceColor = pieceColor;
    }

    @Override
    public boolean validMove(Coordinates from, Coordinates to, Board board) {
        if(pieceColor == PieceColor.WHITE){
            return from.getY() < to.getY();
        } else {
            return from.getY() > to.getY();
        }
    }
}
