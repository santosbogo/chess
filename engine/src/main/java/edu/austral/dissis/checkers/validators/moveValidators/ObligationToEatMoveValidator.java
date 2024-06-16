package edu.austral.dissis.checkers.validators.moveValidators;

import edu.austral.dissis.checkers.CheckersHelper;
import edu.austral.dissis.engine.components.Board;
import edu.austral.dissis.engine.components.Coordinates;
import edu.austral.dissis.engine.enums.PieceColor;
import edu.austral.dissis.engine.validators.moveValidators.MoveValidator;

public class ObligationToEatMoveValidator implements MoveValidator {
    @Override
    public boolean validMove(Coordinates from, Coordinates to, Board board) {
        if(!isEatingMove(from, to, board)){
            return !canAnyTeamPieceEat(board, board.getColorAt(from));
        }
        return true;
    }

    private boolean isEatingMove(Coordinates from, Coordinates to, Board board){
        return new CheckersHelper().isOnlyOneEnemyPieceBetween(from, to, board);
    }

    private boolean canAnyTeamPieceEat(Board board, PieceColor color){
        for (int x = 1; x <= board.getXSize(); x++) {
            for (int y = 1; y <= board.getYSize(); y++) {
                Coordinates from = new Coordinates(x, y);
                if(board.isEmptySquare(from) || !board.getColorAt(from).equals(color)) continue;
                if (!new CheckersHelper().canPieceFromCoordinateEat(from, board)) continue;
                return true;
            }
        }
        return false;
    }

}
