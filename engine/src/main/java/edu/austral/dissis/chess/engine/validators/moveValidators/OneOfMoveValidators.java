package edu.austral.dissis.chess.engine.validators.moveValidators;

import edu.austral.dissis.chess.engine.board.Board;
import edu.austral.dissis.chess.engine.board.Coordinates;

import java.util.List;

public class OneOfMoveValidators implements MoveValidator {

    private final List<MoveValidator> moveValidators;

    public OneOfMoveValidators(List<MoveValidator> moveValidators) {
        this.moveValidators = moveValidators;
    }

    @Override
    public boolean validMove(Coordinates from, Coordinates to, Board board) {
        for (MoveValidator moveValidator : moveValidators) {
            if (moveValidator.validMove(from, to, board)) {
                return true;
            }
        }
        return false;
    }
}
