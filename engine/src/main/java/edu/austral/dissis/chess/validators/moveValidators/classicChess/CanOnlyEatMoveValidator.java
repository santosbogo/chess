package edu.austral.dissis.chess.validators.moveValidators.classicChess;

import edu.austral.dissis.engine.components.Board;
import edu.austral.dissis.engine.components.Coordinates;
import edu.austral.dissis.engine.validators.moveValidators.MoveValidator;

public class CanOnlyEatMoveValidator implements MoveValidator {
    @Override
    public boolean validMove(Coordinates from, Coordinates to, Board board) {
        return !board.isEmptySquare(to) && isEnemyPiece(from, to, board);
    }

    private boolean isEnemyPiece(Coordinates from, Coordinates to, Board board) {
        return board.getPieceAt(from).getColor() != board.getPieceAt(to).getColor();
    }
}
