package edu.austral.dissis.chess.engine.validators.moveValidators;

import edu.austral.dissis.chess.engine.coordinates.Coordinates;

public interface MoveValidator {

    public boolean validMove(Coordinates from, Coordinates to);
}
