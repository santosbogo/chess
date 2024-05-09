package edu.austral.dissis.chess.engine.referee;

import edu.austral.dissis.chess.engine.board.Board;
import edu.austral.dissis.chess.engine.enums.PieceColor;
import edu.austral.dissis.chess.engine.enums.StatusOptions;
import edu.austral.dissis.chess.engine.validators.endOfGameValidators.EndOfGameValidator;

import java.util.List;

public class StatusReferee {

    public static boolean isEndOfGame(PieceColor colorTurn, Board board, List<EndOfGameValidator> endOfGameValidators) {
        for (EndOfGameValidator endOfGameValidator : endOfGameValidators) {
            if (endOfGameValidator.isEndOfGame(colorTurn, board)) {
                return true;
            }
        }
        return false;
    }

    public static StatusOptions getStatus(PieceColor colorTurn, Board board, List<EndOfGameValidator> endOfGameValidators) {
        for (EndOfGameValidator endOfGameValidator : endOfGameValidators) {
            StatusOptions status = endOfGameValidator.getStatus(colorTurn, board);
            if (status != null) {
                return status;
            }
        }
        return StatusOptions.NORMAL;
    }
}
