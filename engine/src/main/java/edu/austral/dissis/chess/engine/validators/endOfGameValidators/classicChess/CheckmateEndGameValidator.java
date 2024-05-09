package edu.austral.dissis.chess.engine.validators.endOfGameValidators.classicChess;

import edu.austral.dissis.chess.engine.board.Board;
import edu.austral.dissis.chess.engine.enums.PieceColor;
import edu.austral.dissis.chess.engine.enums.StatusOptions;
import edu.austral.dissis.chess.engine.referee.MoveReferee;
import edu.austral.dissis.chess.engine.validators.endOfGameValidators.EndOfGameValidator;

public class CheckmateEndGameValidator implements EndOfGameValidator {

    @Override
    public boolean isEndOfGame(PieceColor colorTurn, Board board) {
        if(isCheck(colorTurn, board)){
            return new MoveReferee(colorTurn, board).colorHasNoValidMoves();
        }

        return false;
    }

    @Override
    public StatusOptions getStatus(PieceColor colorTurn, Board board) {
        if (isEndOfGame(colorTurn, board)) {
            if (colorTurn.equals(PieceColor.WHITE)) return StatusOptions.BLACK_CHECKMATE;
            else return StatusOptions.WHITE_CHECKMATE;
        }
        return null;
    }

    private boolean isCheck(PieceColor colorTurn, Board board){
        return board.isKingThreatened(colorTurn);
    }
}
