package edu.austral.dissis.chess.engine.validators.moveValidators.classicChess;

import edu.austral.dissis.chess.engine.components.Board;
import edu.austral.dissis.chess.engine.components.Coordinates;
import edu.austral.dissis.chess.engine.validators.moveValidators.MoveValidator;

public class CantEatMoveValidator implements MoveValidator{
    @Override
    public boolean validMove(Coordinates from, Coordinates to, Board board) {
        return board.isEmptySquare(to);
    }
}
