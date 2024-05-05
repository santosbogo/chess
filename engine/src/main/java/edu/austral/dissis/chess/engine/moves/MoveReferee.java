package edu.austral.dissis.chess.engine.moves;

import edu.austral.dissis.chess.engine.buenos.Board;
import edu.austral.dissis.chess.engine.buenos.Coordinates;
import edu.austral.dissis.chess.engine.enums.PieceColor;
import edu.austral.dissis.chess.engine.validators.moveValidators.MoveValidator;

import java.util.List;

public class MoveReferee {

    public boolean isValidMove(Coordinates from, Coordinates to, Board board) {
        List<MoveValidator> validators = board.getPieceAt(from).getMoveValidators();

        for (MoveValidator validator : validators) {
            if (!validator.validMove(from, to, board)) {
                return false;
            }
        }
        return true;
    }

    public boolean colorHasNoValidMoves(PieceColor colorTurn, Board board){
        for (int fromX = 1; fromX <= board.getXSize(); fromX++) {
            for (int fromY = 1; fromY <= board.getYSize(); fromY++) {
                Coordinates tempFrom = new Coordinates(fromX, fromY);

                if (board.isNotEmptySquare(tempFrom) && board.getPieceAt(tempFrom).getColor().equals(colorTurn)) {

                    for (int toX = 1; toX <= board.getXSize(); toX++) {
                        for (int toY = 1; toY <= board.getYSize(); toY++) {
                            Coordinates tempTo = new Coordinates(toX, toY);

                            if (isValidMove(tempFrom, tempTo, board)) {
                                return false;
                            }
                        }
                    }
                }
            }
        }

        return true;
    }
}
