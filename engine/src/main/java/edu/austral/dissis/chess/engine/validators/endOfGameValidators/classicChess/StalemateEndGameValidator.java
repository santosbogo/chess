package edu.austral.dissis.chess.engine.validators.endOfGameValidators.classicChess;

import edu.austral.dissis.chess.engine.buenos.Board;
import edu.austral.dissis.chess.engine.enums.PieceColor;
import edu.austral.dissis.chess.engine.moves.MoveReferee;
import edu.austral.dissis.chess.engine.validators.endOfGameValidators.EndOfGameValidator;

public class StalemateEndGameValidator implements EndOfGameValidator {
    @Override
    public boolean isEndOfGame(PieceColor colorTurn, Board board) {
        MoveReferee moveReferee = new MoveReferee();
        return moveReferee.colorHasNoValidMoves(colorTurn, board);
    }
}
