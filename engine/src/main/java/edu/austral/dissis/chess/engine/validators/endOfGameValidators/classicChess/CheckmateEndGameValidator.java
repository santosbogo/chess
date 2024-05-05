package edu.austral.dissis.chess.engine.validators.endOfGameValidators.classicChess;

import edu.austral.dissis.chess.engine.buenos.Board;
import edu.austral.dissis.chess.engine.enums.PieceColor;
import edu.austral.dissis.chess.engine.moves.MoveReferee;
import edu.austral.dissis.chess.engine.validators.endOfGameValidators.EndOfGameValidator;

public class CheckmateEndGameValidator implements EndOfGameValidator {

    @Override
    public boolean isEndOfGame(PieceColor colorTurn, Board board) {
        if(isCheck(colorTurn, board)){
            return new MoveReferee().colorHasNoValidMoves(colorTurn, board);
        }

        return false;
    }

    private boolean isCheck(PieceColor colorTurn, Board board){
        return board.isKingThreatened(colorTurn);
    }
}
