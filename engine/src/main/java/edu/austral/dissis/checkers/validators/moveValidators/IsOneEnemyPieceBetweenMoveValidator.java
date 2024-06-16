package edu.austral.dissis.checkers.validators.moveValidators;

import edu.austral.dissis.checkers.CheckersHelper;
import edu.austral.dissis.engine.components.Board;
import edu.austral.dissis.engine.components.Coordinates;
import edu.austral.dissis.engine.validators.moveValidators.MoveValidator;

public class IsOneEnemyPieceBetweenMoveValidator implements MoveValidator {

    @Override
    public boolean validMove(Coordinates from, Coordinates to, Board board) {
        return new CheckersHelper().isOnlyOneEnemyPieceBetween(from, to, board);
    }
}

