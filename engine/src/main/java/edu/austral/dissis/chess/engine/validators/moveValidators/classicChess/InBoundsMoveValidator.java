package edu.austral.dissis.chess.engine.validators.moveValidators.classicChess;

import edu.austral.dissis.chess.engine.buenos.Board;
import edu.austral.dissis.chess.engine.buenos.Coordinates;
import edu.austral.dissis.chess.engine.validators.moveValidators.MoveValidator;

public class InBoundsMoveValidator implements MoveValidator {
    @Override
    public boolean validMove(Coordinates from, Coordinates to, Board board) {
        return board.isInBounds(to);
    }
}
