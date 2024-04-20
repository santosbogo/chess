package edu.austral.dissis.chess.engine.validators.basicMovesValidators;

import edu.austral.dissis.chess.engine.coordinates.Coordinates;
import edu.austral.dissis.chess.engine.validators.MoveValidator;

public class HorizontalMoveValidator implements MoveValidator {
    private final int maxSquares;

    public HorizontalMoveValidator(int maxSquares){
        this.maxSquares = maxSquares;
    }

    public HorizontalMoveValidator (){
        this.maxSquares = 2147483647;
    }

    @Override
    public boolean validMove(Coordinates from, Coordinates to) {
        return isHorizontalMove(from, to) && isInRange(from, to);
    }

    private boolean isHorizontalMove(Coordinates from, Coordinates to){
        return from.getX() == to.getX();
    }

    private boolean isInRange(Coordinates from, Coordinates to){
        return Math.abs(to.getX() - from.getX()) <= maxSquares;
    }
}
