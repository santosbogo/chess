package edu.austral.dissis.checkers.validators.endOfGameValidators;

import edu.austral.dissis.engine.components.Coordinates;
import edu.austral.dissis.engine.enums.StatusOptions;
import edu.austral.dissis.engine.components.Board;
import edu.austral.dissis.engine.enums.PieceColor;
import edu.austral.dissis.engine.validators.endOfGameValidators.EndOfGameValidator;

public class EatAllPiecesCheckmateEndOfGameValidator implements EndOfGameValidator {
    @Override
    public boolean isEndOfGame(PieceColor colorTurn, Board board) {
        return colorDoesntHavePieces(colorTurn, board);
    }

    @Override
    public StatusOptions getStatus(PieceColor colorTurn, Board board) {
        if (colorTurn.equals(PieceColor.WHITE)) return StatusOptions.BLACK_WIN;
        else return StatusOptions.WHITE_WIN;
    }

    private boolean colorDoesntHavePieces(PieceColor color, Board board) {
        for (int x = 1; x <= board.getXSize(); x++) {
            for (int y = 1; y <= board.getYSize(); y++) {
                Coordinates coordinates = new Coordinates(x, y);
                if(board.isEmptySquare(coordinates)) continue;
                if (board.getColorAt(coordinates).equals(color)) return false;
            }
        }
        return true;
    }
}