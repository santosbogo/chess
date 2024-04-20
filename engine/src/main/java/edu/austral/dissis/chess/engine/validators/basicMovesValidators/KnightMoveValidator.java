package edu.austral.dissis.chess.engine.validators.basicMovesValidators;

import edu.austral.dissis.chess.engine.coordinates.Coordinates;
import edu.austral.dissis.chess.engine.validators.MoveValidator;

public class KnightMoveValidator implements MoveValidator {

    @Override
    public boolean validMove(Coordinates from, Coordinates to) {
        int xDistance = Math.abs(to.getX() - from.getX());
        int yDistance = Math.abs(to.getY() - from.getY());

        return xDistance * yDistance == 2;
    }
}
