package edu.austral.dissis.checkers.validators.moveValidators;

import edu.austral.dissis.checkers.CheckersHelper;
import edu.austral.dissis.engine.components.Board;
import edu.austral.dissis.engine.components.Coordinates;
import edu.austral.dissis.engine.validators.moveValidators.MoveValidator;

public class DiagonalEatMoveValidator implements MoveValidator {
    private final int maxSquares;

    public DiagonalEatMoveValidator(int maxSquares) {
        this.maxSquares = maxSquares;
    }

    public DiagonalEatMoveValidator() {
        this.maxSquares = 2147483647;
    }

    @Override
    public boolean validMove(Coordinates from, Coordinates to, Board board) {
        return isDiagonalMove(from, to) && isInRange(from, to) && new CheckersHelper().isOnlyOneOpponentPieceBetween(from, to, board);

    }

    private boolean isDiagonalMove(Coordinates from, Coordinates to) {
        return Math.abs(from.getX() - to.getX()) == Math.abs(from.getY() - to.getY());
    }

    private boolean isInRange(Coordinates from, Coordinates to) {
        return Math.abs(to.getX() - from.getX()) <= maxSquares;
    }


}

