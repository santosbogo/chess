package edu.austral.dissis.chess.engine.validators.moveValidators.classicChessMoveValidators;

import edu.austral.dissis.chess.engine.components.Board;
import edu.austral.dissis.chess.engine.coordinates.Coordinates;
import edu.austral.dissis.chess.engine.coordinates.Piece;
import edu.austral.dissis.chess.engine.validators.moveValidators.MoveValidator;

import java.util.HashMap;

public class FirstTwoStepsMoveValidator implements MoveValidator {

    @Override
    public boolean validMove(Coordinates from, Coordinates to) {
//        Board board FIXME: De donde saco el board??
        Board board = new Board(8, 8, new HashMap<>()); //FIXME: Esto es solo para que no tire error, pero no esta bien
        Piece piece = board.getPieceAt(from);

        if (piece.isFirstMove() && isTwoStepsMove(from, to))
            return true;

        return false;
    }

    private boolean isTwoStepsMove(Coordinates from, Coordinates to){
        return Math.abs(to.getY() - from.getY()) == 2;
    }


}
