package edu.austral.dissis.checkers.validators.endOfGameValidators;

import edu.austral.dissis.chess.enums.ChessStatusOptions;
import edu.austral.dissis.engine.components.Board;
import edu.austral.dissis.engine.enums.PieceColor;
import edu.austral.dissis.engine.validators.endOfGameValidators.EndOfGameValidator;

public class DrawEndOfGameValidator implements EndOfGameValidator {
    @Override
    public boolean isEndOfGame(PieceColor colorTurn, Board board) {
        return false;
    }

    @Override
    public ChessStatusOptions getStatus(PieceColor colorTurn, Board board) {
        return null;
    }
}
