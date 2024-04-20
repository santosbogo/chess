package edu.austral.dissis.chess.engine.validators.moveValidators.classicChessMoveValidators;

import edu.austral.dissis.chess.engine.coordinates.Coordinates;
import edu.austral.dissis.chess.engine.validators.moveValidators.MoveValidator;

public class EnPassantMoveValidator implements MoveValidator {
    @Override
    public boolean validMove(Coordinates from, Coordinates to) {
        return false;
    }

//    TODO : Implementar, de donde saco el board?? Mismo problema que en clearPathValidator
}
