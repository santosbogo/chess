package edu.austral.dissis.checkers.validators.endOfGameValidators;

import edu.austral.dissis.checkers.CheckersHelper;
import edu.austral.dissis.engine.components.Board;
import edu.austral.dissis.engine.enums.PieceColor;
import edu.austral.dissis.engine.enums.StatusOptions;
import edu.austral.dissis.engine.validators.endOfGameValidators.EndOfGameValidator;

public class BoothPlayersCantMoveStalemateEndOfGameValidator implements EndOfGameValidator {
    @Override
    public boolean isEndOfGame(PieceColor colorTurn, Board board) {
        return new CheckersHelper().colorDoesntHaveValidMoves(board, PieceColor.WHITE) &&
                new CheckersHelper().colorDoesntHaveValidMoves(board, PieceColor.BLACK);
    }

    @Override
    public StatusOptions getStatus(PieceColor colorTurn, Board board) {
        return StatusOptions.STALEMATE;
    }
}
