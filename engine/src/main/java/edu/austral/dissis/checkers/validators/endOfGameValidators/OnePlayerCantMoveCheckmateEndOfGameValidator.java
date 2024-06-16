package edu.austral.dissis.checkers.validators.endOfGameValidators;

import edu.austral.dissis.checkers.CheckersHelper;
import edu.austral.dissis.engine.components.Board;
import edu.austral.dissis.engine.enums.PieceColor;
import edu.austral.dissis.engine.enums.StatusOptions;
import edu.austral.dissis.engine.validators.endOfGameValidators.EndOfGameValidator;

public class OnePlayerCantMoveCheckmateEndOfGameValidator implements EndOfGameValidator {
    @Override
    public boolean isEndOfGame(PieceColor colorTurn, Board board) {
        PieceColor enemyColor = colorTurn.equals(PieceColor.WHITE) ? PieceColor.BLACK : PieceColor.WHITE;
        return new CheckersHelper().colorDoesntHaveValidMoves(board, colorTurn) &&
                !new CheckersHelper().colorDoesntHaveValidMoves(board, enemyColor);
    }

    @Override
    public StatusOptions getStatus(PieceColor colorTurn, Board board) {
        if (colorTurn.equals(PieceColor.WHITE)) return StatusOptions.BLACK_WIN;
        else return StatusOptions.WHITE_WIN;

    }
}
