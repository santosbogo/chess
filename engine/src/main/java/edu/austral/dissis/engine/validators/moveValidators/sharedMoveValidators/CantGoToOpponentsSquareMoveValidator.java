package edu.austral.dissis.engine.validators.moveValidators.sharedMoveValidators;

import edu.austral.dissis.engine.components.Board;
import edu.austral.dissis.engine.components.Coordinates;
import edu.austral.dissis.engine.validators.moveValidators.MoveValidator;

public class CantGoToOpponentsSquareMoveValidator implements MoveValidator {
    @Override
    public boolean validMove(Coordinates from, Coordinates to, Board board) {
        return board.isEmptySquare(to);
    }
}
